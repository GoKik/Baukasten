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
  
  private int width, col, bgCol, style;
  private float startValue, stopValue, step, value, markerStep;
  private boolean showMarkers, hovered, pressed, changed;
  
  public Slider(PApplet p, int x, int y, int w) {
    super(p, x, y);
    width = w;
    startValue = 0;
    stopValue = 100;
    step = 1;
    markerStep = 1;
    value = 0;
    style = 1;
    col = parent.color(0, 0, 0);
    bgCol = parent.color(255, 255, 255);
  }
  
  public float getValue() {
    return value;
  }
  
  public void setValue(float i) {
    value = i;
    float min = startValue;
    float max = stopValue;
    if (startValue > stopValue) {
      min = stopValue;
      max = startValue;
    }
    if (value < min) {
      value = min;
    } // end of if
    if (value > max) {
      value = max;
    } // end of if
    if (value % step != 0) {
      float s = value % step;
      if (s < step / 2) {
        value -= s;
      } else {
        value += (step - s);
      } // end of if-else
    } // end of if
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
  
  public void setStopValue(float s) {
    stopValue = s;
    if (stopValue >= startValue && value > stopValue) {
      value = stopValue;
    } // end of if
    if (stopValue < startValue && value < stopValue) {
      value = stopValue;
    } // end of if
  }
  
  public void setStartValue(float s) {
    startValue = s;
    if (startValue <= stopValue && value < startValue) {
      value = startValue;
    } // end of if
    if (startValue > stopValue && value > startValue) {
      value = startValue;
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
    float min = startValue;
    float max = stopValue;
    if (startValue > stopValue) {
      min = stopValue;
      max = startValue;
    }
    if (showMarkers) {
      float p = min + (min % markerStep);
      
      for (float i = p; i <= max; i += markerStep ) {
        if (style == HORIZONTAL) {
          parent.line(getXFromValue(i), yPos - 10, getXFromValue(i), yPos + 10);
        } else {
          parent.line(xPos - 10, getYFromValue(i), xPos + 10, getYFromValue(i)); 
        } // end of if-else
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
  
  public void setMarkerStep(float s) {
    markerStep = s;
  }
  
  public void setStep(float s) {
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
      if (style == HORIZONTAL) {
        value = getValueFromX(e.getX());
      } else {
        value = getValueFromY(e.getY());
      } // end of if-else
      return true;
    } else if (pressed && e.getAction() == MouseEvent.RELEASE) {
      pressed = false;
      changed = true;
      return true;
    } else if (pressed && e.getAction() == MouseEvent.DRAG) {
      if (style == HORIZONTAL) {
        value = getValueFromX(e.getX());
      } else {
        value = getValueFromY(e.getY());
      } // end of if-else
      return true;
    } else if (hovered && e.getAction() == MouseEvent.WHEEL) {
      setValue(value + (e.getCount() * step));
    } // end of if-else
    return false;
  }
  
  private void mouseOver(int xT, int yT) {
    
    if (style == HORIZONTAL) {
      if (xT > xPos - 10 && xT < xPos + width + 10 && yT > yPos - 10 && yT < yPos + 10) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else {
      if (xT > xPos - 10 && xT < xPos + 10 && yT > yPos -10 && yT < yPos + width + 10) {
        hovered = true;
      } else {
        hovered = false;
      }
    } // end of if-else
    
    
  }
  
  
  public void keyEvent(KeyEvent e) {
    //nothing yet
  }
  
  public boolean valueChanged() {
    if (changed) {
      changed = false;
      return true;
    } else {
      return false;
    } // end of if-else
  }
  private int getXFromValue(float v) {
    return (int)(xPos + ((float)width / (stopValue - startValue)) * (v - startValue));
  }  
  
  private int getYFromValue(float v) {
    return (int)(yPos + ((float)width / (stopValue - startValue)) * (v - startValue));
  }
  
  private float getValueFromX(float x) {
    float v = (((float)(x - xPos) * (stopValue - startValue)) / (float)width) + startValue;
    float min = startValue;
    float max = stopValue;
    if (startValue > stopValue) {
      min = stopValue;
      max = startValue;
    }
    if (v < min) {
      v = min;
    } // end of if
    if (v > max) {
      v = max;
    } // end of if
    
    if (v % step != 0) {
      float s = v % step;
      if (s < step / 2) {
        v -= s;
      } else {
        v += (step - s);
      } // end of if-else
    } // end of if
    
    return v;
  }
  
  private float getValueFromY(int y) {
    float v = (((float)(y - yPos) * (stopValue - startValue)) / (float)width) + startValue;
    float min = startValue;
    float max = stopValue;
    if (startValue > stopValue) {
      min = stopValue;
      max = startValue;
    }
    if (v < min) {
      v = min;
    } // end of if
    if (v > max) {
      v = max;
    } // end of if
    
    if (v % step != 0) {
      float s = v % step;
      if (s < step / 2) {
        v -= s;
      } else {
        v += (step - s);
      } // end of if-else
    } // end of if
    
    return v;
  }
}