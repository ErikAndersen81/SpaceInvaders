/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Image;

/**
 *
 * @author erik
 */
public interface Entity{
int getWidth();
int getHeight();
int getX();
int getY();
Image getImage();
boolean outOfBounds();
void hit();
void move();
}
