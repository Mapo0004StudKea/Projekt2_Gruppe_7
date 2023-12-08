package Delfinen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class MemberSystem {
    Accounting acc = new Accounting();
    public static ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addMember() {
        System.out.println();
        System.out.println("Opret medlem");
        System.out.println("Indtast fulde navn på medlemmet");
        String name = scanner.nextLine();
        try {
            System.out.println("Indtast fødselsdato. yyyy-mm-dd");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.println("indtast telefon nummer: ");
            int makeId = scanner.nextInt();
            scanner.nextLine();
            Member m1 = new Member(makeId, name, date);
            listMember.add(m1);
            System.out.println("sæt medlemskab");
            System.out.println(m1);
            chooseMembership();
            int checkAge = LocalDate.now().compareTo(date);
            if (m1.getExercise() == true || m1.getCompetitionSwimmer() == true && checkAge >= 18 && checkAge <= 65) {
                m1.setPrice(Accounting.exerciseMemberPrice);
            } else if (m1.getExercise() == true || m1.getCompetitionSwimmer() == true   && checkAge < 18) {
                m1.setPrice(Accounting.juniorMemberPrice);
            } else if (m1.getExercise() == true || m1.getCompetitionSwimmer() == true && checkAge > 65) {
                m1.setPrice(Accounting.seniorMemberPrice);
            }
            if (m1.getCompetitionSwimmer() == true) {
                if (checkAge < 18) {
                    CompetitionSystem.juniorTeam.add(m1);
                }
            }
                if (m1.getCompetitionSwimmer() == true) {
                    if (checkAge > 18) {
                        CompetitionSystem.seniorTeam.add(m1);
                    }
                }

            System.out.println(m1.getPrice());
            System.out.println("Vil du betale nu eller senere, tryk j eller n for ja/nej.");
            System.out.println(m1);
            String payNow = scanner.nextLine();
            if (payNow.equals("j")) {
                acc.newTransaction();
            } else {
                m1.setHasPaid(false);
            }
            System.out.println("Du har oprettet et ny medlem");

        } catch (DateTimeParseException d) {
            System.out.println("Du har trykket forkert husk! YYYY-MM-DD");
            addMember();
        }
    }

    public void seeIfMemberHadPaid() {
        try {

            System.out.println();
            for (int i = 0; i < listMember.size(); i++) {
                if (listMember.get(i).getHasPaid() == true) {
                    System.out.println(listMember.get(i));
                }
            }

        }catch(InputMismatchException e){
            System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
            scanner.nextLine();
        }
    }

    public void seeMemberPassive() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getPassive() == true) {
                System.out.println(listMember.get(i));

            }
        }
    }

    public void viewMemberList() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) {
            System.out.println(listMember.get(i));
        }
    }

    public void deleteMember() {
        try {
            System.out.println();
            System.out.println("Indtast medlemmets ID for at slette:");
            int memberIdToDelete = scanner.nextInt();

            Iterator<Member> iterator = listMember.iterator();
            boolean memberFound = false;

            while (iterator.hasNext()) {
                Member member = iterator.next();
                if (member.getId() == memberIdToDelete) {
                    iterator.remove();
                    memberFound = true;
                    System.out.println("Medlem slettet!");
                    break;
                }
            }
            if (!memberFound) {
                System.out.println("Medlem ikke fundet med det angivne ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
            scanner.nextLine();
        }
    }

    public void editMember() {
        try {
        System.out.println();
        if (listMember.isEmpty()) {
            System.out.println("Ingen medlemmer at redigere.");
            return;
        }

        System.out.println("Liste over medlemmer:");
        viewMemberList();

        System.out.println("Indtast medlemmets ID for at redigere:");
        int memberIdToEdit = scanner.nextInt();
        scanner.nextLine();

        Iterator<Member> iterator = listMember.iterator();
        boolean memberFound = false;

        while (iterator.hasNext()) {
            Member member = iterator.next();
            if (member.getId() == memberIdToEdit) {
                System.out.println("Nuværende medlemsoplysninger: " + member);

                System.out.println("Ønsker du at ændre navn på medlemmet? j/n");
                String newName2 = scanner.nextLine();
                if(newName2.equals("j")){
                    System.out.println("Indtast nyt fulde navn på medlemmet:");
                    String newName = scanner.nextLine();
                    member.setName(newName);}

                System.out.println("Ønsker du at ændre fødselsdag? j/n");
                String newBDay = scanner.nextLine();
                if(newBDay.equals("j")){
                    System.out.println("Indtast ny fødselsdato. yyyy-mm-dd:");
                    LocalDate newDate = LocalDate.parse(scanner.nextLine());
                    member.setAge(newDate);}

                System.out.println("1. for at ændre til passivt medlem");
                System.out.println("2. for at ændre til konkurrence medlem");
                System.out.println("3. for at ændre til motionist medlem");
                int checkAge = LocalDate.now().compareTo(member.getAge());
                int chose = scanner.nextInt();

                switch (chose) {
                    case 1:
                        scanner.nextLine();
                        System.out.println("Ønsker medlemmet at sin medlem status er passiv? j/n");
                        String passiv = scanner.nextLine();
                        if (passiv.equals("j")) {
                            member.setPrice(Accounting.passiveMemberPrice);
                            member.setPassive(true);
                            member.setExercise(false);
                            member.setCompetitionSwimmer(false);
                            System.out.println("Medlemskabet er nu passivt");

                            System.out.println("det bliver så " + Accounting.passiveMemberPrice);
                            acc.newTransaction();
                            if (passiv.equals("n"));
                        }
                        break;

                    case 2:
                        scanner.nextLine();
                        System.out.println("Ønsker medlemmet at ændre sin medlem status er sat til kunkurrance svømmer? j/n");
                        String competition = scanner.nextLine();

                        if (competition.equals("j")) {
                            if (checkAge < 18){member.setPrice(Accounting.juniorMemberPrice);}
                            if (checkAge >=65){member.setPrice(Accounting.seniorMemberPrice);}
                            else member.setPrice(Accounting.exerciseMemberPrice);
                            member.setCompetitionSwimmer(true);
                            member.setPassive(false);
                            member.setExercise(false);
                            System.out.println("Medlemskabet er nu kunkurrance svømmer");

                            if (checkAge < 18){System.out.println("det bliver så " + Accounting.juniorMemberPrice);}
                            if (checkAge >=65){System.out.println("det bliver så " + Accounting.seniorMemberPrice);}
                            if ( checkAge >= 18 && checkAge <= 65 ) {
                                member.setPrice(Accounting.exerciseMemberPrice);
                            }
                            acc.newTransaction();
                            if (competition.equals("n"));
                        }
                        break;

                    case 3:
                        scanner.nextLine();
                        System.out.println("Ønsker medlemmet at sin medlem status motionist j/n");
                        String exerciser = scanner.nextLine();

                        if (exerciser.equals("j")) {
                            if (checkAge < 18){member.setPrice(Accounting.juniorMemberPrice);}
                            if (checkAge >=65){member.setPrice(Accounting.seniorMemberPrice);}
                            else member.setPrice(Accounting.exerciseMemberPrice);
                            member.setExercise(true);
                            member.setPassive(false);
                            member.setCompetitionSwimmer(false);
                            System.out.println("Medlemskabet er nu Motionist");
                            if (checkAge < 18){System.out.println("det bliver så " + Accounting.juniorMemberPrice);}
                            if (checkAge >=65){System.out.println("det bliver så " + Accounting.seniorMemberPrice);}
                            if ( checkAge >= 18 && checkAge <= 65 ) {
                                member.setPrice(Accounting.exerciseMemberPrice);
                            }
                            acc.newTransaction();
                            if (exerciser.equals("n"));
                        }
                        break;
                }

                System.out.println("Medlemsoplysninger opdateret!");
                System.out.println(member);
                System.out.println(member.getPrice());
                System.out.println("medlem passive status er: " + member.getPassive());
                System.out.println("medlem konkurrence status er: " + member.getCompetitionSwimmer());
                System.out.println("medlem motion status er: " + member.getExercise());
                memberFound = true;
                break;
            }
        }
        if (!memberFound) {
            System.out.println("Medlem ikke fundet med det angivne ID.");
        }
        }   catch (InputMismatchException e) {
            System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
            scanner.nextLine();
        }

    }

    public Member chooseMembership() {
        System.out.println("Choose membership");
        System.out.println("Tast m for Motionist medlemskab");
        System.out.println("Tast k for Konkurrence medlemskab");
        String medlemskab = scanner.nextLine();

        if (medlemskab.equals("m")) {
            System.out.println("Indtast ID nummer");
            int choice = scanner.nextInt();
            for (Member m : listMember) {
                if (choice == m.getId()) {
                    m.setExercise(true);
                    m.setPassive(false);
                    m.setCompetitionSwimmer(false);
                    return m;
                }
            }
        }
        if (medlemskab.equals("k")) {
            System.out.println("Indtast ID nummer");
            int choice = scanner.nextInt();
            for (Member m : listMember) {
                if (choice == m.getId()) {
                    m.setCompetitionSwimmer(true);
                    m.setExercise(false);
                    m.setPassive(false);
                    return m;
                }
            }
        }
        return null;
    }
}