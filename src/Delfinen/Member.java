package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Member {
    String name;
    LocalDate age;
    int id;
    boolean competitionSwimmer;
    boolean passive;
    boolean exercise;
    boolean JunSen;
    double price;
    boolean hasPaid =true;
    ArrayList<Result> backstrokeListe = new ArrayList<>();
    ArrayList<ResultTournament> backstrokeListTournament = new ArrayList<>();
    ArrayList<Result> crawlListe = new ArrayList<>();
    ArrayList<ResultTournament> crawlListTournament = new ArrayList<>();
    ArrayList<Result> freestyleListe = new ArrayList<>();
    ArrayList<ResultTournament> freestyleListTournament = new ArrayList<>();
    ArrayList<Transaction> listTransaction = new ArrayList<>();

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
        System.out.println("ID          Fulde Navn           Alder        Medlemskab     Motionist     konkurrencesvømmer     Junior- eller Seniorsvømmer     Pris     Betalt?");
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

    public void setJunSen(boolean junSen) {
        JunSen = junSen;
    }

    public LocalDate getAge() {
        return age;
    }
    public void setAge(LocalDate age) {
        this.age = age;
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
    public boolean getExercise() {
        return exercise;
    }

    public double getListeResultBackstrokeListe() {
        if (!backstrokeListe.isEmpty()){
            return  Collections.min(backstrokeListe).tid;}
        return Double.MAX_VALUE;
    }
    public double getListeResultBackstrokeListeTournament() {
        if (!backstrokeListTournament.isEmpty()){
            return  Collections.min(backstrokeListTournament).tid;}
        return Double.MAX_VALUE;
    }

    public double getListeResultCralwList() {
        if (!crawlListe.isEmpty()){
            return  Collections.min(crawlListe).tid;}
        return Double.MAX_VALUE;
    }
    public double getListeResultCrawlListeTournament() {
        if (!crawlListTournament.isEmpty()){
            return  Collections.min(crawlListTournament).tid;}
        return Double.MAX_VALUE;
    }

    public double getListeResultfreestyleListe() {
        if (!freestyleListe.isEmpty()){
            return  Collections.min(freestyleListe).tid;}
        return Double.MAX_VALUE;
    }
    public double getListeResultFreestylelListeTournament() {
        if (!freestyleListTournament.isEmpty()){
            return  Collections.min(freestyleListTournament).tid;}
        return Double.MAX_VALUE;
    }

    void MemberShipPayment(double amount) {
        double remainingBalance = price - amount;
        listTransaction.add(new Transaction("indbetalt", amount, remainingBalance));
        price = remainingBalance;
    }

    void printTransaction(){
        System.out.println(this);
        System.out.println("text"+"\t"+"dato"+"\t"+"indbetalt");
        for ( Transaction t : listTransaction){
            System.out.println(t);
        }
        System.out.println();
    }
}