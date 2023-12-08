package Delfinen;

import java.time.LocalDate;

public class Result implements Comparable <Result> {
    double tid;
    LocalDate resultDate;
    Disciplin disciplin = new Disciplin("Crawl", 100);

    public Result(double tid, LocalDate resultatDate){
        this.tid = tid;
        this.resultDate = resultatDate;
    }


    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resultDate + " disciplin " + disciplin;
    }
    public void setDisciplin(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    public void setTid(double tid) {
        this.tid = tid;
    }

    @Override
    public int compareTo(Result o) {
        return Double.compare(this.tid,o.tid);
    }
}