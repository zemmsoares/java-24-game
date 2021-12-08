package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener
{
	private int numClicks = 0;
	private static int verdades = 0;
	private static int falsos = 0;
	
	public static void tester(String teste)
	{
		System.out.println(teste);
		if(teste=="erro") {
			falsos++;
			
		}else {
			verdades++;
		}
	}
	

	public void actionPerformed(ActionEvent event)
	{
		//System.out.println(teste);
		numClicks++;
		System.out.println("I was clicked " + numClicks + " times.");
		System.out.println("falsos " + falsos + " times.");
		System.out.println("verdadeiros " + verdades + " times.");
	}
}