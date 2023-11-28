package Delfinen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
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
            System.out.println("2: Se medlemsliste");
            System.out.println("3: slet medlem");
            System.out.println("4: Ændre medlemsdata");
            System.out.println("5: Tilbage til hovedmenu");
            System.out.println("6: Se medlemmer som har betalt");
            System.out.println("8: for at putte medlemmer i ´se medlemsliste´");
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: ms.addMember();
                    break;
                case 2: ms.viewMemberList();
                    break;
                case 3:
                    ms.viewMemberList();
                    ms.deleteMember();
                    break;
                case 4:
                    ms.editMember();
                    break;
                case 5: mainMenu();
                    break;
                case 6: ms.seeIfMemberHadPaid();
                    break;
                case 8:
                    ms.EkstraMember();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }

    public void Accounting() {
        while(true) {
            System.out.println();
            System.out.println("Accounting og finans menu");
            System.out.println("1: Se liste med passive medlemmer");
            System.out.println("2: for at et medlem kan betale");
            System.out.println("3: Se kontingent priser");
            System.out.println("4: Print transaction");
            System.out.println("5: Tilbage til hovedmenu");
            System.out.println("6. Liste med total beløb");
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: ms.seeMemberPassive();
                    break;
                case 2:
                    ms.viewMemberList();
                    ms.newTransaction();
                    break;
                case 3:
                    acc.viewPrices();
                    break;

                case 4:
                    ms.viewMemberList();
                    System.out.println("vælg hvem du gerne ville se");
                    int chocie = scanner.nextInt();
                    ms.listMember.get(chocie).printTransektions();
                    break;
                case 5:
                    mainMenu();
                    break;

                case 6:ms.listOfPayment();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }


    }
    public void swimResults() {
        while(true) {
            System.out.println();
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("1: sæt nyt svømmeresultat");
            System.out.println("2: tilbage til hovedmenu");
            System.out.print("Skriv dit valg: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:  ms.setNewResult();
                    break;
                case 2: mainMenu();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }
}