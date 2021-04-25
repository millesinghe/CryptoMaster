package process;

import model.dao.app.CoinDAO;
import model.dao.app.CoinsDAO;
import model.dao.app.TransactionDAO;
import model.dao.market.MarketCoinsDAO;
import operation.FileHandler;
import util.Constants;

/**
 * @author Milinda
 */
public class BOManager {

    public void addNewCoin(String coinId, String name, String date, double amount, double price){
        CoinsDAO coins = this.getBuyList();

        CoinDAO c = new CoinDAO(coinId,name);
        TransactionDAO tx1 = new TransactionDAO(date,amount,price);
        c.setTx(null);

        FileHandler.writeXMLFile(Constants.XML_BUY_PORTFOLIO,coins);
    }

    private CoinsDAO getBuyList() {
        CoinsDAO coins = (CoinsDAO) FileHandler.readXMLFile(Constants.XML_BUY_PORTFOLIO, CoinsDAO.class);
        return coins;
    }
}
