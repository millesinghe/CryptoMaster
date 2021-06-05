package process;

import model.dao.ProfitDAO;
import model.dao.db.Coin;
import model.dao.db.Tx;
import model.dao.xml.XmlAppDao;
import db.helper.CoinHandler;
import helper.FileHandler;
import util.Constants;
import util.DB_Constants;
import vender.binance.util.BinanceConstant;
import vender.binance.util.BinanceRequestor;
import vender.core.HTTPRequestor;
import vender.core.RequestHeader;

import java.util.HashMap;
import java.util.List;

/**
 * @author Milinda
 */
public class BOHelper {

    public CoinHandler coinHandler;

    public BOHelper(){
        if (this.coinHandler == null) {
            coinHandler = new CoinHandler();
        }
    }

    public List<ProfitDAO> getWalletProfit(boolean isBuy, String fromDate){
        return coinHandler.getWalletProfit(isBuy, fromDate);
    }

    public String getCoinName(String id){
        return coinHandler.getCoin(id.toUpperCase()).getName();
    }

    public boolean upsertCoinDB(String coinMarket, Coin obj){
        return coinHandler.upsertCoinDB(coinMarket, obj);
    }

    public boolean upsertTxDB(String coinMarket, Tx obj){
        return coinHandler.upsertTxDB(coinMarket, obj);
    }

    public List<Coin> getWalletCoins(String coinMarket){
        return coinHandler.getWalletCoins(coinMarket);
    }


    public boolean insertTxDB(Coin obj){
        //coinHandler.insertTx(obj);
        return true;
    }


    public void updateXML(String updateFile, XmlAppDao pojo, String coinname) {
        FileHandler.writeCoibnDataToXML(updateFile,pojo, coinname);
    }
}
