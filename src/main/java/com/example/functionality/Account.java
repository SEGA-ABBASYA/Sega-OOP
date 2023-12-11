package com.example.functionality;

import java.io.Serializable;

public class Account implements Serializable {
    public String getUser_name() {
        return user_name;
    }

    protected String user_name;

    private Integer account_number;
    Client owner;

    // The real pass taken from pass text field
    private String pass;

    protected String hashed_pass;

    public Double balance;

    // The account is saving (1) or current (0)
    protected Boolean acc_type;

    // (0) if monthly interest, (1) if yearly
    protected Boolean TypeOfInterest;

    protected float fees;

    // (0) if transfer or withdraw, (1) if deposit
    protected boolean operation;

    // Value of transferred or deposited or withdrawn money
    protected double value;

    // the value of money if he wants to pay his fees
    protected float fees_value;

    PassHashing hash = new PassHashing();
   public void inner_hash(){
       hashed_pass = hash.Hash(pass);
   }

   public void calculate_fees(float fees_value){
       if (balance < 3000)
       {
           fees += 50;
       }
   }

//    public void update_balance(boolean operation, double value) throws MoneyExceptions
//    {
//        if (value > balance && !operation)
//        {
//            throw new MoneyExceptions(value);
//        }
//        else if (operation)
//        {
//            balance += value;
//        }
//        else {
//            balance -= value;
//        }
//    }

    public void increaseBalance(double value)
    {
       balance += value;
    }
    public void decreaseBalance(double value) throws MoneyExceptions {
        if(value > balance)
        {
            throw new MoneyExceptions(value);
        }
        else
        {
            balance -= value;
        }
    }

    public void update_fees(float fees_value) throws MoneyExceptions{
       if (fees_value > fees)
       {
           throw new MoneyExceptions(fees_value);
       }
       else{
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
//        this.hashed_pass = hashed_pass;
        this.balance = balance;
        this.acc_type = acc_type;
        this.TypeOfInterest = State;
        this.owner = owner;
        this.account_number = ++DataBase.getInstance().lastAccountNumber;
        System.out.println("created new account");
    }

    public String getHashed_pass() {
        return hashed_pass;
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

    public Double getBalance() { return balance; }

    public String getState()
    {
        if (TypeOfInterest)
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
}
