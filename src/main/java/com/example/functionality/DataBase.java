package com.example.functionality;
import com.example.segaoop.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase implements Serializable {
    private static DataBase instance;

    HashMap<String, Person> clients;
    HashMap<String, Person> employees;
    ArrayList<Transaction> transactionHistory;


    // singleton
    public static DataBase getInstance()
    {
        if(instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }
    //___________________________FILE SYSTEM____________________________________
    public static void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(instance);
        }
    }

    // implementation: DataBase loadedInstance = DataBase.loadFromFile("singleton.ser");
    public static DataBase loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (DataBase) in.readObject();
        }
    }

    //___________________________QUERY FUNCTIONS________________________________________
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
