/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.logics;

import java.util.ArrayList;
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
    private final Score score;
    private final Paddle leftPaddle;
    private final Paddle rightPaddle;
    private final Ball ball;
    private final Movement movementPaddles;
    private final Movement movementBall;
    private final ArrayList<Integer> paddleMovements;
    
    public PongLogics(Score score, Paddle leftPaddle, Paddle rightPaddle, 
            Ball ball, Movement movementPaddles, Movement movementBall, 
            ArrayList<Integer> paddleMovements) {
        this.score = score;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ball = ball;
        this.movementPaddles = movementPaddles;
        this.movementBall = movementBall;
        this.paddleMovements = paddleMovements;
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
            leftPaddle.setY(leftPaddle.getY() + movementPaddles.getMovementY());
        }

        if (paddleMovements.get(1) == 1) {
            leftPaddle.setY(leftPaddle.getY() - movementPaddles.getMovementY());
        }

        if (paddleMovements.get(2) == 1) {
            rightPaddle.setY(rightPaddle.getY() + movementPaddles.getMovementY());
        }
                
        if (paddleMovements.get(3) == 1) {
            rightPaddle.setY(rightPaddle.getY() - movementPaddles.getMovementY());
        }
    }
    
    public void ballHitsPaddle() {     
        if (leftPaddle.ballHitsVerticalLeft(ball.getX(), ball.getY(), ball.getRadius())) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            
        } else if (rightPaddle.ballHitsVerticalRight(ball.getX(), ball.getY(), ball.getRadius())) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            
        } else if (leftPaddle.ballHitsHorizontalLeft(ball.getX(), ball.getY(), ball.getRadius())) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            movementBall.setMovementY(-1 * movementBall.getMovementY());
            
        } else if (rightPaddle.ballHitsHorizontalRight(ball.getX(), ball.getY(), ball.getRadius())) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            movementBall.setMovementY(-1 * movementBall.getMovementY());
        }
    }
    
    public void paddlesOnBoard(int gameHeight) {
        if (leftPaddle.getY() < 0) { 
            leftPaddle.setY(0);
        } else if ((leftPaddle.getY() + leftPaddle.getHeight()) > gameHeight) {
            leftPaddle.setY(gameHeight - leftPaddle.getHeight());
        }
                
        if (rightPaddle.getY() < 0) { 
            rightPaddle.setY(0);
        } else if ((rightPaddle.getY() + rightPaddle.getHeight()) > gameHeight) {
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
            score.increase(0);
            
        } else if (ball.getX() + ball.getRadius() * 2 > gameWidth) {
            movementBall.setMovementX(-1 * movementBall.getMovementX());
            score.increase(1);
        }
    }
    
}
