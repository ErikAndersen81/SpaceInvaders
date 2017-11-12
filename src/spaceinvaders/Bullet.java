/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author erik
 */
public class Bullet implements Entity{
private int x, y;
private int dy;
private int width, height;
private Image image;
private boolean visible;

    public Bullet(int xi, int yi){
        initBullet(xi,yi);
    }

    private void initBullet(int xi,int yi) {
        ImageIcon ii = new ImageIcon("bullet.png");
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        x=xi-(width/2);
        y=yi+height;
        visible = true;
        image = ii.getImage();
    }
    
    @Override
    public void move(){
        y-=3;
    }
    
    @Override
    public boolean outOfBounds(){
        if (visible){
            return (y+height < 0);
        }
        else{return true;}
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
    public void hit(){
    boolean visible = false;
    }
}
