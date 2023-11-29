package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Result {
    double tid;
    LocalDate resultDate;
    ArrayList<Disciplin> ListOfDisciplin = new ArrayList<>();
    Disciplin displin = new Disciplin("Crawl", 100);

    public Result(double tid, LocalDate resultatDate, Disciplin displin){
        this.tid = tid;
        this.resultDate = resultatDate;
        this.displin = displin;
        //this.ListOfDisciplin = ListOfDisciplin;
    }

    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resultDate + " disciplin " + displin;
    }

    public void setDisplin(Disciplin displin) {
        this.displin = displin;
    }
}