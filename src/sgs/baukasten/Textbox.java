//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Textbox extends GUIObjekt implements PConstants {
  
  private int breite, hoehe, col, bgCol;
  private String content = "";
  private String shownContent = "";
  private boolean hovered, pressed, focused = true;
  private int cursor = 0;
  
  public Textbox(PApplet p, int x, int y, int b, int h) {
    super(p, x, y);
    breite = b;
    hoehe = h;
    col = parent.color(0, 0, 0);
    bgCol = parent.color(255, 255, 255);
  }
  
  public void mouseEvent(MouseEvent e) {
    //nothing
  }
  
  public void keyEvent(KeyEvent e) {
    if (e.getAction() == KeyEvent.PRESS) {
      if (e.getKeyCode() == 37 && cursor > 0) {
        cursor--;
      } // end of if
      if (e.getKeyCode() == 39 && cursor < content.length()) {
        cursor++;
      } // end of if
    } // end of if
    if (focused && e.getAction() == KeyEvent.TYPE) {
      if (e.getKey() == ENTER) {
        focused = false;
      } else if (e.getKey() == BACKSPACE) {
        content = content.substring(0, content.length() - 1);
      } else if (e.getKey() == DELETE) {
        content = "";
      } else {
        content += e.getKey();
      } // end of if
    }
    }
    
    public void draw() {
      parent.textSize(20);
      parent.stroke(col);
      parent.fill(bgCol);
      parent.rect(xPos, yPos, breite, hoehe);
      parent.fill(col);
      parent.text(content, xPos, yPos, breite, hoehe);
      int x = xPos + (int)parent.textWidth(content.substring(0, cursor));
      parent.line(x, yPos - 20, x, yPos + 20);
    }
    
    public void setzeFarbe(int c) {
      col = c;
    }
    
    public void setzeHintergrundFarbe(int c) {
      bgCol = c;
    }
    
  }
