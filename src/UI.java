/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.Timer;

public class UI {

    private static final int BOARD_WIDTH = 1572;
    private static final int BOARD_HEIGHT = 805;
    private static final int BIG_FONT = 18;
    private static final int SMALL_FONT = 15;
    public static String userResponse = new String();
    public static JFrame frame = new JFrame();
    public static JTextArea messagePanelText = new JTextArea("",22,16);

    public static JLabel player1PipPanelText = new JLabel( );
    public static JLabel player2PipPanelText = new JLabel();
    public static JLabel timerPanelText = new JLabel();

    public static Timer timer = new Timer();
    public static String startTime = "00:00:00";
    public static int seconds = 0;

    // when either of the bear offs contain 15 counters this changes to true
    public static boolean gameOver = false;

    //Used to check whether a game has been played and if it has the board won't be reinitialized
    public static boolean gamePlayed = false;

    public static boolean gameFinished = false;
    
    public static JPanel mainPanel = new JPanel(new BorderLayout());

    public static String getUserInput(){
        return userResponse;
    }

    public static void inputCommands(String userResponse) {

        int currentPosition;
        int nextPosition;

        Scanner scanner = new Scanner(userResponse).useDelimiter("\\s* \\s*");

        if(gameFinished && scanner.hasNext()){

            Backgammon.resetDoublingCubeValue();

            messagePanelText.setText("");
            messagePanelText.append("A new game has started. The scores are \n" + Backgammon.player1.getPlayerName()
                    + ": " + Backgammon.player1.points + "\n " + Backgammon.player2.getPlayerName() + ": " + Backgammon.player2.points + "\n");

            gameFinished = false;

            //method called to set up the board to its original state
            mainPanelSetUp();

            scanner = new Scanner("");
        }

        //if user types quit exit the program
        else if(userResponse.equalsIgnoreCase("quit"))
            System.exit(0);
        
        else if(userResponse.equalsIgnoreCase("cheat"))
            Backgammon.setCheatBoard();
        
        else if(userResponse.equalsIgnoreCase("next")) {
            next();
        } 
        else if(userResponse.equalsIgnoreCase("yes") && gameOver) {
        	// added to ensure that if the user replays the game that gameOver is no longer set to true
        	gameOver = false;

            //When replay is selected the board is reset and the main menu is brough back up
            gamePlayed = true;
            Backgammon.initializeBoard();
            UI.mainMenuUI();

            //resets the timer for the next game
            seconds = 0;

        	/*if(Backgammon.player1.getPlayerName() != "" && Backgammon.player2.getPlayerName() != "")
        	{
        		Backgammon.initializeBoard();
                initializeUI();
        	}*/
            
        }
        else if(userResponse.equalsIgnoreCase("no") && gameOver) {
            System.exit(0);
        }
        
        // new sprint 4 code, ensures the user enters either 'yes' or 'no' when the game is over
        // if they don't this code runs, asking them to enter a valid input
        else if(gameOver && !(userResponse.equalsIgnoreCase("yes") || userResponse.equalsIgnoreCase("no")))
        {
        	UI.messagePanelText.append("\nYou have entered an invalid input");
        	UI.messagePanelText.append("\nPlease enter either 'yes' to play again, or 'no' to exit the game");
        	
        	UI.messagePanelText.append("\nWould you like to play again?");
            UI.messagePanelText.append("\nEnter 'yes' to play again or 'no' to exit the game");
        }
        	
        // new sprint 4 code, calls the doubleScore method if the user enters double
        else if(userResponse.equalsIgnoreCase("double"))
        {
        	Backgammon.requestDoubleScore();
        }
        
        else if(userResponse.equalsIgnoreCase("accept") && Backgammon.cubeRequest)
        {
        	Backgammon.doubleScore();
        }
        
        else if(userResponse.equalsIgnoreCase("reject") && Backgammon.cubeRequest)
        {
        	finishGame(Backgammon.currentPlayer);
        }
        	
        	
        else if(scanner.hasNext()) {
            String response = scanner.next();
            if (response.equalsIgnoreCase("bar") && !Backgammon.currentPlayer.isMoveMade()) {
                try {
                    nextPosition = scanner.nextInt();

                    if(Backgammon.currentPlayer.playerColor == 'W') {
                        currentPosition = 26;
                    } else {
                        currentPosition = 27;
                    }

                    if(Moves.getMoves()[currentPosition][nextPosition]) {
                        Backgammon.moveCounter(currentPosition, nextPosition);

                        if(Dice.diceAreEqual()) {
                            Player.setDiceIfDoubles(currentPosition, nextPosition, false);
                        } else {
                            Player.setDice(currentPosition, nextPosition, false);
                        }
                    }
                } catch(java.util.NoSuchElementException ex) {
                    messagePanelText.append("\n-Please enter your move in the format: bar 2");
                }
            } else if (response.equalsIgnoreCase("off") && !Backgammon.currentPlayer.isMoveMade()) {
                try {
                    currentPosition = scanner.nextInt();

                    if(Backgammon.currentPlayer.playerColor == 'W') {
                        nextPosition = 0;
                    } else {
                        nextPosition = 25;
                    }

                    if(Moves.getMoves()[currentPosition][nextPosition]) {
                        Backgammon.bearCounterOff(currentPosition);
                        if(Dice.diceAreEqual()) {
                            Player.setDiceIfDoubles(currentPosition, nextPosition, false);
                        } else {
                            Player.setDice(currentPosition, nextPosition, false);
                        }
                    }
                } catch(java.util.NoSuchElementException ex) {
                    messagePanelText.append("\n-Please enter your move in the format: bar 2");
                }
            }
            //If the user types in move followed by the position of the checker they want to
            //move followed by the position they wish it to move to the checker will move
            if(scanner.hasNext()) {
                if(response.equalsIgnoreCase("move") && !Backgammon.currentPlayer.isMoveMade()) {
                    try {
                        currentPosition = scanner.nextInt();
                        nextPosition = scanner.nextInt();

                        if(currentPosition < 1 || currentPosition > 24 || nextPosition < 1 || nextPosition > 24) {
                            messagePanelText.append("\n-Please enter your move between 1-24");
                        }
                        else if (!Moves.getMoves()[currentPosition][nextPosition]) {
                            messagePanelText.append("\n-Please enter a valid move");
                        }
                        else {
                            Boolean bar = Backgammon.isBarred();
                            Player.playerMove(currentPosition, nextPosition);
                            if(Dice.diceAreEqual()) {
                                Player.setDiceIfDoubles(currentPosition, nextPosition, bar);
                            } else {
                                Player.setDice(currentPosition, nextPosition, bar);
                            }

                            Backgammon.currentPlayer.playerMove(currentPosition, nextPosition);
                            frame.repaint();
                        }
                    } catch(java.util.NoSuchElementException ex) {
                        messagePanelText.append("\n-Please enter your move in the format: move 1 2");
                    }
                }
            } else { //Letting user select their legal play by typing the corresponding letter A - Z
                try {
                    response = response.toUpperCase().replaceAll("\\s", "");
                    int moveNumber = 0;
                    if (response.length() > 1) {
                        moveNumber = 26 * (response.length()-1);
                    }
                    //converting last character to ascii code
                    int ascii = (int) response.charAt(response.length()-1);
                    moveNumber += ascii - 64;

                    if (moveNumber > Moves.possibleMoves.size()) {
                        if(!Dice.hasPlayerRolledDice()) {
                            messagePanelText.append("\n-Please roll the dice!");
                        }
                        else {
                            messagePanelText.append("\n-Please enter a valid move");
                        }

                    } else {
                        if (Backgammon.currentPlayer.getPlayerColor() == 'R') {
                            currentPosition = 25 - Moves.possibleMoves.get(moveNumber - 1).getFromPip();
                            nextPosition = 25 - Moves.possibleMoves.get(moveNumber - 1).getToPip();
                        }
                        else {
                            currentPosition = Moves.possibleMoves.get(moveNumber - 1).getFromPip();
                            nextPosition = Moves.possibleMoves.get(moveNumber - 1).getToPip();
                        }

                        Backgammon.currentPlayer.playerMove(currentPosition, nextPosition);
                        frame.repaint();
                    }
                } catch (NoSuchElementException e) {
                    messagePanelText.append("\n-Please enter move in the proper format.");
                }
            }
        }
        rePaintMainPanel();
    }

