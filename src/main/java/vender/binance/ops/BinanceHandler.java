package vender.binance.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.db.BinCoin;
import model.dao.db.Coin;
import org.json.JSONObject;
import process.BOHelper;
import util.Constants;
import vender.binance.dao.BinanceCoin;
import vender.binance.dao.WalletCoins;
import vender.binance.util.BinanceConstant;
import vender.binance.util.BinanceRequestor;
import vender.core.HTTPRequestor;
import vender.core.RequestHeader;

import java.util.HashMap;

/**
 * @author Milinda
 */
public class BinanceHandler {

    private final String BALANCES = "balances";

    private BinanceAPI api;

    public BinanceHandler(){
        this.api = new BinanceAPI();
    }

    public boolean getUpdatedCoinsStats(){
        if(!api.checkServiceStatus())
            return false;

        RequestHeader headers = null;
        String result = api.requestUpdatedCoinsStats();

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

    public boolean getUsersWalletCoins(){
        if(!api.checkServiceStatus())
            return false;

        RequestHeader headers = new RequestHeader(BinanceRequestor.class);

        HashMap<String,String> parameters = new HashMap<>();

        String result = api.requestUsersWalletCoins();
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

    public boolean requestTransactionHistory() {
        if(!api.checkServiceStatus())
            return false;

        RequestHeader headers = new RequestHeader(BinanceRequestor.class);

        HashMap<String,String> parameters = new HashMap<>();

        String[] buyToken = this.processWalletToken();
        parameters.put("symbol","");

        String result = "";

        JSONObject resultJSON = new JSONObject(result);

        ObjectMapper objectMapper = new ObjectMapper();
        return false;
    }

    private String[] processWalletToken() {
        //this
        return null;
    }
}
