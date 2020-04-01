package ui;

import exceptions.ClimbAlreadyExists;
import exceptions.GradeOutOfBounds;
import model.Gym;
import model.Problem;
import model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallPanel extends JPanel {
    Wall currentWall;
    private JLabel textBox1;
    private JTextField colorClimbAdded;
    private JComboBox addPanelWalls;
    private JComboBox addPanelGrade;
    private JComboBox removePanelOptions;
    private String[] wallNames = {"Show Wall", "Ship", "Slab", "Berg", "Small Cave", "Big Cave"};
    private Integer[] grades = {1, 2, 3, 4, 5, 6,};
    private JButton removeClimbButtonMapPanel;
    private JButton backOutWallPanel;
    private JLabel climbs;
    private JButton addWallPanel;
    private Integer[] numbers;
    private JLabel removeInstructionsLabel;

    public WallPanel(Wall w, CardLayout cardLayout, JPanel cards) {

        currentWall = w;
        makeBackButton(cardLayout, cards);
        setUpWallPanel();


    }

    private void makeBackButton(CardLayout cardLayout, JPanel cards) {
        backOutWallPanel = new JButton("Back");
        add(backOutWallPanel);
        backOutWallPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cards, "Main Panel");
                //climbs.setText("");
                //wallSelect = 0;
                remove(removePanelOptions);
            }
        });
    }

    public void setUpFieldsAndLabelsForWallPanel() {

        textBox1 = new JLabel("<html>Add climbs to the right<br>Textbox is for the color of the climb.</html>");
        colorClimbAdded = new JTextField();
        colorClimbAdded.setColumns(10);
        addPanelWalls = new JComboBox(wallNames);
        addPanelWalls.setSelectedIndex(5);
        addPanelGrade = new JComboBox<>(grades);
        add(textBox1, BorderLayout.NORTH);
        textBox1.setPreferredSize(new Dimension(200, 50));
        textBox1.setBounds(100, 25, 250, 25);
        add(addPanelGrade, BorderLayout.CENTER);
        add(colorClimbAdded, BorderLayout.CENTER);
        // add(backOutWallPanel, BorderLayout.NORTH);
        backOutWallPanel.setBounds(10, 10,
                75, 25);
        colorClimbAdded.setBounds(350, 25, 75, 25);
        addPanelGrade.setBounds(450, 25, 75, 25);
        addWallPanel = new JButton("Add");
        addWallPanel.setBounds(600, 10, 75, 25);
    }

    public void setUpWallPanel() {

        setUpFieldsAndLabelsForWallPanel();
        addClimbForWallPanel();

        removeClimbsForWallPanel();
        climbs = new JLabel("");
        climbs.setText(getProblemsForRemoveGUI(currentWall));
        add(climbs, BorderLayout.CENTER);
        climbs.setBounds(100, 200, 200, 250);

        int numOptions = currentWall.getProblemList().size();
        numbers = new Integer[numOptions];

        for (int i = 0; i <= numOptions - 1; i++) {
            numbers[i] = i + 1;
        }
        removePanelOptions = new JComboBox(numbers);
        removePanelOptions.setBounds(500, 350, 75, 25);
        add(removePanelOptions, BorderLayout.SOUTH);
        removeInstructionsLabel = new JLabel("Choose the number corresponding to the climb you want to remove");
        add(removeInstructionsLabel);


    }

    private void removeClimbsForWallPanel() {
        removeClimbButtonMapPanel = new JButton("Remove");
        add(removeClimbButtonMapPanel, BorderLayout.SOUTH);
        removeClimbButtonMapPanel.setBounds(500, 300, 150, 25);
        removeClimbButtonMapPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Wall removeWall = currentWall;
                try {
                    removeWall.getProblemList().remove(removePanelOptions.getSelectedIndex());
                    JOptionPane.showMessageDialog(null,
                            "Success!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    climbs.setText(getProblemsForRemoveGUI(currentWall));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Something went wrong, go back and try again!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                }

                //todo Added exception handling to a trouble prone area just in case to make the program more robust

            }
        });
    }


    public void addClimbForWallPanel() {
        addWallPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int grade = addPanelGrade.getSelectedIndex() + 1;
                String color = colorClimbAdded.getText();
                try {
                    currentWall.addProblem(new Problem(color, grade));
                    JOptionPane.showMessageDialog(null,
                            "Success!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    colorClimbAdded.setText("");
                    climbs.setText(getProblemsForRemoveGUI(currentWall));
                } catch (ClimbAlreadyExists climbAlreadyExists) {
                    JOptionPane.showMessageDialog(null,
                            "Failed because climb already exists!.",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    colorClimbAdded.setText("");
                } catch (GradeOutOfBounds gradeOutOfBounds) {
                    JOptionPane.showMessageDialog(null,
                            "Failed because grade is out of bounds!",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                }

                //todo   Added exception handling here for the new exception thrown by addProblem


            }
        });
        add(addWallPanel);
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
