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
public class Player {
    private Integer id;
    private String name;
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
    
    public void increseScore(int score) {
        this.score += score;
    }
    
}
