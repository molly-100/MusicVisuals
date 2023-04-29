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
    
}


