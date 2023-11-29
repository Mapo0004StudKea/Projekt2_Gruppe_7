package Delfinen;

import java.time.LocalDate;
import java.util.Scanner;

public class CompetitionSystem {
    MemberSystem ms = new MemberSystem();
    Scanner scan = new Scanner(System.in);
    Disciplin crawl = new Disciplin("Crawl - 500m", 500);
    Disciplin rygsvømning = new Disciplin("Rygsvømning - 500m", 500);
    Disciplin freestyle = new Disciplin("freestyle - 500m", 500);

    public void setNewResult() {
        System.out.println();

        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();
        System.out.println("vælg en et medlem");
        int choice = scan.nextInt();

        System.out.println("set ny resultat, skriv tid");
        double tid = scan.nextDouble();

        Result re = new Result(tid, LocalDate.now(), rygsvømning);

        System.out.println("vægle en disciplin: ");
        System.out.println("1. for at vægle Rygsvømning - 500m");
        System.out.println("2. for at vægle Crawl - 500m");
        System.out.println("3. for at vægle freestyle - 500m");
        int menuChoice = scan.nextInt();
    

        switch (menuChoice) {
            case 1:
                re.setDisplin(rygsvømning);
                break;
            case 2:
                re.setDisplin(crawl);
                break;
            case 3:
                re.setDisplin(freestyle);
                break;
        }


        MemberSystem.listMember.get(choice).listeResult.add(re);
        System.out.println(MemberSystem.listMember.get(choice));

        for (int i = 0; i < MemberSystem.listMember.get(choice).listeResult.size(); i++) {
            System.out.println(MemberSystem.listMember.get(choice).listeResult.get(i));
        }
    }

}