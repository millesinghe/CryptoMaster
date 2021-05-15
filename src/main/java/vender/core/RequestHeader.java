package vender.core;

import helper.PropertiesLoader;
import util.Constants;
import vender.binance.ops.BinanceRequestor;

import java.net.URISyntaxException;

/**
 * @author Milinda
 */
public class RequestHeader {

    private String apiKey;

    private Class<BinanceRequestor> type;

    public RequestHeader(Class<BinanceRequestor> type) {
        this.type = type;
    }

    public Class<BinanceRequestor> getType() {
        return type;
    }

    public String getApiKey(CryptoExchangeRequestor requestor) {
        String propKey = null;

        if(requestor instanceof BinanceRequestor) {
            propKey = Constants.API_KEY_BINANCE;
        }
        if (propKey == null)
            return null;

        Object propertyFromProp = null;
        try {
            propertyFromProp = new PropertiesLoader().getPropertyFromProp(Constants.PROP_USER,propKey);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        apiKey = String.valueOf(propertyFromProp);

        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
