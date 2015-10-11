package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.ArrayList;

public abstract class GUIObject implements PConstants {
  protected int xPos, yPos, width, height;
  protected int oldX, oldY, oldX2, oldY2;
  protected PApplet parent;
  protected boolean resizable[] = {true, true, true, true};
  protected boolean keepPropotions = false;
  protected float propotion;      
  private ArrayList<Property> properties;
  protected ArrayList<OnPropertyChangedListener> onPropertyChangedListeners;
  private Property pxPos, pyPos, pwidth, pheight, presizable, pkeepPropotions;
  
  public GUIObject(PApplet p, int x, int y, int w, int h) {
    init();
    parent = p;
    xPos = oldX = x;
    pxPos = registerProperty("X", oldX);
    yPos = oldY = y;
    pyPos = registerProperty("Y", oldY);
    oldX2 = oldX + w;
    oldY2 = oldY + h;
    width = w;
    height = h;
    pwidth = registerProperty("Width", w);
    pheight = registerProperty("Height", h);
    propotion = (float)w / h;
    boolean[] b = {true, true, true, true};
    presizable = registerProperty("Resizable", b);
    pkeepPropotions = registerProperty("Keep Propotions", false);
  }
  
  public GUIObject(PApplet p, int x, int y) {
    this(p, x, y, 10, 10); 
  }
  
  private void init() {
    properties = new ArrayList<Property>();
    onPropertyChangedListeners = new ArrayList<OnPropertyChangedListener>(); 
    addOnPropertyChangedListener(new OnPropertyChangedListener() {
      public void onPropertyChanged(String tag, Object value) {
        if (tag.equals("X") || tag.equals("Y") || tag.equals("Width") || tag.equals("Height")) {
          onFixSize();
        } // end of if
      }
    });
  }
  
  public abstract void draw();
  public abstract boolean mouseEvent(MouseEvent e);
  public abstract void keyEvent(KeyEvent e);
  
  public Object getProptertyValue(String tag) {
    for (int i = 0; i < properties.size() ; i++ ) {
      if (properties.get(i).getTag().equals(tag)) {
        return properties.get(i).value;
      } // end of if
    } // end of for
    return null;
  }
  
  public ArrayList<Object> getPropertyValues() {
    ArrayList v = new ArrayList<Object>();
    for (int i = 0; i < properties.size() ; i++ ) {
      v.add(properties.get(i).value);
    } // end of for
    return v;
  }
  
  public ArrayList<String> getPropertyTags() {
    ArrayList v = new ArrayList<String>();
    for (int i = 0; i < properties.size() ; i++ ) {
      v.add(properties.get(i).getTag());
    } // end of for
    return v;
  }
  
  public Property registerProperty(String tag, Object o) {
    Property p = new Property(tag, o);
    properties.add(p);
    return p;
  }    
  
  public void setPropertyValue(String tag, Object v) {
    for (int i = 0; i < properties.size() ; i++ ) {
      if (properties.get(i).getTag().equals(tag)) {
        properties.get(i).value = v;   
        for (int j = 0; j < onPropertyChangedListeners.size(); j++) {
          onPropertyChangedListeners.get(j).onPropertyChanged(tag, v);
        } // end of for
      }
    } // end of if
  } // end of for
  
  public interface OnPropertyChangedListener {
    public void onPropertyChanged(String tag, Object value);
  }
  
  public void addOnPropertyChangedListener(OnPropertyChangedListener l) {
    onPropertyChangedListeners.add(l);
  }
  
  public int getPropertiesCount() {
    return properties.size();
  }
  
  public void onResize(float xFactor, float yFactor) {
    if (((boolean[])presizable.value)[0]) {
      pxPos.value = (int)(oldX * xFactor);
    } // end of if     
    if (((boolean[])presizable.value)[1]) {
      pyPos.value = (int)(oldY * yFactor);
    } // end of if
    if (((boolean[])presizable.value)[2]) {
      pwidth.value = (int)(oldX2 * xFactor) - (int)pxPos.value;
      if ((int)pwidth.value < 0) {
        pwidth.value = 0;
      } // end of if
    } // end of if
    if (((boolean[])presizable.value)[3]) {
      if ((boolean)pkeepPropotions.value) {
        if (((boolean[])presizable.value)[2]) {
          pheight.value = (int)((int)pwidth.value * (1/propotion));
          if ((int)pyPos.value + (int)pheight.value > parent.height) {
            pheight.value = parent.height - (int)pyPos.value; 
            pwidth.value = (int)((int)pheight.value * propotion);   
          } // end of if
        } else {
          pheight.value = (int)(oldY2 * yFactor) - (int)pyPos.value;
          pwidth.value = (int)((int)pheight.value * propotion);   
          if ((int)pxPos.value + (int)pwidth.value > parent.width) {
            pwidth.value = parent.width - (int)pxPos.value; 
            pheight.value = (int)((int)pwidth.value * (1/propotion));  
          } // end of if
        } // end of if-else
      } else {
        pheight.value = (int)(oldY2 * yFactor) - (int)pyPos.value;
      } // end of if-else
      if ((int)pheight.value < 0) {
        pheight.value = 0;
      } // end of if
    } // end of if
  }
  
  public void onFixSize() {
    oldX = (int)pxPos.value;
    oldX2 = (int)pxPos.value + (int)pwidth.value;
    oldY = (int)pyPos.value;
    oldY2 = (int)pyPos.value + (int)pheight.value;
    xPos = (int)pxPos.value;
    yPos = (int)pyPos.value;
    width = (int)pwidth.value;
    height = (int)pheight.value;
  }
  
  public void setResizable(boolean r) {
    setResizable(r, r, r, r, r);
  }
  
  public void setResizable(boolean r, boolean p) {
    setResizable(r, r, r, r, p);
  }
  
  public void setResizable(boolean r1, boolean r2, boolean p) {
    setResizable(r1, r2, r1, r2, p);
  }
  
  public void setResizable(boolean x, boolean y, boolean w, boolean h) {
    setResizable(x, y, w, h, false);
  }
  
  public void setResizable(boolean x, boolean y, boolean w, boolean h, boolean p) {
    boolean[] b = {x, y, w, h};
    presizable.value = b;
    resizable = b;
    pkeepPropotions.value = p;
    keepPropotions = p;
  }
  
  public int getX() {
    return (int)pxPos.value;
  }
  
  public int getY() {
    return (int)pyPos.value;
  }
  
  public void setX(int x) {
    pxPos.value = x;
    xPos = x;
    onFixSize();
  }
  
  public void setY(int y) {
    pyPos.value = y;    
    yPos = y; 
    onFixSize();
  }
  
  public int getWidth() {
    return (int)pwidth.value;
  }
  
  public int getHeight() {
    return (int)pheight.value;
  }
  
  public void setWidth(int w) {
    pwidth.value = w;   
    width = w;
    onFixSize();
  }
  
  public void setHeight(int h) {
    pheight.value = h;  
    height = h;
    onFixSize();
  }
  
  public class Property {
    
    private String tag;
    public Object value;
    
    public Property(String t, Object v) {
      tag = t;
      value = v;
    }
    
    public String getTag() {
      return tag;
    }
  }
  
}