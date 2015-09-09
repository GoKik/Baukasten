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
  
  private String name;
  private int col, bgCol, disCol;
  private int textSize, style, width, height;
  private boolean hovered, pressed, checked, disabled;
  
  public Checkbox(PApplet p, String n, int x, int y, boolean c) {
    super(p, x, y);
    name = n;
    col = parent.color(0, 0, 0);
    bgCol = parent.color(255, 255, 255);
    disCol = parent.color(30, 30, 30, 150);
    style = CHECKBOX;
    textSize = 15;
    width = height = 30;
    checked = c;
  }
  
  public void setColor(int c) {
    col = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol = c;
  }
  
  public void setzeTextSize(int t) {
    textSize = t;
  }
  
  public void setStyle(int i) {
    if (i == CHECKBOX || i == CHECKBOX_ROUND) {
      style = i;
      height = width;
    } else if (i == TOGGLE_BUTTON) {
      style = i;
    }
  }
  
  public void draw() {
    parent.textSize(textSize);
    parent.strokeWeight(2);
    
    if (pressed) {
      if (checked) {
        parent.fill(bgCol);
        parent.stroke(col);
        drawBody(xPos + 2, yPos + 2, width - 4, height - 4);
      } else {
        parent.fill(bgCol);
        parent.stroke(col);
        drawBody(xPos + 2, yPos + 2, width - 4, height - 4);
        parent.fill(col);
        parent.noStroke();
        drawBody(xPos + 7, yPos + 7, width - 13, height - 13);
      }
    } else if ((hovered && !checked) || (!hovered && checked)) {
      parent.fill(bgCol);
      parent.stroke(col);
      drawBody(xPos, yPos, width, height);
      parent.fill(col);
      parent.noStroke();
      drawBody(xPos + 5, yPos + 5, width - 9, height - 9);
    } else {
      parent.fill(bgCol);
      parent.stroke(col);
      drawBody(xPos, yPos, width, height);
    }
    
    if (disabled) {
      parent.fill(disCol);
      parent.stroke(disCol);
      drawBody(xPos, yPos, width, height);
      if (style == TOGGLE_BUTTON) {
        parent.fill(255, 255, 255);
        parent.textAlign(CENTER, CENTER);
        parent.text("DISABLED", xPos + (width / 2), yPos + (height / 2));
      }
    }
  }
  
  private void drawBody(int x, int y, int b, int h) {
    if (style == CHECKBOX) { 
      parent.textAlign(LEFT, CENTER);
      parent.rect(x, y, b, h);
      parent.fill(col);
      parent.text(name, xPos + 35, yPos + (height / 2));
    } else if (style == CHECKBOX_ROUND) {
      parent.textAlign(LEFT, CENTER);
      parent.ellipseMode(CORNER);
      parent.ellipse(x, y, b, h);
      parent.fill(col);
      parent.text(name, xPos + 35, yPos + (height / 2));
    } else if (style == TOGGLE_BUTTON) {
      parent.textAlign(CENTER, CENTER);
      parent.rect(x, y, b, h);
      parent.text(name, xPos + (width / 2), yPos + (height / 2));
    }
  }
  
  public void mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.PRESS) {
      pressed(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.RELEASE) {
      clicked(e.getX(), e.getY());
    }
  }
  
  private void mouseOver(int x, int y) {
    if (style == CHECKBOX || style == TOGGLE_BUTTON) {
      if (x > xPos && x < xPos + width && y > yPos && y < yPos + height) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else if (style == CHECKBOX_ROUND) {
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
        checked = !checked;
      }
    }
    pressed = false;
  }
  
  public boolean isChecked() {
    return checked;
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
    checked = true;
  }
  
  protected void uncheck() {
    checked = false;
  }
  
}