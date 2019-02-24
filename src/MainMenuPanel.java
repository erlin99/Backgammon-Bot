/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenuPanel extends JPanel {
    private BufferedImage image;

    public MainMenuPanel()
    {
        try {
            image = ImageIO.read(getClass().getResource("boards/Background.png"));
        } catch (IOException ex) {
            // handle exception...
            System.out.println(ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); 
    }
}
