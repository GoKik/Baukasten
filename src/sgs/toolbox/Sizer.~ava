package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Sizer extends GUIObject implements PConstants {
  
  private Property horizontal, minX, minY, maxX, maxY, col;
  private boolean hovered, active;
  private ArrayList<OnMoveListener> listeners;
  
  public interface OnMoveListener {
    public void onStart();
    public void onMove(int pos);
    public void onStop();
  }
  
  public void addOnMoveListener(OnMoveListener l) {
    listeners.add(l);
  }
  
  public Sizer(PApplet p, int x, int y, int w, boolean h) {
    super(p, x, y, h?w:1, h?1:w);
    horizontal = registerProperty("Horizontal", h);
    listeners = new ArrayList<OnMoveListener>();
    col = registerProperty("Color", parent.color(0));
    minX = registerProperty("Minimal X", x - 50);
    minY = registerProperty("Minimal Y", y - 50);
    maxX = registerProperty("Maximal X", x + 50);
    maxY = registerProperty("Maximal Y", y + 50);
  }
  
  public boolean isHorizontal() {
    return (boolean)horizontal.value;
  }
  
  public void setMinX(int x) {
    minX.value = x;
  }
  
  public void setMinY(int y) {
    minY.value = y;
  }
  
  public void setMaxX(int x) {
    maxX.value = x;
  }
  
  public void setMaxY(int y) {
    maxY.value = y;
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void draw() {
    parent.stroke((int)col.value);
    parent.strokeWeight(1);
    if ((boolean)horizontal.value) {
      parent.line(xPos, yPos, xPos + width, yPos);
    } else {                          
      parent.line(xPos, yPos, xPos, yPos + height);
    } // end of if-else
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered) {
        parent.cursor(MOVE);
        return true;
      } else {
        parent.cursor(ARROW);
        return false;
      } // end of if-else
    } else if (hovered && e.getAction() == MouseEvent.PRESS && !active) {
      active = true;
      if (listeners.size() > 0) {
        for (int i = 0; i < listeners.size(); i++) {
          listeners.get(i).onStart();
        } // end of for
      } // end of if
      return true;
    } else if (hovered && e.getAction() == MouseEvent.DRAG) {
      dragged(e.getX(), e.getY());
      return true;
    } else if (e.getAction() == MouseEvent.RELEASE && active) {
      if (listeners.size() > 0) {
        for (int i = 0; i < listeners.size(); i++) {
          listeners.get(i).onStop();
        } // end of for
      } // end of if
      active = false;
      return true;
    } // end of if-else
    return false;
  }
  
  private void mouseOver(int x, int y) {
    if ((boolean)horizontal.value) {
      hovered = (x > xPos && x < xPos + width && y > yPos - 5 && y < yPos + 5);
    } else {
      hovered = (x > xPos - 5 && x < xPos + 5 && y > yPos && y < yPos + height);
    } // end of if-else
  }
  
  private void dragged(int x, int y) {
    if ((boolean)horizontal.value) {
      if (y >= (int)minY.value && y <= (int)maxY.value) {
        yPos = y;
        if (listeners.size() > 0) {
          for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onMove(yPos);
          } // end of for
        } // end of if
      } // end of if
    } else {
      if (x >= (int)minX.value && x <= (int)maxX.value) {
        xPos = x;               
        if (listeners.size() > 0) {
          for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onMove(xPos);
          } // end of for
        } // end of if
      }
    } // end of if-else
  }
  
  public void keyEvent(KeyEvent e) {
    
  }
  
  
}