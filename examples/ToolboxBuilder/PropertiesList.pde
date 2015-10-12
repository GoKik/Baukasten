import toolbox.GUIObject.PropertyListener;

public class PropertiesList extends GUIObject {
  
  ArrayList<String> tags;
  ArrayList<Object> values;
  ArrayList<Textbox> boxes;
  ArrayList<String> types;
  GUIObject object;
  
  public PropertiesList(PApplet p, int x, int y, int w, int h, GUIObject o) {
    super(p, x, y, w, h);
    object = o;
    tags = o.getPropertyTags();
    values = o.getPropertyValues();
    o.addPropertyListener(new PropertyListener() {
      public void onPropertyChanged(String tag, Object value) {
        values.set(tags.indexOf(tag), value);
        boxes.get(tags.indexOf(tag)).setContent(value.toString());
        //println("Property - " + tag + ": " + value.toString());
      }
    });
    types = new ArrayList<String>();
    boxes = new ArrayList<Textbox>();
    for (int i = 0; i < tags.size(); i++) {
      boxes.add(new Textbox(parent, xPos + 10 + (width/2), yPos + 20 + (i * 30), (width/2) - 20, 25));
      types.add("");
    }
    for (int i = 0; i < tags.size(); i++) {
      String s = "";
      if (values.get(i).getClass() == String.class) {
        s = (String)values.get(i);
        types.set(i, "STRING");
      } else if (values.get(i).getClass() == Integer.class) {
        if (tags.get(i).toLowerCase().contains("color")) {
          s = String.valueOf(((color)values.get(i)) >> 16 & 0xFF);
          s += ", ";
          s += String.valueOf(((color)values.get(i)) >> 8 & 0xFF);
          s += ", ";
          s += String.valueOf(((color)values.get(i)) & 0xFF);
          types.set(i, "COLOR");
        } else {
          s = String.valueOf((int)values.get(i));
          types.set(i, "INT");
        }
      } else if (values.get(i).getClass() == Float.class) {
        s = String.valueOf((float)values.get(i));
        types.set(i, "FLOAT");
      } else if (values.get(i).getClass() == Boolean.class) {
        if ((boolean)values.get(i)) {
          s = "true";
        } else {
          s = "false";
        }
        types.set(i, "BOOLEAN");
      } 
      boxes.get(i).setContent(s);
    }
  }
  
  public void draw() {
    for (int i = 0; i < tags.size(); i++) {
      boxes.get(i).draw();
    }
    for (int i = 0; i < tags.size(); i++) {
      if (boxes.get(i).wasEnterPressed()) {
        Object value = boxes.get(i).getContent();
        if (types.get(i).equals("INT")) value = Integer.parseInt((String)value);
        if (types.get(i).equals("FLOAT")) value = Float.parseFloat((String)value);
        if (types.get(i).equals("BOOLEAN")) {
          if (((String)value).equals("true")) {
            value = true; 
          } else {
            value = false;
          }
        }
        if (types.get(i).equals("COLOR")) {
          String r = ((String)value).substring(0, 3); //<>//
          String g = ((String)value).substring(5, 8);
          String b = ((String)value).substring(10, 13);
          value = color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)); 
        }
        object.setProperty(tags.get(i), value); //<>//
      }
    }
    textSize(15);
    fill(255);
    textAlign(LEFT, CENTER);
    for (int i = 0; i < tags.size(); i++) {
      text(tags.get(i), xPos + 10, yPos + 30 + (i * 30));
    }
  }
  
  public boolean mouseEvent(MouseEvent e) {
    for (int i = 0; i < tags.size(); i++) {
      boxes.get(i).mouseEvent(e);
    }
    return false;
  }
  
  public void keyEvent(KeyEvent e) {
    for (int i = 0; i < tags.size(); i++) {
      boxes.get(i).keyEvent(e);
    }
  }
}