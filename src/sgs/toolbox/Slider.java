package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Slider extends GUIObject implements PConstants {
  
  public static final int HORIZONTAL = 1;
  public static final int VERTICAL = 2;
  
  private int width, minValue, maxValue, step, value, col, bgCol, markerStep, style;
  private boolean showMarkers, hovered, pressed;
  
  public Slider(PApplet p, int x, int y, int w) {
    super(p, x, y);
    width = w;
    minValue = 0;
    maxValue = 100;
    step = 1;
    value = 0;
    style = 1;
    col = parent.color(0, 0, 0);
    bgCol = parent.color(255, 255, 255);
  }
  
  public int getValue() {
    return value;
  }
  
  public void setColor(int c) {
    col = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol = c;
  }
  
  public void setStyle(int i) {
    if (i == HORIZONTAL || i == VERTICAL) {
      style = i;
    } // end of if
  }
  
  public void setMaxValue(int m) {
    maxValue = m;
    if (value > maxValue) {
      value = maxValue;
    } // end of if
  }
  
  public void setMinValue(int m) {
    minValue = m;
    if (value < minValue) {
      value = minValue;
    } // end of if
  }
  
  public void draw() {
    parent.strokeWeight(2);
    parent.stroke(col);
    if (style == HORIZONTAL) {
      parent.line(xPos, yPos, xPos + width, yPos); 
    } else {
      parent.line(xPos, yPos, xPos, yPos + width); 
    } // end of if-else
    if (showMarkers) {
      for (int i = minValue; i <= maxValue ; i++ ) {
        if (i % markerStep == 0) {
          if (style == HORIZONTAL) {
            parent.line(getXFromValue(i), yPos - 10, getXFromValue(i), yPos + 10);
          } else {
            parent.line(xPos - 10, getYFromValue(i), xPos + 10, getYFromValue(i)); 
          } // end of if-else
        } // end of if
      } // end of for
    } // end of if
    parent.fill(col);
    parent.ellipseMode(CENTER);
    parent.stroke(bgCol);
    int x, y;
    if (style == HORIZONTAL) {
      x = getXFromValue(value);
      y = yPos;
    } else {
      y = getYFromValue(value);
      x = xPos;
    } // end of if-else
    
    if (pressed) {
      parent.ellipse(x, y, 18, 18);
    } else if (hovered) {
      parent.ellipse(x, y, 20, 20);
    } else {   
      parent.ellipse(x, y, 16, 16);
    } // end of if-else  
  }
  
  public void setMarkerStep(int s) {
    markerStep = s;
  }
  
  public void setStep(int s) {
    step = s;
  }
  
  public void showMarkers(boolean b) {
    showMarkers = b;
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered) {
        return true;
      } // end of if
    } else if (!pressed && hovered && e.getAction() == MouseEvent.PRESS) {
      pressed = true;
      return true;
    } else if (pressed && e.getAction() == MouseEvent.RELEASE) {
      pressed = false;
      return true;
    } else if (pressed && e.getAction() == MouseEvent.DRAG) {
      if (style == HORIZONTAL) {
        value = getValueFromX(e.getX());
      } else {
        value = getValueFromY(e.getY());
      } // end of if-else
      return true;
    }
    return false;
  }
  
  private void mouseOver(int xT, int yT) {
    int x, y;
    if (style == HORIZONTAL) {
      x = getXFromValue(value);
      y = yPos;
    } else {
      y = getYFromValue(value);
      x = xPos;
    } // end of if-else
    if (PApplet.sqrt(PApplet.pow(xT - x, 2) + PApplet.pow(yT - y, 2)) < 8) {
      hovered = true;
    } else {
      hovered = false;
    }
  }
  
  
  public void keyEvent(KeyEvent e) {
    //nothing yet
  }
  
  private int getXFromValue(int v) {
    return (int)(xPos + ((float)width / (maxValue - minValue)) * (v - minValue));
  }  
  
  private int getYFromValue(int v) {
    return (int)(yPos + ((float)width / (maxValue - minValue)) * (v - minValue));
  }
  
  private int getValueFromX(int x) {
    int v = (((x - xPos) * (maxValue - minValue)) / width) + minValue;
    if (v < minValue) {
      v = minValue;
    } // end of if
    if (v > maxValue) {
      v = maxValue;
    } // end of if
    
    if (v % step != 0) {
      int s = v % step;
      if (s < step / 2) {
        v -= s;
      } else {
        v += (step - s);
      } // end of if-else
    } // end of if
    
    return v;
  }
  
  private int getValueFromY(int y) {
    int v = (((y - yPos) * (maxValue - minValue)) / width) + minValue;
    if (v < minValue) {
      v = minValue;
    } // end of if
    if (v > maxValue) {
      v = maxValue;
    } // end of if
    
    if (v % step != 0) {
      int s = v % step;
      if (s < step / 2) {
        v -= s;
      } else {
        v += (step - s);
      } // end of if-else
    } // end of if
    
    return v;
  }
}