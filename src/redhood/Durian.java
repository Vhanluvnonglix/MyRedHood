package redhood;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import redhood.Fruit;

public class Durian extends Fruit {
    public Durian(int x, int y, String imagePath, int fallSpeed) {
        super(x, y, imagePath, fallSpeed); 
    }

    @Override
    public void fall() {
        y += fallSpeed; 
        if (y > 700) {  
            y = 0; 
            x = (int)(Math.random() * (1000 - image.getWidth(null))); 
        }
    }
}
