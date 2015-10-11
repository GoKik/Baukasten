package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class TabContainer extends GUIContainer implements PConstants {
  
  private Property col, bgCol, mCol, menuHeight, selectedTab;
  private int hovered = -1, clicked = -1;
  
  
  public TabContainer(PApplet p, int x, int y, int w, int h, int hM) {
    super(p, x, y, w, h);
    menuHeight = registerProperty("Menu Height", hM);
    col = registerProperty("Color", parent.color(255, 255, 255));
    bgCol = registerProperty("Background Color", parent.color(100, 100, 100));
    mCol = registerProperty("Menu Color", parent.color(70, 70, 70));     
    selectedTab = registerProperty("Selected Tab", 0);
  }
  
  public void setColor(int c) {
    col.value = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol.value = c;
  }
  
  public void setMenuColor(int c) {
    mCol.value = c;
  }
  
  public void newTab(String n) {
    objects.add(new Tab(parent, n));
  }
  
  public void removeTab(String n) {
    for (int i = 0; i < objects.size(); i++) {
      if (((String)((TabContainer.Tab)objects.get(i)).getName()).equals(n)) {
        objects.remove(i);
        break;
      } // end of if
    } // end of for
  }
  
  public int getSelectedTab() {
    return (int)selectedTab.value;
  }
  
  public void chooseTab(int i) {
    selectedTab.value = i;
    if ((int)selectedTab.value < 0) {
      selectedTab.value = objects.size() - 1;
    } // end of if
    if ((int)selectedTab.value >= objects.size()) {
      selectedTab.value = 0;
    } // end of if
  }
  
  public void addObject(String n, GUIObject o) {
    for (int i = 0; i < objects.size(); i++) {
      if (((String)((TabContainer.Tab)objects.get(i)).getName()).equals(n)) {
        ((TabContainer.Tab)objects.get(i)).addObject(o);
        break;
      } // end of if
    } // end of for
  }
  
  public void draw() {
    parent.noStroke();
    parent.fill((int)bgCol.value);
    parent.rect(xPos, yPos + (int)menuHeight.value, width, height - (int)menuHeight.value); 
    if (onDrawListener != null) {                 
      onDrawListener.draw(xPos, yPos, width, height);
    }
    parent.translate(xPos, yPos + (int)menuHeight.value);
    if ((int)selectedTab.value < objects.size()) {
      objects.get((int)selectedTab.value).draw();
    } // end of if
    parent.translate(-xPos, -yPos - (int)menuHeight.value); 
    parent.noStroke();
    parent.fill((int)mCol.value);
    parent.rect(xPos, yPos, width, (int)menuHeight.value);
    parent.textAlign(CENTER, CENTER);
    int tabW = 70;
    int menuW = objects.size() * tabW;
    if (menuW > width) {
      tabW = width / objects.size();
    } // end of if
    for (int i = 0; i < objects.size() ; i++ ) {
      if (i == hovered && i != (int)selectedTab.value) {
        parent.textSize(11);
      } else {
        parent.textSize(12);
      } // end of if-else
      parent.fill((int)col.value);
      parent.text(((TabContainer.Tab)objects.get(i)).getName(), xPos + (i * tabW), yPos + ((int)menuHeight.value/4), tabW, (int)menuHeight.value/2); 
    } // end of for
    parent.rect(xPos + ((int)selectedTab.value * tabW), yPos + (int)menuHeight.value - 2, tabW, 2);  
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered > -1) {
        return true;
      } // end of if
    } else if (hovered > -1 && e.getAction() == MouseEvent.RELEASE) {
      selectedTab.value = hovered;
      return true;
    } else if (isBarHovered(e.getX(), e.getY()) && e.getAction() == MouseEvent.WHEEL) {
      chooseTab((int)selectedTab.value - e.getCount());
    }       
    MouseEvent eNew = new MouseEvent(e.getNative(), e.getMillis(), e.getAction(), e.getModifiers(), e.getX() - xPos, e.getY() - yPos - (int)menuHeight.value, e.getButton(), e.getCount());
    if ((int)selectedTab.value < objects.size()) {  
      if (objects.get((int)selectedTab.value).mouseEvent(eNew)) {
        return true;
      }
    }   
    return false;
  }
  
  public void keyEvent(KeyEvent e) {
    if ((int)selectedTab.value < objects.size()) {
      objects.get((int)selectedTab.value).keyEvent(e);
    } // end of if 
  }
  
  private boolean isBarHovered(int x, int y) {
    if (x > xPos && x < xPos + width && y > yPos && y < yPos + (int)menuHeight.value) {
      return true;
    } else {
      return false;
    }
  }
  
  private void mouseOver(int x, int y) {
    for (int i = 0; i < objects.size() ; i++ ) {
      if (x > xPos + (i * 70) && x < xPos + (i * 70) + 70 && y > yPos && y < yPos + (int)menuHeight.value) {
        hovered = i;
        return;
      } // end of if
    } // end of for
    hovered = -1;
  }
  
  protected class Tab extends GUIObject implements PConstants {
    
    private Property name;
    private ArrayList<GUIObject> objects;
    
    public Tab(PApplet p, String n) {
      super(p, 0, 0);
      name = registerProperty("Name", n);
      objects = new ArrayList<GUIObject>();
    }
    
    public String getName() {
      return (String)name.value;
    }
    
    public void addObject(GUIObject o) {
      objects.add(o);
    }
    
    public void draw() {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).draw();
      } // end of for
    }
    
    public void onResize(float xFactor, float yFactor) {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).onResize(xFactor, yFactor);
      } // end of for
    }
    
    public void onFixSize() {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).onFixSize();
      } // end of for
    }
    
    public void setResizable(boolean x, boolean y, boolean w, boolean h, boolean p) {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).setResizable(x, y, w, h, p);
      } // end of for
    }
    
    public void keyEvent(KeyEvent e) {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).keyEvent(e);
      } // end of for
    } 
    
    public boolean mouseEvent(MouseEvent e) {
      for (int i = 0; i < objects.size() ; i++ ) {
        if (objects.get(i).mouseEvent(e)) {
          return true;
        }
      } // end of for
      return false;
    }
  }
  
  
  
}