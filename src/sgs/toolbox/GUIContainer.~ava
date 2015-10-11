package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public abstract class GUIContainer extends GUIObject implements PConstants {
  
  protected ArrayList<GUIObject> objects;
  private int oldWidth, oldHeight;
  protected Sizer s1;
  protected OnDrawListener onDrawListener;
  
  public void setOnDrawListener(OnDrawListener l) {
    onDrawListener = l;
  }
  
  public GUIContainer(PApplet p, int x, int y, int w, int h) {
    super(p, x, y, w, h);
    objects = new ArrayList<GUIObject>();
    oldWidth = w;
    oldHeight = h;
  }
  
  public GUIContainer(PApplet p, int x, int y) {
    this(p, x, y, 0, 0);
  }
  
  public void draw() {
    if (onDrawListener != null) {                 
      onDrawListener.draw(xPos, yPos, width, height);
    }
    parent.translate(xPos, yPos);
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    } // end of for
    parent.translate(-xPos, -yPos);
  }
  
  public void addSizer(Sizer s, boolean setStart, boolean setStop) {
    s1 = s;
    final int deltaX = s1.getX() - xPos;
    final int deltaY = s1.getY() - yPos;
    final int deltaW = s1.getX() - (xPos + width);
    final int deltaH = s1.getY() - (yPos + height);
    final boolean horizontal = s1.isHorizontal();
    s1.addOnMoveListener(new Sizer.OnMoveListener() {
      public void onStart() {
        onFixSize();
      }
      public void onMove(int pos) {
        if (setStart) {
          if (horizontal) {
            yPos = pos - deltaY;
          } else {
            xPos = pos - deltaX;
          } // end of if-else
          if (!setStop) {
            if (horizontal) {
              height = oldY2 - yPos;
            } else {
              width = oldX2 - xPos;
            } // end of if-else
          } // end of if-else
        } else if (setStop) {
          if (horizontal) {
            height = pos - yPos - deltaH;
          } else { 
            width = pos + xPos - deltaW;
          }
        }
        onChildResize();
      }
      public void onStop() {
        onFixSize();
      }
    });
  }
  
  public boolean mouseEvent(MouseEvent e) { 
    MouseEvent eNew = new MouseEvent(e.getNative(), e.getMillis(), e.getAction(), e.getModifiers(), e.getX() - xPos, e.getY() - yPos, e.getButton(), e.getCount());
    for (int i = objects.size() - 1; i >= 0; i--) {
      if (objects.get(i).mouseEvent(eNew)) {
        return true;
      }
    }                
    return false;
  }
  
  public void keyEvent(KeyEvent e) {
    for (int i = objects.size() - 1; i >= 0; i--) {
      objects.get(i).keyEvent(e);
    }
  }
  
  public void onFixSize() {
    super.onFixSize();
    oldWidth = oldX2 - oldX;
    oldHeight = oldY2 - oldY;
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).onFixSize();
    } // end of for
  }
  
  public void onResize(float xFactor, float yFactor) {
    super.onResize(xFactor, yFactor);
    onChildResize();
  }
  
  public void onChildResize() {
    float xFactor = (float)width / oldWidth;
    float yFactor = (float)height / oldHeight;
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).onResize(xFactor, yFactor);
    } // end of for
  }
  
  
  
}
