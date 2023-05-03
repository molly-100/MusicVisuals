package ie.tudublin;

import java.util.ArrayList;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class TestStar {
    PApplet pApplet;
    AudioBuffer audioBuffer;
    AudioPlayer audioPlayer;
    FFT fft;

    Heart leftHeart;
    Heart rightHeart;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    ArrayList<Particle> particles = new ArrayList<Particle>();


    public TestStar(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.audioPlayer = audioPlayer;


        fft = new FFT(this.audioPlayer.bufferSize(), this.audioPlayer.sampleRate());

    }



    public void render(){
        System.out.println("Hello world");
        pApplet.background(0);
        draw();
    }











    
    float off = 0;

    float lerpedBuffer[] = new float[1024];

    void drawDaisy() {

   
        // Set the center point of the daisy
        float centerX = pApplet.width / 2;
        float centerY = pApplet.height / 2;
        float average = 0;
        float sum = 0;

        

        // Set the size of the daisy
        float daisySize = 200;

        // Calculate sum and average of the samples
        for (int i = 0; i < audioBuffer.size(); i++) {
            sum += PApplet.abs(audioBuffer.get(i));
        }
        average = sum / (float) audioBuffer.size();
        smoothedAmplitude = PApplet.lerp(smoothedAmplitude, average, 0.2f);

        // Map the amplitude value to a range of values that will control the size of
        // the center circle
        float size = PApplet.map(smoothedAmplitude, 0, 1, 130, 800);

        // Set the color of the daisy
        pApplet.fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        pApplet.ellipse(centerX, centerY, size, size);

        // Draw the petals of the daisy
        pApplet.fill(255, 255, 255); // white
        for (int i = 0; i < 6; i++) {
            float angle = i * PApplet.TWO_PI / 6;
            float petalX = centerX + PApplet.cos(angle) * (daisySize / 2);
            float petalY = centerY + PApplet.sin(angle) * (daisySize / 2);
            pApplet.pushMatrix();
            pApplet.translate(petalX, petalY);
            pApplet.rotate(angle);
            pApplet.ellipse(0, 0, 110, 100);
            pApplet.popMatrix();
        }

        // Set the color of the daisy
        pApplet.fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        pApplet.ellipse(centerX, centerY, 130, 130);

        // Set the color and stroke for the smile
        // Draw the smile
        pApplet.strokeWeight(5);
        pApplet.stroke(0);
        pApplet.noFill();
        pApplet.arc(centerX, centerY + 25, 60, 60, 0, PApplet.PI);

        // Draw the eyes
        pApplet.fill(0);
        pApplet.noStroke();
        pApplet.ellipse(centerX - 25, centerY - 10, 20, 20);
        pApplet.ellipse(centerX + 25, centerY - 10, 20, 20);

        // blush
        pApplet.fill(255, 192, 203);
        pApplet.noStroke();
        pApplet.ellipse(centerX - 35, centerY + 9, 15, 10);
        pApplet.ellipse(centerX + 35, centerY + 9, 15, 10);
    }

    void drawstem() {
        pApplet.pushMatrix(); // save the current coordinate system
        float halfH = (pApplet.height / 2) + 65;
        float halfW = (pApplet.width / 2);
        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < audioBuffer.size(); i++) {
            sum += PApplet.abs(audioBuffer.get(i));
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], audioBuffer.get(i), 0.1f);
        }
        average = sum / (float) audioBuffer.size();

        smoothedAmplitude = PApplet.lerp(smoothedAmplitude, average, 0.1f);

        for (int i = 0; i < audioBuffer.size(); i++) {
            float x = halfW - (lerpedBuffer[i] * halfH * 0.5f);
            float y = PApplet.map(i, 0, audioBuffer.size(), halfH, pApplet.height);
            pApplet.stroke(PApplet.map(i, 0, audioBuffer.size(), 0, 255), 252, 0);
            pApplet.line(halfW, y, x, y);
        }
        pApplet.popMatrix(); // restore the previous coordinate system
    }

    public void draw() {
        pApplet.background(0);
        drawDaisy();
        drawstem();	
        
      

        fft.forward(audioPlayer.mix);

        // maintain a variable number of particles between 10 and 20
        int minParticles = 10;
        int maxParticles = 20;
        int numParticles = particles.size();
        int targetParticles = (int) PApplet.map(fft.getBand(20), 0, 1, minParticles, maxParticles);
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
            x = pApplet.random(pApplet.width);
            y = pApplet.random(pApplet.height);
            vx = pApplet.random(-1, 1);
            vy = 4; // set the falling speed to a constant value of 2
            size = pApplet.random(10, 20);
            color = pApplet.color(pApplet.random(255), pApplet.random(255), pApplet.random(255));
        }

        void update() {
            x += vx;
            y += vy;

            if (y > pApplet.height) {
                y = 0;
            }
        }

        void draw() {
            int index = (int) PApplet.map(x, 100, pApplet.width, 100, fft.specSize());
            float amplitude = fft.getBand(index);

            size = amplitude * 70;
            // limit the maximum size
            if (size > 50) {
                size = 50;
            }
            pApplet.fill(color);
            pApplet.noStroke();
            pApplet.ellipse(x, y, size, size);
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
          pApplet.smooth();
          pApplet.noStroke();
          pApplet.fill(color);
          pApplet.beginShape();
          pApplet.vertex(x, y);
          pApplet.bezierVertex(x - size * 1, y - size * 2, x - size * 3, y + size / 2, x, y + size * 2);
          pApplet.bezierVertex(x + size * 3, y + size / 2, x + size * 1, y - size * 2, x, y);
          pApplet.endShape();
        }
        
        void update(float amplitude) {
          // Map the amplitude value to a range of values that will control the size of the heart
          size = PApplet.map(amplitude, 0, 1, 25, 100);
        }
      }
}
