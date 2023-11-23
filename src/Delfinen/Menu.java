package Delfinen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    MemberSystem ms = new MemberSystem();
    Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while  (true) {
            System.out.println("Velkommen til svømmehallen Delfinen");
            System.out.println("1. medlemsoplysninger");
            System.out.println("2. kontingenter");
            System.out.println("3. svømmeresultater");
            System.out.println("4. stop program");
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
        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("Tast 1 for at oprette et medlem");
            System.out.println("Tast 2 for at se medlemmer");
            System.out.println("Tast 3 for at slette medlemmer");
            System.out.println("Tast 4 for hovedmenu");
            System.out.println("Tast 8 for at putte medlemmer i ´se medlemmer´");
            int choice = s.nextInt();

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
                    mainMenu();
                case 8:
                    ms.EkstraMember();
                    break;
            }
        }
    }

    public void Accounting() {
        System.out.println("kommer senere.");
    }
    public void swimResults() {
        System.out.println("kommer senere.");
    }
}
