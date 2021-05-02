package model.dao.app;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author Milinda
 */
public class TransactionDAO {

    private double amount;
    private double dealPrice;
    private String date;
    private MetacoinDAO meta;

    @JacksonXmlProperty(isAttribute = true)
    private boolean isBuy;

    public TransactionDAO(){}

    public TransactionDAO(String date, double amount, double dealPrice, boolean isBuy) {
        this.date = date;
        this.amount = amount;
        this.dealPrice = dealPrice;
        this.isBuy = isBuy;
        this.meta = new MetacoinDAO();;
    }

    public TransactionDAO(String date, double amount, double dealPrice, double fee, double equalUSDT, boolean isBuy) {
        this.date = date;
        this.amount = amount;
        this.dealPrice = dealPrice;
        this.isBuy = isBuy;
        this.meta = new MetacoinDAO(fee,equalUSDT);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public MetacoinDAO getMeta() {
        return meta;
    }

    public void setMeta(MetacoinDAO meta) {
        this.meta = meta;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }
}
