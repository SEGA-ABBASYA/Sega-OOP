package com.example.functionality;

import java.util.ArrayList;

import java.util.ArrayList;

public class Client extends Person{

    //private Integer id;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }


    //private Double Balance;
    private Double Balance;
    public void setBalance(Double Balance) {this.Balance = Balance;}
    public Double getBalance() {return Balance;}



    //private String firstName;
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }



    //private String lastName;
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }


    //private String telephone;
    public void setTelephone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getTelephone() {return phoneNumber;}



    // if saving account 1 or current account 0
    private Boolean accountType;
    public void setState(Boolean accountType) {this.accountType = accountType;}
    public Boolean getState() {
        return accountType;
    }


    ArrayList<Notification> clientNotification = new ArrayList<>();
    public void addNotification(Notification newNotification) {
        this.clientNotification.add(newNotification);
    }

    public Client(String id, String firstName, String lastName, Boolean accountType , String phoneNumber , Double Balance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.Balance=Balance;
        this.id = id;
        this.accountType = accountType;
    }
}
