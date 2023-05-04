package ie.tudublin;

import processing.core.PApplet;
import java.util.ArrayList;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import processing.core.PConstants;

public class SpinningSpheres 
{
  private float angle = 0;
  MyVisual mv;
  FFT fft;

 
  public SpinningSpheres(MyVisual mv)
  {
    this.mv = mv;
  }

  public void draw()
  {
    mv.background(0);
    

    float level = mv.getSmoothedAmplitude(); //song.mix.level();
    //float sizeChange = MyVisual.map(amplitude, 0, 1, 1, 2);

    //float level = song.mix.level();

    mv.translate(mv.width/2, mv.height/2, 0);
    mv.rotateY(angle);
    mv.rotateX(angle);

    //create rotating spheres
    mv.noStroke();

    for (int i = 0; i < 1000; i++)
    {
      float x = mv.random(-mv.width/2, mv.width/2);
        float y = mv.random(-mv.height/2, mv.height/2);
        float z = mv.random(-100, 100);
        
        float d = PApplet.dist(0, 0, 0, x, y, z);


        mv.fill(mv.random(255), mv.random(255), mv.random(255));
        mv.popStyle();
        mv.pushMatrix();
        mv.translate(x, y, z);
        mv.sphere((float) (d * level * 0.1));
        mv.popMatrix();
    }
    angle += 0.01;
  }

  public void render()
  {
    draw();
  }
  
}