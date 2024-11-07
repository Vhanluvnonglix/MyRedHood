/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhood;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Fruit {
    protected int x, y;
    protected int fallSpeed;  
    protected int score;
    protected Image image;

    public Fruit(int x, int y, String imagePath, int fallSpeed) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(imagePath).getImage();
        this.fallSpeed = fallSpeed; 
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public abstract void fall();

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public int getFallSpeed() {
        return fallSpeed;
    }

    public int getScore() {
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