    public static void next(){

        Backgammon.player1.setMoveMade(false);
        Backgammon.player1.currentPosition = -1;
        Backgammon.player2.setMoveMade(false);
        Backgammon.player2.currentPosition = -1;

        // if something is clicked i.e. the green circle is present it clears the green circle once next is clicked
        Backgammon.deSelect();

        if(Backgammon.currentPlayer == Backgammon.player1) {
            Backgammon.currentPlayer = Backgammon.player2;
        } else {
            Backgammon.currentPlayer = Backgammon.player1;
        }

        Dice.resetDice();

        BoardNumbers.changeBoard(Backgammon.currentPlayer);

        Dice.playerHasRolledDice(false);

//        messagePanelText.append("\n-" + Backgammon.currentPlayer.getPlayerName() + " it is your turn! Your color is " + Backgammon.currentPlayer.playerColorString);
        messagePanelText.append("\n-" + Backgammon.currentPlayer.getPlayerName() + "(" + Backgammon.currentPlayer.playerColorString + ") it is your turn!" );
    }

    //Method used to calculate the points of the winner of a match
    public static int calculatePoints(Player player){

        boolean gammon, backgammon, rejectedDoublingCube;

        if(player == Backgammon.player1){
            gammon = Backgammon.counterMap[0].getNumCounters() == 0;
            rejectedDoublingCube = Backgammon.counterMap[25].getNumCounters() < 15;
        } else {
            gammon = Backgammon.counterMap[25].getNumCounters() == 0;
            rejectedDoublingCube = Backgammon.counterMap[0].getNumCounters() < 15;
        }

        if(player == Backgammon.player1){
            backgammon = Backgammon.counterMap[26].getNumCounters() > 0;
        } else {
            backgammon = Backgammon.counterMap[27].getNumCounters() > 0;
        }


        if(rejectedDoublingCube){
            return Backgammon.getDoublingCubeValue();
        }
        else if(backgammon)
        {
            return 3 * Backgammon.getDoublingCubeValue();
        }
        else if(gammon)
        {
            return 2 * Backgammon.getDoublingCubeValue();
        }
        else {
            return Backgammon.getDoublingCubeValue();
        }

    }

