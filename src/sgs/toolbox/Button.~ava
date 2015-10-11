package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Button extends GUIObject implements PConstants{
  
  public final static int RECT = 1;
  public final static int ROUND = 2;
  
  private Property name, col, bgCol, disCol, textSize, style;
  //private String name;
  //private int col, bgCol, disCol;
  //private int textSize, style;
  private boolean hovered, disabled, pressed, clicked;
  
  public Button(PApplet p, String n, int x, int y, int w, int h) {
    super(p, x, y, w, h);
    name = registerProperty("Name", n);
    textSize = registerProperty("Text Size", 1);
    while (parent.textWidth(n) < width - 20) { 
      textSize.value = (int)textSize.value + 1;
      parent.textSize((int)textSize.value);
    } // end of while
    style = registerProperty("Style", 1);
    col = registerProperty("Color", p.color(0, 0, 0));
    bgCol = registerProperty("Background Color", p.color(255, 255, 255));
    disCol = registerProperty("Disabled Color", p.color(30, 30, 30, 150));
    addOnPropertyChangedListener(new OnPropertyChangedListener() {
      public void onPropertyChanged(String tag, Object value) {
        if (tag.equals("Style")) {
          setStyle((int)value);
        } else if (tag.equals("Name")) {
          setName((String)value);
        }
      }
    });
  }
  
  public void setName(String n) {
    name.value = n;     
    parent.textSize(1);
    textSize.value = 1;
    while (parent.textWidth(n) < width - 20) { 
      textSize.value = (int)textSize.value + 1;
      parent.textSize((int)textSize.value);
    } // end of while
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
  }
  
  public void setStyle(int i) {
    if (i == RECT) {
      style.value = i;
    } else if (i == ROUND) {  
      style.value = i;
      height = width;
    }
  }
  
  public void onResize(float xFactor, float yFactor) {
    super.onResize(xFactor, yFactor);
    textSize.value = 1;
    parent.textSize(1);
    while (parent.textWidth((String)name.value) < width - 20) { 
      textSize.value = (int)textSize.value + 1;
      parent.textSize((int)textSize.value);
    } // end of while
  }
  
  public void draw() {
    parent.textAlign(CENTER, CENTER);
    parent.strokeWeight(2);
    parent.textSize((int)textSize.value);
    if (disabled) {
      parent.fill((int)col.value); 
      parent.noStroke();
      //parent.stroke((int)col);
      drawBody(xPos, yPos, width, height);
      parent.fill((int)disCol.value);   
      //parent.stroke((int)disCol);
      drawBody(xPos, yPos, width, height);
      parent.fill(255, 255, 255);
      parent.textAlign(CENTER, CENTER);
      parent.textSize(width/6);
      parent.text("DISABLED", xPos + (width / 2), yPos + (height / 2));
    } else {
      if (pressed) {
        parent.fill((int)col.value);
        parent.stroke((int)bgCol.value);
        drawBody(xPos, yPos, width, height);
        parent.fill((int)bgCol.value);
        parent.text((String)name.value, xPos + (width / 2), yPos + (height / 2));
      } else if (hovered) {
        parent.fill((int)col.value);
        parent.stroke((int)col.value);
        drawBody(xPos, yPos, width, height);
        parent.fill((int)bgCol.value);
        parent.text((String)name.value, xPos + (width / 2), yPos + (height / 2));
      } else {
        parent.fill((int)bgCol.value);
        parent.stroke((int)col.value);
        drawBody(xPos, yPos, width, height);
        parent.fill((int)col.value);
        parent.text((String)name.value, xPos + (width / 2), yPos + (height / 2));
      }
    } // end of if-else
    
  }
  
  private void drawBody(int x, int y, int b, int h) {
    if ((int)style.value == RECT) {
      parent.rect(x, y, b, h);
    } else if ((int)style.value == ROUND) {
      parent.ellipseMode(CORNER);
      parent.ellipse(x, y, b, h);
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
      if (clicked) {
        return true;
      } // end of if
    }
    return false;
  }
  
  private void mouseOver(int x, int y) {
    if ((int)style.value == RECT) {
      if (x > xPos && x < xPos + width && y > yPos && y < yPos + height) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else if ((int)style.value == ROUND) {
      if (parent.sqrt(parent.pow(x - (xPos + (width/2)), 2) + parent.pow(y - (yPos + (width/2)), 2)) < width/2) {
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
    if (disabled) {
      clicked = false;
      return;
    }
    mouseOver(x, y);
    if (hovered) {
      clicked = true;
    } else {
      clicked = false;
    }
    pressed = false;
  }
  
  public boolean wasPressed() {
    if (clicked) {
      clicked = false;
      return true;
    } else {
      return false;
    }
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
  
}
  
    