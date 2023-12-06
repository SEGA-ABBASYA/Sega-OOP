package com.example.functionality;


import java.util.ArrayList;

public class Client extends Person{
    //private Integer id;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

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
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // PLACE HOLDER FOR ACCOUNTS ARRAY

    private String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    private String role;
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    //    private Float balance;

    private String passwordHash;
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    ArrayList<Notification> clientNotification = new ArrayList<>();

    public void addNotification(Notification newNotification) {
        this.clientNotification.add(newNotification);
    }

    public Client(String id, String firstName, String lastName, String passwordHash, String role, String state, String telephone)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.passwordHash = passwordHash;
        this.role = role;
        this.state = state;
    }
}
