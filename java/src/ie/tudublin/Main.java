package ie.tudublin;
//import processing.core.PApplet;
// import example.*;

// import example.CubeVisual;
// import example.MyVisual;
// import example.RotatingAudioBands;
// import ie.tudublin.Experiance;

public class Main 
{	
	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new practice());		
	}


	public static void main(String[] args)
	{
		System.out.println("Hello world");
		Main main = new Main();
		main.startUI();		
	}
}