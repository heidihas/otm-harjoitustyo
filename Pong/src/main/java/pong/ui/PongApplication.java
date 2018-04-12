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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JLabel;
import pong.domain.Ball;
import pong.domain.Movement;
import pong.domain.Paddle;
import pong.domain.Score;

/**
 *
 * @author Heidi
 */
public class PongApplication extends Application {
    // 1) Perustoiminta
    // pallo nopeutuu
    // mailojen reunat kimmottavat
    
    // 2) Tietokanta
    // tietokannan käyttöönotto
    // olemassaolevien käyttäjänimien valinta?
    // tilastointi
    
    // 3) Hienosäätö visuaalisesti
    // lopetussivun visuaalinen hienosäätö
    
    // 4) Jos jää aikaa
    // valittavissa oleva aloitusnopeus
    // valittavissa pelin päättymispisteet
    
    // 5) Dokumentointi
    // VIIKKO4!! sovelluslogiikka eri tiedostoissa: jaa lyhyempiin metodeihin
    // VIIKKO4!! testit käyttöliittymään?
    // virhetilanteet (kuten ei nimetöntä)
    
    // start liian pitkä!!
    public void start(Stage stage) {
        final int gameWidth = 640;
        final int gameHeight = 480;

        stage.setTitle("Pong");

        // first page scene setup
        Image image = new Image("file:pong.png");
        Label text = new Label("Player names");
        Label text1 = new Label("Player 1: ");
        TextField name1 = new TextField();
        Label text2 = new Label("Player 2: ");
        TextField name2 = new TextField();
        Button startButton = new Button("Start");
        
        GridPane gridStart = new GridPane();
        
        ImageView view = new ImageView(image);
        view.setScaleX(0.5);
        view.setScaleY(0.5);
        
        gridStart.add(view, 0, 0);
        gridStart.setAlignment(Pos.CENTER);
        
        GridPane grid = new GridPane();
        
        grid.add(text, 0, 0);
        grid.add(text1, 0, 1);
        grid.add(name1, 1, 1);
        grid.add(text2, 0, 2);
        grid.add(name2, 1, 2);
        grid.add(startButton, 1, 5);
        
        grid.setPrefSize(640, 180);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(0, 20, 20, 20));
        
        BorderPane border = new BorderPane();
        border.prefHeight(gameHeight);
        border.prefWidth(gameWidth);
        border.setTop(gridStart);
        border.setCenter(grid);
        
        Scene firstScene = new Scene(border);
        stage.setScene(firstScene);
        
        // last page scene setup
        Label winnerName = new Label("");
        winnerName.setTextFill(Color.RED);
        Label lastText = new Label("won the round with the score");
        Label winnerScore = new Label("");
        Label mark = new Label("!");
        Button restartButton = new Button("Re-start");
        Button newGameButton = new Button("New game");
        Button endGameButton = new Button("End game");
        
        GridPane gridLast = new GridPane();
        
        gridLast.add(winnerName, 1, 0);
        gridLast.add(lastText, 0, 2);
        gridLast.add(winnerScore, 1, 3);
        gridLast.add(mark, 1, 4);
        gridLast.add(restartButton, 0, 7);
        gridLast.add(newGameButton, 0, 8);
        gridLast.add(endGameButton, 0, 9);
        
        gridLast.setPrefSize(640, 480);
        gridLast.setAlignment(Pos.CENTER);
        gridLast.setVgap(10);
        gridLast.setHgap(10);
        gridLast.setPadding(new Insets(20, 20, 20, 20));
        
        Scene lastScene = new Scene(gridLast);
        
        // game scene setup
        Group root = new Group();
        Scene gameScene = new Scene(root);

        Canvas canvas = new Canvas(gameWidth, gameHeight);
        root.getChildren().add(canvas);

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        
        // setup player names
        JLabel player1 = new JLabel("");
        JLabel player2 = new JLabel("");
        
        // setup score
        Score score = new Score(0, 0);

        // create paddle for right player
        Paddle rightPaddle = new Paddle(615, gameHeight / 2 - 40, 20, 80, Color.BURLYWOOD);

        // create paddle for left player
        Paddle leftPaddle = new Paddle(5, gameHeight / 2 - 40, 20, 80, Color.BURLYWOOD);

        // create ball
        Ball ball = new Ball(gameWidth / 2, gameHeight / 2, 10);
        
        // setup movements
        Movement movementBall = new Movement(2.5, 2.5);
        Movement movementPaddles = new Movement(4.0, 4.0);
        
        
        ArrayList<Integer> paddleMovements = new ArrayList<>();
        // index 0 and 1 tell whether left paddle moves up or down
        paddleMovements.add(0);
        paddleMovements.add(0);
        // index 2 and 3 tell whether right paddle moves up or down
        paddleMovements.add(0);
        paddleMovements.add(0);

