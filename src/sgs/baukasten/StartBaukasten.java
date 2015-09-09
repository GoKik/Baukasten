//package sgs.baukasten;

import processing.core.*;     
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

public class StartBaukasten extends PApplet{
  
  Baukasten baukasten;
  Stift stift;
  Knopf kLoesche;
  Wahlbox w1, w2, w3, wT;
  WahlboxGruppe wG;
  Slider sR, sG, sB, sM;
  Textbox tBox;
  TabContainer tC;
  
  public void setup() {
    
    baukasten = new Baukasten(this, Baukasten.JAVA_MODE);
    
    stift = new Stift(this);
    stift.setzeBereich(width/2, 0, width, height);
    stift.setzeHintergrundFarbe(color(240));
    
    kLoesche = new Knopf(this, "Lösche", 20, 80, 60, 60);
    kLoesche.setzeFarbe(color(255, 100, 100));
    kLoesche.setzeHintergrundFarbe(color(255));
    kLoesche.setzeStil(Knopf.ROUND);
    
    w1 = new Wahlbox(this, "Test", 30, 240, false);
    w1.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w1.setzeFarbe(color(255, 100, 100));
    
    w2 = new Wahlbox(this, "Test", 30, 280, true);
    w2.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w2.setzeFarbe(color(255, 100, 100));
    
    w3 = new Wahlbox(this, "Test", 30, 320, false);
    w3.setzeStil(Wahlbox.CHECKBOX_ROUND);
    w3.setzeFarbe(color(255, 100, 100));
    
    wG = new WahlboxGruppe(this);
    wG.fuegeEin(w1);
    wG.fuegeEin(w2);      
    wG.fuegeEin(w3);
    
    wT = new Wahlbox(this, "Do sth.", 50, 300, false);
    wT.setzeFarbe(color(255, 100, 100));
    
    sR = new Slider(this, 40, 160, 300);
    sR.setColor(color(255, 100, 100));
    sR.setMaxValue(255); 
    sR.setBackgroundColor(color(100));
    
    sG = new Slider(this, 40, 190, 300);
    sG.setColor(color(255, 100, 100));
    sG.setMaxValue(255);  
    sG.setBackgroundColor(color(100));
    
    sB = new Slider(this, 40, 220, 300);
    sB.setColor(color(255, 100, 100));
    sB.setMaxValue(255);  
    sB.setBackgroundColor(color(100));
    
    sM = new Slider(this, 40, 360, 300);
    sM.setColor(color(255, 100, 100));
    sM.setMaxValue(600);
    sM.setMinValue(200);
    sM.setStep(20);
    sM.setMarkerStep(100);
    sM.showMarkers(true);
    sM.setBackgroundColor(color(100));
    
    tBox = new Textbox(this, 50, 150, 200, 30);
    
    tC = new TabContainer(this, 0, 0, 400, 480);
    tC.setColor(color(255));
    tC.setBackgroundColor(color(70));
    
    tC.newTab("Colors");
    tC.addObject("Colors", sR);
    tC.addObject("Colors", sG);
    tC.addObject("Colors", sB);
    tC.addObject("Colors", sM);
    
    tC.newTab("Buttons");
    tC.addObject("Buttons", kLoesche);
    tC.addObject("Buttons", wG);
    
    tC.newTab("Text");
    tC.addObject("Text", tBox);
    tC.addObject("Text", wT);
    
    baukasten.fuegeEin(tC);
    baukasten.fuegeEin(stift);
  }
  
  public void draw() {
    background(100);
    
    if (kLoesche.wurdeGedrueckt()) {
      stift.loescheZeichnung();
    }
    
    if (tC.getSelectedTab() == 0) {
      text(sM.getValue(), 370, 360);
    } // end of if
    
    stift.setzeFarbe(color(sR.getValue(), sG.getValue(), sB.getValue()));
    
    stift.bewegeBis(mouseX, mouseY);
  }
  
  public void mousePressed() {
    if (stift.istOben()) {
      stift.bewegeBis(mouseX, mouseY);
      stift.runter();
    }
  }
  
  public void mouseReleased() {
    stift.hoch();
  }  
  
  public void settings() {  
    size(800, 480);
    //orientation(LANDSCAPE);
  }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "StartBaukasten" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}//Klassenende