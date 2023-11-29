package Delfinen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MemberSystem {
    static ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Accounting acc = new Accounting();


    // der er problem med Array listen, id og Array listen plads er ikke den samme.
    public void addMember() {
        System.out.println();
        System.out.println("Opret medlem");
        System.out.println("Indtast fulde navn på medlemmet");
        String name = scanner.nextLine();
        try {
            System.out.println("Indtast fødselsdato. yyyy-mm-dd");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            int makeId = listMember.size();
            Member m1 = new Member(makeId, name, date);
            listMember.add(m1);
            System.out.println("sæt medlemskab");
            m1.setExercise(true);
            int checkAge = LocalDate.now().compareTo(date);
            if (m1.getExercise() == true && checkAge >= 18 && checkAge <= 65) {
                m1.setPrice(Accounting.exerciseMemberPrice);
            } else if (m1.getExercise() == true && checkAge < 18) {
                m1.setPrice(Accounting.juniorMemberPrice);
            } else if (m1.getExercise() == true && checkAge > 65) {
                m1.setPrice(Accounting.seniorMemberPrice);
            }
            System.out.println(m1.getPrice());
            System.out.println("Vil du betale nu eller senere, tryk j eller n for ja/nej.");
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

    // der er problem med Array listen, id og Array listen plads er ikke den samme.

    public void viewMemberList() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getPassive() == true) {
                System.out.println(listMember.get(i));
                System.out.println("medlem er passivt");
            }
            if (listMember.get(i).getExercise() == true) {
                System.out.println(listMember.get(i));
                System.out.println("medlem er motionist");
            }
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

                System.out.println("Indtast nyt fulde navn på medlemmet:");
                String newName = scanner.nextLine();
                member.setName(newName);

                System.out.println("Indtast ny fødselsdato. yyyy-mm-dd:");
                LocalDate newDate = LocalDate.parse(scanner.nextLine());
                member.setAge(newDate);

                System.out.println("Ønsker medlemmet at være passivt? j/n");
                String passive = scanner.nextLine();
                if (passive.equals("j")) {
                    member.setPrice(Accounting.passiveMemberPrice);
                    member.setPassive(true);
                    System.out.println("Medlemskabet er nu passivt");
                    if (passive.equals("j")) {
                        member.setExercise(false);
                    }
                }

                System.out.println("Medlemsoplysninger opdateret!");
                System.out.println(member);
                System.out.println(member.getPrice());
                System.out.println("medlem er nu passiv: " + member.getPassive());
                memberFound = true;
                break;
            }
        }
        if (!memberFound) {
            System.out.println("Medlem ikke fundet med det angivne ID.");
        }
    }

    public void EkstraMember() {
        System.out.println();
        int makeId = listMember.size() + (1);
        Member m1 = new Member(makeId, "Martin Poulsen", LocalDate.of(1960, 2, 21));
        m1.setExercise(true);
        m1.setPassive(false);
        listMember.add(m1);

        int makeId2 = listMember.size() + (1);
        Member m2 = new Member(makeId2, "Lars Poulsen", LocalDate.of(2017, 2, 21));
        m2.setExercise(false);
        m2.setPassive(true);
        listMember.add(m2);

        int makeId3 = listMember.size() + (1);
        Member m3 = new Member(makeId3, "Hej Poulsen", LocalDate.of(1965, 2, 21));
        m3.setExercise(true);
        m3.setPassive(false);
        listMember.add(m3);

        int makeId4 = listMember.size() + (1);
        Member m4 = new Member(makeId4, "Erik Poulsen", LocalDate.of(1997, 2, 21));
        m4.setExercise(false);
        m4.setPassive(true);
        listMember.add(m4);

        int makeId5 = listMember.size() + (1);
        Member m5 = new Member(makeId5, "Godmorgen Poulsen", LocalDate.of(2018, 2, 21));
        m5.setExercise(true);
        m5.setPassive(false);
        listMember.add(m5);
    }

}