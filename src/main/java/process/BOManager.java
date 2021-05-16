package process;

import model.dao.db.Coin;
import model.dao.db.Tx;
import util.Constants;

import java.util.ArrayList;

/**
 * @author Milinda
 */
public class BOManager {

    private BOHelper boHelper;

    public BOManager(){
        boHelper = new BOHelper();
    }

    public void addNewCoinTx(boolean isBuy, String coinId, String name, String date, String amount, String price, String fee){
        Coin coin = this.addCoinRecord(isBuy, coinId.toUpperCase(), name, date, amount, price, fee);
        boHelper.insertTxDB(coin);
    }

    private Coin addCoinRecord(boolean isBuy, String coinId, String name, String date, String amount, String unit, String fee){
        Tx tx1 = new Tx(date, isBuy, amount, unit,fee);
        Coin coin = new Coin(coinId,name,tx1);
        return coin;
    }
}
