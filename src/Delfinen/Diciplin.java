package Delfinen;

import java.util.ArrayList;

public class Diciplin {
    String navn;
    double lenght;
    ArrayList<Result> ListOfResault = new ArrayList<>();

    public Diciplin(String navn, double lenght){
        this.navn = navn;
        this.lenght = lenght;
    }

    @Override
    public String toString() {
        return "diciplin: " + navn + " length: " + lenght;
    }
}
