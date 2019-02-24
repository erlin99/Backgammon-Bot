import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;

/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

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

    public static void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 16));

        if (currentPlayer == 'R') {
            for (int i = 1; i <= 24; i++) {
                if (i <= 12) {
                    g.drawString(Integer.toString(i), XCOORDINATES[24 - i], BOTTOMY);
                }
                else {
                    g.drawString(Integer.toString(i), XCOORDINATES[24 - i], TOPY);
                }
            }
        }
        else if (currentPlayer == 'W') {
            for (int i = 1; i <= 24; i++) {
                if (i <= 12) {
                    g.drawString(Integer.toString(i), XCOORDINATES[i-1], TOPY);
                }
                else {
                    g.drawString(Integer.toString(i), XCOORDINATES[i-1], BOTTOMY);
                }
            }
        }
    }
}
