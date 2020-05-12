package model;

import java.util.*;

public class app {
    public static void main(String args[]) {
        run();
    }

    private static void run() {
        Scanner userInput = new Scanner(System.in);

        Set<Pet> pets = new HashSet<>();

        boolean isRunning = true;

        while (isRunning) {
            int mode;

            displayMenu();

            mode = userInput.nextInt();
            switch (mode) {
                case 1:
                    viewAllPets(pets);
                    break;
                case 2:
                    addPets(pets);
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

    private static void viewAllPets(Set<Pet> pets) {
        // creating a list and sorting by IDs
        ArrayList<Pet> sortPets = new ArrayList<>(pets);
        Collections.sort(sortPets);

        System.out.printf("\n%3s %10s %4s\n", "ID", "Name", "Age");
        for (Pet pet : sortPets) {
            System.out.printf("%3d %10s %4d\n", pet.getId(), pet.getName(), pet.getAge());
        }

        log("");
    }

    private static void addPets(Set<Pet> pet) {
        Scanner input = new Scanner(System.in);

        boolean isDone = false;

        while(!isDone) {
            log("add pet (name, age): ", true);
            String userInput = input.nextLine();

            if (userInput.equals("done") || userInput.equals("Done")) {
                isDone = true;
            } else {
                String[] petEntry = userInput.split(" ");
                String name = petEntry[0];
                String ageAsString = petEntry[1];
                int age = Integer.parseInt(ageAsString);
                pet.add(new Pet(name, age));
            }
        }
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
