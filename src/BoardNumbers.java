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

    public static void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 16));
        g.drawString("1", 1130, TOPY);
        g.drawString("2", 1042, TOPY);
        g.drawString("3", 954, TOPY);
        g.drawString("4", 866, TOPY);
        g.drawString("5", 778, TOPY);
        g.drawString("6", 690, TOPY);
        g.drawString("7", 520, TOPY);
        g.drawString("8", 432, TOPY);
        g.drawString("9", 344, TOPY);
        g.drawString("10", 249, TOPY);
        g.drawString("11", 160, TOPY);
        g.drawString("12", 72, TOPY);
        g.drawString("13", 72, BOTTOMY);
        g.drawString("14", 158, BOTTOMY);
        g.drawString("15", 248, BOTTOMY);
        g.drawString("16", 337, BOTTOMY);
        g.drawString("17", 425, BOTTOMY);
        g.drawString("18", 512, BOTTOMY);
        g.drawString("19", 680, BOTTOMY);
        g.drawString("20", 768, BOTTOMY);
        g.drawString("21", 858, BOTTOMY);
        g.drawString("22", 948, BOTTOMY);
        g.drawString("23", 1037, BOTTOMY);
        g.drawString("24", 1122, BOTTOMY);
    }
}
