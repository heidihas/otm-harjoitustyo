/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.ui;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JLabel;
import org.apache.commons.lang3.StringUtils;
import pong.dao.PlayerDao;
import pong.database.Database;
import pong.domain.Ball;
import pong.domain.Movement;
import pong.domain.Paddle;
import pong.domain.Player;
import pong.domain.Score;
import pong.logics.PongLogics;

/**
 *
 * @author Heidi
 */
/**
 * The class provides the game application with all its functions and scenes.
 */
public class PongApplication extends Application {
    // testausdokumentti 1h
    // tarkista dokumentointi 2h
    
    // Jatkokehitysideoita:
    // valittavissa pelin päättymispisteet
    // aloitusnopeus vaikuttaa mailoihin
    // rajattu määrä käyttäjänimiä
    // error-viesti ei siirrä aloitussivun sijainteja
    // yksinpeli
    // vaikeustason alkuoletus
    // kulmien kimmottaminen, mahdolliset bugit (jää kiinni mailaan)
    // kuvan integroiminen sovellukseen
    
    /**
     * The method builds the application layout properties, takes care of the scene shifts and collects all parameters and events in the game. 
     * 
     * @param stage a Stage object to set the current application scene
     * 
     * @see pong.dao.PlayerDao
     * @see pong.database.Database
     * @see pong.domain.Ball
     * @see pong.domain.Movement
     * @see pong.domain.Paddle
     * @see pong.domain.Player
     * @see pong.domain.Score
     * @see pong.logics.PongLogics
     * 
     * @throws SQLException 
     */
    @Override
    public void start(Stage stage) throws SQLException {
        
        // database set-up
        Database database = new Database("jdbc:sqlite:player.db");
        database.getConnection();
        database.init("Player");
        PlayerDao dao = new PlayerDao(database);
        
        // window set-up
        final int gameWidth = 640;
        final int gameHeight = 480;

        stage.setTitle("Pong");

        // first page scene set-up
        Image image = new Image("file:pong.png");
                
        Label text = new Label("Player names");
        
        Label text1 = new Label("Player 1: ");
        ComboBox name1 = new ComboBox();
        name1.setItems(existingPlayers(dao));
        name1.setVisibleRowCount(3);
        name1.setEditable(true);
        name1.setValue("");
        Label error1 = new Label("");
        error1.setTextFill(Color.INDIANRED);
        
        Label text2 = new Label("Player 2: ");
        ComboBox name2 = new ComboBox();
        name2.setItems(existingPlayers(dao));
        name2.setVisibleRowCount(3);
        name2.setEditable(true);
        name2.setValue("");
        Label error2 = new Label("");
        error2.setTextFill(Color.INDIANRED);
        
        Label gameLevel = new Label("Game level: ");
        ChoiceBox level = new ChoiceBox(FXCollections.observableArrayList("Easy", 
                "Medium", "Hard"));
        Label errorLevel = new Label("");
        errorLevel.setTextFill(Color.INDIANRED);
        Button startButton = new Button("Start");
        
        GridPane gridStart = new GridPane();
        
        ImageView view = new ImageView(image);
        view.setScaleX(0.5);
        view.setScaleY(0.5);
        
        gridStart.add(view, 0, 0);
        gridStart.setPrefSize(640, 250);
        gridStart.setAlignment(Pos.CENTER);
        
        GridPane grid = new GridPane();
        
        grid.add(text, 0, 0);
        grid.add(text1, 0, 1);
        grid.add(name1, 1, 1);
        grid.add(error1, 2, 1);
        grid.add(text2, 0, 2);
        grid.add(name2, 1, 2);
        grid.add(error2, 2, 2);
        grid.add(gameLevel, 0, 3);
        grid.add(level, 1, 3);
        grid.add(errorLevel, 2, 3);
        grid.add(startButton, 1, 5);
        
        grid.setPrefSize(640, 180);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(6);
        grid.setHgap(10);
        grid.setPadding(new Insets(0, 20, 20, 20));
        
        BorderPane border = new BorderPane();
        border.prefHeight(gameHeight);
        border.prefWidth(gameWidth);
        border.setTop(gridStart);
        border.setCenter(grid);
        
        Scene firstScene = new Scene(border);
        stage.setScene(firstScene);
        
        // last page scene set-up
        Label winnerName = new Label("");
        winnerName.setTextFill(Color.RED);
        winnerName.setFont(new Font ("Arial", 30));
        
        Label lastText = new Label("won the round!");
        Label top5 = new Label("Top 5 players");
        
        Label fir = new Label("1.");
        Label sec = new Label("2.");
        Label thir = new Label("3.");
        Label fou = new Label("4.");
        Label fiv = new Label("5.");
        
        Label firName = new Label(" - ");
        Label secName = new Label(" - ");
        Label thirName = new Label(" - ");
        Label fouName = new Label(" - ");
        Label fivName = new Label(" - ");
        
        Label[] printNames = new Label[5];
        printNames[0] = firName;
        printNames[1] = secName;
        printNames[2] = thirName;
        printNames[3] = fouName;
        printNames[4] = fivName;
        
        Label firScore = new Label(" - ");
        Label secScore = new Label(" - ");
        Label thirScore = new Label(" - ");
        Label fouScore = new Label(" - ");
        Label fivScore = new Label(" - ");
        
        Label[] printScores = new Label[5];
        printScores[0] = firScore;
        printScores[1] = secScore;
        printScores[2] = thirScore;
        printScores[3] = fouScore;
        printScores[4] = fivScore;
        
        Button restartButton = new Button("Re-start");
        Button newGameButton = new Button("New game");
        Button endGameButton = new Button("End game");
        
        VBox winnerText = new VBox();
        
        winnerText.getChildren().add(winnerName);
        winnerText.getChildren().add(lastText);
        
        winnerText.setPrefSize(640, 150);
        winnerText.setSpacing(20);
        winnerText.setAlignment(Pos.CENTER);
        winnerText.setPadding(new Insets(20, 20, 20, 20));
        
        GridPane gridTop = new GridPane();
        
        gridTop.add(fir, 0, 0);
        gridTop.add(sec, 0, 1);
        gridTop.add(thir, 0, 2);
        gridTop.add(fou, 0, 3);
        gridTop.add(fiv, 0, 4);
        gridTop.add(firName, 1, 0);
        gridTop.add(secName, 1, 1);
        gridTop.add(thirName, 1, 2);
        gridTop.add(fouName, 1, 3);
        gridTop.add(fivName, 1, 4);
        gridTop.add(firScore, 2, 0);
        gridTop.add(secScore, 2, 1);
        gridTop.add(thirScore, 2, 2);
        gridTop.add(fouScore, 2, 3);
        gridTop.add(fivScore, 2, 4);
        
        gridTop.setPrefSize(640, 180);
        gridTop.setAlignment(Pos.CENTER);
        gridTop.setVgap(10);
        gridTop.setHgap(30);
        gridTop.setPadding(new Insets(0, 20, 20, 20));
        
        VBox topFive = new VBox();
        
        topFive.getChildren().add(top5);
        topFive.getChildren().add(gridTop);
        
        topFive.setPrefSize(640, 200);
        topFive.setSpacing(20);
        topFive.setAlignment(Pos.CENTER);
        topFive.setPadding(new Insets(20, 20, 20, 20));
        
        HBox buttons = new HBox();
        
        buttons.getChildren().add(restartButton);
        buttons.getChildren().add(newGameButton);
        buttons.getChildren().add(endGameButton);
        
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(58, 20, 30, 20));
        
