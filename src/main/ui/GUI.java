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
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    private static final int HEIGHT = 500;
    private static final int WIDTH = 750;
    private static final String GYM_FILE = "./data/testfile2.txt";

    int wallSelect;
    private static final int WALL_QUANTITY = 6;
    JPanel mainPanel;
    JPanel addRemovePanel;
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
    String[] options = {"Wall", "Difficulty"};
    CardLayout cardLayout = new CardLayout();
    private JPanel climbsPanel;
    private JLabel allClimbs;
    private JButton backClimbPanel;
    private JButton addClimbButton;
    private JLabel removeLabel;
    private JTextField colorClimbRemove;
    private JComboBox removePanelWalls;
    private JComboBox<Integer> removePanelGrade;
    private JComboBox<String> sortingOptionsComboBox;
    private static int OPTIONS_SIZE = 1;
    private JButton sortingOptionsGo;
    private JOptionPane confirmAddOption;
    JLabel photo;
    private JButton slabButton;
    private JPanel mapPanel;
    private JButton bigCaveButton;
    private JButton smallCaveButton;
    private JButton showWallButton;
    private JButton bergButton;
    private JLabel kidsAreaLabel;
    private JPanel bergPanel;
    private JPanel showWallPanel;
    private JPanel slabPanel;
    private JButton shipButton;
    private JPanel shipPanel;
    private JPanel wallPanel;
    private JLabel climbs;
    private JButton addWallPanel;
    private JButton backOutShip;

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


        //basic frame setup
        setLocationRelativeTo(null);
        setVisible(true);
        loadGym();
        //setting up card layout
        cards = new JPanel(cardLayout);
        mainPanel = new JPanel(new BorderLayout());
        cards.add("Main Panel", mainPanel);
        add(cards);
        //setting up all panels that will be seen
        initAddAndRemovePanel();
        initSeeClimbs();
        setUpHome();


    }

    public void setUpHome() {

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
        mainPanel.add(addNewButton, BorderLayout.NORTH);
        mainPanel.add(seeClimbsButton, BorderLayout.SOUTH);


        initMapPanel();


    }

    public void initMapPanel() {
        //mapPanel = new JPanel(null);
        photo = new JLabel();
        photo.setLayout(null);
        ImageIcon img = new ImageIcon("./data/24.png");
        photo.setIcon(img);
        //mapPanel.add(photo);
        mainPanel.add(photo, BorderLayout.CENTER);
        photo.setBounds(0, -45, 750, 500);
        kidsAreaLabel = new JLabel("Children's Area");
        kidsAreaLabel.setBounds(50, 10, 125, 25);
        photo.add(kidsAreaLabel);

        initSlab();
        initBigCave();
        initSmallCave();
        initShowWall();
        initBerg();
        initShip();

        initMapPanels();
        addClimbForWallPanel(backOutShip);

        removeClimbForWallPanels();




    }

    private void removeClimbForWallPanels() {
        Wall w = selectWall(wallSelect+1);
        List<String> climbsOnWall = w.getProblems();
        JComboBox removeWallPanelsJCombo = new JComboBox(climbsOnWall);
        mapPanel.add(removeWallPanelsJCombo);
    }

    public void initMapPanels() {
        wallPanel = new JPanel();
        cards.add(wallPanel, "Wall Panel");
        backOutShip = new JButton("Back");
        backOutShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
                climbs.setText("");
                wallSelect = 0;
            }
        });
    }

    public void addClimbForWallPanel(JButton backOutShip) {
        wallPanel.add(textBox1);

        wallPanel.add(addPanelGrade);
        wallPanel.add(colorClimbAdded);
        wallPanel.add(backOutShip);
        addWallPanel = new JButton("Add");
        addWallPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int grade = addPanelGrade.getSelectedIndex() + 1;
                String color = colorClimbAdded.getText();
                gym.addProblem(new Problem(color, grade), selectWall(wallSelect + 1));
                JOptionPane.showMessageDialog(addRemovePanel,
                        "Success!.",
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
                colorClimbAdded.setText("");

            }
        });
        wallPanel.add(addWallPanel);
    }

    private void initShip() {
        shipButton = new JButton("Ship");
        shipButton.setBounds(350, 415, 75, 25);
        photo.add(shipButton);
        shipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Wall Panel");
                climbs = new JLabel("");
                climbs.setText(gym.getShip().getProblems());
                wallPanel.add(climbs);
                //wallPanelFunctionality();
            }
        });
        wallSelect = 2;

    }

    public void initSlab() {
        slabButton = new JButton("Slab");
        slabButton.setBounds(250, 375, 75, 25);
        photo.add(slabButton);
        slabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Wall Panel");
                climbs = new JLabel();
                climbs.setText(gym.getSlab().getProblems());
                wallPanel.add(climbs);
            }
        });
        wallSelect = 3;

