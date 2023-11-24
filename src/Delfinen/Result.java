package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Result {
    double tid;
    LocalDate resultDate;
    ArrayList<Disciplin> ListOfDisciplin = new ArrayList<>();

    public Result(double tid, LocalDate resultatDate){
        this.tid = tid;
        this.resultDate = resultatDate;
    }

    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resultDate;
    }
}


