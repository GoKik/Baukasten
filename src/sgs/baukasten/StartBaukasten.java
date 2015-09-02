//package sgs.baukasten;

import processing.core.*;     
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

public class StartBaukasten extends PApplet{
  
  Baukasten baukasten;
  Stift stift, buntstift;
  Knopf kLoesche;
  Wahlbox w1, w2, w3, w4, w5;
  WahlboxGruppe wG;
  Slider slider;
  Textbox tBox;
  
  public void setup() {
    
    baukasten = new Baukasten(this, Baukasten.JAVA_MODE);
    stift = new Stift(this);
    stift.setzeBereich(0, height/2, width/2, height);
    buntstift = new Stift(this);
    buntstift.setzeBereich(width/2, height/2, width, height);
    buntstift.setzeFarbe(color(255, 100, 100));
    kLoesche = new Knopf(this, "Lösche", 10, 10, 80, 80);
    kLoesche.setzeFarbe(color(255, 100, 100));
    kLoesche.setzeHintergrundFarbe(color(255, 255, 255));
    kLoesche.setzeStil(Knopf.ROUND);
    w1 = new Wahlbox(this, "Test", 100, 10, true);
    w1.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w1.setzeFarbe(color(255, 100, 100));
    w2 = new Wahlbox(this, "Test", 150, 10, true);
    w2.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w2.setzeFarbe(color(255, 100, 100));
    
    w3 = new Wahlbox(this, "Test", 200, 10, true);
    w3.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w3.setzeFarbe(color(255, 100, 100));
    
    w4 = new Wahlbox(this, "Test", 250, 10, true);
    w4.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w4.setzeFarbe(color(255, 100, 100));
    wG = new WahlboxGruppe(this);
    wG.fuegeEin(w1);
    wG.fuegeEin(w2);      
    wG.fuegeEin(w3);
    wG.fuegeEin(w4);
    
    slider = new Slider(this, 100, 60, 300);
    slider.setColor(color(255, 100, 100));
    slider.setMaxValue(255);
    
    tBox = new Textbox(this, 20, 150, 100, 40);
    
    baukasten.fuegeEin(tBox);
    baukasten.fuegeEin(slider);
    baukasten.fuegeEin(wG);
    baukasten.fuegeEin(stift);
    baukasten.fuegeEin(buntstift);
    baukasten.fuegeEin(kLoesche);
  }
  
  public void draw() {
    background(255);
    
    strokeWeight(1);
    stroke(255, 100, 100);
    line(0, 100, width, 100);
    line(0, height/2, width, height/2);
    if (kLoesche.wurdeGedrueckt()) {
      stift.loescheZeichnung();
      buntstift.loescheZeichnung();
    }
    if (!w1.istGewaehlt()) {
      stift.setzeFarbe(color(255, 255, 255));
    } else {
      stift.setzeFarbe(color(0, 0, 0));
    }
    buntstift.setzeFarbe(color(slider.getValue(), 255 - slider.getValue(), 0));
    
    stift.bewegeBis(mouseX, mouseY);
    buntstift.bewegeBis(mouseX, mouseY);
  }
  
  public void mousePressed() {
    if (stift.istOben()) {
      stift.bewegeBis(mouseX, mouseY);
      stift.runter();
    }
    if (buntstift.istOben()) {
      buntstift.bewegeBis(mouseX, mouseY);
      buntstift.runter();
    }
  }
  
  public void mouseReleased() {
    stift.hoch();
    buntstift.hoch();
  }  
  
  public void settings() {  size(480, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "StartBaukasten" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}//Klassenende