//        slabPanel = new JPanel();
//        cards.add(slabPanel, "Slab Panel");
//        JLabel climbs = new JLabel(gym.getSlab().getProblems());
//        slabPanel.add(climbs);
//        JButton backOutSlab = new JButton("Back");
//        backOutSlab.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                cardLayout.show(cards, "Main Panel");
//            }
//        });
//        slabPanel.add(backOutSlab);
    }

    private void initBerg() {
        bergButton = new JButton("Berg");
        bergButton.setBounds(400, 50, 100, 25);
        photo.add(bergButton);
        bergButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Wall Panel");
                climbs = new JLabel();
                climbs.setText(gym.getBerg().getProblems());
                wallPanel.add(climbs);
            }
        });
        wallSelect = 4;

//        bergPanel = new JPanel();
//        cards.add(bergPanel, "Berg Panel");
//        JLabel climbs = new JLabel(gym.getBerg().getProblems());
//        bergPanel.add(climbs);
//        JButton backOutBerg = new JButton("Back");
//        backOutBerg.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                cardLayout.show(cards, "Main Panel");
//            }
//        });
//        bergPanel.add(backOutBerg);
    }

    private void initShowWall() {
        showWallButton = new JButton("Show Wall");
        showWallButton.setBounds(590, 350, 100, 25);
        photo.add(showWallButton);
        showWallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Wall Panel");
                climbs = new JLabel();
                climbs.setText(gym.getShowWall().getProblems());
                wallPanel.add(climbs);
            }
        });
        wallSelect = 1;
//        showWallPanel = new JPanel();
//        cards.add(showWallPanel, "Show Wall Panel");
//        JLabel climbs = new JLabel(gym.getShowWall().getProblems());
//        showWallPanel.add(climbs);
    }

    private void initSmallCave() {
        smallCaveButton = new JButton("Small Cave");
        smallCaveButton.setBounds(475, 50, 100, 25);
        photo.add(smallCaveButton);
        smallCaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Wall Panel");
                climbs = new JLabel();
                climbs.setText(gym.getSmallCave().getProblems());
                wallPanel.add(climbs);
            }
        });
        wallSelect = 5;

//        bergPanel = new JPanel();
//        cards.add(bergPanel, "Berg Panel");
//        JLabel climbs = new JLabel(gym.getBerg().getProblems());
//        bergPanel.add(climbs);
    }

    private void initBigCave() {
        bigCaveButton = new JButton("Big Cave");
        bigCaveButton.setBounds(600, 100, 100, 25);
        photo.add(bigCaveButton);
        bigCaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Wall Panel");
                climbs = new JLabel();
                climbs.setText(gym.getBigCave().getProblems());
                wallPanel.add(climbs);
            }
        });
        wallSelect = 6;

