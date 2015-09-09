package toolbox;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class TabContainer extends GUIObject implements PConstants {
  
  private ArrayList<Tab> tabs;
  private ArrayList<String> tabNames;
  private int selectedTab = 0;
  private int width, height, col, bgCol;
  private int hovered = -1, clicked = -1;
  
  public TabContainer(PApplet p, int x, int y, int b, int h) {
    super(p, x, y);
    tabs = new ArrayList<Tab>();
    tabNames = new ArrayList<String>();
    width = b;
    height = h;
  }
  
  public void setColor(int c) {
    col = c;
  }
  
  public void setBackgroundColor(int c) {
    bgCol = c;
  }
  
  public void newTab(String n) {
    tabs.add(new Tab(parent, n));
    tabNames.add(n);
  }
  
  public void removeTab(String n) {
    int i = tabNames.indexOf(n);
    tabs.remove(i);
    tabNames.remove(i);
  }
  
  public int getSelectedTab() {
    return selectedTab;
  }
  
  public void addObject(String n, GUIObject o) {
    int i = tabNames.indexOf(n);
    tabs.get(i).addObject(o);
  }
  
  public void draw() {
    parent.noStroke();  
    parent.fill(bgCol);
    parent.rect(xPos, yPos, width, 40);
    parent.fill(col);
    parent.textAlign(CENTER, CENTER);
    
    for (int i = 0; i < tabs.size() ; i++ ) {
      if (i == hovered && i != selectedTab) {
        parent.textSize(11);
      } else {
        parent.textSize(12);
      } // end of if-else
      parent.text(tabNames.get(i), xPos + (i * 70), yPos, 70, 40); 
    } // end of for
    parent.rect(xPos + (selectedTab * 70), yPos + 38, 70, 2);
    
    if (selectedTab < tabs.size()) {    
      tabs.get(selectedTab).draw();
    } // end of if
  }
  
  public void mouseEvent(MouseEvent e) {
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
    } else if (e.getAction() == MouseEvent.RELEASE) {
      clicked(e.getX(), e.getY());
    }
    if (selectedTab < tabs.size()) {  
      tabs.get(selectedTab).mouseEvent(e);
    }
  }
  
  public void keyEvent(KeyEvent e) {
    if (selectedTab < tabs.size()) {  
      tabs.get(selectedTab).keyEvent(e);
    }
  }
  
  private void clicked(int x, int y) {
    mouseOver(x, y);
    if (hovered > -1) {
      selectedTab = hovered;
    } // end of if
  }
  
  private void mouseOver(int x, int y) {
    for (int i = 0; i < tabs.size() ; i++ ) {
      if (x > xPos + (i * 70) && x < xPos + (i * 70) + 70 && y > yPos && y < yPos + 40) {
        hovered = i;
        return;
      } // end of if
    } // end of for
    hovered = -1;
  }
  
  private class Tab extends GUIObject implements PConstants {
    
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
    
    public void keyEvent(KeyEvent e) {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).keyEvent(e);
      } // end of for
    } 
    
    public void mouseEvent(MouseEvent e) {
      for (int i = 0; i < objects.size() ; i++ ) {
        objects.get(i).mouseEvent(e);
      } // end of for
    }
  }
  
  
  
}