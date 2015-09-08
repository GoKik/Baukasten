//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Textbox extends GUIObjekt implements PConstants {
  
  private int breite, hoehe, col, bgCol, hlCol;
  private String content = "hallo";
  private boolean hovered, pressed, focused = true;
  private int cursor = 3, startPos = 2;
  
  public Textbox(PApplet p, int x, int y, int b, int h) {
    super(p, x, y);
    breite = b;
    hoehe = h;
    col = parent.color(0, 0, 0);
    bgCol = parent.color(230, 230, 230);
    hlCol = parent.color(255, 100, 100);
  }
  
  public void mouseEvent(MouseEvent e) {
    //nothing
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
      if (e.getKeyCode() == 39 && cursor < content.length()) {
        cursor++;
        while (parent.textWidth(content.substring(startPos, cursor)) > breite - 10) {  
          startPos++;
        } // end of if
      } // end of if
    } // end of if
    if (focused && e.getAction() == KeyEvent.TYPE) {
      if (e.getKey() == ENTER) {
        focused = false;
      } else if (e.getKey() == BACKSPACE) { 
        if (cursor > 0) {
          if (cursor < content.length()) {
            String a = content.substring(0, cursor - 1);
            String b = content.substring(cursor);
            content = a + b;
          } else {  
            content = content.substring(0, content.length() - 1);   
          } // end of if-else
          cursor--;
          if (cursor < startPos) {
            startPos--;
          } // end of if
        } // end of if
      } else if (e.getKey() == DELETE) {
        if (cursor < content.length()) {
          String a = content.substring(0, cursor);
          String b = content.substring(cursor + 1);
          content = a + b;
        }
      } else {
        String a = content;
        String b = "";
        if (cursor < content.length()) {    
          a = content.substring(0, cursor);
          b = content.substring(cursor);
        }
        content = a + e.getKey() + b;
        cursor++;
        while (parent.textWidth(content.substring(startPos, cursor)) > breite - 10) {
          startPos++;
        } 
      } // end of if
    }
  }
  
  public void draw() {
    parent.textSize(20);
    parent.textAlign(LEFT, CENTER);
    parent.noStroke();
    parent.fill(bgCol);
    parent.rect(xPos, yPos, breite, hoehe);
    parent.stroke(hlCol);
    parent.strokeWeight(2);
    parent.line(xPos, yPos - 2, xPos, yPos + hoehe + 2);
    parent.line(xPos + breite, yPos - 2, xPos + breite, yPos + hoehe + 2);
    parent.fill(col);
    parent.strokeWeight(1);
    int x = xPos + 5 + (int)parent.textWidth(content.substring(startPos, cursor));
    parent.text(content.substring(startPos), xPos + 5, yPos, breite - 10, hoehe);
    parent.line(x, yPos + 5, x, yPos + hoehe - 5);
  }
  
  public void setzeFarbe(int c) {
    col = c;
  }
  
  public void setzeHintergrundFarbe(int c) {
    bgCol = c;
  }
  
}
