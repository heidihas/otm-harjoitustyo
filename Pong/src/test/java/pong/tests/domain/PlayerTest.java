/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.Player;

/**
 *
 * @author Heidi
 */
public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player(0, "Matti", 10);
    }
    
    @Test
    public void createdPlayerExists() {
        assertTrue(player!=null);      
    }
    
    @Test
    public void getScoreString() {
        assertEquals("10", player.getScoreString());
    }
    
    @Test
    public void increseScore() {
        player.increseScore(10);
        assertEquals(20, player.getScore());
    }
    
}