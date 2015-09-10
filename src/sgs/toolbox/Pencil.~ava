package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Pencil extends GUIObject implements PConstants {
  
  private boolean isDown, fillBG, snapToM;
  private int oldX, oldY, minX, minY, maxX, maxY;
  private float angle = 0;
  private ArrayList<Line> drawing = new ArrayList<Line>();
  private int col, bgCol;
  private int width = 1;
  
  public Pencil(PApplet p) {
    super(p, 0, 0);
    col = parent.color(0, 0, 0);
    minX = 0;
    maxX = parent.width;
    minY = 0;
    maxY = parent.height;
  }
  
  public void snapToMouse(boolean s) {
    snapToM = s;
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (snapToM && inBounds(e.getX(), e.getY())) {
      if (e.getAction() == MouseEvent.PRESS) {
        if (isUp()) {
          moveTo(e.getX(), e.getY());
          down();
        }
      } else if (e.getAction() == MouseEvent.RELEASE) {
        up();
      } else if (e.getAction() == MouseEvent.DRAG) {
        moveTo(e.getX(), e.getY());
      }
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  private boolean inBounds(int x, int y) {
    return (x > minX && x < maxX && y > minY && y < maxY);
  }
  
  public void keyEvent(KeyEvent e) {
    //nothing
  }
  
  public void draw() {
    if (fillBG) {
      parent.noStroke();
      parent.fill(bgCol);
      parent.rect(minX, minY, maxX - minX, maxY - minY);  
    } // end of if
    parent.stroke(col);
    parent.strokeWeight(width);
    for (int i = 0; i < drawing.size(); i++) {
      drawing.get(i).draw();
    }
  }
  
  public void setBounds(int x1, int y1, int x2, int y2) {
    minX = x1;
    minY = y1;
    maxX = x2;
    maxY = y2;
  }
  
  public void down() {
    isDown = true;
  }
  
  public void up() {
    isDown = false;
  }
  
  public void moveTo(int x, int y) {
    oldX = xPos;
    oldY = yPos;
    xPos = x;
    yPos = y;
    if (isDown && checkBorders()) {
      drawing.add(new Line(oldX, oldY, xPos, yPos));   
    }
  }
  
  private boolean checkBorders() {
    float xNew, yNew;
    if ((xPos <= minX || xPos >= maxX || yPos <= minY || yPos >= maxY)
    && (oldX <= minX || oldX >= maxX || oldY <= minY || oldY >= maxY)) {
      return false; 
    }
    if (!(xPos < minX && oldX < minX)) {
      if (xPos < minX) {
        yNew = (parent.parseFloat(minX - oldX) / parent.parseFloat(xPos - oldX)) * (yPos - oldY) + oldY;
        xNew = minX;
        xPos = (int)xNew;
        yPos = (int)yNew;
        return true;
      } else if (oldX < minX) {
        yNew = (parent.parseFloat(minX - xPos) / parent.parseFloat(oldX - xPos)) * (oldY - yPos) + yPos;
        xNew = minX;
        oldX = (int)xNew;
        oldY = (int)yNew;
        return true;
      }
    } else {
      return false; 
    }  
    if (!(xPos > maxX && oldX > maxX)) {
      if (xPos > maxX) {
        yNew = (parent.parseFloat(maxX - oldX) / parent.parseFloat(xPos - oldX)) * (yPos - oldY) + oldY; 
        xNew = maxX;
        xPos = (int)xNew;
        yPos = (int)yNew;
        return true;
      } else if (oldX > maxX) {
        yNew = (parent.parseFloat(maxX - xPos) / parent.parseFloat(oldX - xPos)) * (oldY - yPos) + yPos; 
        xNew = maxX;
        oldX = (int)xNew;
        oldY = (int)yNew;
        return true;
      } 
    } else {
      return false;
    }
    if (!(yPos < minY && oldY < minY)) {
      if (yPos < minY) {
        xNew = (parent.parseFloat(minY - oldY) / (yPos - oldY)) * (xPos - oldX) + oldX;
        yNew = minY;
        xPos = (int)xNew;
        yPos = (int)yNew;
        return true;
      } else if (oldY < minY) {
        xNew = (parent.parseFloat(minY - yPos) / (oldY - yPos)) * (oldX - xPos) + xPos;
        yNew = minY;
        oldX = (int)xNew;
        oldY = (int)yNew;
        return true;
      } 
    } else {
      return false;
    }
    if (!(yPos > maxY && oldY > maxY)) {
      if (yPos > maxY) {
        xNew = (parent.parseFloat(maxY - oldY) / (yPos - oldY)) * (xPos - oldX) + oldX;
        yNew = maxY;
        xPos = (int)xNew;
        yPos = (int)yNew;
        return true;
      } else if (oldY > maxY) {
        xNew = (parent.parseFloat(maxY - yPos) / (oldY - yPos)) * (oldX - xPos) + xPos;
        yNew = maxY;
        oldX = (int)xNew;
        oldY = (int)yNew;
        return true;
      } 
    } else {
      return false;
    }
    return true;
    
  }
  
  public void moveBy(int x, int y) {
    moveTo(xPos + x, yPos + y);
  }
  
  public void moveBy(int r) {
    moveTo(xPos + PApplet.parseInt(r * PApplet.cos(angle)), yPos + PApplet.parseInt(r * PApplet.sin(angle)));
  }
  
  public void turnBy(int w) {
    angle += w;
    angle %= 360;
  }
  
  public void turnTo(int w) {
    angle = w;
    angle %= 360;
  }
  
  public void setColor(int c) {
    col = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol = c;
    fillBG = true;
  }
  
  public void setStrokeWidth(int b) {
    width = b;
  }
  
  public boolean isUp() {
    return !isDown;
  }
  
  public int getColor() {
    return col;
  }
  
  public void clearDrawing() {
    drawing.clear();
  }
  
  private class Line {
    
    private int xA, yA, xB, yB;
    
    public Line(int xa, int ya, int xb, int yb) {
      xA = xa;
      yA = ya;
      xB = xb;
      yB = yb;
    }
    
    public void draw() {
      parent.line(xA, yA, xB, yB);
    }
    
  }
}
  