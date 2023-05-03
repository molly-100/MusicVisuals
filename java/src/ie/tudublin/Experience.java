// package ie.tudublin;


// // dont need to iniatate the objects as they are all present in visual 

// import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
// import ddf.minim.Minim;
// import ddf.minim.analysis.FFT;
// import processing.core.PApplet;
// import ie.tudublin.Idea;

// public class Experience extends Visual{

//     Minim minim;
//     AudioPlayer ap;
//     AudioInput ai;
//     AudioBuffer ab;


//     Idea idea;//call the file  and name the file 

//     FFT fft;

//     int mode = 1;

//     float y = 0;

//     boolean lastPressed = false;
//     boolean showLyrics = false;
    

//     public void setup()
//     {
//         // minim = new Minim(this);
//         // Uncomment this to use the microphone
//         // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
//         // ab = ai.mix; 

//         // And comment the next two lines out
//         // ap = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 1024);
//         // ap.play();
//         // ab = ap.mix;
//         // colorMode(HSB);
//         startMinim();
//         loadAudio("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3 ");

    

        
//     }


//     public void keyPressed() {

// 		if (keyCode == ' ') {

//             getAudioPlayer().cue(0);
//             getAudioPlayer().play();

//             // if (ap.isPlaying()) {
//             //     ap.pause();
//             // } else {
//             //     ap.rewind();
//             //     ap.play();
//             // }
//         }
// 	}
    
//     public void settings()
//     {
//         size(1024, 1024);
//     }



//     public void draw()
//     {
//         background(0);
//         // came up with an idea for an opening this is branch is for our ideas and rough work 
        

//         switch (mode) {
// 			//Opening :- shooting stars then sycadiics patterns to create the effect of 
//             //someone hallucinating or in a trans with the words welcome to the expereiance appearing
//             //stars shooting then the words welcome to the experiance appear like a start buttton  
           
//         case 1://When you press key 1 (hadassah)
//               partOne(); 

                    
//             break;

//         case 2:// when you press key 2 (cece)
            

//             break;

//         case 3:

//             break;

//         case 4:

//             break;
//         }
//         keyPressingLogic();
        
//     }

//     void keyPressingLogic(){

//         if(keyPressed){
//             switch(key){

//                 case '1':
//                     mode = 1;
//                     break;

//                 case '2':
//                     mode = 2;
//                     break;

//                 case '3':
//                     mode = 3;
//                     break;
                
//                 case '4':
//                     mode = 4;
//                     break;
//             }

//             lastPressed = true;
//         } else{

//             lastPressed = false;
//         }
//     }

//     void partOne(){

//         try{
//             calculateFFT();
//         }

//         catch(VisualException e){
//             e.printStackTrace();
//         }

//         calculateFrequencyBands();
//         calculateAverageAmplitude();

//         idea.render();

        
//         }
// }
