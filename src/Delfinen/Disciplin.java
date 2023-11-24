package Delfinen;

public class Disciplin {
    String navn;
    double length;


    public Disciplin(String navn, double length){
        this.navn = navn;
        this.length = length;
    }

    @Override
    public String toString() {
        return "disciplin: " + navn + " length: " + length;
    }
}
