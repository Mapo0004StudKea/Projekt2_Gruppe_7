package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Accounting {
    final double exerciseMemberPrice = 1600;
    final double passiveMemberPrice = 500;
    final double seniorMemberPrice = 1200; //over 65
    final double juniorMemberPrice = 1000; //under 18

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

    public void chooseMembership() {

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Vælg medlemskabstype");
            System.out.println("Tast 1 for MotionSvømmer" + "\n" + "tast 2 for passivt medlemskab" + "\n" + "Tast 3 for Konkurrence");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:

                    break;

                case 2:
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid try again. ");
            }
        }
    }

    public static void main(String[] args) {
        Member mss = new Member(12, "Dromedar", LocalDate.of(1998,12,22));
        Accounting.list.add(mss);
        mss.setPassive(true);
        mss.setExercise(false);
        a.seeAllExerciseMembers();


    }


}
