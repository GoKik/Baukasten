package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public abstract class GUIObject implements PConstants {
  protected int xPos, yPos;
  protected PApplet parent;
  
  public GUIObject(PApplet p, int x, int y) {
    parent = p;
    xPos = x;
    yPos = y;
  }
  
  public abstract void draw();
  public abstract boolean mouseEvent(MouseEvent e);
  public abstract void keyEvent(KeyEvent e);
  
  public int getXPos() {
    return xPos;
  }
  
  public int getYPos() {
    return yPos;
  }
}