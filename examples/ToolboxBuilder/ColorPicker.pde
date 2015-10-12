
public class ColorPicker extends GUIObject {
  
  private PImage spectrum, specSmall;
  private int r, g, b;
  private boolean hovered, opened, colorChanged;
  private Button btnClose;
  
  public ColorPicker(PApplet p, int x, int y, int w, int h) {
    super(p, x, y, w, h);
    spectrum = loadImage("spectrum_chart.jpg");
    spectrum.resize(100, 100);
    specSmall = loadImage("spectrum_chart.jpg");
    specSmall.resize(w, h);
    btnClose = new Button(parent, "x", xPos + (width/2) + 27, yPos + (height/2) - 75, 20, 20);
    btnClose.setBackgroundColor(color(50));
    btnClose.setColor(color(255));
    btnClose.setStyle(Button.ROUND);
  }
  
  public void draw() {
    noStroke();
    if (!opened) {
      fill(color(r, g, b));
      rect(xPos, yPos, width, height);
    } else {
      fill(color(70));
      rect(xPos + (width/2) - 55, yPos + (height/2) - 80, 110, 135);
      fill(color(r, g, b));
      rect(xPos + (width/2) - 50, yPos + (height/2) - 75, 70, 20);
      btnClose.draw();
      image(spectrum, xPos + (width/2) - 50, yPos + (height/2) - 50);
    }
  }
  
  public boolean mouseEvent(MouseEvent e) {
    if (btnClose.mouseEvent(e)) {
      if (btnClose.wasPressed()) {
        opened = false;
      }
      return true;
    }
    if (e.getAction() == MouseEvent.MOVE) {
      mouseOver(e.getX(), e.getY());
      return hovered;
    } else if (e.getAction() == MouseEvent.PRESS) {
      if (hovered && !opened) {
        opened = true;
        return true;
      } else if (opened) {
        pickColor();
        return true;
      } else {
        return false;
      }
    } else if (opened && e.getAction() == MouseEvent.DRAG) {
      pickColor();
      return true;
    } else if (opened) {
      return true;
    } else {
      return false;
    }
  }
  
  private void mouseOver(int x, int y) {
    if (opened) {
      hovered = (x > xPos + (width/2) - 50 && x < xPos + (width/2) + 50 && y > yPos + (height/2) - 50 && y < yPos + (height/2) + 50);
    } else {
      hovered = (x > xPos && x < xPos + width && y > yPos && y < yPos + height);
    }
  }
  
  private void pickColor() {
    r = get(mouseX, mouseY) >> 16 & 0xFF;
    g = get(mouseX, mouseY) >> 8 & 0xFF;
    b = get(mouseX, mouseY) & 0xFF;
    colorChanged = true;
  }
  
  public boolean hasColorChanged() {
    if (colorChanged) {
      colorChanged = false;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isOpened() {
    return opened;
  }
  
  public void setColor(int c) {
    r = c >> 16 & 0xFF;
    g = c >> 8 & 0xFF;
    b = c & 0xFF;
  }
  
  public int getColor() {
    return color(r, g, b);
  }
  
  public void keyEvent(KeyEvent e) { }
  
}
    