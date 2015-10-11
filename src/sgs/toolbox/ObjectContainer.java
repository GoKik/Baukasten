package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class ObjectContainer extends GUIContainer {
  
  private Property bgCol;
  
  public ObjectContainer(PApplet p, int x, int y, int w, int h) {
    super(p, x, y, w, h);
    bgCol = registerProperty("Background Color", -1);
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
  }
  
  public void draw() {
    parent.noStroke();
    if ((int)bgCol.value >= 0) {
      parent.fill((int)bgCol.value);
      parent.rect(xPos, yPos, width, height);
    } // end of if
    
    super.draw();
  }
  
  public void add(GUIObject o) {
    objects.add(o);
  }
  
  public void remove(GUIObject o) {
    objects.remove(o);
  }
  
  public int getSize() {
    return objects.size();
  }
  
  
}