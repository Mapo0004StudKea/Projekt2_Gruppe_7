package Delfinen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class ReadFileTournament {

    public static void ReadMyFillTour() throws IOException {
        FileReader fil = new FileReader("Tournament.txt");
        BufferedReader ind = new BufferedReader(fil);

        String linje = ind.readLine();
        while (linje != null) {
            String[] bidder = linje.split(",");

            int id = Integer.parseInt(bidder[0].trim());
            String name = bidder[1].trim();
            LocalDate age = LocalDate.parse(bidder[2].trim());
            double time = Double.parseDouble(bidder [3].trim());
            String tournamentName = bidder[4].trim();
            int placement = Integer.parseInt(bidder[5].trim());

            Member member = new Member(id, name, age);


            int checkAge = LocalDate.now().compareTo(age);
            member.setExercise(false);
            member.setPassive(false);
            member.setCompetitionSwimmer(true);
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
            if (MemberSystem.listMember.size() <10) {
                ResultTournament rt = new ResultTournament(time, LocalDate.now(), tournamentName, placement);
                member.backstrokeListTournament.add(rt);
                rt.setDisciplin(CompetitionSystem.backstroke);
                rt.setCheckTournament(true);
                System.out.println(member);
                System.out.println(rt);
            }

            if (MemberSystem.listMember.size()>10 && MemberSystem.listMember.size()>20) {
                ResultTournament rt = new ResultTournament(time, LocalDate.now(), tournamentName, placement);
                member.crawlListTournament.add(rt);
                rt.setDisciplin(CompetitionSystem.crawl);
                rt.setCheckTournament(true);
                System.out.println(member);
                System.out.println(rt);
            }

            if (MemberSystem.listMember.size()>20 && MemberSystem.listMember.size()<30) {
                ResultTournament rt = new ResultTournament(time, LocalDate.now(), tournamentName, placement);
                member.freestyleListTournament.add(rt);
                rt.setDisciplin(CompetitionSystem.freestyle);
                rt.setCheckTournament(true);
                System.out.println(member);
                System.out.println(rt);
            }


            linje = ind.readLine();
        }
        ind.close();
        fil.close();

        for (Member currentMember : MemberSystem.listMember) {
            if (currentMember.getCompetitionSwimmer()) {
                currentMember.MemberShipPayment(currentMember.getPrice());
                currentMember.setHasPaid(true);
            }
        }
    }
}

