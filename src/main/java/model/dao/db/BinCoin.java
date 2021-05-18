package model.dao.db;

import model.dao.Param;
import util.Constants;

import java.util.List;

/**
 * @author Milinda
 */
public class BinCoin extends Coin{

    private String binPrice;

    private String binAmount;

    private String lbinAmount;


    public BinCoin(){}

    public BinCoin(String id, String name, String walPrice, String walAmount, Tx tx1){
        super(id,name,tx1);
        super.setPrice(walPrice);
        this.binPrice = walPrice;
        this.binAmount = walAmount;

    }

    public BinCoin(String id, String free, String locked) {
        super.setId(id);
        this.binAmount = free;
        this.lbinAmount = locked;
    }

    public String getBinPrice() {
        return binPrice;
    }

    public void setBinPrice(String binPrice) {
        super.setPrice(binPrice);
        this.binPrice = binPrice;
    }

    public String getBinAmount() {
        return binAmount;
    }

    public void setBinAmount(String binAmount) {
        this.binAmount = binAmount;
    }

    public List<Param> bindParamsInsert(Coin coin, List<Param> paramList) {
        BinCoin obj = (BinCoin) coin;
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getPrice()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getAmount()));
        return paramList;
    }


    private String _appendUpdatedValue(String attApp, String attUpdate){
        String res = attUpdate;
        if(attUpdate == null)
            res = attApp;
        return res;
    }

    public List<Param> bindParamsUpdate(Coin ac, Coin uc, List<Param> paramList) {
        BinCoin appCoin = (BinCoin) ac;
        BinCoin upsertCoin = (BinCoin) uc;
        paramList.add(new Param(Constants.DATATYPE_STRING,this._appendUpdatedValue(appCoin.getPrice(),upsertCoin.getPrice())));
        paramList.add(new Param(Constants.DATATYPE_STRING,this._appendUpdatedValue(appCoin.getAmount(),upsertCoin.getAmount())));
        paramList.add(new Param(Constants.DATATYPE_STRING,this._appendUpdatedValue(appCoin.getBinAmount(),upsertCoin.getBinAmount())));

        return paramList;
    }


}
