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
public class Paddle {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Paddle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    public boolean ballHitsVerticalLeft(int x, int y) {
        if ((x == (this.x + this.width))
                && ((y > this.y) && (y < (this.y + this.height)))) {
            return true;
        }
        return false;
    }
    
    public boolean ballHitsVerticalRight(int x, int y, int radius) {
        if (((x + 2 * radius) == this.x)
                && ((y > this.y) && (y < (this.y + this.height)))) {
            return true;
        }
        return false;
    }
    
    public boolean ballHitsHorizontalLeft(int x, int y, int radius) {
        if ((x <= (this.x + this.width))
                && (((y + 2 * radius) >= this.y) && (y <= (this.y + this.height)))) {
            return true;
        }
        return false;
    }
    
    public boolean ballHitsHorizontalRight(int x, int y, int radius) {
        if (((x + 2 * radius) >= this.x)
                && (((y + 2 * radius) >= this.y) && (y <= (this.y + this.height)))) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
