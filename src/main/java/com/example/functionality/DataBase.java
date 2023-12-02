package com.example.functionality;
import com.example.segaoop.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private static DataBase instance;

    HashMap<String, Person> clients;
    HashMap<String, Person> employees;
    ArrayList<Transaction> transactionHistory;

    private DataBase() {

    }

    // singleton
    public static DataBase getInstance()
    {
        if(instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }

    public HashMap<String, Person> getClients() {
        return clients;
    }
    public HashMap<String, Person> getEmployees() {
        return employees;
    }
    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addClient(String id, Person client)
    {
        clients.put(id, client);
    }
    public void removeClient(String id)
    {
        clients.remove(id);
    }

    public void addEmployee(String id, Person employee)
    {
        employees.put(id, employee);
    }
    public void removeEmployee(String id)
    {
        employees.remove(id);
    }

    public void addTransaction(Transaction transaction)
    {
        transactionHistory.add(transaction);
    }
}
