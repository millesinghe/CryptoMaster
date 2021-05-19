package vender.core;

import vender.binance.util.BinanceRequestor;


/**
 * @author Milinda
 */
public class RequestHeader {

    private Class<BinanceRequestor> type;

    public RequestHeader(Class<BinanceRequestor> type) {
        this.type = type;
    }

    public Class<BinanceRequestor> getType() {
        return type;
    }

}
