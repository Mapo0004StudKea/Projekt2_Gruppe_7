package Delfinen;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFillMotionister.ReadMyFillMotionister();
        ReadFill.ReadMyFill();
        Menu newMenu = new Menu();
        newMenu.mainMenu();

    }
}