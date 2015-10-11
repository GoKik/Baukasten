package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Textbox extends GUIObject implements PConstants {
  
  private Property col, bgCol, hlCol, content;
  private boolean hovered, pressed, focused, enterPressed;
  private int cursor = 0, startPos = 0;
  
  public Textbox(PApplet p, int x, int y, int w, int h) {
    super(p, x, y, w, h);
    col = registerProperty("Color", parent.color(0, 0, 0));
    bgCol = registerProperty("Background Color", parent.color(230, 230, 230));
    hlCol = registerProperty("Highlight Color", parent.color(255, 100, 100));
    content = registerProperty("Content", "");
    addOnPropertyChangedListener(new OnPropertyChangedListener() {
      public void onPropertyChanged(String tag, Object v) {
        if (tag.equals("Content")) {
          setContent((String)v);
        } // end of if
      }
    });
  }
  
  public void setContent(String c) {
    content.value = c;
    cursor = 0;
    startPos = 0;
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void setHightlightColor(int c) {
    hlCol.value = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
  }
  
  public void keyEvent(KeyEvent e) {
    parent.textSize(20);
    if (e.getAction() == KeyEvent.PRESS) {
      if (e.getKeyCode() == 37 && cursor > 0) {
        cursor--;
        if (cursor < startPos) {
          startPos--;
        } // end of if
      } // end of if
      if (e.getKeyCode() == 39 && cursor < ((String)content.value).length()) {
        cursor++;
        while (parent.textWidth(((String)content.value).substring(startPos, cursor)) > width - 10) {  
          startPos++;
        } // end of if
      } // end of if
      if (e.getKeyCode() == 35) {
        cursor = ((String)content.value).length();
        while (parent.textWidth(((String)content.value).substring(startPos, cursor)) > width - 10) {  
          startPos++;
        } // end of if
      } // end of if
      if (e.getKeyCode() == 36) {
        cursor = 0;
        startPos = 0;
      } // end of if
    } // end of if
    if (focused && e.getAction() == KeyEvent.TYPE) {
      if (e.getKey() == ENTER) {
        focused = false;
        enterPressed = true;
      } else if (e.getKey() == BACKSPACE) { 
        if (cursor > 0) {
          if (cursor < ((String)content.value).length()) {
            String a = ((String)content.value).substring(0, cursor - 1);
            String b = ((String)content.value).substring(cursor);
            content.value = a + b;
          } else {  
            content.value = ((String)content.value).substring(0, ((String)content.value).length() - 1);   
          } // end of if-else
          cursor--;
          if (cursor < startPos) {
            startPos--;
          } // end of if
        } // end of if
      } else if (e.getKey() == DELETE) {
        if (cursor < ((String)content.value).length()) {
          String a = ((String)content.value).substring(0, cursor);
          String b = ((String)content.value).substring(cursor + 1);
          content.value = a + b;
        }
      } else {
        String a = ((String)content.value);
        String b = "";
        if (cursor < ((String)content.value).length()) {    
          a = ((String)content.value).substring(0, cursor);
          b = ((String)content.value).substring(cursor);
        }
        content.value = a + e.getKey() + b;
        cursor++;
        while (parent.textWidth(((String)content.value).substring(startPos, cursor)) > width - 10) {
          startPos++;
        } 
      } // end of if
    }
  }
  
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered) {
        return true;
      } // end of if
    } else if (e.getAction() == MouseEvent.PRESS) {
      if (hovered) {
        return true;
      } // end of if
    } else if (e.getAction() == MouseEvent.RELEASE) {
      clicked(e.getX(), e.getY());
      if (focused) {
        return true;
      } // end of if
    } else if (focused && e.getAction() == MouseEvent.WHEEL) {
      cursor += e.getCount();
      if (cursor < 0) {
        cursor = ((String)content.value).length();
      } // end of if
      if (cursor > ((String)content.value).length()) {
        cursor = 0;
      }
      while (cursor < startPos) {
        startPos--;
      } // end of if
      while (parent.textWidth(((String)content.value).substring(startPos, cursor)) > width - 10) {  
        startPos++;
      } // end of if
    }
    return false;
  }
  
  public void onResize(float xFactor, float yFactor) {
    super.onResize(xFactor, yFactor);
    while (parent.textWidth(((String)content.value).substring(startPos, cursor)) > width - 30) {  
      startPos++;
    } // end of if
  }
  
  private void mouseOver(int x, int y) {
    if (x > xPos && x < xPos + width && y > yPos && y < yPos + height) {
      parent.cursor(TEXT);
      hovered = true;
    } else {
      parent.cursor(ARROW);
      hovered = false;
    }
  }
  
  private void clicked(int x, int y) {
    mouseOver(x, y);
    if (hovered) {
      focused = true;
      int dX = x - 5 - xPos;
      int minD = 100;
      int ind = 0;
      parent.textSize(20);
      for (int i = 0; i <= ((String)content.value).substring(startPos).length() ;i++ ) {
        int d = (int)parent.abs(parent.textWidth(((String)content.value).substring(startPos, startPos + i)) - dX);
        if (d < minD) {
          minD = d;
          ind = i;
        } // end of if
      } // end of for
      cursor = startPos + ind;
    } else {
      focused = false;
    } // end of if-else
  }
  
  public boolean wasEnterPressed() {
    if (enterPressed) {
      enterPressed = false;
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  public void draw() {
    parent.textSize(20);
    parent.textAlign(LEFT, CENTER);
    parent.noStroke();
    parent.fill((int)bgCol.value);
    parent.rect(xPos, yPos, width, height);
    parent.stroke((int)hlCol.value);
    parent.strokeWeight(2);
    parent.line(xPos, yPos - 2, xPos, yPos + height + 2);
    parent.line(xPos + width, yPos - 2, xPos + width, yPos + height + 2);
    parent.fill((int)col.value);
    parent.strokeWeight(1);
    int x = xPos + 5 + (int)parent.textWidth(((String)content.value).substring(startPos, cursor));
    parent.text(((String)content.value).substring(startPos), xPos + 5, yPos, width - 10, height);
    if (focused && parent.millis() % 800 < 300) {
      parent.line(x, yPos + 5, x, yPos + height - 5);
    } // end of if
    
  }
  
  public String getContent() {
    return (String)content.value;
  }
  
}
