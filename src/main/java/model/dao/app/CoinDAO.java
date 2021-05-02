package model.dao.app;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
@JacksonXmlRootElement(localName = "coin")
public class CoinDAO implements XmlAppDao {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlElementWrapper(localName = "transactions")
    private List<TransactionDAO> tx;

    public CoinDAO(){}

    public CoinDAO(String id, String name){
        this.id = id;
        this.name = name;
        this.tx = new ArrayList<TransactionDAO>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TransactionDAO> getTx() {
        return tx;
    }

    public void setTx(List<TransactionDAO> tx) {
        this.tx = tx;
    }

    public void setNewTx(TransactionDAO newtx) {
        this.tx.add(newtx);
    }
}
