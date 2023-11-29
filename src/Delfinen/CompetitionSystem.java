package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CompetitionSystem {
    public ArrayList<Member> juniorTeam = new ArrayList<>();
    public ArrayList<Member> seniorTeam = new ArrayList<>();
    MemberSystem ms = new MemberSystem();
    Scanner scan = new Scanner(System.in);
    Disciplin crawl = new Disciplin("Crawl - 500m", 500);
    Disciplin rygsvømning = new Disciplin("Rygsvømning - 500m", 500);
    Disciplin freestyle = new Disciplin("freestyle - 500m", 500);

    public void addMemberToTeams(){
        for (int i = 0; i < MemberSystem.listMember.size(); i++) {
            Member member = MemberSystem.listMember.get(i);
            System.out.println("Vil du tilføje " + member.name + " til hold? (Ja/Nej)");
            String userInput = scan.nextLine();
            int age = LocalDate.now().getYear() - member.age.getYear();

            if (userInput.equalsIgnoreCase("Ja")) {
                if (juniorTeam.contains(member)) {
                    System.out.println("Der findes allerede en juniorsvømmer" + member);
                    continue;
                }
                if (seniorTeam.contains(member)) {
                    System.out.println("Der findes allerede en seniorsvømmer" + member);
                    continue;
                }
                if (member.getCompetitionSwimmer() && age < 18) {
                    System.out.println(member.name + " er tilføjet juniorholdet.");
                    juniorTeam.add(member);
                } else if (member.getCompetitionSwimmer() && age >= 18) {
                    System.out.println(member.name + " er tilføjet seniorholdet.");
                    seniorTeam.add(member);
                }
            } else if (userInput.equalsIgnoreCase("Nej")) {
                System.out.println("Bliver ikke gjort mere...");
            }
        }

        System.out.println("Junior Team Size: " + juniorTeam.size());
        System.out.println("Senior Team Size: " + seniorTeam.size());
    }
    public void viewJuniorTeamList() {
        System.out.println();
        System.out.println("Ungdomsholdet: ");
        System.out.println();
            for (int i = 0; i < juniorTeam.size(); i++) {
                System.out.println(juniorTeam.get(i));
            }
    }
    public void viewSeniorTeamList() {
        System.out.println();
        System.out.println("Seniorholdet: ");
        System.out.println();
        for (int i = 0; i < seniorTeam.size(); i++) {
            System.out.println(seniorTeam.get(i));
        }
    }
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