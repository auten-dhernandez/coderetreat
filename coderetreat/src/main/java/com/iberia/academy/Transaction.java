package com.iberia.academy;

import java.time.LocalDate;

public class Transaction {

    private final LocalDate date;
    private final int amount;
    private final int balance;

    public Transaction(int amount, int balance) {
        this.amount = amount;
        this.balance = balance;
        this.date = LocalDate.now();
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        String formattedAmount = ((amount > 0) ? "+" : "") + amount
                ;
        return String.format("%s\t%s\t%d", date, formattedAmount, balance);
    }
}
