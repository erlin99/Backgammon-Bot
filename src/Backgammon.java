import javax.swing.*;
import java.awt.*;

public class Backgammon {
	
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
        
        
        
        JPanel pipPanel2 = new JPanel();
        pipPanel2.setBackground(Color.BLUE);
        pipPanel2.setBounds(650,0,650,30);
        
        JLabel pip2 = new JLabel("Player 2 - Pip Count:");
        pip2.setFont(new Font("Serif", Font.PLAIN, 18));
        pipPanel2.add(pip2);
        
        
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.YELLOW);
        messagePanel.setBounds(1300,0,250,570);
        
        JLabel message = new JLabel("Message box");
        message.setFont(new Font("Serif", Font.PLAIN, 18));
        messagePanel.add(message);
       
         
        JTextArea messageText = new JTextArea("Here is where your next move options will appear",22,16);
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
        
        JLabel cmd = new JLabel("Enter your option:");
        cmd.setFont(new Font("Serif", Font.PLAIN, 18));
        commandPanel.add(cmd);
  


        panel.add(boardPanel);
        panel.add(pipPanel1);
        panel.add(pipPanel2);
        panel.add(messagePanel);
        panel.add(commandPanel);
        
 

        frame.setContentPane(panel);
        
        frame.setVisible(true);
   
    	
    	
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
}