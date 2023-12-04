package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Member {
    String name;
    LocalDate age;
    String address;
    int id;
    boolean competitionSwimmer;
    boolean passive;
    boolean exercise;
    boolean JunSen;
    double price;
    boolean hasPaid =true;
    ArrayList<Result> listeResult = new ArrayList<>();
    ArrayList<Result> rygsvømningListe = new ArrayList<>();
    ArrayList<Transaction> listTransaction = new ArrayList<>();
    double totalPayment;

    Member(int id, String name, LocalDate age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String toString() {
        System.out.println();
        String memberType = passive ? "Aktiv" : "Passivt";
        String exerciser = exercise ? "Ja" : "Nej";
        String konkurrenceSvømmer = competitionSwimmer ? "Ja" : "Nej";
        String juniorSeniorSvømmer = JunSen ? "Juniorsvømmer" : "Seniorsvømmer";
        String harBetalt = hasPaid ? "ja" : "nej";
        System.out.println("ID       Fulde Navn           Alder        Medlemskab     Motionist     konkurrencesvømmer     Junior- eller Seniorsvømmer     Pris     Betalt?");
        return String.format("%-5d    %-17s    %-10s   %-12s   %-10s    %-19s    %-28s    %-1s     %-12s    ",
                id, name, age, memberType, exerciser, konkurrenceSvømmer, juniorSeniorSvømmer, price, harBetalt);
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

    public void setCompetitionSwimmer(boolean competitionSwimmer) {
        this.competitionSwimmer = competitionSwimmer;
    }

    public boolean getCompetitionSwimmer() {
        return competitionSwimmer;
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



    public double getListeResult() {
        if (!rygsvømningListe.isEmpty()){
        return  Collections.min(rygsvømningListe).tid;}
        return Double.MAX_VALUE;
    }

    public boolean getJunSen() {
        return JunSen;
    }

    public void setJunSen(boolean junSen) {
        JunSen = junSen;
    }
    public void setAge(LocalDate age) {
        this.age = age;
    }

    public ArrayList<Transaction> getListTransaction() {
        return listTransaction;
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