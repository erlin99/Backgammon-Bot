/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
public class Player {

    public String playerName;
    public char playerColor;
    public String playerColorString;
    public int pipCount;
    public boolean moveMade = false;
    public static int currentPosition = -1;
    public static int nextPosition;
    public static int points = 0;

    public Player(String playerName, char playerColor, int pipCount) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.pipCount = pipCount;
        setColorString();
    }

    /*
    Method which allows player to click to move pieces. This is called from the action listener
    of each button on the board
     */
    public void clickMove(int position) {

        if(!moveMade) {
            if(currentPosition == -1) {
                currentPosition = position;
                if(Backgammon.counterMap[currentPosition].getColor() != Backgammon.currentPlayer.getPlayerColor()) {
                    currentPosition = -1;
                } else {
                    Backgammon.counterMap[currentPosition].setSelected(true);
                }
            } else if(position == currentPosition) {
                currentPosition = -1;
                Backgammon.deSelect();
            }
            else {
                nextPosition = position;
                if(Backgammon.counterMap[nextPosition].getColor() == Backgammon.currentPlayer.getPlayerColor() || Backgammon.counterMap[nextPosition].getColor() == 'B' || Backgammon.counterMap[nextPosition].getNumCounters() < 2) {
                    playerMove(currentPosition, nextPosition);
                }
            }
        }
        UI.rePaintMainPanel();
    }

    private void setColorString() {
        if(playerColor == 'W') {
            playerColorString = "White";
        } else if ( playerColor == 'R') {
            playerColorString = "Red";
        }
    }

    //Sets the dice so that if any dice are used they disappear and can't be used again
    public static void setDice(int selectedPosition, int movePosition, boolean bar) {

        //Creating a variable distance moved and seeing which dice have been used in this move
        int distanceMoved = 0;

        if(Backgammon.currentPlayer.getPlayerColor() == 'W') {
            distanceMoved = selectedPosition - movePosition;
        } else {
            distanceMoved = movePosition - selectedPosition;
        }

        //if there is a counter on the bar then barMove is given the value of the dice used
        int barMove = 0;
        if(bar) {
            if(Backgammon.currentPlayer.getPlayerColor() == 'W') {
                barMove = 25 - movePosition;
            } else {
                barMove = movePosition;
            }
        }

        //If the player bears off the die or dice that is used is then set to 0
        //The two dice are compared to see if the smaller one can be used or if its the bigger one used or if its a combination of both
        if((movePosition == 25 || movePosition == 0) && (distanceMoved != Dice.getDie1Value() && distanceMoved != Dice.getDie2Value() && distanceMoved != (Dice.getDie1Value() + Dice.getDie2Value()))){
            if(Backgammon.currentPlayer.getPlayerColor() == 'W'){

                distanceMoved = selectedPosition;
            } else {
                distanceMoved = 25 - selectedPosition;
            }

            int greaterDice;
            int smallerDice;

            if(Dice.getDie1Value() > Dice.getDie2Value()) {
                greaterDice = Dice.getDie1Value();
                smallerDice = Dice.getDie2Value();
            } else {
                greaterDice = Dice.getDie2Value();
                smallerDice = Dice.getDie1Value();
            }

            if(distanceMoved < smallerDice) {
                if(smallerDice == Dice.getDie1Value()) {
                    Dice.setDieValue1(0);
                } else {
                    Dice.setDieValue2(0);
                }
            } else if(distanceMoved < greaterDice) {
                if(greaterDice == Dice.getDie1Value()) {
                    Dice.setDieValue1(0);
                } else {
                    Dice.setDieValue2(0);
                }
            } else if(distanceMoved < smallerDice + greaterDice) {
                Dice.setDieValue1(0);
                Dice.setDieValue2(0);
            }
        }

        //These if statements check which dice has been used in the move and then sets that dice to 0
        // this makes the correct die disappear from the board once used
        if(bar && barMove == Dice.getDie1Value()) {
            Dice.setDieValue1(0);
        }
        else if(bar && barMove == Dice.getDie2Value()) {
            Dice.setDieValue2(0);
        }
        else if(bar && (barMove == Dice.getDie1Value() + Dice.getDie2Value())) {
            Dice.setDieValue1(0);
            Dice.setDieValue2(0);
            Dice.setDieValue3(0);
            Dice.setDieValue4(0);
        }
        else if(distanceMoved == Dice.getDie1Value()) {
            Dice.setDieValue1(0);
        }
        else if(distanceMoved == Dice.getDie2Value()) {
            Dice.setDieValue2(0);
        }
        else if(distanceMoved == Dice.getDie2Value() + Dice.getDie1Value()) {
            Dice.setDieValue1(0);
            Dice.setDieValue2(0);
        }

        // this checks to see if the game is finished
        // if game isn't over, it will post/print the moves
        postMoves();

        //Resets currentPosition and nextPosition so another move can be made
        currentPosition = -1;
        nextPosition = 0;
    }

    // same as the setDice function only it deals with the edge case of when the 2 die values are equal
    public static void setDiceIfDoubles(int selectedPosition, int movePosition, boolean bar) {

        //Creating a variable distance moved and seeing which dice have been used in this move
        int distanceMoved = 0;
        if(Backgammon.currentPlayer.getPlayerColor() == 'W') {
            distanceMoved = selectedPosition - movePosition;
        } else {
            distanceMoved = movePosition - selectedPosition;
        }

        //if there is a counter on the bar then barMove is given the value of the dice used
        int barMove = 0;
        if(bar) {
            if(Backgammon.currentPlayer.getPlayerColor() == 'W') {
                barMove = 25 - movePosition;
            } else {
                barMove = movePosition;
            }
        }

        //This logic sets the dice's value for when a player is moving off the bar and has doubles
        if(bar ) {
            if (barMove == Dice.getDie1Value()) {
                if (Dice.getDie4Value() != 0) {
                    Dice.setDieValue4(0);
                } else if (Dice.getDie3Value() != 0) {
                    Dice.setDieValue3(0);
                } else if (Dice.getDie2Value() != 0) {
                    Dice.setDieValue2(0);
                } else if (Dice.getDie1Value() != 0) {
                    Dice.setDieValue1(0);
                }

            } else if (barMove == Dice.getDie1Value() * 2) {
                if (Dice.getDie4Value() != 0 && Dice.getDie3Value() != 0) {
                    Dice.setDieValue4(0);
                    Dice.setDieValue3(0);
                } else if (Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                } else if (Dice.getDie2Value() != 0 && Dice.getDie1Value() != 0) {
                    Dice.setDieValue2(0);
                    Dice.setDieValue1(0);
                }
            } else if (barMove == Dice.getDie1Value() * 3) {
                if (Dice.getDie4Value() != 0 && Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue4(0);
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                } else if (Dice.getDie1Value() != 0 && Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue1(0);
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                }
            } else if (barMove == Dice.getDie1Value() * 4) {
                Dice.setDieValue4(0);
                Dice.setDieValue3(0);
                Dice.setDieValue2(0);
                Dice.setDieValue1(0);
            }
        }
        //This sets the players dice for sending a counter to bear off with doubles
        //The if statement also catches to see if the values are a multiple of the dice and if so use the next if statement
        else if((movePosition == 25 || movePosition == 0) && (distanceMoved != Dice.getDie1Value() && distanceMoved != Dice.getDie1Value()*2 && distanceMoved != Dice.getDie1Value()*3 && distanceMoved != Dice.getDie1Value()*4)){
            if(distanceMoved < Dice.getDie1Value()){
                if (Dice.getDie4Value() != 0) {
                    Dice.setDieValue4(0);
                } else if (Dice.getDie3Value() != 0) {
                    Dice.setDieValue3(0);
                } else if (Dice.getDie2Value() != 0) {
                    Dice.setDieValue2(0);
                } else if (Dice.getDie1Value() != 0) {
                    Dice.setDieValue1(0);
                }
            }
            else if(distanceMoved < Dice.getDie1Value()*2) {
                if (Dice.getDie4Value() != 0 && Dice.getDie3Value() != 0) {
                    Dice.setDieValue4(0);
                    Dice.setDieValue3(0);
                } else if (Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                } else if (Dice.getDie2Value() != 0 && Dice.getDie1Value() != 0) {
                    Dice.setDieValue2(0);
                    Dice.setDieValue1(0);
                }
            }
            else if(distanceMoved < Dice.getDie1Value()*3) {
                if (Dice.getDie4Value() != 0 && Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue4(0);
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                } else if (Dice.getDie1Value() != 0 && Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue1(0);
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                }
            }
            else if(distanceMoved < Dice.getDie1Value()*4) {
                Dice.setDieValue1(0);
                Dice.setDieValue2(0);
                Dice.setDieValue3(0);
                Dice.setDieValue4(0);
            }
        }
        else {
            //sets the dice from 4-1 to 0 depending on how many dice have been used in a move
            if (distanceMoved == Dice.getDie1Value()) {
                if (Dice.getDie4Value() != 0) {
                    Dice.setDieValue4(0);
                } else if (Dice.getDie3Value() != 0) {
                    Dice.setDieValue3(0);
                } else if (Dice.getDie2Value() != 0) {
                    Dice.setDieValue2(0);
                } else if (Dice.getDie1Value() != 0) {
                    Dice.setDieValue1(0);
                }

            } else if (distanceMoved == Dice.getDie1Value() * 2) {
                if (Dice.getDie4Value() != 0 && Dice.getDie3Value() != 0) {
                    Dice.setDieValue4(0);
                    Dice.setDieValue3(0);
                } else if (Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                } else if (Dice.getDie2Value() != 0 && Dice.getDie1Value() != 0) {
                    Dice.setDieValue2(0);
                    Dice.setDieValue1(0);
                }
            } else if (distanceMoved == Dice.getDie1Value() * 3) {
                if (Dice.getDie4Value() != 0 && Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue4(0);
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                } else if (Dice.getDie1Value() != 0 && Dice.getDie3Value() != 0 && Dice.getDie2Value() != 0) {
                    Dice.setDieValue1(0);
                    Dice.setDieValue3(0);
                    Dice.setDieValue2(0);
                }
            } else if (distanceMoved == Dice.getDie1Value() * 4) {
                Dice.setDieValue4(0);
                Dice.setDieValue3(0);
                Dice.setDieValue2(0);
                Dice.setDieValue1(0);
            }
        }

        //Resets currentPosition and nextPosition so another move can be made
        currentPosition = -1;
        nextPosition = 0;

        postMoves();
    }

    public static void postMoves(){
        if(!UI.gameOver){
            Moves.getMoves();
            Moves.printMoves();
        }
    }

    //Removes a counter from current Position and adds it to next positions
    public static void playerMove(int selectedPosition, int movePosition) {
        //Resets the visual of the board
        Backgammon.deSelect();
        UI.rePaintMainPanel();

        Boolean bar = Backgammon.isBarred();

        if(Backgammon.isBarred()) {
            //If the move is valid move it there. If there is only one counter take that counter
            if(Moves.getMoves()[selectedPosition][movePosition]) {
                if(Backgammon.counterMap[movePosition].getNumCounters() < 2 && Backgammon.counterMap[movePosition].getColor() != Backgammon.currentPlayer.getPlayerColor()) {
                    Backgammon.takeCounter(selectedPosition, movePosition);
                }
                else {
                    Backgammon.moveCounter(selectedPosition, movePosition);
                }
            }
            else {
                UI.messagePanelText.append("\nLooks like that is not a valid move bar!");
            }
        }
        else if(Moves.getMoves()[selectedPosition][movePosition]) {
            if(Backgammon.counterMap[movePosition].getNumCounters() < 2 && Backgammon.counterMap[movePosition].getColor() != Backgammon.currentPlayer.getPlayerColor()) {
                Backgammon.takeCounter(selectedPosition, movePosition);
            }
            else {
                Backgammon.moveCounter(selectedPosition, movePosition);
            }
        }
        else {
            UI.messagePanelText.append("\nLooks like that is not a valid move!");
        }
        if(Dice.diceAreEqual()) {
            setDiceIfDoubles(selectedPosition, movePosition, bar);
        } else {
            setDice(selectedPosition, movePosition, bar);
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
