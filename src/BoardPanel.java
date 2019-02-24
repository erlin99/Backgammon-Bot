/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardPanel extends JPanel {
 
	private BufferedImage image;
    
    public BoardPanel()
    {
        try {
            image = ImageIO.read(getClass().getResource("boards/board.png"));
        } catch (IOException ex) {
            // handle exception...
            System.out.println(ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters

        for(int i = 0; i<=27; i++){
            Backgammon.counterMap[i].draw(g2);
        }
        
        Dice.draw(g2);
        BoardNumbers.draw(g2);
    }
}
