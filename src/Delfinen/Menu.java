package Delfinen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    MemberSystem ms = new MemberSystem();
    Scanner scanner = new Scanner(System.in);

    public void hovedMenu () {
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
                        medlemsOplysningerMenu();
                        break;
                    case 2:
                        kontingenter();
                        break;
                    case 3:
                        svømmeResultater();
                        break;
                    default:
                        System.out.println("Ugyldigt valg.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer choice.");
                scanner.nextLine();
            }
        }
    }

    public void medlemsOplysningerMenu() {
        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.println("Menu for medlemsoplysninger");
            System.out.println("Tast 1 for at oprette et medlem");
            System.out.println("Tast 2 for at se medlemmer");
            System.out.println("Tast 3 for at slette medlemmer");
            int choice = s.nextInt();

            switch (choice) {
                case 1: ms.addMember();
                    break;

                case 2: ms.watchMembers();
                break;
                case 8: ms.EkstraMember();
                break;

                case 3:
                    System.out.println("Vælg nummer på person du ønsker at slette");
                    ms.watchMembers();
                    int indexToRemove = s.nextInt();
                    //ms.deleteMembers(indexToRemove);
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
