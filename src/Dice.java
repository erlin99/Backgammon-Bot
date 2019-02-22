/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;


//Static Dice class providing static methods, no need to declare a dice class in Backgammon or UI

public class Dice 
{
	private static int dieValue1;
	private static int dieValue2;
	private static boolean equalDice = false;
	private static Random rand = new Random();
	

	public static void rollDice()
	{
		dieValue1 = rand.nextInt(6) + 1;
		dieValue2 = rand.nextInt(6) + 1;
	}
	
	public static int getDie1Value()
	{
		return dieValue1;
	}
	
	public static int getDie2Value()
	{
		return dieValue2;
	}
	
	public boolean diceAreEqual()
	{
		if(dieValue1 == dieValue2)
		{
			equalDice = true;
			// draw 2 additional dice
			// return dieValue1 4 times some how
		}
		
		return false;
	}
	
	public static void initialDiceRoll()
	{
		do
		{
			rollDice();
//			draw(Graphics2D g);
			
			System.out.println(getDie1Value() + " " + getDie2Value());
			
			if(dieValue1 == dieValue2)
			{
				UI.messagePanelText.append("\n-The 2 dice have the same value. We must roll again!");
			}
			
			else if(dieValue1 > dieValue2)
			{
				UI.messagePanelText.append("\n-" + Backgammon.currentPlayer.getPlayerName() + " it is your turn! Your color is " + Backgammon.currentPlayer.playerColorString);
			}
			else 
			{
				Backgammon.player1.setMoveMade(true);
				Backgammon.currentPlayer = Backgammon.player2;
				UI.messagePanelText.append("\n-" + Backgammon.currentPlayer.getPlayerName() + " it is your turn! Your color is " + Backgammon.currentPlayer.playerColorString);
				//**** NEED TO FIX ISSUE WHERE RED CAN'T GO NEXT
			}
			
		}
		while(dieValue1 == dieValue2);
	}
	
	
	public void draw(Graphics2D g) // draw must be called by paintComponent of the panel
	{
		/*
		 * Create a new diePanel that reads in a particular die image depending on the values of getDie1Value()
		 * and getDie2Value(). Create instances of this panel in UI and repaint it on each new turn after the roll dice
		 * button is clicked
		 */
		//draw
		
//		UI.frame.repaint();
	}
	
//	public static void main(String [] args)
//	{
//		
//		rollDice();
//		
//		System.out.println(getDie1Value() + " " + getDie2Value());
//	}
	
	
}
