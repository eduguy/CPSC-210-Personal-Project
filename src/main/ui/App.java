package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Frame");

        JLabel label = new JLabel("This is a Swing frame", JLabel.CENTER);
        label.setText("hi");  // Look familiar?  <----------

        frame.add(label);

        frame.setSize(350, 200); // width=350, height=200
        frame.setVisible(true); // Display the frame
    }

}