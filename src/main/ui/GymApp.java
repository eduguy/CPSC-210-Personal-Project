package ui;

import java.util.Scanner;

import model.Gym;
import model.Problem;

public class GymApp {

    public GymApp() {
        runGym();
    }

    Gym gym;

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

    public void performAction(int choice) {
        if (choice == 1) {
            displayAll();
        } else if (choice == 2) {
            displaySorted();
        } else if (choice == 3) {
            displayToDo();
        } else if (choice == 4) {
            displayClimbsOfDifficulty();
        } else if (choice == 5) {
            addClimbs();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public void displayAll() {
        if (!gym.hasClimbs()) {
            System.out.println("Gym has no climbs");
        } else {
            System.out.println(gym.toStringAllProblems());

        }


    }

    public void displaySorted() {
        if (!gym.hasClimbs()) {
            System.out.println("Gym has no climbs");
        } else {
            System.out.println("test");
            System.out.println(gym.getAllClimbsInOrderOfDifficulty());
        }
    }

    public void displayToDo() {

    }

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

    public void addClimbs() {
        Scanner kbReader = new Scanner(System.in);
        System.out.println("What color is the climb?");
        String color = kbReader.nextLine();
        System.out.println("What grade is it? (1-6)");
        int grade = kbReader.nextInt();
        System.out.println("What wall is it on?");
        System.out.println("1: Show Wall\n2:Ship\n3:Slab\n4:Berg\n5:Small Cave\n6:Big Cave");
        int response = kbReader.nextInt();
        if (response == 1) {
            gym.addProblem(new Problem(color, grade), gym.getShowWall());
        } else if (response == 2) {
            gym.addProblem(new Problem(color, grade), gym.getShip());
        } else if (response == 3) {
            gym.addProblem(new Problem(color, grade), gym.getSlab());
        } else if (response == 4) {
            gym.addProblem(new Problem(color, grade), gym.getBerg());
        } else if (response == 5) {
            gym.addProblem(new Problem(color, grade), gym.getSmallCave());
        } else if (response == 6) {
            gym.addProblem(new Problem(color, grade), gym.getBigCave());
        } else {
            System.out.println("Invalid wall");
        }
    }

//    public void addtoTodo() {
//
//    }

    public void displayOptions() {
        System.out.println("Select from the below operations:\n\n1: Display all climbs\n2: Display all climbs sorted "
                + "by difficulty\n3: Show your to do list\n"
                + "4: Display all climbs of a specific difficulty\n5: Add new climbs to the gym\nPress 9 to exit");
    }
}
