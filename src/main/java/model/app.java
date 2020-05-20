package model;

import java.io.*;
import java.util.*;

public class app {
    public static void main(String args[]) {
        app obj = new app();
        obj.run();
    }

    private void run() {
        try {
            Scanner userInput = new Scanner(System.in);

            log("would you like to start with a pre-defined pet list(0) or start from scratch(1)? ");
            int userSelection = userInput.nextInt();
            Set<Pet> pets = new HashSet<>();

            // Letting the user start with a pre-defined list of pets
            if(userSelection == 1) {
                File p = new File("J:\\Software Engineering\\petInput.txt");
                pets = readFromFile(p);
            }

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
                        writePetsToFile(pets);
                        break;
                    default:
                        log("\nInvalid input\n" +
                                "Please enter one of the following\n");
                        break;
                }
            }
        } catch(Exception e) {
            log("");
            System.out.println(e);
            log("You may have entered characters instead of numbers");
        }
    }

    // Function that reads data from .txt file and creates a list
    // Function referenced from week five of our data structures and algorithms class
    private Set<Pet> readFromFile(File file) {
        try {
            Set<Pet> pets = new HashSet<>();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String tempLine = "";

            while (tempLine != null) {
                tempLine = bufferedReader.readLine();
                if (tempLine != null) {
                    // found that you can only call the regex split on a array not a list
                    String[] tmpLineArray = tempLine.split("\\|");

                    // Creating a procInfo object
                    Pet p = new Pet();

                    // Setting the name,id,priority, and runtime while reading in from file
                    p.setId(Integer.parseInt(tmpLineArray[0]));
                    p.setName(tmpLineArray[1]);
                    p.setAge(Integer.parseInt(tmpLineArray[2]));

                    // Adding object to our list to be returned
                    pets.add(p);
                }
            }

            return pets;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private static void writePetsToFile(Set<Pet> pets) {
        try {
            // Using serializable to write pets to a txt file
            // code referenced from: https://stackoverflow.com/questions/17293991/how-to-write-and-read-java-serialized-objects-into-a-file
            FileOutputStream file = new FileOutputStream(new File("pets.txt"));
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(pets);

            stream.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception e) {
            System.out.println(e);
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

        printf();
        for (Pet pet : sortPets) {
            System.out.printf("%3d %10s %4d\n", pet.getId(), pet.getName(), pet.getAge());
        }

        log("");
    }

    private static void addPets(Set<Pet> pet) {
        Scanner input = new Scanner(System.in);
        boolean isDone = false;

        // setting PetID to the size of the list
        // so that if user decides to add more pets after the first looping
        // it will still add pets in the correct index.
        int petID = pet.size();
        if (pet.isEmpty()) {
            // if no pets are in memory
            petID = 0;
        }

        // Creating a while loop to continuously allow user to add pets
        // until user types 'done'
        while(!isDone) {
            log("add pet (name, age): ", true);
            String userInput = input.nextLine();

            // using the equalsIgnoreCase method
            // https://www.tutorialspoint.com/java/lang/string_equalsignorecase.htm
            if (userInput.equalsIgnoreCase("done")) {
                isDone = true;
            } else {
                if (pet.size() >= 5) {
                    log("Error: Database is full.");
                    isDone = true;
                } else {
                    String[] petEntry = userInput.split(" ");
                    String name = petEntry[0];
                    String ageAsString = petEntry[1];
                    int age = Integer.parseInt(ageAsString);

                    if (age > 20) {
                        log("Error: " + age + " is not a valid age.\n");
                    } else {
                        pet.add(new Pet(petID, name, age));
                    }
                    petID++;
                }
            }
        }
        log(" ");
    }

    private static void updatePet(Set<Pet> pets) {
        Scanner input = new Scanner(System.in);
        // Need to work with a list to properly manipulate pets
        ArrayList<Pet> petList = new ArrayList<>(pets);

        log("Enter the pet ID you can to update: ", true);
        int userChoice = Integer.parseInt(input.nextLine());

        // loop to find index of pet user wants to update
        // will delete the pet at that index and then create a new pet
        // save it into the index in which the old pet was deleted.
        for (Pet pet: petList) {
            if(pet.getId() == (userChoice)) {
                String oldPetName = pet.getName();
                int oldPetAge = pet.getAge();
                pets.remove(pet);

                log("Enter new name and new age: ", true);
                String newPet = input.nextLine();

                // creating an array from splitting the inputs
                // https://stackoverflow.com/questions/50903859/java-string-get-the-character-after-space/50903903
                String[] petEntry = newPet.split(" ");
                String name = petEntry[0];
                String ageAsString = petEntry[1];
                int age = Integer.parseInt(ageAsString);
                pets.add(new Pet(userChoice, name, age));

                log("");
                log(oldPetName + " " + oldPetAge + " changed to " + name + " " + age);
            }
        }
        log(" ");
    }

    private static void removePet(Set<Pet> pets) {
        Scanner input = new Scanner(System.in);
        // creating a pet List so we can properly remove pet
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
            // comparing strings, can use .equals()
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
        // using 3-10-4 / You may use 3 characters for ID, 10 characters for
        // NAME, and 4 characters for AGE.
        System.out.printf("\n%3s %10s %4s\n", "ID", "Name", "Age");
    }
}
