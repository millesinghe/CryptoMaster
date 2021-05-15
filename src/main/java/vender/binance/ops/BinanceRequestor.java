package vender.binance.ops;

import helper.PropertiesLoader;
import model.dao.VendorKeys;
import okhttp3.Request;
import util.Constants;
import vender.core.CryptoExchangeRequestor;
import vender.core.RequestHeader;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Milinda
 */
public class BinanceRequestor implements CryptoExchangeRequestor {

    private  VendorKeys apiKeys;

    public VendorKeys getApiKeys() {
        return apiKeys;
    }

    public BinanceRequestor() {
        this.generateVendorAuth();
    }

    private VendorKeys generateVendorAuth(){
        String propKey = null;
        String propSecret = null;

        propKey = Constants.API_KEY_BINANCE;
        propSecret = Constants.API_SECRET_BINANCE;

        if (propKey == null)
            return null;

        try {
                String apiKey = new PropertiesLoader().getPropertyFromProp(Constants.PROP_USER,propKey).toString();
                String apiSecret = new PropertiesLoader().getPropertyFromProp(Constants.PROP_USER,propSecret).toString();
                apiKeys = new VendorKeys(apiKey,apiSecret);
        } catch (URISyntaxException e) {
                e.printStackTrace();
         }
         return apiKeys;
    }

    public Request requestByAPIKey(String url, String method, RequestHeader header) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, null)
                .addHeader(Constants.CONTENT_TYPE, Constants.APP_JSON)
                .addHeader(BinanceConstant.X_MBX_API,this.getApiKeys().getApiKeys())
                .build();
        return request;
    }

    public String bindParamWithSign(HashMap<String, String> parameters) {
        String queryPath = "";
        String signature = "";
        String timestamp = getTimeStamp();
        if (!parameters.isEmpty()) {
            queryPath += jsonQueryParam(parameters) + "&" + timestamp;
        } else {
            queryPath += getTimeStamp();
        }
        try {
            signature = this.getSignature(queryPath, this.getApiKeys().getApiSecrets());
        }
        catch (Exception e) {
            System.out.println("Please Ensure Your Secret Key Is Set Up Correctly! " + e);
            System.exit(0);
        }
        queryPath += "&signature=" + signature;

        return queryPath;
    }

    private String jsonQueryParam(HashMap<String,String> parameters) {
        String urlPath = "";
        boolean isFirst = true;

        for (Map.Entry mapElement : parameters.entrySet()) {
            if (isFirst) {
                isFirst = false;
                urlPath += (String)mapElement.getKey() + "=" + (String)mapElement.getValue();
            } else {
                urlPath += "&" + (String)mapElement.getKey() + "=" + (String)mapElement.getValue();
            }
        }
        return urlPath;
    }

    private String getSignature(String data, String key) {
        byte[] hmacSha256 = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), BinanceConstant.HMAC_SHA256);
            Mac mac = Mac.getInstance(BinanceConstant.HMAC_SHA256);
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return bytesToHex(hmacSha256);
    }

    private String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String getTimeStamp() {
        long timestamp = System.currentTimeMillis();
        return "timestamp=" + String.valueOf(timestamp);
    }
}
