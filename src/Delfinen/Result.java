package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Result {
    double tid;
    LocalDate resultDate;
    ArrayList<Diciplin> ListOfDisciplin = new ArrayList<>();
    Diciplin displin = new Diciplin("Crawl", 100);

    public Result(double tid, LocalDate resultatDate,Diciplin displin){
        this.tid = tid;
        this.resultDate = resultatDate;
        this.displin = displin;
        //this.ListOfDisciplin = ListOfDisciplin;
    }

    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resultDate + " disciplin " + displin;
    }

    public void setDisplin(Diciplin displin) {
        this.displin = displin;
    }
}