    // ***** NEEDS TO BE REFACTORED - Would like to print the winner's name over the board instead of in the command window
    public static void finishGame(Player player) {

        player.setPoints(player.getPoints() + calculatePoints(player));

        //If the player has more points than points to win the game ends and ask the player if they would like to play again
        //Otherwise the points are added to the players score and displayed on the board
        if(player.getPoints() >= Backgammon.pointsToWin){

            //Stops the timer
            timer.cancel();

            UI.messagePanelText.append("\nWould you like to play again?");
            UI.messagePanelText.append("\nEnter 'yes' to play again or 'no' to exit the game");

            //pop up window for when one of the players wins.
            int answer = JOptionPane.showConfirmDialog(mainPanel, "Congratulations " + player.playerName + ", You Win!!! " +
                            "\nWould you like to play again?","END OF GAME!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                // added to ensure that if the user replays the game that gameOver is no longer set to true
                gameOver = false;

                //When replay is selected the board is reset and the main menu is brough back up
                gamePlayed = true;
                Backgammon.initializeBoard();
                UI.mainMenuUI();

                //resets the timer for the next game
                seconds = 0;
            } else if (answer == JOptionPane.NO_OPTION) {

                System.exit(0);
            }

            gameOver = true;
            Backgammon.player1.setPoints(0);
            Backgammon.player2.setPoints(0);
            Backgammon.resetDoublingCubeValue();

            //Ensures the user can't roll the dice after the game has finished
            Dice.playerHasRolledDice(true);
        } else {
            gameFinished = true;

            messagePanelText.append("\nThe game has finished.\n" + player.getPlayerName() + " has won scoring " + calculatePoints(player) + " points" + "\nThe scores are \n" + Backgammon.player1.getPlayerName()
                    + ": " + Backgammon.player1.points + "\n" + Backgammon.player2.getPlayerName() + ": " + Backgammon.player2.points + "\n" + "\nEnter any key to continue to the next game");
        }
    }

