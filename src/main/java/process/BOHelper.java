package process;

import model.dao.app.CoinDAO;
import model.dao.app.XmlAppDao;
import model.dao.market.MarketCoinDAO;
import model.dao.market.MarketCoinsDAO;
import operation.FileHandler;
import operation.PropertiesLoader;
import util.Constants;

import java.io.File;
import java.net.URISyntaxException;

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


    public CoinDAO getBuyList(String readFile, String coinId) {

        boolean file = false;
        try {
            file = new PropertiesLoader().getFileFromResource(readFile).exists();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.err.println("New Coin detected");
        }
        if (file == false)
                return null;
        return (CoinDAO) FileHandler.readXMLFile(readFile, CoinDAO.class);
    }

    public void updateXML(String updateFile, XmlAppDao pojo, String coinname) {
        FileHandler.writeCoibnDataToXML(updateFile,pojo, coinname);
    }
}
