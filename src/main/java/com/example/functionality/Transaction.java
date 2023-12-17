package com.example.functionality;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    public String transactionDate;
    protected double amount;

    protected int balance =9000;
    protected String sender;
    protected String receiver;
    protected String type;
    protected Bank branch;
    protected String transactionID;

    protected String employee;
    public Transaction() {

    }

    public Transaction(double amount, String sender, String receiverUserName,String type) {
        LocalDateTime transactionDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.transactionDate = transactionDateTime.format(formatter);
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiverUserName;
        this.type=type;
        this.transactionID= String.valueOf((++DataBase.getInstance().lastTransactionID));
    }

    public Transaction(String transactionDate, double amount, String sender, String receiverUserName, String type, String transactionID) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiverUserName;
        this.type = type;
        this.transactionID = transactionID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bank getBranch() {
        return branch;
    }

    public void setBranch(Bank branch) { this.branch = branch; }

    public String getTransactionID() { return transactionID; }

    public void setTransactionID(String transactionID) { this.transactionID = transactionID; }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
