package com.example.functionality;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Account implements Serializable {

    // for checking in fees text
    public boolean flag = false;
    protected String user_name;
    private Integer account_number;
    Client owner;
    // The real pass taken from pass text field
    private String pass;

    public Double balance;

    // The account is saving (1) or current (0)
    public Boolean acc_type;
    // (0) if monthly interest, (1) if yearly
    protected Boolean TypeOfInterest;
    // (1) working , (0) Stopped
    protected Boolean State;

    protected float fees = 0;

    public void setFees_value(float fees_value) {
        this.fees_value = fees_value;
    }

    public float getFees_value() {
        return fees_value;
    }

    // Value of transferred or deposited or withdrawn money
    protected double value;

    // the value of money if he wants to pay his fees
    protected float fees_value;

    // The Collection of Notifications for the account
    ArrayList<Notification> accountNotification = new ArrayList<>();

    public void addNotification(Notification newNotification) {

        this.accountNotification.add(newNotification);
        //---Sorting the Array After Adding the New Notification---//
        Collections.sort(this.accountNotification,Notification.dateComparator);
    }
    public void applyInterest(LocalDate joinDate,Boolean Type0fInterest)
    {
        if (Type0fInterest)
        {
            int numYears = LocalDate.now().getYear() - joinDate.getYear();
            while (numYears>0)
            {
                calculate_balance(acc_type,Type0fInterest);
                String s = "Yearly Interest has been added\nYour Current Balance is " + balance;
                Notification interestNotification = new Notification("Important",s);
                addNotification(interestNotification);
                numYears--;
            }
        }
        else
        {
            int numMonths;
            int numYears = LocalDate.now().getYear() - joinDate.getYear();
            numMonths = (numYears*12) + LocalDate.now().getMonthValue() - joinDate.getMonthValue();
            while (numMonths>0)
            {
                calculate_balance(acc_type,Type0fInterest);
                String s = "Monthly Interest has been added\nYour Current Balance is " + balance;
                Notification interestNotification = new Notification("Important",s);
                addNotification(interestNotification);
                numMonths--;
            }
        }
    }
    public int countUnreadMessages(){
        int unReadMessages = 0;
        for(Notification n : accountNotification){
            if(!n.getMessageReadStatus())
            {
                unReadMessages++;
            }
        }
        return unReadMessages;
    }

    //for less than 3000 in the balance
    public float update_fees(float transaction) throws MoneyExceptions{
        double tran = transaction;
        if(transaction > balance){
            throw new MoneyExceptions(transaction,balance);

        }
        if(transaction < 0){
            throw new MoneyExceptions(tran);
        }
            flag = true;
            balance = balance - (transaction*0.05f);
            return transaction*0.1f;
    }

    // for more than 3000 LE in the Balance
    public float update_fees(double transaction) throws MoneyExceptions{

        if(transaction > balance){
            throw new MoneyExceptions(transaction,balance);
        }
        else if(transaction<0){
            throw new MoneyExceptions(transaction);
        }
            flag = true;
            float f = 100;
            balance -= f;
            return f;
    }

    public void increaseBalance(double value)
    {
       balance += value;
    }
    public void Update(double value) throws MoneyExceptions {
        if(value > balance)
        {
            throw new MoneyExceptions(value);
        }
        else
        {
            balance -= value;
        }
    }

    public void Update(float fees_value) throws MoneyExceptions{
       if (fees_value > fees)
       {
           throw new MoneyExceptions(fees_value);
       }
       else{
           balance -= fees_value;
           fees -= fees_value;
       }
    }

    public void calculate_balance(boolean acc_type,boolean TypeOfInterest){
       double interest;
       //Saving and monthly interest
       if (acc_type && !TypeOfInterest){
           interest = balance * (19.25/100) * (0.083);
           balance += interest;
       }
       //Saving and yearly interest
       else if (acc_type && TypeOfInterest){
           interest = balance * (19.25/100) * (1);
           balance += interest;
       }
    }

    public Account(String user_name, String pass, double balance,Boolean State,Boolean acc_type, Client owner) {
        this.user_name = user_name;
        this.pass = pass;
        this.balance = balance;
        this.acc_type = acc_type;
        this.State = State;
        this.owner = owner;
        this.account_number = ++DataBase.getInstance().lastAccountNumber;
        System.out.println("created new account");
    }


    public Integer getAccount_number() {
        return account_number;
    }

    public Client getOwner() {
        return owner;
    }

    public String getPass() {
        return pass;
    }

    public void setState(Boolean state) {
        State = state;
    }

    public String getState()
    {
        if (State)
        {
            return "Active";
        }
        else
            return "Inactive";
    }

    public String getType()
    {
        if (acc_type)
        {
            return "Saving";
        }
        else
        {
            return "Current";
        }
    }

    public ArrayList<Notification> getAllNotifications() {
        return accountNotification;
    }

    public double getBalance() {
        return balance;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getUser_name() {
        return user_name;
    }
}
