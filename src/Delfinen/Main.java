package Delfinen;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        MemberSystem ms = new MemberSystem();

       /* Member m1 = new Member(1, "Martin Poulsen", LocalDate.of(1997,2,21));
        Member m2 = new Member(2, "Lars Poulsen", LocalDate.of(1997,02, 21));
        Member m3 = new Member(3, "Hej Poulsen", LocalDate.of(1997,02, 21));
        Member m4 = new Member(4, "Erik Poulsen", LocalDate.of(1997,02, 21));
        Member m5 = new Member(5, "Godmorgen Poulsen", LocalDate.of(1997,02, 21));
        ms.listMember.add(m1);
        ms.listMember.add(m2);
        ms.listMember.add(m3);
        ms.listMember.add(m4);
        ms.listMember.add(m5);*/

        Menu newMenu = new Menu();
        Member m = new Member(222, "viktor", LocalDate.of(1997, 2, 21));
        System.out.println(m.getId());
        System.out.println(m);
        m.setPassive(true);
        newMenu.hovedMenu();

    }
}
