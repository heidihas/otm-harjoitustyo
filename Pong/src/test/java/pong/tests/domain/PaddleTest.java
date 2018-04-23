/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.domain;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.Paddle;

/**
 *
 * @author Heidi
 */
public class PaddleTest {
    
    Paddle paddle;
    
    @Before
    public void setUp() {
        paddle = new Paddle(20, 20, 20, 80, Color.BLUE);
    }
    
    @Test
    public void createdPaddleExists() {
        assertTrue(paddle!=null);      
    }
    
    @Test
    public void setX() {
       paddle.setX(0);
       assertEquals(0, paddle.getX());
    }
    
    @Test
    public void setY() {
       paddle.setY(10);
       assertEquals(10, paddle.getY());
    }
    
    @Test
    public void setWidth() {
       paddle.setWidth(100);
       assertEquals(100, paddle.getWidth());
    }
    
    @Test
    public void setHeight() {
       paddle.setHeight(26);
       assertEquals(26, paddle.getHeight());
    }
    
    @Test
    public void setColor() {
       paddle.setColor(Color.AQUA);
       assertEquals(Color.AQUA, paddle.getColor());
    }
    
    @Test
    public void ballHitsVerticalLeft() {
        assertEquals(true, paddle.ballHitsVerticalLeft(40, 30));
    }
    
    @Test
    public void ballHitsVerticalLeftNot() {
        assertEquals(false, paddle.ballHitsVerticalLeft(40, 20));
    }
    
    @Test
    public void ballHitsVerticalLeftNot1() {
        assertEquals(false, paddle.ballHitsVerticalLeft(40, 100));
    }
    
    @Test
    public void ballHitsVerticalLeftNot2() {
        assertEquals(false, paddle.ballHitsVerticalLeft(42, 20));
    }
    
    @Test
    public void ballHitsVerticalLeftNot3() {
        assertEquals(false, paddle.ballHitsVerticalLeft(42, 80));
    }
    
    @Test
    public void ballHitsVerticalRight() {
        assertEquals(true, paddle.ballHitsVerticalRight(10, 30, 5));
    }
    
    @Test
    public void ballHitsVerticalRightNot() {
        assertEquals(false, paddle.ballHitsVerticalRight(10, 20, 5));
    }
    
    @Test
    public void ballHitsVerticalRightNot1() {
        assertEquals(false, paddle.ballHitsVerticalRight(10, 100, 5));
    }
    
    @Test
    public void ballHitsVerticalRightNot2() {
        assertEquals(false, paddle.ballHitsVerticalRight(8, 20, 5));
    }
    
    @Test
    public void ballHitsVerticalRightNot3() {
        assertEquals(false, paddle.ballHitsVerticalRight(8, 80, 5));
    }
    
    @Test
    public void ballHitsHorizontalLeft() {
        assertEquals(true, paddle.ballHitsHorizontalLeft(39, 20, 5));
    }
    
    @Test
    public void ballHitsHorizontalLeftNot() {
        assertEquals(false, paddle.ballHitsHorizontalLeft(39, 101, 5));
    }
    
    @Test
    public void ballHitsHorizontalRight() {
        assertEquals(true, paddle.ballHitsHorizontalRight(20, 20, 5));
    }
    
    @Test
    public void ballHitsHorizontalRightNot() {
        assertEquals(false, paddle.ballHitsHorizontalRight(20, 101, 5));
    }
}
