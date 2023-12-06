package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CompetitionSystem {
    public ArrayList<Member> juniorTeam = new ArrayList<>();
    public ArrayList<Member> seniorTeam = new ArrayList<>();
    MemberSystem ms = new MemberSystem();
    Scanner scan = new Scanner(System.in);
    static Disciplin crawl = new Disciplin("Crawl - 500m", 500);
    static Disciplin backstroke = new Disciplin("Rygsvømning - 500m", 500);
    static Disciplin freestyle = new Disciplin("freestyle - 500m", 500);

    public void addMemberToTeams() {
        for (int i = 0; i < MemberSystem.listMember.size(); i++) {
            Member member = MemberSystem.listMember.get(i);
            //  System.out.println("Vil du tilføje " + member.name + " til hold? (Ja/Nej)");
            int age = LocalDate.now().getYear() - member.age.getYear();

        /*    if (userInput.equalsIgnoreCase("Ja")) {
                if (juniorTeam.contains(member)) {
                    System.out.println("Der findes allerede en juniorsvømmer" + member);
                    continue;
                }
                if (seniorTeam.contains(member)) {
                    System.out.println("Der findes allerede en seniorsvømmer" + member);
                    continue;
                }*/
            if (member.getCompetitionSwimmer() && age < 18) {
                System.out.println(member.name + " er tilføjet juniorholdet.");
                juniorTeam.add(member);
            } else if (member.getCompetitionSwimmer() && age >= 18) {
                System.out.println(member.name + " er tilføjet seniorholdet.");
                seniorTeam.add(member);
            }

            System.out.println("Junior Team Size: " + juniorTeam.size());
            System.out.println("Senior Team Size: " + seniorTeam.size());
        }/* else if (userInput.equalsIgnoreCase("Nej")) {
                System.out.println("Bliver ikke gjort mere...");
            }*/
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
    public void displayTeamMembers(ArrayList<Member> team) {
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
        System.out.println("Junior Team Size: " + juniorTeam.size());
    }
    public void viewSeniorTeamList() {
        System.out.println();
        System.out.println("Seniorholdet: ");
        for (int i = 0; i < seniorTeam.size(); i++) {
            System.out.println(seniorTeam.get(i));
        }
        System.out.println("Senior Team Size: " + seniorTeam.size());
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

        Result re = new Result(tid, LocalDate.now());

        System.out.println("vægle en disciplin: ");
        System.out.println("1. for at vægle Rygsvømning - 500m");
        System.out.println("2. for at vægle Crawl - 500m");
        System.out.println("3. for at vægle freestyle - 500m");
        int menuChoice = scan.nextInt();

        switch (menuChoice) {
            case 1:
                re.setDisciplin(backstroke);
                for (Member m : MemberSystem.listMember) {
                    if (choice == m.getId()) {
                        m.backstrokeListe.add(re);
                        System.out.println(m);
                    }
                    // mangler fejltastning
                    for (int i = 0; i < m.backstrokeListe.size(); i++) {
                        System.out.println(m.backstrokeListe.get(i));
                    }
                }
                break;
            case 2:
                re.setDisciplin(crawl);
                for (Member m2 : MemberSystem.listMember) {
                    if (choice == m2.getId()) {
                        m2.crawlListe.add(re);
                        System.out.println(m2);
                    }
                    for (int i=0; i<m2.crawlListe.size(); i++) {
                        System.out.println(m2.crawlListe.get(i));
                    }
                }
                break;
            case 3:
                re.setDisciplin(freestyle);
                for (Member m3 : MemberSystem.listMember) {
                    if (choice == m3.getId()) {
                        m3.freestyleListe.add(re);
                        System.out.println(m3);
                    }
                    for (int i=0; i<m3.freestyleListe.size(); i++) {
                        System.out.println(m3.freestyleListe.get(i));
                    }
                }
                break;
        }

    }
    public void editResult() {
        Menu menu = new Menu();
        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();
        System.out.println("vælg ID på medlemmet: ");
        int choice = scan.nextInt();
        Member selectedMember = null;
        for (Member m : MemberSystem.listMember) {
            if (choice == m.getId()) {
                selectedMember = m;
                break;
            }
        }
        if (selectedMember == null) {
            System.out.println("Medlem ikke fundet.");
            return;
        }

        System.out.println("Vælg disciplin:");
        System.out.println("1. Rygsvømning - 500m");
        System.out.println("2. Crawl - 500m");
        System.out.println("3. Freestyle - 500m");
        int disciplineChoice = scan.nextInt();

        switch (disciplineChoice) {
            case 1:
                editDisciplineResults(selectedMember.backstrokeListe);
                menu.swimResults();
                break;
            case 2:
                editDisciplineResults(selectedMember.crawlListe);
                menu.swimResults();
                break;
            case 3:
                editDisciplineResults(selectedMember.freestyleListe);
                menu.swimResults();
                break;
            default:
                System.out.println("Ugyldigt valg.");
        }
    }
    private void editDisciplineResults(List<Result> resultList) {
        System.out.println("Vælg indeks for den resultat, du vil redigere:");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }

        int resultIndex = scan.nextInt();

        if (resultIndex >= 0 && resultIndex < resultList.size()) {

            System.out.println("Indtast ny tid:");
            double newTime = scan.nextDouble();
            resultList.get(resultIndex).setTid(newTime);

            System.out.println("Resultatet er blevet redigeret:");
            System.out.println(resultList.get(resultIndex));
        } else {
            System.out.println("Ugyldigt indeks.");
        }
    }
    public void deleteResult() {
        Menu menu = new Menu();
        System.out.println();

        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();

        System.out.println("Vælg ID nr. på medlemmet:");
        int memberId = scan.nextInt();

        Member selectedMember = null;
        for (Member m : MemberSystem.listMember) {
            if (memberId == m.getId()) {
                selectedMember = m;
                break;
            }
        }

        if (selectedMember == null) {
            System.out.println("Medlem ikke fundet.");
            return;
        }

        System.out.println("Vælg disciplin:");
        System.out.println("1. Rygsvømning - 500m");
        System.out.println("2. Crawl - 500m");
        System.out.println("3. Freestyle - 500m");
        int disciplineChoice = scan.nextInt();

        switch (disciplineChoice) {
            case 1:
                deleteDisciplineResults(selectedMember.backstrokeListe);
                menu.swimResults();
                break;
            case 2:
                deleteDisciplineResults(selectedMember.crawlListe);
                menu.swimResults();
                break;
            case 3:
                deleteDisciplineResults(selectedMember.freestyleListe);
                menu.swimResults();
                break;
            default:
                System.out.println("Ugyldigt valg.");
        }
    }
    private void deleteDisciplineResults(List<Result> resultList) {
        System.out.println("Vælg indeks for det resultat, du vil slette:");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }

        int resultIndex = scan.nextInt();

        if (resultIndex >= 0 && resultIndex < resultList.size()) {
            Result deletedResult = resultList.remove(resultIndex);
            System.out.println("Resultatet er blevet slettet:");
            System.out.println(deletedResult);

        } else {
            System.out.println("Ugyldigt indeks.");
        }
    }
    public void sortListResultInJuniorTeamBackstroke(){
        for (int i = 0; i < juniorTeam.size() ; i++) {
            juniorTeam.get(i).backstrokeListe.sort(null);
        }
    }
    public void sortListResultInJuniorTeamCrawl(){
        for (int i = 0; i < juniorTeam.size() ; i++) {
            juniorTeam.get(i).crawlListe.sort(null);
        }
    }
    public void sortListResultInJuniorTeamFree(){
        for (int i = 0; i < juniorTeam.size() ; i++) {
            juniorTeam.get(i).freestyleListe.sort(null);
        }
    }
    public void sortListResultInSeniorTeamBackstroke(){
        for (int i = 0; i < seniorTeam.size() ; i++) {
            seniorTeam.get(i).backstrokeListe.sort(null);
        }
    }
    public void sortListResultInSeniorTeamCrawl(){
        for (int i = 0; i < seniorTeam.size() ; i++) {
            seniorTeam.get(i).crawlListe.sort(null);
        }
    }
    public void sortListResultInSeniorTeamFree(){
        for (int i = 0; i < seniorTeam.size() ; i++) {
            seniorTeam.get(i).freestyleListe.sort(null);
        }
    }
    public void listOfResultJuniorBackstroke500() {
        sortListResultInJuniorTeamBackstroke();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListe()));
        for (Member member : juniorTeam) {
            System.out.println(member.getName() + " id nr: " + member.getId());
            for (Result result : member.backstrokeListe) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
    public void listOfResultJuniorCrawl500() {
        sortListResultInJuniorTeamCrawl();

        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCralwList()));
        for (Member member : juniorTeam) {
            System.out.println(member.getName() + " id nr: " + member.getId());
            for (Result result : member.crawlListe) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
    public void listOfResultJuniorFreeStyle500() {
        sortListResultInJuniorTeamBackstroke();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultfreestyleListe()));
        for (Member member : juniorTeam) {
            System.out.println(member.getName() + " id nr: " + member.getId());
            for (Result result : member.freestyleListe) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
    public void listOfResultSeniorBackstroke500() {
        sortListResultInJuniorTeamBackstroke();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListe()));
        for (Member member : seniorTeam) {
            System.out.println(member.getName() + " id nr: " + member.getId());
            for (Result result : member.backstrokeListe) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
    public void listOfResultSeniorCrawl500() {
        sortListResultInJuniorTeamBackstroke();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCralwList()));
        for (Member member : seniorTeam) {
            System.out.println(member.getName() + " id nr: " + member.getId());
            for (Result result : member.crawlListe) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
    public void listOfResultSeniorFreeStyle500() {
        sortListResultInJuniorTeamBackstroke();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultfreestyleListe()));
        for (Member member : seniorTeam) {
            System.out.println(member.getName() + " id nr: " + member.getId());
            for (Result result : member.freestyleListe) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
    public void bedst5OfBackstrokeJuniorSenior() {
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamBackstroke();
            System.out.println("de bedste 5 rygsvømning for junoir");
            System.out.println();
            juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListe()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(juniorTeam.get(i).getName() + " id nr: " + juniorTeam.get(i).getId());
                System.out.println(juniorTeam.get(i).backstrokeListe);
                System.out.println();
            }
        }
        if (seniorTeam.isEmpty()){
            System.out.println("Senior teamet er tomt");
        }
        if (!seniorTeam.isEmpty()) {
            sortListResultInSeniorTeamBackstroke();
            System.out.println("de bedste 5 rygsvømning for senior");
            System.out.println();
            seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListe()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(seniorTeam.get(i).getName() + " id nr: " + seniorTeam.get(i).getId());
                System.out.println(seniorTeam.get(i).backstrokeListe);
                System.out.println();
            }
        }
    }
    public void bedst5OfCrawlJuniorSenior() {
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamCrawl();
            System.out.println("de bedste 5 crawl for junior");
            System.out.println();
            juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCralwList()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(juniorTeam.get(i).getName() + " id nr: " + juniorTeam.get(i).getId());
                System.out.println(juniorTeam.get(i).crawlListe);
                System.out.println();
            }
        }
        if (seniorTeam.isEmpty()){
            System.out.println("Senior teamet er tomt");
        }
        if (!seniorTeam.isEmpty()) {
            sortListResultInSeniorTeamCrawl();
            System.out.println(" de bedste 5 crawl for senior");
            System.out.println();
            seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCralwList()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(seniorTeam.get(i).getName() + " id nr: " + seniorTeam.get(i).getId());
                System.out.println(seniorTeam.get(i).crawlListe);
                System.out.println();
            }
        }

    }
    public void bedst5OfFreeStyleJuniorSenior() {
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamFree();
            System.out.println("de bedste 5 freestyle for junior");
            System.out.println();
            juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultfreestyleListe()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(juniorTeam.get(i).getName() + " id nr: " + juniorTeam.get(i).getId());
                System.out.println(juniorTeam.get(i).freestyleListe);
                System.out.println();
            }
        }
        if (seniorTeam.isEmpty()){
            System.out.println("Senior teamet er tomt");
        }
        if (!seniorTeam.isEmpty()) {
            sortListResultInSeniorTeamFree();
            System.out.println("de bedste 5 freestyle for senior");
            System.out.println();
            seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultfreestyleListe()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(seniorTeam.get(i).getName() + " id nr: " + seniorTeam.get(i).getId());
                System.out.println(seniorTeam.get(i).freestyleListe);
                System.out.println();
            }
        }

    }
}