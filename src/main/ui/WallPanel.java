package ui;

import exceptions.ClimbAlreadyExists;
import model.Problem;
import model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallPanel extends JPanel {
    private Integer[] grades = {1, 2, 3, 4, 5, 6,};

    private final JButton removeClimbButtonMapPanel;
    private final Integer[] numbers;
    private final JComboBox removePanelOptions;
    private JButton backOutWallPanel;
    private JLabel climbs;
    private JLabel textBox1;
    private JTextField colorClimbAdded;
    private JButton addWallPanel;
    private Wall wall;
    JPanel panel;
    private JComboBox addPanelGrade;

    public WallPanel(Wall w, CardLayout cardLayout, JPanel cards) {
        this.wall = w;
        this.setLayout(null);
        panel = this;
        wallPanelSetup();
        backOutWallPanel = new JButton("Back");
        backOutWallPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
                climbs.setText("");
                remove(removePanelOptions);
            }
        });
        //cards.add(wallPanel, "Wall Panel");
        removeClimbButtonMapPanel = new JButton("Remove");
        this.add(removeClimbButtonMapPanel, BorderLayout.SOUTH);
        removeClimbButtonMapPanel.setBounds(500, 300, 150, 25);
        removeClimbButtonMapPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    w.getProblemList().remove(removePanelOptions.getSelectedIndex());
                    JOptionPane.showMessageDialog(panel,
                            "Success!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    climbs.setText(getProblemsForRemoveGUI(wall));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel,
                            "Something went wrong, go back and try again!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                }

                //todo Added exception handling to a trouble prone area just in case to make the program more robust

            }
        });

        //this is from the initWall methods
        climbs = new JLabel("");
        climbs.setText(getProblemsForRemoveGUI(wall));
        this.add(climbs, BorderLayout.CENTER);
        climbs.setBounds(100, 200, 200, 250);
        int numOptions = w.getProblemList().size();
        numbers = new Integer[numOptions];

        for (int i = 0; i <= numOptions - 1; i++) {
            numbers[i] = i + 1;
        }
        removePanelOptions = new JComboBox(numbers);
        removePanelOptions.setBounds(500, 350, 75, 25);
        this.add(removePanelOptions, BorderLayout.SOUTH);

        addClimbForWallPanel();

    }

    public void wallPanelSetup() {
        add(textBox1, BorderLayout.NORTH);
        textBox1.setPreferredSize(new Dimension(200, 50));
        textBox1.setBounds(100, 25, 250, 25);
        add(addPanelGrade, BorderLayout.CENTER);
        add(colorClimbAdded, BorderLayout.CENTER);
        add(backOutWallPanel, BorderLayout.NORTH);
        backOutWallPanel.setBounds(10, 10, 75, 25);
        colorClimbAdded.setBounds(350, 25, 75, 25);
        addPanelGrade.setBounds(450, 25, 75, 25);
        addWallPanel = new JButton("Add");
        addWallPanel.setBounds(600, 10, 75, 25);
    }


    public void addClimbForWallPanel() {
        // wallPanelSetup();

        addWallPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int grade = addPanelGrade.getSelectedIndex() + 1;
                String color = colorClimbAdded.getText();
                try {
                    wall.addProblem(new Problem(color, grade));
                    JOptionPane.showMessageDialog(panel,
                            "Success!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    colorClimbAdded.setText("");
                    climbs.setText(getProblemsForRemoveGUI(wall));
                } catch (ClimbAlreadyExists climbAlreadyExists) {
                    JOptionPane.showMessageDialog(panel,
                            "Failed because climb already exists!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    colorClimbAdded.setText("");
                }

                //todo   Added exception handling here for the new exception thrown by addProblem


            }
        });
        this.add(addWallPanel);


        textBox1 = new JLabel("<html>Add climbs below<br>What is the color of the climb?</html>");
        colorClimbAdded = new JTextField();
        colorClimbAdded.setColumns(10);
        addPanelGrade = new JComboBox<>(grades);


    }

    //EFFECTS: Prints out all problems on this wall with their information
    public String getProblemsForRemoveGUI(Wall w) {
        String s = "";
        int i = 1;
        for (Problem p : w.getProblemList()) {
            s += (i + ": Color: " + p.getColor() + " | Grade: " + p.getGrade() + " | Wall: " + p.getWall() + "\n");
            i++;
        }
        //code used from stackoverflow:https://stackoverflow.com/questions/1090098/newline-in-jlabel
        String s1 = ("<html>" + s.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        return s1;
    }

}
