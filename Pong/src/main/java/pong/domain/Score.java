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
    
    public void setLeftScore(int leftPlayerScore) {
        this.leftPlayerScore = leftPlayerScore;
    }
    
    public void setRightScore(int rightPlayerScore) {
        this.rightPlayerScore = rightPlayerScore;
    }
    
    public int getLeftScore() {
        return this.leftPlayerScore;
    }
    
    public int getRightScore() {
        return this.rightPlayerScore;
    }
    
    public String getLeftScoreString() {
        return String.valueOf(this.leftPlayerScore);
    }
    
    public String getRightScoreString() {
        return String.valueOf(this.rightPlayerScore);
    }

    @Override
    public String toString() {
        return this.leftPlayerScore + " : " + this.rightPlayerScore;
    }
}
