package com.example.segaoop;

import com.example.functionality.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class TransactionViewController implements Initializable {

    @FXML
    private TableColumn<Transaction, String> TransactionDate;

    @FXML
    private TableColumn<Transaction, String> Type_Column;

    @FXML
    private TableColumn<Transaction, Integer> Amount_Column;

    @FXML
    private TableColumn<Transaction,Integer> Balance_Column;

    @FXML
    private TableColumn<Transaction, String> Sender_Column;

    @FXML
    private TableColumn<Transaction, String> Receiver_Column;

    @FXML
    private TableColumn<Transaction, String> TransactionID_Column;

    @FXML
    private TableColumn<Transaction, String> Branch_Column;

    @FXML
    private TableColumn<Transaction, String> Employee_Column;

    @FXML
    private Label DateLabel;

    @FXML
    private TableView<Transaction> TransactionTable;

    @FXML
    private DatePicker endDate;
    @FXML
    private DatePicker startDate;
    ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();//save the dates from the datePicker
    @FXML //get the date from the datePicker
    void getDate(MouseEvent event) {
        DateLabel.setVisible(true);
        if (!selectedDates.isEmpty()){
            observedTransactionList.clear();
            for (LocalDate date : selectedDates) {
                System.out.println(date);
            }
            if (selectedDates.size() == 1)
            {
                DateLabel.setText("Transactions from "+selectedDates.get(0).toString()+".");
            }
            else if (selectedDates.get(0).isAfter(selectedDates.get(1))) {
                System.out.println("Invalid Date.");
                DateLabel.setText("Please choose an invalid date.");
            }
            else if (selectedDates.size()==2)
            {
                DateLabel.setText("Transactions from "+selectedDates.get(0).toString()+" to "+selectedDates.get(1).toString()+".");
            }

            System.out.println("num of dates is "+selectedDates.size());
            filterTransactions();
        }
        else
        {
            DateLabel.setText("Please choose a date.");
        }
    }
    @FXML
    void resetDate(MouseEvent event) {
        endDate.setDisable(true);
        selectedDates.clear();
        TransactionTable.setItems(TransactionList);
        DateLabel.setVisible(true);
        System.out.println("date reset");
        DateLabel.setText("Reset Transactions.");
    }
    /*private static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        Random random = new Random();
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay + 1));
        return LocalDate.ofEpochDay(randomEpochDay);
    }*///generate random dates to save in the transactions
    void makeTransaction()
    {
        //LocalDate startDate = LocalDate.of(2020, 1, 1);
        //LocalDate endDate = LocalDate.of(2023, 12, 31);

        if (DataBase.getInstance().getCurrentUser() instanceof Employee)
         {
            for (Transaction x : DataBase.getInstance().getTransactionHistory()) {
                TransactionList.add(x);
            }
        }
        else if (DataBase.getInstance().getCurrentUser() instanceof Client)
        {
            for (Transaction x : DataBase.getInstance().getTransactionHistory()) {
                if (x.getReceiver().equals(((Client) DataBase.getInstance().getCurrentUser()).getId())||x.getSender().equals(((Client) DataBase.getInstance().getCurrentUser()).getId())){
                TransactionList.add(x);
                }
            }
        }
//        for (int i=0;i<30;i++)
//        {
//            LocalDate randomDate = generateRandomDate(startDate, endDate);
//            Random rand = new Random();
//            int rand_amount = rand.nextInt(25,2795);
//            TransactionList.add(new Transaction(randomDate.toString(),rand_amount-rand_amount%5));
//        }
    }//generate random transactions to save in the TableView
    ObservableList<Transaction> TransactionList = FXCollections.observableArrayList();
    //list for transactions
    ObservableList<Transaction> observedTransactionList = FXCollections.observableArrayList();
    //list for transactions after the filter


    void filterTransactions()
    {
        if (selectedDates.size() == 2){
            TransactionList.stream()
                    .filter(tr -> tr.getTransactionDate().substring(0,10).compareTo(selectedDates.get(0).toString())>=0)
                    .filter(tr -> tr.getTransactionDate().substring(0,10).compareTo(selectedDates.get(1).toString())<=0)
                    .forEach(tr -> observedTransactionList.add(tr));

            /*for (Transaction i:TransactionList) {
                if (i.getTransactionDate().compareTo(selectedDates.get(0).toString()) >= 0) {
                    if (i.getTransactionDate().compareTo(selectedDates.get(1).toString()) <= 0){
                        observedTransactionList.add(i);
                    }
                }
            }*/
        }
        else if (selectedDates.size()==1)
        {
            TransactionList.stream()
                    .filter(tr -> tr.getTransactionDate().substring(0,10).compareTo(selectedDates.get(0).toString())>=0)
                    .forEach(tr -> observedTransactionList.add(tr));

            /*for (Transaction i:TransactionList) {
                if (i.getTransactionDate().compareTo(selectedDates.get(0).toString()) >= 0) {
                        observedTransactionList.add(i);
                    }
                }*/
        }
        TransactionTable.setItems(observedTransactionList);
    }//filter transactions from start_date to last_date
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDate.setOnAction(event ->
        {
            if (selectedDates.isEmpty()) {
                selectedDates.add(0, startDate.getValue());
            }
            else
            {
                selectedDates.set(0, startDate.getValue());
            }
            endDate.setDisable(false);
        });//get Start_date.

        endDate.setOnAction(event -> {
            if (selectedDates.size() == 1) {
                selectedDates.add(1, endDate.getValue());
            }
            else
            {
                selectedDates.set(1, endDate.getValue());
            }
        });//get End_date.

       /*datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        boolean alreadySelected = selectedDates.contains(item);
                        setDisable(alreadySelected);
                        setStyle(alreadySelected ? "-fx-background-color: #029cde;" : "");
                    }
                };
            }
        });
        //style the datePicker*/


        TransactionDate.setCellValueFactory(new PropertyValueFactory<>("TransactionDate"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Amount_Column.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Balance_Column.setCellValueFactory(new PropertyValueFactory<>("Balance"));
        Sender_Column.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        Receiver_Column.setCellValueFactory(new PropertyValueFactory<>("Receiver"));
        TransactionID_Column.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
        Branch_Column.setCellValueFactory(new PropertyValueFactory<>("Branch"));
        Employee_Column.setCellValueFactory(new PropertyValueFactory<>("Employee"));

        makeTransaction();
        TransactionList.sort(new TransactionCompare());
        TransactionTable.setItems(TransactionList);
    }

    @FXML
    private void refreshTransactions()
    {
        TransactionList.clear();

        //TransactionList.addAll(DataBase.getInstance().getTransactionHistory());
        if (DataBase.getInstance().getCurrentUser() instanceof Employee)
        {
            for (Transaction x : DataBase.getInstance().getTransactionHistory()) {
                TransactionList.add(x);
            }
        }
        else if (DataBase.getInstance().getCurrentUser() instanceof Client)
        {
            for (Transaction x : DataBase.getInstance().getTransactionHistory()) {
                if (x.getReceiver().equals(((Client) DataBase.getInstance().getCurrentUser()).getId())||x.getSender().equals(((Client) DataBase.getInstance().getCurrentUser()).getId())){
                    TransactionList.add(x);
                }
            }
        }

    }
}

