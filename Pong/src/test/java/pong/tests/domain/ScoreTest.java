/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.Score;

/**
 *
 * @author Heidi
 */
public class ScoreTest {
    
    Score score;
    
    @Before
    public void setUp() {
        score = new Score(5, 6);
    }
    
    @Test
    public void createdScoreExists() {
        assertTrue(score!=null);      
    }
    
    @Test
    public void setScoreLeft() {
        score.setLeftScore(7);
        assertEquals(7, score.getLeftScore());
    }
    
    @Test
    public void setScoreRight() {
        score.setRightScore(10);
        assertEquals(10, score.getRightScore());
    }
    
    @Test
    public void setScoreLeftString() {
        score.setLeftScore(10);
        assertEquals("10", score.getLeftScoreString());
    }
    
    @Test
    public void setScoreRightString() {
        score.setRightScore(10);
        assertEquals("10", score.getRightScoreString());
    }
    
    @Test
    public void scoreToString() {
        score.setRightScore(10);
        score.setLeftScore(0);
        assertEquals("0 : 10", score.toString());
    }
    
    @Test
    public void increseLeft() {
        score.increse(1);
        assertEquals(6, score.getLeftScore());
    }
    
    @Test
    public void increseLeftRight() {
        score.increse(1);
        assertEquals(6, score.getRightScore());
    }
    
    @Test
    public void increseRight() {
        score.increse(0);
        assertEquals(7, score.getRightScore());
    }
    
    @Test
    public void increseRightLeft() {
        score.increse(0);
        assertEquals(5, score.getLeftScore());
    }
    
    @Test
    public void increseNothing() {
        score.increse(-2);
        assertEquals("5 : 6", score.toString());
    }
    
    @Test
    public void increseNothing2() {
        score.increse(2);
        assertEquals("5 : 6", score.toString());
    }
}
