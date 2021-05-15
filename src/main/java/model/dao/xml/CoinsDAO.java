package model.dao.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * @author Milinda
 */
@JacksonXmlRootElement(localName = "coins")
public class CoinsDAO implements XmlAppDao {

    @JacksonXmlProperty(localName = "coin")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CoinDAO> coin;

    public CoinsDAO() {
    }

    public List<CoinDAO> getCoin() {
        return coin;
    }

    public void setCoin(List<CoinDAO> coin) {
        this.coin = coin;
    }
}
