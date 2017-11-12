/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author erik
 */
public class SpaceShip implements ActionListener, Entity {
    private int dx;
    private int dy;
    private int x;
    private int y;
    private int life;
    private int height;
    private int width;
    private int score;
    private LinkedList<Entity> bullets;
    private Image image;
    private Timer timer;
    private final int DELAY= 100;
    private boolean firing;
    
    
    public SpaceShip(){
    initSpaceShip();
    }

    private void initSpaceShip() {
        ImageIcon ii = new ImageIcon("spaceship.png");
        image = ii.getImage();
        height = ii.getIconHeight();
        width = ii.getIconWidth();
        bullets = new LinkedList<>();
        x= GameWindow.WIN_WIDTH/2;
        y = height;
        life = 3;
    }
    
    public void move(){
        if (x < 1){
            x=0;
        }
        if (x>GameWindow.WIN_WIDTH - width){
            x=GameWindow.WIN_WIDTH - width;
        }
        if (y<0){
            y=0;
        }
        if (y>GameWindow.WIN_HEIGHT-height){
            y=GameWindow.WIN_HEIGHT-height;
        }
        y+=dy;
        x+=dx;
    }
    
    public void setScore(int s){
        score=s;
    }
    
    public int getScore(){
        return score;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Image getImage(){
        return image;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_LEFT){
            dx = -1;
        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 1;
        }
        if (key == KeyEvent.VK_UP){
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN){
            dy = 1;
        }
        if (key==KeyEvent.VK_SPACE){
            firing = true;
        }
        
    }

    public LinkedList<Entity> getBullets(){
        return bullets;
    }
        
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT) {
            dx = (dx>0) ? 1 : 0;
        }
        
        if(key == KeyEvent.VK_RIGHT){
            dx = (dx<0) ? -1: 0;
        }
        
        if((key == KeyEvent.VK_DOWN) || (key == KeyEvent.VK_UP)){
            dy = 0;
        }
        if (key==KeyEvent.VK_SPACE){
            firing = false;
        }
    }

    
    public void actionPerformed(ActionEvent ae) {
        if ((firing)&& (ae.getWhen()%5==0)){
            bullets.add(new Bullet(x+width/2,y));
        }
    }

    @Override
    public int getWidth() {
        return (int) (width*0.6);
    }

    @Override
    public int getHeight() {
        return (int) (height*0.8);
    }

    @Override
    public boolean outOfBounds() {
        return life<=0;
    }

    @Override
    public void hit() {
        life --;
    }
    
    public int getLife(){
        return life;
    }
}