        BorderPane borderLast = new BorderPane();
        borderLast.prefHeight(gameHeight);
        borderLast.prefWidth(gameWidth);
        borderLast.setTop(winnerText);
        borderLast.setCenter(topFive);
        borderLast.setBottom(buttons);
        
        Scene lastScene = new Scene(borderLast);
        
        // game scene set-up
        Group root = new Group();
        Scene gameScene = new Scene(root);

        Canvas canvas = new Canvas(gameWidth, gameHeight);
        root.getChildren().add(canvas);

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        
        // player names set-up
        JLabel player1 = new JLabel("");
        JLabel player2 = new JLabel("");
        
        // score set-up
        Score score = new Score(0, 0);

        // right paddle set-up
        Paddle rightPaddle = new Paddle(615, gameHeight / 2 - 40, 20, 80, Color.BURLYWOOD);

        // left paddle set-up
        Paddle leftPaddle = new Paddle(5, gameHeight / 2 - 40, 20, 80, Color.BURLYWOOD);

        // ball set-up
        Ball ball = new Ball(gameWidth / 2, gameHeight / 2, 10);
        
        // movement set-up
        Movement movementBall = new Movement(0, 0);
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
        
        // logics and winning score set-up
        PongLogics logics = new PongLogics(score, leftPaddle, rightPaddle, ball, 
                movementPaddles, movementBall, paddleMovements);
        logics.setWinningScore(10);
        
