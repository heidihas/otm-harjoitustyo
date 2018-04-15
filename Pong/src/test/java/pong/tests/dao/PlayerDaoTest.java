/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        File file = new File("db", "player.db");
        try {
            database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = new PlayerDao(database);
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
    
}
