package vender.binance.ops;

import helper.FileHandler;
import org.json.JSONObject;
import util.Constants;
import vender.binance.util.BinanceConstant;
import vender.binance.util.BinanceRequestor;
import vender.core.HTTPRequestor;
import vender.core.RequestHeader;

import java.util.HashMap;
import java.util.Properties;

/**
 * @author Milinda
 */
public class BinanceAPI  {

    private Properties propBinance;

    public BinanceAPI(){
        propBinance = FileHandler.loadPropertyFile(FileHandler.propSys.getProperty(Constants.BINANCE_PROP_FILE));
    }

    protected String requestUpdatedCoinsStats(){

        RequestHeader headers = null;
        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.ALL_COINS_PRICES)
                , Constants.GET, headers, null);
        return result;
    }

    protected String requestUsersWalletCoins(){

        RequestHeader headers = new RequestHeader(BinanceRequestor.class);

        HashMap<String,String> parameters = new HashMap<>();

        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.LATEST_WALLET_BALANCE)+ "?"
                , Constants.GET, headers, parameters);
        return result;
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

    protected boolean checkServiceStatus(){
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

    protected String requestTransactionHistory() {

        RequestHeader headers = new RequestHeader(BinanceRequestor.class);

        HashMap<String,String> parameters = new HashMap<>();

        String result = new HTTPRequestor().reqToServer(
                Constants.HTTPS + propBinance.getProperty(BinanceConstant.SERVICE_URL)
                        + propBinance.getProperty(BinanceConstant.ALL_COIN_TRANSACTION)+ "?"
                , Constants.GET, headers, parameters);

       return result;
    }

}