        keyboardSetUp(gameScene, paddleMovements);
        
        // right paddle moves with up and down
        // left paddle moves with w and s 
        
        AnimationTimer animationTimer = new AnimationTimer() {
            private long sleepNanoseconds = 1000000000 * 1000000;
            private long prevTime = 0;
            // handle liian pitkä!!!
            public void handle(long currentNanoTime) {
                // increase the speed of ball and paddles
                /*if ((currentNanoTime - prevTime) >= sleepNanoseconds) {
                    movementBall.setMovementX(movementBall.getMovementX()+1);
                    movementBall.setMovementY(movementBall.getMovementY()+1);
                    movementPaddles.setMovementX(movementPaddles.getMovementX()*1.1);
                    movementPaddles.setMovementY(movementPaddles.getMovementY()*1.1);
                    prevTime = currentNanoTime;
                }*/
                
                // draw game board
                graphics.clearRect(0, 0, gameWidth, gameHeight);
                
                // draw score
                graphics.setFill(Color.BLACK);
                graphics.fillText(score.toString(), gameWidth / 2 - 15, 40);
                
                // draw player names
                graphics.setFill(Color.INDIANRED);
                graphics.fillText(player1.getText(), 100, 40);
                graphics.fillText(player2.getText(), (540 - player2.getPreferredSize().getWidth()), 40);
                
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
                    leftPaddle.setY(leftPaddle.getY() + (int) movementPaddles.getMovementX());
                }

                if (paddleMovements.get(1) == 1) {
                    leftPaddle.setY(leftPaddle.getY() - (int) movementPaddles.getMovementX());
                }

                if (paddleMovements.get(2) == 1) {
                    rightPaddle.setY(rightPaddle.getY() + (int) movementPaddles.getMovementX());
                }
                
                if (paddleMovements.get(3) == 1) {
                    rightPaddle.setY(rightPaddle.getY() - (int) movementPaddles.getMovementX());
                }

                // move ball
                ball.move(movementBall);

                // check if ball hits paddle
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
                
                if (score.getLeftScore() == 2 || score.getRightScore() == 2) {
                    stop();
                    if (score.getLeftScore() > score.getRightScore()) {
                        winnerName.setText(name1.getText());
                        winnerScore.setText(score.getLeftScoreString());
                    } else {
                        winnerName.setText(name2.getText());
                        winnerScore.setText(score.getRightScoreString());
                    }
                    stage.setScene(lastScene);
                }
            }
        };

        startButton.setOnAction((event) -> {
            player1.setText("Player 1: " + name1.getText());
            player2.setText("Player 2: " + name2.getText());
            stage.setScene(gameScene);
            animationTimer.start();
        });
        
        restartButton.setOnAction((event) -> {
            score.setLeftScore(0);
            score.setRightScore(0);
            rightPaddle.setX(615);
            rightPaddle.setY(gameHeight / 2 - 40);
            leftPaddle.setX(5);
            leftPaddle.setY(gameHeight / 2 - 40);
            movementPaddles.setMovementX(4.0);
            movementPaddles.setMovementY(4.0);
            ball.setX(gameWidth / 2);
            ball.setY(gameHeight / 2);
            movementBall.setMovementX(2.5);
            movementBall.setMovementY(2.5);
            paddleMovements.removeAll(paddleMovements);
            paddleMovements.add(0);
            paddleMovements.add(0);
            paddleMovements.add(0);
            paddleMovements.add(0);
            keyboardSetUp(gameScene, paddleMovements);
            stage.setScene(gameScene);
            animationTimer.start();
        });
        
        newGameButton.setOnAction((event) -> {
            score.setLeftScore(0);
            score.setRightScore(0);
            rightPaddle.setX(615);
            rightPaddle.setY(gameHeight / 2 - 40);
            leftPaddle.setX(5);
            leftPaddle.setY(gameHeight / 2 - 40);
            movementPaddles.setMovementX(4.0);
            movementPaddles.setMovementY(4.0);
            ball.setX(gameWidth / 2);
            ball.setY(gameHeight / 2);
            movementBall.setMovementX(2.5);
            movementBall.setMovementY(2.5);
            paddleMovements.removeAll(paddleMovements);
            paddleMovements.add(0);
            paddleMovements.add(0);
            paddleMovements.add(0);
            paddleMovements.add(0);
            keyboardSetUp(gameScene, paddleMovements);
            name1.setText("");
            name2.setText("");
            stage.setScene(firstScene);
        });
        
        endGameButton.setOnAction((event) -> {
            stage.close();
        });
        
        stage.show();
    }
    // keyboardSetUp liian pitkä!!!
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
        launch(PongApplication.class);
    }
}
