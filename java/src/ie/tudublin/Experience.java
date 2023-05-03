package ie.tudublin;


// dont need to iniatate the objects as they are all present in visual 

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Experience extends Visual{

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    Idea idea;//call the file  and name the file 

    FFT fft;

    int mode = 0;

    float y = 0;
    

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

        idea = new Idea(this);

        
    }

    public void draw()
    {
        background(0);
        // came up with an idea for an opening this is branch is for our ideas and rough work 
        
        switch (mode) {
			case 0://Opening :- shooting stars then sycadiics patterns to create the effect of 
            //someone hallucinating or in a trans with the words welcome to the expereiance appearing
            //stars shooting then the words welcome to the experiance appear like a start buttton  
            background(0);
            
            idea.render();
          
                
                break;
        case 1://When you press key 1 (hadassah)
               
                    
            break;

        case 2:// when you press key 2 (cece)
            

            break;

        case 3:

            break;

        case 4:

            break;
        }

        
    }

    
}