package util;

/**
 * @author Milinda
 */
public class DB_Constants {

    public static final String DB = "crypto.sqlite";
    public static final String SQLITE_DRIVER = "jdbc:sqlite:";

    public static final String ATT_COIN_ID = "coin_id";
    public static final String ATT_COIN_NAME = "coin_name";
    public static final String ATT_COIN_LAST = "last_price";
    public static final String ATT_ISBUY = "is_buy";

    public static final String GET_COIN_NAMES = "SELECT coin_name FROM coin WHERE coin_id = ?";
    public static final String GET_COIN_BYID = "SELECT * FROM coin WHERE coin_id = ?";
    public static final String INSERT_COIN = "INSERT INTO coin (coin_id, coin_name, last_price) VALUES (?,?,?)";
    public static final String INSERT_TX = "INSERT INTO tx (timestamp, coin_id, is_buy, coin_amount, unit_price, fee) VALUES (?,?,?,?,?,?)";

}
