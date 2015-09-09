//package sgs.toolbox;

import processing.core.*;     
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

public class StartToolbox extends PApplet{
  
  Toolbox toolbox;
  Pencil pencil;
  Button btnClear;
  Checkbox c1, c2, c3, cT;
  CheckboxGroup cG;
  Slider sR, sG, sB, sM;
  Textbox tBox;
  TabContainer tC;
  
  public void setup() {
    
    toolbox = new Toolbox(this, Toolbox.JAVA_MODE);
    
    pencil = new Pencil(this);
    pencil.setBounds(width/2, 0, width, height);
    pencil.setBackgroundColor(color(240));
    
    btnClear = new Button(this, "Lösche", 20, 80, 60, 60);
    btnClear.setColor(color(255, 100, 100));
    btnClear.setBackgroundColor(color(255));
    btnClear.setStyle(Button.ROUND);
    
    c1 = new Checkbox(this, "Test", 30, 240, false);
    c1.setStyle(Checkbox.CHECKBOX_ROUND);
    c1.setColor(color(255, 100, 100));
    
    c2 = new Checkbox(this, "Test", 30, 280, true);
    c2.setStyle(Checkbox.CHECKBOX_ROUND);
    c2.setColor(color(255, 100, 100));
    
    c3 = new Checkbox(this, "Test", 30, 320, false);
    c3.setStyle(Checkbox.CHECKBOX_ROUND);
    c3.setColor(color(255, 100, 100));
    
    cG = new CheckboxGroup(this);
    cG.add(c1);
    cG.add(c2);      
    cG.add(c3);
    
    cT = new Checkbox(this, "Do sth.", 50, 300, false);
    cT.setColor(color(255, 100, 100));
    
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
    tC.addObject("Buttons", btnClear);
    tC.addObject("Buttons", cG);
    
    tC.newTab("Text");
    tC.addObject("Text", tBox);
    tC.addObject("Text", cT);
    
    toolbox.add(tC);
    toolbox.add(pencil);
  }
  
  public void draw() {
    background(100);
    
    if (btnClear.wasPressed()) {
      pencil.clearDrawing();
    }
    
    if (tC.getSelectedTab() == 0) {
      text(sM.getValue(), 370, 360);
    } // end of if
    
    pencil.setColor(color(sR.getValue(), sG.getValue(), sB.getValue()));
    
    pencil.moveTo(mouseX, mouseY);
  }
  
  public void mousePressed() {
    if (pencil.isUp()) {
      pencil.moveTo(mouseX, mouseY);
      pencil.down();
    }
  }
  
  public void mouseReleased() {
    pencil.up();
  }  
  
  public void settings() {  
    size(800, 480);
    //orientation(LANDSCAPE);
  }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "StartToolbox" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}//Klassenende