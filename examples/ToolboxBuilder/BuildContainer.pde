

public class BuildContainer extends ObjectContainer {
  
  GUIContainer container;
  TabContainer tabObjects;
  Button addButton;
  
  public BuildContainer(PApplet p, int x, int y, int w, int h, GUIContainer c) {
    super(p, x, y, w, h);
    container = c;
    tabObjects = new TabContainer(p, x, y, w, h, 40);
    tabObjects.setColor(color(255));
    tabObjects.setBackgroundColor(color(100));
    tabObjects.setMenuColor(color(70));
    
    addButton = new Button(p, "Add\nButton", 20, 20, 60, 60);
    
    tabObjects.newTab("Add");
    tabObjects.addObject("Add", addButton);
    
    tabObjects.newTab("Settings");
    tabObjects.addObject("Settings", new PropertiesList(parent, 0, 0, width, height - 40, container));
    
    this.add(tabObjects);
    
  }
  
}