
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
        Create an array of size Plays.size()
        Pass each new board into calculateScore and store these scores in the array.
         */
        int[] scores = new int[possiblePlays.number()];

        for (int i = 0; i < possiblePlays.number(); i++) {


        }

        return scores;
    }

    //Calculates the score of each move our player can make
    private int calculateScore(BoardAPI board){

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

    private int homeBoardBlocks() {
        //TODO
        return 0;
    }

    private int numCheckerInHome() {
        //TODO
        return 0;
    }

    private int primeLength() {
        //TODO
        return 0;
    }

    private int pipCountDifference() {
        //TODO
        return 0;
    }

    private int calculateOpposingPip(int pip) {
        return 24-pip+1;
    }

    public void move(int[][] possibleBoard, Move move) {
        possibleBoard[me.getId()][move.getFromPip()]--;
        possibleBoard[me.getId()][move.getToPip()]++;

        if (move.getToPip()< BAR && move.getToPip() > BEAR_OFF &&
                possibleBoard[opponent.getId()][calculateOpposingPip(move.getToPip())] == 1)
        {
            possibleBoard[opponent.getId()][calculateOpposingPip(move.getToPip())]--;
            possibleBoard[opponent.getId()][BAR]++;
        }
    }

    public void move(int[][] possibleBoard, Play play) {
        for (Move move : play) {
            move(possibleBoard, move);
        }
    }
}
