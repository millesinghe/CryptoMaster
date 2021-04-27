package process;

import model.dao.app.CoinDAO;
import model.dao.app.CoinsDAO;
import model.dao.app.TransactionDAO;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
public class BOManager {

    private BOHelper boHelper;

    public BOManager(){
        boHelper = new BOHelper();
    }

    public void addNewCoin(String coinId, String name, String date, double amount, double price, double fee, double equalUSDT){
        CoinsDAO coins = this.addCoinRecord(coinId, name, date, amount, price, fee, equalUSDT);
        boHelper.updateXML(Constants.XML_BUY_PORTFOLIO,coins);
    }

    public void removeCoin(String coinId, String name, String date, double amount, double price, double fee, double equalUSDT){
        CoinsDAO coins = this.addCoinRecord(coinId, name, date, amount, price, fee, equalUSDT);
        boHelper.updateXML(Constants.XML_SELL_PORTFOLIO,coins);
    }

    private CoinsDAO addCoinRecord(String coinId, String name, String date, double amount, double price, double fee, double equalUSDT){
        CoinsDAO coins = boHelper.getBuyList();

        //filter CoinSet from the List
        List<CoinDAO> otherCoins = new ArrayList<CoinDAO>();

        boolean isExistingCoin = false;
        for(CoinDAO coin: coins.getCoin()){
            if (coin.getId().equalsIgnoreCase(coinId)){
                TransactionDAO tx1 = new TransactionDAO(date,amount,price,fee,equalUSDT);
                coin.setNewTx(tx1);
            }
            otherCoins.add(coin);
        }
        if(!isExistingCoin){
            CoinDAO newCoin = new CoinDAO(coinId,name);
            TransactionDAO tx1 = new TransactionDAO(date,amount,price,fee,equalUSDT);
            newCoin.setNewTx(tx1);
            otherCoins.add(newCoin);
        }
        coins.setCoin(otherCoins);
        return coins;
    }
}
