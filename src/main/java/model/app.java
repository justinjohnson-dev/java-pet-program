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
                    updatePet(pets);
                    break;
                case 4:
                    removePet(pets);
                    break;
                case 5:
                    searchPetsByName(pets);
                    break;
                case 6:
                    searchPetsByAge(pets);
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

        // using 3-10-4 / You may use 3 characters for ID, 10 characters for
        // NAME, and 4 characters for AGE.
        printf();
        for (Pet pet : sortPets) {
            System.out.printf("%3d %10s %4d\n", pet.getId(), pet.getName(), pet.getAge());
        }

        log("");
    }

    private static void addPets(Set<Pet> pet) {
        Scanner input = new Scanner(System.in);

        boolean isDone = false;

        int petID = 0;
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
                pet.add(new Pet(petID, name, age));

                petID++;
            }
        }
        log(" ");
    }

    private static void updatePet(Set<Pet> pets) {
        Scanner input = new Scanner(System.in);
        ArrayList<Pet> petList = new ArrayList<>(pets);

        log("Enter the pet ID you can to update: ", true);
        int userChoice = Integer.parseInt(input.nextLine());

        for (Pet pet: petList) {
            if(pet.getId() == (userChoice)) {
                pets.remove(pet);

                log("Enter new name and new age: ");
                String newPet = input.nextLine();

                // creating an array from splitting the inputs
                // https://stackoverflow.com/questions/50903859/java-string-get-the-character-after-space/50903903
                String[] petEntry = newPet.split(" ");
                String name = petEntry[0];
                String ageAsString = petEntry[1];
                int age = Integer.parseInt(ageAsString);
                pets.add(new Pet(userChoice, name, age));
            }
        }
        log(" ");
    }

    private static void removePet(Set<Pet> pets) {
        Scanner input = new Scanner(System.in);
        ArrayList<Pet> petList = new ArrayList<>(pets);

        log("Enter the pet ID to remove: ", true);
        int userChoice = Integer.parseInt(input.nextLine());

        for (Pet pet: petList) {
            if(pet.getId() == (userChoice)) {
                log(pet.getName() + " " + pet.getAge() + " is removed.");
                pets.remove(pet);
            }
        }
        log(" ");
    }

    private static void searchPetsByName(Set<Pet> pets) {
        Scanner input = new Scanner(System.in);

        log("Enter a name to search: ", true);
        String userChoice = input.nextLine();

        printf();
        for (Pet pet: pets) {
            if(pet.getName().equals(userChoice)) {
                System.out.printf("%3d %10s %4d\n", pet.getId(), pet.getName(), pet.getAge());
            }
        }
        log(" ");
    }

    private static void searchPetsByAge(Set<Pet> pets) {
        Scanner input = new Scanner(System.in);

        log("Enter age to search: ", true);
        int userChoice = input.nextInt();

        printf();
        for (Pet pet: pets) {
            if(pet.getAge() == (userChoice)) {
                System.out.printf("%3d %10s %4d\n", pet.getId(), pet.getName(), pet.getAge());
            }
        }
        log(" ");
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

    private static void printf() {
        System.out.printf("\n%3s %10s %4s\n", "ID", "Name", "Age");
    }
}