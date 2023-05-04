
package ie.tudublin;

import ddf.minim.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSource;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import jogamp.opengl.glu.nurbs.Curve;
import processing.core.PApplet;
import processing.core.PConstants;



public class Half {



float rotationX = 0;
float rotationY = 0;
float rotationZ = 0;
float sphereRadius = 450;
int lineCount = 30;
int squareSize = 15;
int colorChangeRate = 20;
int colorTime = 0;

MyVisual mv;



 
public Half(MyVisual mv) {

    this.mv = mv;
 
 }

 public void render()
 {
   draw();
 }


void draw() {

mv.strokeWeight(2);
  mv.background(0);
    int width;
    int height;
    mv.translate(mv.width/2, mv.height/2, 0);
  mv.rotateX(rotationX);
  mv.rotateY(rotationY);
  mv.rotateZ(rotationZ);
  
  // Get the amplitude of the song
  float amplitude = mv.getSmoothedAmplitude(); //song.mix.level();
  float sizeChange = MyVisual.map(amplitude, 0, 1, 1, 2);
  
  colorTime++;
  if (colorTime % colorChangeRate == 0) {
    mv.stroke(mv.random(255), mv.random(255), mv.random(255));
  }
  
  for (int i = 0; i < lineCount; i++) {
    for (int j = 0; j < lineCount; j++) {
      mv.pushMatrix();
      float xPos = MyVisual.map(i, 0, lineCount, -sphereRadius, sphereRadius);
      float yPos = MyVisual.map(j, 0, lineCount, -sphereRadius, sphereRadius);
      mv.translate(xPos, yPos, 0);
      float dist = MyVisual.sqrt(MyVisual.sq(xPos) + MyVisual.sq(yPos));
      float angle = MyVisual.atan2(yPos, xPos);
      float zPos = MyVisual.sqrt(MyVisual.sq(sphereRadius) - MyVisual.sq(dist));
      mv.translate(0, 0, zPos);
      float rotAngle = MyVisual.map(zPos, 0, sphereRadius, 0, PConstants.PI);
      mv.rotateX(rotAngle);
      mv.rotateY(angle + MyVisual.radians(mv.frameCount));
      
      // Scale the square based on the amplitude
      float scaledSize = sizeChange * squareSize;
      
      for (int k = 0; k < 4; k++) {
        mv.line(-scaledSize/2, -scaledSize/2, -scaledSize/2, scaledSize/2);
        mv.line(-scaledSize/2, scaledSize/2, scaledSize/2, scaledSize/2);
        mv.line(scaledSize/2, scaledSize/2, scaledSize/2, -scaledSize/2);
        mv.line(scaledSize/2, -scaledSize/2, -scaledSize/2, -scaledSize/2);
        mv.translate(0, 0, scaledSize/2);
        mv.rotateX(PConstants.PI/2);
      }
      mv.popMatrix();
    }
  }
  
  rotationX += 0.01;
  rotationY += 0.02;
  rotationZ += 0.03;
}
}
