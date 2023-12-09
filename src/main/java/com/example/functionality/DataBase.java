package com.example.functionality;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBase implements Serializable {

//    HashMap<String, Person> users;
    HashMap<String, Client> clients = new HashMap<>();
    HashMap<String, Employee> employees = new HashMap<>();

    // maps account owner username to account
    HashMap<String, Account> accounts = new HashMap<>();
    ArrayList<Transaction> transactionHistory = new ArrayList<>();
    HashMap<Integer, Bank> bankBranches = new HashMap<>();
    Person currentUser;
    Account currentAccount;
    Employee admin;
    public  int lastAccountNumber;

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
    }

    //___________________________FILE SYSTEM____________________________________
    public void loadFromFile() throws IOException {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("database.ser"));
//            if(in.available() > 0)
//            {
                instance = (DataBase) in.readObject();
                System.out.println("hi, file loaded successful");
//            }
//            else
//            {
//                //instance = (DataBase) in.readObject();
//                System.out.println("hi, file is empty");
//            }
        }
        catch (FileNotFoundException e)
        {
//            e.printStackTrace();
            File newFile = new File("database.ser");
            newFile.createNewFile();
            Client lalo = new Client("lalo", "lalo", "salamanca", "lalo123", "working", "0101111111");
            Account lolo = new Account("lalo", "lalo123", 8000, lalo);
            DataBase.getInstance().addClient(lalo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) in.close();
        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//
//        }
    }
    public void saveToFile() throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database.ser"));
            out.writeObject(instance);
            out.flush();
            out.close();
            System.out.println("object saved to file, bye ...");
        }
        catch (IOException e) {
            e.printStackTrace();
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
    public HashMap<String, Employee> getAllEmployees()
    {
        return employees;
    }

    public HashMap<String, Account> getAllAccounts() {
        return accounts;
    }

    public HashMap<Integer, Bank> getAllBankBranches() {
        return bankBranches;
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

    //__________________
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
        if(instance.admin == null)
        {
            instance.admin = new Employee("admin", "Bruce", "Wayne", "010787839379", "Gotham", "admin", "Gotham public University", 1000000, 4, 1913, "admin");
        }
        return admin;
    }

    public Account getAccount(String username)
    {
        return accounts.get(username);
    }
    public void addAccount(Account acc)
    {
        accounts.put(acc.user_name, acc);
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataBase.getInstance().loadFromFile();

        Client lalo = new Client("a", "hector", "salamanca", "a", "working", "0101111111");
        Account lolo = new Account("a", "a", 8000, lalo);
        DataBase.getInstance().addClient(lalo);
        DataBase.getInstance().addAccount(lolo);

        for (String key : DataBase.getInstance().getAllAccounts().keySet())
        {
            System.out.println("account: " + key + " " + DataBase.getInstance().getAllClients().get(key).lastName);
        }
        if(DataBase.getInstance().getAllAccounts().containsKey("a"))
            System.out.println("!!found lalo");

        DataBase.getInstance().saveToFile();
    }
}
