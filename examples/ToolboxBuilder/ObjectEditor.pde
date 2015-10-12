import toolbox.*; //<>//

public class ObjectEditor extends GUIObject {

  private GUIObject object;
  private Button btnMove, btnResize;
  private boolean shift, control, hideButtons = true;
  private boolean onMove, onResize;
  private int startX[] = {0, 0}, startY[] = {0, 0};
  private MouseEventManager mouseManager;

  public ObjectEditor(PApplet p, GUIObject o) {
    super(p, o.getX(), o.getY(), o.getWidth(), o.getHeight());
    object = o;
    mouseManager = new MouseEventManager(xPos, yPos, width, height);
    btnMove = new Button(parent, "+", xPos + (width/2) - 25, yPos + height + 10, 20, 20);
    btnMove.setStyle(2);
    btnResize = new Button(parent, ">", xPos + (width/2) + 5, yPos + height + 10, 20, 20);
    btnResize.setStyle(2);
    btnMove.setBackgroundColor(color(255));
    btnResize.setBackgroundColor(color(255));
    btnMove.setColor(color(70));
    btnResize.setColor(color(70));
    setXFromValue(o.getX());
    setYFromValue(o.getY());
    setWidthFromValue(o.getWidth());
    setHeightFromValue(o.getHeight());
    o.addPropertyListener(new PropertyListener() {
      public void onPropertyChanged(String tag, Object value) {
        if (tag.equals("X")) {
          setXFromValue((int)value);
        } else if (tag.equals("Y")) {
          setYFromValue((int)value);
        } else if (tag.equals("Width")) {
          setWidthFromValue((int)value);
        } else if (tag.equals("Height")) {
          setHeightFromValue((int)value);
        }
      }
    });
  }

  public void draw() {
    object.draw();
    if (!hideButtons && btnMove.isPressed() && onMove) {
      object.setProperty("X", startX[0] + (mouseX - startX[1]));
      object.setProperty("Y", startY[0] + (mouseY - startY[1]));
    }
    if (!hideButtons && btnResize.isPressed() && onResize) {
      int w = width, h = height;
      int dx = startX[0] + (mouseX - startX[1]);
      int dy = startY[0] + (mouseY - startY[1]);
      println("startX[0]: " + startX[0]);
      println("startY[0]: " + startY[0]);
      println("dx: " + (dx - startX[0]));
      println("dy: " + (dy - startY[0]));
      if (shift) {
        w = dx;
        h = dx;
      } else if (control) {
        if (abs(dx - startX[0]) > abs(dy - startY[0])) {
          w = dx;
        } else {  
          h = dy;
        }
      } else {
        w = dx;
        h = dy;
      }
      if (w < 10) w = 10;
      if (h < 10) h = 10;
      object.setProperty("Width", w);
      object.setProperty("Height", h);
    } 
    if (!hideButtons) {
      btnMove.draw();
      btnResize.draw();
    }
  }

  public boolean mouseEvent(MouseEvent e) {
    if (mouseManager.mouseEvent(e)) { //<>//
      if (mouseManager.isHovered()) {
        hideButtons = false;
      }
      return true;
    } else if (!hideButtons && btnMove.mouseEvent(e)) { 
      if (e.getAction() == MouseEvent.PRESS && !onMove) {
        startX[0] = xPos;
        startX[1] = e.getX();
        startY[0] = yPos;
        startY[1] = e.getY();
        onMove = true;
      }
      return true;
    } else if (!hideButtons && btnResize.mouseEvent(e)) {
      if (e.getAction() == MouseEvent.PRESS && !onResize) {
        startX[0] = width;
        startX[1] = e.getX();
        startY[0] = height;
        startY[1] = e.getY();
        onResize = true;
      }
      return true;
    } else if (e.getAction() == MouseEvent.PRESS) {
      hideButtons = true;
    } else if (e.getAction() == MouseEvent.RELEASE) {
      onResize = false;
      onMove = false;
    }
    shift = e.isShiftDown();
    control = e.isControlDown();
    return false;
  }

  public void keyEvent(KeyEvent e) {
    
  }

  public GUIObject getObject() {
    return object;
  }
  
  private void setXFromValue(int x) {
    //if (o.getClass() == Slider.class) {
      xPos = x;
      mouseManager.setX(x);
      btnMove.setX(x + (width / 2) - 25);
      btnResize.setX(x + (width / 2) + 5);
    //}
  }
  
  private void setYFromValue(int y) {
    //if (o.getClass() == Slider.class) {
      yPos = y;
      mouseManager.setY(y);
      btnMove.setY(y + height + 10);
      btnResize.setY(y + height + 10);
    //}
  }
  
  private void setWidthFromValue(int w) {
    //if (o.getClass() == Slider.class) {
      width = w;
      mouseManager.setWidth(w);
      btnMove.setX(xPos + (w / 2) - 25);
      btnResize.setX(xPos + (w / 2) + 5);
    //}
  }
  
  private void setHeightFromValue(int h) {
    //if (o.getClass() == Slider.class) {
      height = h;
      mouseManager.setHeight(h);
      btnMove.setY(yPos + h + 10);
      btnResize.setY(yPos + h + 10);
    //}
  }
}