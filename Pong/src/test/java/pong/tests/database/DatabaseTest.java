/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.database.Database;

/**
 *
 * @author Heidi
 */
public class DatabaseTest {
    
    Database database;
    
    @Before
    public void setUp() {
        File file = new File("db", "player.db");
        try {
            database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void createdDatabaseExists() {
        assertTrue(database!=null);      
    }
    
    @Test
    public void getConnection() {
        try {
            Connection conn = database.getConnection();
            assertTrue(conn != null); 
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
