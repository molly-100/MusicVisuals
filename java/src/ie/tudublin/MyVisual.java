package ie.tudublin;

import java.security.Key;
import ie.tudublin.Idea;

import example.WaveForm;
import ie.tudublin.*;

public class MyVisual extends Visual
{    
    WaveForm wf;
    Idea h_idea2;
    stars s;
    music_note mNote;
    DiscoBall db;
    //Idea h_idea;
    int mode = 1;
   // AudioBandsVisual abv;

    // public MyVisual() {
	// }

	public void settings()
    {
        size(1024, 700,P3D);
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
        s = new stars(this);
        db = new DiscoBall(this);
        
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
           
                part_one();
                System.out.println("in 1 ");
                
                    break;
            case 2://When you press key 1 (hadassah)
             
                part_two();
                System.out.println("in 2 ");
                
                break;

            case 3:// when you press key 2 (cece)
        
               part_three();

                break;

            case 4://when you press key 3 (molly)

                part_four();

                break;

            case 5://when you press key 4 (aisha)

                break;

            default:
                break;

        }
        
    }


    void part_one()
    {
        h_idea2.render();
    }

    void part_two()
    {
        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        colorMode(HSB, 360, 100, 100);
        mNote.render();
    }

    void part_three()
    {
       
        try {
                calculateFFT();
            } 
            
        catch (VisualException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
             calculateFrequencyBands();
             s.render();
       
    }

    void part_four()
    {
       
        try {
                calculateFFT();
            } 
            
        catch (VisualException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
             calculateFrequencyBands();
             db.render();
       
    }

    


}

