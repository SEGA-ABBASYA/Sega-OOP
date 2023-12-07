package com.example.functionality;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBase implements Serializable {

//    HashMap<String, Person> users;
    HashMap<String, Client> clients;
    HashMap<String, Employee> employees;

    // maps account owner username to account
    HashMap<String, Account> accounts;
    ArrayList<Transaction> transactionHistory;
    HashMap<Integer, Bank> bankBranches;
    Person currentUser;
    Account currentAccount;
    Employee admin;
    public int lastAccountNumber;

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

    private DataBase() {
        lastAccountNumber = 100;
        getInstance().admin = new Employee("admin", "Bruce", "Wayne", "010787839379", "Gotham", "admin", "Gotham public University", 1000000, 4, 1913);
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
//    public HashMap<String, Person> getAllClients() {
//        return (HashMap<String, Person>) users.entrySet().stream().filter(e-> e instanceof Client).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
//    public HashMap<String, Person> getAllEmployees() {
//        return (HashMap<String, Person>) users.entrySet().stream().filter(e-> e instanceof Employee).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }

    public HashMap<String, Client> getAllClients() {
        return clients;
    }
    public HashMap<String, Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(String id)
    {
        return employees.get(id);
    }
    public Client getClient(String id)
    {
        return clients.get(id);
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addClient(Client client)
    {
        clients.put(client.getId(), client);
    }
    public void removeClient(String id)
    {
        clients.remove(id);
    }

    public void addEmployee(String id, Employee employee)
    {
        employees.put(employee.getID(), employee);
    }
    public void removeEmployee(String id)
    {
        employees.remove(id);
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

    public Person getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Employee getAdmin() {
        return admin;
    }
    public Account getAccount(String username)
    {
        return accounts.get(username);
    }
}
