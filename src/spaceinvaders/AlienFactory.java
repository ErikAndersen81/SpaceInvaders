/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;

/**
 *
 * @author erik
 */
public class AlienFactory{

    LinkedList<Entity> aliens;
    static final int DELAY=300;
    Timer timer;
    
public AlienFactory(){
    initAlienFactory();
}    

    private void initAlienFactory() {
        aliens = new LinkedList<Entity>();
    }
    
    public void breedAliens(int number, int dy){
        for(int i=0; i<number;i++){
            int type = (int) (Math.random() * 8);
            String filename = new String("alien"+ type + ".png");
            Entity alien = new Alien(-i * dy,filename);
            aliens.add(alien);
        }
    }
    
    public LinkedList<Entity> getAliens(){
        return aliens;
    }
}
