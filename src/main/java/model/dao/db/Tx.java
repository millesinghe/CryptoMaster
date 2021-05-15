package model.dao.db;

import model.dao.xml.MetacoinDAO;

/**
 * @author Milinda
 */
public class Tx {

    private String timestamp;
    private boolean isBuy;
    private String coinAmount;
    private String unitPrice;
    private String fee;

    public Tx(){
    }

    public Tx(String timestamp, boolean is_buy, String coin_amount, String unit_price, String fee) {
        this.timestamp = timestamp;
        this.isBuy = is_buy;
        this.coinAmount = coin_amount;
        this.unitPrice = unit_price;
        this.fee = fee;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public String getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(String coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
