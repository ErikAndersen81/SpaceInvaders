/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author erik
 */
public class Life extends JPanel implements ActionListener {
    
    private Image image;
    private int points;
    private int width;
    
    public Life(int p){
        initLife(p);
    }

    private void initLife(int p) {
        ImageIcon ii = new ImageIcon("heart.png");
        image = ii.getImage();
        width = ii.getIconWidth();
        this.setSize(p*width, ii.getIconHeight());
        this.setBackground(new Color(0,0,0,1));
        setPoints(p);
    }

    public void setPoints(int p) {
        points=p;
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<points;i++){
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, 0+(i)*width, 0 ,this);
        }
    }
}
