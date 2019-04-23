
public class Bot0 implements BotAPI {

    private static final int BAR = 25;           // index of the BAR
    private static final int BEAR_OFF = 0;       // index of the BEAR OFF
    public static final int NUM_PIPS = 24;      // excluding BAR and BEAR OFF
    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    Bot0(PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }

    public String getName() {
        return "ArraysStartAt1";
    }

    public String getCommand(Plays possiblePlays) {

        int[] moveScores = assignScoresToMoves(possiblePlays);

        int chosenMove = 0;
        int highestScore = 0;
        //loop to find the move with the highest score
        for (int i = 0; i < moveScores.length; i++)
        {
            if (moveScores[i] > highestScore) {
                highestScore = moveScores[i];
                chosenMove = i;
            }
        }

        return Integer.toString(chosenMove);
    }
    
    //TODO
    // how do we call for a double?

    public String getDoubleDecision() 
    {
    	int winProbability = winProbability();
    	
    	if(winProbability < 66)
    	{
    		// we can choose to take or drop double
    	}
    	
    	if(winProbability >= 66 && winProbability < 75 )
    	{
    		// we want to call a double right away
    		return "y";
    	}
    	
    	if((winProbability >= 75 && winProbability < 80) || (winProbability >=80 )) //no gammon chances) )
    	{
    		// we want to call a double right away
    		return "y";
    	}
    	
    	if(winProbability >=80 )//significant gammon chances) )
    	{
    	    return "n";
    	}
    	
    	//TODO
    	//More decisions to be made, found in slide 18 of the bot slides
    	
    	
        return "n";
    }

    private int[] assignScoresToMoves(Plays possiblePlays){
        /*
        for each available move create an instance of a new board and pass it into
        calculate score.
        Create an array of size possiblePlays.number()
        Pass each new board into calculateScore and store these scores in the array.
         */
        int[] scores = new int[possiblePlays.number()];

        for (int i = 0; i < possiblePlays.number(); i++)
        {
            int[][] possibleMove = board.get(); //making copy so original board is not changed
            possibleMove = move(possibleMove, possiblePlays.get(i)); //making the move

            scores[i] = calculateScore(possibleMove);
        }

        return scores;
    }

    /** Assign scores to the different situations*/
    final int BLOCK = 2;
    final int BLOT = -1;
    final int PRIME = 0;
    final int ANCHOR = 0;
    final int HIT = 0;

    //Calculates the score of each move our player can make
    private int calculateScore(int[][] nextBoard) {
        /**
         * 1. Check for blots.
         * 2. Check for blocks.
         * 3. Check for 3 blocks next to each other.
         * 4. check if you send opponent checkers to the bar.
         */
        int[][] originalBoard = board.get();
        int score = 0;

        if (haveDoneAHit(nextBoard))
            score += HIT;

        for(int pip = 1; pip <= NUM_PIPS; pip++) {

            //if in the 4th quadrant x4
            if (pip <= 24 && pip >= 19) {
                score += 4 * score(nextBoard, pip);
            }
            //if in the 3rd quadrant x3
            else if (pip <= 18 && pip >= 13) {
                score += 3 * score(nextBoard, pip);
            }
            //if in the 2nd quadrant x2
            else if (pip <= 12 && pip >= 7) {
                score += 2 * score(nextBoard, pip);
            }
            //if in 1st quadrant(closest to the bear off) x1
            else if (pip <= 6 && pip >= 1) {
                score += score(nextBoard, pip);
            }
        }

        return score;
    }

    private int score(int[][] nextBoard, int pipNumber) {
        int score = 0;

        if (isBlock(nextBoard, pipNumber))
            score += BLOCK;

        if (isBlot(nextBoard, pipNumber))
            score -= BLOT;

        /**
         * Add more situations where points have to be added or subtracted to the score
         */

        return score;
    }


    //Returns probability of our bot to win. This is used when accepting or offering the doubling cube
    private int winProbability(){
        //TODO
        return 0;
    }

    private boolean haveDoneAHit(int[][] nextBoard) {
        boolean result = false;

        //if the difference between the number of checkers in the newBoard is >= to one then we sent one to the bar
        if (nextBoard[opponent.getId()][BAR] - board.getNumCheckers(opponent.getId(), BAR) >= 1)
            result = true;

        return result;
    }

