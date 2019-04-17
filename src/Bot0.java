
public class Bot0 implements BotAPI {

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
        return "ArraysStartAt1"; // must match the class name
    }

    public String getCommand(Plays possiblePlays) {
        chooseMove();
        // Add your code here
        return "1";
    }

    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }


    public void chooseMove(){

        /*System.out.println(info.getAllInfo());
        System.out.println(info.getLatestInfo());*/



    }

    public int calucalteScore(BoardAPI board){

        int block = 2;
        int blot = -1;


        int[][] possibleBoard = board.get();


        for(int i=0; i<=25; i++){

        }

        return 0;
    }
}
