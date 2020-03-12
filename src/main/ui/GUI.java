package ui;

import model.Gym;
import model.Problem;
import model.Wall;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GUI extends JFrame {

    private final int HEIGHT = 500;
    private final int WIDTH = 750;
    private static final String GYM_FILE = "./data/testfile2.txt";

    private final int WALL_QUANTITY = 6;
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
    private JComboBox addPanelGrade;
    String[] wallNames = {"Show Wall", "Ship", "Slab", "Berg", "Small Cave", "Big Cave"};
    Integer[] grades = {1, 2, 3, 4, 5, 6,};
    CardLayout cardLayout = new CardLayout();
    private JPanel climbsPanel;
    private JLabel allClimbs;
    private JButton backClimbPanel;
    private JButton addClimbButton;


    public GUI() {
        super("The Hive Surrey");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window listener code from stackoverflow
        // (https://stackoverflow.com/questions/16295942/java-swing-adding-action-listener-for-exit-on-close)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveGym();
                e.getWindow().dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
        loadGym();
        cards = new JPanel(cardLayout);
        mainPanel = new JPanel();
        cards.add("Main Panel", mainPanel);
        add(cards);
        setUpHome();
        initAddPanel();
        initSeeClimbs();

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
        seeClimbsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Climbs Panel");
                allClimbs.setText(gym.toStringAllProblems());
            }
        });
        removeClimbsButton = new JButton("Click here to remove climbs.");
        mainPanel.add(addNewButton);
        mainPanel.add(seeClimbsButton);
        mainPanel.add(removeClimbsButton);
    }

    public void initSeeClimbs() {
        climbsPanel = new JPanel();
        allClimbs = new JLabel(gym.toStringAllProblems());
        climbsPanel.add(allClimbs);
        System.out.println(gym.toStringAllProblems());
        cards.add("Climbs Panel", climbsPanel);

        backClimbPanel = new JButton("Back");
        backClimbPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
            }
        });
        climbsPanel.add(backClimbPanel);
    }

    public void initAddPanel() {

        addPanel = new JPanel();
        textBox1 = new JLabel("Add climbs below\nWhat is the color of the climb?");
        addPanel.add(textBox1);
        colorClimbAdded = new JTextField();
        colorClimbAdded.setColumns(10);

        addPanel.add(colorClimbAdded);

        addPanelWalls = new JComboBox(wallNames);
        addPanelWalls.setSelectedIndex(WALL_QUANTITY - 1);


        addPanel.add(addPanelWalls);
        addPanelGrade = new JComboBox<>(grades);
        addPanel.add(addPanelGrade);
        cards.add("Add Panel", addPanel);

        backAddPanel = new JButton("Back");
        addPanel.add(backAddPanel);
        backAddPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
            }
        });

        addClimbButton = new JButton("Add");
        addClimbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int grade = addPanelGrade.getSelectedIndex() + 1;
                String color = colorClimbAdded.getText();
                int index = addPanelWalls.getSelectedIndex() + 1;

                gym.addProblem(new Problem(color, grade), selectWall(index));
            }
        });
        addPanel.add(addClimbButton);

    }

    public Wall selectWall(int index) {
        if (index == 1) {
            return gym.getShowWall();
        } else if (index == 2) {
            return gym.getShip();
        } else if (index == 3) {
            return gym.getSlab();
        } else if (index == 4) {
            return gym.getBerg();
        } else if (index == 5) {
            return gym.getSmallCave();
        }
        return gym.getBigCave();

    }

    //stright copy from app
    //MODIFIES: this
    //EFFECTS: Loads climbs from the gym file, if climbs exist;
    //         otherwise it will initialize an empty gym
    public void loadGym() {
        try {
            List<Problem> list = Reader.readProblems(new File(GYM_FILE));
            gym = new Gym();
            for (Problem p : list) {
                if (p.getWall().equals("Show Wall")) {
                    gym.getShowWall().addProblem(p);
                } else if (p.getWall().equals("Ship")) {
                    gym.getShip().addProblem(p);
                } else if (p.getWall().equals("Slab")) {
                    gym.getSlab().addProblem(p);
                } else if (p.getWall().equals("Berg")) {
                    gym.getBerg().addProblem(p);
                } else if (p.getWall().equals("Small Cave")) {
                    gym.getSmallCave().addProblem(p);
                } else {
                    gym.getBigCave().addProblem(p);
                }
            }
        } catch (IOException e) {
            gym = new Gym();
        }
    }

    //EFFECTS: saves all problems to GYM_FILE
    public void saveGym() {
        Writer writer = null;
        try {
            writer = new Writer(new File(GYM_FILE));

            for (Wall w : gym.wallList) {
                for (Problem p : w.getProblemList()) {
                    writer.write(p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to save to the gym file");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.close();
        System.out.println("Gym saved");

    }

}
