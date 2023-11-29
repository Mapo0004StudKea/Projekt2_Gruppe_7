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
    boolean competitionSwimmer;
    double price;
    boolean hasPaid =true;
    ArrayList<Result> listeResult = new ArrayList<>();
    ArrayList<Transaction> listTransaction = new ArrayList<>();
    double totalPayment;

    Member(int id, String name, LocalDate age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String toString() {
        System.out.println();
        System.out.println("ID       Fulde Navn           Alder        Medlemskab     Pris     Betalt?");
        return String.format("%-5d    %-17s    %-10s   %-12s   %-5s    %-7s   ",
                id, name, age, exercise, price, hasPaid);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
    public boolean getHasPaid() {
        return hasPaid;
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

    public boolean getExercise() {
        return exercise;
    }

    public boolean getCompetitionSwimmer() {
        return competitionSwimmer;
    }
    public void setCompetitionSwimmer(boolean competitionSwimmer) {
        this.competitionSwimmer = competitionSwimmer;
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

    public LocalDate getAge() {
        return age;
    }

    void MemberShipPayment(double amount) {
        listTransaction.add(new Transaction ("indbetalt", amount, price));
        price = price - amount;

    }
    void printTransektions(){
        System.out.println(this);
        System.out.println("text"+"\t"+"dato"+"\t"+"indbetalt");
        for ( Transaction t : listTransaction){
            System.out.println(t);
        }
        System.out.println();
    }
    void totalPayment(){
        for (int i = 0; i <listTransaction.size() ; i++) {
            double payment = listTransaction.get(0).amount + totalPayment;
            System.out.println(payment);
        }
    }
}