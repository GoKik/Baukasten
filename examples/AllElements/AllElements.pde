import toolbox.*;

  Toolbox toolbox;
  Pencil pencil;
  Button button1, button2;
  Checkbox c1, c2, c3, c21, c22, c23;
  CheckboxGroup cG1;
  Slider sH, sM, sV;
  Textbox tBox;
  TabContainer tC;
  
  public void setup() {
    
    toolbox = new Toolbox(this, Toolbox.JAVA_MODE, 800, 480);
    
    pencil = new Pencil(this, width/2, 0, width/2, height);
    pencil.setBackgroundColor(color(240));
    pencil.snapToMouse(true);
    
    button1 = new Button(this, "Round Button", 20, 80, 80, 80);
    button1.setColor(color(255, 100, 100));
    button1.setBackgroundColor(color(100));
    button1.setStyle(Button.ROUND);
    
    button2 = new Button(this, "I'm a\nsquare", 120, 80, 80, 80);
    button2.setColor(color(255, 100, 100));
    button2.setBackgroundColor(color(100));
    button2.setStyle(Button.RECT);
    
    c1 = new Checkbox(this, "I'm round", 30, 240, false);
    c1.setStyle(Checkbox.CHECKBOX_ROUND);
    c1.setColor(color(255, 100, 100));
    
    c2 = new Checkbox(this, "I'm a square", 30, 280, true);
    c2.setStyle(Checkbox.CHECKBOX);
    c2.setColor(color(255, 100, 100));
    
    c3 = new Checkbox(this, "I'm a toggle-button", 30, 320, false);
    c3.setStyle(Checkbox.TOGGLE_BUTTON);
    c3.setColor(color(255, 100, 100));
    
    c21 = new Checkbox(this, "Child 1", 130, 240, false);
    c21.setStyle(Checkbox.CHECKBOX_ROUND);
    c21.setColor(color(255, 100, 100));
    
    c22 = new Checkbox(this, "Child 2", 130, 280, false);
    c22.setStyle(Checkbox.CHECKBOX_ROUND);
    c22.setColor(color(255, 100, 100));
    
    c23 = new Checkbox(this, "Child 3", 130, 320, false);
    c23.setStyle(Checkbox.CHECKBOX_ROUND);
    c23.setColor(color(255, 100, 100));
    
    cG1 = new CheckboxGroup(this);
    cG1.add(c21);
    cG1.add(c22);      
    cG1.add(c23);
    
    sH = new Slider(this, 40, 120, 300);
    sH.setColor(color(255, 100, 100));
    sH.setBackgroundColor(color(100));
    sH.setStopValue(0); 
    sH.setStartValue(100);
    
    sM = new Slider(this, 40, 160, 300);
    sM.setColor(color(255, 100, 100));
    sM.setBackgroundColor(color(100));
    sM.setStopValue(0.6f);
    sM.setStartValue(0.1f);
    sM.setStep(0.1f);
    sM.setMarkerStep(0.1f);
    sM.showMarkers(true);
    
    sV = new Slider(this, 370, 80, 370);
    sV.setColor(color(255, 100, 100));
    sV.setBackgroundColor(color(100));
    sV.setStyle(Slider.VERTICAL);
    
    tBox = new Textbox(this, 30, 80, 200, 30);
    
    tC = new TabContainer(this, 0, 0, 400, height, 40);
    tC.setColor(color(255));
    tC.setBackgroundColor(color(100));
    tC.setMenuColor(color(70));
    
    tC.newTab("Sliders");
    tC.addObject("Sliders", sH);
    tC.addObject("Sliders", sM);
    tC.addObject("Sliders", sV);
    
    tC.newTab("Buttons");
    tC.addObject("Buttons", button1);
    tC.addObject("Buttons", button2);
    
    tC.newTab("Text");
    tC.addObject("Text", tBox);    
    
    toolbox.add(tC);
    toolbox.add(pencil); 
  }
  
  public void draw() {
    
    if (button1.wasPressed()) {
      //do something
    }
    
    if (button2.wasPressed()) {
      //do something
    }
    
    if (sH.valueChanged()) {
      float v = sH.getValue();
      // do something
    }
    
    if (c1.isChecked()) {
      //do something
    }
    
    if (tBox.wasEnterPressed()) {
      //String str = tBox.getContent();
      //do something
    }
    
  }