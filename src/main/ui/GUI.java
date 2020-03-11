package ui;

import model.Gym;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final int HEIGHT = 500;
    private final int WIDTH = 750;
    final String MAIN_PANEL = "Main Panel";
    JPanel mainPanel;
    JPanel addPanel;
    JLabel textBox1;
    JPanel cards;
    Gym gym;
    private JButton addNewButton;
    private JButton seeClimbsButton;
    private JButton removeClimbsButton;
    CardLayout cardLayout;


    public GUI() {
        super("The Hive Surrey");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        gym = new Gym();

        cards = new JPanel(new CardLayout());
        mainPanel = new JPanel();
        mainPanel.setName("Main Panel");
        cards.add(mainPanel);
        add(cards);
        setUpHome();

        initializeButtons();

//        cardLayout.first(cards);

//        showMain();

    }

    public void setUpHome() {
        JLabel background = new JLabel("Welcome to my program!");
        //   background.setIcon(new ImageIcon("data/review.PNG"));// your image here
        mainPanel.add(background);
        addNewButton = new JButton("Click here to add climbs");
        seeClimbsButton = new JButton("Click here to see all climbs");
        removeClimbsButton = new JButton("Click here to remove climbs.");
        mainPanel.add(addNewButton);
        mainPanel.add(seeClimbsButton);
        mainPanel.add(removeClimbsButton);
    }

//    public void showMain() {
//        CardLayout c1 = new CardLayout();
//        c1.show(mainPanel, "Main Panel");
//    }

    public void initializeButtons() {
        addNewButton = new JButton("Click here to add climbs");
        addPanel = new JPanel();
        addPanel.setName("Add Panel");
        textBox1 = new JLabel("add climbs");
        //addPanel.add(addNewButton);
        //addPanel.add(textBox1);
        //cards.add(addPanel);
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("HI ALL");
                // CardLayout c1 = (CardLayout)(cards.getLayout());
                //  c1.show(addPanel, "Add Panel");
            }
        });
    }


}
