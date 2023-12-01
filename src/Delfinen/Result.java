package Delfinen;

import java.time.LocalDate;

public class Result implements Comparable <Result> {
    double tid;
    LocalDate resultDate;
    Disciplin displin = new Disciplin("Crawl", 100);

    public Result(double tid, LocalDate resultatDate){
        this.tid = tid;
        this.resultDate = resultatDate;
    }


    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resultDate + " disciplin " + displin;
    }
    public String getDisplin() {
        return displin.navn;
    }
    public void setDisplin(Disciplin displin) {
        this.displin = displin;
    }
    public double getTid() {
        return tid;
    }

    public void setTid(double tid) {
        this.tid = tid;
    }
    public LocalDate getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDate resultDate) {
        this.resultDate = resultDate;
    }

    @Override
    public int compareTo(Result o) {
        return Double.compare(this.tid,o.tid);
    }
}