package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class MouseEventManager implements PConstants {
  
  int xPos, yPos, width, height;
  
  private boolean hovered, pressed, clicked, toggled, scrolled, rightClicked, dragged, released;
  private int scrollCount;
  
  public MouseEventManager(int x, int y, int w, int h) {
    xPos = x;
    yPos = y;
    width = w;
    height = h;
  }
  
  public boolean isHovered() {
    return hovered;
  }
  
  public boolean isPressed() {
    return pressed;
  }
  
  public boolean wasClicked() {
    if (clicked) {
      clicked = false;
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  public boolean isToggled() {
    return toggled;
  }
  
  public boolean wasScrolled() {
    if (scrolled) {
      scrolled = false;
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  public int getScrollCount() {
    return scrollCount;
  }
  
  public boolean wasRightClicked() {
    if (rightClicked) {
      rightClicked = false;
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  public boolean isDragged() {
    return dragged;
  }
  
  public boolean wasReleased() {
    if (released) {
      released = false;
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE && mouseOver(e.getX(), e.getY())) {
      return true;
    } else if (e.getAction() == MouseEvent.PRESS) {
      pressed = hovered;
      return true;
    } else if (e.getAction() == MouseEvent.RELEASE && hovered && pressed) {
      pressed = false;
      dragged = false;
      released = true;
      if (e.getButton() == LEFT) {
        clicked = true;
      } else if (e.getButton() == RIGHT) {
        rightClicked = true;
      } // end of if-else
      toggled = !toggled;
      return true;
    } else if (e.getAction() == MouseEvent.DRAG && pressed) {
      dragged = true; 
      return true;
    } else if (e.getAction() == MouseEvent.WHEEL && hovered) {
      scrolled = true;
      scrollCount = e.getCount(); 
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  private boolean mouseOver(int x, int y) {
    hovered = (x > xPos && x < xPos + width && y > yPos && y < yPos + height);
    return hovered;
  }
}