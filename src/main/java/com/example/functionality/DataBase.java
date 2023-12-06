package com.example.functionality;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBase implements Serializable {

    HashMap<String, Person> users;
    ArrayList<Transaction> transactionHistory;
    HashMap<Integer, Bank> bankBranches;

    //__________________________SINGLETON_________________________________________
    private static DataBase instance;

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
            System.out.println("object loaded to file, bye");
        }
    }

    public static void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            if(in.readObject() != null)
            {
                instance = (DataBase) in.readObject();
                System.out.println("hi, file loaded successful");
            }
            else
            {
                System.out.println("hi, file is empty");
            }
        }
    }

    //___________________________QUERY FUNCTIONS________________________________________
    public HashMap<String, Person> getClients() {
        return (HashMap<String, Person>) users.entrySet().stream().filter(e-> e instanceof Client).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public HashMap<String, Person> getEmployees() {
        return (HashMap<String, Person>) users.entrySet().stream().filter(e-> e instanceof Employee).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addClient(String id, Client client)
    {
        users.put(id, client);
    }
    public void removeClient(String id)
    {
        users.remove(id);
    }

    public void addEmployee(String id, Employee employee)
    {
        users.put(id, employee);
    }
    public void removeEmployee(String id)
    {
        users.remove(id);
    }

    public void addTransaction(Transaction transaction)
    {
        transactionHistory.add(transaction);
    }

    public void addBranch(Integer id ,Bank branch)
    {
        bankBranches.put(id, branch);
    }
    public void removeBranch(Integer id)
    {
        bankBranches.remove(id);
    }
}
