package vender.binance.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.dao.db.Coin;
import model.dao.db.Tx;

/**
 * @author Milinda
 */
public class BinanceCoin extends Coin{

    private String symbol;

    private String price;

    private String amount;

    @JsonIgnore
    private String name;

    public BinanceCoin() {
    }

    public BinanceCoin(String id, String name, Tx tx1) {
        super(id, name,null);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
