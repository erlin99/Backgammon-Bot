import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Backgammon{


    public static void main(String args[])
    {
        //initialize the frame
        JFrame frame = new JFrame();
        frame.setSize(1572, 805);
        frame.setTitle("Backgammon Version 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // the 'null' means the Panel doesn't follow a specific layout manager
        JPanel mainPanel = new JPanel(null);


        displayBoard boardPanel = new displayBoard();
//        boardPanel.setPreferredSize(new Dimension(1320, 760));

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
        player1PipPanelText.setFont(new Font("Serif", Font.PLAIN, 18));
        player1PipPanelContainer.add(player1PipPanelText);

        JTextField player1PipScoreField = new JTextField(3);
        // gets rid of default border
        player1PipScoreField.setBorder(BorderFactory.createEmptyBorder());
        player1PipScoreField.setBackground(myGreen);
        player1PipScoreField.setForeground(Color.WHITE);
        player1PipScoreField.setFont(new Font("Serif", Font.PLAIN, 15));
        player1PipScoreField.setEditable(false);
        player1PipPanelContainer.add(player1PipScoreField);



        JPanel player2PipPanelContainer = new JPanel();
        player2PipPanelContainer.setBackground(myGreen);
        player2PipPanelContainer.setBounds(650,0,650,30);

        JLabel player2PipPanelText = new JLabel("Player 2 - Pip Count:");
        player2PipPanelText.setForeground(Color.WHITE);
        player2PipPanelText.setFont(new Font("Serif", Font.PLAIN, 18));
        player2PipPanelContainer.add(player2PipPanelText);

        JTextField player2PipScoreField = new JTextField(3);
        player2PipScoreField.setBorder(BorderFactory.createEmptyBorder());
        player2PipScoreField.setBackground(myGreen);
        player2PipScoreField.setForeground(Color.WHITE);
        player2PipScoreField.setFont(new Font("Serif", Font.PLAIN, 15));
        player2PipScoreField.setEditable(false);
        player2PipPanelContainer.add(player2PipScoreField);




        JPanel messagePanelContainer = new JPanel();
        messagePanelContainer.setBackground(myGray);
        messagePanelContainer.setBounds(1300,0,250,620);

        JLabel messageHeading = new JLabel("Message box");
        messageHeading.setFont(new Font("Serif", Font.PLAIN, 18));
        messagePanelContainer.add(messageHeading);

        JTextArea messagePanelText = new JTextArea("Here is where your next move options will appear.",22,16);
        messagePanelText.append(" Enter your option in the command panel below.");
        messagePanelText.setFont(new Font("Serif", Font.PLAIN, 18));
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
        commandPanelHeading.setFont(new Font("Serif", Font.PLAIN, 18));
        commandPanelContainer.add(commandPanelHeading);

        JTextField userCmd = new JTextField(15);
        userCmd.setFont(new Font("Serif", Font.PLAIN, 18));
        userCmd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commandPanelContainer.add(userCmd);


        userCmd.addActionListener(new ActionListener()
        {
            String userResponse = new String();
            public void actionPerformed(ActionEvent e)
            {
                userResponse = userCmd.getText();
                //if user types quit exit the program
                if(userResponse.equalsIgnoreCase("quit"))
                    System.exit(0);
                // clears text when user clicks enter
                userCmd.setText("");
//            	System.out.println("Text=" + userResponse);
            }
        });


//        messageText.append(userCmd.getText());
//        System.out.println(messageText.getText());


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

        fillCounterMap();

        //ORIGINAL BORDERLAYOUT ATTEMPT
//        displayPanel boardPanel = new displayPanel();
//        boardPanel.setPreferredSize(new Dimension(1320, 760));
//        frame.add(boardPanel, BorderLayout.LINE_START);
//
//        JPanel pipPanel = new JPanel();
//        pipPanel.setBackground(Color.RED);
//        pipPanel.setPreferredSize(new Dimension(25, 50));
//        frame.add(pipPanel, BorderLayout.PAGE_START);
//
//        JPanel infoPanel = new JPanel();
//        infoPanel.setBackground(Color.BLUE);
//        infoPanel.setPreferredSize(new Dimension(50, 300));
//        frame.add(infoPanel, BorderLayout.LINE_END);



     	//BORDER LAYOUT ATTEMPT 2
//    	JFrame frame = new JFrame();
//    	frame.setSize(1500, 900);
//        frame.setTitle("Backgammon Version 1");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        displayPanel board = new displayPanel();
//        JPanel pipPanel1 = new JPanel();
//        pipPanel1.setBackground(Color.RED);
//        pipPanel1.setPreferredSize(new Dimension(200,30));
//        JPanel messagePanel = new JPanel();
//        messagePanel.setPreferredSize(new Dimension(150,550));
//        messagePanel.setBackground(Color.YELLOW);
//
//
//        JPanel leftPanel = new JPanel(new BorderLayout());
//
//
//        leftPanel.add(pipPanel1, BorderLayout.PAGE_START);
//        leftPanel.add(board, BorderLayout.CENTER);
//
//        JPanel total = new JPanel(new BorderLayout());
//        total.add(leftPanel, BorderLayout.CENTER);
//        total.add(messagePanel, BorderLayout.EAST);
//
//        frame.add(total);
//
//        frame.setVisible(true);

        
    }

    //Given a number between one and 24 a counter if there is one will be sent to the respective bar
    public static void barCounter(int counterPosition){
        if(Globals.counterMap[counterPosition].getNumCounters() > 0 && (counterPosition > 0 && counterPosition < 25)){
            Globals.counterMap[counterPosition].removeCounter();
            if(Globals.counterMap[counterPosition].getColor() == 1){
                Globals.counterMap[27].addCounter();
            } else if(Globals.counterMap[counterPosition].getColor() == 2){
                Globals.counterMap[26].addCounter();
            }
        }

    }

    //Given a position from 1 to 24 and there is a counter that counter will be sent to bear off
    public static void bearOff(int counterPosition){
        if(Globals.counterMap[counterPosition].getNumCounters() > 0 && (counterPosition > 0 && counterPosition < 25)){
            Globals.counterMap[counterPosition].removeCounter();
            if(Globals.counterMap[counterPosition].getColor() == 1){
                Globals.counterMap[25].addCounter();
            } else if(Globals.counterMap[counterPosition].getColor() == 2){
                Globals.counterMap[0].addCounter();
            }
        }

    }

    //Moves a counter from one position to another
    public static void moveCounter(int currentPosition, int nextPosition){
        boolean currentPositionInsideBounds = (Globals.counterMap[currentPosition].getNumCounters() > 0 && (currentPosition > 0 && currentPosition < 25));
        boolean nexPositionInsideBounds = (Globals.counterMap[nextPosition].getNumCounters() > 0 && (nextPosition > 0 && nextPosition < 25));

        if(currentPositionInsideBounds && nexPositionInsideBounds){
            Globals.counterMap[currentPosition].removeCounter();
            Globals.counterMap[nextPosition].addCounter();
        }

    }

    /*
    CounterMap is a CounterPositions array with 28 spaces That holds a x co-ordinate, a Y co-ordinate, Color,
    number of counters and wether it is on the top row or not
    CounterMap[0] is the white bear off location
    counterMap[1]-[24] is the pip positions
    counterMap[25] is the white bear off
    counterMap[26] is the locations for the white bar
    counterMap[27 is the location for the red bar
     */
    public static void fillCounterMap(){

        //Filling counterMap with starting positions and number of checkers in each position
        //Color 1 = red
        //Color 2 = white
        //Color 0 = blank
        for(int i=0 ;i<Globals.counterMap.length; i++){
            Globals.counterMap[i] = null;
        }

        int initialxCo = 1107;
        int bottomyCo = 625;
        int topyCo = 40;
        int triangleBase = 88;
        int bar = 80;

        for(int i=0; i<=27; i++){
            switch (i){
                //White bear off
                case 0:
                    Globals.counterMap[i] = new CounterPositions(1220, 615, 2, 0, false);
                    Globals.counterMap[i].setBearoff(true);
                    break;
                case 1:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 1, 2, false);
                    initialxCo -= triangleBase;
                    break;
                case 2:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 3:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 4:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 5:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 6:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 2, 5, false);
                    initialxCo -= (triangleBase + bar);
                    break;
                case 7:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 8:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 2, 3, false);
                    initialxCo -= triangleBase;
                    break;
                case 9:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 10:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 11:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 0, 0, false);
                    initialxCo -= triangleBase;
                    break;
                case 12:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, bottomyCo, 1, 5, false);
                    break;
                case 13:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 2, 5, true);
                    initialxCo += triangleBase;
                    break;
                case 14:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 15:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 16:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 17:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 1, 3, true);
                    initialxCo += triangleBase;
                    break;
                case 18:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += (triangleBase + bar);
                    break;
                case 19:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 1, 5, true);
                    initialxCo += triangleBase;
                    break;
                case 20:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 21:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 22:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 23:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 0, 0, true);
                    initialxCo += triangleBase;
                    break;
                case 24:
                    Globals.counterMap[i] = new CounterPositions(initialxCo, topyCo, 2, 2, true);
                    initialxCo += triangleBase;
                    break;
                //Red bear off
                case 25 :
                    Globals.counterMap[i] = new CounterPositions(1220, 90, 1, 0, true);
                    Globals.counterMap[i].setBearoff(true);
                    break;
                //White bar
                case 26:
                    Globals.counterMap[i] = new CounterPositions(580, 305, 2, 0, false);
                    break;
                //Red bar
                case 27:
                    Globals.counterMap[i] = new CounterPositions(580, 370, 1, 0, true);
                    break;
            }
        }
    }
}