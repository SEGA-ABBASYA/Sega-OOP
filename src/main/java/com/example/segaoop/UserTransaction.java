package com.example.segaoop;
import java.time.LocalDate;

public class UserTransaction {
    private String transactionDate;
    private int debit;
    private int balance =9000;

    public UserTransaction(String transactionDate, int debit) {
        this.transactionDate = transactionDate;
        this.debit = debit;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getDebit() {
        return debit;
    }

    public int getBalance() {
        return balance;
    }
}
