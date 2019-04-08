import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

//static class as no need to create an instantiation of it in any other part of the application
public class Moves 
{
	private static boolean[][] arrayOfAcceptableMoves = new boolean[28][28];
	public static LinkedList<MoveNode> possibleMoves = new LinkedList<>();

	public static int dieValue1;
	public static int dieValue2;
	public static int dieValue3;
	public static int dieValue4;
	
	// fills the 'arrayOfAcceptableMoves' with acceptable moves
	public static boolean[][] getMoves() {
		// check if the game has ended
		/*if(Backgammon.counterMap[0].getNumCounters() == 15) {
			UI.finishGame(Backgammon.player2);
		}
		else if(Backgammon.counterMap[25].getNumCounters() == 15) {
			UI.finishGame(Backgammon.player1);
		}*/

		char currentColor = Backgammon.currentPlayer.getPlayerColor();
		dieValue1 = Dice.getDie1Value();
		dieValue2 = Dice.getDie2Value();
		dieValue3 = Dice.getDie3Value();
		dieValue4 = Dice.getDie4Value();

		// clear the array of acceptable moves each time the function is called
		for(int row=0; row<28; row++)
		{
			for(int column=0; column<28; column++)
			{
				arrayOfAcceptableMoves[row][column] = false;
			}
		}

		if(Backgammon.isBarred()) 
		{
			arrayOfAcceptableMoves = barMoves();
		} 
		else 
		{
			for (int i = 1; i <= 24; i++) 
			{
				if (Backgammon.counterMap[i].getColor() == currentColor) 
				{
					// check dieValue1, dieValue2, and dieValue1 + dieValue2 from this location 'i'
					// if the move is valid store it in the arrayOfAcceptableMoves array
					// NOTE: the array is of type boolean, the coordinates of the move itself are stored in the indexing of the array

					// Edge case: the dice are equal - have to check the value of one of the dice *1, 2, 3 & 4
					if (!(Dice.diceAreEqual()) && currentColor == 'R') 
					{

						// this if statement stops an ArrayIndexOutOfBoundsException
						if (i + dieValue1 < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + dieValue1], i)) {
								arrayOfAcceptableMoves[i][i + dieValue1] = true;
							}
						}

						if (i + dieValue2 < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + dieValue2], i)) {
								arrayOfAcceptableMoves[i][i + dieValue2] = true;
							}
						}

						if (i + dieValue1 + dieValue2 < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + dieValue1 + dieValue2], i)) {
								arrayOfAcceptableMoves[i][i + dieValue1 + dieValue2] = true;
							}
						}
					}

					if (!(Dice.diceAreEqual()) && currentColor == 'W') {
						// this if statement stops an ArrayIndexOutOfBoundsException
						if (i - dieValue1 >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - dieValue1], i)) {
								arrayOfAcceptableMoves[i][i - dieValue1] = true;
							}
						}

						if (i - dieValue2 >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - dieValue2], i)) {
								arrayOfAcceptableMoves[i][i - dieValue2] = true;
							}
						}

						if (i - dieValue1 - dieValue2 >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - dieValue1 - dieValue2], i)) {
								arrayOfAcceptableMoves[i][i - dieValue1 - dieValue2] = true;
							}
						}
					} else if (Dice.diceAreEqual() && currentColor == 'R') {
						if (i + dieValue1 < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + dieValue1], i)) {
								arrayOfAcceptableMoves[i][i + dieValue1] = true;
							}
						}

						if (i + (dieValue2 * 2) < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + (dieValue2 * 2)], i)) {
								arrayOfAcceptableMoves[i][i + (dieValue2 * 2)] = true;
							}
						}

						if (i + (dieValue3 * 3) < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + (dieValue3 * 3)], i)) {
								arrayOfAcceptableMoves[i][i + (dieValue3 * 3)] = true;
							}
						}

						if (i + (dieValue4 * 4) < Backgammon.counterMap.length) {
							if (acceptableMove(Backgammon.counterMap[i + (dieValue4 * 4)], i)) {
								arrayOfAcceptableMoves[i][i + (dieValue4 * 4)] = true;
							}
						}
					} else if (Dice.diceAreEqual() && currentColor == 'W') {
						if (i - dieValue1 >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - dieValue1], i)) {
								arrayOfAcceptableMoves[i][i - dieValue1] = true;
							}
						}

						if (i - (dieValue2 * 2) >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - (dieValue2 * 2)], i)) {
								arrayOfAcceptableMoves[i][i - (dieValue2 * 2)] = true;
							}
						}

						if (i - (dieValue3 * 3) >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - (dieValue3 * 3)], i)) {
								arrayOfAcceptableMoves[i][i - (dieValue3 * 3)] = true;
							}
						}

						if (i - (dieValue4 * 4) >= 0) {
							if (acceptableMove(Backgammon.counterMap[i - (dieValue4 * 4)], i)) {
								arrayOfAcceptableMoves[i][i - (dieValue4 * 4)] = true;
							}
						}
					}
				}
				// correctBearOff() commented below
				if (correctBearOff()) {
					bearOffMoves(i);
				}
			}
		}
		return arrayOfAcceptableMoves;
	}

	//Gets the available moves for if a counter is on the bar
	public static boolean[][] barMoves() 
	{

		// clear the array of acceptable moves each time the function is called
		for(int row=0; row<28; row++) {
			for(int column=0; column<28; column++) {
				arrayOfAcceptableMoves[row][column] = false;
			}
		}

		char player = Backgammon.currentPlayer.getPlayerColor();

		if(player == 'W') {
			if(acceptableMove(Backgammon.counterMap[25 - dieValue1], 0)) {
				arrayOfAcceptableMoves[26][25 - dieValue1] = true;
			}

			if(acceptableMove(Backgammon.counterMap[25 - dieValue2], 0)) {
				arrayOfAcceptableMoves[26][25 - dieValue2] = true;
			}

			if(acceptableMove(Backgammon.counterMap[25 - (dieValue1 + dieValue2)], 0) && (dieValue1 + dieValue2) <= 6) {
				arrayOfAcceptableMoves[26][25 - (dieValue1 + dieValue2)] = true;
			}
		} else {
			if(acceptableMove(Backgammon.counterMap[dieValue1], 0)) {
				arrayOfAcceptableMoves[27][dieValue1] = true;
			}

			if(acceptableMove(Backgammon.counterMap[dieValue2], 0)) {
				arrayOfAcceptableMoves[27][dieValue2] = true;
			}

			if(acceptableMove(Backgammon.counterMap[(dieValue1 + dieValue2)], 0) && (dieValue1 + dieValue2) <= 6)
			{
				arrayOfAcceptableMoves[27][(dieValue1 + dieValue2)] = true;
			}
		}
		return arrayOfAcceptableMoves;
	}

	// allows user to bear off their counter without needing an exact die value that lands on the bear off
	// i.e. they're 3 positions away and the die value is 5 - they can bear off the counter
	public static void bearOffMoves(int i) 
	{
		if(Backgammon.currentPlayer.getPlayerColor() == 'W') {
			if(validBearOff(i, Dice.getDie1Value())) {
				arrayOfAcceptableMoves[i][0] = true;
			} else if(validBearOff(i, Dice.getDie2Value())) {
				arrayOfAcceptableMoves[i][0] = true;
			} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())) {
				arrayOfAcceptableMoves[i][0] = true;
			}

			if(Dice.diceAreEqual()) {
				if(validBearOff(i, Dice.getDie1Value())) {
					arrayOfAcceptableMoves[i][0] = true;
				} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())) {
					arrayOfAcceptableMoves[i][0] = true;
				} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value())) {
					arrayOfAcceptableMoves[i][0] = true;
				} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value() + Dice.getDie4Value())) {
					arrayOfAcceptableMoves[i][0] = true;
				}
			}
		} else {
			if(validBearOff(i, Dice.getDie1Value())) {
				arrayOfAcceptableMoves[i][25] = true;
			} else if(validBearOff(i, Dice.getDie2Value())) {
				arrayOfAcceptableMoves[i][25] = true;
			} if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())) {
				arrayOfAcceptableMoves[i][25] = true;
			}

			if(Dice.diceAreEqual()) {
				if(validBearOff(i, Dice.getDie1Value())) {
					arrayOfAcceptableMoves[i][25] = true;
				} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())) {
					arrayOfAcceptableMoves[i][25] = true;
				} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value())) {
					arrayOfAcceptableMoves[i][25] = true;
				} else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value() + Dice.getDie4Value())) {
					arrayOfAcceptableMoves[i][25] = true;
				}
			}
		}
	}

	// checks that you can only move to the correct bear off and that the dice contain values that enable you to reach the bear off
	public static boolean validBearOff(int currentPosition, int dieValue) 
	{
		if(Backgammon.counterMap[currentPosition].getColor() == Backgammon.currentPlayer.getPlayerColor()) 
		{
			if(Backgammon.currentPlayer.getPlayerColor() == 'W') 
			{
				return currentPosition - dieValue <= 0;
			}
			else
			{
				return currentPosition + dieValue >= 25;
			}
		} 
		else 
		{
			return  false;
		}
	}

    // writes list of moves to the message box in the format specified on the Trello board
    public static void printMoves()
    {
    	// possibleMoves is a reference to the linkedlist at the top
    	// clear clears the list
        possibleMoves.clear();
        MoveNode move;

		//Ends the game if one of the two side has bore off all their counters
		if(Backgammon.counterMap[0].getNumCounters() == 15) {
			UI.finishGame(Backgammon.player2);
			UI.player2PipPanelText.setText(Backgammon.player2.playerName + " - Score: " + Backgammon.player2.getPoints());
			Dice.resetDice();
		}
		else if(Backgammon.counterMap[25].getNumCounters() == 15) {
			UI.finishGame(Backgammon.player1);
			UI.player1PipPanelText.setText(Backgammon.player1.playerName + " - Score: " + Backgammon.player1.getPoints());
			Dice.resetDice();
		}

        //add the possible moves to the linkedlist
        for(int row = 0; row<28; row++) {
            for(int column=0; column<28; column++) {
                if(arrayOfAcceptableMoves[row][column]) {
                	//if the color is red change the pip numbers so they match the board numbers
                	if (Backgammon.currentPlayer.getPlayerColor() == 'R')
                	{
						move = new MoveNode(25 - row, 25 - column);
						possibleMoves.addLast(move);
					}
                	else {
                		move = new MoveNode(row, column);
						possibleMoves.add(0, move);
					}
                }
            }
        }

        assignHits(possibleMoves);

        // Sprint 4 fix added - added !UI.gameOver into the conditions to ensure that this code doesn't print to the message panel
        // when the game has already ended
		//gameFinished ensures that when a game is over that no moves print while waiting for input
        if (possibleMoves.size() == 0 && !UI.gameOver && !UI.gameFinished){

        	//This try catch is need for the method to run
        	try {
				UI.displayNoMove();
			} catch(InterruptedException ex){

			}
            // sets the currentplayer to the next player (other player)
            UI.next();
        } else if (possibleMoves.size() == 1 && !UI.gameOver && !UI.gameFinished) {

			int currentPosition;
			int nextPosition;

			//This try catch is need for the method to run
			try {
				UI.displayForcedMove();
			} catch(InterruptedException ex){

			}

			//Gets the correct current and next Positions for the current player
			if(Backgammon.currentPlayer.getPlayerColor() == 'W'){
				currentPosition = possibleMoves.get(0).getFromPip();
				nextPosition = possibleMoves.get(0).getToPip();
			} else {
				currentPosition = 25 - possibleMoves.get(0).getFromPip();
				nextPosition = 25 - possibleMoves.get(0).getToPip();
			}

			//prints all moves onto text panel
			UI.messagePanelText.append(allMoves(possibleMoves).toString());

			Backgammon.currentPlayer.playerMove(currentPosition, nextPosition);
			UI.frame.repaint();

			if(!UI.gameFinished){
				UI.next();
			}

		} else if(!UI.gameOver && !UI.gameFinished && !Dice.allDiceUsed()){
//        	UI.messagePanelText.append("\n" + Backgammon.currentPlayer.playerName + ", here are your possible moves:");
        	UI.messagePanelText.append("\nHere are your possible moves:");
			UI.messagePanelText.append(allMoves(possibleMoves).toString());
		}
	}

	//creates a string of all the moves
	public static StringBuilder allMoves(LinkedList<MoveNode> moves){

		StringBuilder allMoves = new StringBuilder();
//		allMoves.append("\n");
		int i = 0;
		int count = 0;
		for (MoveNode m : moves) {
			if (i >= 25) {
				i = 0;
			}
			//if the amount of moves is larger the alphabet, starting printing 2 letters
			if (count < 26) {
				allMoves.append("\n" + ((char) (65 + i++)) + ". ");
			}
			//else just print one number
			else {
				allMoves.append("\nA" + ((char) (65 + i++)) + ". ");
			}

			//if the move is from the bar print bar instead of the number
			if (m.getFromPip() >= 26) {
				if (m.isHit())
				{
					allMoves.append("Bar - " + m.getToPip() + "*");
				}
				else
				{
					allMoves.append("Bar - " + m.getToPip());
				}
			}
			if (m.getToPip() == 0 || m.getToPip() == 25) {
				allMoves.append(m.getFromPip() + " - Off");
			}
			else {
				if (m.isHit()) {
					allMoves.append(m.getFromPip() + " - " + m.getToPip() + "*");
				}
				else
				{
					allMoves.append(m.getFromPip() + " - " + m.getToPip());
				}
			}
			count++;
		}

		return allMoves;
	}

	//assigns true or false to the isHit variable for each move nodes in the linked list
	private static void assignHits(LinkedList<MoveNode> moves) {

		for (int i = 0; i < moves.size(); i++)
		{
			int from = moves.get(i).getFromPip();
			int to = moves.get(i).getToPip();

			if (Backgammon.currentPlayer.getPlayerColor() == 'R') {
				from = 25 - from;
				to = 25 - to;
			}

			boolean differentColor = Backgammon.counterMap[from].getColor() != Backgammon.counterMap[to].getColor();
			if(differentColor && Backgammon.counterMap[to].getNumCounters() == 1)
				moves.get(i).setHit(true);
		}
	}

	//check if all checkers of the color are in the correct quadrant for bearOff
	private static boolean correctBearOff() {
		int count = 0;
		if (Backgammon.currentPlayer.getPlayerColor() == 'W'){
			for(int i = 0; i <= 6; i++) {
				if(Backgammon.counterMap[i].getColor() == 'W')
					count += Backgammon.counterMap[i].getNumCounters();
			}
		} else if (Backgammon.currentPlayer.getPlayerColor() == 'R'){
			for(int i = 19; i <= 25; i++) {
				if(Backgammon.counterMap[i].getColor() == 'R')
					count += Backgammon.counterMap[i].getNumCounters();
			}
		}
		return count >= 15;
	}
	
	private static boolean acceptableMove(CounterPositions position, int i) {

		boolean sameColor = position.getColor() == Backgammon.currentPlayer.getPlayerColor();
		boolean oneCounterOrEmpty = position.getNumCounters() <= 1;
		boolean bar = position.pipNumber == 26 || position.pipNumber == 27;
		boolean sameSpace = position.pipNumber == i;
		boolean notToBearOff = position.pipNumber == 0 || position.pipNumber == 25;

		return ((sameColor || oneCounterOrEmpty) && !bar && !sameSpace) && !notToBearOff;
	}
}
