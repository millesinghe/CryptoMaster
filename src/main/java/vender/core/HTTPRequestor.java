package vender.core;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Constants;
import vender.binance.ops.BinanceRequestor;

import java.io.IOException;

/**
 * @author Milinda
 */
public class HTTPRequestor {

    public String reqToServer(String url, String method, RequestHeader header){
        String jsonData =null;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = null;
        if(header == null){
            request = new Request.Builder()
                    .url(url)
                    .method(method, null)
                    .addHeader(Constants.CONTENT_TYPE, Constants.APP_JSON)
                    .build();
        }else if (header.getType() == BinanceRequestor.class){
            request = new BinanceRequestor().requestByAPIKey(url, method,header);
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
