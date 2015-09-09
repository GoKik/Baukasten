//package sgs.baukasten;

import processing.core.*;
import processing.opengl.*;
import processing.javafx.*;
import processing.event.*;
import processing.data.*;
import processing.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class TabContainer extends GUIObjekt implements PConstants {
  
  private HashMap<String, Tab> tabs;
  private int selectedTab = 0;
  
  public TabContainer(PApplet p) {
    super(p, 0, 0);
    tabs = new HashMap<String, Tab>();
  }
  
  public void newTab(String n) {
    tabs.put(n, new Tab(parent, n));
  }
  
  public void removeTab(String n) {
    tabs.remove(n);
  }
  
  public void addObject(String n, GUIObjekt o) {
    tabs.get(n).addObject(o);
  }
  
  public void draw() {
    if (selectedTab < tabs.size()) {    
      tabs.get(selectedTab).draw();
    } // end of if
  }
  
  public void mouseEvent(MouseEvent e) {
    if (selectedTab < tabs.size()) {  
      tabs.get(selectedTab).mouseEvent(e);
    }
  }
  
  public void keyEvent(KeyEvent e) {
    if (selectedTab < tabs.size()) {  
      tabs.get(selectedTab).keyEvent(e);
    }
  }
    
    private class Tab extends GUIObjekt implements PConstants {
      
      private String name;
      private ArrayList<GUIObjekt> objects;
      
      public Tab(PApplet p, String n) {
        super(p, 0, 0);
        name = n;
        objects = new ArrayList<GUIObjekt>();
      }
      
      public void addObject(GUIObjekt o) {
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