/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.Ball;
import pong.domain.Movement;
/**
 *
 * @author Heidi
 */
public class MovementTest {
    
    Movement movement;
    
    @Before
    public void setUp() {
        movement = new Movement(2, 2);
    }
    
    @Test
    public void createdMovementExists() {
        assertTrue(movement!=null);      
    }
    
    @Test
    public void getX() {
        assertEquals(2.0, movement.getMovementX(), 0);      
    }
    
    @Test
    public void getY() {
        assertEquals(2.0, movement.getMovementY(), 0);      
    }
    
    @Test
    public void setX() {
        movement.setMovementX(4.5);
        assertEquals(4.5, movement.getMovementX(), 0);      
    }
    
    @Test
    public void setY() {
        movement.setMovementY(4.5);
        assertEquals(4.5, movement.getMovementY(), 0);      
    }
    
    @Test
    public void randomDirectionX() {
        movement.randomDirection();
        assertTrue(movement.getMovementX() == -2.0 || movement.getMovementX() == 2.0);
    }
    
    @Test
    public void randomDirectionY() {
        movement.randomDirection();
        assertTrue(movement.getMovementX() == -2.0 || movement.getMovementX() == 2.0);
    }
    
    @Test
    public void setLevelEasyX() {
        movement.setLevel("Easy");
        assertEquals(2.5, movement.getMovementX(), 0);
    }
    
    @Test
    public void setLevelEasyY() {
        movement.setLevel("Easy");
        assertEquals(2.5, movement.getMovementY(), 0);
    }
    
    @Test
    public void setLevelMediumX() {
        movement.setLevel("Medium");
        assertEquals(4.0, movement.getMovementX(), 0);
    }
    
    @Test
    public void setLevelMediumY() {
        movement.setLevel("Medium");
        assertEquals(4.0, movement.getMovementY(), 0);
    }
    
    @Test
    public void setLevelHardX() {
        movement.setLevel("Hard");
        assertEquals(6.0, movement.getMovementX(), 0);
    }
    
    @Test
    public void setLevelHardY() {
        movement.setLevel("Hard");
        assertEquals(6.0, movement.getMovementY(), 0);
    }
}