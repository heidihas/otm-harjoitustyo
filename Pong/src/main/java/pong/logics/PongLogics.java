/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.logics;

import java.util.ArrayList;
import pong.dao.PlayerDao;
import pong.database.Database;
import pong.domain.Ball;
import pong.domain.Movement;
import pong.domain.Paddle;
import pong.domain.Score;

/**
 *
 * @author Heidi
 */
public class PongLogics {
    private int winningScore;
    private Score score;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;
    private Movement movementPaddles;
    private Movement movementBall;
    private ArrayList<Integer> paddleMovements;
    private PlayerDao dao;
    private Database database;
    
    public PongLogics(Score score, Paddle leftPaddle, Paddle rightPaddle, 
            Ball ball, Movement movementPaddles, Movement movementBall, 
            ArrayList<Integer> paddleMovements, PlayerDao dao, Database database) {
        this.score = score;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ball = ball;
        this.movementPaddles = movementPaddles;
        this.movementBall = movementBall;
        this.paddleMovements = paddleMovements;
        this.dao = dao;
        this.database = database;
    }
    
    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }
    
    public int getWinningScore() {
        return this.winningScore;
    }
    
    public boolean playerWon() {
        return (score.getLeftScore() == winningScore || score.getRightScore() == winningScore);
    }
    
    public void moveBall() {
        ball.move(movementBall);
    }
    
    public void movePaddles() {
        if (paddleMovements.get(0) == 1) {
            leftPaddle.setY(leftPaddle.getY() + (int) movementPaddles.getMovementY());
        }

        if (paddleMovements.get(1) == 1) {
            leftPaddle.setY(leftPaddle.getY() - (int) movementPaddles.getMovementY());
        }

        if (paddleMovements.get(2) == 1) {
            rightPaddle.setY(rightPaddle.getY() + (int) movementPaddles.getMovementY());
        }
                
        if (paddleMovements.get(3) == 1) {
            rightPaddle.setY(rightPaddle.getY() - (int) movementPaddles.getMovementY());
        }
    }
    
    public void ballHitsPaddle() {
        /*if ((((int)ball.getY() >= leftPaddle.getY()) && ((int)ball.getY() <= (leftPaddle.getY()+leftPaddle.getHeight()))) && !leftPaddle.outside((int)ball.getX(), (int)ball.getY())) { 
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            movementBall.setMovementY(-1 * movementBall.getMovementY());
        }*/
                
        if (!leftPaddle.outside((int) ball.getX(), (int) ball.getY())) { 
            movementBall.setMovementX(-1 * movementBall.getMovementX()); 
        } 
                
        /*if (((int)ball.getX() > 615) && !rightPaddle.outside((int)ball.getX(), (int)ball.getY())) { 
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            movementBall.setMovementY(-1 * movementBall.getMovementY());
        }*/
                
        if (!rightPaddle.outside((int) ball.getX() + rightPaddle.getWidth(), (int) ball.getY())) { 
            movementBall.setMovementX(-1 * movementBall.getMovementX());
        }
    }
    
    public void paddlesOnBoard(int gameHeight) {
        if (leftPaddle.getY() < 0) { 
            leftPaddle.setY(0);
        }
                
        if ((leftPaddle.getY() + leftPaddle.getHeight()) > gameHeight) {
            leftPaddle.setY(gameHeight - leftPaddle.getHeight());
        }
                
        if (rightPaddle.getY() < 0) { 
            rightPaddle.setY(0);
        }
                
        if ((rightPaddle.getY() + rightPaddle.getHeight()) > gameHeight) {
            rightPaddle.setY(gameHeight - rightPaddle.getHeight());
        }
    }
    
    public void ballHitsUpOrDown(int gameHeight) {
        if (ball.getY() < 0 || (ball.getY() + ball.getRadius() * 2) > gameHeight) {
            movementBall.setMovementY(-1 * movementBall.getMovementY());
        }
    }
    
    public void ballHitsLeftOrRight(int gameWidth) {
        if (ball.getX() < 0) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            score.increse(0);
        }
                
        if (ball.getX() + ball.getRadius() * 2 > gameWidth) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            score.increse(1);
        }
    }
    
}
