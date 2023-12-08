package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CompetitionSystem {
    public static ArrayList<Member> juniorTeam = new ArrayList<>();
    public static ArrayList<Member> seniorTeam = new ArrayList<>();
    MemberSystem ms = new MemberSystem();
    Scanner scan = new Scanner(System.in);
    static Disciplin crawl = new Disciplin("Crawl - 500m", 500);
    static Disciplin backstroke = new Disciplin("Rygsvømning - 500m", 500);
    static Disciplin freestyle = new Disciplin("freestyle - 500m", 500);

    public static void addMemberToTeams() {
        for (int i = 0; i < MemberSystem.listMember.size(); i++) {
            Member member = MemberSystem.listMember.get(i);
            int age = LocalDate.now().getYear() - member.age.getYear();

            if (member.getCompetitionSwimmer() && age < 18) {
                System.out.println(member.name + " er tilføjet juniorholdet.");
                juniorTeam.add(member);
            } else if (member.getCompetitionSwimmer() && age >= 18) {
                System.out.println(member.name + " er tilføjet seniorholdet.");
                seniorTeam.add(member);
            }

            System.out.println("Junior Team Size: " + juniorTeam.size());
            System.out.println("Senior Team Size: " + seniorTeam.size());
        }
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
    public int getIntInput() {
        while(true) {
            try {
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Ugyldigt input. Venligst indtast heltal: ");
                scan.next();
                while (scan.hasNextLine() && !scan.nextLine().isEmpty()) {
                }
            }
        }
    }
    public void setNewResult() { //Sætter resultat for svømmere til træning.
        System.out.println();

        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();

        System.out.println("vælg ID nr. på medlemmet");

        int choice = getIntInput();

        System.out.println("set ny resultat, skriv tid" + "\t" + ("min, sek"));
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
                }
                break;
            case 2:
                re.setDisciplin(crawl);
                for (Member m2 : MemberSystem.listMember) {
                    if (choice == m2.getId()) {
                        m2.crawlListe.add(re);
                        System.out.println(m2);
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
                }
                break;
        }

    }
    public void editResult() {
        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();
        System.out.println("vælg ID på medlemmet: ");
        int choice1 = scan.nextInt();
        Member selectedMember = null;
        for (Member m : MemberSystem.listMember) {
            if (choice1 == m.getId()) {
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
                break;
            case 2:
                editDisciplineResults(selectedMember.crawlListe);
                break;
            case 3:
                editDisciplineResults(selectedMember.freestyleListe);
                break;
            default:
                System.out.println("Ugyldigt valg.");
        }
    }

    public void editDisciplineResults(List<Result> resultList) {
        System.out.println("hvis du ikke ser en liste af resultater, så har personen ikke nogle");
        System.out.println("Vælg indeks for den resultat, du vil redigere:");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }
        int resultIndex = scan.nextInt();

        if (resultIndex >= 0 && resultIndex < resultList.size()) {

            System.out.println("Indtast ny tid(husk at skrive med komma):");
            double newTime = scan.nextDouble();
            resultList.get(resultIndex).setTid(newTime);

            System.out.println("Resultatet er blevet redigeret:");
            System.out.println(resultList.get(resultIndex));
        } else {
            System.out.println("Ugyldigt indeks.");
        }
    }

    public void deleteResult() {
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
                break;
            case 2:
                deleteDisciplineResults(selectedMember.crawlListe);
                break;
            case 3:
                deleteDisciplineResults(selectedMember.freestyleListe);
                break;
            default:
                System.out.println("Ugyldigt valg.");
        }
    }

    public void deleteDisciplineResults(List<Result> resultList) {
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

    //Sorterer juniorteamets resultater for hver disciplin til træning.
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

    //Sorterer seniorteamets resultater for hver disciplin til træning.
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

    //Printer alle juniorernes tider ud i hver disciplin til træning.
    public void listOfResultJuniorBackstroke500() {
        sortListResultInJuniorTeamBackstroke();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListe()));
        for (Member member : juniorTeam) {
            if (!member.backstrokeListe.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (Result result : member.backstrokeListe) {
                    System.out.println(result);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultJuniorCrawl500() {
        sortListResultInJuniorTeamCrawl();

        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCralwList()));
        for (Member member : juniorTeam) {
            if(!member.crawlListe.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (Result result : member.crawlListe) {
                    System.out.println(result);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultJuniorFreeStyle500() {
        sortListResultInJuniorTeamBackstroke();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultfreestyleListe()));
        for (Member member : juniorTeam) {
            if(!member.freestyleListe.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (Result result : member.freestyleListe) {
                    System.out.println(result);
                }
                System.out.println();
            }
        }
    }

    //Printer alle seniorernes tider ud i hver disciplin til træning.
    public void listOfResultSeniorBackstroke500() {
        sortListResultInJuniorTeamBackstroke();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListe()));
        for (Member member : seniorTeam) {
            if(!member.backstrokeListe.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (Result result : member.backstrokeListe) {
                    System.out.println(result);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultSeniorCrawl500() {
        sortListResultInJuniorTeamBackstroke();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCralwList()));
        for (Member member : seniorTeam) {
            if(!member.crawlListe.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (Result result : member.crawlListe) {
                    System.out.println(result);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultSeniorFreeStyle500() {
        sortListResultInJuniorTeamBackstroke();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultfreestyleListe()));
        for (Member member : seniorTeam) {
            if(!member.freestyleListe.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (Result result : member.freestyleListe) {
                    System.out.println(result);
                }
                System.out.println();
            }
        }
    }

    //Printer de 5 bedste i hver disciplin for juniorer og seniorer til træning.
    public void bedst5OfBackstrokeJuniorSenior() {
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamBackstroke();
            System.out.println("de bedste 5 rygsvømning for junior");
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

    //-----------------------------------------------------------\\
    //Turneringsresultater.

    public void sortListResultInJuniorTeamBackstrokeTournament() {  //Sorterer juniorteamets resultater for backstroke.

        for (int i = 0; i < juniorTeam.size() ; i++) {
            juniorTeam.get(i).backstrokeListTournament.sort(null);
        }
    }
    public void sortListResultInSeniorTeamBackstrokeTournament() { //Sorterer seniorteamets resultater for backstroke i en turnering.
        System.out.println("For senior team: ");
        for (int i=0;i<seniorTeam.size();i++) {
            seniorTeam.get(i).backstrokeListTournament.sort(null);
        }
    }

    public void listOfResultBackstroke500Tournament() { //Printer alle juniorernes tider ud i backstroke i en turnering.
        sortListResultInJuniorTeamBackstrokeTournament();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListeTournament()));
        for (Member member : juniorTeam) {
            if (!member.backstrokeListTournament.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (ResultTournament res : member.backstrokeListTournament) {
                    System.out.println(res);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultBackstroke500TournamentSenior() { //Printer alle seniorernes tider ud i backstroke i en turnering.
        sortListResultInSeniorTeamBackstrokeTournament();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListeTournament()));
        for (Member member : seniorTeam) {
            if(!member.backstrokeListTournament.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (ResultTournament res : member.backstrokeListTournament) {
                    if (member.backstrokeListTournament.contains(res)) {

                        System.out.println(res);
                    }
                }
                System.out.println();
            }
        }
    }

    public void sortListResultInJuniorTeamCrawlTournament() { //sorterer i crawl for juniorer.
        for (int i = 0; i < juniorTeam.size() ; i++) {
            juniorTeam.get(i).crawlListTournament.sort(null);
        }
    }
    public void sortListResultInSeniorTeamCrawlTournament() { //sorterer i crawl for seniorer.
        System.out.println("For senior team: ");
        for (int i=0;i<seniorTeam.size();i++) {
            seniorTeam.get(i).crawlListTournament.sort(null);
        }
    }
    public void listOfResultCrawl500Tournament() {
        sortListResultInJuniorTeamCrawlTournament();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCrawlListeTournament()));
        for (Member member : juniorTeam) {
            if(!member.crawlListTournament.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (ResultTournament res : member.crawlListTournament) {
                    System.out.println(res);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultCrawl500TournamentSenior() {
        sortListResultInSeniorTeamCrawlTournament();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCrawlListeTournament()));
        for (Member member : seniorTeam) {
            if(!member.crawlListTournament.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (ResultTournament res : member.crawlListTournament) {
                    if (member.crawlListTournament.contains(res)) {

                        System.out.println(res);
                    }
                }
                System.out.println();
            }
        }
    }

    public void sortListResultInJuniorTeamFreestyleTournament() {
        for (int i = 0; i < juniorTeam.size() ; i++) {
            juniorTeam.get(i).freestyleListTournament.sort(null);
        }
    }
    public void sortListResultInSeniorTeamFreestyleTournament() {
        System.out.println("For senior team: ");
        for (int i=0;i<seniorTeam.size();i++) {
            seniorTeam.get(i).freestyleListTournament.sort(null);
        }
    }
    public void listOfResultFreestyle500Tournament() {
        sortListResultInJuniorTeamFreestyleTournament();
        juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultFreestylelListeTournament()));
        for (Member member : juniorTeam) {
            if(!member.freestyleListTournament.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (ResultTournament res : member.freestyleListTournament) {
                    System.out.println(res);
                }
                System.out.println();
            }
        }
    }
    public void listOfResultFreestyle500TournamentSenior() {
        sortListResultInSeniorTeamFreestyleTournament();
        seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultFreestylelListeTournament()));
        for (Member member : seniorTeam) {
            if(!member.freestyleListTournament.isEmpty()) {
                System.out.println(member.getName() + " id nr: " + member.getId());
                for (ResultTournament res : member.freestyleListTournament) {
                    if (member.freestyleListTournament.contains(res)) {

                        System.out.println(res);
                    }
                }
                System.out.println();
            }
        }
    }

    public void best5OfBackstrokeJuniorSeniorTournament() { //udprinter de fem bedste i rygsvømning.
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamBackstroke();
            System.out.println("de bedste 5 rygsvømning for junior");
            System.out.println();
            juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListeTournament()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(juniorTeam.get(i).getName() + " id nr: " + juniorTeam.get(i).getId());
                System.out.println(juniorTeam.get(i).backstrokeListTournament);
                System.out.println();
            }
        }
        if (seniorTeam.isEmpty()){
            System.out.println("Senior teamet er tomt");
        }
        if (!seniorTeam.isEmpty()) {
            sortListResultInJuniorTeamBackstrokeTournament();
            System.out.println("de bedste 5 rygsvømning for senior");
            System.out.println();
            seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultBackstrokeListeTournament()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(seniorTeam.get(i).getName() + " id nr: " + seniorTeam.get(i).getId());
                System.out.println(seniorTeam.get(i).backstrokeListTournament);
                System.out.println();
            }
        }
    }
    public void best5OfCrawlJuniorSeniorTournament() { //udprinter de fem bedste i crawl.
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamCrawl();
            System.out.println("de bedste 5 crawl for junior");
            System.out.println();
            juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCrawlListeTournament()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(juniorTeam.get(i).getName() + " id nr: " + juniorTeam.get(i).getId());
                System.out.println(juniorTeam.get(i).crawlListTournament);
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
            seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultCrawlListeTournament()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(seniorTeam.get(i).getName() + " id nr: " + seniorTeam.get(i).getId());
                System.out.println(seniorTeam.get(i).crawlListTournament);
                System.out.println();
            }
        }
    }
    public void best5OfFreeStyleJuniorSeniorTournament() { //udprinter de fem bedste i freestyle.
        if (juniorTeam.isEmpty()){
            System.out.println("Junior teamet er tomt");
        }
        if (!juniorTeam.isEmpty()) {
            sortListResultInJuniorTeamFree();
            System.out.println("de bedste 5 freestyle for junior");
            System.out.println();
            juniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultFreestylelListeTournament()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(juniorTeam.get(i).getName() + " id nr: " + juniorTeam.get(i).getId());
                System.out.println(juniorTeam.get(i).freestyleListTournament);
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
            seniorTeam.sort(Comparator.comparingDouble(member -> member.getListeResultFreestylelListeTournament()));
            for (int i = 0; i <= 4; i++) {
                System.out.println(seniorTeam.get(i).getName() + " id nr: " + seniorTeam.get(i).getId());
                System.out.println(seniorTeam.get(i).freestyleListTournament);
                System.out.println();
            }
        }
    }
    public void setNewResultTournament() { //sætter nyt rsultat i turnering.
        System.out.println();

        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();

        System.out.println("vælg ID nr. på medlemmet");

        int choice = getIntInput();

        System.out.println("set ny resultat, skriv tid" + "\t" + ("min, sek"));
        double tid = scan.nextDouble();

        System.out.println("Skriv navnet på stævnet");
        String tournamentName = scan.next();

        System.out.println("Skriv hvilken placering svømmeren fik til stævnet");
        int placement = scan.nextInt();

        ResultTournament res = new ResultTournament(tid, LocalDate.now(), tournamentName, placement);

        System.out.println("vægle en disciplin: ");
        System.out.println("1. for at vægle Rygsvømning - 500m");
        System.out.println("2. for at vægle Crawl - 500m");
        System.out.println("3. for at vægle freestyle - 500m");
        int menuChoice = scan.nextInt();

        switch (menuChoice) {
            case 1:

                for (Member m : MemberSystem.listMember) {
                    if (choice == m.getId()) {
                        res.setDisciplin(backstroke);
                        m.backstrokeListTournament.add(res);
                        System.out.println(m);
                    }

                    for (int i = 0; i < m.backstrokeListTournament.size(); i++) {
                        System.out.println(m.backstrokeListTournament.get(i));
                    }
                }
                break;
            case 2:
                res.setDisciplin(crawl);
                for (Member m2 : MemberSystem.listMember) {
                    if (choice == m2.getId()) {
                        m2.crawlListTournament.add(res);
                        System.out.println(m2);
                    }
                    for (int i=0; i<m2.crawlListTournament.size(); i++) {
                        System.out.println(m2.crawlListTournament.get(i));
                    }
                }
                break;
            case 3:
                res.setDisciplin(freestyle);
                for (Member m3 : MemberSystem.listMember) {
                    if (choice == m3.getId()) {
                        m3.freestyleListTournament.add(res);
                        System.out.println(m3);
                    }
                    for (int i=0; i<m3.freestyleListTournament.size(); i++) {
                        System.out.println(m3.freestyleListTournament.get(i));
                    }
                }
                break;
        }

    }
    public void editResultTournament() {
        System.out.println("Liste over medlemmer:");
        ms.viewMemberList();
        System.out.println("vælg ID på medlemmet: ");
        int choice1 = scan.nextInt();
        Member selectedMember = null;
        for (Member m : MemberSystem.listMember) {
            if (choice1 == m.getId()) {
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
                editDisciplineResultsTournament(selectedMember.backstrokeListTournament);
                break;
            case 2:
                editDisciplineResultsTournament(selectedMember.crawlListTournament);
                break;
            case 3:
                editDisciplineResultsTournament(selectedMember.freestyleListTournament);
                break;
            default:
                System.out.println("Ugyldigt valg.");
        }
    }
    public void editDisciplineResultsTournament(List<ResultTournament> resultList) {
        System.out.println("hvis du ikke ser en liste af resultater, så har personen ikke nogle");
        System.out.println("Vælg indeks for den resultat, du vil redigere:");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }
        int resultIndex = scan.nextInt();

        if (resultIndex >= 0 && resultIndex < resultList.size()) {

            System.out.println("Indtast ny tid:");
            double newTime = scan.nextDouble();
            resultList.get(resultIndex).setTid(newTime);

            System.out.println("Indtast nyt stævnenavn:");
            String newTournamentName = scan.next();
            resultList.get(resultIndex).setTournamentName(newTournamentName);

            System.out.println("Indtast ny placering:");
            int newPlacement = scan.nextInt();
            resultList.get(resultIndex).setPlacement(newPlacement);

            System.out.println("Resultatet er blevet redigeret:");
            System.out.println(resultList.get(resultIndex));
        } else {
            System.out.println("Ugyldigt indeks.");
        }
    }
    public void deleteResultTournament() {
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
                deleteDisciplineResultsTournament(selectedMember.backstrokeListTournament);
                break;
            case 2:
                deleteDisciplineResultsTournament(selectedMember.crawlListTournament);
                break;
            case 3:
                deleteDisciplineResultsTournament(selectedMember.freestyleListTournament);
                break;
            default:
                System.out.println("Ugyldigt valg.");
        }
    }
    public void deleteDisciplineResultsTournament(List<ResultTournament> resultList) {
        System.out.println("Vælg indeks for det resultat, du vil slette:");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }

        int resultIndex = scan.nextInt();

        if (resultIndex >= 0 && resultIndex < resultList.size()) {
            ResultTournament deletedResult = resultList.remove(resultIndex);
            System.out.println("Resultatet er blevet slettet:");
            System.out.println(deletedResult);

        } else {
            System.out.println("Ugyldigt indeks.");
        }
    }
}