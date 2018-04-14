/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public void setUp() {
        database = new Database();
        dao = new PlayerDao(database);
    }
    
    @Test
    public void createdDaoExists() {
        assertTrue(dao!=null);      
    }
    
    @Test
    public void findAllPlayers() {
        List<Player> players = new ArrayList();
        try {
            assertEquals(players, dao.findAll());
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
    
}
