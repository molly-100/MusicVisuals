package ie.tudublin;

import jogamp.opengl.util.jpeg.JPEGDecoder.EXIF;

public class Main
{	

	// public void startUI()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new Idea());		
	// }

	public void exp(){

		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new Experience());
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		//main.startUI();
		main.exp();			
	}
}