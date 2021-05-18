package vender.binance.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.FileHandler;
import model.dao.db.BinCoin;
import model.dao.db.Coin;
import org.json.JSONObject;
import process.BOHelper;
import util.Constants;
import vender.binance.dao.BinanceCoin;
import vender.binance.dao.WalletCoins;
import vender.core.HTTPRequestor;
import vender.core.RequestHeader;

import java.util.HashMap;
import java.util.Properties;

/**
 * @author Milinda
 */
public class BinanceAPI  {

    public final String DATA = "data";
    private final String FiVE = "5";
    private final String BALANCES = "balances";

    private Properties propBinance;

    public BinanceAPI(){
        propBinance = FileHandler.loadPropertyFile(FileHandler.propSys.getProperty(Constants.BINANCE_PROP_FILE));
    }

    public boolean requestUpdatedCoinsStats(){
        if(!checkServiceStatus())
            return false;

        RequestHeader headers = null;
        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.ALL_COINS_PRICES)
                , Constants.GET, headers, null);
        ObjectMapper objectMapper = new ObjectMapper();
        BinanceCoin[] bcoins = null;
        try {
            bcoins = objectMapper.readValue(result, BinanceCoin[].class);
            if(this.upsertMarketCoins(bcoins)){
                System.out.println("Successfully imported coin information");
            }else{
                System.err.println("Exception occurred while importing the coins information");;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean upsertMarketCoins(BinanceCoin[] bcoins) {
        for (BinanceCoin bcoin:bcoins){
            String id = bcoin.getSymbol();
            if(id.substring(id.length()-4,id.length()).equalsIgnoreCase(Constants.USDT)){
                if(id.equalsIgnoreCase(Constants.USDT)){
                    id = Constants.USDT;
                }else{
                    id = id.replace(Constants.USDT,Constants.EMPTY_STR);
                }
                Coin coin = new BinCoin(id, Constants.EMPTY_STR, bcoin.getPrice(), "0", null);
                boolean isInserted = new BOHelper().upsertCoinDB(Constants.BINANCE,coin);
                if (!isInserted){
                    System.err.println("Exception while inserting a new Coin");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean requestUsersWalletCoins(){
        if(!checkServiceStatus())
            return false;

        RequestHeader headers = new RequestHeader(BinanceRequestor.class);

        HashMap<String,String> parameters = new HashMap<>();

        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.LATEST_WALLET_BALANCE)+ "?"
                , Constants.GET, headers, parameters);
        JSONObject resultJSON = new JSONObject(result);

        ObjectMapper objectMapper = new ObjectMapper();
        WalletCoins[] walletCoins = null;
        try {
            walletCoins = objectMapper.readValue(resultJSON.get(BALANCES).toString(), WalletCoins[].class);
            if(upsertWalletBalance(walletCoins)){
                System.out.println("Successfully imported User's Wallet information");
            }else{
                System.err.println("Exception occurred while importing the User's Wallet information");;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean upsertWalletBalance(WalletCoins[] wcoins) {
        for (WalletCoins wcoin:wcoins){
            if(Double.parseDouble(wcoin.getFree()) > 0 || Double.parseDouble(wcoin.getLocked()) > 0 ){
                String id = wcoin.getAsset();
                System.out.println(id);
                Coin coin = new BinCoin(id, wcoin.getFree(), wcoin.getLocked());
                boolean isInserted = new BOHelper().upsertCoinDB(Constants.BINANCE,coin);
                if (!isInserted){
                    System.err.println("Exception while inserting a new Coin");
                    return false;
                }
            }
        }
        return true;

    }


    public void orderACoin(String symbol, String side, String type, String timeInForce, String quantity, String price) throws Exception {
        HashMap<String,String> parameters = new HashMap<String,String>();
        parameters.put("symbol", symbol);
        parameters.put("side", side);
        parameters.put("type", type);
        parameters.put("timeInForce", timeInForce);
        parameters.put("quantity", quantity);
        parameters.put("price", price);
       // httpRequest.sendSignedRequest(parameters, "/api/v3/order", "POST");
        parameters.clear();
    }

    public boolean checkServiceStatus(){
        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.CHECK_CONNECTION),
                Constants.GET, null, null);
        JSONObject jsonStstus = new JSONObject(result);
        int status = jsonStstus.getInt(BinanceConstant.STATUS);
        if(status == 0){
            return true;
        }
        return false;
    }

    public boolean insertDefaultCoins() {
        // Forcefully adding USDT
        Coin coin = new BinCoin(Constants.USDT, Constants.TETHER, "1", "0", null);
        boolean isInserted = new BOHelper().upsertCoinDB(Constants.BINANCE,coin);
        if (!isInserted){
            System.err.println("Exception while inserting a new Coin");
            return false;
        }
        return isInserted;
    }
}
