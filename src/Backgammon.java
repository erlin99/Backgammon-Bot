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
   
        
    }
}