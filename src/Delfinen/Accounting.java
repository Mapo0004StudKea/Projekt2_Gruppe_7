package Delfinen;

import java.util.Scanner;

public class Accounting {
    static final double exerciseMemberPrice = 1600;
    static final double passiveMemberPrice = 500;
    static final double seniorMemberPrice = 1200;
    static final double juniorMemberPrice = 1000;
    Scanner scan = new Scanner(System.in);

    public void viewPrices() {
        System.out.println("Kontingent priser: ");
        System.out.println("Under 18 år      =  " + juniorMemberPrice + " kr.");
        System.out.println("Mellem 18-60 år  =  " + exerciseMemberPrice + " kr.");
        System.out.println("Over 60 år       =  " + seniorMemberPrice + " kr.");
        System.out.println("Passiv medlem    =  " + passiveMemberPrice + " kr.");
    }

    public void newTransaction() {
        System.out.println();
        System.out.println("vælg ID nr. på medlemmet");
        int choice = scan.nextInt();
        for (Member m : MemberSystem.listMember) {
            if (choice == m.getId()) {
                m.MemberShipPayment(m.getPrice());
                m.setHasPaid(true);
                System.out.println("kontingent er betalt");
            }
        }
    }

    public void listOfPayment() {
        double total = 0;
        for (int i = 0; i < MemberSystem.listMember.size(); i++) {
            if (MemberSystem.listMember.get(i).hasPaid) {
                if (MemberSystem.listMember.get(i).hasPaid && !MemberSystem.listMember.get(i).listTransaction.isEmpty()) {
                    total += MemberSystem.listMember.get(i).listTransaction.get(0).amount;
                }
            }
        }
        System.out.println(total);
    }
}