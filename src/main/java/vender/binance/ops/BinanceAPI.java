package vender.binance.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.FileHandler;
import model.dao.Param;
import model.dao.db.Coin;
import org.json.JSONArray;
import org.json.JSONObject;
import process.BOHelper;
import util.Constants;
import vender.binance.dao.BinanceCoin;
import vender.binance.dao.WalletCoins;
import vender.core.HTTPRequestor;
import vender.core.RequestHeader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Milinda
 */
public class BinanceAPI  {

    private Properties propBinance;

    public BinanceAPI(){
        propBinance = FileHandler.loadPropertyFile(FileHandler.propSys.getProperty(Constants.BINANCE_PROP_FILE));
    }

    public boolean getUpdatedCoinsStats(){
        if(!checkServiceStatus())
            return false;

        RequestHeader headers = null;
        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.ALL_COINS_PRICES)
                , Constants.GET, headers, null);
        ObjectMapper objectMapper = new ObjectMapper();
        BinanceCoin[] bcoin = null;
        try {
            bcoin = objectMapper.readValue(result, BinanceCoin[].class);
            if(this.processList(bcoin)){
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

    private boolean processList(BinanceCoin[] bcoins) {
        for (BinanceCoin bcoin:bcoins){
            if(bcoin.getSymbol().contains(Constants.USDT)){
                String coinId;
                String id = bcoin.getSymbol();
                if(id.equalsIgnoreCase(Constants.USDT)){
                    coinId = Constants.USDT;
                }else{
                    id = id.replace(Constants.USDT,Constants.EMPTY_STR);
                }
                Coin coin = new Coin(id, Constants.EMPTY_STR, bcoin.getPrice(), null);
                boolean isInserted = new BOHelper().upsertCoinDB(coin);
                if (!isInserted){
                    System.err.println("Excetion while inserting a new Coin");
                    return false;
                }
            }
        }
        return true;

    }

    public boolean getUsersPortfolioCoins(){
        if(!checkServiceStatus())
            return false;

        RequestHeader headers = new RequestHeader(BinanceRequestor.class);

        HashMap<String,String> parameters = new HashMap<>();
        parameters.put(BinanceConstant.TYPE,BinanceConstant.SPOT);
        parameters.put(Constants.LIMIT,"5");

        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.DAILY_WALLET_SNAPSHOT)+ "?"
                , Constants.GET, headers, parameters);
        JSONObject resultJSON = new JSONObject(result);
        result = new JSONObject(
                new JSONObject(
                        new JSONArray(
                                resultJSON.get("snapshotVos").toString()
                        ).get(4).toString()
                ).get("data").toString()).get("balances").toString();
        ObjectMapper objectMapper = new ObjectMapper();
        WalletCoins[] walletCoins = null;
        try {
            walletCoins = objectMapper.readValue(result, WalletCoins[].class);
            System.out.println(walletCoins);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
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
        int status = jsonStstus.getInt("status");
        if(status == 0){
            return true;
        }
        return false;
    }
    
}
