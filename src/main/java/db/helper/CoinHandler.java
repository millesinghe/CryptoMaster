package db.helper;

import db.core.DBManager;
import model.dao.Param;
import model.dao.db.Coin;
import model.dao.db.Tx;
import util.Constants;
import util.DB_Constants;

import java.sql.PreparedStatement;
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

    public String getCoinName(String coindId) {
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,coindId));
        String res = null;
        ResultSet rs = null;
        try {
            rs = dbManager.executeResponseQuery(rs, DB_Constants.GET_COIN_NAMES, paramList);
            while (rs.next()) {
                res = rs.getString(DB_Constants.ATT_COIN_NAME);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }finally {
            dbManager.resultSetClose(rs);
        }
        return res;
    }

    public String getCoinId(String coindId) {

        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,coindId));
        String res = null;
        ResultSet rs = null;
        try {
            rs = dbManager.executeResponseQuery(rs,DB_Constants.GET_COIN_BYID, paramList);
            while (rs.next()) {
                 res = rs.getString(DB_Constants.ATT_COIN_NAME);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }finally {
            dbManager.resultSetClose(rs);
        }
        return res;
    }

    public boolean upsertCoinDB(Coin obj){
        String coinid = this.getCoinId(obj.getId());
        if(coinid == null){
            this.insertCoin(obj);
        }else{

        }
        return true;
    }

    private boolean insertCoin(Coin obj) {
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getId()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getName()));
        paramList.add(new Param(Constants.DATATYPE_STRING,obj.getLast()));

        try {
            dbManager.executeQuery(DB_Constants.INSERT_COIN,paramList);
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


    private double getTimeStamp(){
        Date today = Calendar.getInstance().getTime();

        long epochTime = 0;

        // Constructs a SimpleDateFormat using the given pattern
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

        // format() formats a Date into a date/time string.
        String currentTime = crunchifyFormat.format(today);
        System.out.println("Current Time = " + currentTime);

        try {

            // parse() parses text from the beginning of the given string to produce a date.
            Date date = crunchifyFormat.parse(currentTime);

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
            epochTime = date.getTime();

            System.out.println("Current Time in Epoch: " + epochTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Local ZoneID
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("defaultZoneId: " + defaultZoneId);

        Date date = new Date();

        // Default Zone: UTC+0
        Instant instant = date.toInstant();
        System.out.println("instant : " + instant);

        // Local TimeZone
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        System.out.println("localDateTime : " + localDateTime);

        return epochTime;
    }

}
