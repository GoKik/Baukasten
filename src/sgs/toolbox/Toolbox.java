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
  private int mode, initWidth, initHeight, oldWidth, oldHeight;
  private float propotion;
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
    initWidth = parent.width;
    initHeight = parent.height;
    propotion = (float)initHeight / initWidth; 
  }
  
  public void add(GUIObject o) {
    objects.add(o);
  }
  
  public void clear() {
    objects.clear();
  }
  
  public void setToBack(GUIObject o) {
    int i = objects.indexOf(o);
    if (i != -1) {
      objects.remove(i);
      objects.add(0, o);
    } // end of if
  }
  
  public void setToFront(GUIObject o) {
    int i = objects.indexOf(o);
    if (i != -1) {
      objects.remove(i);
      objects.add(o);
    } // end of if
    
  }
  
  public void draw() {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }
  }
  
  public void pre() {
    if (parent.width != oldWidth || parent.height != oldHeight) {
      float xFactor = (float)parent.width / initWidth;
      float yFactor = (float)parent.height / initHeight;
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).onResize(xFactor, yFactor);
      } // end of for     
      oldWidth = parent.width;
      oldHeight = parent.height;
    } // end of if 
  }
  
  public void mouseEvent(MouseEvent e) {  
    for (int i = 0; i < objects.size(); i++) {
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
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).keyEvent(e);
    }
  }
  
  public void dispose() {
    // Anything in here will be called automatically when 
    // the parent sketch shuts down. For instance, this might
    // shut down a thread used by this library.
  }
}