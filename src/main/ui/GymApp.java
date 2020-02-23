package ui;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.Gym;
import model.Problem;
import model.Wall;
import persistence.Reader;

//Gym application
public class GymApp {
    Gym gym;
    private static final String GYM_FILE = "./data/climbs.txt";

    //EFFECTS: starts gym application
    public GymApp() {
        runGym();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    public void runGym() {
        int response = 0;
        Scanner kbReader = new Scanner(System.in);
        gym = new Gym();

        while (true) {
            displayOptions();
            response = kbReader.nextInt();

            if (response == 9) {
                System.exit(1);
            } else {
                performAction(response);
            }


        }
    }

    //MODIFIES: this
    //EFFECTS: Loads climbs from the gym file, if climbs exist;
    //         otherwise it will initilaize an empty gym
    public void loadGym() {
        try {
            List<Problem> list = Reader.readGym(new File(GYM_FILE));
        } catch (IOException e) {
            gym = new Gym();
        }
    }


    //MODIFIES: this
    //EFFECTS: select the action for the program
    public void performAction(int choice) {
        if (choice == 1) {
            displayAll();
        } else if (choice == 2) {
            displaySorted();
        } else if (choice == 3) {
            removeClimbs();
        } else if (choice == 4) {
            displayClimbsOfDifficulty();
        } else if (choice == 5) {
            addClimbs();
        } else {
            System.out.println("Selection is not valid");
        }
    }

    //EFFECTS: display all climbs
    public void displayAll() {
        if (!gym.hasClimbs()) {
            System.out.println("Gym has no climbs");
        } else {
            System.out.println(gym.toStringAllProblems());

        }


    }

    //EFFECTS: display climbs sorted by grades
    public void displaySorted() {
        if (!gym.hasClimbs()) {
            System.out.println("Gym has no climbs");
        } else {
            System.out.println(gym.getAllClimbsInOrderOfDifficulty());
        }
    }

    //MODIFIES: this
    //EFFECTS: if climbs exist, allow user to pick a climb to remove from gym
    public void removeClimbs() {
        if (!gym.hasClimbs()) {
            System.out.println("Gym has no climbs");
        } else {
            Scanner kbReader = new Scanner(System.in);
            System.out.println(gym.toStringAllProblems());
            System.out.println("What wall is the climb you want to remove on?");
            System.out.println("1: Show Wall\n2: Ship\n3: Slab\n4: Berg\n5: Small Cave\n6: Big Cave");
            int response = kbReader.nextInt();
            Wall w = selectWall(response);
            if (w.getProblemList().isEmpty()) {
                System.out.println("No climbs");
            } else {
                System.out.println("Press the number corresponding to the climb you want to remove");
                System.out.println(getProblemsForApp(w));
                int input = kbReader.nextInt();
                w.getProblemList().remove(input - 1);
            }

        }

    }

    //EFFECTS: Prints out all problems on this wall with their information
    public String getProblemsForApp(Wall w) {
        String s = "";
        int i = 1;
        for (Problem p : w.getProblemList()) {
            s += (i + ": Color: " + p.getColor() + " | Grade: " + p.getGrade() + " | Wall: " + p.getWall() + "\n");
            i++;
        }
        return s;
    }


    //REQUIRES: response is between 1 and 6
    //EFFECTS: user input selects a wall to operate on
    public Wall selectWall(int response) {
        if (response == 1) {
            return gym.getShowWall();
        } else if (response == 2) {
            return gym.getShip();
        } else if (response == 3) {
            return gym.getSlab();
        } else if (response == 4) {
            return gym.getBerg();
        } else if (response == 5) {
            return gym.getSmallCave();
        }
        return gym.getBigCave();

    }

    //EFFECTS: displays all climbs of a specific difficulty
    public void displayClimbsOfDifficulty() {
        if (!gym.hasClimbs()) {
            System.out.println("Gym has no climbs");
        } else {
            Scanner kbReader = new Scanner(System.in);
            System.out.println("What grade of climbs do you want to see? (1-6)");
            int response = kbReader.nextInt();
            System.out.println(gym.getAllClimbsOfDifficulty(response));

        }

    }

    //MODIFIES: this
    //EFFECTS: adds a climb to the gym
    public void addClimbs() {
        Scanner kbReader = new Scanner(System.in);
        System.out.println("What color is the climb?");
        String color = kbReader.nextLine();
        System.out.println("What grade is it? (1-6)");
        int grade = kbReader.nextInt();
        System.out.println("What wall is it on?");
        System.out.println("1: Show Wall\n2: Ship\n3: Slab\n4: Berg\n5: Small Cave\n6: Big Cave");
        int response = kbReader.nextInt();
        Wall w = selectWall(response);
        gym.addProblem(new Problem(color, grade), w);
    }


    //EFFECTS: displays all actions application can take
    public void displayOptions() {
        System.out.println("Select from the below operations:\n\n1: Display all climbs\n2: Display all climbs sorted "
                + "by difficulty\n3: Remove climbs from the gym\n"
                + "4: Display all climbs of a specific difficulty\n5: Add new climbs to the gym\nPress 9 to exit");
    }
}
