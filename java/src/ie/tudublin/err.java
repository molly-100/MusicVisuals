package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ie.tudublin.idea.Star;
import processing.core.PApplet;
import processing.core.PFont ;
import processing.core.PGraphics;



public class err extends PApplet{

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    PFont f;
    PGraphics textCanvas;
    String sentence = "";

    FFT fft;

    int mode = 0;

    float y = 0;
    
    float[] spectrum;
    Star[] stars;
    int numStars = 300;
    int maxSpeed = 0;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }

        if (keyCode == BACKSPACE) {
            if (sentence.length() > 0) {
              sentence = sentence.substring(0, sentence.length()-1);
            }
          } else if (key == '\n') {
            sentence = "";
          } else {
            sentence += key;
          }

	}
    
    public void settings()
    {
        size(1024, 1024);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 

        // And comment the next two lines out
        ap = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    }

    public void draw()
    {

        // came up with an idea for an opening this is branch is for our ideas and rough work 
        
        switch (mode) {
            
			case 0://Opening :- shooting stars then sycadiics patterns to create the effect of 
            //someone hallucinating or in a trans with the words welcome to the expereiance appearing
            //stars shooting then the words welcome to the experiance appear like a start buttton  
            background(0);

            // ((Object) fft).analyze(spectrum);
        
            translate(width / 2, height / 2);
        
            for (int i = 0; i < numStars; i++) {
                Star s = stars[i];
                s.update();
                s.show();
            }
        
        class Star {
            float x, y, z;
            float pz;
            float speed;
        
            Star() {
                x = random(-width, width);
                y = random(-height, height);
                z = random(width);
                pz = z;
                speed = random(1, maxSpeed);
            }
        
            void update() {
                z = z - speed;
        
                if (z < 1) {
                    z = width;
                    x = random(-width, width);
                    y = random(-height, height);
                    pz = z;
                }
            }
        
            void show() {
                fill(255);
                noStroke();
        
                float sx = map(x / z, 0, 1, 0, width);
                float sy = map(y / z, 0, 1, 0, height);
        
                float r = map(speed, 1, maxSpeed, 1, 8);
        
                ellipse(sx, sy, r, r);
        
                float px = map(x / pz, 0, 1, 0, width);
                float py = map(y / pz, 0, 1, 0, height);
        
                stroke(255, 100);
                line(px, py, sx, sy);
        
                pz = z;
            }
        }
          
                break;

        case 1:
                
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



void drawDot(float x, float y, int depth) {
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
                  
            break;

        case 2:
            

            break;

        case 3:

            break;

        case 4:

            break;
        }

    }

    private void drawDot(float random, float random2, int i) {
    }
    }

    
