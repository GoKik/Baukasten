import toolbox.*;

Toolbox toolbox;
TabContainer tC;
Button addButton, addSlider, addCheckbox, addTextbox, addTab;
ObjectContainer oC;

ArrayList<ObjectEditor> editObjects;

void setup() {

  toolbox = new Toolbox(this, Toolbox.JAVA_MODE, 1800, 900, false);
  toolbox.setBackgroundColor(color(255, 255, 255));
  editObjects = new ArrayList<ObjectEditor>();
  
  oC = new ObjectContainer(this, 0, 0, width, height);
  
  addButton = new Button(this, "add\nButton", 20, 20, 60, 60);
  addSlider = new Button(this, "add\nSlider", 20, 100, 60, 60);
  addCheckbox = new Button(this, "add\nCheckbox", 20, 180, 60, 60);
  addTextbox = new Button(this, "add\nTextbox", 20, 260, 60, 60);
  addTab = new Button(this, "add\nTag", 20, 340, 60, 60);

  tC = new TabContainer(this, 0, 0, 300, height, 40);
  tC.setColor(color(255));
  tC.setBackgroundColor(color(100));
  tC.setMenuColor(color(70));

  tC.newTab("Add");
  tC.addObject("Add", addButton);
  tC.addObject("Add", addSlider);
  tC.addObject("Add", addCheckbox);
  tC.addObject("Add", addTextbox);
  tC.addObject("Add", addTab);
  
  tC.newTab("Settings");

  toolbox.add(oC);
  toolbox.add(tC);
}

void draw() {
  if (addButton.wasPressed()) {
    Button b = new Button(this, " ", 310, 10, 60, 60);
    registerObject(b);
  }
  if (addSlider.wasPressed()) {
    Slider s = new Slider(this, 310, 10, 200);
    registerObject(s);
  }
  if (addCheckbox.wasPressed()) {
    Checkbox c = new Checkbox(this, " ", 310, 10, false);
    registerObject(c);
  }
  if (addTextbox.wasPressed()) {
    Textbox t = new Textbox(this, 310, 10, 150, 30);
    registerObject(t);
  }
  if  (addTab.wasPressed()) {
    TabContainer t = new TabContainer(this, 310, 10, 200, 400, 40);
    registerObject(t);
  }
  
  
}

void registerObject(GUIObject o) {
  editObjects.add(new ObjectEditor(this, o));
  oC.add(editObjects.get(editObjects.size() - 1));
  String tab = o.getClass().getSimpleName();
  tab += " " + String.valueOf(editObjects.size() - 1);
  tC.newTab(tab);
  if (o.getClass() == TabContainer.class) {
    tC.addObject(tab, new TabBuilder(this, 0, 0, tC.getWidth(), height - 40, (TabContainer)o));
  } else {
    tC.addObject(tab, new PropertiesList(this, 0, 0, tC.getWidth(), height - 40, o));
  }
}