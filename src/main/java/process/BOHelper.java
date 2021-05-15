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
        return coinHandler.getCoinName(id.toUpperCase());
    }

    public boolean upsertCoinDB(Coin obj){
        return coinHandler.upsertCoinDB(obj);
    }


    public boolean insertTxDB(Coin obj){
        coinHandler.insertTx(obj);
        return true;
    }


    public void updateXML(String updateFile, XmlAppDao pojo, String coinname) {
        FileHandler.writeCoibnDataToXML(updateFile,pojo, coinname);
    }
}
