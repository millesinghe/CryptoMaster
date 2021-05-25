package vender.core;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.Constants;
import vender.binance.util.BinanceRequestor;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Milinda
 */
public class HTTPRequestor {

    public String reqToServer(String url, String httpMethod, RequestHeader header, HashMap<String, String> parameters){
        String jsonData =null;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = null;
        if(header == null){
            request = new Request.Builder()
                    .url(url)
                    .method(httpMethod, null)
                    .addHeader(Constants.CONTENT_TYPE, Constants.APP_JSON)
                    .build();
        }else if (header.getType() == BinanceRequestor.class){
            BinanceRequestor binaceRequest = new BinanceRequestor();
            String quaryParam = "";
            if(parameters != null){
                quaryParam = binaceRequest.bindParamWithSign(parameters);
            }
            request = binaceRequest.requestByAPIKey(url+quaryParam, httpMethod,header);
        }
        Response response = null;
        try {
            response = client.newCall(request).execute();
            jsonData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

}
