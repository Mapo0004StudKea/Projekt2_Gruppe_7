package Delfinen;

import java.time.LocalDate;

public class Transaction {
    String text;
    LocalDate date;
    double amount;
    double newBalance;

    Transaction(String text, double amount, double newBalance) {
        this.text = text;
        this.amount = amount;
        this.newBalance = newBalance;
        date = LocalDate.now();
    }

    @Override
    public String toString() {
        return text + "\t" + date + "\t" + amount + "\t" + newBalance;

    }

}
