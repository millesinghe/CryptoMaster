package vender.binance.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.Param;
import model.dao.db.Coin;
import org.json.JSONArray;
import org.json.JSONObject;
import process.BOHelper;
import util.Constants;
import vender.binance.dao.BinanceCoin;
import vender.core.HTTPRequestor;
import vender.core.RequestHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Milinda
 */
public class BinanceAPI  {


    public String getAPI() {
        return null;
    }

    public boolean getUpdatedCoinsStats(){
        if(!checkServiceStatus())
            return false;

        RequestHeader headers = null;
        String result = new HTTPRequestor().reqToServer(Constants.HTTPS +BinanceConstant.SERVICE_URL+BinanceConstant.ALL_COINS_PRICES, Constants.GET, headers);
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

    public boolean getUserPortfolio(){
        return true;
    }

    public boolean checkServiceStatus(){
        String result = new HTTPRequestor().reqToServer(Constants.HTTPS +BinanceConstant.SERVICE_URL+BinanceConstant.CHECK_CONNECTION, Constants.GET, null);
        JSONObject jsonStstus = new JSONObject(result);
        int status = jsonStstus.getInt("status");
        if(status == 0){
            return true;
        }
        return false;
    }
    
}
