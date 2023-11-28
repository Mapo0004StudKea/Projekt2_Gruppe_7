package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Accounting extends MemberSystem {
    final double exerciseMemberPrice = 1600; //Alm. Medlemskab
    final double passiveMemberPrice = 500; //passiv
    final double seniorMemberPrice = 1200; //over 60
    final double juniorMemberPrice = 1000; //under 18
    Scanner scan = new Scanner(System.in);

    MemberSystem ms = new MemberSystem();
    static Accounting a = new Accounting();
    static ArrayList<Member> list = new ArrayList<>();

    public void seeAllExerciseMembers() {
        for (int i = 0; i<list.size(); i++) { //Henter et medlem fra arrayListen og viser hvis det er en motionist svømmer
            if (list.get(i).getExercise()==true) {
                System.out.println(list.get(i));
            }
        }
    }
    public void viewPrices() {
        System.out.println("Kontingent priser: ");
        System.out.println("Under 18 år      =  "+juniorMemberPrice+" kr.");
        System.out.println("Mellem 18-60 år  =  "+exerciseMemberPrice+" kr.");
        System.out.println("Over 60 år       =  "+seniorMemberPrice+" kr.");
        System.out.println("Passiv medlem    =  "+passiveMemberPrice+" kr.");
        System.out.println("");
    }

}