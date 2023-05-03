package ie.tudublin;

import java.security.Key;
import ie.tudublin.Idea;

import example.WaveForm;
import ie.tudublin.*;

public class MyVisual extends Visual
{    
    WaveForm wf;
    Idea h_idea2;
    TestStar testStar;
    music_note mNote;
    //Idea h_idea;
    int mode = 1;
   // AudioBandsVisual abv;

    // public MyVisual() {
	// }

	public void settings()
    {
        size(1024, 500);
        //h_idea2 = new Idea();
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        //loadAudio("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3");  
         
        h_idea2 = new Idea(this);
        mNote = new music_note(this);
        testStar = new TestStar(this, ab, ap);
        
        // Call this instead to read audio from the microphone
       // startListening(); 
        
       // wf = new WaveForm(this);
       // abv = new AudioBandsVisual(this);

        //    for (int i = 0; i < stars.length; i++) {
        //         stars[i] = new Star();
        //    }

    }

    public void keyPressed()
    {
        if(key >= '0' && key <= '6')
        {
            mode = key - '0';
        }
        if(keyCode == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    // public void keyPressed()
    // {
    //     if (key == ' ')
    //     {
    //         getAudioPlayer().cue(0);
    //         getAudioPlayer().play();
    //     }
    // }

    @Override
    public void draw()
    {
        switch (mode) 
        {
			case 1://first to play (grace)
                System.out.print("in 1");
                part_one();
                //mNote.render();
                    break;
            case 2://When you press key 1 (hadassah)
                //mNote.render();
                System.out.print("in 2 ");
                part_two();
                break;

            case 3:// when you press key 2 (cece)
        
        

                break;

            case 4://when you press key 3 (molly)

                break;

            case 5://when you press key 4 (aisha)

                break;

            default:
                break;

        }
        
        KeyPress();

    }

    public void KeyPress()
    {
        switch (key) 
        {
            case 1://first to play (grace)
                mode = 1;
                    break;
            case 2://When you press key 1 (hadassah)
                mode = 2;
                        
                break;

            case 3:// when you press key 2 (cece)
        
                mode = 3;

                break;

            case 4://when you press key 3 (molly)

                mode  = 4;
                break;

            case 5://when you press key 4 (aisha)

                mode = 5;
                break;

            default:
                break;
            
        }
        // try
        // {
        //     // Call this if you want to use FFT data
        //     calculateFFT(); 
        // }
        // catch(VisualException e)
        // {
        //     e.printStackTrace();
        // }
        // // Call this is you want to use frequency bands
        // calculateFrequencyBands(); 

        // // Call this is you want to get the average amplitude
        // calculateAverageAmplitude();        
        // wf.render();
        // //abv.render();
    }


    void part_one()
    {
        h_idea2.render();
    }

    void part_two() {
        testStar.render();
    }
}
