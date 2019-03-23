
//static class as no need to create an instantiation of it in any other part of the application
public class Moves 
{
	public static CounterPositions[] counterMap = new CounterPositions[28];
	private static boolean[][] arrayOfAcceptableMoves = new boolean[28][28];

	public static int dieValue1;
	public static int dieValue2;
	public static int dieValue3;
	public static int dieValue4;
	
	// fills the 'arrayOfAcceptableMoves' with acceptable moves
	public static boolean[][] getMoves()
	{
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

		if(Backgammon.isBarred()){
			arrayOfAcceptableMoves = barMoves();
		} else {

			for (int i = 1; i <= 24; i++) {
				if (Backgammon.counterMap[i].getColor() == currentColor) {
					// check dieValue1, dieValue2, and dieValue1 + dieValue2 from this location 'i'
					// if the move is valid store it in the arrayOfAcceptableMoves array
					// NOTE: the array is of type boolean, the coordinates of the move itself are stored in the indexing of the array

					// Edge case: the dice are equal - have to check the value of one of the dice *1, 2, 3 & 4
					if (!(Dice.diceAreEqual()) && currentColor == 'R') {

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
				bearOffMoves(i);
			}
		}
		return arrayOfAcceptableMoves;

	}

	//Gets the available moves for if a counter is on the bar
	public static boolean[][] barMoves(){

		// clear the array of acceptable moves each time the function is called
		for(int row=0; row<28; row++)
		{
			for(int column=0; column<28; column++)
			{
				arrayOfAcceptableMoves[row][column] = false;
			}
		}

		char player = Backgammon.currentPlayer.getPlayerColor();

		if(player == 'W'){
			if(acceptableMove(Backgammon.counterMap[25 - dieValue1], 0)){
				arrayOfAcceptableMoves[26][25 - dieValue1] = true;
			}

			if(acceptableMove(Backgammon.counterMap[25 - dieValue2], 0)){
				arrayOfAcceptableMoves[26][25 - dieValue2] = true;
			}

			if(acceptableMove(Backgammon.counterMap[25 - (dieValue1 + dieValue2)], 0) && (dieValue1 + dieValue2) <= 6){
				arrayOfAcceptableMoves[26][25 - (dieValue1 + dieValue2)] = true;
			}
		} else {
			if(acceptableMove(Backgammon.counterMap[dieValue1], 0)){
				arrayOfAcceptableMoves[27][dieValue1] = true;
			}

			if(acceptableMove(Backgammon.counterMap[dieValue2], 0)){
				arrayOfAcceptableMoves[27][dieValue2] = true;
			}

			if(acceptableMove(Backgammon.counterMap[(dieValue1 + dieValue2)], 0) && (dieValue1 + dieValue2) <= 6){
				arrayOfAcceptableMoves[27][(dieValue1 + dieValue2)] = true;
			}
		}

		return arrayOfAcceptableMoves;
	}

	public static void bearOffMoves(int i){

		if(Backgammon.currentPlayer.getPlayerColor() == 'W'){
			if(validBearOff(i, Dice.getDie1Value())){
				arrayOfAcceptableMoves[i][0] = true;
			}else if(validBearOff(i, Dice.getDie2Value())){
				arrayOfAcceptableMoves[i][0] = true;
			}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())){
				arrayOfAcceptableMoves[i][0] = true;
			}

			if(Dice.diceAreEqual()){
				if(validBearOff(i, Dice.getDie1Value())){
					arrayOfAcceptableMoves[i][0] = true;
				}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())){
					arrayOfAcceptableMoves[i][0] = true;
				}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value())){
					arrayOfAcceptableMoves[i][0] = true;
				}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value() + Dice.getDie4Value())){
					arrayOfAcceptableMoves[i][0] = true;
				}
			}
		} else {
			if(validBearOff(i, Dice.getDie1Value())){
				arrayOfAcceptableMoves[i][25] = true;
			}else if(validBearOff(i, Dice.getDie2Value())){
				arrayOfAcceptableMoves[i][25] = true;
			} if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())){
				arrayOfAcceptableMoves[i][25] = true;
			}

			if(Dice.diceAreEqual()){
				if(validBearOff(i, Dice.getDie1Value())){
					arrayOfAcceptableMoves[i][25] = true;
				}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value())){
					arrayOfAcceptableMoves[i][25] = true;
				}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value())){
					arrayOfAcceptableMoves[i][25] = true;
				}else if(validBearOff(i, Dice.getDie1Value() + Dice.getDie2Value() + Dice.getDie3Value() + Dice.getDie4Value())){
					arrayOfAcceptableMoves[i][25] = true;
				}
			}
		}

	}

	public static boolean validBearOff(int currentPosition, int dieValue){
		if(Backgammon.counterMap[currentPosition].getColor() == Backgammon.currentPlayer.getPlayerColor()){
			if(Backgammon.currentPlayer.getPlayerColor() == 'W'){
				return currentPosition - dieValue <= 0;
			} else {
				return currentPosition + dieValue >= 25;
			}
		} else {
			return  false;
		}

	}

	// writes list of moves to the message box in the format specified on the Trello board
	public static void printMoves()
	{
		String result = "\n" + Backgammon.currentPlayer.playerName + ", Here is your list of possible moves:";
		
		for(int row=0; row<28; row++)
		{
			for(int column=0; column<28; column++)
			{
				if(arrayOfAcceptableMoves[row][column])
				{
					result += "\n" + row + " - " + column;
				}
			}
		}
		
		UI.messagePanelText.append(result);
	}
	
	private static boolean acceptableMove(CounterPositions position, int i)
	{
//		TODO
		boolean sameColor = position.getColor() == Backgammon.currentPlayer.getPlayerColor();
		boolean oneCounterOrEmpty = position.getNumCounters() <= 1;
		boolean bar = position.pipNumber == 26 || position.pipNumber == 27;
		boolean sameSpace = position.pipNumber == i;

		return (sameColor || oneCounterOrEmpty) && !bar && !sameSpace;
	}
}
