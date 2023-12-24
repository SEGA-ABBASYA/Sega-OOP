package com.example.functionality;

import java.io.Serializable;
import java.util.ArrayList;


public class Client extends Person implements Serializable {
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

    @Override
    public void showContent() {
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(telephone);
        System.out.println(id);
    }

    public Client(String id, String firstName, String lastName, String telephone)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        System.out.println("created new client");
    }
}
