package Delfinen;

public class Diciplin {
    String navn;
    double length;


    public Diciplin(String navn, double length){
        this.navn = navn;
        this.length = length;
    }

    @Override
    public String toString() {
        return  navn + " length: " + length;
    }
}
