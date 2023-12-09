package com.example.functionality;

import java.io.Serializable;

public class Bank implements Serializable  {
    protected int branchID;
    protected String address;
    public static int lastBranchID=99;
    public Bank(String address) {
        branchID = lastBranchID+1;
        lastBranchID++;
        this.address = address;
    }

    public int getBranchID() {
        return branchID;
    }

    public String getAddress() {
        return address;
    }
}
