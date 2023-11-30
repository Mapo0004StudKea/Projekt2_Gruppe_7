package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CompetitionSystem {
    public ArrayList<Member> juniorTeam = new ArrayList<>();
    public ArrayList<Member> seniorTeam = new ArrayList<>();
    MemberSystem ms = new MemberSystem();
    Scanner scan = new Scanner(System.in);
    Disciplin crawl = new Disciplin("Crawl - 500m", 500);
    Disciplin rygsvømning = new Disciplin("Rygsvømning - 500m", 500);
    Disciplin freestyle = new Disciplin("freestyle - 500m", 500);



    public void addMemberToTeams() {
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

    public void removeMemberFromTeams() {
        System.out.println("Vælg hold (Junior/Senior) for at fjerne et medlem:");
        String teamChoice = scan.nextLine();
        ArrayList<Member> selectedTeam;
        if (teamChoice.equalsIgnoreCase("Junior")) {
            selectedTeam = juniorTeam;
        } else if (teamChoice.equalsIgnoreCase("Senior")) {
            selectedTeam = seniorTeam;
        } else {
            System.out.println("Ugyldigt valg. Vælg enten Junior eller Senior.");
            return;
        }
        System.out.println("Liste over medlemmer på " + teamChoice + "holdet:");
        displayTeamMembers(selectedTeam);
        System.out.println("Indtast ID på medlemmet, som du vil fjerne:");
        int memberIdToRemove = scan.nextInt();
        scan.nextLine();
        boolean removed = selectedTeam.removeIf(member -> member.getId() == memberIdToRemove);
        if (removed) {
            System.out.println("Medlemmet er fjernet fra " + teamChoice + "holdet.");
        } else {
            System.out.println("Medlemmet med ID " + memberIdToRemove + " blev ikke fundet på " + teamChoice + "holdet.");
        }
    }

    private void displayTeamMembers(ArrayList<Member> team) {
        for (Member member : team) {
            System.out.println("ID: " + member.getId() + ", Navn: " + member.getName());
        }
    }


    public void viewJuniorTeamList() {
        System.out.println();
        System.out.println("Ungdomsholdet: ");
        for (int i = 0; i < juniorTeam.size(); i++) {
            System.out.println(juniorTeam.get(i));
        }
    }

    public void listOfResultJunior() {
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResult()));

        for (Member member : juniorTeam) {
            System.out.println(member);

            System.out.println(member.listeResult);
        }

    }

    public void viewSeniorTeamList() {
        System.out.println();
        System.out.println("Seniorholdet: ");
        for (int i = 0; i < seniorTeam.size(); i++) {
            System.out.println(seniorTeam.get(i));
        }
    }

    public void setNewResult() {
        System.out.println();

        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();

        System.out.println("vælg ID nr. på medlemmet");
        // hvis du skriver forkert kan du ikke komme ind i metoden igen.
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

        for (Member m : MemberSystem.listMember) {
            if (choice == m.getId()) {
                m.listeResult.add(re);
                System.out.println(m);
            }
            // mangler fejltastning
            for (int i = 0; i < m.listeResult.size(); i++) {
                System.out.println(m.listeResult.get(i));
            }
        }

    }
}