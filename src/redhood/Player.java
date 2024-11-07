/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhood;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

class Player {
    private int x;
    private int y;
    private Image image;
    
    //Blink blink
    private boolean Blinking = false;
    private int BlinkCount = 0; 
    private final int BlinkMax = 20;
    
    int Hp; //blood
    
    private Image left;
    private Image right;
    
    public Player(int x, int y, String leftImage, String rightImage){
        this.x = x;
        this.y = y;
        this.left = new ImageIcon(leftImage).getImage();
        this.right = new ImageIcon(rightImage).getImage();
        this.image = left;
        this.Hp = 3;
    }
    
    public void draw(Graphics g){
        if(Blinking && BlinkCount < BlinkMax ){
            if(BlinkCount % 2 == 0){
                g.drawImage(image, x, y, null);
            }
        }else{
            g.drawImage(image, x, y, null);
        }

    }
    
    public void Movement(int dx){

        if(dx<0){
            image = left;
        }else if(dx>0){
            image = right;
        }
        
        x = x + dx;
        
        if(x<0){
            x=0;
        }else if(x>1000 - image.getWidth(null)){
            x = 1000 - image.getWidth(null);
        }
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getHp(){
        return Hp;
    }
    
    public void setHp(int Hp){
        this.Hp = Hp; 
    }
    
    //Rock collided
    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
    
    public void deleteHp(int amount){
    Hp -= amount;
        if (Hp < 0) {
            Hp = 0;  
        }
        Blinking = true; 
        BlinkCount = 0;
    }

    
    public boolean isBlinking(){
        return Blinking;
    }
    
    public void Blink(){
        if (BlinkCount < BlinkMax){
            BlinkCount++;
            
            if(BlinkCount >= BlinkMax){
                Blinking = false; //Stop Blinking 
            }
        }
    }

    
}
