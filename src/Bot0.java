
public class Bot0 implements BotAPI {

    public static final int BAR = 25;           // index of the BAR
    public static final int BEAR_OFF = 0;       // index of the BEAR OFF
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

    public String getDoubleDecision() {
        // Add your code here
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

    //Calculates the score of each move our player can make
    private int calculateScore(int[][] board) {

        int block = 2;
        int blot = -1;

        for(int i=0; i<=25; i++){

        }

        return 0;
    }

    //Returns probability of our bot to win. This is used when accepting or offering the doubling cube
    private int winProbability(){
        //TODO
        return 0;
    }

    private boolean isBlot() {
        //TODO
        return false;
    }

    private boolean isBlock() {
        //TODO
        return false;
    }

    //Sd = number blocks p0 - number of blots p1
    private int blockBlotDifference(){
        //TODO
        return 0;
    }

    //Function takes a PlayerAPI as its argument so we can calculate both players number of blocks in their home board
    private int homeBoardBlocks(PlayerAPI player) 
    {
    	int runningSum = 0;
    	
    	for(int i=1; i<=6; i++)
    	{
    		if(board.getNumCheckers(player, i) > 1)
    			runningSum ++;
    	}
    	
    	//TODO
    	// FOR TESTING
//    	System.out.println("RunningSum = " + runningSum);
    	
        return runningSum;
    }

    //Function takes a PlayerAPI as its argument so we can calculate both players number of checkers in their home board
    private int numCheckersInHome(PlayerAPI player) {
        
    	int runningSum = 0;
    	
    	for(int i=1; i<=6; i++)
    	{
    		runningSum += board.getNumCheckers(player.getId(), i);
    	}
    	
    	//TODO
    	// FOR TESTING
//    	System.out.println("RunningSum = " + runningSum);
    	
        return runningSum;
    }

    // takes an argument PlayerAPI so we can calculate either players prime length
    private int primeLength(PlayerAPI player) {
         	
    	//TODO
    	// Haven't tested this method yet
    	// we might want to return the pips at the beginning and end of the prime
    	int primeLength = 0;
//    	int beginningPipOfPrime;
//    	int endingPipOfPrime;
    	int currentPlayer = player.getId();
    	
    	// array for storing the lengths of the primes a player has on the board
    	int [] primeLengths = new int[12];
    	// variable count to increment the place in the array above to store the value of the prime lengths 
    	int count = 0;
    	
    	
    	for(int i=1; i<=24; i++)
    	{
    		// if the currentPlayer has more than 1 checker on a position increment primeLength
    		if(board.getNumCheckers(currentPlayer, i) > 1)
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

    private int pipCountDifference() {
        //TODO
        return 0;
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
