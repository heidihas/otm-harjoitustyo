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
public class Score {
    
    private int leftPlayerScore;
    private int rightPlayerScore;

    public Score(int leftPlayerScore, int rightPlayerScore) {
        this.leftPlayerScore = leftPlayerScore;
        this.rightPlayerScore = rightPlayerScore;
    }
    
    public void increse(int value) {
        if (value == 1) {
            leftPlayerScore++;
        } else if (value == 0) {
            rightPlayerScore++;
        }    
    }

    @Override
    public String toString() {
        return this.leftPlayerScore + " : " + this.rightPlayerScore;
    }
}
