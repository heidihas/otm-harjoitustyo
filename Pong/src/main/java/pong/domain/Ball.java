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
 * The class creates a Ball object and manages its location in the game.
 */
public class Ball {
    
    private double x;
    private double y;
    private double radius;

    public Ball(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * The method moves the ball by increasing or decreasing the coordinates according to the Movement object assigned to the ball.
     * 
     * @param movement Movement object provided by the application
     * 
     * @see pong.domain.Movement
     * @see pong.domain.Movement#getMovementX() 
     * @see pong.domain.Movement#getMovementY() 
     */
    public void move(Movement movement) {
        this.x += movement.getMovementX();
        this.y += movement.getMovementY();
    }
}
