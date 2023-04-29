package ie.tudublin;

import processing.core.PApplet;
import processing.core.PFont ;
import processing.core.PGraphics; 



public class star extends PApplet
{
public void setup() {
  size(1240,1240);
  PGraphics textCanvas = createGraphics(800, 400);
  PFont f = loadFont("InterUI-ExtraBold-250.vlw");
  background(0);
}

public void draw() {}

public void keyPressed() {
 
  Object sentence;
  if (keyCode == BACKSPACE) {
    if (((Object) sentence).length() > 0) {
      sentence = sentence.substring(0, sentence.length()-1);
    }
  } else if (key == '\n') {
    sentence = "";
  } else {
    sentence += key;
  }
  // draw sentence on off-screen canvas
  textCanvas.beginDraw();
  textCanvas.textFont(f, 250);
  textCanvas.background(0);
  textCanvas.fill(255);
  textCanvas.textSize(250);
  // measure sentence width
  float sentenceWidth = textCanvas.textWidth(sentence);
  // draw sentence centered
  textCanvas.text(sentence, (width-sentenceWidth)/2, 300);
  textCanvas.endDraw();
  // draw new sentence
  background(0); 
  noStroke();
  // go through 2000 iterations of the recursive
  // drawing of dots and thin lines
  for (int i = 0; i < 2000; i++) {
    drawDot(random(0, width), random(0, height), 10);
  }
}

public void drawDot(float x, float y, int depth) {
  // stop recursion if depth is 0
  if (depth == 0) {
    return;
  }
  // look up the brightness of current position
  // in the textcanvas (where we drew the sentence)
  if (brightness(textCanvas.get((int)x, (int)y)) > 0) {
    // if inside sentence, print a dot with some transparency
    // depending on the depth of recursion
    fill(255, map(depth, 0, 10, 80, 180));
    ellipse(x, y, depth/2, depth/2);
  }
  // find next position
  float nextX = x + random(-20, 20);
  float nextY = y + random(-20, 20);
  // go into recursion on next level
  drawDot(nextX, nextY, depth - 1);
}

}