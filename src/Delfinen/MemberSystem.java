package Delfinen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MemberSystem {
    Accounting acc = new Accounting();
    public static ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // der er problem med Array listen, id og Array listen plads er ikke den samme.
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

            System.out.println(m1.getPrice());
            System.out.println("Vil du betale nu eller senere, tryk j eller n for ja/nej.");
            String scan = scanner.nextLine(); //Hvorfor er det nødvendigt med denne scanner. Hvis vi kke har den, ryger den ud af conditions inden vi kan trykke "betal nu".
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
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getHasPaid() == true) {
                System.out.println(listMember.get(i));
            }
        }
    }

    public void seeMemberPassive() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) { //Henter et medlem fra arrayListen og viser hvis det er en motionist svømmer
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
        System.out.println();
        System.out.println("Indtast medlemmets ID for at slette:");
        int memberIdToDelete = scanner.nextInt();

        Iterator<Member> iterator = listMember.iterator(); // hvordan virker den her metode.
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
    }

    // der skal laves en rettelse så hvis man ændre sit fødsels år skal man ikke betale så meget.
    public void editMember() {
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
                        System.out.println("Ønsker medlemmet at sin medlem status j/n");
                        String passiv = scanner.nextLine();
                        if (passiv.equals("j")) {
                            member.setPrice(Accounting.passiveMemberPrice);
                            member.setPassive(true);
                            System.out.println("Medlemskabet er nu passivt");
                            if (passiv.equals("n")) {
                                member.setExercise(false);
                                member.setCompetitionSwimmer(false);
                            }
                        }
                        break;

                    case 2:
                        scanner.nextLine();
                        System.out.println("Ønsker medlemmet at ændre sin medlem status j/n");
                        String competition = scanner.nextLine();

                        if (competition.equals("j")) {
                            if (checkAge < 18){member.setPrice(Accounting.juniorMemberPrice);}
                            if (checkAge >=65){member.setPrice(Accounting.seniorMemberPrice);}
                            else member.setPrice(Accounting.exerciseMemberPrice);
                            member.setCompetitionSwimmer(true);
                            System.out.println("Medlemskabet er nu passivt");
                            if (competition.equals("n")) {
                                member.setPassive(false);
                                member.setExercise(false);
                            }
                        }
                        break;

                    case 3:
                        scanner.nextLine();
                        System.out.println("Ønsker medlemmet at sin medlem status j/n");
                        String exerciser = scanner.nextLine();

                        if (exerciser.equals("j")) {
                            if (checkAge < 18){member.setPrice(Accounting.juniorMemberPrice);}
                            if (checkAge >=65){member.setPrice(Accounting.seniorMemberPrice);}
                            else member.setPrice(Accounting.exerciseMemberPrice);
                            member.setExercise(true);
                            System.out.println("Medlemskabet er nu passivt");
                            if (exerciser.equals("n")) {
                                member.setPassive(false);
                                member.setCompetitionSwimmer(false);
                            }
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

  /*  public void EkstraMember() {
        System.out.println();
        int makeId = listMember.size() + (1);
        Member m1 = new Member(makeId, "Martin Poulsen", LocalDate.of(2015, 2, 21));
        m1.setPassive(true);
        m1.setExercise(false);
        m1.setCompetitionSwimmer(true);
        m1.setJunSen(false);
        m1.setPrice(500);
        m1.setHasPaid(true);
        listMember.add(m1);
        Result re = new Result(2, LocalDate.now());
        Result re1 = new Result(1, LocalDate.now());
        re.setDisciplin(CompetitionSystem.backstroke);
        re1.setDisciplin(CompetitionSystem.backstroke);
        m1.backstrokeListe.add(re);
        m1.backstrokeListe.add(re1);
        Result re15 = new Result(10, LocalDate.now());
        re15.setDisciplin(CompetitionSystem.crawl);


        int makeId2 = listMember.size() + (1);
        Member m2 = new Member(makeId2, "Lars Poulsen", LocalDate.of(2017, 2, 21));
        m2.setPassive(true);
        m2.setExercise(false);
        m2.setCompetitionSwimmer(true);
        m2.setJunSen(true);
        m2.setPrice(1000);
        m2.setHasPaid(false);
        listMember.add(m2);
        Result re2 = new Result(6, LocalDate.now());
        Result re3 = new Result(3, LocalDate.now());
        re2.setDisciplin(CompetitionSystem.backstroke);
        re3.setDisciplin(CompetitionSystem.backstroke);
        m2.backstrokeListe.add(re2);
        m2.backstrokeListe.add(re3);

        int makeId3 = listMember.size() + (1);
        Member m3 = new Member(makeId3, "Hej Poulsen", LocalDate.of(2015, 2, 21));
        m3.setPassive(true);
        m3.setExercise(false);
        m3.setCompetitionSwimmer(true);
        m3.setJunSen(false);
        m3.setPrice(1600);
        m3.setHasPaid(true);
        listMember.add(m3);
        Result re4 = new Result(8, LocalDate.now());
        Result re5 = new Result(1.2, LocalDate.now());
        re4.setDisciplin(CompetitionSystem.backstroke);
        re5.setDisciplin(CompetitionSystem.backstroke);
        m3.backstrokeListe.add(re4);
        m3.backstrokeListe.add(re5);

        int makeId4 = listMember.size() + (1);
        Member m4 = new Member(makeId4, "Erik Poulsen", LocalDate.of(2020, 2, 21));
        m4.setPassive(true);
        m4.setExercise(false);
        m4.setCompetitionSwimmer(true);
        m4.setJunSen(false);
        m4.setPrice(1600);
        m4.setHasPaid(true);
        listMember.add(m4);
        Result re6 = new Result(6.5, LocalDate.now());
        Result re7 = new Result(3.1, LocalDate.now());
        re6.setDisciplin(CompetitionSystem.backstroke);
        re7.setDisciplin(CompetitionSystem.backstroke);
        m4.backstrokeListe.add(re6);
        m4.backstrokeListe.add(re7);
        Result re14 = new Result(3, LocalDate.now());
        re14.setDisciplin(CompetitionSystem.crawl);
        m4.listeResult.add(re14);

        int makeId5 = listMember.size() + (1);
        Member m5 = new Member(makeId5, "Godmorgen Poulsen", LocalDate.of(2018, 2, 21));
        m5.setPassive(true);
        m5.setExercise(false);
        m5.setCompetitionSwimmer(true);
        m5.setJunSen(true);
        m5.setPrice(1000);
        m5.setHasPaid(false);
        listMember.add(m5);
        Result re8 = new Result(4, LocalDate.now());
        Result re9 = new Result(7, LocalDate.now());
        re8.setDisciplin(CompetitionSystem.backstroke);
        re9.setDisciplin(CompetitionSystem.backstroke);
        m5.backstrokeListe.add(re8);
        m5.backstrokeListe.add(re9);
        Result re13 = new Result(2.7, LocalDate.now());
        re13.setDisciplin(CompetitionSystem.crawl);
        m5.listeResult.add(re13);

        int makeId6 = listMember.size() + (1);
        Member m6 = new Member(makeId6, "Godmorgen Poulsen 2", LocalDate.of(2018, 2, 21));
        m6.setPassive(true);
        m6.setExercise(false);
        m6.setCompetitionSwimmer(true);
        m6.setJunSen(true);
        m6.setPrice(1000);
        m6.setHasPaid(false);
        listMember.add(m6);
        Result re10 = new Result(4, LocalDate.now());
        re10.setDisciplin(CompetitionSystem.backstroke);
        Result re11 = new Result(2.9, LocalDate.now());
        re11.setDisciplin(CompetitionSystem.backstroke);
        m6.backstrokeListe.add(re11);
        m6.backstrokeListe.add(re10);
        Result re12 = new Result(2.6, LocalDate.now());
        re12.setDisciplin(CompetitionSystem.crawl);
        m6.listeResult.add(re12);

        int makeId7 = listMember.size() + 1;
        Member m7 = new Member(makeId7, "Alice Johnson", LocalDate.of(1990, 5, 10));

// Set the desired characteristics for the new member
        m7.setPassive(true);
        m7.setExercise(true);
        m7.setCompetitionSwimmer(false);
        m7.setJunSen(true);
        m7.setPrice(800);
        m7.setHasPaid(true);

// Add result times for backstroke
        Result re16 = new Result(5, LocalDate.now());
        Result re17 = new Result(2.5, LocalDate.now());
        re16.setDisciplin(CompetitionSystem.backstroke);
        re17.setDisciplin(CompetitionSystem.backstroke);
        m7.backstrokeListe.add(re16);
        m7.backstrokeListe.add(re17);

// Add result times for crawl
        Result re18 = new Result(3.8, LocalDate.now());
        re18.setDisciplin(CompetitionSystem.crawl);
        m7.listeResult.add(re18);

// Add the new member to the list
        listMember.add(m7);

        for (int i = 0; i < 10; i++) {
            int memberId = listMember.size() + 1;
            Member member = new Member(memberId, "Member " + (i + 1), LocalDate.of(1990 - i, 1, 1));

            // Set random characteristics for the member
            member.setPassive(true);
            member.setExercise(false);
            member.setCompetitionSwimmer(true);
            member.setJunSen(Math.random() < 0.5);
            member.setPrice(500 + i * 100);
            member.setHasPaid(Math.random() < 0.8); // 80% chance of having paid

            // Add random result times for backstroke
            Result backstrokeResult1 = new Result(Math.random() * 20, LocalDate.now());
            Result backstrokeResult2 = new Result(Math.random() * 20, LocalDate.now());
            backstrokeResult1.setDisciplin(CompetitionSystem.backstroke);
            backstrokeResult2.setDisciplin(CompetitionSystem.backstroke);
            member.backstrokeListe.add(backstrokeResult1);
            member.backstrokeListe.add(backstrokeResult2);

            // Add random result times for crawl
            Result crawlResult = new Result(Math.random() * 20, LocalDate.now());
            crawlResult.setDisciplin(CompetitionSystem.backstroke);
            member.backstrokeListe.add(crawlResult);

            // Add the new member to the list
            listMember.add(member);
        }
        for (int i = 0; i < 10; i++) {
            int memberId = listMember.size() + 1;
            Member member = new Member(memberId, "Member " + (i + 1), LocalDate.of(1990 - i, 1, 1));

            // Set random characteristics for the member
            member.setPassive(true);
            member.setExercise(false);
            member.setCompetitionSwimmer(true);
            member.setJunSen(Math.random() < 0.5);
            member.setPrice(500 + i * 100);
            member.setHasPaid(Math.random() < 0.8); // 80% chance of having paid

            // Add random result times for backstroke
            Result backstrokeResult1 = new Result(Math.random() * 20, LocalDate.now());
            Result backstrokeResult2 = new Result(Math.random() * 20, LocalDate.now());
            backstrokeResult1.setDisciplin(CompetitionSystem.crawl);
            backstrokeResult2.setDisciplin(CompetitionSystem.crawl);
            member.crawlListe.add(backstrokeResult1);
            member.crawlListe.add(backstrokeResult2);

            // Add random result times for crawl
            Result crawlResult = new Result(Math.random() * 20, LocalDate.now());
            crawlResult.setDisciplin(CompetitionSystem.crawl);
            member.crawlListe.add(crawlResult);

            // Add the new member to the list
            listMember.add(member);
        }

        for (int i = 0; i < 10; i++) {
            int memberId = listMember.size() + 1;
            Member member = new Member(memberId, "Member " + (i + 1), LocalDate.of(1990 - i, 1, 1));

            // Set random characteristics for the member
            member.setPassive(true);
            member.setExercise(false);
            member.setCompetitionSwimmer(true);
            member.setJunSen(Math.random() < 0.5);
            member.setPrice(500 + i * 100);
            member.setHasPaid(Math.random() < 0.8); // 80% chance of having paid

            // Add random result times for backstroke
            Result backstrokeResult1 = new Result(Math.random() * 20, LocalDate.now());
            Result backstrokeResult2 = new Result(Math.random() * 20, LocalDate.now());
            backstrokeResult1.setDisciplin(CompetitionSystem.freestyle);
            backstrokeResult2.setDisciplin(CompetitionSystem.freestyle);
            member.freestyleListe.add(backstrokeResult1);
            member.freestyleListe.add(backstrokeResult2);

            // Add random result times for crawl
            Result crawlResult = new Result(Math.random() * 20, LocalDate.now());
            crawlResult.setDisciplin(CompetitionSystem.freestyle);
            member.freestyleListe.add(crawlResult);

            // Add the new member to the list
            listMember.add(member);
        }
    }*/
}