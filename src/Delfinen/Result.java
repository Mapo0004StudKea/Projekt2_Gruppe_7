package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Result implements Comparable <Result> {
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

    public static void main(String[] args) {
        Disciplin displin = new Disciplin("Crawl", 100);
        ArrayList<Result> listRe = new ArrayList<>();

        Result re = new Result(7,LocalDate.now(),displin);
        Result re1 = new Result(8,LocalDate.now(),displin);
        Result re2 = new Result(5,LocalDate.now(),displin);
        listRe.add(re);
        listRe.add(re1);
        listRe.add(re2);

        listRe.sort(null);

        //Collections.sort(listRe);

        System.out.println(listRe);


    }

    @Override
    public String toString() {
        return "din nye tid "+tid +" dd "+ resultDate + " disciplin " + displin;
    }
    public Disciplin getDisplin() {
        return displin;
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