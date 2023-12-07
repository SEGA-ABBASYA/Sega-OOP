package com.example.functionality;

public class Account {
    protected String user_name;
    private int account_number;
    Client owner;

    // The real pass taken from pass text field
    private String pass;

    protected String hashed_pass;

    protected double balance;

    // The account is saving (1) or current (0)
    protected boolean acc_type;

    // (0) if monthly interest, (1) if yearly
    protected boolean TypeOfInterest;

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

    public void update_balance(boolean operation, double value) throws MoneyExceptions
    {
        if (value > balance && !operation)
        {
            throw new MoneyExceptions(value);
        }
        else if (operation)
        {
            balance += value;
        }
        else {
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

    public Account(String user_name, String pass, String hashed_pass, double balance, Client owner) {
        this.user_name = user_name;
        this.pass = pass;
        this.hashed_pass = hashed_pass;
        this.balance = balance;
        this.owner = owner;
        this.account_number = ++DataBase.getInstance().lastAccountNumber;
    }

    public String getHashed_pass() {
        return hashed_pass;
    }

    public int getAccount_number() {
        return account_number;
    }

    public Client getOwner() {
        return owner;
    }
}
