package process;

import model.dao.app.CoinDAO;
import model.dao.app.TransactionDAO;
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

    public void addNewCoin(boolean isBuy, String coinId, String name, String date, double amount, double price, double fee, double equalUSDT){
        String opsFile = new StringBuilder(Constants.XML_DEAL_RECORDS)
                .append(coinId)
                .append(Constants.XML_FILE).toString();
        CoinDAO coin = this.addCoinRecord(isBuy, opsFile,coinId, name, date, amount, price, fee, equalUSDT);
        boHelper.updateXML(opsFile,coin, coinId);
    }

    private CoinDAO addCoinRecord(boolean isBuy, String opsFile, String coinId, String name, String date, double amount, double price, double fee, double equalUSDT){
        CoinDAO coin = boHelper.getBuyList(opsFile,coinId);
        if (coin != null){
            TransactionDAO tx1 = new TransactionDAO(date,amount,price,fee,equalUSDT, isBuy);
            coin.setNewTx(tx1);
        }else{
            TransactionDAO tx1 = new TransactionDAO(date,amount,price,fee,equalUSDT,isBuy);
            coin = new CoinDAO(coinId,name);
            ArrayList<TransactionDAO> txs = new ArrayList<TransactionDAO>();
            txs.add(tx1);
            coin.setTx(txs);
        }

        return coin;
    }
}
