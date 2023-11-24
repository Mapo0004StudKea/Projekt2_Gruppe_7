package Delfinen;

import java.time.LocalDate;

public class Result {
    double tid;
    LocalDate resaultDate;

    public Result(double tid, LocalDate resaultDate){
        this.tid = tid;
        this.resaultDate = resaultDate;
    }

    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resaultDate;
    }
}


