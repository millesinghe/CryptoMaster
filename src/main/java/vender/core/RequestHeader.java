package vender.core;

import helper.PropertiesLoader;
import model.dao.VendorKeys;
import util.Constants;
import vender.binance.ops.BinanceRequestor;

import java.net.URISyntaxException;

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
