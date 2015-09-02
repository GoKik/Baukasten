//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Baukasten implements PConstants {
  PApplet parent;
  
  public final static int JAVA_MODE = 1;
  public final static int ANDROID_MODE = 2;
  private int mode;
  private ArrayList<GUIObjekt> objects = new ArrayList<GUIObjekt>();
  
  public Baukasten(PApplet parent, int m) {
    this.parent = parent;
    if (m == JAVA_MODE || m == ANDROID_MODE) {
      mode = m;
    }
    parent.registerMethod("pre", this);
    parent.registerMethod("draw", this);
    parent.registerMethod("post", this);
    parent.registerMethod("mouseEvent", this);
    parent.registerMethod("keyEvent", this);
    parent.registerMethod("pause", this);
    parent.registerMethod("resume", this);
    parent.registerMethod("dispose", this);
  }
  
  public void fuegeEin(GUIObjekt o) {
    objects.add(o);
  }
  
  public void loescheAlles() {
    objects.clear();
  }
  
  public void setzeInHintergrund(GUIObjekt o) {
    int i = objects.indexOf(o);
    if (i != -1) {
      objects.remove(i);
      objects.add(0, o);
    } // end of if
  }
  
  public void setzeInVordergrund(GUIObjekt o) {
    int i = objects.indexOf(o);
    if (i != -1) {
      objects.remove(i);
      objects.add(o);
    } // end of if
    
  }
  
  public void pre() {
    
  }
  
  public void draw() {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }
  }
  
  public void post() {
    
  }
  
  public void mouseEvent(MouseEvent e) {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseEvent(e);
    }
    if (mode == ANDROID_MODE && e.getAction() == MouseEvent.RELEASE) {
      parent.mouseX = -1;
      parent.mouseY = -1;
    } // end of if
  }  
  
  public void keyEvent(KeyEvent e) {
    
  }
  
  public void pause() {
    
  }
  
  public void resume() {
    
  }
  
  public void dispose() {
    // Anything in here will be called automatically when 
    // the parent sketch shuts down. For instance, this might
    // shut down a thread used by this library.
  }
}