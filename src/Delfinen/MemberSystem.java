package Delfinen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberSystem {
    ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addMember() {
        System.out.println("Opret medlem");
        System.out.println("Indtast fulde navn på medlemmet");
        String name = scanner.nextLine();
        try {
            System.out.println("Indtast fødselsdato. yyyy-mm-dd");
            LocalDate date = LocalDate.parse(scanner.nextLine());

        int makeId = 0;
            makeId = listMember.size() + 1;
        Member m1= new Member(makeId,name, date);
        listMember.add(m1);
            System.out.println("Du har oprettet et ny medlem");
    }catch (DateTimeParseException d){
            System.out.println("Du har trykket forkert husk! YYYY-MM-DD");
            addMember();
        }
    }

    public void viewMemberList() {
        for (int i = 0; i < listMember.size() ; i++) {
            System.out.println(listMember.get(i));
        }
    }
    public void deleteMember() {
        System.out.println("Indtast medlemmets ID for at slette:");
        int memberIdToDelete = scanner.nextInt();

        boolean memberFound = false;
        for (Member member : listMember) {
            if (member.getId() == memberIdToDelete) {
                listMember.remove(member);
                memberFound = true;
                System.out.println("Medlem slettet!");
            }
        }

        if (!memberFound) {
            System.out.println("Medlem ikke fundet med det angivne ID.");
        }
    }

    /*public void deleteMembers(int index) {     //chatten hjalp til her.
        if (index>=0 && index<listMember.size()) {
            listMember.remove(index);
        } else
            System.out.println("invalid, try again.");
    }*/
    public void EkstraMember() {

        int makeId = listMember.size() + (1);
        Member m1 = new Member(makeId, "Martin Poulsen", LocalDate.of(1997, 2, 21));
        listMember.add(m1);

        int makeId2 = listMember.size() + (1);
        Member m2 = new Member(makeId2, "Lars Poulsen", LocalDate.of(1997, 02, 21));
        listMember.add(m2);

        int makeId3 = listMember.size() + (1);
        Member m3 = new Member(makeId3, "Hej Poulsen", LocalDate.of(1997, 02, 21));
        listMember.add(m3);

        int makeId4 = listMember.size() + (1);
        Member m4 = new Member(makeId4, "Erik Poulsen", LocalDate.of(1997, 02, 21));
        listMember.add(m4);

        int makeId5 = listMember.size() + (1);
        Member m5 = new Member(makeId5, "Godmorgen Poulsen", LocalDate.of(1997, 02, 21));
        listMember.add(m5);
    }

}
