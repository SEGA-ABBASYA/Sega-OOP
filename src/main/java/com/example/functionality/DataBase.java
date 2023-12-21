package com.example.functionality;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class DataBase implements Serializable {

//    HashMap<String, Person> users;
    HashMap<String, Client> clients = new HashMap<>();
    HashMap<String, Employee> employees = new HashMap<>();

    // maps account owner username to account
    HashMap<String, Account> accounts = new HashMap<>();
    ArrayList<Transaction> transactionHistory = new ArrayList<>();
    ArrayList<Report> sentReports = new ArrayList<>();
    HashMap<Integer, Bank> bankBranches = new HashMap<>();
    Person currentUser;
    Report selectedReport;
    Account currentAccount;
    Employee admin;
    public int lastAccountNumber;
    public int lastTransactionID;

    public String getUsernameforedit() {
        return usernameforedit;
    }

    public void setUsernameforedit(String usernameforedit) {
        this.usernameforedit = usernameforedit;
    }

    public String usernameforedit;

    public void setIdforedit(Client idforedit) {
        this.idforedit = idforedit;
    }

    public Client getIdforedit() {
        return idforedit;
    }

    public Client idforedit;



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
        lastTransactionID = 100;
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
            Client lalo = new Client("lalo", "lalo", "salamanca","0101111111");
            Account lolo = new Account("lalo", "lalo123", 8000, false,true,lalo);
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

    public ArrayList<Report> getSentReports() {return sentReports;}

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
    public void addReport(Report report) {sentReports.add(report);}

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
    public Report getSelectedReport(){return selectedReport;}

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
    public void setSelectedReport(Report selectedReport) {this.selectedReport = selectedReport;}

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

        Client lalo = new Client("hector", "hector", "salamanca", "0101111111");
        Account lolo = new Account("hector", "a", 8000, true,true,lalo);
        DataBase.getInstance().addClient(lalo);
        DataBase.getInstance().addAccount(lolo);

        Client tuco = new Client("tuco", "tuco", "salamanca", "0101111111");
        Account toco = new Account("tuco", "a", 8000,true,true, tuco);
        DataBase.getInstance().addClient(tuco);
        DataBase.getInstance().addAccount(toco);

        Employee em = new Employee("gus", "Gustavo", "Fring", "0101029506", "Los Pllos Hermanos", "normal", "Albuquerque University", 20000,  4 , 2027, "a");
        DataBase.getInstance().addEmployee(em.getID() ,em);

        /*for (int i=0;i<30;i++)
        {
            LocalDate startDate = LocalDate.of(2020, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 12, 31);
            Random random = new Random();
            long startEpochDay = startDate.toEpochDay();
            long endEpochDay = endDate.toEpochDay();
            long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay + 1));

            LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
            Random rand = new Random();
            int rand_amount = rand.nextInt(25,2795);
            instance.addTransaction(new Transaction(randomDate.toString(),rand_amount-rand_amount%5));
        }*/

        for(Transaction x : DataBase.getInstance().getTransactionHistory())
        {
            System.out.println("date: " + x.getTransactionDate());
        }

        for (String key : DataBase.getInstance().getAllAccounts().keySet())
        {
            System.out.println("account: " + key + " " + DataBase.getInstance().getAllClients().get(key).lastName);
        }
        if(DataBase.getInstance().getAllAccounts().containsKey("a"))
            System.out.println("!!found lalo");

        for(Report r : DataBase.getInstance().getSentReports())
            System.out.println(r.getCategory());

        DataBase.getInstance().saveToFile();
    }
}
