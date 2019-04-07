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
	public static LinkedList<MoveNode> moves = new LinkedList<>();

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
    	// moves is a reference to the linkedlist at the top 
    	// clear clears the list
        moves.clear();
        MoveNode move;

		//Ends the game if one of the two side has bore off all their counters
		if(Backgammon.counterMap[0].getNumCounters() == 15) {
			UI.finishGame(Backgammon.player2);
		}
		else if(Backgammon.counterMap[25].getNumCounters() == 15) {
			UI.finishGame(Backgammon.player1);
		}


        //add the possible moves to the linkedlist
        for(int row = 0; row<28; row++) {
            for(int column=0; column<28; column++) {
                if(arrayOfAcceptableMoves[row][column]) {
                        move = new MoveNode(row, column);
                        moves.add(0, move);
                }
            }
        }
        
        moves = deleteDuplicateMoves(moves); //deleting duplicates in the moves

        // Sprint 4 fix added - added !UI.gameOver into the conditions to ensure that this code doesn't print to the message panel
        // when the game has already ended
        if (moves.size() == 0 && !UI.gameOver){

        	//This try catch is need for the method to run
        	try {
				UI.displayNoMove();
			} catch(InterruptedException ex){

			}
            // sets the currentplayer to the next player (other player)
            UI.next();
        } else if (moves.size() == 1 && !UI.gameOver) {

			//This try catch is need for the method to run
			try {
				UI.displayForcedMove();
			} catch(InterruptedException ex){

			}

			int currentPosition = moves.get(0).getFromPip();
			int nextPosition = moves.get(0).getToPip();

			UI.messagePanelText.append(allMoves(moves).toString());

			Backgammon.currentPlayer.playerMove(currentPosition, nextPosition);
			UI.frame.repaint();


			UI.next();
		} else if(!UI.gameOver){
        	UI.messagePanelText.append("\n" + Backgammon.currentPlayer.playerName + ", here are your possible moves:");
			UI.messagePanelText.append(allMoves(moves).toString());
		}
	}

	//creates a string of all the moves
	public static StringBuilder allMoves(LinkedList<MoveNode> moves){
		
		StringBuilder allMoves = new StringBuilder();
		
		if (Backgammon.currentPlayer.getPlayerColor() == 'R') 
		{
			for (int i = 0; i < moves.size(); i++) 
			{
				int nextPosition = 25 - moves.get(i).getToPip();
				int currentPosition = 25 - moves.get(i).getFromPip();
				
				if (moves.get(i).getFromPip() >= 26) 
				{
					if (moves.get(i).isHit()) 
					{
						allMoves.append("\nBar - " + nextPosition + "*");
					}
					else 
					{
						allMoves.append("\nBar - " + nextPosition );
					}
				} 
				else if (moves.get(i).getToPip() == 0 || moves.get(i).getToPip() == 25)
				{
					allMoves.append("\n" + currentPosition + " - Off");
				} 
				else 
				{

					if (moves.get(i).isHit()) {
						allMoves.append("\n" + currentPosition + " - " + nextPosition + "*");
					} else {
						allMoves.append("\n" + currentPosition + " - " + nextPosition);
					}
				}
			}
		} 
		else 
		{
			for (MoveNode m : moves) 
			{
				if (m.isHit()) 
				{
					allMoves.append("\n" + m.getFromPip() + " - " + m.getToPip() + "*");
				}
				else 
				{
					if (m.getFromPip() >= 26) 
					{
						allMoves.append("\nBar - " + m.getToPip());
					}
					else if (m.getToPip() == 0 || m.getToPip() == 25) 
					{
						allMoves.append("\n" + m.getFromPip() + " - Off");
					} 
					else 
					{
						allMoves.append("\n" + m.getFromPip() + " - " + m.getToPip()); }
				}
			}
		}

		return allMoves;
	}

	//This method deletes the duplicates from the list
	public static LinkedList<MoveNode> deleteDuplicateMoves(LinkedList<MoveNode> list){
		//loops through the linked list
		for (int i = 0; i < list.size() - 1; i++) {
			//if the getTo pip == to the pip that the next move is from and said move is not a hit
			if (list.get(i).getToPip() == list.get(i+1).getFromPip() && !isAHit(list.get(i).getToPip())){
				int temp = list.get(i+1).getToPip();
				list.get(i).setToPip(temp);
				list.remove(i+1);
			} else if (isAHit(list.get(i).getToPip())){
				list.get(i).setHit(true);
			}
		}

		return list;
	}

	//checks if the pip that it can move to is a hit
	private static boolean isAHit(int pipToGo){
		if (Backgammon.counterMap[pipToGo].getNumCounters() == 1) {
			if(Backgammon.currentPlayer.getPlayerColor() == 'R') {
				if (Backgammon.counterMap[pipToGo].getColor() == 'W')
					return true;
			} else {
				if (Backgammon.counterMap[pipToGo].getColor() == 'R')
					return true;
			}
		}
		return false;
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
