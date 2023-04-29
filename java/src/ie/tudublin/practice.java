package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;

public class practice extends Visual
{

    private Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    int mode = 0;

    FFT fft;

    public void settings()
    {
        size(1024,1024, P3D);
        smooth();
    }

    public void setup()
    {
        colorMode(HSB);
        startMinim();
        loadAudio("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3");
        getAudioPlayer().play();
    }

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
	}

    public void draw()
    {
        background(0);
        noFill();
        noStroke();
        float amplitude = 0;
        float[] bands;
        frameRate(50);

        calculateAverageAmplitude();
        amplitude = getSmoothedAmplitude();

        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        bands = getSmoothedBands();
    }

}

class Star extends PApplet
{
    PApplet p;
    float x;
    float y;
    float size;
    float speed;
    float height;
    float width;
    float rotate_Star;
    int num_point = 5;
    Star[] stars;
    int i = 0;

    Star(PApplet p, float x, float y, float size, float speed, float height, float width, Star[] stars) 
    {
        this.p = p;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.rotate_Star = 0;
        this.stars = stars;
    }

    void display(float[] bands) 
    {
        p.pushMatrix();
        p.translate(x, y);
        p.rotate(rotate_Star);
        p.scale(size/300);
        p.noStroke();

        p.beginShape();

        for (int i = 0; i < 6; i++) {
            float angle = TWO_PI * i / 6;
            float x = cos(angle) * 100;
            float y = sin(angle) * 100;
            p.vertex(x, y);
            angle += TWO_PI / (6 * 2);
            x = cos(angle) * 50;
            y = sin(angle) * 50;
            p.vertex(x, y);
        }
        p.endShape();
        p.popMatrix();
    }  

}


