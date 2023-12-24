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

    public void setIdforEmpEdit(String idforEmpEdit) {
        this.idforEmpEdit = idforEmpEdit;
    }

    public String getIdforEmpEdit() {
        return idforEmpEdit;
    }

    public String idforEmpEdit;




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

                instance = (DataBase) in.readObject();
                System.out.println("hi, file loaded successful");

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

        //====client 1 with 2 acc======
        Client lalo1 = new Client("525565827", "Bobette ", "Egdal ", "3376256148");

        Account lolo1 = new Account("BobetteE", "12345", 8000, true,true,lalo1);

        Account lolo2 = new Account("BobetteE2", "12345", 8000, false,true,lalo1);
        DataBase.getInstance().addClient(lalo1);
        DataBase.getInstance().addAccount(lolo1);
        DataBase.getInstance().addAccount(lolo2);
//======client 2 with 2 acc============
        Client jesse = new Client("525342827 ", "jesse ", "pinkman ", "6138348166");

        Account jesse1 = new Account("jesseP", "mrwhite", 8000, true,true,jesse);

        Account jesse2 = new Account("jesseP", "science", 8000, false,true,jesse);
        DataBase.getInstance().addClient(jesse);
        DataBase.getInstance().addAccount(jesse1);
        DataBase.getInstance().addAccount(jesse2);

//=========client with acc 1=========

        Client walter = new Client("791723156 ", "walter", "white ", "5038197019");

        Account walter1 = new Account("walterW", "heisenberg", 600000, true,true,walter);

        DataBase.getInstance().addClient(walter);
        DataBase.getInstance().addAccount(walter1);
//==========client with 1 acc 2===
        Client hank = new Client("147631496 ", "hank ", "shrader ", "5853319665");

        Account hank1 = new Account("hankS", "dea", 6000, true,true,hank);

        DataBase.getInstance().addClient(hank);
        DataBase.getInstance().addAccount(hank1);

        Employee em = new Employee("gus", "Gustavo", "Fring", "0101029506", "Los Pllos Hermanos", "normal", "Albuquerque University", 20000,  4 , 2027, "a");
        Employee em1 = new Employee("saulGM", "Saul", "Goodman", "0108539506", "Albuquerque", "normal", "Harvard University", 20000,  4 , 2025, "bettercallsaul");

        Employee em2 = new Employee("MikeE", "Mike", "Ehrmantraut", "0121110026", "Los Pllos Hermanos", "normal", "New Mexico University", 70000,  4 , 1997, "gun");

        DataBase.getInstance().addEmployee(em.getID() ,em);
        DataBase.getInstance().addEmployee(em1.getID() ,em1);
        DataBase.getInstance().addEmployee(em2.getID() ,em2);

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

        if(DataBase.getInstance().getAllAccounts().containsKey("a"))
            System.out.println("!!found lalo");

        for(Report r : DataBase.getInstance().getSentReports())
            System.out.println(r.getCategory());

        DataBase.getInstance().saveToFile();
    }
}
