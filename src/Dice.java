/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
import java.awt.*;
import java.util.Random;

//Static Dice class providing static methods, no need to declare a dice class in Backgammon or UI
public class Dice {
	private static int dieValue1;
	private static int dieValue2;
	private static int dieValue3 = 0;
	private static int dieValue4 = 0;
	private static boolean equalDice = false;
	private static boolean hasRolledDice = false;
	private static boolean initialRollComplete = false;
	private static Random rand = new Random();

	private static final int DIE_1_X_CO = 953;
	private static final int DIE_2_X_CO = 773;
	private static final int DIE_3_X_CO = 343;
	private static final int DIE_4_X_CO = 163;
	private static final int DICE_Y_CO = 308;
	private static final int DICE_SIZE = 100;
	private static final int DICE_CORNER_RADIUS = 40;
	private static final int SPOT_RADIUS = 20;

	public static void rollDice() {
		// reset the equalDice boolean to false (in case it was set to true in the previous roll)
		equalDice = false;
		// clear the current dice values
		UI.frame.repaint();

		dieValue1 = rand.nextInt(6) + 1;
		dieValue2 = rand.nextInt(6) + 1;

		if(diceAreEqual() && dieValue1 !=0 && dieValue2 != 0) {
			dieValue3 = dieValue1;
			dieValue4 = dieValue1;
		}

		if(initialRollComplete) {
			Moves.getMoves();
			Moves.printMoves();
		}
	}

	public static int getDie1Value()
	{
		return dieValue1;
	}

	public static int getDie2Value()
	{
		return dieValue2;
	}

	public static int getDie3Value() {
		return dieValue3;
	}

	public static int getDie4Value() {
		return dieValue4;
	}


	public static boolean diceAreEqual()
	{
		// they cannot equal 0 as Enda makes the die equal 0 to make it disappear once the user has moved
		// the specified amount of moves on that die
		if(dieValue1 == dieValue2 && dieValue1 != 0 && dieValue2 != 0)
			equalDice = true;

		return equalDice;
	}


	public static void playerHasRolledDice(boolean hasOrHasNot) {
		if(hasOrHasNot == true)
			hasRolledDice = true;

		if(hasOrHasNot == false)
			hasRolledDice = false;
	}

	public static boolean hasPlayerRolledDice()
	{
		return hasRolledDice;
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
			}

			// change to the correct board numbers depending on who wins the initial dice roll
			BoardNumbers.changeBoard(Backgammon.currentPlayer);

			// deals with printing of moves to the message box during the initial roll, ensures no redundancy
			if(!(diceAreEqual())) 
			{
				Moves.getMoves();
				initialRollComplete = true;
			}
			
		} while(diceAreEqual());

		//Stops player rolling again on the first turn
		playerHasRolledDice(true);
	}

	private static GradientPaint makeColor(float x1, float y1, Color c1, float x2, float y2, Color c2) {
		GradientPaint whiteGradient = new GradientPaint(x1, y1, c1, x2, y2, c2);

		return whiteGradient;
	}

	public static void drawDice(Graphics2D g, int xCoordinate, int diceNumber) {
		switch (diceNumber) {
			case 1:
				g.fillOval(xCoordinate + 40, DICE_Y_CO + 40, SPOT_RADIUS, SPOT_RADIUS);
				break;

			case 2:
				g.fillOval((xCoordinate + 20), (DICE_Y_CO + 20), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 60), (DICE_Y_CO + 60), SPOT_RADIUS, SPOT_RADIUS);
				break;

			case 3:
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				break;

			case 4:
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				break;

			case 5:
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 40), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				break;

			case 6:
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 15), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 65), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 15), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				g.fillOval((xCoordinate + 65), (DICE_Y_CO + 40), SPOT_RADIUS, SPOT_RADIUS);
				break;
		}
	}

	// draw must be called by paintComponent of the panel
	public static void draw(Graphics2D g) {

		GradientPaint whiteGradient1 = makeColor(DIE_1_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
				(DIE_1_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);

		//set die colour
	    g.setPaint(whiteGradient1);

	    if(dieValue1 != 0) {
			//drawRoundRect defined by a location (x,y), dimension (w h), and the width and height of an arc with which to round the corners.
			g.fillRoundRect(DIE_1_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
		}

		GradientPaint whiteGradient2 = makeColor(DIE_2_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
				(DIE_2_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
	    g.setPaint(whiteGradient2);

	    if(dieValue2 != 0) {
			g.fillRoundRect(DIE_2_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
		}

		// set spot color
		g.setColor(Color.BLACK);

		Dice.drawDice(g, DIE_1_X_CO, dieValue1);
		Dice.drawDice(g, DIE_2_X_CO, dieValue2);

		if(diceAreEqual()) {
			GradientPaint whiteGradient3 = makeColor(DIE_3_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
					(DIE_3_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
		    g.setPaint(whiteGradient3);

		    //Makes dice 3 disappear is equal to 0
		    if(dieValue3 != 0) {
				g.fillRoundRect(DIE_3_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
			}

			GradientPaint whiteGradient4 = makeColor(DIE_4_X_CO, DICE_Y_CO, Color.LIGHT_GRAY,
					(DIE_4_X_CO + DICE_SIZE), (DICE_Y_CO + DICE_SIZE), Color.WHITE);
		    g.setPaint(whiteGradient4);

		    if(dieValue4 != 0) {
				g.fillRoundRect(DIE_4_X_CO, DICE_Y_CO, DICE_SIZE, DICE_SIZE, DICE_CORNER_RADIUS, DICE_CORNER_RADIUS);
			}

			// set spot color
			g.setColor(Color.BLACK);

			Dice.drawDice(g, DIE_3_X_CO, dieValue3);
			Dice.drawDice(g, DIE_4_X_CO, dieValue4);
		}
	}

	//These are used to calculate the correct moves when only one dice has been used in a move
	public static void setDieValue1(int dieValue1) {
		Dice.dieValue1 = dieValue1;
	}

	public static void setDieValue2(int dieValue2) {
		Dice.dieValue2 = dieValue2;
	}

	public static void setDieValue3(int dieValue3) {
		Dice.dieValue3 = dieValue3;
	}

	public static void setDieValue4(int dieValue4) {
		Dice.dieValue4 = dieValue4;
	}
}
