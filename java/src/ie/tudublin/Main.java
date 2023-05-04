package ie.tudublin;

public class Main
{	

	public static void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}
	

	public static void main(String[] args)
	{
		startUI();		
	}
}