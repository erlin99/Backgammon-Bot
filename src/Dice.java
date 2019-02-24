/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

//Static Dice class providing static methods, no need to declare a dice class in Backgammon or UI
public class Dice
{
	private static int dieValue1;
	private static int dieValue2;
	private static boolean equalDice = false;
	private static Random rand = new Random();
	
	private static final int DIE_1_X_CO = 953;
	private static final int DIE_2_X_CO = 773;
	private static final int DIE_3_X_CO = 343;
	private static final int DIE_4_X_CO = 163;
	private static final int DICE_Y_CO = 308;
	private static final int DICE_SIZE = 100;
	private static final int DICE_CORNER_RADIUS = 40;
	private static final int SPOT_RADIUS = 20;

	public static void rollDice()
	{
		// reset the equalDice boolean to false (in case it was set to true in the previous roll)
		equalDice = false;
		// clear the current dice values
		UI.frame.repaint();
		
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
	
	public static boolean diceAreEqual()
	{
		if(dieValue1 == dieValue2)
			equalDice = true;
	
		return equalDice;
	}
	
	public static void initialDiceRoll()
	{
		do
		{
			rollDice();
			// show the dice by calling the paintComponent method on the main panel
			UI.frame.repaint();

			if(diceAreEqual())
			{
				//***** ADD TIMER - Want the equal dice to show for a second or two before the new roll
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
		while(diceAreEqual());
	}
	
	private static GradientPaint makeColor(float x1, float y1, Color c1, float x2, float y2, Color c2)
	{
		GradientPaint whiteGradient = new GradientPaint(x1, y1, c1, x2, y2, c2);
		
		return whiteGradient;
	}

	public static void draw(Graphics2D g) // draw must be called by paintComponent of the panel
	{
		GradientPaint whiteGradient1 = makeColor(DIE_1_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
				(DIE_1_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
		
		//set die colour
	    g.setPaint(whiteGradient1);
		
		//drawRoundRect defined by a location (x,y), dimension (w h), and the width and height of an arc with which to round the corners.
		g.fillRoundRect(DIE_1_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
		
		
		GradientPaint whiteGradient2 = makeColor(DIE_2_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
				(DIE_2_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
	    g.setPaint(whiteGradient2);
		g.fillRoundRect(DIE_2_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
		
		// set spot color
		g.setColor(Color.BLACK);
		
		switch(dieValue1)
		{
			case 1:
				//fillOval(int x, int y, int width, int height)
			    g.fillOval((DIE_1_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
			break;	
			
			case 2:
				g.fillOval((DIE_1_X_CO + 20), (DICE_Y_CO + 20), SPOT_RADIUS, SPOT_RADIUS);
			    g.fillOval((DIE_1_X_CO + 60), (DICE_Y_CO + 60), SPOT_RADIUS, SPOT_RADIUS);	
			break;
				
			case 3:
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);	
			break;
			
			case 4:
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);	
			break;
				
			case 5:
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);		
			break;
					
			case 6:
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 15), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_1_X_CO + 65), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);		
			break;
		}
		
		switch(dieValue2)
		{
			case 1:
				//fillOval(int x, int y, int width, int height)
			    g.fillOval((DIE_2_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
			break;	
			
			case 2:
				g.fillOval((DIE_2_X_CO + 20), (DICE_Y_CO + 20), SPOT_RADIUS, SPOT_RADIUS);
			    g.fillOval((DIE_2_X_CO + 60), (DICE_Y_CO + 60), SPOT_RADIUS, SPOT_RADIUS);	
			break;
				
			case 3:
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);	
			break;
			
			case 4:
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);	
			break;
				
			case 5:
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);		
			break;
					
			case 6:
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 15), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((DIE_2_X_CO + 65), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);		
			break;
		}
		
		if(diceAreEqual())
		{
			GradientPaint whiteGradient3 = makeColor(DIE_3_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
					(DIE_3_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
		    g.setPaint(whiteGradient3);
		    
			g.fillRoundRect(DIE_3_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
			
			
			GradientPaint whiteGradient4 = makeColor(DIE_4_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
					(DIE_4_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
		    g.setPaint(whiteGradient4);
			
			g.fillRoundRect(DIE_4_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
			
			// set spot color
			g.setColor(Color.BLACK);
			
			switch(dieValue1)
			{
				case 1:
				    g.fillOval((DIE_3_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				    g.fillOval((DIE_4_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				break;	
				
				case 2:
					g.fillOval((DIE_3_X_CO + 20), (DICE_Y_CO + 20), SPOT_RADIUS, SPOT_RADIUS);
				    g.fillOval((DIE_3_X_CO + 60), (DICE_Y_CO + 60), SPOT_RADIUS, SPOT_RADIUS);
				    g.fillOval((DIE_4_X_CO + 20), (DICE_Y_CO + 20), SPOT_RADIUS, SPOT_RADIUS);
				    g.fillOval((DIE_4_X_CO + 60), (DICE_Y_CO + 60), SPOT_RADIUS, SPOT_RADIUS);
				break;
					
				case 3:
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				break;
				
				case 4:
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);	
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				break;
					
				case 5:
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);	
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				break;
						
				case 6:
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 15), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_3_X_CO + 65), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);		
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 15), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
					g.fillOval((DIE_4_X_CO + 65), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				break;
			}
		}
	}
}
