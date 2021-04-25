package process;

import model.dao.app.CoinsDAO;
import model.dao.app.XmlAppDao;
import model.dao.market.MarketCoinDAO;
import model.dao.market.MarketCoinsDAO;
import operation.FileHandler;
import util.Constants;

/**
 * @author Milinda
 */
public class BOHelper {

    public String getCoinName(String id){

        MarketCoinsDAO coins = (MarketCoinsDAO) FileHandler.readXMLFile(Constants.XML_MARKET_COINS, MarketCoinsDAO.class);

        for(MarketCoinDAO coin : coins.getCoin()){
            if (id.equalsIgnoreCase(coin.getId()))
                return coin.getName();
        }
        return null;
    }


    public CoinsDAO getBuyList() {
        return (CoinsDAO) FileHandler.readXMLFile(Constants.XML_BUY_PORTFOLIO, CoinsDAO.class);
    }

    public void updateXML(String updateFile, XmlAppDao pojo) {
        if (updateFile.equals("BUY")){
            FileHandler.writeXMLFile(Constants.XML_BUY_PORTFOLIO,pojo);
        } else if (updateFile.equals("SELL")){
            FileHandler.writeXMLFile(Constants.XML_SELL_PORTFOLIO,pojo);
        }
    }
}
