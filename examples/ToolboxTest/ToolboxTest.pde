import toolbox.*;

  Toolbox toolbox;
  Pencil pencil;
  Button btnClear, btnRect;
  Checkbox c1, c2, c3, c21, c22, c23, c31, c32, c33;
  CheckboxGroup cG1, cG2, cG3;
  Slider sR, sG, sB, sM, sV;
  Textbox tBox;
  TabContainer tC;
  
  public void setup() {
    
    toolbox = new Toolbox(this, Toolbox.JAVA_MODE, 800, 480, true);
    
    pencil = new Pencil(this, width/2, 0, width/2, height);
    pencil.setBackgroundColor(color(240));
    pencil.snapToMouse(true);
    pencil.setResizable(false, false, true, true, false);
    
    btnClear = new Button(this, "Lösche", 20, 80, 80, 80);
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
    
    c21 = new Checkbox(this, "Test", 130, 240, false);
    c21.setStyle(Checkbox.CHECKBOX);
    c21.setColor(color(255, 100, 100));
    
    c22 = new Checkbox(this, "Test", 130, 280, false);
    c22.setStyle(Checkbox.CHECKBOX);
    c22.setColor(color(255, 100, 100));
    
    c23 = new Checkbox(this, "Test", 130, 320, false);
    c23.setStyle(Checkbox.CHECKBOX);
    c23.setColor(color(255, 100, 100));
    
    c31 = new Checkbox(this, "Test", 230, 240, false);
    c31.setStyle(Checkbox.TOGGLE_BUTTON);
    c31.setColor(color(255, 100, 100));
    
    c32 = new Checkbox(this, "Test", 230, 280, false);
    c32.setStyle(Checkbox.TOGGLE_BUTTON);
    c32.setColor(color(255, 100, 100));
    
    c33 = new Checkbox(this, "Test", 230, 320, false);
    c33.setStyle(Checkbox.TOGGLE_BUTTON);
    c33.setColor(color(255, 100, 100));
    
    cG1 = new CheckboxGroup(this);
    cG1.add(c1);
    cG1.add(c2);      
    cG1.add(c3);
    
    cG2 = new CheckboxGroup(this);
    cG2.add(c21);
    cG2.add(c22);      
    cG2.add(c23);
    
    cG3 = new CheckboxGroup(this);
    cG3.add(c31);
    cG3.add(c32);      
    cG3.add(c33);
    
    sR = new Slider(this, 40, 120, 300);
    sR.setColor(color(255, 100, 100));
    sR.setStopValue(0); 
    sR.setStartValue(255);
    sR.setBackgroundColor(color(100));
    
    sG = new Slider(this, 40, 150, 300);
    sG.setColor(color(255, 100, 100));
    sG.setStopValue(255);  
    sG.setBackgroundColor(color(100));
    
    sB = new Slider(this, 40, 180, 300);
    sB.setColor(color(255, 100, 100));
    sB.setStopValue(255);  
    sB.setBackgroundColor(color(100));
    
    sM = new Slider(this, 40, 360, 300);
    sM.setColor(color(255, 100, 100));
    sM.setStopValue(0.6f);
    sM.setStartValue(0.1f);
    sM.setStep(0.1f);
    sM.setMarkerStep(0.1f);
    sM.showMarkers(true);
    sM.setBackgroundColor(color(100));
    
    sV = new Slider(this, 370, 80, 370);
    sV.setColor(color(255, 100, 100));
    sV.setStyle(Slider.VERTICAL);
    sV.setBackgroundColor(color(100));
    
    tBox = new Textbox(this, 50, 150, 200, 30);
    
    tC = new TabContainer(this, 0, 0, 400, height, 40);
    tC.setColor(color(255));
    tC.setBackgroundColor(color(100));
    tC.setMenuColor(color(70));
    
    tC.newTab("Colors");
    tC.addObject("Colors", sR);
    tC.addObject("Colors", sG);
    tC.addObject("Colors", sB);
    tC.addObject("Colors", sM);
    
    tC.newTab("Buttons");
    tC.addObject("Buttons", btnClear);
    tC.addObject("Buttons", cG1);
    tC.addObject("Buttons", cG2);
    tC.addObject("Buttons", cG3);
    
    tC.newTab("Text");
    tC.addObject("Text", tBox);
    tC.addObject("Text", sV); 
    
    tC.setResizable(false, true, false);
    btnClear.setResizable(false);
    cG1.setResizable(false);
    cG2.setResizable(false);
    cG3.setResizable(false);
    
    
    tC.setTabDrawer(new TabContainer.TabDrawer() {
      public void drawTab(int t, int x, int y, int w, int h) {
        fill(255);
        textSize(12);
        if (t == 0) {
          text(sM.getValue(), x + (int)(0.8 * w), y + (int)(0.8 * h));
        } // end of if
      }
    });
    
    sV.setResizable(false, false, false, true, false);
    
    toolbox.add(tC);
    toolbox.add(pencil); 
  }
  
  public void draw() {
    background(100);
    
    if (btnClear.wasPressed()) {
      pencil.clearDrawing();
    }
    
    
    pencil.setColor(color(sR.getValue(), sG.getValue(), sB.getValue()));
  }