//        bergPanel = new JPanel();
//        cards.add(bergPanel, "Berg Panel");
//        JLabel climbs = new JLabel(gym.getBerg().getProblems());
//        bergPanel.add(climbs);
    }


    public void initSeeClimbs() {
        climbsPanel = new JPanel(new FlowLayout());
        allClimbs = new JLabel(gym.toStringAllProblems());
        climbsPanel.add(allClimbs);
        System.out.println(gym.toStringAllProblems());
        cards.add("Climbs Panel", climbsPanel);

        sortingOptionsComboBox = new JComboBox<String>(options);
        sortingOptionsComboBox.setSelectedIndex(OPTIONS_SIZE - 1);
        sortingOptionsGo = new JButton("Sort");
        sortingOptionsGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sortIndex();
            }
        });
        // TODO: 2020-03-12 : continue the sorting method, take input for how to sort, also reformat
        // TODO: possibly make names clickable to add to wishilst

        climbsPanel.add(sortingOptionsGo);
        climbsPanel.add(sortingOptionsComboBox);
        backClimbPanel = new JButton("Back");
        backClimbPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
            }
        });
        climbsPanel.add(backClimbPanel);
    }

    private void sortIndex() {
        if (sortingOptionsComboBox.getSelectedIndex() == 0) {
            allClimbs.setText(gym.toStringAllProblems());
        } else if (sortingOptionsComboBox.getSelectedIndex() == 1) {
            allClimbs.setText(gym.getAllClimbsInOrderOfDifficulty());
        }


    }

    public void initAddAndRemovePanel() {

        addRemovePanel = new JPanel();
        textBox1 = new JLabel("Add climbs below\nWhat is the color of the climb?");
        addRemovePanel.add(textBox1);
        colorClimbAdded = new JTextField();
        colorClimbAdded.setColumns(10);

        addRemovePanel.add(colorClimbAdded);

        addPanelWalls = new JComboBox(wallNames);
        addPanelWalls.setSelectedIndex(WALL_QUANTITY - 1);

        confirmAddOption = new JOptionPane("Success!");

        addRemovePanel.add(addPanelWalls);
        addPanelGrade = new JComboBox<>(grades);
        addRemovePanel.add(addPanelGrade);
        cards.add("Add Panel", addRemovePanel);

        backAddPanel = new JButton("Back");
        addRemovePanel.add(backAddPanel);
        backAddPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");

            }
        });

        initAddClimbButton();

        initRemovePanel();

    }

    //https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    private void initAddClimbButton() {
        addClimbButton = new JButton("Add");
        addClimbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int grade = addPanelGrade.getSelectedIndex() + 1;
                String color = colorClimbAdded.getText();
                int index = addPanelWalls.getSelectedIndex() + 1;
                gym.addProblem(new Problem(color, grade), selectWall(index));
                JOptionPane.showMessageDialog(addRemovePanel,
                        "Success!.",
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
                colorClimbAdded.setText("");
            }
        });
        addRemovePanel.add(addClimbButton);
    }

    public void initRemovePanel() {
        removeLabel = new JLabel("Remove climbs here");
        addRemovePanel.add(removeLabel);

        colorClimbRemove = new JTextField();
        colorClimbRemove.setColumns(10);

        addRemovePanel.add(colorClimbRemove);

        removePanelWalls = new JComboBox(wallNames);
        removePanelWalls.setSelectedIndex(WALL_QUANTITY - 1);

        addRemovePanel.add(removePanelWalls);
        removePanelGrade = new JComboBox<>(grades);
        addRemovePanel.add(removePanelGrade);
        removeClimbsButton = new JButton("Remove");
        addRemovePanel.add(removeClimbsButton);
        removeClimbsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int grade = addPanelGrade.getSelectedIndex() + 1;
                String color = colorClimbAdded.getText();
                int index = addPanelWalls.getSelectedIndex() + 1;
                // TODO: 2020-03-12: not possible to remove problems because I can't match problems,
                //  should probably make a seperate panel


                gym.addProblem(new Problem(color, grade), selectWall(index));

            }
        });

        addRemovePanel.add(removeClimbsButton);

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
