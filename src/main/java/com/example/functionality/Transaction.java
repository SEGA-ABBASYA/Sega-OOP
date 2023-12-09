package com.example.functionality;

import java.io.Serializable;

public class Transaction implements Serializable {
    protected String transactionDate;
    protected int amount;

    protected int balance =9000;
    protected String senderName;
    protected String receiverName;
    protected String type;
    protected Bank branch;
    protected String transactionID;

    public Transaction(String transactionDate, int amount) {
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public Transaction(String transactionDate, int amount, String senderName, String receiverName, String type, String transactionID) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.type = type;
        this.transactionID = transactionID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }
}
