/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author erik
 */
public class Alien implements Entity{
    private int x,y;
    private int dx,dy;
    private int width, height;
    private int step,stepCount;
    private Image image;
    private boolean visible;
    
    public Alien(String filename){
        initAlienShip(filename);
    }
    
    public Alien(int yi,String filename){
        y=yi;
        initAlienShip(filename);
    }

    private void initAlienShip(String filename) {
        ImageIcon ii = new ImageIcon(filename);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        y = (y==0) ? y=-height : y;
        x = (int) (Math.random() * GameWindow.WIN_WIDTH*0.3 + GameWindow.WIN_WIDTH*0.1);
        dx = (Math.random()>0.5) ? 1 : -1;
        dy = 1;
        step = (int) (Math.random() * GameWindow.WIN_WIDTH*0.5 + GameWindow.WIN_WIDTH*0.1);
        stepCount=0;
        image = ii.getImage();
        visible = true;
    }
    
    @Override
    public void move(){
        stepCount ++;
        if (stepCount==step){
            stepCount = 0;
            dx = (dx>0) ? -1:1;
        }
        x+=dx;
        y+=dy;
        boundaryCheck();
    }

    private void boundaryCheck() {
          if (x < 1){
            x=0;
            dx = 1;
        }
        if (x>GameWindow.WIN_WIDTH - width){
            x=GameWindow.WIN_WIDTH- width;
            dx = -1;
        }
    }
    
    
    @Override
    public int getX(){
        return x;
    }
    
    @Override
    public int getY(){
        return y;
    }

    @Override
    public Image getImage(){
        return image;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean outOfBounds(){
        if (visible){
            return (y>GameWindow.WIN_HEIGHT);
        }
        else{return true;}
    }
    
    @Override
    public void hit(){
        visible = false;
    }
}
