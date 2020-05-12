package model;

import java.util.Scanner;

public class app {

    public static void main(String args[]) {
        run();
    }

    private static void run() {
        Scanner userInput = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            int mode;

            displayMenu();

            mode = userInput.nextInt();
            switch (mode) {
                case 1:
                    viewAllPets();
                    break;
                case 2:
                    addPets();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    log("\nInvalid input\n" +
                            "Please enter one of the following\n");
                    break;
            }
        }
    }

    private static void displayMenu() {
        log("What would you like to do?");
        log("\t1) View all pets");
        log("\t2) Add more pets");
        log("\t3) Update an existing pet");
        log("\t4) Remove an existing pet");
        log("\t5) Search pets by name");
        log("\t6) Search pets by age");
        log("\t7) exit program");
        log("Your choice: ", true);
    }

    private static void viewAllPets() {
        log("All the pets will display here");
        log("");
    }

    private static void addPets() {
        Pet newPet = new Pet();
        Scanner input = new Scanner(System.in);

        boolean isDone = false;

        while(!isDone) {
            log("add pet (name, age): ");
            String userInput = input.next();

            if (userInput.equals("done")) {
                isDone = true;
            } else {
                newPet.setName(userInput);
            }
        }

        System.out.print(newPet);
    }

    private static void log(String m, boolean isSameLine) {
        if (isSameLine) {
            System.out.print(m);
        } else {
            System.out.println(m);
        }
    }

    private static void log(String m) {
        System.out.println(m);
    }
}
