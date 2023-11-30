package com.example.segaoop;

public class Client{
    private Integer ID;
    public void setID(Integer ID) {
        this.ID = ID;
    }
    public Integer getID() {
        return ID;
    }

    private String FirstName;
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getFirstName() {
        return FirstName;
    }

    private String LastName;
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getLastName() {
        return LastName;
    }

    private Integer TelephoneNumber;
    public Integer getTelephoneNumber() {
        return TelephoneNumber;
    }
    public void setTelephoneNumber(Integer telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    private Integer AccountNumber;
    public Integer getAccountNumber() {
        return AccountNumber;
    }
    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    private String State;
    public String getState() {
        return State;
    }
    public void setState(String state) {
        State = state;
    }

    private String Type;
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }

    private String Username;
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }

    private Float Balance;
    public Float getBalance() {
        return Balance;
    }
    public void setBalance(Float balance) {
        Balance = balance;
    }

    private String Password;
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public Client(Float balance,Integer ID,Integer AccountNumber,String FirstName,String LastName,String Username,String Password,String Type,String State,Integer TelephoneNumber)
    {
        Balance = balance;
        this.ID = ID;
        this.AccountNumber = AccountNumber;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.TelephoneNumber = TelephoneNumber;
        this.Username = Username;
        this.Password = Password;
        this.Type = Type;
        this.State = State;
    }
}
