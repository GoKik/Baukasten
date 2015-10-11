package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Pencil extends GUIContainer implements PConstants {
  
  private boolean isDown;
  private Property fillBG, snapToM, keepDProps, col, bgCol, weight;
  private int oldPX, oldPY, penX, penY, oldW, oldH;
  private float angle = 0;
  private ArrayList<Line> drawing = new ArrayList<Line>();
  
  public Pencil(PApplet p, int x, int y, int w, int h) {
    super(p, x, y, w, h);
    col = registerProperty("Color", parent.color(0, 0, 0));
    fillBG = registerProperty("Fill Background", false);
    snapToM = registerProperty("Snap to Mouse", false);
    keepDProps = registerProperty("Keep Drawing Proportions", true);
    bgCol = registerProperty("Background Color", parent.color(255, 255, 255));
    weight = registerProperty("Weight", 1);
    penX = x;
    penY = y;
    oldW = w;
    oldH = h;
    addOnPropertyChangedListener(new OnPropertyChangedListener() {
      public void onPropertyChanged(String tag, Object value) {
        if (tag.equals("Background Color")) {
          setBackgroundColor((int)value);
        } // end of if
      }
    });
  }
  
  public void snapToMouse(boolean s) {
    snapToM.value = s;
  }
  
  public void keepDrawingPropotions(boolean p) {
    keepDProps.value = p;
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
    fillBG.value = true;
  }
  
  public void fillBackground(boolean b) {
    fillBG.value = b;
  }
  
  public void setStrokeWidth(int b) {
    weight.value = b;
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if ((boolean)snapToM.value) {
      if (e.getAction() == MouseEvent.PRESS && inBounds(e.getX(), e.getY())) {
        if (isUp()) {
          moveTo(e.getX(), e.getY());
          down();
        } 
        return true;
      } else if (e.getAction() == MouseEvent.RELEASE && !isUp()) {
        up();
        return true;
      } else if (e.getAction() == MouseEvent.DRAG && !isUp()) {
        moveTo(e.getX(), e.getY());
        return true;
      } else if (inBounds(e.getX(), e.getY())) {
        return true;
      } else {
        return false;
      } // end of if-else
    } else {
      return false;
    } // end of if-else
  }
  
  private boolean inBounds(int x, int y) {
    return (x > xPos && x < xPos + width && y > yPos && y < yPos + height);
  }
  
  public void keyEvent(KeyEvent e) {
    //nothing
  }
  
  public void onResize(float xFactor, float yFactor) {
    super.onResize(xFactor, yFactor);
    onChildResize();
  }
  
  public void onChildResize() {
    super.onChildResize();
    float widthFactor = (float)width / oldW;
    float heightFactor = (float)height / oldH; 
    for (int i = 0; i < drawing.size(); i++) {
      drawing.get(i).onResize((boolean)keepDProps.value?heightFactor:widthFactor, heightFactor);
    } // end of if
  }
  
  public void onFixSize() {
    super.onFixSize();
    oldW = width;
    oldH = height; 
    for (int i = 0; i < drawing.size(); i++) {
      drawing.get(i).onFixSize();
    } // end of if
  }
  
  public void draw() {
    if ((boolean)fillBG.value) {
      parent.noStroke();
      parent.fill((int)bgCol.value);
      parent.rect(xPos, yPos, width, height);  
    } // end of if
    parent.stroke((int)col.value);
    parent.strokeWeight((int)weight.value);
    for (int i = 0; i < drawing.size(); i++) {
      drawing.get(i).draw(xPos, yPos);
    }
  }
  
  public void down() {
    isDown = true;
  }
  
  public void up() {
    isDown = false;
  }
  
  public void moveTo(int x, int y) {
    oldPX = penX;
    oldPY = penY;
    penX = x;
    penY = y;
    if (isDown && checkBorders()) {
      drawing.add(new Line(oldPX - xPos, oldPY - yPos, penX - xPos, penY - yPos));   
    }
  }
  
  private boolean checkBorders() {
    float xNew, yNew;
    if ((penX <= xPos || penX >= xPos + width || penY <= yPos || penY >= yPos + height)
    && (oldPX <= xPos || oldPX >= xPos + width || oldPY <= yPos || oldPY >= yPos + height)) {
      return false; 
    }
    if (!(penX < xPos && oldPX < xPos)) {
      if (penX < xPos) {
        yNew = (parent.parseFloat(xPos - oldPX) / parent.parseFloat(penX - oldPX)) * (penY - oldPY) + oldPY;
        xNew = xPos;
        penX = (int)xNew;
        penY = (int)yNew;
        return true;
      } else if (oldPX < xPos) {
        yNew = (parent.parseFloat(xPos - penX) / parent.parseFloat(oldPX - penX)) * (oldPY - penY) + penY;
        xNew = xPos;
        oldPX = (int)xNew;
        oldPY = (int)yNew;
        return true;
      }
    } else {
      return false; 
    }  
    if (!(penX > xPos + width && oldPX > xPos + width)) {
      if (penX > xPos + width) {
        yNew = (parent.parseFloat(xPos + width - oldPX) / parent.parseFloat(penX - oldPX)) * (penY - oldPY) + oldPY; 
        xNew = xPos + width;
        penX = (int)xNew;
        penY = (int)yNew;
        return true;
      } else if (oldPX > xPos + width) {
        yNew = (parent.parseFloat(xPos + width - penX) / parent.parseFloat(oldPX - penX)) * (oldPY - penY) + penY; 
        xNew = xPos + width;
        oldPX = (int)xNew;
        oldPY = (int)yNew;
        return true;
      } 
    } else {
      return false;
    }
    if (!(penY < yPos && oldPY < yPos)) {
      if (penY < yPos) {
        xNew = (parent.parseFloat(yPos - oldPY) / (penY - oldPY)) * (penX - oldPX) + oldPX;
        yNew = yPos;
        penX = (int)xNew;
        penY = (int)yNew;
        return true;
      } else if (oldPY < yPos) {
        xNew = (parent.parseFloat(yPos - penY) / (oldPY - penY)) * (oldPX - penX) + penX;
        yNew = yPos;
        oldPX = (int)xNew;
        oldPY = (int)yNew;
        return true;
      } 
    } else {
      return false;
    }
    if (!(penY > yPos + height && oldPY > yPos + height)) {
      if (penY > yPos + height) {
        xNew = (parent.parseFloat(yPos + height - oldPY) / (penY - oldPY)) * (penX - oldPX) + oldPX;
        yNew = yPos + height;
        penX = (int)xNew;
        penY = (int)yNew;
        return true;
      } else if (oldPY > yPos + height) {
        xNew = (parent.parseFloat(yPos + height - penY) / (oldPY - penY)) * (oldPX - penX) + penX;
        yNew = yPos + height;
        oldPX = (int)xNew;
        oldPY = (int)yNew;
        return true;
      } 
    } else {
      return false;
    }
    return true;
    
  }
  
  public void moveBy(int x, int y) {
    moveTo(penX + x, penY + y);
  }
  
  public void moveBy(int r) {
    moveTo(penX + PApplet.parseInt(r * PApplet.cos(angle)), penY + PApplet.parseInt(r * PApplet.sin(angle)));
  }
  
  public void turnBy(int w) {
    angle += w;
    angle %= 360;
  }
  
  public void turnTo(int w) {
    angle = w;
    angle %= 360;
  }
  
  public boolean isUp() {
    return !isDown;
  }
  
  public int getColor() {
    return (int)col.value;
  }
  
  public void clearDrawing() {
    drawing.clear();
  }
  
  private class Line {
    
    private int x1, y1, x2, y2, oldX, oldX2, oldY, oldY2;
    
    public Line(int xa, int ya, int xb, int yb) {
      x1 = xa;
      y1 = ya;
      x2 = xb;
      y2 = yb;
      oldY = y1;
      oldY2 = y2;
      oldX = x1;
      oldX2 = x2;
    }
    
    public void onResize(float xFactor, float yFactor) {        
      x1 = (int)(oldX * xFactor);
      x2 = (int)(oldX2 * xFactor); 
      
      y1 = (int)(oldY * yFactor);
      y2 = (int)(oldY2 * yFactor); 
    }
    
    public void onFixSize() {
      oldY = y1;
      oldY2 = y2;
      oldX = x1;
      oldX2 = x2;
    }
    
    public void draw(int x, int y) {
      parent.line(x + x1, y + y1, x + x2, y + y2);
    }
    
  }
}
  