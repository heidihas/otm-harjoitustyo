/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.ui;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pong.domain.Ball;
import pong.domain.Movement;
import pong.domain.Paddle;
import pong.domain.Score;

/**
 *
 * @author Heidi
 */
public class PongApplication extends Application{
    
    public void start(Stage stage) {
        final int gameWidth = 640;
        final int gameHeight = 480;

        stage.setTitle("Pong");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(gameWidth, gameHeight);
        root.getChildren().add(canvas);

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        
        // setup score
        Score score = new Score(0, 0);

        // create paddle for right player
        Paddle rightPaddle = new Paddle(615, 20, 20, 80, Color.BURLYWOOD);

        // create paddle for left player
        Paddle leftPaddle = new Paddle(5, 20, 20, 80, Color.BURLYWOOD);

        // create ball
        Ball ball = new Ball(gameWidth / 2, gameHeight / 2, 10);
        
        // setup movements
        Movement movementBall = new Movement(2.0, 2.0);
        Movement movementPaddles = new Movement(2.0, 2.0);
        
        
        ArrayList<Integer> paddleMovements = new ArrayList<>();
        // index 0 and 1 tell whether left paddle moves up or down
        paddleMovements.add(0);
        paddleMovements.add(0);
        // index 2 and 3 tell whether right paddle moves up or down
        paddleMovements.add(0);
        paddleMovements.add(0);

        keyboardSetUp(scene, paddleMovements);
        
        // right paddle moves with up and down
        // left paddle moves with w and s 
        
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                graphics.clearRect(0, 0, gameWidth, gameHeight);
                
                // draw score
                graphics.setFill(Color.BLACK);
                graphics.fillText(score.toString(), gameWidth / 2 - 15, 40);

                // draw left paddle
                graphics.setFill(leftPaddle.getColor());
                graphics.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());

                // draw right paddle
                graphics.setFill(rightPaddle.getColor());
                graphics.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());

                // draw ball
                graphics.setFill(Color.INDIANRED);
                graphics.fillOval(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);

                // move paddles
                if (paddleMovements.get(0) == 1) {
                    leftPaddle.setY(leftPaddle.getY() + (int)movementPaddles.getMovementX());
                }

                if (paddleMovements.get(1) == 1) {
                    leftPaddle.setY(leftPaddle.getY() - (int)movementPaddles.getMovementX());
                }

                if (paddleMovements.get(2) == 1) {
                    rightPaddle.setY(rightPaddle.getY() + (int)movementPaddles.getMovementX());
                }
                
                if (paddleMovements.get(3) == 1) {
                    rightPaddle.setY(rightPaddle.getY() - (int)movementPaddles.getMovementX());
                }

                // move ball
                ball.move(movementBall);

                // check if ball hits paddle
                /*if ((((int)ball.getY() >= leftPaddle.getY()) && ((int)ball.getY() <= (leftPaddle.getY()+leftPaddle.getHeight()))) && !leftPaddle.outside((int)ball.getX(), (int)ball.getY())) { 
                    movementBall.setMovementX(-1 * movementBall.getMovementX());
                    movementBall.setMovementY(-1 * movementBall.getMovementY());
                }*/
                
                if (!leftPaddle.outside((int)ball.getX(), (int)ball.getY())) { 
                    movementBall.setMovementX(-1 * movementBall.getMovementX()); 
                } 
                
                /*if (((int)ball.getX() > 615) && !rightPaddle.outside((int)ball.getX(), (int)ball.getY())) { 
                    movementBall.setMovementX(-1 * movementBall.getMovementX());
                    movementBall.setMovementY(-1 * movementBall.getMovementY());
                }*/
                
                if (!rightPaddle.outside((int)ball.getX()+rightPaddle.getWidth(), (int)ball.getY())) { 
                    movementBall.setMovementX(-1 * movementBall.getMovementX());
                } 
                
                // check if paddles stay on game board
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

                // check if ball hits the up or down edge of the game board
                if (ball.getY() < 0 || (ball.getY() + ball.getRadius() * 2) > gameHeight) {
                    movementBall.setMovementY(-1 * movementBall.getMovementY());
                }

                // check if ball hits the left or right edge of the game board
                if (ball.getX() < 0) {
                    movementBall.setMovementX(-1 * movementBall.getMovementX());
                    score.increse(0);
                }
                
                if (ball.getX() + ball.getRadius() * 2 > gameWidth) {
                    movementBall.setMovementX(-1 * movementBall.getMovementX());
                    score.increse(1);
                }
            }
        }.start();

        stage.show();
    }

    private void keyboardSetUp(Scene scene, ArrayList<Integer> paddleMovements) {
        scene.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.S) {
                paddleMovements.set(0, 1);
            }
            
            if (e.getCode() == KeyCode.W) {
                paddleMovements.set(1, 1);
            }
            
            if (e.getCode() == KeyCode.DOWN) {
                paddleMovements.set(2, 1);
            }
            
            if (e.getCode() == KeyCode.UP) {
                paddleMovements.set(3, 1);
            }
        });
        
        scene.setOnKeyReleased((KeyEvent e) -> {
            if (e.getCode() == KeyCode.S) {
                paddleMovements.set(0, 0);
            }
            
            if (e.getCode() == KeyCode.W) {
                paddleMovements.set(1, 0);
            }
            
            if (e.getCode() == KeyCode.DOWN) {
                paddleMovements.set(2, 0);
            }
            
            if (e.getCode() == KeyCode.UP) {
                paddleMovements.set(3, 0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
