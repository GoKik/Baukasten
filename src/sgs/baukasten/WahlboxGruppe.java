//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class WahlboxGruppe extends GUIObjekt implements PConstants {
  
  private ArrayList<Wahlbox> boxes;
  private int checkedBox = 0;
  
  public WahlboxGruppe(PApplet p) {
    super(p, 0, 0);
    boxes = new ArrayList();
  }
  
  public void fuegeEin(Wahlbox b) {
    boxes.add(b);
    if (boxes.size() == 1) {
      boxes.get(0).check();
    } // end of if
  }
  
  public void draw() {
    for (int i = 0; i < boxes.size(); i++) {
      boxes.get(i).draw();
    } // end of for
  }
  
  public void mouseEvent(MouseEvent e) {
    int b = -1;
    for (int i = 0; i < boxes.size(); i++) {
      boxes.get(i).mouseEvent(e);
      if (i != checkedBox && boxes.get(i).istGewaehlt()) {
        b = i;
      } // end of if
    } // end of for
    if (b != -1) {
      boxes.get(checkedBox).uncheck();
      boxes.get(b).check();
      checkedBox = b;
    }
  }
  
  public void keyEvent(KeyEvent e) {
    for (int i = 0; i < boxes.size(); i++) {
      boxes.get(i).keyEvent(e);
    } // end of for
  }
  
  
  
}