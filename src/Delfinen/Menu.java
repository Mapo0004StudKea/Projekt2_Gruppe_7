package Delfinen;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    CompetitionSystem cs = new CompetitionSystem();
    Accounting acc = new Accounting();
    MemberSystem ms = new MemberSystem();
    Scanner scanner = new Scanner(System.in);

    public void mainMenu() throws IOException {


        while  (true) {
            System.out.println("Velkommen til svømmehallen Delfinen");
            System.out.println("1: Medlemsoplysninger");
            System.out.println("2: Kontingenter");
            System.out.println("3: Svømmeresultater");
            System.out.println("4: Stop program");
            try {
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

                switch (choice) {
                    case 1:
                        memberInfoMenu();
                        break;
                    case 2:
                        Accounting();
                        break;
                    case 3:
                        swimResults();
                        break;
                    case 4:
                        System.out.println("Stopper programmet.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
                scanner.nextLine();
            }
        }
    }

    public void memberInfoMenu() {
        while(true) {
            System.out.println();
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("1: Opret nyt medlem");
            System.out.println("2: Slet et medlem");
            System.out.println("3: Rediger medlemsdata");
            System.out.println("4: Se medlemsliste");
            System.out.println("5: Se medlemmer som har betalt");
            System.out.println("6: Tilbage til hovedmenu");
            System.out.println("8: for at putte medlemmer i ´se medlemsliste´");
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        ms.addMember();
                        break;
                    case 2:
                        ms.viewMemberList();
                        ms.deleteMember();
                        break;
                    case 3:
                        ms.editMember();
                        break;
                    case 4:
                        ms.viewMemberList();
                        break;
                    case 5:
                        ms.seeIfMemberHadPaid();
                        break;
                    case 6:
                        mainMenu();
                        break;

                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            }   catch (InputMismatchException e) {
                System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
                scanner.nextInt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Accounting() {
        while(true) {
            System.out.println();
            System.out.println("Accounting og finans menu");
            System.out.println("1: Se kontingent priser");
            System.out.println("2: For at et medlem kan betale");
            System.out.println("3: Print transaktion");
            System.out.println("4. Liste med total beløb");
            System.out.println("5: Se liste med passive medlemmer");
            System.out.println("6: Tilbage til hovedmenu");
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        acc.viewPrices();
                        break;
                    case 2:
                        ms.viewMemberList();
                        acc.newTransaction();
                        break;
                    case 3:
                        ms.viewMemberList();
                        System.out.println("vælg hvem du gerne ville se");
                        int choices = scanner.nextInt();
                        for (Member m : MemberSystem.listMember) {
                            if (choices == m.getId()) {
                                m.printTransaction();
                            }
                        }
                        break;
                    case 4:
                        acc.listOfPayment();
                        break;
                    case 5:
                        ms.seeMemberPassive();
                        break;
                    case 6:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            }   catch (InputMismatchException e) {
                System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
                scanner.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public void swimResults() {
        while(true) {
            System.out.println();
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("1: Tilføj medlem til hold");
            System.out.println("2: Fjern medlem fra hold");
            System.out.println("3: Se holdliste");
            System.out.println("4: Se træning og resultat menu");
            System.out.println("5: Se turnering og resultat menu");
            System.out.println("6: Tilbage til hovedmenu");
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        cs.addMemberToTeams();
                        break;
                    case 2:
                        cs.removeMemberFromTeams();
                        break;
                    case 3:
                        displayTeams();
                        break;
                    case 4:
                        TrainingAndResultMenu();
                        break;
                    case 5:
                        TournamentAndResultMenu();
                        break;
                    case 6:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            }   catch (InputMismatchException e) {
                System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
                scanner.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void displayTeams() {
        System.out.println("-- Hold størrelse --");
        System.out.println("Junior Team Size: " + cs.juniorTeam.size());
        System.out.println("Senior Team Size: " + cs.seniorTeam.size());

        System.out.println("\nVil du se holdlisterne? ");
        System.out.println("1: For at se Juniorholdet.");
        System.out.println("2: For at se Seniorholdet.");
        System.out.println("3: Tilbage til medlemsoplysninger Menu");
        System.out.print("Skriv dit valg: ");
        int choice = scanner.nextInt();
        try {
            switch (choice) {
                case 1:
                    cs.viewJuniorTeamList();
                    break;
                case 2:
                    cs.viewSeniorTeamList();
                    break;
                case 3:
                    swimResults();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
            scanner.nextLine();
        }
    }
    public void TrainingAndResultMenu(){
        System.out.println("Menu for træning og resultater");
        System.out.println("1: Sæt nyt svømmeresultat");
        System.out.println("2: Se juniorholdet bedste træningstider i Rygsvømning, Crawl og Freestyle");
        System.out.println("3: Se seniorholdet bedste træningstider i Rygsvømning, Crawl og Freestyle");
        System.out.println("4: Se de bedste 5 i rygsvømning for junior og senior");
        System.out.println("5: Se de bedste 5 i crawl for junior og senior ");
        System.out.println("6: Se de bedste 5 i freestyle for junior og senior");
        System.out.println("7: For at redigere resultater");
        System.out.println("8: For at slette et resultat");
        System.out.println("9: Tilbage til tidligere menu");
        System.out.print("Skriv dit valg: ");
        int choice = scanner.nextInt();
        try {
            switch (choice) {
                case 1:
                    cs.setNewResult();
                    TrainingAndResultMenu();
                    break;
                case 2:
                    cs.listOfResultJuniorBackstroke500();
                    cs.listOfResultJuniorCrawl500();
                    cs.listOfResultJuniorFreeStyle500();
                    TrainingAndResultMenu();
                    break;
                case 3:
                    cs.listOfResultSeniorBackstroke500();
                    cs.listOfResultSeniorCrawl500();
                    cs.listOfResultSeniorFreeStyle500();
                    TrainingAndResultMenu();
                    break;
                case 4:
                    cs.bedst5OfBackstrokeJuniorSenior();
                    TrainingAndResultMenu();
                    break;
                case 5:
                    cs.bedst5OfCrawlJuniorSenior();
                    TrainingAndResultMenu();
                    break;
                case 6:
                    cs.bedst5OfFreeStyleJuniorSenior();
                    TrainingAndResultMenu();
                    break;
                case 7:
                    cs.editResult();
                    TrainingAndResultMenu();
                    break;
                case 8:
                    cs.deleteResult();
                    TrainingAndResultMenu();
                    break;
                case 9:
                    swimResults();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
            scanner.nextLine();
        }
    }
    public void TournamentAndResultMenu() {
        System.out.println("Menu for turnering og resultater");
        System.out.println("1: Sæt nyt svømmeresultat for turnering");
        System.out.println("2: Se liste for resultater 'backstroke, crawl og freestyle for junior' i turnering");
        System.out.println("3: Se liste for resultater 'backstroke, crawl og freestyle' for seniorer i turnering");
        System.out.println("4: Se de bedste 5 i rygsvømning for junior og senior");
        System.out.println("5: Se de bedste 5 i crawl for junior og senior ");
        System.out.println("6: Se de bedste 5 i freestyle for junior og senior");
        System.out.println("7: Tilbage til tidligere menu");
        System.out.print("Skriv dit valg: ");
        int choice = scanner.nextInt();
        try {
            switch (choice) {
                case 1:
                    cs.setNewResultTournament();
                    TournamentAndResultMenu();
                    break;
                case 2:
                    System.out.println("Stævneresultater for juniorer i backstroke" + "\n");
                    cs.listOfResultBackstroke500Tournament();
                    System.out.println("Stævneresultater for juniorer i crawl" + "\n");
                    cs.listOfResultCrawl500Tournament();
                    System.out.println("Stævneresultater for juniorer i freestyle" + "\n");
                    cs.listOfResultFreestyle500Tournament();
                    TournamentAndResultMenu();
                    break;
                case 3:
                    System.out.println("Stævneresultater for seniorer i backstroke" + "\n");
                    cs.listOfResultBackstroke500TournamentSenior();
                    System.out.println("Stævneresultater for seniorer i crawl" + "\n");
                    cs.listOfResultCrawl500TournamentSenior();
                    System.out.println("Stævneresultater for seniorer i freestyle" + "\n");
                    cs.listOfResultFreestyle500TournamentSenior();
                    TournamentAndResultMenu();
                    break;
                case 4:
                    cs.best5OfBackstrokeJuniorSeniorTournament();
                    break;
                case 5:
                    cs.best5OfCrawlJuniorSeniorTournament();
                    break;
                case 6:
                    cs.best5OfFreeStyleJuniorSeniorTournament();
                    break;
                case 7:
                    swimResults();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
            scanner.nextLine();
        }
    }
}