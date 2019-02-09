import jdk.nashorn.internal.objects.Global;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class displayBoard extends JPanel {

    //private BufferedImage boardImage;
    //private int FRAME_WIDTH = 1080, FRAME_HEIGHT = 720;
    //public CounterPositions counter = new CounterPositions(30, 30, 30, 1);

    //public CounterPositions counter = new CounterPositions(655, 40, 70, 1);

    private BufferedImage image;

    public displayBoard() {
        try {
            image = ImageIO.read(new File("boardBackground.png"));
        } catch (IOException ex) {
            // handle exception...
            System.out.println(ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters


        for(int i = 0; i<=27; i++){
            Globals.counterMap[i].draw(g2);
        }
    }
}
