import java.util.Scanner;

/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

public class Backgammon
{

	public static CounterPositions[] counterMap = new CounterPositions[28];
    private static final int[] RESET = {0,2,0,0,0,0,5,0,3,0,0,0,5,5,0,0,0,3,0,5,0,0,0,0,2};
    private static final char[] RESET_COLORS = {'B', 'R', 'B', 'B', 'B', 'B', 'W', 'B', 'W', 'B', 'B', 'B', 'R', 'W', 'B', 'B', 'B', 'R', 'B', 'R', 'B', 'B', 'B', 'B', 'W'};

    public static Player player1 = new Player("Player 1", 'R', 167);
    public static Player player2 = new Player("Player 2", 'W', 167);
    public static Player currentPlayer = player1;

    public static void main(String [] args)
    {
        initializeBoard();
        UI.mainMenuUI();

    }

    // deSelects the green circle around the checker
    public static void deSelect()
    {
        for(int i=0; i<counterMap.length; i++)
        {
            counterMap[i].setSelected(false);
        }
    }

    //Given a number between one and 24 a counter if there is one will be sent to the respective bar
    public static void sendCounterToBar(int counterPosition)
    {
        if(counterMap[counterPosition].getNumCounters() > 0 && (counterPosition > 0 && counterPosition < 25))
        {
            int currentColour = counterMap[counterPosition].getColor();
            if(currentColour == 'R')
            {
                moveCounter(counterPosition, 27);
            }
            else if (currentColour == 'W')
            {
                moveCounter(counterPosition, 26);
            }
        }
    }

    //Given a position from 1 to 24 and there is a counter that counter will be sent to bear off
    public static void bearCounterOff(int counterPosition)
    {

        if(counterMap[counterPosition].getNumCounters() > 0 && (counterPosition > 0 && counterPosition < 25))
        {
            char currentColour = counterMap[counterPosition].getColor();
            if(currentColour == 'R')
            {
                moveCounter(counterPosition, 25);
            }
            else if (currentColour == 'W')
            {
                moveCounter(counterPosition, 0);
            }
        }
    }

    //Moves a counter from one position to another
    public static void moveCounter(int currentPosition, int nextPosition)
    {
        boolean inbounds = (currentPosition >= 0 && currentPosition <= 27) && (nextPosition >= 0 && nextPosition <= 27);

        if(inbounds){
            char currentColour = counterMap[currentPosition].getColor();
            counterMap[currentPosition].removeCounter();

            if (counterMap[nextPosition].getColor() == 'B')
            {
                counterMap[nextPosition].setColor(currentColour);
            }
            counterMap[nextPosition].addCounter();
        }
    }

    /*
    Method that sends a counter to the bar a replaces it with a counter
    of the opposing color
     */
    public static void takeCounter(int currentPosition, int nextPosition){
        sendCounterToBar(nextPosition);
        counterMap[nextPosition].setColor(counterMap[currentPosition].getColor());
        counterMap[currentPosition].removeCounter();
        counterMap[nextPosition].addCounter();
    }

