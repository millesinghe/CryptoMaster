package process;

import model.dao.app.CoinDAO;
import model.dao.app.CoinsDAO;
import model.dao.app.TransactionDAO;
import model.dao.market.MarketCoinsDAO;
import operation.FileHandler;
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

    public void addNewCoin(String coinId, String name, String date, double amount, double price){
        CoinsDAO coins = boHelper.getBuyList();

        //filter CoinSet from the List
        List<CoinDAO> otherCoins = new ArrayList<CoinDAO>();

        for(CoinDAO coin: coins.getCoin()){
            if (coin.getId().equalsIgnoreCase(coinId)){
                TransactionDAO tx1 = new TransactionDAO(date,amount,price);
                coin.setNewTx(tx1);
                otherCoins.add(coin);
            }else {
                otherCoins.add(coin);
            }
        }

        coins.setCoin(otherCoins);
        boHelper.updateXML("BUY",coins);
    }

    public void removeCoin(String coinId, String name, String date, double amount, double price){
        CoinsDAO coins = new BOHelper().getBuyList();

        //filter CoinSet from the List
        List<CoinDAO> otherCoins = new ArrayList<CoinDAO>();

        for(CoinDAO coin: coins.getCoin()){
            if (coin.getId().equalsIgnoreCase(coinId)){
                TransactionDAO tx1 = new TransactionDAO(date,amount,price);
                coin.setNewTx(tx1);
                otherCoins.add(coin);
            }else {
                otherCoins.add(coin);
            }
        }

        coins.setCoin(otherCoins);
        boHelper.updateXML("SELL",coins);
    }

}
