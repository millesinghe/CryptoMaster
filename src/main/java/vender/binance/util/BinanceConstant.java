package vender.binance.util;

/**
 * @author Milinda
 */
public class BinanceConstant {

    public static final String KEYFILE = "vendor/binance.key" ;

    public static final String SERVICE_URL = "service.host.url";
    public static final String ALL_COINS_PRICES = "get.allcoin.prices";
    public static final String ALL_COIN_TRANSACTION = "get.all.transactions";
    public static final String CHECK_CONNECTION = "server.connection.status";
    public static final String LATEST_WALLET_BALANCE = "latest.wallet.balance";

    public static final String X_MBX_API = "X-MBX-APIKEY";
    public static final String HMAC_SHA256 = "HmacSHA256";

    public static final String MSG = "msg";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String SPOT = "SPOT";

    public static final String GET_ALL_WALLET_COIN_ID = "SELECT * FROM coin WHERE bin_amount > 0";


}
