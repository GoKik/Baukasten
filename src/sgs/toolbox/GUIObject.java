package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public abstract class GUIObject implements PConstants {
  protected int xPos, yPos, oldX, oldY, width, height, oldX2, oldY2;
  protected PApplet parent;
  protected boolean resizable[] = {true, true, true, true};
  protected boolean keepPropotions = false;
  protected float propotion;
  
  
  public GUIObject(PApplet p, int x, int y, int w, int h) {
    parent = p;
    xPos = oldX = x;
    yPos = oldY = y;
    oldX2 = xPos + w;
    oldY2 = yPos + h;
    width = w;
    height = h;
    propotion = (float)w / h;
  }
  
  public GUIObject(PApplet p, int x, int y) {
    parent = p;
    xPos = oldX = x;
    yPos = oldY = y;
  }
  
  public abstract void draw();
  public abstract boolean mouseEvent(MouseEvent e);
  public abstract void keyEvent(KeyEvent e);
  
  public void onResize(float xFactor, float yFactor) {
    if (resizable[0]) {
      xPos = (int)(oldX * xFactor);
    } // end of if     
    if (resizable[1]) {
      yPos = (int)(oldY * yFactor);
    } // end of if
    if (resizable[2]) {
      width = (int)(oldX2 * xFactor) - xPos;
      if (width < 0) {
        width = 0;
      } // end of if
    } // end of if
    if (resizable[3]) {
      if (keepPropotions) {
        if (resizable[2]) {
          height = (int)(width * (1/propotion));
          if (yPos + height > parent.height) {
            height = parent.height - yPos; 
            width = (int)(height * propotion);   
          } // end of if
        } else {
          height = (int)(oldY2 * yFactor) - yPos;
          width = (int)(height * propotion);   
          if (xPos + width > parent.width) {
            width = parent.width - xPos; 
            height = (int)(width * (1/propotion));  
          } // end of if
        } // end of if-else
      } else {
        height = (int)(oldY2 * yFactor) - yPos;
      } // end of if-else
      if (height < 0) {
        height = 0;
      } // end of if
    } // end of if
  }
  
  public void onFixSize() {
    oldX = xPos;
    oldX2 = xPos + width;
    oldY = yPos;
    oldY2 = yPos + height;
  }
  
  public void setResizable(boolean r) {
    setResizable(r, r, r, r, r);
  }
  
  public void setResizable(boolean r, boolean p) {
    setResizable(r, r, r, r, p);
  }
  
  public void setResizable(boolean r1, boolean r2, boolean p) {
    setResizable(r1, r2, r1, r2, p);
  }
  
  public void setResizable(boolean x, boolean y, boolean w, boolean h) {
    setResizable(x, y, w, h, false);
  }
  
  public void setResizable(boolean x, boolean y, boolean w, boolean h, boolean p) {
    resizable[0] = x;
    resizable[1] = y;
    resizable[2] = w;
    resizable[3] = h;
    keepPropotions = p;
  }
  
  public int getXPos() {
    return xPos;
  }
  
  public int getYPos() {
    return yPos;
  }
}