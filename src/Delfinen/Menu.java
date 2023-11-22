package Delfinen;

import Delfinen.MemberSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    MemberSystem ms = new MemberSystem();
    Scanner scanner = new Scanner(System.in);

    public void hovedMenu () {

        while  (true) {
            System.out.println("Velkommen til svømmehallen Delfinen: ");
            System.out.println("1. Medlemsoplysninger");
            System.out.println("2. Kontingenter");
            System.out.println("3. Svømmeresultater");
            System.out.println("4. Stop program");
            System.out.print("Skriv dit valg: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        medlemsOplysningerMenu();
                        break;
                    case 2:
                        kontingenter();
                        break;
                    case 3:
                        svømmeResultater();
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

    public void medlemsOplysningerMenu() {
        boolean shutUp = false;
        while(!shutUp) {
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("Tast 1 for at oprette et medlem");
            System.out.println("Tast 2 for at se medlemmer.");
            System.out.println("Tast 3 for at slette medlemmer.");
            System.out.println("Tast 4 for ekstra medlemmer");
            System.out.println("tast 5 for at komme tilbage til hovedmenu.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: ms.addMember();
                    break;
                case 2: ms.watchMembers();
                    break;
                case 3:
                    System.out.println("Vælg nummer på person du ønsker at slette");
                    ms.watchMembers();
                    int indexToRemove = scanner.nextInt();
                    //ms.deleteMembers(indexToRemove);
                    break;
                case 4: ms.EkstraMember();
                    break;
                case 5:
                    System.out.println("tilbage til hovedmenu.");
                    shutUp = true;
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }

    public void kontingenter() {
        System.out.println("kommer senere.");
    }
    public void svømmeResultater() {
        System.out.println("kommer senere.");
    }
}