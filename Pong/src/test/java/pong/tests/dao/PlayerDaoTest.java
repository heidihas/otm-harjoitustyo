/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.dao.PlayerDao;
import pong.database.Database;
import pong.domain.Player;

/**
 *
 * @author Heidi
 */
public class PlayerDaoTest {
    
    PlayerDao dao;
    Database database;
    
    @Before
    public void setUp() throws SQLException {
        database = new Database("jdbc:sqlite:player.db");
        dao = new PlayerDao(database);
        
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Player WHERE name = ?");
            stmt.setString(1, "Testi");
            stmt.executeUpdate();
        }
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Player WHERE name = ?");
            stmt.setString(1, "Matti");
            stmt.executeUpdate();
        }
        Player player = new Player(0, "Testi", 10);
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Player (name, score) VALUES (?, ?)");
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getScore());
            stmt.executeUpdate();
        }
        
    }
    
    @Test
    public void createdDaoExists() {
        assertTrue(dao!=null);      
    }
    
    @Test
    public void findAllPlayers() {
        try {
            assertFalse(dao.findAll().isEmpty());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findFivePlayers() {
        try {
            assertTrue(dao.findFiveTop().size() <= 5);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findPlayerByNameFalse() {
        try {
            assertEquals(null, dao.findOneByName("Matti"));
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findPlayerByNameTrue() {
        try {
            Player onko = dao.findOneByName("Testi");
            assertEquals("Testi", onko.getName());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void updateGetName() {
        try {
            Player onko = new Player(1, "Testi", 40);
            dao.saveOrUpdate(onko);
            assertEquals("Testi", onko.getName());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void updateGetScore() {
        try {
            Player onko = new Player(1, "Testi", 40);
            dao.saveOrUpdate(onko);
            assertEquals(40, onko.getScore());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void saveGetName() {
        try {
            Player onko = new Player(1, "Matti", 40);
            dao.saveOrUpdate(onko);
            assertEquals("Matti", onko.getName());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void saveGetScore() {
        try {
            Player onko = new Player(1, "Matti", 40);
            dao.saveOrUpdate(onko);
            assertEquals(40, onko.getScore());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void saveGetTesti() {
        try {
            Player onko = new Player(1, "Matti", 40);
            dao.saveOrUpdate(onko);
            assertEquals(10, dao.findOneByName("Testi").getScore());
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
