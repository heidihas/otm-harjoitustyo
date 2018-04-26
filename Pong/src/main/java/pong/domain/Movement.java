/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.domain;

import java.util.Random;

/**
 *
 * @author Heidi
 */
public class Movement {
    
    private double movementX;
    private double movementY;

    public Movement(double xMovement, double yMovement) {
        this.movementX = xMovement;
        this.movementY = yMovement;
    }

    public double getMovementX() {
        return movementX;
    }

    public void setMovementX(double xMovement) {
        this.movementX = xMovement;
    }

    public double getMovementY() {
        return movementY;
    }

    public void setMovementY(double yMovement) {
        this.movementY = yMovement;
    }
    
    public void setLevel(Object obj) {
        if (obj.equals("Easy")) {
            this.movementX = 2.5;
            this.movementY = 2.5;
        } else if (obj.equals("Medium")) {
            this.movementX = 4;
            this.movementY = 4;
        } else {
            this.movementX = 6;
            this.movementY = 6;
        }
    }
    
    public void randomDirection() {
        Random random = new Random();
        double p = Math.pow(-1, random.nextInt());
        this.movementX = this.movementX * p;
        this.movementY = this.movementY * p;
    }
}
