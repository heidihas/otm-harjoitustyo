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
public class BallTest {
    
    Ball ball;
    Movement movement;
    
    @Before
    public void setUp() {
        ball = new Ball(0, 0, 10);
        movement = new Movement(2, 2);
    }
    
    @Test
    public void createdBallExists() {
        assertTrue(ball!=null);      
    }
    
    @Test
    public void ballX() {
        assertEquals(0.0, ball.getX(), 0);
    }
    
    @Test
    public void ballY() {
        assertEquals(0.0, ball.getY(), 0);
    }
    
    @Test
    public void ballRadius() {
        assertEquals(10.0, ball.getRadius(), 0);
    }
    
    @Test
    public void getBallX() {
        ball.setX(2);
        assertEquals(2.0, ball.getX(), 0);
    }
    
    @Test
    public void getBallY() {
        ball.setY(2);
        assertEquals(2.0, ball.getY(), 0);
    }
    
    @Test
    public void getBallRadius() {
        ball.setRadius(2);
        assertEquals(2.0, ball.getRadius(), 0);
    }
    
    @Test
    public void moveBallX() {
        ball.move(movement);
        assertEquals(2.0, ball.getX(), 0);
    }
    
    @Test
    public void moveBallY() {
        ball.move(movement);
        assertEquals(2.0, ball.getY(), 0);
    }
    
}
