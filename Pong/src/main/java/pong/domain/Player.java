/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.domain;

/**
 *
 * @author Heidi
 */
/**
 * The class creates a Player object for each entry in the player table of the database.
 */
public class Player {
    private final Integer id;
    private final String name;
    private int score;

    public Player(Integer id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public String getScoreString() {
        return String.valueOf(this.score);
    }
    
    /**
     * The method increases the player's score with a given amount.
     * 
     * @param score score amount to be added, provided by the application
     */
    public void increaseScore(int score) {
        this.score += score;
    }
    
}
