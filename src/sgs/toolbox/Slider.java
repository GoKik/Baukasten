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
  public static final int CIRCLE = 3;
  
  private Property col, bgCol, style, startValue, stopValue, step, value, markerStep, showMarkers;
  private boolean hovered, pressed, changed;
  
  public Slider(PApplet p, int x, int y, int w) {
    super(p, x, y, w, w);
    startValue = registerProperty("Start Value", 0f);
    stopValue = registerProperty("Stop Value", 100f);
    step = registerProperty("Step", 1f);
    markerStep = registerProperty("Marker Step", 1f);
    value = registerProperty("Value", 0f);
    style = registerProperty("Style", 1);
    col = registerProperty("Color", parent.color(0, 0, 0));
    bgCol = registerProperty("Background Color", parent.color(255, 255, 255));
    showMarkers = registerProperty("Show Markers", false);
    addOnPropertyChangedListener(new OnPropertyChangedListener() {
      public void onPropertyChanged(String tag, Object v) {
        if (tag.equals("Value")) {
          setValue((float)v);
        } else if (tag.equals("Style")) {
          setStyle((int)v);
        } else if (tag.equals("Start Value")) {
          setStartValue((float)v);
        } else if (tag.equals("Stop Value")) {
          setStopValue((float)v);
        }
      }
    });
  }
  
  public float getValue() {
    return (float)value.value;
  }
  
  public void setValue(float i) {
    value.value = i;
    float min = (float)startValue.value;
    float max = (float)stopValue.value;
    if ((float)startValue.value > (float)stopValue.value) {
      min = (float)stopValue.value;
      max = (float)startValue.value;
    }
    if ((float)value.value < min) {
      value.value = min;
    } // end of if
    if ((float)value.value > max) {
      value.value = max;
    } // end of if
    if ((float)value.value % (float)step.value != 0) {
      float s = (float)value.value % (float)step.value;
      if (s < (float)step.value / 2) {
        value.value = (float)value.value - s;
      } else {
        value.value = (float)value.value + ((float)step.value - s);
      } // end of if-else
    } // end of if
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
  }
  
  public void setStyle(int i) {
    if (i == HORIZONTAL || i == VERTICAL || i == CIRCLE) {
      style.value = i;
    } // end of if
  }
  
  public void setMarkerStep(float s) {
    markerStep.value = s;
  }
  
  public void setStep(float s) {
    step.value = s;
  }
  
  public void showMarkers(boolean b) {
    showMarkers.value = b;
  }
  
  public void setStopValue(float st) {
    stopValue.value = st;
    if ((float)stopValue.value >= (float)startValue.value && (float)value.value > (float)stopValue.value) {
      value.value = (float)stopValue.value;
    } // end of if
    if ((float)stopValue.value < (float)startValue.value && (float)value.value < (float)stopValue.value) {
      value.value = (float)stopValue.value;
    } // end of if    float min = startValue;
    if ((float)value.value % (float)step.value != 0) {
      float s = (float)value.value % (float)step.value;
      if (s < (float)step.value / 2) {
        value.value = (float)value.value - s;
      } else {
        value.value = (float)value.value + ((float)step.value - s);
      } // end of if-else
    } // end of if
  }
  
  public void setStartValue(float st) {
    startValue.value = st;
    if ((float)startValue.value <= (float)stopValue.value && (float)value.value < (float)startValue.value) {
      value.value = (float)startValue.value;
    } // end of if
    if ((float)startValue.value > (float)stopValue.value && (float)value.value > (float)startValue.value) {
      value.value = (float)startValue.value;
    } // end of if if (value.value % step.value != 0) {
    float s = (float)value.value % (float)step.value;
    if (s < (float)step.value / 2) {
      value.value = (float)value.value - s;
    } else {
      value.value = (float)value.value + ((float)step.value - s);
    } // end of if-else
  }                     
  
  public void draw() {
    parent.strokeWeight(2);
    parent.stroke((int)col.value);
    parent.noFill();
    if ((int)style.value == HORIZONTAL) {
      parent.line(xPos, yPos, xPos + width, yPos); 
    } else if ((int)style.value == VERTICAL) {
      parent.line(xPos, yPos, xPos, yPos + height); 
    } else if ((int)style.value == CIRCLE) {
      parent.ellipseMode(CENTER);
      parent.ellipse(xPos,yPos, width*2, width*2);
    }
    float min = (float)startValue.value;
    float max = (float)stopValue.value;
    if ((float)startValue.value > (float)stopValue.value) {
      min = (float)stopValue.value;
      max = (float)startValue.value;
    }
    if ((boolean)showMarkers.value) {
      float p = min + (min % (float)markerStep.value);
      
      for (float i = p; i <= max; i += (float)markerStep.value ) {
        if ((int)style.value == HORIZONTAL) {
          parent.line(getXFromValue(i), yPos - 10, getXFromValue(i), yPos + 10);
        } else if ((int)style.value == VERTICAL) {
          parent.line(xPos - 10, getYFromValue(i), xPos + 10, getYFromValue(i)); 
        } else if ((int)style.value == CIRCLE) {
          float a = i/(max-min)*360;
          int x1 = (int)getCoordinates(xPos, yPos, width-5, a, true);
          int y1 = (int)getCoordinates(xPos, yPos, width-5, a, false);
          int x2 = (int)getCoordinates(xPos, yPos, width+5, a, true);
          int y2 = (int)getCoordinates(xPos, yPos, width+5, a, false);
          parent.line(x1, y1, x2, y2);
        } // end of for
      } // end of if
    }
    
    parent.fill((int)col.value);
    parent.ellipseMode(CENTER);
    parent.stroke((int)bgCol.value);
    int x = xPos;
    int y = yPos;
    if ((int)style.value == HORIZONTAL) {
      x = getXFromValue((float)value.value);
      y = yPos;
    } else if ((int)style.value == VERTICAL){
      y = getYFromValue((float)value.value);
      x = xPos;
    } else if ((int)style.value == CIRCLE) {
      float a = ((float)value.value-min)/(max-min)*360 + 90;
      x = (int)getCoordinates(xPos, yPos, width, a, true);
      y = (int)getCoordinates(xPos, yPos, width, a, false); 
    }
    
    if (pressed) {
      parent.ellipse(x, y, 18, 18);
    } else if (hovered) {
      parent.ellipse(x, y, 20, 20);
    } else {   
      parent.ellipse(x, y, 16, 16);
    } // end of if-else  
  }
  
  
  public boolean mouseEvent(MouseEvent e) {
    
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered) {
        return true;
      } // end of if
    } else if (!pressed && hovered && e.getAction() == MouseEvent.PRESS) {
      pressed = true;   
      if ((int)style.value == HORIZONTAL) {
        value.value = getValueFromX(e.getX());
      } else if ((int)style.value == VERTICAL){
        value.value = getValueFromY(e.getY());
      } else if ((int)style.value == CIRCLE){
        value.value = getValueFromCircle(e.getX(), e.getY());
      } // end of if-else
      return true;
    } else if (pressed && e.getAction() == MouseEvent.RELEASE) {
      pressed = false;
      changed = true;
      return true;
    } else if (pressed && e.getAction() == MouseEvent.DRAG) {
      if ((int)style.value == HORIZONTAL) {
        value.value = getValueFromX(e.getX());
      } else if ((int)style.value == VERTICAL) {
        value.value = getValueFromY(e.getY());
      } else if ((int)style.value == CIRCLE){
        value.value = getValueFromCircle(e.getX(), e.getY());
      } // end of if-else
      return true;
    } else if (hovered && e.getAction() == MouseEvent.WHEEL) {
      setValue((float)value.value + (e.getCount() * (float)step.value));
    } // end of if-else
    return false;
  }
  
  private void mouseOver(int xT, int yT) {
    
    if ((int)style.value == HORIZONTAL) {
      if (xT > xPos - 10 && xT < xPos + width + 10 && yT > yPos - 10 && yT < yPos + 10) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else if ((int)style.value == VERTICAL) {
      if (xT > xPos - 10 && xT < xPos + 10 && yT > yPos -10 && yT < yPos + height + 10) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else if ((int)style.value == CIRCLE) {
      if (width < getRadius(xPos, yPos, xT, yT)+5 && width > getRadius(xPos, yPos, xT, yT)-5) {
        hovered = true;
      } else {
        hovered = false;// end of if-else
        
      }
    }
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
    return (int)(xPos + ((float)width / ((float)stopValue.value - (float)startValue.value)) * (v - (float)startValue.value));
  }  
  
  private int getYFromValue(float v) {
    return (int)(yPos + ((float)height / ((float)stopValue.value - (float)startValue.value)) * (v - (float)startValue.value));
  }
  
  private float getValueFromCircle(int x, int y) {
    float min = (float)startValue.value;
    float max = (float)stopValue.value;
    if ((float)startValue.value > (float)stopValue.value) {
      min = (float)stopValue.value;
      max = (float)startValue.value;
    }
    float a = getAngle(xPos, yPos, x, y);
    float v = ((a+270)%360)/360*(max-min) + min;  
    if (v % (float)step.value != 0) {
      float s = v % (float)step.value;
      if (s < (float)step.value / 2) {
        v -= s;
      } else {
        v += ((float)step.value - s);
      } // end of if-else
    } // end of if
    return v;
  }
  
  private float getValueFromX(float x) {
    float v = (((float)(x - xPos) * ((float)stopValue.value - (float)startValue.value)) / (float)width) + (float)startValue.value;
    float min = (float)startValue.value;
    float max = (float)stopValue.value;
    if ((float)startValue.value > (float)stopValue.value) {
      min = (float)stopValue.value;
      max = (float)startValue.value;
    }
    if (v < min) {
      v = min;
    } // end of if
    if (v > max) {
      v = max;
    } // end of if
    
    if (v % (float)step.value != 0) {
      float s = v % (float)step.value;
      if (s < (float)step.value / 2) {
        v -= s;
      } else {
        v += ((float)step.value - s);
      } // end of if-else
    } // end of if
    
    return v;
  }
  
  private float getValueFromY(int y) {
    float v = (((float)(y - yPos) * ((float)stopValue.value - (float)startValue.value)) / (float)height) + (float)startValue.value;
    float min = (float)startValue.value;
    float max = (float)stopValue.value;
    if ((float)startValue.value > (float)stopValue.value) {
      min = (float)stopValue.value;
      max = (float)startValue.value;
    }
    if (v < min) {
      v = min;
    } // end of if
    if (v > max) {
      v = max;
    } // end of if
    
    if (v % (float)step.value != 0) {
      float s = v % (float)step.value;
      if (s < (float)step.value / 2) {
        v -= s;
      } else {
        v += ((float)step.value - s);
      } // end of if-else
    } // end of if
    
    return v;
  }
  public float getCoordinates(float startX, float startY, float r, float a, boolean x) {
    
    if (x) {
      return (r * parent.cos(parent.radians(a))) + startX;
    } else {
      return (-r * parent.sin(parent.radians(a))) + startY;
    }
  } // getCoordinates
  public float getRadius(float startX, float startY, float endX, float endY) {
    return parent.sqrt(parent.sq(startX - endX) + parent.sq(startY - endY));
  } // getRadius
  
  public float getAngle(float startX, float startY, float endX, float endY) {
    float r = getRadius(startX, startY, endX, endY);
    if (endY - startY > 0) { 
      return parent.degrees(parent.acos((endX - startX)/(-r))) + 180;
    } else {
      return parent.degrees(parent.acos((endX - startX)/r));
    }
  } // getAngle
}
