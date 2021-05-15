package db.core;

import helper.FileHandler;
import model.dao.Param;
import util.Constants;
import util.DB_Constants;

import java.io.File;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Milinda
 */
public class DBManager {

    private Connection conn;
    private PreparedStatement pstmt;

    public DBManager(){
        conn =  new DBConnector().getConnection();
    }

    public static void createNewDatabase(String fileName) {

        FileHandler.loadProperties();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_Constants.SQLITE_DRIVER+FileHandler.propUser.getProperty("memory.db") +File.separator+ DB_Constants.DB);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private PreparedStatement buildPreparedStmt(PreparedStatement pstmt, String query, List<Param> paramList) throws SQLException {
        pstmt = conn.prepareStatement(query);
        for (int i = 0; i < paramList.size(); i++) {
            if(paramList.get(i).getType().equalsIgnoreCase(Constants.DATATYPE_STRING)){
                pstmt.setString(i+1, (String) paramList.get(i).getValue());
            } else if(paramList.get(i).getType().equalsIgnoreCase(Constants.DATATYPE_INT)){
                pstmt.setInt(i+1, (Integer) paramList.get(i).getValue());
            } else if(paramList.get(i).getType().equalsIgnoreCase(Constants.DATATYPE_DOUBLE)){
                pstmt.setDouble(i+1, (Double) paramList.get(i).getValue());
            } else if(paramList.get(i).getType().equalsIgnoreCase(Constants.DATATYPE_BOOLEAN)){
                pstmt.setBoolean(i+1, (Boolean) paramList.get(i).getValue());
            }
        }
        return pstmt;
    }

    public ResultSet executeResponseQuery(ResultSet rs,String query, List<Param> paramList) throws SQLException {
        if (conn != null) {
            pstmt = null;

            pstmt = this.buildPreparedStmt(pstmt, query, paramList);
            rs = pstmt.executeQuery();

            return rs;
        }
        return null;
    }

    public void executeQuery(String query, List<Param> paramList) throws SQLException {
        if (conn != null) {
            pstmt = null;
            try {
                pstmt = this.buildPreparedStmt(pstmt, query, paramList);
                pstmt.executeUpdate();
            } finally{
                preStatmentClose(pstmt);
            }
        }
    }

    public void resultSetClose(ResultSet rs) {
        if(rs != null){
            try{
                rs.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void preStatmentClose(PreparedStatement pstmt){
        if(this.pstmt != null){
            try{
                this.pstmt.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        createNewDatabase("test.db");
    }
}