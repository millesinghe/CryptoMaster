package model.dao.db;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import model.dao.xml.TransactionDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
public class Coin {

    private String name;

    private String id;

    private String last;

    private List<Tx> listTx;

    public Coin(){}

    public Coin(String id, String name, String last, Tx tx1){
        this.id = id;
        this.name = name;
        this.last =last;
        this.listTx = new ArrayList<Tx>();
        this.listTx.add(tx1);
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

    public List<Tx> getListTx() {
        return listTx;
    }

    public void setListTx(List<Tx> listTx) {
        this.listTx = listTx;
    }

    public void setNewTx(Tx newtx) {
        this.listTx.add(newtx);
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
