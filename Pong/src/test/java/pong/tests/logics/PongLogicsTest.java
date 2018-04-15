/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.logics;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.dao.PlayerDao;
import pong.database.Database;
import pong.domain.Ball;
import pong.domain.Movement;
import pong.domain.Paddle;
import pong.domain.Score;
import pong.logics.PongLogics;

/**
 *
 * @author Heidi
 */
public class PongLogicsTest {
    
    PongLogics logics;
    Score score;
    Paddle leftPaddle;
    Paddle rightPaddle;
    Ball ball;
    Movement movementPaddles;
    Movement movementBall;
    ArrayList<Integer> paddleMovements;
    PlayerDao dao;
    Database database;
    
    @Before
    public void setUp() {
        score = new Score(0, 0);
        leftPaddle = new Paddle(20, 20, 20, 80, Color.RED);
        rightPaddle = new Paddle(20, 20, 20, 80, Color.RED);
        ball = new Ball(10, 10, 10);
        movementPaddles = new Movement(2, 2);
        movementBall = new Movement(1, 1);
        paddleMovements = new ArrayList<>();
        paddleMovements.add(0);
        paddleMovements.add(0);
        paddleMovements.add(0);
        paddleMovements.add(0);
        database = new Database();
        dao = new PlayerDao(database);
        logics = new PongLogics(score, leftPaddle, rightPaddle, ball, 
                movementPaddles, movementBall, paddleMovements, dao, database);
    }
    
    @Test
    public void createdLogicsExists() {
        assertTrue(logics!=null);      
    }
    
    @Test
    public void winningScore() {
        logics.setWinningScore(10);
        assertTrue(logics.getWinningScore() == 10);
    }
    
    @Test 
    public void nobodyWon() {
        logics.setWinningScore(10);
        assertFalse(logics.playerWon());
    }
    
    @Test 
    public void somebodyWon() {
        score.setLeftScore(logics.getWinningScore());
        assertTrue(logics.playerWon());
    }
    
    @Test 
    public void moveBallX() {
        logics.moveBall();
        assertTrue(11 == ball.getX());
    }
    
    @Test 
    public void moveBallY() {
        logics.moveBall();
        assertTrue(11 == ball.getY());
    }
    
    @Test
    public void moveLeftPaddleUp() {
        paddleMovements.set(0, 1);
        logics.movePaddles();
        assertTrue(leftPaddle.getY() == 22);
    }
    
    @Test
    public void moveLeftPaddleDown() {
        paddleMovements.set(1, 1);
        logics.movePaddles();
        assertTrue(leftPaddle.getY() == 18);
    }
    
    @Test
    public void moveRightPaddleUp() {
        paddleMovements.set(2, 1);
        logics.movePaddles();
        assertTrue(rightPaddle.getY() == 22);
    }
    
    @Test
    public void moveRightPaddleDown() {
        paddleMovements.set(3, 1);
        logics.movePaddles();
        assertTrue(rightPaddle.getY() == 18);
    }
}