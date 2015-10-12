package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class CheckboxGroup extends GUIContainer implements PConstants {
  
  private int checkedBox = 0;
  
  public CheckboxGroup(PApplet p) {
    super(p, 0, 0);
  }
  
  public void add(Checkbox b) {
    objects.add(b);
    if (objects.size() == 1) {
      ((Checkbox)objects.get(0)).check();
    } // end of if
  }
  
  public void onResize(float xFactor, float yFactor) {
    for (int i = 0; i < objects.size() ; i++ ) {
      objects.get(i).onResize(xFactor, yFactor);
    } // end of for
  }
  
  public void setResizable(boolean x, boolean y, boolean w, boolean h, boolean p) {
    for (int i = 0; i < objects.size() ; i++ ) {
      objects.get(i).setResizable(x, y, w, h, p);
    } // end of for
  }
  
  public boolean mouseEvent(MouseEvent e) {
    int b = -1;
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).mouseEvent(e)) {
        if (i != checkedBox && ((Checkbox)objects.get(i)).isChecked()) {
          b = i;
        } // end of if
        if (b != -1) {
          ((Checkbox)objects.get(checkedBox)).uncheck();
          ((Checkbox)objects.get(b)).check();
          checkedBox = b;
        }
        return true;
      }
    } // end of for
    return false;
  }  
  
  
}