import sgs.baukasten.*;

Baukasten baukasten;
Stift stift, buntstift;
Knopf kLoesche;
Wahlbox wTest;

void setup() {
  size(480, 800);
  baukasten = new Baukasten(this, Baukasten.JAVA_MODE);
  stift = new Stift(this);
  stift.setzeBereich(0, 100, width, height / 2);
  buntstift = new Stift(this);
  buntstift.setzeBereich(0, height/2, width, height);
  buntstift.setzeFarbe(color(255, 100, 100));
  kLoesche = new Knopf(this, "Lösche", 10, 10, 80, 80);
  kLoesche.setzeFarbe(color(255, 100, 100));
  kLoesche.setzeHintergrundFarbe(color(255, 255, 255));
  kLoesche.setzeStil(Knopf.ROUND);
  wTest = new Wahlbox(this, "Test", 100, 10, true);
  wTest.setzeStil(Wahlbox.CHECKBOX_ROUND);
  wTest.setzeFarbe(color(255, 100, 100));
  baukasten.fuegeEin(stift);
  baukasten.fuegeEin(buntstift);
  baukasten.fuegeEin(kLoesche);
  baukasten.fuegeEin(wTest);
}

void draw() {
  background(255);
  
  strokeWeight(1);
  stroke(255, 100, 100);
  line(0, 100, width, 100);
  line(0, height/2, width, height/2);
  if (kLoesche.wurdeGedrueckt()) {
    stift.loescheZeichnung();
    buntstift.loescheZeichnung();
  }
  if (!wTest.istGewaehlt()) {
    stift.setzeFarbe(color(255, 255, 255));
  } else {
    stift.setzeFarbe(color(0, 0, 0));
  }
  
  stift.bewegeBis(mouseX, mouseY);
  buntstift.bewegeBis(mouseX, mouseY);
}

void mousePressed() {
  if (stift.istOben()) {
    stift.bewegeBis(mouseX, mouseY);
    stift.runter();
  }
  if (buntstift.istOben()) {
    buntstift.bewegeBis(mouseX, mouseY);
    buntstift.runter();
  }
}

void mouseReleased() {
  stift.hoch();
  buntstift.hoch();
}