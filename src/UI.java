/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UI {

    private static final int BOARD_WIDTH = 1572;
    private static final int BOARD_HEIGHT = 805;
    private static final int BIG_FONT = 18;
    private static final int SMALL_FONT = 15;
    public static String userResponse = new String();
    public static JFrame frame = new JFrame();
    public static JTextArea messagePanelText = new JTextArea("Here is where your next move options will appear.",22,16);

   public static boolean gameOver = false;
    
    // the 'null' means the Panel doesn't follow a specific layout manager
    public static JPanel mainPanel = new JPanel(null);

    public static String getUserInput(){
        return userResponse;
    }

    public static void inputCommands(String userResponse) {

        int currentPosition;
        int nextPosition;

        Scanner scanner = new Scanner(userResponse).useDelimiter("\\s* \\s*");

        //if user types quit exit the program
        if(userResponse.equalsIgnoreCase("quit"))
            System.exit(0);
        else if(userResponse.equalsIgnoreCase("cheat"))
            Backgammon.setCheatBoard();
        if(userResponse.equalsIgnoreCase("next")) {
            next();
        } else if(userResponse.equalsIgnoreCase("yes") && gameOver) {
            //frame.removeAll();
            Backgammon.initializeBoard();
            initializeUI();
        }
        else if(userResponse.equalsIgnoreCase("no") && gameOver) {
            System.exit(0);
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
                    response = response.toUpperCase();
                    int moveNumber = 0;
                    if (response.length() > 1) {
                        moveNumber = 26 * response.length();
                    }
                    //converting last character to ascii code
                    int ascii = (int) response.charAt(response.length()-1);
                    moveNumber += ascii - 64;

                    if (moveNumber > Moves.moves.size()) {
                        messagePanelText.append("\n-Please enter a valid move");
                    } else {
                        currentPosition = Moves.moves.get(moveNumber - 1).getFromPip();
                        nextPosition = Moves.moves.get(moveNumber - 1).getToPip();

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

        Backgammon.deSelect();

        if(Backgammon.currentPlayer == Backgammon.player1) {
            Backgammon.currentPlayer = Backgammon.player2;
        } else {
            Backgammon.currentPlayer = Backgammon.player1;
        }

        BoardNumbers.changeBoard(Backgammon.currentPlayer);

        Dice.playerHasRolledDice(false);

        messagePanelText.append("\n-" + Backgammon.currentPlayer.getPlayerName() + " it is your turn! Your color is " + Backgammon.currentPlayer.playerColorString);

        //**** FOR TESTING
//            Backgammon.counterMap[25].setNumCounters(15);

        // check if the game has ended
        if(Backgammon.counterMap[0].getNumCounters() == 15) {
            finishGame(Backgammon.player2);
        }
        else if(Backgammon.counterMap[25].getNumCounters() == 15) {
            finishGame(Backgammon.player1);
        }
    }

    // called in UI.java at bottom of inputCommands (as this function is used after every iteration of game play/ every move)
    public static void finishGame(Player player) {
        UI.messagePanelText.append("\nCongratulations " + player.playerName + ", You Win!!!");

        UI.messagePanelText.append("\nWould you like to play again?");
        UI.messagePanelText.append("\nEnter 'yes' to play again or 'no' to exit the game");

        gameOver = true;
        // NEEDS TO BE FIXED
        // also need something to freeze the board once the game is over
//        String userResponse = UI.getUserInput();
//
//        inputCommands(userResponse);
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
        boardPanel.setBounds(0, 30, 1300, 760);

        //create a green colour using hex codes close to the board bg green colour
        Color myGreen = Color.decode("#006600");
        Color myGray = Color.decode("#F2F2F2");

        // the panel to display player1's pip count
        JPanel player1PipPanelContainer = new JPanel();
        player1PipPanelContainer.setBackground(myGreen);
        player1PipPanelContainer.setBounds(0, 0, 650, 30);

        // the text showing that it is Player1's pip count
        JLabel player1PipPanelText = new JLabel("Player 1 - Pip Count:");
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
        player2PipPanelContainer.setBounds(650, 0, 650, 30);

        JLabel player2PipPanelText = new JLabel("Player 2 - Pip Count:");
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

        JPanel messagePanelContainer = new JPanel();
        messagePanelContainer.setBackground(myGray);
        messagePanelContainer.setBounds(1300, 0, 250, 620);

        JLabel messageHeading = new JLabel("Message box");
        messageHeading.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        messagePanelContainer.add(messageHeading);

        messagePanelText.append(" Enter your option in the command panel below.");
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
        commandPanelContainer.setBounds(1300, 620, 250, 130);

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
                    messagePanelText.append("\n" + "-" + userResponse);
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

        //DOUBLING CUBE METHOD TO BE IMPLEMENTED IN LATER SPRINTS
//        doublingCube.addActionListener(new ActionListener(){
//        	public void actionPerformed(ActionEvent e){
//
//        	        }
//        	    });

        mainPanel.add(boardPanel);
        mainPanel.add(player1PipPanelContainer);
        mainPanel.add(player2PipPanelContainer);
        mainPanel.add(messagePanelContainer);
        mainPanel.add(commandPanelContainer);

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
        
        Dice.initialDiceRoll();
        Moves.printMoves();

        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    public static void mainMenuUI () {
        //initialize the frame
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setTitle("Backgammon Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(null);

        MainMenuPanel menuPanel = new MainMenuPanel();
        menuPanel.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        //Setting up text field to enter name of the red checker player
        JTextField redPlayer = new JTextField();
        redPlayer.setFont(new Font("Serif", Font.PLAIN, 27));
        redPlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        redPlayer.setBounds(196,374, 418, 35);
        mainPanel.add(redPlayer);

        //Setting up text field to enter name of the white checker player
        JTextField whitePlayer = new JTextField();
        whitePlayer.setFont(new Font("Serif", Font.PLAIN, 27));
        whitePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        whitePlayer.setBounds(838,374, 457, 35);
        mainPanel.add(whitePlayer);

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
                String red = redPlayer.getText();
                if(!red.isEmpty()) {
                    Backgammon.player1.setPlayerName(red);
                }
                String white = whitePlayer.getText();
                if(!white.isEmpty()) {
                    Backgammon.player2.setPlayerName(white);
                }
                initializeUI();
            }
        });

        mainPanel.add(menuPanel);
        mainPanel.add(startButton);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}
            
