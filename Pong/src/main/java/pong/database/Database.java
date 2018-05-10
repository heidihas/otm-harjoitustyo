/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.database;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public void init() {
        String statement = "CREATE TABLE Player (id integer PRIMARY KEY, name varchar(8), score integer);";
        
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(statement);
            
            conn.close();
            
        } catch (Throwable t) {
        }
    }
}
