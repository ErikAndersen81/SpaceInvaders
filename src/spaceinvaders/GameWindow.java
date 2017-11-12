/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author erik
 */
public class GameWindow extends JFrame {
    
    public static final int WIN_WIDTH=800;
    public static final int WIN_HEIGHT=600;
    
    public GameWindow() {
        
        initGameWindow();
    };

    private void initGameWindow() {

        this.add(new Space());
        
        this.setSize(GameWindow.WIN_WIDTH,GameWindow.WIN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SpaceInvaders");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
    }
    
    
    
}