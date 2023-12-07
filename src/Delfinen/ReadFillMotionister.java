package Delfinen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class ReadFillMotionister {

    public static void ReadMyFillMotionister() throws IOException {
        FileReader fil = new FileReader("src\\motionister.txt");
        BufferedReader ind = new BufferedReader(fil);

        String linje = ind.readLine();
        while (linje != null) {
            String[] bidder = linje.split(",");

            int id = Integer.parseInt(bidder[0].trim());
            String name = bidder[1].trim();
            LocalDate age = LocalDate.parse(bidder[2].trim());

            Member member = new Member(id, name, age);

            int checkAge = LocalDate.now().compareTo(age);
            member.setExercise(true);
            member.setPassive(true);
            member.setCompetitionSwimmer(false);
            if ( checkAge >= 18 && checkAge <= 65 ) {
                member.setPrice(Accounting.exerciseMemberPrice);
            }
            if ( checkAge < 18 ) {
                member.setPrice(Accounting.juniorMemberPrice);
                member.setJunSen(true);
            }
            if (checkAge > 65) {
                member.setPrice(Accounting.seniorMemberPrice);}
            MemberSystem.listMember.add(member);
            linje = ind.readLine();
        }
        ind.close();
        fil.close();

        for (Member currentMember : MemberSystem.listMember) {
            if (currentMember.getExercise()) {
                currentMember.MemberShipPayment(currentMember.getPrice());
                currentMember.setHasPaid(true);
                System.out.println("kontingent er betalt");
                System.out.println(currentMember);
            }
        }
    }
}