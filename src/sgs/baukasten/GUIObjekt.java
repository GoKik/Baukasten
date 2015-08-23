package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public abstract class GUIObjekt implements PConstants {
  protected int xPos, yPos;
  protected PApplet parent;
  
  public GUIObjekt(PApplet p, int x, int y) {
    parent = p;
    xPos = x;
    yPos = y;
  }
  
  public abstract void draw();
  public abstract void mouseEvent(MouseEvent e);
  public abstract void keyEvent(KeyEvent e);
  
  public int getXPos() {
    return xPos;
  }
  
  public int getYPos() {
    return yPos;
  }
}