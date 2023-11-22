package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberSystem {
    ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addMember() {
        System.out.println("Opret medlem");
        System.out.println("Indtast fulde navn på medlemmet");
        String name = scanner.nextLine();
        System.out.println("Indtast fødselsdato. yyyy-mm-dd");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        int makeId = 0;
        for (int i = 1; i<listMember.size(); i++) {
            makeId = listMember.size() + (1);
        }
        Member m1= new Member(makeId,name, date);
        listMember.add(m1);
    }

    public void watchMembers() {
        for (int i = 0; i < listMember.size() ; i++) {
            System.out.println(listMember.get(i));
        }

    }

    /*public void deleteMembers(int index) {     //chatten hjalp til her.
        if (index>=0 && index<listMember.size()) {
            listMember.remove(index);
        } else
            System.out.println("invalid, try again.");
    }*/
    public void EkstraMember() {
        Member m1 = new Member(1, "Martin Poulsen", LocalDate.of(1997, 2, 21));
        Member m2 = new Member(2, "Lars Poulsen", LocalDate.of(1997, 02, 21));
        Member m3 = new Member(3, "Hej Poulsen", LocalDate.of(1997, 02, 21));
        Member m4 = new Member(4, "Erik Poulsen", LocalDate.of(1997, 02, 21));
        Member m5 = new Member(5, "Godmorgen Poulsen", LocalDate.of(1997, 02, 21));
        listMember.add(m1);
        listMember.add(m2);
        listMember.add(m3);
        listMember.add(m4);
        listMember.add(m5);
    }

}