    private boolean isBlot(int[][] nextBoard, int pipNumber) {
        boolean result = false;

        if (nextBoard[me.getId()][pipNumber] == 1) {
            result = true;
        }

        return result;
    }

    private boolean isBlock(int[][] nextBoard, int pipNumber) {
        boolean result = false;

        if (nextBoard[me.getId()][pipNumber] > 1)
            result = true;

        return result;
    }

    //Sd = number blocks p0 - number of blots p1
    private int blockBlotDifference(int[][] nextBoard)
    {
    	int blockCount = 0, blotCount = 0;
    	
    	for(int i=1; i<=NUM_PIPS; i++)
    	{
    		if(isBlock(nextBoard, i))
    			blockCount++;
    		
    		if(isBlot(nextBoard, i))
    			blotCount++;
    	}
    	
        return blockCount-blotCount;
    }

    //Function takes a PlayerAPI as its argument so we can calculate both players number of blocks in their home board
    private int homeBoardBlocks(int[][] nextBoard) 
    {
    	int runningSum = 0;
    	
    	for(int i=1; i<=6; i++)
    	{
    		if(nextBoard[me.getId()][i] > 1)
    			runningSum ++;
    	}
    	
    	
        return runningSum;
    }


    private int numCheckersInHome(int[][] nextBoard) {
        
    	int runningSum = 0;
    	
    	for(int i=1; i<=6; i++)
    	{
    		runningSum += nextBoard[me.getId()][i];
    	}
    	
    	
        return runningSum;
    }

    
    private int primeLength(int[][] nextBoard) {
         	
    	// we might want to return the pips at the beginning and end of the prime
    	int primeLength = 0;
//    	int beginningPipOfPrime;
//    	int endingPipOfPrime;
    	int currentPlayer = me.getId();
    	
    	// array for storing the lengths of the primes a player has on the board
    	int [] primeLengths = new int[12];
    	// variable count to increment the place in the array above to store the value of the prime lengths 
    	int count = 0;
    	
    	
    	for(int i=1; i<=NUM_PIPS; i++)
    	{
    		// if the currentPlayer has more than 1 checker on a position increment primeLength
    		if(nextBoard[currentPlayer][i] > 1)
    		{
    			primeLength++;
    		}
    		else
    		{
    			// this if is entered if a 'prime' of length 1 was found
    			// a prime of length 1 isn't really a prime so it resets primeLength to 0
    			if(primeLength == 1)
    			{
    				primeLength = 0;
    			}
    			// if a prime of length 2 or greater is found it's added to the primeLengths array in the next position
    			// primeLength is then reset to 0 to continue searching
    			else if(primeLength > 1)
    			{
    				primeLengths[count++] = primeLength;
    				primeLength = 0;
    			}
    			
    		}
    	}
    	
    	int maxPrimeLength = 0;
    	
    	for(int j=0; j<12; j++)
    	{
    		if(primeLengths[j] > maxPrimeLength)
    			maxPrimeLength = primeLengths[j];
    	}

        return maxPrimeLength;
    }

    private int pipCountDifference(int[][] nextBoard) 
    {
        int ourPipCount = 0, opponentPipCount = 0;
        
        for(int i=25; i>=0; i--)
        {
        	ourPipCount = ourPipCount + (nextBoard[me.getId()][i]*i);
        	opponentPipCount = opponentPipCount + (nextBoard[opponent.getId()][i]*i);
        }
        
        return ourPipCount - opponentPipCount;
    }


    private int calculateOpposingPip(int pip) {
        return 24-pip+1;
    }

    private int[][] move(int[][] possibleBoard, Move move) {
        possibleBoard[me.getId()][move.getFromPip()]--;
        possibleBoard[me.getId()][move.getToPip()]++;

        if (move.getToPip()< BAR && move.getToPip() > BEAR_OFF &&
                possibleBoard[opponent.getId()][calculateOpposingPip(move.getToPip())] == 1)
        {
            possibleBoard[opponent.getId()][calculateOpposingPip(move.getToPip())]--;
            possibleBoard[opponent.getId()][BAR]++;
        }
        return possibleBoard;
    }

    private int[][] move(int[][] possibleBoard, Play play) {
        for (Move move : play) {
            possibleBoard = move(possibleBoard, move);
        }
        return possibleBoard;
    }
}
