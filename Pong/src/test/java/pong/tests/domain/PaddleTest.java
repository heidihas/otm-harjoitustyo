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
    public void outside1() {
        assertEquals(true, paddle.outside(0, 20));
    }
    
    @Test
    public void outside2() {
        assertEquals(true, paddle.outside(21+20, 20));
    }
    
    @Test
    public void outside3() {
        assertEquals(true, paddle.outside(20, 19));
    }
    
    @Test
    public void outside4() {
        assertEquals(true, paddle.outside(20, 21+80));
    }
    
    @Test
    public void outside5() {
        assertEquals(false, paddle.outside(39, 20));
    }
    
    @Test
    public void outside6() {
        assertEquals(false, paddle.outside(20, 20));
    }
}
