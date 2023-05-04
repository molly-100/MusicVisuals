package ie.tudublin;

import java.util.ArrayList;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import processing.core.PConstants;

public class stars  {

   
    FFT fft;

    
    Heart leftHeart;
    Heart rightHeart;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    MyVisual mv;

    float off = 0;

    float lerpedBuffer[] = new float[1024];

    ArrayList<Particle> particles = new ArrayList<Particle>();

    // public void settings() {
    //     size(1024, 1000);
    //     // fullScreen(P3D, SPAN);
    // }
    
    public stars(MyVisual mv)
    {
        this.mv = mv;

        for (int i = 0; i < 100; i++) {
            Particle p = new Particle();
            particles.add(p);
        }

        y = mv.height / 2;
        smoothedY = y;

        // Create the left and right hearts
        leftHeart = new Heart(mv.width * 0.2f, mv.height / 2, 10, mv.color(255, 0, 0));
        rightHeart = new Heart(mv.width * 0.8f, mv.height / 2, 10, mv.color(255, 0, 0));

    }

    public void render(){
        draw();
    }


    

    void drawDaisy() {

   
        // Set the center point of the daisy
        float centerX = mv.width / 2;
        float centerY = mv.height / 2;
        float average = 0;
        float sum = 0;

        

        // Set the size of the daisy
        float daisySize = 200;

        // Calculate sum and average of the samples
        for (int i = 0; i < mv.ab.size(); i++) {
            sum += MyVisual.abs(mv.ab.get(i));
        }
        average = sum / (float) mv.ab.size();
        smoothedAmplitude = MyVisual.lerp(smoothedAmplitude, average, 0.2f);

        // Map the amplitude value to a range of values that will control the size of
        // the center circle
        float size = MyVisual.map(smoothedAmplitude, 0, 1, 130, 800);

        // Set the color of the daisy
        mv.fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        mv.ellipse(centerX, centerY, size, size);

        // Draw the petals of the daisy
        mv.fill(255, 255, 255); // white
        for (int i = 0; i < 6; i++) {
            float angle = i * PConstants.TWO_PI / 6;
            float petalX = centerX + MyVisual.cos(angle) * (daisySize / 2);
            float petalY = centerY + MyVisual.sin(angle) * (daisySize / 2);
            mv.pushMatrix();
            mv.translate(petalX, petalY);
            mv.rotate(angle);
            mv.ellipse(0, 0, 110, 100);
            mv.popMatrix();
        }

        // Set the color of the daisy
        mv.fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        mv.ellipse(centerX, centerY, 130, 130);

        // Set the color and stroke for the smile
        // Draw the smile
        mv.strokeWeight(5);
        mv.stroke(0);
        mv.noFill();
        mv.arc(centerX, centerY + 25, 60, 60, 0, PConstants.PI);

        // Draw the eyes
        mv.fill(0);
        mv.noStroke();
        mv.ellipse(centerX - 25, centerY - 10, 20, 20);
        mv.ellipse(centerX + 25, centerY - 10, 20, 20);

        // blush
        mv.fill(255, 192, 203);
        mv.noStroke();
        mv.ellipse(centerX - 35, centerY + 9, 15, 10);
        mv.ellipse(centerX + 35, centerY + 9, 15, 10);
    }

    void drawstem() {
        mv.pushMatrix(); // save the current coordinate system
        float halfH = (mv.height / 2) + 65;
        float halfW = (mv.width / 2);
        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < mv.ab.size(); i++) {
            sum += MyVisual.abs(mv.ab.get(i));
            lerpedBuffer[i] = MyVisual.lerp(lerpedBuffer[i], mv.ab.get(i), 0.1f);
        }
        average = sum / (float) mv.ab.size();

        smoothedAmplitude = MyVisual.lerp(smoothedAmplitude, average, 0.1f);

        for (int i = 0; i < mv.ab.size(); i++) {
            float x = halfW - (lerpedBuffer[i] * halfH * 0.5f);
            float y = MyVisual.map(i, 0, mv.ab.size(), halfH, mv.height);
            mv.stroke(MyVisual.map(i, 0, mv.ab.size(), 0, 255), 252, 0);
            mv.line(halfW, y, x, y);
        }
        mv.popMatrix(); // restore the previous coordinate system
    }

    public void draw() {
        mv.background(0);

        float[] arr;


        
        
      

        try {
            mv.calculateFFT();
        } catch (VisualException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        arr = mv.getSmoothedBands();

        // maintain a variable number of particles between 10 and 20
        int minParticles = 10;
        int maxParticles = 20;
        int numParticles = particles.size();
        int targetParticles = (int) MyVisual.map(arr[8], 0, 1, minParticles, maxParticles);
        if (numParticles < targetParticles) {
            for (int i = 0; i < targetParticles - numParticles; i++) {
                Particle p = new Particle();
                particles.add(p);
            }
        } else if (numParticles > targetParticles) {
            particles.subList(targetParticles, numParticles).clear();
        }

        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.update();
            p.draw();
        }

        drawDaisy();
        drawstem();	

      // Update the left and right hearts based on the audio amplitude
  leftHeart.update(smoothedAmplitude);
  rightHeart.update(smoothedAmplitude);

  // Draw the left and right hearts
  leftHeart.draw();
  rightHeart.draw();
    }

    class Particle {
        float x, y;
        float vx, vy;
        float size;
        int color;

        Particle() {
            x = mv.random(mv.width);
            y = mv.random(mv.height);
            vx = mv.random(-1, 1);
            vy = 4; // set the falling speed to a constant value of 2
            size = mv.random(10, 20);
            color = mv.color(mv.random(255), mv.random(150), mv.random(255));
        }

        void update() {
            x += vx;
            y += vy;

            if (y > mv.height) {
                y = 0;
            }
        }

        void draw() {

            float[] arr;

            // try {
            //     mv.calculateFFT();
            // } catch (VisualException e) 
            // {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
    
            arr = mv.getSmoothedBands();

             float amplitude = (int) MyVisual.map(x, 100, mv.width, 100, arr[5]);
            //int index = (int) MyVisual.map(x, 100, mv.width, 100, arr.length > 50 ? arr[50] : 0);
            //float amplitude = arr[index % 5];

            size = amplitude * 70;
            // limit the maximum size
            if (size > 50) {
                size = 50;
            }
            mv.fill(color);
            mv.noStroke();
            mv.ellipse(x, y, size, size);
        }
    }
    class Heart {
        float x, y;
        float size;
        int color;
        
        Heart(float x, float y, float size, int color) {
          this.x = x;
          this.y = y;
          this.size = size;
          this.color = color;
        }
        
        void draw() {
          // Draw the heart shape using bezier curves
          mv.smooth();
          mv.noStroke();
          mv.fill(color);
          mv.beginShape();
          mv.vertex(x, y);
          mv.bezierVertex(x - size * 1, y - size * 2, x - size * 3, y + size / 2, x, y + size * 2);
          mv.bezierVertex(x + size * 3, y + size / 2, x + size * 1, y - size * 2, x, y);
          mv.endShape();
        }
        
        void update(float amplitude) {
          // Map the amplitude value to a range of values that will control the size of the heart
          size = MyVisual.map(amplitude, 0, 1, 25, 100);
        }
      }
}