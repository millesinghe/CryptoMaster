package db.helper;

import db.core.DBManager;
import model.dao.Param;
import model.dao.db.BinCoin;
import model.dao.db.CbCoin;
import model.dao.db.Coin;
import model.dao.db.Tx;
import util.Constants;
import util.DB_Constants;
import vender.binance.util.BinanceConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Tx getTx(String txId) {
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,txId));
        Tx res = null;
        ResultSet rs = null;
        try {
            rs = dbManager.executeResponseQuery(rs, DB_Constants.GET_TX, paramList);
            while (rs.next()) {
                String id = rs.getString(DB_Constants.ATT_id);
                String orderId  = rs.getString(DB_Constants.ATT_orderId);
                String walletName  = rs.getString(DB_Constants.ATT_walletName);
                boolean isBuy = rs.getBoolean(DB_Constants.ATT_ISBUY);
                String buy_coin_type = rs.getString(DB_Constants.ATT_buy_coin_type);
                String buy_amount = rs.getString(DB_Constants.ATT_buy_amount);
                String unit_price = rs.getString(DB_Constants.ATT_unit_price);
                String USDTEqual = rs.getString(DB_Constants.ATT_USDTEqual);
                String spend_coin_type = rs.getString(DB_Constants.ATT_spend_coin_type);
                String spend_amount = rs.getString(DB_Constants.ATT_spend_amount);
                String fee = rs.getString(DB_Constants.ATT_fee);
                String timestamp = rs.getString(DB_Constants.ATT_timestamp);
                res = new Tx(id, orderId, walletName, isBuy, buy_coin_type, buy_amount, unit_price,
                        USDTEqual, spend_coin_type, spend_amount, fee, timestamp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }finally {
            dbManager.resultSetClose(rs);
        }
        return res;
    }


    public boolean upsertTxDB(String coinMarket, Tx upsertTx){
        Tx tx = this.getTx(upsertTx.getId());
        if(tx == null){
            System.out.println(upsertTx.getBuy_coin_type() + "Transaction ID - " + upsertTx.getOrderId());
            this.insertTx(coinMarket,upsertTx);
        }
//NOT Required
//        else{
//            this.updateTx(tx, coinMarket, upsertTx);
//        }
//
        return true;
    }


    private boolean insertTx(String coinMarket, Tx obj) {
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getId()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getOrderId()));
        paramList.add(new Param(Constants.DATATYPE_STRING,Constants.BINANCE));
        paramList.add(new Param(Constants.DATATYPE_STRING,String.valueOf(obj.getIsBuy())));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getBuy_coin_type()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getBuy_amount()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getUnit_price()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getUSDTEqual()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getSpend_coin_type()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getSpend_amount()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getFee()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getTimestamp()));

        try {
            if(coinMarket.equals(Constants.BINANCE)){
                dbManager.executeQuery(DB_Constants.INSERT_TX,paramList);

            } else if(coinMarket.equals(Constants.COINBASE)){
                //paramList = new CbCoin().bindParamsInsert(obj, paramList);
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
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
                paramList = new BinCoin().bindParamsInsertCoin(obj, paramList);
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


    public List<Coin> getWalletCoins(String coinMarket){
        List<Coin> res = new ArrayList<>();
        List<Param> paramList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = dbManager.executeResponseQuery(rs, BinanceConstant.GET_ALL_WALLET_COIN_ID , paramList);
            while (rs.next()) {
                String id = rs.getString(DB_Constants.ATT_COIN_ID);
                String name = rs.getString(DB_Constants.ATT_COIN_NAME);
                String binPrice = rs.getString(DB_Constants.ATT_BIN_PRICE);
                String binAmount = rs.getString(DB_Constants.ATT_BIN_AMOUNT);
                res.add(new BinCoin(id,name,binPrice,binAmount,null));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }finally {
            dbManager.resultSetClose(rs);
        }
        return res;
    }

    public boolean insertTx(Coin coin) {
        List<Param> paramList = new ArrayList<>();
        Tx tx = coin.getListTx().get(0);
        try {
            dbManager.executeQuery(DB_Constants.INSERT_TX,paramList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

}
