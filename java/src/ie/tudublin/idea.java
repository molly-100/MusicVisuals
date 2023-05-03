package ie.tudublin;

// import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
// import ddf.minim.Minim;
// import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PConstants;

public class Idea 
{ 
    
    private MyVisual mv;
    //private Idea h_idea2;

    Star[] stars = new Star[500];
    float speed = 50;


    public Idea(MyVisual mv)
    {
        this.mv = mv;
        //this.idea = new Idea(mv);

        for (int i = 0; i < stars.length; i++) 
        {
            stars[i] = new Star();
        }
    }
    // Minim minim;
    // AudioPlayer ap;
    // AudioInput ai;
    // AudioBuffer ab;
    // int mode = 0;

    //FFT fft;
    float y = 0;    

    float[] spectrum;

    //Star[] stars;
    //int numStars = 100;
    //int maxSpeed = 0;

    // public void keyPressed() {
    //     if (key >= '0' && key <= '9') {
    //         mode = key - '0';
    //     }
    //     if (keyCode == ' ') {
    //         if (ap.isPlaying()) {
    //             ap.pause();
    //         } else {
    //             ap.rewind();
    //             ap.play();
    //         }
    //     }
    // }


   


    // public void settings()
    // {
    //     size(1024, 1024);
    // }

    //Star[] stars = new Star[numStars];

    //public void setup() {
    
    //minim = new Minim(this);
    // Uncomment this to use the microphone
    // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
    // ab = ai.mix; 

        // And comment the next two lines out
        //   ap = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 512);
        //   ap.play();
        //   ab = ap.mix;
        //colorMode(HSB);
    
    
     
    // }

    public void draw() 
    {
        //speed = map(mouseX, 0, width, 0, 50);

        mv.background(0);

        mv.translate(mv.width / 2, mv.height / 2);

        for (int i = 0; i < stars.length; i++) {
            stars[i].update();
            stars[i].show();
        }

        // Draw the word "experience" in the center of the screen
        mv.textAlign(PConstants.CENTER, PConstants.CENTER);
        mv.textSize(94);
        mv.fill(255);
        mv.text("EXPERIENCE", 0, 0);

        // Move the origin to the center of the screen
        mv.translate(-(mv.width) / 2, (mv.height) / 2);

    }

    public void render()
    {
        draw();
        // System.out.print("here");
    }



    class Star {
        float x, y, z;
        float pz;

        Star() {
            x = mv.random(-(mv.width), mv.width);
            y = mv.random(-(mv.height), mv.height);
            z = mv.random(mv.width);
            pz = z;
        // speed = random(1, maxSpeed);
        //speed = map(mouseX, 0, width, 0, 50);
        }

        void update() {
            z = z - speed;

            if (z < 1) {
                z = mv.width;
                x = mv.random(-(mv.width), mv.width);
                y = mv.random(-(mv.height), mv.height);
                pz = z;
            }
        }
        void show() {
            mv.fill(255);
            mv.noStroke();

            float sx = PApplet.map(x / z, 0, 1, 0, mv.width);
            float sy = PApplet.map(y / z, 0, 1, 0, mv.height);

            float r = PApplet.map(z, 0, mv.width/2, 16, 0);

            mv.ellipse(sx, sy, r, r);

            float px = PApplet.map(x / pz, 0, 1, 0, mv.width/2);
            float py = PApplet.map(y / pz, 0, 1, 0, mv.height/2);

            pz = z;

            mv.stroke(255, 100);
            mv.line(px, py, sx, sy);
        }
    }

}