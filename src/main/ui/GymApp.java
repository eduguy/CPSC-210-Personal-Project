package ui;

import java.util.Scanner;
import model.Gym;

public class GymApp {
    Gym gym;

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
        } else if (choice.equals("w")) {
            doWithdrawal();
        } else if (choice.equals("t")) {
            doTransfer();
        } else if (choice.equals("s")) {
            saveAccounts();
        } else if (choice.equals("p")) {
            printAccount();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public void displayAll() {

    }

    public void displayOptions() {
        System.out.println("Select from the below operations\n 1: Display all climbs\n 2: Display all climbs sorted "
                + "by difficulty\n 3: Show your to do list\n 4: Show your completed climbs\n "
                + "5: Display all climbs of a specific difficulty\n 6: Add new climbs to the gym");
    }
}
