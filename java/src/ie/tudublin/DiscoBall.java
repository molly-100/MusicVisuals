


package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSource;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import jogamp.opengl.glu.nurbs.Curve;
import processing.core.PApplet;

public class DiscoBall  {

  // Minim minim;
  // AudioPlayer ap;
  // AudioInput ai;
  // AudioBuffer ab;
  FFT fft;

  MyVisual mv;

  int mode = 0;
  float theta = 0;
  float rotationSpeed = (float) 0.01;
  float[] FFT_g;
    
    




  public DiscoBall(MyVisual mv) {

     this.mv = mv;
   
   // fft = new FFT(mv.ab.size(), ((AudioSource) mv.ap).sampleRate());
    
  }

  public void render()
  {
    draw ();
  }

  public void draw() {


     // Create gradient background
    for (int y = 0; y < mv.height; y++) {

      // Calculate the color at each row
      int c = mv.lerpColor(mv.color(139, 0, 139), mv.color(255, 140, 0), map2(y, 0, mv.height, 0, 1));

      // Set the color for the row
      mv.stroke(c);

      // Draw a line for the row
      mv.line(0, y, mv.width, y);

    } 


    mv.pushMatrix(); // Save the current transformation matrix

    // displays hearts on the screen
    for (int i = 0; i < 5; i++) {

      Heart heart = new Heart();
      heart.display();

    }

    mv.popMatrix(); // Restore the previous transformation matrix
  
    mv.translate(mv.width/2, mv.height/2, 0); // Move the sphere to the center of the screen
    float angle = (float) (mv.frameCount * 0.01); // Use a fixed rotation speed based on frame count
    mv.rotateY(angle); // Rotate the sphere based on the angle
  
    //fft = new FFT(mv.ap.bufferSize(), mv.ap.sampleRate()); // Initialize FFT with the audio buffer size and sample rate
    //fft.forward(mv.ab); // Perform FFT on the audio buffer
    float[] spectrum = fft.getSpectrumImaginary(); // Get frequency spectrum data
  

    float sum = 0;

    for (int i = 0; i < spectrum.length; i++) {

      sum += spectrum[i]; // Calculate the sum of all frequency values

    }
    float average = sum / spectrum.length; // Calculate the average frequency value
  
    rotationSpeed = map2(average, 0, 255, 0.001, 0.1); // Map the average frequency value to a rotation speed range
    mv.rotateY(rotationSpeed * mv.frameCount); // Rotate the sphere based on the current frame count and rotation speed
  
    // set the thickness of the lines in the disco ball
    mv.stroke(204,204,255); 
    mv.strokeWeight((float) 3.5); // sets the thickness of lines in the disco Ball
    mv.line(0, -250, 0, 0);

    

    mv.fill(160,160,160);// set th colour of the sphere
    mv.sphere(250); // Draw the sphere

    discoBallRope();

  
  }
  
  private float map2(float value, double f, double g, double d, double e) {

    return (float) (d + (e - d) * ((value - f) / (g - f)));

  }


  void discoBallRope() {

    mv.stroke(192,192,192); 
    mv.strokeWeight(10); // Set the thickness of the line
    mv.line(0, 0, 0, 0, -(mv.height/2), 0); // Draw the line from the top of the sphere to the top of the screen

  }

  class Heart {

    float heartSize;
    float heartX;
    float heartBottomY;
    float r;
    
    Heart() {

      heartSize = mv.random(10, 100);
      heartX = mv.random(mv.width);
      heartBottomY = mv.random(mv.height+heartSize);
      r = mv.random(255);

    }


    
    
    void display() {


      float level = mv.ap.mix.level();
  
      if (level > 0.1) {

        // Set heart position and color
        heartX = mv.random(mv.width);
        heartBottomY = mv.random(mv.height+heartSize);
        r = mv.random(255);

      }
  
      mv.fill(r, 0, 0);
      mv.stroke(r, 0, 0);
      //mv.strokeWeight(2);
      //left half of heart
      mv.beginShape();
      mv.curveVertex(heartX, heartBottomY+heartSize); //anchor point
      mv.curveVertex(heartX, heartBottomY); //bottom tip
      mv.curveVertex(heartX - heartSize/2, (float) (heartBottomY-heartSize/1.5)); //left edge
      mv.curveVertex(heartX - heartSize/3, heartBottomY-heartSize); //top of left edge
      mv.curveVertex(heartX, (float) (heartBottomY-heartSize*.75)); //top middle dip
      mv.curveVertex(heartX, heartBottomY); //guiding point
      mv.endShape();
  
      //right half of heart
      mv.beginShape();
      mv.curveVertex(heartX, heartBottomY);
      mv.curveVertex(heartX, (float) (heartBottomY-heartSize*.75));
      mv.curveVertex(heartX + heartSize/3, heartBottomY-heartSize);
      mv.curveVertex(heartX + heartSize/2, (float) (heartBottomY-heartSize/1.5));
      mv.curveVertex(heartX, heartBottomY);
     mv. curveVertex(heartX, heartBottomY + heartSize);
      mv.endShape();
      
    }



    

  }

}
