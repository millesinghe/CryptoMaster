package util;

/**
 * @author Milinda
 */
public class DB_Constants {

    public static final String DB = "crypto.sqlite";
    public static final String SQLITE_DRIVER = "jdbc:sqlite:";

    public static final String ATT_COIN_ID = "coin_id";
    public static final String ATT_COIN_NAME = "coin_name";
    public static final String ATT_COIN_AMOUNT = "coin_amount";
    public static final String ATT_BIN_PRICE = "bin_price";
    public static final String ATT_BIN_AMOUNT = "bin_amount";
    public static final String ATT_CB_AMOUNT = "cb_amount";
    public static final String ATT_CB_PRICE = "cb_price";

    public static final String GET_APP_COIN = "SELECT * FROM coin WHERE coin_id = ?";
    public static final String INSERT_BINANCE_COIN = "INSERT INTO coin (coin_id,coin_name,bin_price,bin_amount) VALUES (?,?,?,?)";
    public static final String UPDATE_BINANCE_COIN = "UPDATE coin SET bin_price = ?, coin_amount = ?, bin_amount = ? WHERE coin_id = ?";

    public static final String GET_TX = "SELECT * FROM tx WHERE id = ?";
    public static final String INSERT_TX = "INSERT INTO tx (id, orderId, walletName, isBuy, buy_coin_type, buy_amount, unit_price, USDTEqual, spend_coin_type, spend_amount, fee,timestamp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String ATT_id = "id";
    public static final String ATT_orderId = "orderId";
    public static final String ATT_walletName = "walletName";
    public static final String ATT_ISBUY = "isBuy";
    public static final String ATT_buy_coin_type = "buy_coin_type";
    public static final String ATT_buy_amount = "buy_amount";
    public static final String ATT_unit_price = "unit_price";
    public static final String ATT_USDTEqual = "USDTEqual";
    public static final String ATT_spend_coin_type = "spend_coin_type";
    public static final String ATT_spend_amount = "spend_amount";
    public static final String ATT_fee = "fee";
    public static final String ATT_timestamp = "timestamp";

}
