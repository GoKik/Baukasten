package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public class PropertiesManager {
  
  public static final String STRING = "strngTg";
  public static final String INT = "ntTg";
  public static final String BOOLEAN = "blnTg";
  public static final String FLOAT = "fltTg";
  
  private ArrayList<Property> properties;
  
  public PropertiesManager() {
    properties = new ArrayList<Property>();
  }
  
  public void addProperty(String tag, Object value) {
    properties.add(new Property(tag, value));
  }
  
  public int getCount() {
    return properties.size();
  }
  
  public Object getValue(String tag) {
    for (int i = 0; i < properties.size() ; i++ ) {
      if (properties.get(i).getTag().equals(tag)) {
        return properties.get(i).getValue();
      } // end of if
    } // end of for
  }
  
  public ArrayList<Object> getValueList() {
    ArrayList v = new ArrayList<Object>();
    for (int i = 0; i < properties.size() ; i++ ) {
      if (properties.get(i).getTag().equals(tag)) {
        v.add(properties.get(i).getValue());
      } // end of if
    } // end of for
    return v;
  } 
  
  public ArrayList<String> getTagList() {
    ArrayList v = new ArrayList<String>();
    for (int i = 0; i < properties.size() ; i++ ) {
      if (properties.get(i).getTag().equals(tag)) {
        v.add(properties.get(i).getTag());
      } // end of if
    } // end of for
    return v;
  } 
  
  
}