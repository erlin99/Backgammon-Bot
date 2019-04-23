
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
        String returning = new String();

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

        if(checkIfWeShouldCallDouble())
        {
        	returning = "double";
        }
        else
        	returning =  Integer.toString(chosenMove + 1);

        return returning;
    }
    
    public boolean checkIfWeShouldCallDouble()
    {
    	int winProbability = winProbability(me.getId());

    	if((cube.isOwned() && cube.getOwnerId() == me.getId()) || !(cube.isOwned()))
    	{
        	// if both players are 2 or less points from winning
        	if((me.getScore() >= match.getLength()-2) && (opponent.getScore() >= match.getLength()-2))
        	{
        		if(winProbability < 50)
        			return false;

        		if(winProbability >= 50 && winProbability < 75)
        			return true;

        		if(winProbability >= 75 && winProbability < 100)
        			return true;
        	}
        	else
        	{
        		if(winProbability < 66)
            		return false;

            	if(winProbability >= 66 && winProbability < 75 )
            		return true;

            	//TODO
            	// calculate gammon chances
            	if((winProbability >= 75 && winProbability < 80) || (winProbability >=80 ) && gammonChance(me.getId()) < 75) //&& no gammon chances) )
            		return true;

            	if(winProbability >=80 && gammonChance(me.getId()) > 75)//&& significant gammon chances) )
            	   return false;
        	}
        	
        	//TODO
        	// if statement for post crawford rule
        	// method will return true
        	return false;
    	}
    	else
    		return false;
    }

    public String getDoubleDecision() 
    {
    	int winProbability = winProbability(me.getId());
    	
    	// if both players are 2 or less points from winning
    	if((me.getScore() >= match.getLength()-2) && (opponent.getScore() >= match.getLength()-2))
    	{
    		if(winProbability < 50)
    		{
    			// we can decide if we want to take or drop double
    		}

    		if(winProbability >= 50 && winProbability < 75)
    			return "y";

    		if(winProbability >= 75 && winProbability < 100)
    			return "n";
    	}
    	else
    	{
    		if(winProbability < 35 )
        		// we can choose to take or drop double
                return "n";

        	if((winProbability >= 35 && winProbability <= 45) && gammonChance(opponent.getId()) <= 75 )
        		return "n";

        	if(winProbability >= 35)
        	    return "y";
    	}
    	
    	//TODO
    	// if statement for post crawford rule
    	// method will return "y"
    	
        return "n";
    }

    private int[] assignScoresToMoves(Plays possiblePlays){

        int[] scores = new int[possiblePlays.number()];

        for (int i = 0; i < possiblePlays.number(); i++)
        {
            int[][] possibleMove = board.get(); //making copy so original board is not changed
            possibleMove = move(possibleMove, possiblePlays.get(i)); //making the move

            scores[i] = calculateScore(possibleMove, me.getId());
        }

        return scores;
    }

    /** Assign scores to the different situations*/
    final int BLOCK = 5;
    final int BLOT = 15;
    final int PRIME = 0;
    final int ANCHOR = 0;
    final int HIT = 10;

    //Calculates the score of each move our player can make
    private int calculateScore(int[][] nextBoard, int playerID) {
        /**
         * 1. Check for blots.
         * 2. Check for blocks.
         * 3. Check for 3 blocks next to each other.
         * 4. check if you send opponent checkers to the bar.
         */
        int[][] originalBoard = board.get();
        int score = 0;

        if (haveDoneAHit(nextBoard, playerID))
            score += HIT;

        score -= pipCountDifference(nextBoard, playerID);

        score += blockBlotDifference(nextBoard, playerID);

        score += homeBoardBlocks(nextBoard, playerID);

        score += numCheckersInHome(nextBoard, playerID);

        for(int pip = 1; pip <= NUM_PIPS; pip++) {

            //if in the 4th quadrant x4
            if (pip <= 24 && pip >= 19) {
                score += 4 * score(nextBoard, pip, playerID);
            }
            //if in the 3rd quadrant x3
            else if (pip <= 18 && pip >= 13) {
                score += 3 * score(nextBoard, pip, playerID);
            }
            //if in the 2nd quadrant x2
            else if (pip <= 12 && pip >= 7) {
                score += 2 * score(nextBoard, pip, playerID);
            }
            //if in 1st quadrant(closest to the bear off) x1
            else if (pip <= 6 && pip >= 1) {
                score += score(nextBoard, pip, playerID);
            }
        }

        return score;
    }

    private int score(int[][] nextBoard, int pipNumber, int playerID) {
        int score = 0;

        if (isBlock(nextBoard, pipNumber, playerID))
            score += BLOCK;

        if (isBlot(nextBoard, pipNumber, playerID))
            score -= BLOT;

        /**
         * Add more situations where points have to be added or subtracted to the score
         */

        return score;
    }


    //Returns probability of our bot to win. This is used when accepting or offering the doubling cube
    private int winProbability(int playerID){
        //TODO

        int player1Score = calculateScore(board.get(), me.getId());
        int player2Score = calculateScore(board.get(), opponent.getId());

        System.out.println("Player 1 current board score: " + player1Score);
        System.out.println("Player 2 current board score: " + player2Score);

        if(playerID == 0){
            return player1Score;
        } else {
            return player2Score;
        }
    }

    private boolean haveDoneAHit(int[][] nextBoard, int playerID) {
        boolean result = false;
        int opponentID = opponentOf(playerID);

        //if the difference between the number of checkers in the newBoard is >= to one then we sent one to the bar
        if (nextBoard[opponentID][BAR] - board.getNumCheckers(opponentID, BAR) >= 1)
            result = true;

        return result;
    }

    private boolean isBlot(int[][] nextBoard, int pipNumber, int player) {
        boolean result = false;

        if (nextBoard[player][pipNumber] == 1) {
            result = true;
        }

        return result;
    }

    private boolean isBlock(int[][] nextBoard, int pipNumber, int player) {
        boolean result = false;

        if (nextBoard[player][pipNumber] > 1)
            result = true;

        return result;
    }

    //Sd = number blocks p0 - number of blots p1
    private int blockBlotDifference(int[][] nextBoard, int playerID)
    {
        //assigns an id to opponent player
        int opponentID = opponentOf(playerID);

    	int myBlockCount = 0, opponentBlotCount = 0;
    	
    	for(int pip = 1; pip <= NUM_PIPS; pip++)
    	{
    		if(isBlock(nextBoard, pip, playerID))
    			myBlockCount++;
    		
    		if(isBlot(nextBoard, pip, opponentID))
    			opponentBlotCount++;
    	}
    	
        return myBlockCount - opponentBlotCount;
    }

    //Function takes a PlayerAPI as its argument so we can calculate both players number of blocks in their home board
    private int homeBoardBlocks(int[][] nextBoard, int playerID)
    {
    	int numberOfBlocks = 0;
    	
    	for(int i=1; i<=6; i++)
    	{
    		if(nextBoard[playerID][i] > 1)
    			numberOfBlocks++;
    	}

        return numberOfBlocks;
    }

    private int numCheckersInHome(int[][] nextBoard, int playerID) {
        
    	int numberOfCheckers = 0;
    	
    	for(int i=1; i<=6; i++)
    	{
    		numberOfCheckers += nextBoard[playerID][i];
    	}

        return numberOfCheckers;
    }

    private int primeLength(int[][] nextBoard, int playerID) {
         	
    	// we might want to return the pips at the beginning and end of the prime
    	int primeLength = 0;
//    	int beginningPipOfPrime;
//    	int endingPipOfPrime;
    	int currentPlayer = playerID;
    	
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

    private int pipCountDifference(int[][] nextBoard, int playerID) {

        int opponentID = opponentOf(playerID);

        int ourPipCount = 0, opponentPipCount = 0;
        
        for(int i=25; i>=0; i--)
        {
        	ourPipCount = ourPipCount + (nextBoard[playerID][i]*i);
        	opponentPipCount = opponentPipCount + (nextBoard[opponentID][i]*i);
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

    private int gammonChance(int playerID){

        int gammonProbability = 0;
        int opponentID = opponentOf(playerID);

        boolean opponentInOurHalf = false;
        //If the opponent has 0 checkers beared off gammon is true
        boolean gammon = board.get()[opponentID][0] == 0;

        //Calculate the pipcount of both players
        int ourPipCount = 0;
        int opponentPipCount = 0;

        //if a gammon is not possible return 0 otherwise calculate a score for a gammon
        if(!gammon){
            return 0;
        } else {

            for (int i = 25; i >= 0; i--) {
                ourPipCount = ourPipCount + (board.get()[playerID][i] * i);
                opponentPipCount = opponentPipCount + (board.get()[opponentID][i] * i);
            }

            //If the opponent has checkers in our half of the board set a boolean to true
            for (int i = 24; i >= 13; i--) {
                if (board.get()[opponentID][i] > 0) {
                    opponentInOurHalf = true;
                }
            }

            //If our pipCount is low i.e close to winning we add to pipcount score depending on how close we are
            if (ourPipCount <= 20) {
                gammonProbability += (20 + (20 - ourPipCount));
            }

            //if the opponent is in our half or on the bar 40 is added to the gammon as it significantly increases our chances
            if (opponentInOurHalf) {
                gammonProbability += 40;
            }

            //if we have a difference of at least 10 in our pipcount we add teh differnce to our gammon probability
            if (pipCountDifference(board.get(), playerID) < -10) {
                gammonProbability -= pipCountDifference(board.get(), me.getId());
            }

            return gammonProbability;
        }
    }

    private int opponentOf(int playerID) {

        int opponentID;

        if(playerID == 0)
            opponentID = 1;
        else
            opponentID = 0;

        return opponentID;
    }

    //Inner class to hold the length and start and end pip of the primes
    private class Prime {

        int length;
        int startPip;
        int endPip;

        Prime(int length, int startPip, int endPip) {
            this.length = length;
            this.startPip = startPip;
            this.endPip = endPip;
        }

        public int getLength() {
            return length;
        }

        public int getStartPip() {
            return startPip;
        }

        public int getEndPip() {
            return endPip;
        }
    }
}
