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
        JPanel panel = new JPanel(null);

        
        displayBoard boardPanel = new displayBoard();
//        boardPanel.setPreferredSize(new Dimension(1320, 760));
        
        // the setBounds method takes 4 args
        // first 2 are the x & y coordinates
        // second 2 are the width & height
        boardPanel.setBounds(0,30,1300,760);
        
        
        // the panel to display player1's pip count
        JPanel pipPanel1 = new JPanel();
        pipPanel1.setBackground(Color.RED);
        pipPanel1.setBounds(0,0,650,30);
        
        // the text showing that it is Player1's pip count
        JLabel pip1 = new JLabel("Player 1 - Pip Count:");
        pip1.setFont(new Font("Serif", Font.PLAIN, 18));
        pipPanel1.add(pip1);
        
        JTextField player1Pips = new JTextField(3);
        player1Pips.setFont(new Font("Serif", Font.PLAIN, 15));
        player1Pips.setEditable(false);
        pipPanel1.add(player1Pips);
        
        
        
        JPanel pipPanel2 = new JPanel();
        pipPanel2.setBackground(Color.BLUE);
        pipPanel2.setBounds(650,0,650,30);
        
        JLabel pip2 = new JLabel("Player 2 - Pip Count:");
        pip2.setFont(new Font("Serif", Font.PLAIN, 18));
        pipPanel2.add(pip2);
        
        JTextField player2Pips = new JTextField(3);
        player2Pips.setFont(new Font("Serif", Font.PLAIN, 15));
        player2Pips.setEditable(false);
        pipPanel2.add(player2Pips);
        
        
        
        
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.YELLOW);
        messagePanel.setBounds(1300,0,250,570);
        
        JLabel message = new JLabel("Message box");
        message.setFont(new Font("Serif", Font.PLAIN, 18));
        messagePanel.add(message);
           
        JTextArea messageText = new JTextArea("Here is where your next move options will appear.",22,16);
        messageText.append(" Enter your option in the command panel below.");
        messageText.setFont(new Font("Serif", Font.PLAIN, 18));
        messageText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // wraps the text onto the next line
        messageText.setLineWrap(true);
        // ensures the full word goes onto the next line
        messageText.setWrapStyleWord(true);
        // ensures the user can't type in the text box
        messageText.setEditable(false);
        // the scroll pane ensures scrolling when the text box is full
        JScrollPane jsp = new JScrollPane(messageText);
        messagePanel.add(jsp);       
        
        
        
        JPanel commandPanel = new JPanel();
        commandPanel.setBackground(Color.ORANGE);
        commandPanel.setBounds(1300,570,250,180);
        
        JLabel cmd = new JLabel("Command Panel");
        cmd.setFont(new Font("Serif", Font.PLAIN, 18));
        commandPanel.add(cmd);
        
        JTextField userCmd = new JTextField(15);
        userCmd.setFont(new Font("Serif", Font.PLAIN, 18));
        userCmd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commandPanel.add(userCmd);
        
        
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
        
        
        JButton rollDice = new JButton("Roll Dice");
        // gets rid of dotted border when the button is clicked
        rollDice.setFocusPainted(false);
        commandPanel.add(rollDice);
        
        //DICE ROLL METHOD TO BE IMPLEMENTED IN LATER SPRINTS
//        rollDice.addActionListener(new ActionListener(){  
//        	public void actionPerformed(ActionEvent e){  
//        	              
//        	        }  
//        	    });  
        
        
        JButton doublingCube = new JButton("Double");
        // gets rid of dotted border when the button is clicked
        doublingCube.setFocusPainted(false);
        commandPanel.add(doublingCube);
        
        //DOUBLING CUBE METHOD TO BE IMPLEMENTED IN LATER SPRINTS
//        doublingCube.addActionListener(new ActionListener(){  
//        	public void actionPerformed(ActionEvent e){  
//        	              
//        	        }  
//        	    });  
 
        


        panel.add(boardPanel);
        panel.add(pipPanel1);
        panel.add(pipPanel2);
        panel.add(messagePanel);
        panel.add(commandPanel);
        
 

        frame.setContentPane(panel);
        
        frame.setVisible(true);
   
        
    }
}