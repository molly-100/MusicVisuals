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

}


