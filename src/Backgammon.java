/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Backgammon
{
	private static final int BOARD_WIDTH = 1572;
	private static final int BOARD_HEIGHT = 805;
	private static final int BIG_FONT = 18;
	private static final int SMALL_FONT = 15;
	public static CounterPositions[] counterMap = new CounterPositions[28];
	
	
	
    public static void main(String [] args)
    {
       initializeUI();
    }

    //Given a number between one and 24 a counter if there is one will be sent to the respective bar
    public static void sendCounterToBar(int counterPosition)
    {
        if(counterMap[counterPosition].getNumCounters() > 0 && (counterPosition > 0 && counterPosition < 25))
        {
            int currentColour = counterMap[counterPosition].getColor();
            if(currentColour == 1)
            {
                moveCounter(counterPosition, 27);
            }
            else if (currentColour == 2) 
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
            int currentColour = counterMap[counterPosition].getColor();
            if(currentColour == 1) 
            {
                moveCounter(counterPosition, 25);
            }
            else if (currentColour == 2) 
            {
                moveCounter(counterPosition, 0);
            }
        }
    }

    //Moves a counter from one position to another
    public static void moveCounter(int currentPosition, int nextPosition)
    {
        boolean currentPositionInsideBounds = (counterMap[currentPosition].getNumCounters() > 0 && (currentPosition >= 0 && currentPosition <= 27));
        boolean nexPositionInsideBounds = (counterMap[nextPosition].getNumCounters() >= 0 && (nextPosition >= 0 && nextPosition <= 27));

        if(currentPositionInsideBounds && nexPositionInsideBounds)
        {
            int currentColour = counterMap[currentPosition].getColor();
            counterMap[currentPosition].removeCounter();
            
            if (counterMap[nextPosition].getColor() == 0) 
            {
                counterMap[nextPosition].setColor(currentColour);
            }
            counterMap[nextPosition].addCounter();
        }
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
        //Color 1 = red
        //Color 2 = white
        //Color 0 = blank
        for(int i=0 ;i<counterMap.length; i++)
        {
            counterMap[i] = null;
        }

        int initialxCo = 1107;
        final int BOTTOM_Y_CO = 625;
        final int TOP_Y_CO = 40;
        final int TRIANGLE_BASE = 88;
        final int BAR = 80;

        for(int i=0; i<=27; i++){
            switch (i){
                //White bear off
                case 0:
                    counterMap[i] = new CounterPositions(1220, 615, 2, 0, false);
                    counterMap[i].setBearoff(true);
                    break;
                case 1:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 1, 2, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 2:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 3:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 4:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 5:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 6:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 2, 5, false);
                    initialxCo -= (TRIANGLE_BASE + BAR);
                    break;
                case 7:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 8:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 2, 3, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 9:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 10:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 11:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 0, 0, false);
                    initialxCo -= TRIANGLE_BASE;
                    break;
                case 12:
                    counterMap[i] = new CounterPositions(initialxCo, BOTTOM_Y_CO, 1, 5, false);
                    break;
                case 13:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 2, 5, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 14:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 15:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 16:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 17:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 1, 3, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 18:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += (TRIANGLE_BASE + BAR);
                    break;
                case 19:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 1, 5, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 20:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 21:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 22:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 23:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 0, 0, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                case 24:
                    counterMap[i] = new CounterPositions(initialxCo, TOP_Y_CO, 2, 2, true);
                    initialxCo += TRIANGLE_BASE;
                    break;
                //Red bear off
                case 25 :
                    counterMap[i] = new CounterPositions(1220, 90, 1, 0, true);
                    counterMap[i].setBearoff(true);
                    break;
                //White bar
                case 26:
                    counterMap[i] = new CounterPositions(580, 305, 2, 0, false);
                    break;
                //Red bar
                case 27:
                    counterMap[i] = new CounterPositions(580, 370, 1, 0, true);
                    break;
            }
        }
    }
    
    public static void initializeUI()
    {
    	 //initialize the frame
        JFrame frame = new JFrame();
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setTitle("Backgammon Version 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // the 'null' means the Panel doesn't follow a specific layout manager
        JPanel mainPanel = new JPanel(null);

        BoardPanel boardPanel = new BoardPanel();

        // the setBounds method takes 4 args
        // first 2 are the x & y coordinates
        // second 2 are the width & height
        boardPanel.setBounds(0,30,1300,760);

        //create a green colour using hex codes close to the board bg green colour
        Color myGreen = Color.decode("#006600");
        Color myGray = Color.decode("#F2F2F2");

        // the panel to display player1's pip count
        JPanel player1PipPanelContainer = new JPanel();
        player1PipPanelContainer.setBackground(myGreen);
        player1PipPanelContainer.setBounds(0,0,650,30);

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
        player2PipPanelContainer.setBounds(650,0,650,30);

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
        messagePanelContainer.setBounds(1300,0,250,620);

        JLabel messageHeading = new JLabel("Message box");
        messageHeading.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        messagePanelContainer.add(messageHeading);

        JTextArea messagePanelText = new JTextArea("Here is where your next move options will appear.",22,16);
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
        commandPanelContainer.setBounds(1300,620,250,130);

        JLabel commandPanelHeading = new JLabel("Command Panel");
        commandPanelHeading.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        commandPanelContainer.add(commandPanelHeading);

        JTextField userCmd = new JTextField(15);
        userCmd.setFont(new Font("Serif", Font.PLAIN, BIG_FONT));
        userCmd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commandPanelContainer.add(userCmd);

        initializeBoard();

        userCmd.addActionListener(new ActionListener()
        {
            String userResponse = new String();
            public void actionPerformed(ActionEvent e)
            {
                userResponse = userCmd.getText();
                //if user types quit exit the program
                if(userResponse.equalsIgnoreCase("quit"))
                    System.exit(0);

                //moving red from bar to bear off as a test for sprint 1 one pip at a time (missing timer to slow it down)
                if (userResponse.equalsIgnoreCase("move red")){

                    final Timer timer = new Timer(400, null);
                    timer.addActionListener(new ActionListener() {
                        int i = -1;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (i == -1){
                            	sendCounterToBar(1);
                                frame.repaint();
                            }
                            else if (i == 0) {
                                moveCounter(27, 1);
                                frame.repaint();
                            }
                            else {
                                moveCounter(i, i + 1);
                                frame.repaint();
                            }
                            i++;
                            if (i > 24) {
                                timer.stop();
                            }
                        }
                    });
                    timer.setRepeats(true);
                    timer.start();
                }

                //moving white from bar to bear off as a test for sprint 1 (missing timer to slow it down)
                if (userResponse.equalsIgnoreCase("move white")){
                    final Timer timer = new Timer(400, null);
                    timer.addActionListener(new ActionListener() {
                        int i = 26;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (i == 26) {
                            	sendCounterToBar(24);
                                frame.repaint();
                            }
                            else if (i == 25) {
                                moveCounter(26, 24);
                                frame.repaint();
                            }
                            else {
                                moveCounter(i, i - 1);
                                frame.repaint();
                            }
                            if (i == 1) {
                                timer.stop();
                            }
                            i--;
                        }
                    });
                    timer.setRepeats(true);
                    timer.start();
                }

                //append the text on to the message box
                messagePanelText.append("\n" + userResponse);
                // clears text when user clicks enter
                userCmd.setText("");

                //make sure new text is visible, even if there was a selection in message box
                messagePanelText.setCaretPosition(messagePanelText.getDocument().getLength());
            }
        });

        JButton rollDiceButton = new JButton("Roll Dice");
        // gets rid of dotted border when the button is clicked
        rollDiceButton.setFocusPainted(false);
        commandPanelContainer.add(rollDiceButton);

        //DICE ROLL METHOD TO BE IMPLEMENTED IN LATER SPRINTS
//        rollDice.addActionListener(new ActionListener(){
//        	public void actionPerformed(ActionEvent e){
//
//        	        }
//        	    });

        JButton doublingCubeButton = new JButton("Double");
        // gets rid of dotted border when the button is clicked
        doublingCubeButton.setFocusPainted(false);
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

        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }
}