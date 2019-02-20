public class Player {

    public String playerName;
    public char playerColor;
    public String playerColorString;
    public int pipCount;
    public boolean moveMade = false;

    public Player(String playerName, char playerColor, int pipCount) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.pipCount = pipCount;
        setColorString();
    }

    private void setColorString(){
        if(playerColor == 'W'){
            playerColorString = "White";
        } else if ( playerColor == 'R'){
            playerColorString = "Red";
        }
    }

    public void playerMove(int currentPosition, int nextPosition){
        boolean CurrentPositionIsPlayerColor = Backgammon.counterMap[currentPosition].getColor() == getPlayerColor();
        boolean NextPositionAvailable = Backgammon.counterMap[nextPosition].getColor() == getPlayerColor() || Backgammon.counterMap[nextPosition].getColor() == 'B';

        if(CurrentPositionIsPlayerColor && NextPositionAvailable){
            Backgammon.moveCounter(currentPosition, nextPosition);
            moveMade = true;
        } else if(Backgammon.counterMap[nextPosition].getNumCounters() < 2) {
            Backgammon.takeCounter(currentPosition, nextPosition);
            moveMade = true;
        }
        else {
            UI.messagePanelText.append("\nLooks like that is not a valid move!");
        }
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerColor(char playerColor) {
        this.playerColor = playerColor;
    }

    public void setPipCount(int pipCount) {
        this.pipCount = pipCount;
    }

    public void setMoveMade(boolean moveMade) {
        this.moveMade = moveMade;
    }

    public boolean isMoveMade() {
        return moveMade;
    }

    public String getPlayerName() {
        return playerName;
    }

    public char getPlayerColor() {
        return playerColor;
    }

    public int getPipCount() {
        return pipCount;
    }
}
