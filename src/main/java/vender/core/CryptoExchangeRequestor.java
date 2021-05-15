package vender.core;

import okhttp3.Request;

/**
 * @author Milinda
 */
public interface CryptoExchangeRequestor {

    public Request requestByAPIKey(String url, String method, RequestHeader header);


}
