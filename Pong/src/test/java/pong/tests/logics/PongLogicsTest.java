/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.logics;

import java.util.ArrayList;
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
    ArrayList<Integer> paddlemovements;
    PlayerDao dao;
    Database database;
    
    @Before
    public void setUp() {
        
        logics = new PongLogics(score, leftPaddle, rightPaddle, ball, 
                movementPaddles, movementBall, paddlemovements, dao, database);
    }
    
    @Test
    public void createdLogicsExists() {
        assertTrue(logics!=null);      
    }
    
}
