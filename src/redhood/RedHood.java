/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package redhood;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

class Homegame extends JPanel {

    private ImageIcon Gamebg = new ImageIcon("D:/Red/bg.jpg");
    private ImageIcon Homebg = new ImageIcon("D:/Red/Home.png");
    private ImageIcon Start = new ImageIcon("D:/Red/Start.png");
    public JButton BStart = new JButton(Start);

    //First game dont start
    private boolean GameStarted = false;
    
    //Create player 
    private Player player;
    
    //Rock
    private Rock rock;
    private boolean RockCollided = false;
    
    //Apple 
    private Apple Apple;
    private boolean AppleCollided = false;
    
    //banana
    private Banana banana;
    private boolean BananaCollided = false;
    
    //Watermelon
    private Watermelon wtml;
    private boolean wtmlCollided = false;
    
    //durain
    private Durian ddurian;
    private boolean ddurianCollided = false;
    
    //flower
    private Flower flower;
    private boolean flowerCollided = false;
    
    //Grape
    private Grape grape;
    private boolean grapeCollided = false;
    
    private Bomb bomb;
    private boolean bombCollided = false;
    
    //Score 
    private int Score = 0;
    
    //time 
    private int TimeLeft;
    private Timer GameTimer;
    
    //next map button
    private JButton BNext = new JButton("Next Level");
    
    //Restart
    private boolean GameOver = false;
    private JButton BRestart = new JButton("RESTART");
    
    int level = 1;

