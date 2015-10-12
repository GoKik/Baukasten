import toolbox.GUIObject.PropertyListener;

public class PropertiesList extends GUIObject {
  
  ArrayList<String> tags;
  ArrayList<Object> values;
  ArrayList<GUIObject> fields;
  ArrayList<String> types;
  GUIObject object;
  private int spacing = 40;
  
  public PropertiesList(PApplet p, int x, int y, int w, int h, GUIObject o) {
    super(p, x, y, w, h);
    object = o;
    tags = o.getPropertyTags();
    values = o.getPropertyValues();
    o.addPropertyListener(new PropertyListener() {
      public void onPropertyChanged(String tag, Object value) {
        values.set(tags.indexOf(tag), value);
        changeProperty(tag, value);
      }
    });
    types = new ArrayList<String>();
    fields = new ArrayList<GUIObject>();
    for (int i = 0; i < tags.size(); i++) {
      String s = "";
      if (values.get(i).getClass() == String.class) {
        s = (String)values.get(i);
        fields.add(new Textbox(parent, xPos + 10 + (width/2), yPos + 20 + (i * spacing), (width/2) - 20, 25));
        ((Textbox)fields.get(i)).setContent(s);
        types.add("STRING");
      } else if (values.get(i).getClass() == Integer.class) {
        if (tags.get(i).toLowerCase().contains("color")) {
          types.add("COLOR");
          fields.add(new ColorPicker(parent, xPos + 10 + (width/2), yPos + 20 + (i * spacing), 30, 30));
          ((ColorPicker)fields.get(i)).setColor((int)values.get(i));
        } else {
          s = String.valueOf((int)values.get(i));
          types.add("INT");
          fields.add(new Textbox(parent, xPos + 10 + (width/2), yPos + 20 + (i * spacing), (width/2) - 20, 25));
          ((Textbox)fields.get(i)).setContent(s);
        }
      } else if (values.get(i).getClass() == Float.class) {
        s = String.valueOf((float)values.get(i));
        types.add("FLOAT");
        fields.add(new Textbox(parent, xPos + 10 + (width/2), yPos + 20 + (i * spacing), (width/2) - 20, 25));
        ((Textbox)fields.get(i)).setContent(s);
      } else if (values.get(i).getClass() == Boolean.class) {
        types.add("BOOLEAN");
        fields.add(new Checkbox(parent, " ", xPos + 10 + (width/2), yPos + 20 + (i * spacing), (boolean)values.get(i)));
        ((Checkbox)fields.get(fields.size() - 1)).setStyle(Checkbox.CHECKBOX);
      } 
    }
  }
  
  public void draw() {
    textSize(15);
    fill(255);
    textAlign(LEFT, CENTER);
    for (int i = 0; i < tags.size(); i++) {
      text(tags.get(i), xPos + 10, yPos + 30 + (i * spacing));
    }
    for (int i = 0; i < tags.size(); i++) {
      fields.get(i).draw();
      if (fields.get(i).getClass() == Textbox.class) {
        if (((Textbox)fields.get(i)).wasEnterPressed()) {
          Object value = ((Textbox)fields.get(i)).getContent();
          if (types.get(i).equals("INT")) value = Integer.parseInt((String)value);
          if (types.get(i).equals("FLOAT")) value = Float.parseFloat((String)value);
          object.setProperty(tags.get(i), value);
        }
      } else if (fields.get(i).getClass() == ColorPicker.class) {
        if (((ColorPicker)fields.get(i)).hasColorChanged()) {
          object.setProperty(tags.get(i), ((ColorPicker)fields.get(i)).getColor());
        }
      } else if (fields.get(i).getClass() == Checkbox.class) {
        if (((Checkbox)fields.get(i)).isChecked() != (boolean)values.get(i)) {
          object.setProperty(tags.get(i), ((Checkbox)fields.get(i)).isChecked());
        }
      } //<>//
    }
    for (int i = 0; i < tags.size(); i++) {
      if (fields.get(i).getClass() == ColorPicker.class && ((ColorPicker)fields.get(i)).isOpened()) {
        fields.get(i).draw();
      }
    }
  }
  
  public void changeProperty(String tag, Object value) {
    int index = tags.indexOf(tag);
    if (fields.get(index).getClass() == Textbox.class) {
      if (types.get(index).equals("INT")) value = String.valueOf((int)value);
      if (types.get(index).equals("FLOAT")) value = String.valueOf((int)value);
      ((Textbox)fields.get(index)).setContent((String)value);
    } else if (fields.get(index).getClass() == ColorPicker.class) {
      ((ColorPicker)fields.get(index)).setColor((int)value);
    } else if (fields.get(index).getClass() == Checkbox.class) {
      if ((boolean)value) {
        ((Checkbox)fields.get(index)).check();
      } else {
        ((Checkbox)fields.get(index)).uncheck();
      }
    }
  }
  
  public boolean mouseEvent(MouseEvent e) {
    for (int i = 0; i < tags.size(); i++) {
      if (fields.get(i).mouseEvent(e)) {
        return true;
      }
    }
    return false;
  }
  
  public void keyEvent(KeyEvent e) {
    for (int i = 0; i < tags.size(); i++) {
      fields.get(i).keyEvent(e);
    }
  }
}