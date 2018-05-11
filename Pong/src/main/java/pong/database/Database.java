/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Heidi
 */
/**
 * The class manages the connection with the database.
 */
public class Database {
    private String databaseAddress;

    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }

    /**
     * The method requests connection with the assigned database.
     * 
     * @return connection as Connection object
     * 
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * The method creates the table Player to the database if it doesn't already exist.
     * 
     * @param table table name as String provided by the application
     * 
     * @return executed initialization statement
     * 
     * @throws java.sql.SQLException
     */
    public Statement init(String table) throws SQLException {
        
        try (Connection conn = getConnection()) {
            PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM "
                    + "sqlite_master WHERE type = 'table'");
            ResultSet result = stmt1.executeQuery();
                
            if (!result.next()) {
                PreparedStatement stmt2 = conn.prepareStatement("CREATE TABLE " 
                        + table + " (id integer PRIMARY KEY, name varchar(8), score integer);");
                stmt2.executeUpdate();
                
                return stmt2;
            }
            return null;
        }  
    } 
}

