package Delfinen;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    ArrayList<CompetitionSwimmer> times = new ArrayList<>();
    ArrayList<CompetitionSwimmer> disciplin 

    double time;
    String disciplin;

    CompetitionSwimmer(int id, String name, LocalDate age) {
        super (id, name, age);
    }

    public double getTime() {
        return time;
    }

    public String getDisciplin() {
        return disciplin;
    }
}
