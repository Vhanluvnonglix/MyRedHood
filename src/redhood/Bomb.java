/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhood;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Bomb {
    private int x, y;
    private Image image;
    private int fallSpeed;  

    public Bomb(int x, int y, String imagePath, int fallSpeed) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(imagePath).getImage();
        this.fallSpeed = fallSpeed;  
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void Bombfall() {
        y += fallSpeed;  

        if (y > 700) {  
            y = 0;
            x = (int) (Math.random() * (1000 - image.getWidth(null)));  
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }
}