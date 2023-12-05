package Delfinen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    CompetitionSystem cs = new CompetitionSystem();
    Accounting acc = new Accounting();
    MemberSystem ms = new MemberSystem();
    Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while  (true) {
            System.out.println("Velkommen til svømmehallen Delfinen");
            System.out.println("1: Medlemsoplysninger");
            System.out.println("2: Kontingenter");
            System.out.println("3: Svømmeresultater");
            System.out.println("4: Stop program");
            System.out.print("Skriv dit valg: ");
            int choice = sc.nextInt();
            try {
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
                    case 8:
                        ms.EkstraMember();
                        break;

                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            }   catch (InputMismatchException e) {
                System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
                scanner.nextLine();
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
                        ms.listMember.get(choices).printTransaction();
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
            }
        }


    }
    public void swimResults() {
        while(true) {
            System.out.println();
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("1: Tilføj medlem til hold");
            System.out.println("2: fjern medlem fra hold");
            System.out.println("3: Se hold liste");
            System.out.println("4: sæt nyt svømmeresultat");
            System.out.println("5: se juniorholdet bedste træningstider i Rygsvømning, Crawl og Freestyle");
            System.out.println("6: se seniorholdet bedste træningstider i Rygsvømning, Crawl og Freestyle");
            System.out.println("7: se de bedste 5 i rygsvømning for junior og senior");
            System.out.println("8: se de bedste 5 i crawl for junior og senior ");
            System.out.println("9: se de bedste 5 i freestyle for junior og senior");
            System.out.println("10: tilbage til hovedmenu");
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
                        cs.viewJuniorTeamList();
                        cs.viewSeniorTeamList();
                        break;
                    case 4:
                        cs.setNewResult();
                        break;
                    case 5:
                        cs.listOfResultJuniorBackstroke500();
                        cs.listOfResultJuniorCrawl500();
                        cs.listOfResultJuniorFreeStyle500();
                        break;
                    case 6:
                        cs.listOfResultSeniorBackstroke500();
                        cs.listOfResultSeniorCrawl500();
                        cs.listOfResultSeniorFreeStyle500();
                        break;
                    case 7:
                        cs.bedst5OfBackstrokeJuniorSenior();
                        break;
                    case 8:
                        cs.bedst5OfCrawlJuniorSenior();
                        break;
                    case 9:
                        cs.bedst5OfFreeStyleJuniorSenior();
                        break;
                    case 10:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            }   catch (InputMismatchException e) {
                System.out.println("Error: Indtast venligst et gyldigt heltalsværdi.");
                scanner.nextLine();
            }
        }
    }
}