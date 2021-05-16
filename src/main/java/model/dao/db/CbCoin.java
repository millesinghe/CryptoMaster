package model.dao.db;

import model.dao.Param;

import java.util.List;

/**
 * @author Milinda
 */
public class CbCoin extends Coin{

    private String cbPrice;

    private String cbAmount;


    public CbCoin(){}

    public CbCoin(String id, String name, String walPrice, String walAmount, Tx tx1){
    }

    @Override
    public String getCbPrice() {
        return cbPrice;
    }

    @Override
    public void setCbPrice(String cbPrice) {
        this.cbPrice = cbPrice;
    }

    @Override
    public String getCbAmount() {
        return cbAmount;
    }

    @Override
    public void setCbAmount(String cbAmount) {
        this.cbAmount = cbAmount;
    }

    public List<Param> bindParamsInsert(Coin obj, List<Param> paramList) {
        //Un-implemented Method
        return null;
    }

    public List<Param> bindParamsUpdate(Coin appCoin, Coin upsertCoin, List<Param> paramList) {
        //Un-implemented Method
        return null;
    }
}
