package ui;

import model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallPanel extends JPanel {
    JPanel wallPanel;
    Wall currentWall;

    public WallPanel(Wall w, CardLayout cardLayout, JPanel cards) {
        setLayout(null);
        setVisible(true);
        currentWall = w;
        this.add(new JLabel("HI ALL"));
        JButton button = new JButton("bck");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
            }
        });

        this.add(button);
    }
}
