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
    int gameHeight;
    int gameWidth;
    Score score;
    Paddle leftPaddle;
    Paddle rightPaddle;
    Ball ball;
    Movement movementPaddles;
    Movement movementBall;
    ArrayList<Integer> paddleMovements;
    
    @Before
    public void setUp() {
        gameHeight = 480;
        gameWidth = 640;
        score = new Score(0, 0);
        leftPaddle = new Paddle(5, 200, 20, 80, Color.RED);
        rightPaddle = new Paddle(615, 200, 20, 80, Color.RED);
        ball = new Ball(10, 10, 10);
        movementPaddles = new Movement(2, 2);
        movementBall = new Movement(1, 1);
        paddleMovements = new ArrayList<>();
        paddleMovements.add(0);
        paddleMovements.add(0);
        paddleMovements.add(0);
        paddleMovements.add(0);
        logics = new PongLogics(score, leftPaddle, rightPaddle, ball, 
                movementPaddles, movementBall, paddleMovements);
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
        logics.setWinningScore(10);
        score.setLeftScore(logics.getWinningScore());
        score.setRightScore(logics.getWinningScore());
        assertTrue(logics.playerWon());
    }
    
    @Test 
    public void leftPlayerWon() {
        logics.setWinningScore(10);
        score.setLeftScore(logics.getWinningScore());
        assertTrue(logics.playerWon());
    }
    
    @Test 
    public void rightPlayerWon() {
        logics.setWinningScore(10);
        score.setRightScore(logics.getWinningScore());
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
        assertTrue(leftPaddle.getY() == 202);
    }
    
    @Test
    public void moveLeftPaddleDown() {
        paddleMovements.set(1, 1);
        logics.movePaddles();
        assertTrue(leftPaddle.getY() == 198);
    }
    
    @Test
    public void moveRightPaddleUp() {
        paddleMovements.set(2, 1);
        logics.movePaddles();
        assertTrue(rightPaddle.getY() == 202);
    }
    
    @Test
    public void moveRightPaddleDown() {
        paddleMovements.set(3, 1);
        logics.movePaddles();
        assertTrue(rightPaddle.getY() == 198);
    }
    
    @Test
    public void leftPaddleOffBoardUp() {
        leftPaddle.setY(-1);
        logics.paddlesOnBoard(gameHeight);
        assertEquals(0, leftPaddle.getY());
    }
    
    @Test
    public void rightPaddleOffBoardUp() {
        rightPaddle.setY(-1);
        logics.paddlesOnBoard(gameHeight);
        assertEquals(0, rightPaddle.getY());
    }
    
    @Test
    public void leftPaddleOffBoardDown() {
        leftPaddle.setY(401);
        logics.paddlesOnBoard(gameHeight);
        assertEquals(400, leftPaddle.getY());
    }
    
    @Test
    public void rightPaddleOffBoardDown() {
        rightPaddle.setY(401);
        logics.paddlesOnBoard(gameHeight);
        assertEquals(400, rightPaddle.getY());
    }
    
    @Test
    public void ballHitsUpNot() {
        ball.setY(3);
        logics.ballHitsUpOrDown(gameHeight);
        assertTrue(movementBall.getMovementY() == 1.0);
    }
    
    @Test
    public void ballHitsDownNot() {
        ball.setY(420);
        logics.ballHitsUpOrDown(gameHeight);
        assertTrue(movementBall.getMovementY() == 1.0);
    }
    
    @Test
    public void ballHitsUp() {
        ball.setY(-1);
        logics.ballHitsUpOrDown(gameHeight);
        assertTrue(movementBall.getMovementY() == -1.0);
    }
    
    @Test
    public void ballHitsDown() {
        ball.setY(461);
        logics.ballHitsUpOrDown(gameHeight);
        assertTrue(movementBall.getMovementY() == -1.0);
    }
    
    @Test
    public void ballHitsLeft() {
        ball.setX(-1);
        logics.ballHitsLeftOrRight(gameWidth);
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    @Test
    public void ballHitsRight() {
        ball.setX(621);
        logics.ballHitsLeftOrRight(gameWidth);
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    @Test
    public void ballHitsLeftScore() {
        ball.setX(-1);
        logics.ballHitsLeftOrRight(gameWidth);
        assertTrue(score.getRightScore() == 1);
    }
    
    @Test
    public void ballHitsRightScore() {
        ball.setX(621);
        logics.ballHitsLeftOrRight(gameWidth);
        assertTrue(score.getLeftScore() == 1);
    }
    
    /*@Test
    public void ballHitsLeftPaddle1() {
        ball.setX(10);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    @Test
    public void ballHitsLeftPaddle2() {
        ball.setX(24);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    } */
    
    @Test
    public void ballHitsLeftPaddle3() {
        ball.setY(201);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    @Test
    public void ballHitsLeftPaddle4() {
        ball.setY(270);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    /*@Test
    public void ballHitsRightPaddle1() {
        ball.setX(596);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    @Test
    public void ballHitsRightPaddle2() {
        ball.setX(614);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }*/ 
    
    @Test
    public void ballHitsRightPaddle3() {
        ball.setY(201);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }
    
    @Test
    public void ballHitsRightPaddle4() {
        ball.setY(270);
        logics.ballHitsPaddle();
        assertTrue(movementBall.getMovementX() == -1.0);
    }
}
    