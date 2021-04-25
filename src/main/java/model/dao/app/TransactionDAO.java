package model.dao.app;

/**
 * @author Milinda
 */
public class TransactionDAO {

    private double amount;
    private double dealPrice;
    private String date;
    private MetacoinDAO meta;

    public TransactionDAO() {}

    public TransactionDAO(String date, double amount, double dealPrice) {
        this.date = date;
        this.amount = amount;
        this.dealPrice = dealPrice;
        this.meta = new MetacoinDAO();;
    }

    public TransactionDAO(String date, double amount, double dealPrice, double fee, double equalUSDT) {
        this.date = date;
        this.amount = amount;
        this.dealPrice = dealPrice;
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
}
