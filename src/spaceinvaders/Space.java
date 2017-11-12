/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author erik
 */
public class Space extends JPanel implements ActionListener{
    
    private SpaceShip player;
    private AlienFactory alienFactory;
    private Setting bg;
    public Timer timer;
    private final int DELAY = 5;
    private LinkedList<Entity>  bullets;
    private LinkedList<Entity>  aliens;
    private JLabel game, score;
    private Life life;
    private JPanel info;
    
    public Space(){
        initSpace();
    }
    
    private void initSpace() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        
        player = new SpaceShip();
        alienFactory = new AlienFactory();
        alienFactory.breedAliens(100, 400);
        aliens = alienFactory.getAliens();
        bullets = player.getBullets();
        bg = new Setting();
        timer = new Timer(DELAY, this);
        timer.addActionListener(player);
        
        game = new JLabel("GAME OVER");
        game.setFont(new Font(Font.SERIF,GameWindow.WIN_WIDTH/15,GameWindow.WIN_HEIGHT/15));
        game.setHorizontalAlignment(JLabel.CENTER);
        game.setVisible(false);
        game.setForeground(Color.orange);
        
        
        score = new JLabel("SCORE: 0");
        score.setForeground(Color.magenta);
        
        life = new Life(player.getLife());
        timer.addActionListener(life);
        
        setLayout(new BorderLayout());
        add(score,BorderLayout.NORTH);
        add(game,BorderLayout.SOUTH);
        add(life,BorderLayout.CENTER);
        
        timer.start();
    }
    
    static boolean collides(Entity ob1,Entity ob2){
        int x11 = ob1.getX();
        int x12 = x11 + ob1.getWidth();
        int y11 = ob1.getY();
        int y12 = y11 + ob1.getHeight();
        
        int x21 = ob2.getX();
        int x22 = x21 + ob2.getWidth();
        int y21 = ob2.getY();
        int y22 = y21 + ob2.getHeight();
        
        if(((x11<=x21)&&(x21<=x12)) || ((x11<=x22)&&(x22<=x12))){
            if (((y11<=y21)&&(y21<=y12)) || ((y11<=y22)&&(y22<=y12))){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        player.move();
        bullets=player.getBullets();
        for (Entity alien: aliens){
            if (Space.collides(alien, player)){player.hit();life.setPoints(player.getLife()); alien.hit();}
            for (Entity bullet:bullets){
                if (Space.collides(bullet,alien)){
                    player.setScore(player.getScore()+1000);
                    score.setText("Score: " + player.getScore());
                    bullet.hit();
                    alien.hit();
                }
            }
        }
        
        for (Entity alien : aliens){alien.move();}
        for (Entity bullet: bullets){bullet.move();}
        
        Iterator<Entity> bulletIt = bullets.iterator();
        while(bulletIt.hasNext()){
            Entity e = bulletIt.next();
            if (e.outOfBounds()){
                bulletIt.remove();}
        }
        
        Iterator<Entity> alienIt = aliens.iterator();
        while(alienIt.hasNext()){
            Entity e = alienIt.next();
            if (e.outOfBounds()){
                alienIt.remove();
            }
        }
        bg.move();
        
        repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (aliens.isEmpty()){game.setText("You Win!");gameOver(g);}
        if (player.outOfBounds()){
            gameOver(g);
        }
        else{doDrawing(g);}
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bg.getImage(), 0, bg.getY1(),this);
        g2.drawImage(bg.getImage(), 0, bg.getY2(),this);
        for (Entity entity:bullets){
            g2.drawImage(entity.getImage(), entity.getX(), entity.getY(),this);
        }
        for(Entity entity:aliens){
            g2.drawImage(entity.getImage(), entity.getX(), entity.getY(),this);
        }
        g2.drawImage(player.getImage() , player.getX(), player.getY(), this);
    }

    private void gameOver(Graphics g) {
        game.setVisible(true);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bg.getImage(), 0, bg.getY1(),this);
        g2.drawImage(bg.getImage(), 0, bg.getY2(),this);
    }
    
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyReleased(KeyEvent e ){
            player.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
    }
}
