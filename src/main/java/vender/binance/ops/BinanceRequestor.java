package vender.binance.ops;

import okhttp3.Request;
import vender.core.CryptoExchangeRequestor;
import vender.core.RequestHeader;

/**
 * @author Milinda
 */
public class BinanceRequestor implements CryptoExchangeRequestor {

    public Request requestByAPIKey(String url, String method, RequestHeader header) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, null)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-MBX-APIKEY",header.getApiKey(this))
                .build();
        return request;
    }

}
