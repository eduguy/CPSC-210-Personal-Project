package ui;

import model.Gym;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final int HEIGHT = 500;
    private final int WIDTH = 750;

    JPanel mainPanel;
    JPanel addPanel;
    JLabel textBox1;
    JPanel cards;
    Gym gym;
    private JButton addNewButton;
    private JButton seeClimbsButton;
    private JButton removeClimbsButton;
    private JTextField colorClimbAdded;
    private JButton backAddPanel;
    private JComboBox addPanelWalls;
    String[] wallNames = {"Show Wall", "Ship", "Slab", "Berg", "Small Cave"};
    CardLayout cardLayout = new CardLayout();


    public GUI() {
        super("The Hive Surrey");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        gym = new Gym();

        cards = new JPanel(cardLayout);
        mainPanel = new JPanel();
        cards.add("Main Panel", mainPanel);
        add(cards);
        setUpHome();
        initAddPanel();

//        cardLayout.first(cards);

//        showMain();

    }

    public void setUpHome() {
        JLabel background = new JLabel("Welcome to my program!");
        //   background.setIcon(new ImageIcon("data/review.PNG"));// your image here
        mainPanel.add(background);
        addNewButton = new JButton("Click here to add climbs");
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout c1 = new CardLayout();
                cardLayout.show(cards, "Add Panel");
            }
        });
        seeClimbsButton = new JButton("Click here to see all climbs");
        removeClimbsButton = new JButton("Click here to remove climbs.");
        mainPanel.add(addNewButton);
        mainPanel.add(seeClimbsButton);
        mainPanel.add(removeClimbsButton);
    }



    public void initAddPanel() {
        addPanel = new JPanel();
        textBox1 = new JLabel("Add climbs below\nWhat is the color of the climb?");
        addPanel.add(textBox1);
        colorClimbAdded = new JTextField();
        colorClimbAdded.setColumns(10);
        addPanel.add(colorClimbAdded);
        addPanelWalls = new JComboBox(wallNames);
        addPanel.add(addPanelWalls);
        cards.add("Add Panel", addPanel);

        backAddPanel = new JButton("Back");
        addPanel.add(backAddPanel);
        backAddPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
            }
        });

    }


}
