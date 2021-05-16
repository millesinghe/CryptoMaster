package model.dao.db;

import model.dao.Param;
import util.Constants;
import util.DB_Constants;
import vender.binance.ops.BinanceConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
public class BinCoin extends Coin{

    private String binPrice;

    private String binAmount;


    public BinCoin(){}

    public BinCoin(String id, String name, String walPrice, String walAmount, Tx tx1){
        super(id,name,tx1);
        this.binPrice = walPrice;
        this.binAmount = walAmount;

    }

    @Override
    public String getBinPrice() {
        return binPrice;
    }

    @Override
    public void setBinPrice(String binPrice) {
        this.binPrice = binPrice;
    }

    @Override
    public String getBinAmount() {
        return binAmount;
    }

    @Override
    public void setBinAmount(String binAmount) {
        this.binAmount = binAmount;
    }

    public List<Param> bindParamsInsert(Coin obj, List<Param> paramList) {
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getBinPrice()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getBinAmount()));
        return paramList;
    }

    public List<Param> bindParamsUpdate(Coin appCoin, Coin upsertCoin, List<Param> paramList) {
        if(!appCoin.getBinPrice().equalsIgnoreCase(upsertCoin.getBinPrice())){
            paramList.add(new Param(Constants.DATATYPE_STRING,upsertCoin.getBinPrice()));
        }else{
            paramList.add(new Param(Constants.DATATYPE_STRING,appCoin.getBinPrice()));
        }

        if(!appCoin.getBinAmount().equalsIgnoreCase(upsertCoin.getBinAmount())){
            paramList.add(new Param(Constants.DATATYPE_STRING,upsertCoin.getBinAmount()));
        }else{
            paramList.add(new Param(Constants.DATATYPE_STRING,appCoin.getBinAmount()));
        }

        return paramList;
    }


}
