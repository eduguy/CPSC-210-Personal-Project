package ui;

import exceptions.ClimbAlreadyExists;
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

public class MainPanel extends JFrame {

    private static final int HEIGHT = 500;
    private static final int WIDTH = 750;
    private static final String GYM_FILE = "./data/GymFile.txt";

    JPanel mainPanel;
    JPanel cards;
    Gym gym;

    private JButton seeClimbsButton;


    String[] options = {"Wall", "Difficulty"};
    CardLayout cardLayout = new CardLayout();
    private JPanel climbsPanel;
    private JLabel allClimbs;
    private JButton backClimbPanel;

    private JComboBox<String> sortingOptionsComboBox;
    private static int OPTIONS_SIZE = 1;
    private JButton sortingOptionsGo;

    JLabel photo;
    private JButton slabButton;
    private JButton bigCaveButton;
    private JButton smallCaveButton;
    private JButton showWallButton;
    private JButton bergButton;
    private JLabel kidsAreaLabel;

    private JButton shipButton;
    private JPanel wallPanel;


    public MainPanel() {
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

        loadGym();
        //setting up card layout
        cards = new JPanel(cardLayout);
        mainPanel = new JPanel(new BorderLayout());
        cards.add("Main Panel", mainPanel);
        add(cards);
        //setting up all panels that will be seen
        initSeeAllClimbsPanel();


        initHomePanel();

        setVisible(true);

    }

    public void initHomePanel() {

//        addNewButton = new JButton("Click here to add climbs");
//        addNewButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                CardLayout c1 = new CardLayout();
//                cardLayout.show(cards, "Add Panel");
//            }
//        });
        //allClimbs.setText(gym.toStringAllProblems());
        //removeClimbsButton = new JButton("Click here to remove climbs.");
        //mainPanel.add(addNewButton, BorderLayout.NORTH);

        seeClimbsButton = new JButton("Click here to see all climbs");
        seeClimbsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Climbs Panel");
            }
        });
        JLabel intro = new JLabel("Press on one of the walls to see more information!");
        mainPanel.add(intro, BorderLayout.NORTH);
        mainPanel.add(seeClimbsButton, BorderLayout.SOUTH);
        initMapPanel();


    }

    private void initMapPhoto() {
        photo = new JLabel();
        photo.setLayout(null);
        ImageIcon img = new ImageIcon("./data/Photo.png");
        photo.setIcon(img);
        mainPanel.add(photo, BorderLayout.CENTER);
        photo.setBounds(0, -45, 750, 500);
    }

    public void initMapPanel() {
        initMapPhoto();
        kidsAreaLabel = new JLabel("Children's Area");
        kidsAreaLabel.setBounds(50, 10, 125, 25);
        photo.add(kidsAreaLabel);

        initSlab();
        initBigCave();
        initSmallCave();
        initShowWall();
        initBerg();
        initShip();

        //setUpWallPanel();


    }


//    public void setUpWallPanel() {
//
//        // wallPanel = new JPanel(null);
//        //cards.add(wallPanel, "Wall Panel");
//
//        backButtonForWallPanel();
//        setUpFieldsAndLabelsForWallPanel();
//        removeClimbsForWallPanel();
//        addClimbForWallPanel();
//
//    }