    //Method which repaints the main Panel
    public static void rePaintMainPanel(){
        mainPanel.repaint();
    }

    public static void initializeUI() {

        //initialize the frame
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setTitle("Backgammon Version 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardPanel boardPanel = new BoardPanel();

        // the setBounds method takes 4 args
        // first 2 are the x & y coordinates
        // second 2 are the width & height
        boardPanel.setBounds(0, 30, 1300, 770);

        //create a green colour using hex codes close to the board bg green colour
        Color myGray = Color.decode("#F2F2F2");
        Color myGreen = Color.decode("#006600");

        // the panel to display player1's pip count
        JPanel player1PipPanelContainer = new JPanel();
        player1PipPanelContainer.setBackground(myGreen);
        player1PipPanelContainer.setBounds(0, 0, 510, 30);

        // the text showing that it is Player1's pip count
        player1PipPanelText.setText(Backgammon.player1.playerName + " - Score: " + Backgammon.player1.getPoints());
        player1PipPanelText.setForeground(Color.WHITE);
        player1PipPanelText.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        player1PipPanelContainer.add(player1PipPanelText);

        JTextField player1PipScoreField = new JTextField(3);
        // gets rid of default border
        player1PipScoreField.setBorder(BorderFactory.createEmptyBorder());
        player1PipScoreField.setBackground(myGreen);
        player1PipScoreField.setForeground(Color.WHITE);
        player1PipScoreField.setFont(new Font("Serif", Font.PLAIN, SMALL_FONT));
        player1PipScoreField.setEditable(false);
        player1PipPanelContainer.add(player1PipScoreField);

        JPanel player2PipPanelContainer = new JPanel();
        player2PipPanelContainer.setBackground(myGreen);
        player2PipPanelContainer.setBounds(700, 0, 600, 30);

        player2PipPanelText.setText(Backgammon.player2.playerName + " - Score: " + Backgammon.player2.getPoints());
        player2PipPanelText.setForeground(Color.WHITE);
        player2PipPanelText.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        player2PipPanelContainer.add(player2PipPanelText);

        JTextField player2PipScoreField = new JTextField(3);
        player2PipScoreField.setBorder(BorderFactory.createEmptyBorder());
        player2PipScoreField.setBackground(myGreen);
        player2PipScoreField.setForeground(Color.WHITE);
        player2PipScoreField.setFont(new Font("Serif", Font.PLAIN, SMALL_FONT));
        player2PipScoreField.setEditable(false);
        player2PipPanelContainer.add(player2PipScoreField);

        //Timer panel
        JPanel timerContainer = new JPanel();
        timerContainer.setBackground(myGreen);
        timerContainer.setBounds(510, 0, 200, 30);

        timerPanelText.setText(startTime);
        timerPanelText.setForeground(Color.WHITE);
        timerPanelText.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        timerContainer.add(timerPanelText);

        JTextField timerField = new JTextField(3);
        timerField.setBorder(BorderFactory.createEmptyBorder());
        timerField.setBackground(myGreen);
        timerField.setForeground(Color.WHITE);
        timerField.setFont(new Font("Serif", Font.PLAIN, SMALL_FONT));
        timerField.setEditable(false);
        timerContainer.add(timerField);

        JPanel messagePanelContainer = new JPanel();
        messagePanelContainer.setBackground(myGray);
        messagePanelContainer.setBounds(1300, 0, 250, 620);

        JLabel messageHeading = new JLabel("Message box");
        messageHeading.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        messagePanelContainer.add(messageHeading);

        messagePanelText.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        messagePanelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // wraps the text onto the next line
        messagePanelText.setLineWrap(true);
        // ensures the full word goes onto the next line
        messagePanelText.setWrapStyleWord(true);

        // ensures the user can't type in the text box
        messagePanelText.setEditable(false);
        // the scroll pane ensures scrolling when the text box is full
        JScrollPane jsp = new JScrollPane(messagePanelText);
        messagePanelContainer.add(jsp);

        JPanel commandPanelContainer = new JPanel();
        commandPanelContainer.setBackground(myGray);
        commandPanelContainer.setBounds(1300, 620, 250, 160);  

        JLabel commandPanelHeading = new JLabel("Command Panel");
        commandPanelHeading.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        commandPanelContainer.add(commandPanelHeading);

        JTextField userCmd = new JTextField(15);
        userCmd.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        userCmd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commandPanelContainer.add(userCmd);

        userCmd.addActionListener(new ActionListener() {
            String userResponse = new String();

            public void actionPerformed(ActionEvent e) {
                userResponse = userCmd.getText();

                if (!userResponse.equalsIgnoreCase("next")) {
                    //append the text on to the message box
                    messagePanelText.append("\n> " + userResponse);
                }

                inputCommands(userResponse);

                // clears text when user clicks enter
                userCmd.setText("");

                //make sure new text is visible, even if there was a selection in message box
                messagePanelText.setCaretPosition(messagePanelText.getDocument().getLength());
            }
        });

        JButton rollDiceButton = new JButton("Roll Dice");
        // gets rid of dotted border when the button is clicked
        rollDiceButton.setFocusPainted(false);
        rollDiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandPanelContainer.add(rollDiceButton);

        rollDiceButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!(Dice.hasPlayerRolledDice())) {
        			Dice.rollDice();
        			Dice.playerHasRolledDice(true);
        		}
        		else {
        			UI.messagePanelText.append("\n-You have already rolled this turn.");
        		}
        	}
        });

        JButton nextButton = new JButton("Next Turn");
        // gets rid of dotted border when the button is clicked
        nextButton.setFocusPainted(false);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandPanelContainer.add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                next();
            }
        });

        JButton doublingCubeButton = new JButton("Double");
        // gets rid of dotted border when the button is clicked
        doublingCubeButton.setFocusPainted(false);
        doublingCubeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandPanelContainer.add(doublingCubeButton);

       
        doublingCubeButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		Backgammon.requestDoubleScore();
        	}
        });

        mainPanel.add(boardPanel);
        mainPanel.add(player1PipPanelContainer);
        mainPanel.add(player2PipPanelContainer);
        mainPanel.add(messagePanelContainer);
        mainPanel.add(commandPanelContainer);
        mainPanel.add(timerContainer);

        /*
        Creates a button at each of the pips and gives each one an action listener that allows
        a player to move around the board using clicks
         */
        for (int i = 0; i <= 27; i++) {
            JButton button = new JButton();
            button.setLayout(null);
            
            if (i == 0) {
                button.setBounds(Backgammon.counterMap[i].getxCo(), Backgammon.counterMap[i].getyCo() - 140, 50, 180);
            } else if (i == 25) {
                button.setBounds(Backgammon.counterMap[i].getxCo(), Backgammon.counterMap[i].getyCo() + 30, 50, 180);
            } else if (i <= 12 && i > 0) {
                button.setBounds(Backgammon.counterMap[i].getxCo(), Backgammon.counterMap[i].getyCo() - 200, 50, 290);
            } else if (i > 12 && i <= 24) {
                button.setBounds(Backgammon.counterMap[i].getxCo(), Backgammon.counterMap[i].getyCo() + 20, 50, 290);
            } else if (i == 26) {
                button.setBounds(Backgammon.counterMap[i].getxCo(), Backgammon.counterMap[i].getyCo() - 230, 50, 320);
            } else if (i == 27) {
                button.setBounds(Backgammon.counterMap[i].getxCo(), Backgammon.counterMap[i].getyCo() + 20, 50, 320);
            }

            //Sets each button to be invisible
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            final int position = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Backgammon.currentPlayer.clickMove(position);
                }
            });
            mainPanel.add(button);
        }

        messagePanelText.append("Here is where your next move options will appear.");
        messagePanelText.append(" Enter your option in the command panel below.");

        mainPanelSetUp();

        frame.setContentPane(mainPanel);

        frame.setVisible(true);

    }

    public static void mainMenuUI () {
        //initialize the frame
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setTitle("Backgammon Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel2 = new JPanel(null);

        MainMenuPanel menuPanel = new MainMenuPanel();
        menuPanel.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        //Setting up text field to enter name of the red checker player
        JTextField redPlayer = new JTextField();
        redPlayer.setFont(new Font("Serif", Font.PLAIN, 27));
        redPlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        redPlayer.setBounds(196,374, 418, 35);
        mainPanel2.add(redPlayer);

        //Setting up text field to enter name of the white checker player
        JTextField whitePlayer = new JTextField();
        whitePlayer.setFont(new Font("Serif", Font.PLAIN, 27));
        whitePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        whitePlayer.setBounds(838,374, 457, 35);
        mainPanel2.add(whitePlayer);

        //Setting text field to enter number of points to play to
        JTextField points = new JTextField();
        points.setFont(new Font("monospaced", Font.PLAIN, 27));
        points.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        points.setBounds(721,476, 209, 35);
        mainPanel2.add(points);

        //Start button
        JButton startButton = new JButton();
        startButton.setLayout(null);
        startButton.setBounds(589, 576, 342, 101);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        //When start button is pressed assign the names and initialize game
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if statement that shows pop up window asking users to enter points if they haven't done so
                if (points.getText().replaceAll("\\s", "").isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please enter number of points!",
                            "POINTS!", JOptionPane.WARNING_MESSAGE);
                } else {

                    String red = redPlayer.getText();
                    if(!red.isEmpty()) {
                        Backgammon.player1.setPlayerName(red);
                    }
                    String white = whitePlayer.getText();
                    if(!white.isEmpty()) {
                        Backgammon.player2.setPlayerName(white);
                    }
                    Backgammon.pointsToWin = Integer.parseInt(points.getText().replaceAll("\\s", ""));

                    //If a game has not been played the board is initialized if there has been a game played the mainPanel is reset and displayed
                    if(!gamePlayed){
                        initializeUI();
                    } else {
                        mainPanelSetUp();
                    }
                }

                //Resets the timer and resets seconds for when a new game is started
                timer = new Timer();
                seconds = 0;

                //Timer which records how long the game is running for
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Your database code here
                        seconds++;
                        timerPanelText.setText("Match Length: " + fromSecondsToHHMMSS(seconds));

                    }
                    //Gives the timer a delay of 0 seconds and repeats every 1 second
                }, 0, 1000);
            }
        });

        mainPanel2.add(menuPanel);
        mainPanel2.add(startButton);

        frame.setContentPane(mainPanel2);
        frame.setVisible(true);
    }

    public static String fromSecondsToHHMMSS(int seconds) {
        long hours = TimeUnit.SECONDS.toHours(Long.valueOf(seconds));
        long minutes =  TimeUnit.SECONDS.toMinutes(seconds) - hours*60;
        long remainingSeconds = seconds - ((hours * 3600) + (minutes * 60));
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    //This function sets up the panel after it has already been initialized
    public static void mainPanelSetUp()
    {
        Backgammon.initializeBoard();

        frame.setContentPane(mainPanel);

        //Resets the dice and redoes the intial dice roll
        Dice.playerHasRolledDice(false);
        Dice.initialRollComplete = false;
        Dice.initialDiceRoll();

        Moves.getMoves();
        Moves.printMoves();

    }

    //Both of the methods below force the player to wait a second and also print the appropriate reason for the wait
    public static void displayNoMove() throws InterruptedException {
        if(!Dice.allDiceUsed()){
            messagePanelText.append("\n-" + Backgammon.currentPlayer.playerName + " has no valid moves.");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void displayForcedMove() throws InterruptedException {
        messagePanelText.append("\n-" + Backgammon.currentPlayer.playerName + " has a forced move.");
        TimeUnit.SECONDS.sleep(1);
    }
}