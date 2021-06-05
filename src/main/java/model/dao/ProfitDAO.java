package model.dao;

/**
 * @author Milinda
 */
public class ProfitDAO {

    private String coin;
    private String total_assets;
    private String total_cost;
    private String unit_cost;
    private String current_value;
    private String market_price;
    private String unit_profit;
    private String total_profit;
    private String invest_gain;

    public ProfitDAO() {
    }

    public ProfitDAO(String coin, String total_assets, String total_cost, String unit_cost, String market_price, String current_value, String unit_profit, String total_profit, String invest_gain) {
        this.coin = coin;
        this.total_assets = total_assets;
        this.total_cost = total_cost;
        this.unit_cost = unit_cost;
        this.market_price = market_price;
        this.unit_profit = unit_profit;
        this.total_profit = total_profit;
        this.invest_gain = invest_gain;
        this.current_value = current_value;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getTotal_assets() {
        return total_assets;
    }

    public void setTotal_assets(String total_assets) {
        this.total_assets = total_assets;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getUnit_profit() {
        return unit_profit;
    }

    public void setUnit_profit(String unit_profit) {
        this.unit_profit = unit_profit;
    }

    public String getTotal_profit() {
        return total_profit;
    }

    public void setTotal_profit(String total_profit) {
        this.total_profit = total_profit;
    }

    public String getInvest_gain() {
        return invest_gain;
    }

    public String getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(String current_value) {
        this.current_value = current_value;
    }

    public void setInvest_gain(String invest_gain) {
        this.invest_gain = invest_gain;


    }
}
