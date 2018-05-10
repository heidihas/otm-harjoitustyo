/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.domain;

import javafx.scene.paint.Color;

/**
 *
 * @author Heidi
 */
/**
 * The class creates a Paddle object and manages its location in the game.
 */
public class Paddle {
    
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public Paddle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * The method checks if the ball hits the left paddle on its vertical side.
     * 
     * @param x x coordinate of the ball provided by the application
     * @param y y coordinate of the ball provided by the application
     * @param radius radius of the ball provided by the application
     * 
     * @return true if the ball hits the left paddle or false if not
     */
    public boolean ballHitsVerticalLeft(double x, double y, double radius) {
        return ((x <= (this.x + this.width)) && (x > (this.x + this.width - radius)))
                && ((y > this.y) && (y < (this.y + this.height)));    
    }
    
    /**
     * The method checks if the ball hits the right paddle on its vertical side.
     * 
     * @param x x coordinate of the ball provided by the application
     * @param y y coordinate of the ball provided by the application 
     * @param radius radius of the ball provided by the application
     * 
     * @return true if the ball hits the right paddle or false if not
     */
    public boolean ballHitsVerticalRight(double x, double y, double radius) {
        return (((x + 2 * radius) >= this.x) && ((x + 2 * radius) < (this.x + radius)))
                && ((y > this.y) && (y < (this.y + this.height)));
    }
    
    /**
     * The method checks if the ball hits the left paddle on this horizontal sides.
     * 
     * @param x x coordinate of the ball provided by the application
     * @param y y coordinate of the ball provided by the application
     * @param radius radius of the ball provided by the application
     * 
     * @return true if the ball hits the left paddle or false if not
     */
    public boolean ballHitsHorizontalLeft(double x, double y, double radius) {
        return (x <= (this.x + this.width))
                && (((y + 2 * radius) >= this.y) && (y <= (this.y + this.height)));
    }
    
    /**
     * The method checks if the ball hits the right paddle on its horizontal sides.
     * 
     * @param x x coordinate of the ball provided by the application
     * @param y y coordinate of the ball provided by the application
     * @param radius radius of the ball provided by the application
     * 
     * @return true if the ball hits the right paddle or false if not
     */
    public boolean ballHitsHorizontalRight(double x, double y, double radius) {
        return ((x + 2 * radius) >= this.x)
                && (((y + 2 * radius) >= this.y) && (y <= (this.y + this.height)));
    }
}
