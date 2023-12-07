package Delfinen;

import java.time.LocalDate;

public class ResultTournament extends Result {
    String tournamentName;
    int placement;
    Disciplin disciplin = new Disciplin("Crawl", 100);

    public ResultTournament(double tid, LocalDate resultatDate, String tournamentName, int placement) {

        super(tid, resultatDate);

        this.tournamentName = tournamentName;
        this.placement = placement;

    }

    public String toString() {
        return "Navnet på konkurrencen: " + tournamentName + " Placering til stævnet " + placement +" din nye tid "+tid +" dd "+ resultDate + " disciplin " + disciplin;
    }

    @Override
    public void setDisciplin(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentName() {
        return tournamentName;
    }
    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public int getPlacement() {
        return placement;
    }

}