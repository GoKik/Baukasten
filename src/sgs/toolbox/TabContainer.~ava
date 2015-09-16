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
  
  private ArrayList<String> tabNames;
  private int selectedTab = 0;
  private int col, bgCol, mCol, menuHeight;
  private int hovered = -1, clicked = -1;
  
  
  public TabContainer(PApplet p, int x, int y, int w, int h, int hM) {
    super(p, x, y, w, h);
    menuHeight = hM;
    tabNames = new ArrayList<String>();
  }
  
  public void setColor(int c) {
    col = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol = c;
  }
  
  public void setMenuColor(int c) {
    mCol = c;
  }
  
  public void newTab(String n) {
    objects.add(new Tab(parent, n));
    tabNames.add(n);
  }
  
  public void removeTab(String n) {
    int i = tabNames.indexOf(n);
    objects.remove(i);
    tabNames.remove(i);
  }
  
  public int getSelectedTab() {
    return selectedTab;
  }
  
  public void chooseTab(int i) {
    selectedTab = i;
    if (selectedTab < 0) {
      selectedTab = objects.size() - 1;
    } // end of if
    if (selectedTab >= objects.size()) {
      selectedTab = 0;
    } // end of if
  }
  
  public void addObject(String n, GUIObject o) {
    int i = tabNames.indexOf(n);
    ((TabContainer.Tab)objects.get(i)).addObject(o);
  }
  
  public void draw() {
    parent.noStroke();
    parent.fill(bgCol);
    parent.rect(xPos, yPos + menuHeight, width, height - menuHeight); 
    if (onDrawListener != null) {                 
      onDrawListener.draw(xPos, yPos, width, height);
    }
    parent.translate(xPos, yPos + menuHeight);
    if (selectedTab < objects.size()) {
      objects.get(selectedTab).draw();
    } // end of if
    parent.translate(-xPos, -yPos - menuHeight); 
    parent.noStroke();
    parent.fill(mCol);
    parent.rect(xPos, yPos, width, menuHeight);
    parent.textAlign(CENTER, CENTER);
    int tabW = 70;
    int menuW = objects.size() * tabW;
    if (menuW > width) {
      tabW = width / objects.size();
    } // end of if
    for (int i = 0; i < objects.size() ; i++ ) {
      if (i == hovered && i != selectedTab) {
        parent.textSize(11);
      } else {
        parent.textSize(12);
      } // end of if-else
      parent.fill(col);
      parent.text(tabNames.get(i), xPos + (i * tabW), yPos + (menuHeight/4), tabW, menuHeight/2); 
    } // end of for
    parent.rect(xPos + (selectedTab * tabW), yPos + menuHeight - 2, tabW, 2);  
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      if (hovered > -1) {
        return true;
      } // end of if
    } else if (hovered > -1 && e.getAction() == MouseEvent.RELEASE) {
      selectedTab = hovered;
      return true;
    } else if (isBarHovered(e.getX(), e.getY()) && e.getAction() == MouseEvent.WHEEL) {
      chooseTab(selectedTab - e.getCount());
    }       
    MouseEvent eNew = new MouseEvent(e.getNative(), e.getMillis(), e.getAction(), e.getModifiers(), e.getX() - xPos, e.getY() - yPos - menuHeight, e.getButton(), e.getCount());
    if (selectedTab < objects.size()) {  
      if (objects.get(selectedTab).mouseEvent(eNew)) {
        return true;
      }
    }   
    return false;
  }
  
  public void keyEvent(KeyEvent e) {
    if (selectedTab < objects.size()) {
      objects.get(selectedTab).keyEvent(e);
    } // end of if 
  }
  
  private boolean isBarHovered(int x, int y) {
    if (x > xPos && x < xPos + width && y > yPos && y < yPos + menuHeight) {
      return true;
    } else {
      return false;
    }
  }
  
  private void mouseOver(int x, int y) {
    for (int i = 0; i < objects.size() ; i++ ) {
      if (x > xPos + (i * 70) && x < xPos + (i * 70) + 70 && y > yPos && y < yPos + menuHeight) {
        hovered = i;
        return;
      } // end of if
    } // end of for
    hovered = -1;
  }
  
  protected class Tab extends GUIObject implements PConstants {
    
    private String name;
    private ArrayList<GUIObject> objects;
    
    public Tab(PApplet p, String n) {
      super(p, 0, 0);
      name = n;
      objects = new ArrayList<GUIObject>();
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