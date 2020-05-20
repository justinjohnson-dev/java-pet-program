package model;
import java.io.Serializable;


public class Pet implements Comparable<Pet>, Serializable {
    private int id;
    private String name;
    private int age;

    public Pet() {}

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

    // to string method to write to txt file
    // referenced from: https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    @Override
    public String toString() {
        return "ID:" + id + "\nName:" + name + "\nAge:" + age;
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