//    private void removeClimbsForWallPanel() {
//        removeClimbButtonMapPanel = new JButton("Remove");
//        wallPanel.add(removeClimbButtonMapPanel, BorderLayout.SOUTH);
//        removeClimbButtonMapPanel.setBounds(500, 300, 150, 25);
//        removeClimbButtonMapPanel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                removeWall = selectWall(wallSelect);
//                try {
//                    removeWall.getProblemList().remove(removePanelOptions.getSelectedIndex());
//                    JOptionPane.showMessageDialog(addRemovePanel,
//                            "Success!.",
//                            "Message",
//                            JOptionPane.PLAIN_MESSAGE);
//                    climbs.setText(getProblemsForRemoveGUI(selectWall(wallSelect)));
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(addRemovePanel,
//                            "Something went wrong, go back and try again!.",
//                            "Message",
//                            JOptionPane.PLAIN_MESSAGE);
//                }
//
//
//            }
//        });
//    }
//
//    public void backButtonForWallPanel() {
//        backOutWallPanel = new JButton("Back");
//        backOutWallPanel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                cardLayout.show(cards, "Main Panel");
//                climbs.setText("");
//                wallSelect = 0;
//                wallPanel.remove(removePanelOptions);
//            }
//        });
//    }
//
//    public void addClimbForWallPanel() {
//        addWallPanel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//
//                int grade = addPanelGrade.getSelectedIndex() + 1;
//                String color = colorClimbAdded.getText();
//                try {
//                    gym.addProblem(new Problem(color, grade), selectWall(wallSelect));
//                    JOptionPane.showMessageDialog(addRemovePanel,
//                            "Success!.",
//                            "Message",
//                            JOptionPane.PLAIN_MESSAGE);
//                    colorClimbAdded.setText("");
//                    climbs.setText(getProblemsForRemoveGUI(selectWall(wallSelect)));
//                } catch (ClimbAlreadyExists climbAlreadyExists) {
//                    JOptionPane.showMessageDialog(addRemovePanel,
//                            "Failed because climb already exists!.",
//                            "Message",
//                            JOptionPane.PLAIN_MESSAGE);
//                    colorClimbAdded.setText("");
//                }
//
//
//
//            }
//        });
//        wallPanel.add(addWallPanel);
//    }
//
//    public void setUpFieldsAndLabelsForWallPanel() {
//
//        textBox1 = new JLabel("<html>Add climbs below<br>What is the color of the climb?</html>");
//        colorClimbAdded = new JTextField();
//        colorClimbAdded.setColumns(10);
//        addPanelWalls = new JComboBox(wallNames);
//        addPanelWalls.setSelectedIndex(WALL_QUANTITY - 1);
//        addPanelGrade = new JComboBox<>(grades);
//        wallPanel.add(textBox1, BorderLayout.NORTH);
//        textBox1.setPreferredSize(new Dimension(200, 50));
//        textBox1.setBounds(100, 25, 250, 25);
//        wallPanel.add(addPanelGrade, BorderLayout.CENTER);
//        wallPanel.add(colorClimbAdded, BorderLayout.CENTER);
//        wallPanel.add(backOutWallPanel, BorderLayout.NORTH);
//        backOutWallPanel.setBounds(10, 10,
//                75, 25);
//        colorClimbAdded.setBounds(350, 25, 75, 25);
//        addPanelGrade.setBounds(450, 25, 75, 25);
//        addWallPanel = new JButton("Add");
//        addWallPanel.setBounds(600, 10, 75, 25);
//    }

    private void initShip() {

        shipButton = new JButton("Ship");
        shipButton.setBounds(420, 325, 75, 25);
        photo.add(shipButton);
        shipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setUpWallPanel(gym.getShip());
//                climbs = new JLabel("");
//                climbs.setText(getProblemsForRemoveGUI(gym.getShip()));
//                wallPanel.add(climbs, BorderLayout.CENTER);
//                climbs.setBounds(100, 200, 200, 250);
//                int numOptions = gym.getShip().getProblemList().size();
//                numbers = new Integer[numOptions];
//
//                for (int i = 0; i <= numOptions - 1; i++) {
//                    numbers[i] = i + 1;
//                }
//                removePanelOptions = new JComboBox(numbers);
//                removePanelOptions.setBounds(500, 350, 75, 25);
//                wallPanel.add(removePanelOptions, BorderLayout.SOUTH);
            }
        });

    }

    public void initSlab() {

        slabButton = new JButton("Slab");
        slabButton.setBounds(250, 375, 75, 25);
        photo.add(slabButton);
        slabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // wallSelect = 3;
                setUpWallPanel(gym.getSlab());
//                climbs = new JLabel();
//                climbs.setText(getProblemsForRemoveGUI(gym.getSlab()));
//                wallPanel.add(climbs, BorderLayout.CENTER);
//
//                int numOptions = gym.getSlab().getProblemList().size();
//                numbers = new Integer[numOptions];
//
//                for (int i = 0; i <= numOptions - 1; i++) {
//                    numbers[i] = i + 1;
//                }
//                removePanelOptions = new JComboBox(numbers);
//                removePanelOptions.setBounds(350, 350, 75, 25);
//
//                wallPanel.add(removePanelOptions);
//
//                climbs.setBounds(100, 200, 200, 250);

            }
        });

    }

    private void initBerg() {

        bergButton = new JButton("Berg");
        bergButton.setBounds(400, 50, 100, 25);
        photo.add(bergButton);
        bergButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //wallSelect = 4;
                setUpWallPanel(gym.getBerg());

//                climbs = new JLabel();
//                climbs.setText(getProblemsForRemoveGUI(gym.getBerg()));
//                wallPanel.add(climbs, BorderLayout.CENTER);
//
//                int numOptions = gym.getBerg().getProblemList().size();
//                numbers = new Integer[numOptions];
//
//                for (int i = 0; i <= numOptions - 1; i++) {
//                    numbers[i] = i + 1;
//                }
//                removePanelOptions = new JComboBox(numbers);
//                removePanelOptions.setBounds(350, 350, 75, 25);
//
//                wallPanel.add(removePanelOptions);
//
//                climbs.setBounds(100, 200, 200, 250);

            }
        });

    }

    private void initShowWall() {

        showWallButton = new JButton("Show Wall");
        showWallButton.setBounds(590, 350, 100, 25);
        photo.add(showWallButton);
        showWallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //wallSelect = 1;
                setUpWallPanel(gym.getShowWall());
//                climbs = new JLabel();
//                climbs.setText(getProblemsForRemoveGUI(gym.getShowWall()));
//                wallPanel.add(climbs, BorderLayout.CENTER);
//
//                int numOptions = gym.getShowWall().getProblemList().size();
//                numbers = new Integer[numOptions];
//
//                for (int i = 0; i <= numOptions - 1; i++) {
//                    numbers[i] = i + 1;
//                }
//                removePanelOptions = new JComboBox(numbers);
//                removePanelOptions.setBounds(350, 350, 75, 25);
//
//                wallPanel.add(removePanelOptions);
//
//                climbs.setBounds(100, 200, 200, 250);

            }
        });
    }

    private void initSmallCave() {

        smallCaveButton = new JButton("Small Cave");
        smallCaveButton.setBounds(515, 50, 100, 25);
        photo.add(smallCaveButton);
        smallCaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // wallSelect = 5;
                setUpWallPanel(gym.getSmallCave());
//                climbs = new JLabel();
//                climbs.setText(gym.getSmallCave().getProblems());
//                climbs.setBounds(100, 200, 200, 250);
//                wallPanel.add(climbs);
//
//                int numOptions = gym.getSmallCave().getProblemList().size();
//                numbers = new Integer[numOptions];
//
//                for (int i = 0; i <= numOptions - 1; i++) {
//                    numbers[i] = i + 1;
//                }
//                removePanelOptions = new JComboBox(numbers);
//                removePanelOptions.setBounds(350, 300, 75, 25);
//
//                wallPanel.add(removePanelOptions);


            }
        });


    }

    private void initBigCave() {
        bigCaveButton = new JButton("Big Cave");
        bigCaveButton.setBounds(600, 100, 100, 25);
        photo.add(bigCaveButton);
        bigCaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //wallSelect = 6;
                setUpWallPanel(gym.getBigCave());
//                climbs = new JLabel();
//                climbs.setText(getProblemsForRemoveGUI(gym.getBigCave()));
//                wallPanel.add(climbs, BorderLayout.CENTER);
//
//                int numOptions = gym.getBigCave().getProblemList().size();
//                numbers = new Integer[numOptions];
//
//                for (int i = 0; i <= numOptions - 1; i++) {
//                    numbers[i] = i + 1;
//                }
//                removePanelOptions = new JComboBox(numbers);
//                removePanelOptions.setBounds(350, 350, 75, 25);
//
//                wallPanel.add(removePanelOptions);
//
//                climbs.setBounds(100, 200, 200, 250);

            }
        });

    }

    private void setUpWallPanel(Wall wall) {
        wallPanel = new WallPanel(wall, cardLayout, cards);
        cards.add(wallPanel, "Wall Panel");
        cardLayout.show(cards, "Wall Panel");
    }


    public void initSeeAllClimbsPanel() {
        climbsPanel = new JPanel();
        allClimbs = new JLabel();
        climbsPanel.add(allClimbs);
        cards.add("Climbs Panel", climbsPanel);

        initSortingOptionsComboBox();


        climbsPanel.add(sortingOptionsGo);
        climbsPanel.add(sortingOptionsComboBox);
        backClimbPanel = new JButton("Back");
        backClimbPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
                allClimbs.setText("");
            }
        });
        climbsPanel.add(backClimbPanel);
    }

    public void initSortingOptionsComboBox() {
        sortingOptionsComboBox = new JComboBox<String>(options);
        sortingOptionsComboBox.setSelectedIndex(OPTIONS_SIZE - 1);
        sortingOptionsGo = new JButton("Sort");
        sortingOptionsGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sortingForComboBox();
            }
        });
    }

    private void sortingForComboBox() {
        if (sortingOptionsComboBox.getSelectedIndex() == 0) {
            String response = sortIndexAllClimbs();
            if (response.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "There are no climbs!",
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                allClimbs.setText(response);
            }
        } else if (sortingOptionsComboBox.getSelectedIndex() == 1) {
            String first = gym.getAllClimbsInOrderOfDifficulty();
            if (first.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "There are no climbs!",
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                String s1 = ("<html>" + first.replaceAll("<", "&lt;")
                        .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
                allClimbs.setText(s1);
            }
        }


    }

    public String sortIndexAllClimbs() {
        String record = "";
        for (Wall w : gym.wallList) {

            record += w.getProblems();

        }
        if (record.equals("")) {
            return "";
        }
        String s1 = ("<html>" + record.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        return s1;
    }

//    public void initAddAndRemovePanel() {
//
//        addRemovePanel = new JPanel();
//        textBox1 = new JLabel("<html>Add climbs below<br>What is the color of the climb?</html>");
//        addRemovePanel.add(textBox1);
//        colorClimbAdded = new JTextField();
//        colorClimbAdded.setColumns(10);
//
//        addRemovePanel.add(colorClimbAdded);
//
//        addPanelWalls = new JComboBox(wallNames);
//        addPanelWalls.setSelectedIndex(WALL_QUANTITY - 1);
//
//        confirmAddOption = new JOptionPane("Success!");
//
//        addRemovePanel.add(addPanelWalls);
//        addPanelGrade = new JComboBox<>(grades);
//        addRemovePanel.add(addPanelGrade);
//        cards.add("Add Panel", addRemovePanel);
//
//        backAddPanel = new JButton("Back");
//        addRemovePanel.add(backAddPanel);
//        backAddPanel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                cardLayout.show(cards, "Main Panel");
//
//            }
//        });

//        initAddClimbButton();
//
//        initRemovePanel();

//    }

//    //https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
//    private void initAddClimbButton() {
//        addClimbButton = new JButton("Add");
//        addClimbButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                int grade = addPanelGrade.getSelectedIndex() + 1;
//                String color = colorClimbAdded.getText();
//                int index = addPanelWalls.getSelectedIndex() + 1;
//                try {
//                    gym.addProblem(new Problem(color, grade), selectWall(index));
//                } catch (ClimbAlreadyExists climbAlreadyExists) {
//                    climbAlreadyExists.printStackTrace();
//                }
//                JOptionPane.showMessageDialog(addRemovePanel,
//                        "Success!.",
//                        "Message",
//                        JOptionPane.PLAIN_MESSAGE);
//                colorClimbAdded.setText("");
//            }
//        });
//        addRemovePanel.add(addClimbButton);
//    }
//
//    public void initRemovePanel() {
//        removeLabel = new JLabel("Remove climbs here");
//        addRemovePanel.add(removeLabel);
//
//        colorClimbRemove = new JTextField();
//        colorClimbRemove.setColumns(10);
//
//        addRemovePanel.add(colorClimbRemove);
//
//        removePanelWalls = new JComboBox(wallNames);
//        removePanelWalls.setSelectedIndex(WALL_QUANTITY - 1);
//
//        addRemovePanel.add(removePanelWalls);
//        removePanelGrade = new JComboBox<>(grades);
//        addRemovePanel.add(removePanelGrade);
//        removeClimbsButton = new JButton("Remove");
//        addRemovePanel.add(removeClimbsButton);
//
//        removeClimbsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                int grade = addPanelGrade.getSelectedIndex() + 1;
//                String color = colorClimbAdded.getText();
//                int index = addPanelWalls.getSelectedIndex() + 1;
//
//                try {
//                    gym.addProblem(new Problem(color, grade), selectWall(index));
//                } catch (ClimbAlreadyExists climbAlreadyExists) {
//                    climbAlreadyExists.printStackTrace();
//                }
//
//            }
//        });
//
//        addRemovePanel.add(removeClimbsButton);
//
//    }

    //    public Wall selectWall(int index) {
//        if (index == 1) {
//            return gym.getShowWall();
//        } else if (index == 2) {
//            return gym.getShip();
//        } else if (index == 3) {
//            return gym.getSlab();
//        } else if (index == 4) {
//            return gym.getBerg();
//        } else if (index == 5) {
//            return gym.getSmallCave();
//        }
//        return gym.getBigCave();
//
//    }
//
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
        } catch (IOException | ClimbAlreadyExists e) {
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
        //System.out.println("Gym saved");

    }

//    //EFFECTS: Prints out all problems on this wall with their information
//    public String getProblemsForRemoveGUI(Wall w) {
//        String s = "";
//        int i = 1;
//        for (Problem p : w.getProblemList()) {
//            s += (i + ": Color: " + p.getColor() + " | Grade: " + p.getGrade() + " | Wall: " + p.getWall() + "\n");
//            i++;
//        }
//        //code used from stackoverflow:https://stackoverflow.com/questions/1090098/newline-in-jlabel
//        String s1 = ("<html>" + s.replaceAll("<", "&lt;")
//                .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
//        return s1;
//    }
//
}
