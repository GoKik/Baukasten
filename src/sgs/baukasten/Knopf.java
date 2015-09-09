//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Knopf extends GUIObjekt implements PConstants{
  
  public final static int RECT = 1;
  public final static int ROUND = 2;
  
  private String name;
  private int col, bgCol, disCol;
  private int breite, hoehe, textGroesse, stil;
  private boolean hovered, disabled, pressed, clicked;
  
  public Knopf(PApplet p, String n, int x, int y, int b, int h) {
    super(p, x, y);
    name = n;
    breite = b;
    hoehe = h;
    textGroesse = 15;
    while (parent.textWidth(n) > b - 10) { 
      textGroesse--;
      parent.textSize(textGroesse);
    } // end of while
    col = p.color(0, 0, 0);
    bgCol = p.color(255, 255, 255);
    disCol = p.color(30, 30, 30, 150);
  }
  
  public void setzeFarbe(int c) {
    col = c;
  }
  
  public void setzeHintergrundFarbe(int c) {
    bgCol = c;
  }

  
  public void setzeStil(int i) {
    if (i == RECT || i == ROUND) {
      stil = i;
      hoehe = breite;
    }
  }
  
  public void draw() {
    parent.textAlign(CENTER, CENTER);
    parent.strokeWeight(2);
    
    if (pressed) {
      parent.fill(col);
      parent.stroke(bgCol);
      drawBody(xPos, yPos, breite, hoehe);
      parent.fill(bgCol);
      parent.text(name, xPos + (breite / 2), yPos + (hoehe / 2));
    } else if (hovered) {
      parent.fill(col);
      parent.stroke(col);
      drawBody(xPos, yPos, breite, hoehe);
      parent.fill(bgCol);
      parent.text(name, xPos + (breite / 2), yPos + (hoehe / 2));
    } else {
      parent.fill(bgCol);
      parent.stroke(col);
      drawBody(xPos, yPos, breite, hoehe);
      parent.fill(col);
      parent.text(name, xPos + (breite / 2), yPos + (hoehe / 2));
    }
    
    if (disabled) {
      parent.fill(disCol);
      parent.stroke(disCol);
      drawBody(xPos, yPos, breite, hoehe);
      parent.fill(255, 255, 255);
      parent.textAlign(CENTER, CENTER);
      parent.text("DISABLED", xPos + (breite / 2), yPos + (hoehe / 2));
    }
  }
  
  private void drawBody(int x, int y, int b, int h) {
    if (stil == RECT) {
      parent.rect(x, y, b, h);
    } else if (stil == ROUND) {
      parent.ellipseMode(CORNER);
      parent.ellipse(x, y, b, h);
    }
  }
  
  public void mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.PRESS) {
      pressed(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.RELEASE) {
      clicked(e.getX(), e.getY());
    }
  }
  
  private void mouseOver(int x, int y) {
    if (stil == RECT) {
      if (x > xPos && x < xPos + breite && y > yPos && y < yPos + hoehe) {
        hovered = true;
      } else {
        hovered = false;
      }
    } else if (stil == ROUND) {
      if (parent.sqrt(parent.pow(x - (xPos + (breite/2)), 2) + parent.pow(y - (yPos + (breite/2)), 2)) < breite/2) {
        hovered = true;
      } else {
        hovered = false;
      }
    }
  }
  
  private void pressed(int x, int y) {
    if (disabled) {
      pressed = false;
      return;
    }
    mouseOver(x, y);
    if (hovered && parent.mousePressed) {
      hovered = false;
      pressed = true;
    } else {
      pressed = false;
    }
  }
  
  private void clicked(int x, int y) {
    if (disabled) {
      clicked = false;
      return;
    }
    mouseOver(x, y);
    if (hovered) {
      clicked = true;
    } else {
      clicked = false;
    }
    pressed = false;
  }
  
  public boolean wurdeGedrueckt() {
    if (clicked) {
      clicked = false;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isMouseOver() {
    return hovered;
  }
  
  public boolean istGedrueckt() {
    return pressed;
  }
  
  public void keyEvent(KeyEvent e) {
    //nothing
  }
  
  public boolean isEnabled() {
    return !disabled;
  }
  
  public void disable() {
    disabled = true;
    hovered = false;
    pressed = false;
  }
  
  public void enable() {
    disabled = false;
  }
  
}
  
    