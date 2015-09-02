//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Slider extends GUIObjekt implements PConstants {
  
  private int breite, hoehe, minValue, maxValue, step;
  
  public Slider(PApplet p, int x, int y, int b, int h) {
    super(p, x, y);
    breite = b;
    hoehe = h;
    minValue = 0;
    maxValue = 100;
    step = 1;
  }
  
  public void draw() {
    parent.rect(xPos, yPos, breite, hoehe);  
  }
  
  public void mouseEvent(MouseEvent e) {
    
  }
  
  public void keyEvent(KeyEvent e) {
    
  }
  
}