    /*
    CounterMap is a CounterPositions array with 28 spaces That holds a x co-ordinate, a Y co-ordinate, Color,
    number of counters and whether it is on the top row or not
    CounterMap[0] is the white bear off location
    counterMap[1]-[24] is the pip positions
    counterMap[25] is the white bear off
    counterMap[26] is the locations for the white bar
    counterMap[27 is the location for the red bar
     */
    public static void initializeBoard(){

        //Filling counterMap with starting positions and number of checkers in each position
        for(int i=0 ;i<counterMap.length; i++)
        {
            counterMap[i] = null;
        }

        int x_Co = 1107;
        int y_Co = 625;
        final int TOP_Y_CO = 40;
        final int TRIANGLE_BASE = 88;
        final int BAR = 80;
        boolean topRow = false;

        for(int i=1; i<=24; i++) {
            if (i >= 1 && i <= 12) {
                counterMap[i] = new CounterPositions(x_Co, y_Co, RESET_COLORS[i], RESET[i], topRow, i);
                x_Co -= TRIANGLE_BASE;

                if (i == 6) x_Co -=  BAR;

                if (i == 12)
                {
                    y_Co = TOP_Y_CO;
                    topRow = true;
                    x_Co += TRIANGLE_BASE;
                }
            } else if (i >= 13 && i <= 24)
            {
                counterMap[i] = new CounterPositions(x_Co, y_Co, RESET_COLORS[i], RESET[i], topRow, i);
                x_Co += TRIANGLE_BASE;

                if (i == 18) x_Co += BAR;
            }
        }

        //White bear off
        counterMap[0] = new CounterPositions(1220, 615, 'W', 0, false, 0);
        counterMap[0].setBearoff(true);

        //Red bear off
        counterMap[25] = new CounterPositions(1220, 90, 'R', 0, true, 25);
        counterMap[25].setBearoff(true);

        //White bar
        counterMap[26] = new CounterPositions(580, 305, 'W', 0, false, 26);

        //Red bar
        counterMap[27] = new CounterPositions(580, 370, 'R', 0, true, 27);
    }
    
    
    public static void setCheatBoard()
    {    	
        final int[] CHEAT_SET = {2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,3};
        final char[] CHEAT_SET_COLORS = {'W', 'W', 'W', 'W', 'W', 'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'R', 'B', 'R', 'R'};


        int x_Co = 1107;
        int y_Co = 625;
        final int TOP_Y_CO = 40;
        final int TRIANGLE_BASE = 88;
        final int BAR = 80;
        boolean topRow = false;
        
        for(int i=1; i<=24; i++) 
        {
            if (i >= 1 && i <= 12) 
            {
                counterMap[i] = new CounterPositions(x_Co, y_Co, CHEAT_SET_COLORS[i], CHEAT_SET[i], topRow, i);
                x_Co -= TRIANGLE_BASE;

                if (i == 6) x_Co -=  BAR;

                if (i == 12)
                {
                    y_Co = TOP_Y_CO;
                    topRow = true;
                    x_Co += TRIANGLE_BASE;
                }
            } 
            else if (i >= 13 && i <= 24)
            {
                counterMap[i] = new CounterPositions(x_Co, y_Co, CHEAT_SET_COLORS[i], CHEAT_SET[i], topRow, i);
                x_Co += TRIANGLE_BASE;

                if (i == 18) x_Co += BAR;
            }
        }
        
      //White bear off
        counterMap[0] = new CounterPositions(1220, 615, 'W', 2, false, 0);
        counterMap[0].setBearoff(true);

        //Red bear off
        counterMap[25] = new CounterPositions(1220, 90, 'R', 3, true, 25);
        counterMap[25].setBearoff(true);

        //White bar
        counterMap[26] = new CounterPositions(580, 305, 'W', 3, false, 26);

        //Red bar
        counterMap[27] = new CounterPositions(580, 370, 'R', 3, true, 27);
        
        UI.rePaintMainPanel();
    }
    
    // called in UI.java at bottom of inputCommands (as this function is used after every iteration of game play/ every move)
    public static void finishGame(Player player)
    {
    	UI.messagePanelText.append("\nCongratulations " + player.playerName + ", You Win!!!");
    	
    	UI.messagePanelText.append("\nWould you like to play again?");
    	UI.messagePanelText.append("\nEnter 'yes' to play again or 'no' to exit the game");
    		
    	// NEEDS TO BE FIXED
    	// also need something to freeze the board once the game is over
    	String userResponse = UI.getUserInput();
    	
    	if(userResponse.equalsIgnoreCase("yes"))
    	{
    		initializeBoard();
            UI.mainMenuUI();
    	}
    	else if(userResponse.equalsIgnoreCase("no"))
    	{
    		System.exit(0);
    	}
    	
    	
    }
    
    
    
}