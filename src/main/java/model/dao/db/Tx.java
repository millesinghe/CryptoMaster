package model.dao.db;

import java.util.List;

/**
 * @author Milinda
 */
public class Tx {

    private String id;

    private String orderId;

    private String walletName;

    private boolean isBuy;

    private String buy_coin_type;

    private String buy_amount;

    private String unit_price;

    private String USDTEqual;

    private String spend_coin_type;

    private String spend_amount;

    private String fee;

    private String timestamp;

    public Tx() {
    }

    public Tx(String id, String orderId, String walletName, boolean isBuy, String buy_coin_type, String buy_amount, String unit_price,
              String USDTEqual, String spend_coin_type, String spend_amount, String fee, String timestamp) {
        this.id = id;
        this.orderId = orderId;
        this.walletName = walletName;
        this.isBuy = isBuy;
        this.buy_coin_type = buy_coin_type;
        this.buy_amount = buy_amount;
        this.unit_price = unit_price;
        this.USDTEqual = USDTEqual;
        this.spend_coin_type = spend_coin_type;
        this.spend_amount = spend_amount;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public String getBuy_coin_type() {
        return buy_coin_type;
    }

    public void setBuy_coin_type(String buy_coin_type) {
        this.buy_coin_type = buy_coin_type;
    }

    public String getBuy_amount() {
        return buy_amount;
    }

    public void setBuy_amount(String buy_amount) {
        this.buy_amount = buy_amount;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getUSDTEqual() {
        return USDTEqual;
    }

    public void setUSDTEqual(String USDTEqual) {
        this.USDTEqual = USDTEqual;
    }

    public String getSpend_coin_type() {
        return spend_coin_type;
    }

    public void setSpend_coin_type(String spend_coin_type) {
        this.spend_coin_type = spend_coin_type;
    }

    public String getSpend_amount() {
        return spend_amount;
    }

    public void setSpend_amount(String spend_amount) {
        this.spend_amount = spend_amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
