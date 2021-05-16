package process;

import model.dao.db.Coin;
import model.dao.xml.XmlAppDao;
import db.helper.CoinHandler;
import helper.FileHandler;

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

    public String getCoinName(String id){
        return coinHandler.getCoin(id.toUpperCase()).getName();
    }

    public boolean upsertCoinDB(String coinMarket, Coin obj){
        return coinHandler.upsertCoinDB(coinMarket, obj);
    }


    public boolean insertTxDB(Coin obj){
        coinHandler.insertTx(obj);
        return true;
    }


    public void updateXML(String updateFile, XmlAppDao pojo, String coinname) {
        FileHandler.writeCoibnDataToXML(updateFile,pojo, coinname);
    }
}
