package vender.binance.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.dao.xml.MetacoinDAO;

/**
 * @author Milinda
 */
public class BinanceTx {

    @JsonIgnore
    private boolean isMaker;

    @JsonIgnore
    private boolean isBestMatch;

    @JsonIgnore
    private boolean orderListId;

    private String id;

    private String orderId;
    private String walletName;
    private String symbol;
    private String price;
    private String qty;
    private String quoteQty;
    private String commission;
    private String commissionAsset;
    private String fee;
    private boolean isBuyer;
    private String time;


    public BinanceTx(){}

    public BinanceTx(String orderId, String walletName, String symbol,
                     String price, String qty, String quoteQty, String commission, String commissionAsset,
                     String fee, boolean isBuyer, String time) {
        this.orderId = orderId;
        this.walletName = walletName;
        this.symbol = symbol;
        this.price = price;
        this.qty = qty;
        this.quoteQty = quoteQty;
        this.commission = commission;
        this.commissionAsset = commissionAsset;
        this.fee = fee;
        this.isBuyer = isBuyer;
        this.time = time;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQuoteQty() {
        return quoteQty;
    }

    public void setQuoteQty(String quoteQty) {
        this.quoteQty = quoteQty;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getCommissionAsset() {
        return commissionAsset;
    }

    public void setCommissionAsset(String commissionAsset) {
        this.commissionAsset = commissionAsset;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public boolean isIsBuyer() {
        return isBuyer;
    }

    public void setBuyer(boolean buyer) {
        isBuyer = buyer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIsMaker() {
        return isMaker;
    }

    public void setMaker(boolean maker) {
        isMaker = maker;
    }

    public boolean isIsBestMatch() {
        return isBestMatch;
    }

    public void setBestMatch(boolean bestMatch) {
        isBestMatch = bestMatch;
    }


    public boolean isMaker() {
        return isMaker;
    }

    public boolean isBestMatch() {
        return isBestMatch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBuyer() {
        return isBuyer;
    }

    public boolean isOrderListId() {
        return orderListId;
    }

    public void setOrderListId(boolean orderListId) {
        this.orderListId = orderListId;
    }
}
