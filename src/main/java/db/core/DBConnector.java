package db.core;

import helper.FileHandler;
import util.DB_Constants;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Milinda
 */
public class DBConnector {

    private Connection conn = null;

    public DBConnector(){
        if (conn == null) {
            FileHandler.loadProperties();
            this.connect();
        }
    }
    private Connection connect() {
        // SQLite connection string
        try {
            conn = DriverManager.getConnection(DB_Constants.SQLITE_DRIVER+ FileHandler.propUser.getProperty("memory.db") + File.separator+ DB_Constants.DB);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Connection getConnection(){
        return this.conn;
    }

}
