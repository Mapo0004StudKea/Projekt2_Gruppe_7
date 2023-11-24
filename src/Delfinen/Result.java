package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Result {
    double tid;
    LocalDate resaultDate;
    ArrayList<Diciplin> ListOfDiciplin = new ArrayList<>();

    public Result(double tid, LocalDate resaultDate){
        this.tid = tid;
        this.resaultDate = resaultDate;
    }

    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resaultDate;
    }
}


