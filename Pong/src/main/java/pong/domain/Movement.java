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
}
