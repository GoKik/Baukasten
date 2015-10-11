package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Toolbox implements PConstants {
  PApplet parent;
  
  public final static int JAVA_MODE = 1;
  public final static int ANDROID_MODE = 2;
  private int mode, oldWidth, oldHeight, initWidth, initHeight;
  private int bgCol;
  private float propotion;
  private long resizeMillis = 0;
  private ArrayList<GUIObject> objects = new ArrayList<GUIObject>();
  
  public Toolbox(PApplet parent, int m, int w, int h) {
    this(parent, m, w, h, false);
  }
  
  public Toolbox(PApplet parent, int m, int w, int h, boolean r) {
    this.parent = parent;
    if (m == JAVA_MODE || m == ANDROID_MODE) {
      mode = m;
    }
    parent.registerMethod("pre", this);
    parent.registerMethod("draw", this);
    parent.registerMethod("mouseEvent", this);
    parent.registerMethod("keyEvent", this);
    parent.registerMethod("dispose", this);
    parent.frame.setResizable(r);
    parent.frame.setSize(w, h);
    oldWidth = initWidth = parent.width;
    oldHeight = initHeight = parent.height;
    propotion = (float)oldHeight / oldWidth; 
    bgCol = parent.color(255, 255, 255);
  }
  
  public Toolbox(PApplet parent, int m, boolean r) {
    this.parent = parent;
    if (m == JAVA_MODE || m == ANDROID_MODE) {
      mode = m;
    }
    parent.registerMethod("pre", this);
    parent.registerMethod("draw", this);
    parent.registerMethod("mouseEvent", this);
    parent.registerMethod("keyEvent", this);
    parent.registerMethod("dispose", this);
    parent.frame.setResizable(r);
    oldWidth = initWidth = parent.width;
    oldHeight = initHeight = parent.height;
    propotion = (float)oldHeight / oldWidth;  
    bgCol = parent.color(255, 255, 255);
  }
  
  public void setBackgroundColor(int c) {
    bgCol = c;
  }
  
  public void add(GUIObject o) {
    objects.add(o);
  }
  
  public void clear() {
    objects.clear();
  }
  
  public void sendToFront(GUIObject o) {
    int i = objects.indexOf(o);
    if (i != -1) {
      objects.remove(i);
      objects.add(0, o);
    } // end of if
  }
  
  public void sendToBack(GUIObject o) {
    int i = objects.indexOf(o);
    if (i != -1) {
      objects.remove(i);
      objects.add(o);
    } // end of if
    
  }
  
  public void draw() {
    parent.background(bgCol);
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }
    parent.draw();
  }
  
  public void pre() {
    int pW = parent.width;
    int pH = parent.height;
    if (pW != oldWidth || pH != oldHeight) {
      resizeMillis = parent.millis();
    }
    if (resizeMillis + 1000 > parent.millis()) {
      float xFactor = (float)parent.width / initWidth;
      float yFactor = (float)parent.height / initHeight;
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).onResize(xFactor, yFactor);
      } // end of for   
    } else if (initWidth != oldWidth) {
      initWidth = oldWidth;
      initHeight = oldHeight;
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).onFixSize();
      } // end of for  
    } // end of if-else
    
    oldWidth = pW;
    oldHeight = pH;
  }
  
  public void mouseEvent(MouseEvent e) {  
    for (int i = objects.size() - 1; i >= 0; i--) {
      if (objects.get(i).mouseEvent(e)) {
        break;
      }
    }
    if (mode == ANDROID_MODE && e.getAction() == MouseEvent.RELEASE) {
      parent.mouseX = -1;
      parent.mouseY = -1;
    } // end of if
  }  
  
  public void keyEvent(KeyEvent e) {
    for (int i = objects.size() - 1; i >= 0; i--) {
      objects.get(i).keyEvent(e);
    }
  }
  
  public void dispose() {
    // Anything in here will be called automatically when 
    // the parent sketch shuts down. For instance, this might
    // shut down a thread used by this library.
  }
}