package process;

import model.dao.market.MarketCoinDAO;
import model.dao.market.MarketCoinsDAO;
import operation.FileHandler;
import util.Constants;

/**
 * @author Yasara
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
}
