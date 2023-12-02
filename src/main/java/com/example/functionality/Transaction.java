package com.example.functionality;

public class Transaction {
    protected String transactionDate;
    protected int debit;

    protected int balance =9000;
    protected String senderName;
    protected String receiverName;
    protected String type;
    protected Bank branch;
    protected String transactionID;

    public Transaction(String transactionDate, int debit) {
        this.transactionDate = transactionDate;
        this.debit = debit;
    }

    public Transaction(String transactionDate, int debit, String senderName, String receiverName, String type, String transactionID) {
        this.transactionDate = transactionDate;
        this.debit = debit;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.type = type;
        this.transactionID = transactionID;
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
