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
    public static final String ATT_ISBUY = "is_buy";

    public static final String GET_APP_COIN = "SELECT * FROM coin WHERE coin_id = ?";
    public static final String INSERT_BINANCE_COIN = "INSERT INTO coin (coin_id,coin_name,bin_price,bin_amount) VALUES (?,?,?,?)";
    public static final String UPDATE_BINANCE_COIN = "UPDATE coin SET coin_name = ?, bin_price = ?,  bin_amount = ? WHERE coin_id = ?";

    public static final String INSERT_TX = "INSERT INTO tx (timestamp, coin_id, is_buy, coin_amount, unit_price, fee) VALUES (?,?,?,?,?,?)";

}
