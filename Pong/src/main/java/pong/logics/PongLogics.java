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
/**
 * The class manages the game logics and rules in Pong.
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
    
    /**
     * The method tells whether a player has won the game.
     *
     * @see pong.domain.Score#getLeftScore() 
     * @see pong.domain.Score#getRightScore() 
     * 
     * @return true if a player's score equals the winning score or false if not
     */
    public boolean playerWon() {
        return (score.getLeftScore() == winningScore || score.getRightScore() == winningScore);
    }
    
    /**
     * The method moves the ball according to the ball movement.
     * 
     * @see pong.domain.Ball#move(pong.domain.Movement) 
     */
    public void moveBall() {
        ball.move(movementBall);
    }
    
    /**
     * The method increases the ball speed according to current time and thread conditions.
     * 
     * @see pong.domain.Movement
     */
    public void increaseBallSpeed() {
        movementBall.setMovementX(movementBall.getMovementX() 
                + 0.002 * (1 / movementBall.getMovementX()));
        movementBall.setMovementY(movementBall.getMovementY() 
                + 0.002 * (1 / movementBall.getMovementY()));
    }
    
    /**
     * The method moves the paddles according to the paddle movement and action in the player keys.
     * 
     * @see pong.domain.Paddle
     * @see pong.domain.Movement
     */
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
    
    /**
     * The method checks whether the ball hits a paddle. If yes, the movement of the ball is set to the opposite direction.
     * 
     * @see pong.domain.Paddle#ballHitsVerticalLeft(double, double, double) 
     * @see pong.domain.Paddle#ballHitsVerticalRight(double, double, double) 
     * @see pong.domain.Paddle#ballHitsHorizontalLeft(double, double, double) 
     * @see pong.domain.Paddle#ballHitsHorizontalRight(double, double, double) 
     * @see pong.domain.Ball
     * @see pong.domain.Movement
     */
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
    
    /**
     * The method checks whether the paddles stay on the defined game board.
     * 
     * @param gameHeight defined integer value for the game board height provided by the application
     * 
     * @see pong.domain.Paddle
     */
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
    
    /**
     * The method checks whether the ball hits the up or down edge of the game board.
     * 
     * @param gameHeight defined integer value for the game board height provided by the application
     * 
     * @see pong.domain.Ball
     * @see pong.domain.Movement
     */
    public void ballHitsUpOrDown(int gameHeight) {
        if (ball.getY() < 0 || (ball.getY() + ball.getRadius() * 2) > gameHeight) {
            movementBall.setMovementY(-1 * movementBall.getMovementY());
        }
    }
    
    /**
     * The method checks whether the ball hits the left or right edge of the game board.
     * 
     * @param gameWidth defined integer value for the game board width provided by the application
     * 
     * @see pong.domain.Ball
     * @see pong.domain.Movement
     * @see pong.domain.Score#increase(int) 
     */
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
