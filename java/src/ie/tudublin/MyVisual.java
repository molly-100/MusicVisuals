package ie.tudublin;

import example.WaveForm;

public class MyVisual extends Visual
{    
    WaveForm wf;
    Idea h_idea2;
    stars s;
    music_note mNote;
    DiscoBall db;
    Half hj;
    SpinningSpheres ss;
    int mode = 1;
    public Object song;

	public void settings()
    {
        fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        startMinim();
        //colorMode(HSB);
         
        h_idea2 = new Idea(this);
        mNote = new music_note(this);
        s = new stars(this);
        db = new DiscoBall(this);
        hj = new Half(this);
        ss = new SpinningSpheres(this);

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


    @Override
    public void draw()
    {
        switch (mode) 
        {
			case 1://first to play (grace)
           
                part_one();
                
                    break;
            case 2://When you press key 1 (hadassah)
             
                part_two();
                
                break;

            case 3:// when you press key 2 (cece)
        
               part_three();

                break;

            case 4://when you press key 3 (molly)

                part_four();

                break;

            case 5://when you press key 4 (aisha)

                part_five();
                break;
            
            case 6://when you press key 4 (aisha)

                part_six();
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
        // colorMode(HSB, 360, 100, 100);
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
             //colorMode(HSB, 360, 100, 100); 
            db.render();
       
    }

    void part_five()
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
         hj.render();

    }

    void part_six()
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
         ss.render();

    }
    
}

