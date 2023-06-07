package com.iberia.academy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

    public static final String STATEMENT_HEADER = "Date\tAmount\tBalance";
    final List<Transaction> transactions = new ArrayList<>();

    public void deposit(int amount) {
        int balance = balance();
        transactions.add(new Transaction(amount, balance + amount));
    }

    public void withdraw(int amount) {
        int balance = balance();
        if (amount > balance) {
            throw new NotEnoughBalanceException();
        }
        transactions.add(new Transaction(-amount, balance - amount));
    }

    public int balance() {
       return transactions.stream().mapToInt(transaction -> transaction.getAmount()).reduce(0, Integer::sum);
    }

    public String printStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append(STATEMENT_HEADER);

        for(Transaction transaction : transactions) {
            statement.append("\n").append(transaction.toString());
        }

        return statement.toString();
    }
}
