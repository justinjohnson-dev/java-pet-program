package model;


public class Pet implements Comparable<Pet> {
    private int id;
    private String name;
    private int age;

    public Pet(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // overriding the compareTo method by comparing by pet ID
    // Watch video: https://www.youtube.com/watch?v=5gzayWys06o
    // to understand how to properly implement this functionality
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