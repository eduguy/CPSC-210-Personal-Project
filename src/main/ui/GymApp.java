package ui;

import java.util.Scanner;

public class GymApp {

    public GymApp() {
        runGym();
    }

    public void runGym() {
        int response = 0;
        Scanner kbReader = new Scanner(System.in);

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
        //display climbs
        Scanner kbReader = new Scanner(System.in);
        System.out.println("Do you want to add any of these climbs to your to do list?\n 0 -> No \n 1 -> Yes");
        int response = kbReader.nextInt();
        if (response == 1) {
            // addtoTodo();
        }


    }

    public void displaySorted() {

    }

    public void displayToDo() {

    }

    public void displayClimbsOfDifficulty() {

    }

    public void addClimbs() {

    }

    public void addtoTodo() {

    }

    public void displayOptions() {
        System.out.println("Select from the below operations\n 1: Display all climbs\n 2: Display all climbs sorted "
                + "by difficulty\n 3: Show your to do list\n  "
                + "4: Display all climbs of a specific difficulty\n 5: Add new climbs to the gym");
    }
}
