package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Checkbox extends GUIObject implements PConstants {
  
  public final static int CHECKBOX = 1;
  public final static int CHECKBOX_ROUND = 2;
  public final static int TOGGLE_BUTTON = 3;
  
  private Property name, col, bgCol, disCol, textSize, style, checked;
  private boolean hovered, pressed, disabled;
  
  public Checkbox(PApplet p, String n, int x, int y, boolean c) {
    super(p, x, y, 30, 30);
    name = registerProperty("Name", n);
    col = registerProperty("Color", parent.color(0, 0, 0));
    bgCol = registerProperty("Background Color", parent.color(255, 255, 255));
    disCol = registerProperty("Disabled Color", parent.color(30, 30, 30, 150));
    style = registerProperty("Style", CHECKBOX);
    textSize = registerProperty("Text Size", 15);
    checked = registerProperty("Checked", c);
    addOnPropertyChangedListener(new OnPropertyChangedListener() {
      public void onPropertyChanged(String tag, Object value) {
        if (tag.equals("Style")) {
          setStyle((int)value);
        } // end of if
      }
    });
  }
  
  public void setName(String n) {
    name.value = n;
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
  }
  
  public void setzeTextSize(int t) {
    textSize.value = t;
  }
  
  public void setStyle(int i) {
    if (i == CHECKBOX || i == CHECKBOX_ROUND) {
      style.value = i;
      height = width;
    } else if (i == TOGGLE_BUTTON) {
      style.value = i;
      width = height * 2;
    }
  }
  
  public void draw() {
    parent.textSize((int)textSize.value);
    parent.strokeWeight(2);
    
    if (pressed) {
      if ((boolean)checked.value) {
        parent.fill((int)bgCol.value);
        parent.stroke((int)col.value);
        drawBody(xPos + 2, yPos + 2, width - 4, height - 4);
      } else {
        parent.fill((int)bgCol.value);
        parent.stroke((int)col.value);
        drawBody(xPos + 2, yPos + 2, width - 4, height - 4);
        parent.fill((int)col.value);
        parent.noStroke();
        drawBody(xPos + 7, yPos + 7, width - 13, height - 13);
      }
    } else if (hovered) {
      parent.fill((int)bgCol.value);
      parent.stroke((int)col.value);
      drawBody(xPos, yPos, width, height);
      parent.fill((int)col.value, 150);
      parent.noStroke();
      drawBody(xPos + 5, yPos + 5, width - 9, height - 9);
    } else if ((boolean)checked.value) {
      parent.fill((int)bgCol.value);
      parent.stroke((int)col.value);
      drawBody(xPos, yPos, width, height);
      parent.fill((int)col.value);
      parent.noStroke();
      drawBody(xPos + 5, yPos + 5, width - 9, height - 9);
    } else {
      parent.fill((int)bgCol.value);
      parent.stroke((int)col.value);
      drawBody(xPos, yPos, width, height);
    }
    
    if (disabled) {
      parent.fill((int)disCol.value);
      parent.stroke((int)disCol.value);
      drawBody(xPos, yPos, width, height);
      if ((int)style.value == TOGGLE_BUTTON) {
        parent.fill(255, 255, 255);
        parent.textAlign(CENTER, CENTER);
        parent.text("DISABLED", xPos + (width / 2), yPos + (height / 2));
      }
    }
  }
  
  private void drawBody(int x, int y, int b, int h) {
    if ((int)style.value == CHECKBOX) { 
      parent.textAlign(LEFT, CENTER);
      parent.rect(x, y, b, h);
      parent.fill((int)col.value);
      parent.text((String)name.value, xPos + 35, yPos + (height / 2));
    } else if ((int)style.value == CHECKBOX_ROUND) {
      parent.textAlign(LEFT, CENTER);
      parent.ellipseMode(CORNER);
      parent.ellipse(x, y, b, h);
      parent.fill((int)col.value);
      parent.text((String)name.value, xPos + 35, yPos + (height / 2));
    } else if ((int)style.value == TOGGLE_BUTTON) {
      parent.textAlign(CENTER, CENTER);
      parent.rect(x, y, b, h);
      if ((boolean)checked.value) {
        parent.fill((int)bgCol.value);
      } else {
        parent.fill((int)col.value);
      } // end of if
      parent.text((String)name.value, xPos + (width / 2), yPos + (height / 2));
    }
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered) {
        return true;
      } // end of if
    } else if (e.getAction() == MouseEvent.PRESS) {
      pressed(e.getX(), e.getY());
      if (pressed) {
        return true;
      } // end of if
    } else if (e.getAction() == MouseEvent.RELEASE) {
      clicked(e.getX(), e.getY());
      if (hovered) {
        return true;
      } // end of if
    }
    return false;
  }
  
  private void mouseOver(int x, int y) {
    if ((int)style.value == CHECKBOX || (int)style.value == TOGGLE_BUTTON) {
      if (x > xPos && x < xPos + width && y > yPos && y < yPos + height) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else if ((int)style.value == CHECKBOX_ROUND) {
      if (PApplet.sqrt(PApplet.pow(x - (xPos + (width/2)), 2) + PApplet.pow(y - (yPos + (width/2)), 2)) < width/2) {
        hovered = true;
      } else {
        hovered = false;
      }
    }
  }
  
  private void pressed(int x, int y) {
    if (disabled) {
      pressed = false;
      return;
    }
    mouseOver(x, y);
    if (hovered && parent.mousePressed) {
      hovered = false;
      pressed = true;
    } else {
      pressed = false;
    }
  }
  
  private void clicked(int x, int y) {
    if (!disabled) {
      mouseOver(x, y);
      if (hovered) {
        checked.value = !(boolean)checked.value;
      }
    }
    pressed = false;
  }
  
  public boolean isChecked() {
    return (boolean)checked.value;
  }
  
  public boolean isMouseOver() {
    return hovered;
  }
  
  public boolean isPressed() {
    return pressed;
  }
  
  public void keyEvent(KeyEvent e) {
    //nothing
  }
  
  public boolean isEnabled() {
    return !disabled;
  }
  
  public void disable() {
    disabled = true;
    hovered = false;
    pressed = false;
  }
  
  public void enable() {
    disabled = false;
  }
  
  protected void check() {
    checked.value = true;
  }
  
  protected void uncheck() {
    checked.value = false;
  }
  
}