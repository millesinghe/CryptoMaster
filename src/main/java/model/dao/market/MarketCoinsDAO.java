package model.dao.market;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import model.dao.app.CoinDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
@JacksonXmlRootElement(localName = "coins")
public class MarketCoinsDAO {

    @JacksonXmlProperty(localName = "coin")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<MarketCoinDAO> coin;

    public MarketCoinsDAO() {
        this.coin=new ArrayList<MarketCoinDAO>();
    }

    public List<MarketCoinDAO> getCoin() {
        return coin;
    }

    public void setCoin(List<MarketCoinDAO> coin) {
        this.coin = coin;
    }

}
