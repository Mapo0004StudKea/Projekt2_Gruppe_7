package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    String name;
    LocalDate age;
    String address;
    int id;
    boolean passive;
    boolean exercise;
    double price;
    ArrayList<Result> listeResult = new ArrayList<>();

    Member(int id, String name, LocalDate age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String toString() {
        return id + " " + name + " " + age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public boolean getPassive() {
        return passive;
    }
    public LocalDate getAge() {
        return age;
    }

    public boolean getExercise() {
        return exercise;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setExercise(boolean exercise) {
        this.exercise = exercise;
    }

    public ArrayList<Result> getListMember() {
        return listeResult;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }
}