    Homegame() {
        setLayout(null);
        
        //Start button 
        BStart.setBounds(700, 630, 170, 70);
        add(BStart);

        BStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                StartGame();
            }
        });
        
        //RESTART Button
        BRestart.setBounds(350, 400, 300, 70);
        BRestart.setVisible(false);
        add(BRestart);
        BRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReStartAgain();
            }
        });

        //Next map Button
        BNext.setBounds(350, 400, 300, 70);
        BNext.setVisible(false); // fisrt hide button
        add(BNext);
        BNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NextLevel(); //call function to next map
            }
        });
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(player != null){
                    if(e.getKeyCode() == KeyEvent.VK_LEFT){
                         player.Movement(-40);
                         
                    }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                         player.Movement(40);
                    }
                    
                    repaint();
                }
            }
        });
        setFocusable(true); 
    }
    
    //Show on JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        if(!GameStarted){
             g.drawImage(Homebg.getImage(), 0, 0, 1000, 800, this);
        }
        
        else if(GameStarted){
            g.drawImage(Gamebg.getImage(), 0, 0, 1000, 800, this);
            if (player != null) {
                player.draw(g); 
            }
            if (rock != null) {
                rock.draw(g);
            }
            if(Apple != null) {
                Apple.draw(g);
            }
            if (banana != null) {
                banana.draw(g);
            }
            if (wtml != null){
                wtml.draw(g);
            }
            if (ddurian != null){
                ddurian.draw(g);
            }
            if (flower != null) {
                flower.draw(g);
            }
            if (grape != null) {
                grape.draw(g);
            }
            if (bomb != null) {
                bomb.draw(g);
            }
            
        // ------------------- player & rock ----------------------
        if (player != null && rock != null) {
            if (!RockCollided && player.getBounds().intersects(rock.getBounds())) {
                player.deleteHp(1); 
                RockCollided = true; 
            } else if (RockCollided && !player.getBounds().intersects(rock.getBounds())) {
                RockCollided = false;
            }
            if (player.getHp() <= 0) {
                GameOver(); 
            }
        }

        // ------------------- player & Apple ---------------------
        if (player != null && Apple != null) {
            if (player.getBounds().intersects(Apple.getBounds())) {
                if (!AppleCollided) {
                    Score += 3;  
                    AppleCollided = true;  
                    Apple = null;  
                    if (Score >= 100) {
                        GameOver();
                        return;
                    }
                }
            } else {
                AppleCollided = false;  
            }
        }

        // ------------------- player & banana ---------------------
        if (player != null && banana != null) {
            if (player.getBounds().intersects(banana.getBounds())) {
                if (!BananaCollided) { 
                    Score += 5;  
                    BananaCollided = true; 
                    banana = null; 
                    if (Score >= 100) {
                        GameOver();
                        return;
                    }
                }
            } else {
                BananaCollided = false; 
            }
        }

        // ------------------- player & Watermelon ---------------------
        if(player != null && wtml != null){
            if (player.getBounds().intersects(wtml.getBounds())) {
                if (!wtmlCollided){
                    Score += 7;  
                    wtmlCollided = true; 
                    wtml = null; 
                    if (Score >= 100) {
                        GameOver();
                        return; 
                    }
                }
            } else {
                wtmlCollided = false; 
            }
        }   
        
        // ------------------- player & Durian ---------------------
        if(player != null && ddurian != null) {
            if (player.getBounds().intersects(ddurian.getBounds())) {
                if (!ddurianCollided) {
                    player.deleteHp(2);  
                    player.Blink(); 
                    ddurianCollided = true; 
                    ddurian = null; 
                    if (player.getHp() <= 0) {  
                        GameOver();
                        return;
                    }
                }
            } else {
                ddurianCollided = false;
            }
        }
        
        // ------------------- player & Flower ---------------------
        if (player != null && flower != null) {
            if (player.getBounds().intersects(flower.getBounds())) {
                if (!flowerCollided) {
                    flowerCollided = true;
                    flower = null;
                    if (player.getHp() < 3) {
                        player.setHp(player.getHp() + 1); 
                    }
                }
            } else {
                flowerCollided = false; 
            }
        }
        
        // ------------------- player & grape ---------------------
        if(player != null && grape != null){
            if (player.getBounds().intersects(grape.getBounds())) {
                if (!grapeCollided){
                    Score += 10;  
                    grapeCollided = true; 
                    grape = null; 
                    if (Score >= 100) {
                        GameOver();
                        return; 
                    }
                }
            } else {
                grapeCollided = false; 
            }
        }   
        
        if(player != null && bomb != null) {
            if (player.getBounds().intersects(bomb.getBounds())) {
                if (!bombCollided) {
                    player.Hp = 0;
                    bombCollided = true; 
                    bomb = null; 
                    GameOver();   
                }
            } else {
                bombCollided = false;
            }
        }

         //Show Score
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.PLAIN, 40));
            g.drawString("Score: " + Score, 10, 100);

        //show time
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Time: " + TimeLeft, 815, 50);

        //blood show
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.PLAIN, 40));
            g.drawString("HP: " + player.getHp(), 10, 50);
        }
    }

    private void CreateFallObjects() {
       if (rock == null) {
        rock = new Rock(300, 0, "D:/Red/Rock.png", 3);
        }

        // Create Apple if null
        if (Apple == null) {
            int appleXPosition = (int) (Math.random() * (getWidth() - 50)); 
            Apple = new Apple(appleXPosition, 0, "D:/Red/Apple.png", 4);
        }

        
        //create banana if null
        if (banana == null) {
            int bananaXPosition = (int) (Math.random() * (getWidth() - 50));
            banana = new Banana(bananaXPosition, 0,  "D:/Red/Banana.png", 4);
        }
        
        //Create watermelon if null
        if (wtml == null){
            int wtmlXPosition = (int) (Math.random() * (getWidth() - 50));
            wtml = new Watermelon(wtmlXPosition, 0, "D:/Red/Watermelon.png", 4);
        }
        
        //Create durian if null
        if (ddurian == null){
            int ddurianXPosition = (int) (Math.random() * (getWidth() - 50));
            ddurian = new Durian(ddurianXPosition, 0, "D:/Red/Durian.png", 4);
        }
        
        if (level == 2) {
            if (flower == null) {
                int flowerXPosition = (int) (Math.random() * (getWidth() - 50));
                flower = new Flower(flowerXPosition, 0, "D:/Red/Flower.png", 4);
            }
            if (grape == null) {
                int grapeXPosition = (int) (Math.random() * (getWidth() - 50));
                grape = new Grape(grapeXPosition, 0, "D:/Red/Grape.png", 4);
            }
            if (bomb == null) {
                int bombXPosition = (int) (Math.random() * (getWidth() - 50));
                bomb = new Bomb(bombXPosition, 0, "D:/Red/Bomb.png", 4);
            }
        }
    }

    //When Start Game
    private void StartGame(){
        GameStarted = true;
        remove(BStart);
        
        TimeLeft = 60;
        Score = 0;
        BNext.setVisible(false); //hide Next Button
        
        //hide Restart button
        BRestart.setVisible(false);
        
        //pictures
        if(level == 1){
            player = new Player(400, 570, "D:/Red/RedhoodL.png", "D:/Red/RedhoodR.png");
            rock = new Rock(300, 0, "D:/Red/Rock.png", 4);
            Apple = new Apple(600, 0,"D:/Red/Apple.png", 4);
            banana = new Banana(100, 0, "D:/Red/Banana.png", 4);
            wtml = new Watermelon(500, 0, "D:/Red/Watermelon.png", 4);
            ddurian = new Durian(200, 0, "D:/Red/Durian.png", 4);
            flower = null;
            grape = null;
            bomb = null;
            repaint();
        }
        else if(level == 2){
            player = new Player(400, 570, "D:/Red/RedhoodL.png", "D:/Red/RedhoodR.png");
            rock = new Rock(300, 0, "D:/Red/Rock.png", 6);
            Apple = new Apple(600, 0, "D:/Red/Apple.png", 6);
            banana = new Banana(100, 0, "D:/Red/Banana.png", 6);
            wtml = new Watermelon(500, 0, "D:/Red/Watermelon.png", 6);
            ddurian = new Durian(150, 0, "D:/Red/Durian.png", 6);
            flower = new Flower(700, 0, "D:/Red/Flower.png", 6); 
            grape = new Grape(200, 0, "D:/Red/Grape.png", 6);
            bomb = new Bomb(800, 0, "D:/Red/Bomb.png", 6);
            repaint();
        }

        //Game Time
        GameTimer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TimeLeft > 0) {
                    TimeLeft--; // delete time 
                } else {
                    GameTimer.stop(); //stop
                    GameOver(); // call function game over when time out
                }
                repaint(); // update 
            }
        });
        GameTimer.start(); //start tucth time 5555
      
        //Use Thread for things
        Thread GameTH = new Thread(new Runnable() {
            @Override
            public void run() {
                while (GameStarted) {
                    CreateFallObjects(); 
                    if (rock != null) {
                        rock.Rockfall(); 
                    }
                    if (Apple != null) {
                        Apple.fall(); 
                    }
                    if (banana != null) {
                        banana.fall(); 
                    }
                    if (wtml != null){
                        wtml.fall();
                    }
                    if (ddurian != null){
                        ddurian.fall();
                    }
                    if (flower != null) {
                        flower.Flowerfall();  
                    }
                    if (grape != null) {
                        grape.fall();  
                    }
                    if (bomb != null) {
                        bomb.Bombfall();  
                    }
                    if (player.isBlinking()){
                        player.Blink(); 
                    }
                    repaint(); 
                    try {
                        Thread.sleep(1000 / 60); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }        
        });
        //Strat Use Thread
        GameTH.start();
    }
    
    private boolean gameOver = false; 
    private void GameOver() {
        if (gameOver) {
            return;
        }
        gameOver = true; 
        GameStarted = false;
        GameTimer.stop(); 

        String message;
        int option;
        if (player.getHp() <= 0 && level == 1) { 
            message = "Player's health is 0! Game Over !";
            Object[] options = {"Restart"};
            option = JOptionPane.showOptionDialog(this, message, "GAME OVER!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                level = 1;
                ReStartAgain();
            }
        } else if (player.getHp() <= 0 && level == 2) { 
            message = "Player's health is 0! Game Over !";
            Object[] options = {"Restart"};
            option = JOptionPane.showOptionDialog(this, message, "GAME OVER!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                level = 2;
                ReStartAgain();
            }
        } else if (Score >= 100 && level == 1) { 
            message = "You pass !";
            Object[] options = {"Restart", "Next Level"};
            option = JOptionPane.showOptionDialog(this, message, "Congrats !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                level = 1;
                ReStartAgain();
            } else if (option == 1) {
                NextLevel();
            }
        } else if (Score >= 100 && level == 2) { 
            message = "You are winner !";
            Object[] options = {"Play agian in Level1", "Exit Game"};
            option = JOptionPane.showOptionDialog(this, message, "Congrats !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                level = 1;
                ReStartAgain(); 
            } else if (option == 1) {
                System.exit(0); 
            }
        }else if(TimeLeft <= 0 && level == 1) { 
            message = "Time out !";
            Object[] options = {"Restart"};
            option = JOptionPane.showOptionDialog(this, message, "GAME OVER !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                level = 1;
                ReStartAgain();
            }
        }else if(TimeLeft <= 0 && level == 2) { 
            message = "Time out !";
            Object[] options = {"Restart"};
            option = JOptionPane.showOptionDialog(this, message, "GAME OVER !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                level = 2;
                ReStartAgain();
            }
        }
    }

    private void ReStartAgain() {
        Score = 0;
        player.Hp = 3;
        gameOver = false;
        TimeLeft = 60;
        StartGame();
    }

    private void NextLevel() {
        player.setHp(3);
        Score = 0; 
        TimeLeft = 60; 
        gameOver = false;  
        level = 2;  
        flower = null;
        grape = null;
        bomb = null;
        StartGame();   
    }
}

public class RedHood {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dodge Game Little Red Riding Hood");
        Homegame home = new Homegame();

        frame.add(home);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
