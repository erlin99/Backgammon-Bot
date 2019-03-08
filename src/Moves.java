
//static class as no need to create an instantiation of it in any other part of the application
public class Moves 
{
	public static CounterPositions[] counterMap = new CounterPositions[28];
	private static boolean[][] arrayOfAcceptableMoves = new boolean[28][28];
	
	// fills the 'arrayOfAcceptableMoves' with acceptable moves
	public static void getMoves()
	{
		char currentColor = Backgammon.currentPlayer.getPlayerColor(); 
		int dieValue1 = Dice.getDie1Value();
		int dieValue2 = Dice.getDie2Value();

		// clear the array of acceptable moves each time the function is called
		for(int row=0; row<28; row++)
		{
			for(int column=0; column<28; column++)
			{
				arrayOfAcceptableMoves[row][column] = false;
			}
		}

		for(int i=1; i<=24; i++)
		{
			if(Backgammon.counterMap[i].getColor() == currentColor)
			{
				// check dieValue1, dieValue2, and dieValue1 + dieValue2 from this location 'i'
				// if the move is valid store it in the arrayOfAcceptableMoves array
				// NOTE: the array is of type boolean, the coordinates of the move itself are stored in the indexing of the array
				
				// Edge case: the dice are equal - have to check the value of one of the dice *1, 2, 3 & 4
				if(!(Dice.diceAreEqual()))
				{
					// this if statement stops an ArrayIndexOutOfBoundsException
					if(i+dieValue1 < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+dieValue1]))
						{
							arrayOfAcceptableMoves[i][i+dieValue1] = true;
						}
					}
					
					if(i+dieValue2 < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+dieValue2]))
						{
							arrayOfAcceptableMoves[i][i+dieValue2] = true;
						}
					}
					
					if(i+dieValue1+dieValue2 < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+dieValue1+dieValue2]))
						{
							arrayOfAcceptableMoves[i][i+dieValue1+dieValue2] = true;
						}
					}
				}
				
				else if(Dice.diceAreEqual())
				{
					if(i+dieValue1 < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+dieValue1]))
						{
							arrayOfAcceptableMoves[i][i+dieValue1] = true;
						}
					}
					
					if(i+(dieValue1*2) < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+(dieValue1*2)]))
						{
							arrayOfAcceptableMoves[i][i+(dieValue1*2)] = true;
						}
					}
					
					if(i+(dieValue1*3) < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+(dieValue1*3)]))
						{
							arrayOfAcceptableMoves[i][i+(dieValue1*3)] = true;
						}
					}
					
					if(i+(dieValue1*3) < Backgammon.counterMap.length)
					{
						if(acceptableMove(Backgammon.counterMap[i+(dieValue1*3)]))
						{
							arrayOfAcceptableMoves[i][i+(dieValue1*3)] = true;
						}
					}
				}
			}
		}
		
		printMoves();
	}
	
	// writes list of moves to the message box in the format specified on the Trello board
	public static void printMoves()
	{
		String result = "\n" + Backgammon.currentPlayer.playerName + ", here is your list of possible moves";
		
		for(int row=0; row<28; row++)
		{
			for(int column=0; column<28; column++)
			{
				if(arrayOfAcceptableMoves[row][column] == true)
				{
					result += "\n" + row + " - " + column;
				}
			}
		}
		
		UI.messagePanelText.append(result);
	}
	
	private static boolean acceptableMove(CounterPositions position)
	{
//		TODO
		return true;
	}
}
