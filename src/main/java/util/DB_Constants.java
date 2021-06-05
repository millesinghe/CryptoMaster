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
    public static final String INSERT_TX = "INSERT INTO tx (id, orderId, walletName, isBuy, buy_coin_type, buy_amount, unit_price, USDTEqual, spend_coin_type, spend_amount, fee, fee_coin_type,timestamp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
    public static final String ATT_fee_coin_type = "fee_coin_type";
    public static final String ATT_timestamp = "timestamp";

    public static final String ATT_COIN = "coin";
    public static final String ATT_TOT_ASSETS = "total_assets";
    public static final String ATT_TOT_COST = "total_cost";
    public static final String ATT_UNIT_COST = "unit_cost";
    public static final String ATT_MARKET_PRICES = "market_price";
    public static final String ATT_UNIT_PROFIT = "unit_profit";
    public static final String ATT_TOT_PROFIT = "total_profit";
    public static final String ATT_TOT_GAIN = "profit_gain";
    public static final String ATT_CUR_VALUE = "current_value";

    public static final String GET_COIN_PROFIT = "SELECT * FROM view_buy_coin_stats";
    public static final String GET_COIN_PROFIT_BY_DATE = "SELECT buy_coin_type AS 'coin', SUM(buy_amount) AS " +
            "'total_assets', SUM(spend_amount) AS 'total_cost', SUM(spend_amount)/SUM(buy_amount) AS 'unit_cost', " +
            "bin_price AS 'market_price',(coin.bin_price - SUM(spend_amount)/SUM(buy_amount)) AS 'unit_profit'," +
            "(coin.bin_price - SUM(spend_amount)/SUM(buy_amount))*SUM(buy_amount) AS 'total_profit', " +
            "(coin.bin_price * SUM(buy_amount)) AS 'current_value'," +
            "coin.bin_price-SUM(spend_amount)/SUM(buy_amount))/coin.bin_price*100  AS 'profit_gain'" +
            "FROM tx INNER JOIN coin" +
            " on coin.coin_id = tx.buy_coin_type WHERE tx.isBuy = ? AND tx.timestamp > ? GROUP BY tx.buy_coin_type";

    public static final String GET_COIN_SELL_PROFIT = "SELECT * FROM view_sell_coin_stats";

    public static final String FILTER_COIN_STATS = "SELECT buy_coin_type AS 'coin', SUM(buy_amount) AS 'total_assets', " +
                                "SUM(spend_amount) AS 'total_cost', SUM(spend_amount)/SUM(buy_amount) AS 'unit_cost', " +
                                "bin_price AS 'market_price', " +
                                "(coin.bin_price - SUM(spend_amount)/SUM(buy_amount)) AS 'unit_profit', " +
                                "(coin.bin_price - SUM(spend_amount)/SUM(buy_amount))*SUM(buy_amount)  AS 'total_profit' " +
                                "FROM tx INNER JOIN coin on coin.coin_id = tx.buy_coin_type WHERE tx.isBuy = ? " +
                                "AND ? < tx.timestamp AND tx.timestamp < ? GROUP BY tx.buy_coin_type";

}
