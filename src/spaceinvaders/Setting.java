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
public class Setting {
private Image image;
private int y1,y2;
private int height;

public Setting(){
    initSetting();
}

    private void initSetting() {
        ImageIcon ii = new ImageIcon("space.png");
        height = ii.getIconHeight();
        image = ii.getImage();
        y1 = 0;
        y2 = height;
    }
    
    public void move(){
        y1 += 1;
        y2 += 1;
        if (y1 > height){
            y1= 0-height;
        }
        if (y2 > height){
            y2= 0 - height;
        }
    }
    
    public Image getImage(){
        return image;
    }
    
    public int getY1(){
        return y1;
    }
    
    public int getY2(){
        return y2;
    }
    
}
