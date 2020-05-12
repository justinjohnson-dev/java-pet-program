package model;


public class Pet implements Comparable<Pet> {
    private int id;
    private static int nextId = 0;
    private String name;
    private int age;


    public Pet(String name, int age) {
        this.id = nextId;
        this.name = name;
        this.age = age;

        nextId++;
    }

    @Override
    public int compareTo(Pet pet) {
        return id - pet.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