        AnimationTimer animationTimer = new AnimationTimer() {
            private final long sleepDelay = TimeUnit.NANOSECONDS.convert(40, TimeUnit.SECONDS);
            private final long sleepNanoseconds = TimeUnit.NANOSECONDS.convert(2, TimeUnit.SECONDS);
            private final long startTime = System.nanoTime();
            private long prevTime = System.nanoTime();
            
            /**
             * The AnimationTimer method draws the current game scene according to the progress and checks the events taking place in the game.
             * 
             * @param currentNanoTime 
             */
            @Override
            public void handle(long currentNanoTime) {
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
                graphics.fillRect(leftPaddle.getX(), leftPaddle.getY(), 
                        leftPaddle.getWidth(), leftPaddle.getHeight());

                // draw right paddle
                graphics.setFill(rightPaddle.getColor());
                graphics.fillRect(rightPaddle.getX(), rightPaddle.getY(), 
                        rightPaddle.getWidth(), rightPaddle.getHeight());

                // draw ball
                graphics.setFill(Color.INDIANRED);
                graphics.fillOval(ball.getX(), ball.getY(), ball.getRadius() * 2, 
                        ball.getRadius() * 2);

                // move paddles
                logics.movePaddles();

                // move ball
                logics.moveBall();

                // increase the ball speed
                if ((currentNanoTime - startTime) >= sleepDelay) {
                    if ((currentNanoTime - prevTime) >= sleepNanoseconds) {
                        logics.increaseBallSpeed();
                        prevTime = currentNanoTime;
                    }
                }
                
                // check if ball hits paddle
                logics.ballHitsPaddle();
                
                // check if paddles stay on game board
                logics.paddlesOnBoard(gameHeight);

                // check if ball hits the up or down edge of the game board
                logics.ballHitsUpOrDown(gameHeight);

                // check if ball hits the left or right edge of the game board
                logics.ballHitsLeftOrRight(gameWidth);
                
                // update scores and toplist when player wins
                if (logics.playerWon()) {
                    try {
                        stop();
                        
                        String playerName1 = player1.getText().substring(10);
                        String playerName2 = player2.getText().substring(10);
                        
                        if (score.getLeftScore() > score.getRightScore()) {
                            winnerName.setText(playerName1);
                        } else {
                            winnerName.setText(playerName2);
                        }
                        
                        Player n1 = new Player(0, playerName1, score.getLeftScore());
                        Player n2 = new Player(1, playerName2, score.getRightScore());
                        
                        dao.saveOrUpdate(n1);
                        dao.saveOrUpdate(n2);
                        
                        drawFiveTop(dao, printNames, printScores);
                        
                        stage.setScene(lastScene);
                    } catch (SQLException ex) {
                        Logger.getLogger(PongApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        startButton.setOnAction((event) -> {    
            // print error messages
            if (nameError(name1) || nameError(name2) || level.getValue() == null 
                    || sameName(name1, name2)) {
                error1.setText("");
                error2.setText("");
                errorLevel.setText("");
                String errorSameName = "Choose different names";
                String errorWrongType = "Maximum 8 characters";
                String errorNoLevel = "Choose level";
                
                if (nameError(name1) && nameError(name2)) {
                    error1.setText(errorWrongType);
                    error2.setText(errorWrongType);
                    name1.setValue("");
                    name2.setValue("");
                    
                } else if (nameError(name1)) {
                    error1.setText(errorWrongType);
                    name1.setValue("");
                    
                } else if (nameError(name2)) {
                    error2.setText(errorWrongType);
                    name2.setValue("");
                    
                } else if (sameName(name1, name2)) {
                    error1.setText(errorSameName);
                    error2.setText(errorSameName);
                    name1.setValue("");
                    name2.setValue("");     
                } 
                
                if (level.getValue() == null){
                    errorLevel.setText(errorNoLevel);
                }
                
            } else {
                try {
                    Player p1 = new Player(0, name1.getValue().toString(), 0);
                    Player p2 = new Player(0, name2.getValue().toString(), 0);
                    dao.saveOrUpdate(p1);
                    dao.saveOrUpdate(p2);
                    
                    comboUpdate(name1, name2, p1, p2);
                    
                    player1.setText("Player 1: " + p1.getName());
                    player2.setText("Player 2: " + p2.getName());
                    
                    movementBall.setLevel(level.getValue());
                    movementBall.randomDirection();
                    
                    stage.setScene(gameScene);
                    animationTimer.start();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(PongApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        restartButton.setOnAction((event) -> {
            resetGame(gameHeight, gameWidth, score, rightPaddle, leftPaddle, 
                    movementPaddles, ball, movementBall, paddleMovements, level);
            stage.setScene(gameScene);
            animationTimer.start();
        });
        
        newGameButton.setOnAction((event) -> {
            resetGame(gameHeight, gameWidth, score, rightPaddle, leftPaddle, 
                    movementPaddles, ball, movementBall, paddleMovements, level);
            name1.setValue("");
            name2.setValue("");
            error1.setText("");
            error2.setText("");
            errorLevel.setText("");
            level.setValue(null);
            stage.setScene(firstScene);
        });
        
        endGameButton.setOnAction((event) -> {
            stage.close();
        });
        
        stage.show();
    }
    
    private void resetGame(int gameHeight, int gameWidth, Score score, 
            Paddle rightPaddle, Paddle leftPaddle, Movement movementPaddles, Ball ball, 
            Movement movementBall, ArrayList<Integer> paddleMovements, ChoiceBox level) {
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
        
        movementBall.setLevel(level.getValue());
        movementBall.randomDirection();
        
        paddleMovements.removeAll(paddleMovements);
        paddleMovements.add(0);
        paddleMovements.add(0);
        paddleMovements.add(0);
        paddleMovements.add(0);
    }
    
    private boolean sameName(ComboBox name1, ComboBox name2) {
        return (name1.getValue().toString().equals(name2.getValue().toString()));
    }
    
    private boolean nameError(ComboBox name) {
        String nameText = name.getValue().toString();
        return (nameText.length() > 8 || nameText.length() == 0 || StringUtils.isBlank(nameText));
    }
    
    private void drawFiveTop(PlayerDao dao, Label[] printNames, Label[] printScores) {
        try {
            List<Player> topFivePlayers = dao.findFiveTop();
            
            for (int i = 0; i < topFivePlayers.size(); i++) {
                printNames[i].setText(topFivePlayers.get(i).getName());
                printScores[i].setText(topFivePlayers.get(i).getScoreString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PongApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class NameComparator  implements Comparator<String> {
        @Override
        public int compare(String obj1, String obj2) {
            return obj1.compareTo(obj2);
        }
    }
    
    private ObservableList<String> existingPlayers(PlayerDao dao) throws SQLException {
        ObservableList<String> players = FXCollections.observableArrayList();
        NameComparator com = new NameComparator();
        
        List<Player> allPlayers = dao.findAll();
        for (Player p : allPlayers) {
            players.add(p.getName());
        }
        players.sort(com);
        return players;
    }
    
    private void comboUpdate(ComboBox name1, ComboBox name2, Player p1, Player p2) {
        //adds new player names to the combobox selection if needed
        String playerName1 = p1.getName();
        String playerName2 = p2.getName();
        
        if (!name1.getItems().contains(playerName1)) {
            name1.getItems().add(playerName1);
            name2.getItems().add(playerName1);
        }
        
        if (!name1.getItems().contains(playerName2)) {
            name1.getItems().add(playerName2);
            name2.getItems().add(playerName2);
        }
        
        NameComparator com = new NameComparator();        
        name1.getItems().sort(com);
        name2.getItems().sort(com);
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

    /**
     * The main method for the application.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(PongApplication.class);
    }
}