package Delfinen;

import java.util.Scanner;

public class Menu {
    MemberSystem ms = new MemberSystem();

    public void menu() {
        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.println("Velkommen til svømmehallen Delfinen");
            System.out.println("Tast 1 for at oprette et medlem");
            System.out.println("Tast 2 for at se medlemmer");
            int choice = s.nextInt();

            switch (choice) {
                case 1: ms.addMember();
                    break;

                case 2: ms.watchMembers();
                break;
            }
        }
    }
}
