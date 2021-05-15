package vender.binance.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Milinda
 */
public class BinanceCoin {

    private String symbol;

    private String price;

    @JsonIgnore
    private String name;

    public BinanceCoin() {
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
}
