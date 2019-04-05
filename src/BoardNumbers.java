/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
import javax.swing.*;
import java.awt.*;

public class BoardNumbers extends JPanel {

    private static final int TOPY = 23;
    private static final int BOTTOMY = 708;
    private static final int[] XCOORDINATES = {1130, 1042, 954, 866, 778, 690, 520, 432, 344, 249, 160, 72, 72, 158, 248,
                                                337, 425, 512, 680, 768, 858, 948, 1037, 1122};
    private static char currentPlayer = ' ';

    public static void changeBoard(Player player) {
        currentPlayer = player.playerColor;
        UI.frame.repaint();
    }


    public static void draw(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 16));

        if (currentPlayer == 'R') {
            for (int i = 24; i > 0; i--) {
                if (i >= 13) {
                    g.drawString(Integer.toString(i), XCOORDINATES[i - 1], BOTTOMY);
                }
                else {
                    g.drawString(Integer.toString(i), XCOORDINATES[i - 1], TOPY);
                }
            }
        }
        else if (currentPlayer == 'W') {
            for (int i = 24; i > 0; i--) {
                if (i >= 13) {
                    g.drawString(Integer.toString(i), XCOORDINATES[24-i], TOPY);
                }
                else {
                    g.drawString(Integer.toString(i), XCOORDINATES[24-i], BOTTOMY);
                }
            }
        }
        
        
        // New sprint 4 code to display the doubling cube value
<<<<<<< HEAD
        g.setFont(new Font("serif", Font.BOLD, 32));
=======
        g.setFont(new Font("serif", Font.PLAIN, 32));
>>>>>>> cbf44d1ce4823a307b2906d3b55b4394adc55cae
        
        // if the value of the doubling cube is 1 you actually display '64' instead of 1
        if(Backgammon.getDoublingCubeValue() == 1)
        {
        	g.drawString(Integer.toString(64), 1232, 365);
        }
        // these else ifs and else deal with ensuring the doubling cube value lines up properly within the red cube
        // i.e. the coordinates are different if the value is a single or double digit
        else if(Backgammon.getDoublingCubeValue() < 10 && Backgammon.getDoublingCubeValue() != 1)
        {
        	g.drawString(Integer.toString(Backgammon.getDoublingCubeValue()), 1240, 365);
        }
        else
        {
        	g.drawString(Integer.toString(Backgammon.getDoublingCubeValue()), 1232, 365);
        }
        
        
    }
}