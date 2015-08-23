package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class Stift extends GUIObjekt implements PConstants {
  
  private boolean runter, fillBG;
  private int altX, altY, minX, minY, maxX, maxY;
  private float winkel = 0;
  private ArrayList<Strecke> zeichnung = new ArrayList<Strecke>();
  private int sColor, bgColor;
  private int breite = 1;
  
  public Stift(PApplet p) {
    super(p, 0, 0);
    sColor = parent.color(0, 0, 0);
    minX = 0;
    maxX = parent.width;
    minY = 0;
    maxY = parent.height;
  }
  
  public void mouseEvent(MouseEvent e) {
    //nothing
  }
  
  public void keyEvent(KeyEvent e) {
    //nothing
  }
  
  public void draw() {
    if (fillBG) {
      parent.noStroke();
      parent.fill(bgColor);
      parent.rect(minX, minY, maxX - minX, maxY - minY);  
    } // end of if
    parent.stroke(sColor);
    parent.strokeWeight(breite);
    for (int i = 0; i < zeichnung.size(); i++) {
      zeichnung.get(i).draw();
    }
  }
  
  public void setzeBereich(int x1, int y1, int x2, int y2) {
    minX = x1;
    minY = y1;
    maxX = x2;
    maxY = y2;
  }
  
  public void runter() {
    runter = true;
  }
  
  public void hoch() {
    runter = false;
  }
  
  public void bewegeBis(int x, int y) {
    altX = xPos;
    altY = yPos;
    xPos = x;
    yPos = y;
    if (runter && checkBorders()) {
      zeichnung.add(new Strecke(altX, altY, xPos, yPos));   
    }
  }
  
  private boolean checkBorders() {
    float xNeu, yNeu;
    if ((xPos <= minX || xPos >= maxX || yPos <= minY || yPos >= maxY)
    && (altX <= minX || altX >= maxX || altY <= minY || altY >= maxY)) {
      return false; 
    }
    if (!(xPos < minX && altX < minX)) {
      if (xPos < minX) {
        yNeu = (parent.parseFloat(minX - altX) / parent.parseFloat(xPos - altX)) * (yPos - altY) + altY;
        xNeu = minX;
        xPos = (int)xNeu;
        yPos = (int)yNeu;
        return true;
      } else if (altX < minX) {
        yNeu = (parent.parseFloat(minX - xPos) / parent.parseFloat(altX - xPos)) * (altY - yPos) + yPos;
        xNeu = minX;
        altX = (int)xNeu;
        altY = (int)yNeu;
        return true;
      }
    } else {
      return false; 
    }  
    if (!(xPos > maxX && altX > maxX)) {
      if (xPos > maxX) {
        yNeu = (parent.parseFloat(maxX - altX) / parent.parseFloat(xPos - altX)) * (yPos - altY) + altY; 
        xNeu = maxX;
        xPos = (int)xNeu;
        yPos = (int)yNeu;
        return true;
      } else if (altX > maxX) {
        yNeu = (parent.parseFloat(maxX - xPos) / parent.parseFloat(altX - xPos)) * (altY - yPos) + yPos; 
        xNeu = maxX;
        altX = (int)xNeu;
        altY = (int)yNeu;
        return true;
      } 
    } else {
      return false;
    }
    if (!(yPos < minY && altY < minY)) {
      if (yPos < minY) {
        xNeu = (parent.parseFloat(minY - altY) / (yPos - altY)) * (xPos - altX) + altX;
        yNeu = minY;
        xPos = (int)xNeu;
        yPos = (int)yNeu;
        return true;
      } else if (altY < minY) {
        xNeu = (parent.parseFloat(minY - yPos) / (altY - yPos)) * (altX - xPos) + xPos;
        yNeu = minY;
        altX = (int)xNeu;
        altY = (int)yNeu;
        return true;
      } 
    } else {
      return false;
    }
    if (!(yPos > maxY && altY > maxY)) {
      if (yPos > maxY) {
        xNeu = (parent.parseFloat(maxY - altY) / (yPos - altY)) * (xPos - altX) + altX;
        yNeu = maxY;
        xPos = (int)xNeu;
        yPos = (int)yNeu;
        return true;
      } else if (altY > maxY) {
        xNeu = (parent.parseFloat(maxY - yPos) / (altY - yPos)) * (altX - xPos) + xPos;
        yNeu = maxY;
        altX = (int)xNeu;
        altY = (int)yNeu;
        return true;
      } 
    } else {
      return false;
    }
    return true;
    
  }
  
  public void bewegeUm(int x, int y) {
    bewegeBis(xPos + x, yPos + y);
  }
  
  public void bewegeUm(int r) {
    bewegeBis(xPos + PApplet.parseInt(r * PApplet.cos(winkel)), yPos + PApplet.parseInt(r * PApplet.sin(winkel)));
  }
  
  public void dreheUm(int w) {
    winkel += w;
    winkel %= 360;
  }
  
  public void dreheBis(int w) {
    winkel = w;
    winkel %= 360;
  }
  
  public void setzeFarbe(int c) {
    sColor = c;
  }
  
  public void setzeHintergrundFarbe(int c) {
    bgColor = c;
    fillBG = true;
  }
  
  public void setzeLinienBreite(int b) {
    breite = b;
  }
  
  public boolean istOben() {
    return !runter;
  }
  
  public int getFarbe() {
    return sColor;
  }
  
  public void loescheZeichnung() {
    zeichnung.clear();
  }
  
  private class Strecke {
    
    private int xA, yA, xB, yB;
    
    public Strecke(int xa, int ya, int xb, int yb) {
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
  