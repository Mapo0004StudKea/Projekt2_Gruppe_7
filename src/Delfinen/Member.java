package Delfinen;

import java.time.LocalDate;

public class Member {
    String name;
    LocalDate age;
    String adress;
    int id;
    boolean passive;
    boolean exercise;

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
}


