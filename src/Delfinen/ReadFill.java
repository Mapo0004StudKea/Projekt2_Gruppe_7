package Delfinen;

import Delfinen.Member;
import Delfinen.MemberSystem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReadFill {

    public static void ReadMyFill() throws IOException {
        FileReader fil = new FileReader("src\\Memberlist.txt");
        BufferedReader ind = new BufferedReader(fil);

        String linje = ind.readLine();
        while (linje != null) {
            String[] bidder = linje.split(",");

            int id = Integer.parseInt(bidder[0].trim());
            String name = bidder[1].trim();
            LocalDate age = LocalDate.parse(bidder[2].trim());
            double tid = Double.parseDouble(bidder[3].trim());
            Member member = new Member(id, name, age);

           int checkAge = LocalDate.now().compareTo(age);
            member.setExercise(false);
            member.setPassive(true);
            member.setCompetitionSwimmer(true);
            if (member.getExercise() == true || member.getCompetitionSwimmer() == true && checkAge >= 18 && checkAge <= 65) {
                member.setPrice(Accounting.exerciseMemberPrice);
            } else if (member.getExercise() == true || member.getCompetitionSwimmer() == true && checkAge < 18) {
                member.setPrice(Accounting.juniorMemberPrice);
                member.setJunSen(true);
            } else if (member.getExercise() == true || member.getCompetitionSwimmer() == true && checkAge > 65) {
                member.setPrice(Accounting.seniorMemberPrice);}

            MemberSystem.listMember.add(member);

            if (MemberSystem.listMember.size() < 21){
                Result re1 = new Result(tid, LocalDate.now());
                member.crawlListe.add(re1);
                re1.setDisciplin(CompetitionSystem.crawl);
            }

            if (MemberSystem.listMember.size() > 21 && MemberSystem.listMember.size() <40){
                Result re1 = new Result(tid, LocalDate.now());
                member.backstrokeListe.add(re1);
                re1.setDisciplin(CompetitionSystem.backstroke);
            }
            if (MemberSystem.listMember.size() > 40){
                Result re1 = new Result(tid, LocalDate.now());
                member.freestyleListe.add(re1);
                re1.setDisciplin(CompetitionSystem.freestyle);
            }
                linje = ind.readLine();

            }
            fil.close();
        }
    }

