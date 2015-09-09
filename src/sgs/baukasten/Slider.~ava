//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Slider extends GUIObject implements PConstants {
  
  private int width, minValue, maxValue, step, value, col, bgCol, markerStep;
  private boolean showMarkers, hovered, pressed;
  
  public Slider(PApplet p, int x, int y, int b) {
    super(p, x, y);
    width = b;
    minValue = 0;
    maxValue = 100;
    step = 1;
    value = 0;
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
    parent.line(xPos, yPos, xPos + width, yPos); 
    if (showMarkers) {
      for (int i = minValue; i <= maxValue ; i++ ) {
        if (i % markerStep == 0) {
          parent.line(getXFromValue(i), yPos - 10, getXFromValue(i), yPos + 10);
        } // end of if
      } // end of for
    } // end of if
    parent.fill(col);
    parent.ellipseMode(CENTER);
    parent.stroke(bgCol);
    int x = getXFromValue(value);
    if (pressed) {
      parent.ellipse(x, yPos, 18, 18);
    } else if (hovered) {
      parent.ellipse(x, yPos, 20, 20);
    } else {   
      parent.ellipse(x, yPos, 16, 16);
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
  
  public void mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.PRESS) {
      pressed(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.RELEASE) {
      pressed = false;
    } else if (pressed && e.getAction() == MouseEvent.DRAG) {
      value = getValueFromX(e.getX());
    }
  }
  
  private void mouseOver(int xT, int yT) {
    int x = getXFromValue(value);
    if (PApplet.sqrt(PApplet.pow(xT - x, 2) + PApplet.pow(yT - yPos, 2)) < 8) {
      hovered = true;
    } else {
      hovered = false;
    }
  }
  
  private void pressed(int xT, int yT) {
    int x = getXFromValue(value);
    mouseOver(xT, yT);
    if (!pressed && hovered) {
      pressed = true;
    } // end of if
  }
  
  
  public void keyEvent(KeyEvent e) {
     //nothing yet
  }
  
  private int getXFromValue(int v) {
    return (int)(xPos + ((float)width / (maxValue - minValue)) * (v - minValue));
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
}