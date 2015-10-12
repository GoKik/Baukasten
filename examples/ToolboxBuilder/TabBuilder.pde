

public class TabBuilder extends ObjectContainer {
  
  TabContainer container;
  TabContainer tabSettings;
  Button addTab;
  
  public TabBuilder(PApplet p, int x, int y, int w, int h, TabContainer c) {
    super(p, x, y, w, h);
    container = c;
    tabSettings = new TabContainer(p, x, y, w, h, 40);
    tabSettings.setColor(color(255));
    tabSettings.setBackgroundColor(color(100));
    tabSettings.setMenuColor(color(70));
    
    addTab = new Button(p, "Add\nTab", 20, 20, 60, 60);
    
    tabSettings.newTab("Add");
    tabSettings.addObject("Add", addTab);
    
    tabSettings.newTab("Settings");
    tabSettings.addObject("Settings", new PropertiesList(parent, 0, 0, width, height - 40, container));
    
    this.add(tabSettings);
    
  }
  
  public void draw() {
    super.draw();
    if (addTab.wasPressed()) {
      container.newTab(String.valueOf(tabSettings.getTabCount() - 1));
      tabSettings.newTab(String.valueOf(container.getTabCount()));
      tabSettings.addObject(String.valueOf(container.getTabCount()), new TabManager(parent, 0, 0, width, height - 40, String.valueOf(container.getTabCount()), container));
    }
  }
  
  public class TabManager extends ObjectContainer {
    
    String tab;
    TabContainer container;
    TabContainer tabSettings;
    ArrayList<TabContainer> tabObjects;
    Button addButton;
    
    ArrayList<ObjectEditor> objects;
    
    public TabManager(PApplet p, int x, int y, int w, int h, String t, TabContainer c) {
      super(p, x, y, w, h);
      tab = t;
      container = c;
      tabSettings = new TabContainer(p, x, y, w, h, 40);
      tabSettings.setColor(color(255));
      tabSettings.setBackgroundColor(color(100));
      tabSettings.setMenuColor(color(70));
      
      objects = new ArrayList<ObjectEditor>();
      
      addButton = new Button(p, "Add\nButton", 20, 20, 60, 60);
      
      tabSettings.newTab("Add");
      tabSettings.addObject("Add", addButton);
      
      tabSettings.newTab("Settings");
      
      this.add(tabSettings);
      
    }
    
    public void draw() {
      super.draw();
      if (addButton.wasPressed()) {
        addObject(new Button(parent, " ", 10, 10, 60, 60)); //<>//
        println("added Object");
      }
      
    }
    
    public void addObject(GUIObject o) {
      objects.add(new ObjectEditor(parent, o));
      println("created editor" + objects.size());
      container.addObject(tab, objects.get(objects.size() - 1));
      println("put in container");
      String t = o.getClass().getSimpleName();
      t += " " + String.valueOf(objects.size() - 1);
      tabSettings.newTab(t);
      println(t);
      if (o.getClass() == TabContainer.class) {
        tabSettings.addObject(t, new TabBuilder(parent, 0, 0, width, height - 40, (TabContainer)o));
      } else {
        println("just GUIObject");
        tabSettings.addObject(t, new PropertiesList(parent, 0, 0, width, height - 40, o));
      }
    }
  
  }
    
}