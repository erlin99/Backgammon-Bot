import javax.swing.*;

public class main {
    public static void main(String args[]){
        displayPanel panel = new displayPanel();
        //panel.setTitle("A frame with two components");
        //panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);

        JFrame frame = new JFrame();
        frame.setTitle("A frame with two components");
        frame.setSize(1320, 760);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}