package db.helper;

import db.core.DBManager;
import model.dao.Param;
import model.dao.db.BinCoin;
import model.dao.db.CbCoin;
import model.dao.db.Coin;
import model.dao.db.Tx;
import util.Constants;
import util.DB_Constants;
import vender.binance.dao.BinanceCoin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Milinda
 */
public class CoinHandler {

    private DBManager dbManager;

    public CoinHandler(){
        dbManager = new DBManager();
    }

    public Coin getCoin(String coindId) {
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,coindId));
        Coin res = null;
        ResultSet rs = null;
        try {
            rs = dbManager.executeResponseQuery(rs, DB_Constants.GET_APP_COIN, paramList);
            while (rs.next()) {
                String id = rs.getString(DB_Constants.ATT_COIN_ID);
                String name = rs.getString(DB_Constants.ATT_COIN_NAME);
                String binPrice = rs.getString(DB_Constants.ATT_BIN_PRICE);
                String binAmount = rs.getString(DB_Constants.ATT_BIN_AMOUNT);
                res = new BinCoin(id,name,binPrice,binAmount,null);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }finally {
            dbManager.resultSetClose(rs);
        }
        return res;
    }

    public boolean upsertCoinDB(String coinMarket, Coin upsertCoin){
        Coin appCoin = this.getCoin(upsertCoin.getId());
        if(appCoin == null){
            this.insertCoin(coinMarket,upsertCoin);
        }else{
            this.updateCoin(appCoin, coinMarket, upsertCoin);
        }
        return true;
    }

    private boolean updateCoin(Coin appCoin, String coinMarket, Coin upsertCoin) {
        List<Param> paramList = new ArrayList<>();
        if(!appCoin.getName().equalsIgnoreCase(upsertCoin.getName())){
            paramList.add(new Param(Constants.DATATYPE_STRING,upsertCoin.getName()));
        }else{
            paramList.add(new Param(Constants.DATATYPE_STRING,appCoin.getName()));
        }
        try {
            if(coinMarket.equals(Constants.BINANCE)){
                paramList = new BinCoin().bindParamsUpdate(appCoin, upsertCoin, paramList);
                paramList.add(new Param(Constants.DATATYPE_STRING,upsertCoin.getId()));

                dbManager.executeQuery(DB_Constants.UPDATE_BINANCE_COIN,paramList);

            } else if(coinMarket.equals(Constants.COINBASE)){
                paramList = new CbCoin().bindParamsUpdate(appCoin, upsertCoin, paramList);
                paramList.add(new Param(Constants.DATATYPE_STRING,upsertCoin.getId()));
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean insertCoin(String coinMarket, Coin obj) {
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getId()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getName()));

        try {
            if(coinMarket.equals(Constants.BINANCE)){
                paramList = new BinCoin().bindParamsInsert(obj, paramList);
                dbManager.executeQuery(DB_Constants.INSERT_BINANCE_COIN,paramList);

            } else if(coinMarket.equals(Constants.COINBASE)){
                paramList = new CbCoin().bindParamsInsert(obj, paramList);
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertTx(Coin coin) {
        List<Param> paramList = new ArrayList<>();
        Tx tx = coin.getListTx().get(0);
        paramList.add(new Param(Constants.DATATYPE_STRING,tx.getTimestamp()));
        paramList.add(new Param(Constants.DATATYPE_STRING,coin.getId()));
        paramList.add(new Param(Constants.DATATYPE_BOOLEAN,tx.isBuy()));
        paramList.add(new Param(Constants.DATATYPE_STRING,tx.getCoinAmount()));
        paramList.add(new Param(Constants.DATATYPE_STRING,tx.getUnitPrice()));
        paramList.add(new Param(Constants.DATATYPE_BOOLEAN, tx.getFee()));
        try {
            dbManager.executeQuery(DB_Constants.INSERT_TX,paramList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

}
