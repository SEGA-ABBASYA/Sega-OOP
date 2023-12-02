package com.example.segaoop;

import com.example.functionality.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.util.Callback;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class TransactionViewController implements Initializable {

    @FXML
    private TableColumn<Transaction,Integer> Balance_Column;

    @FXML
    private Label DateLable;
    @FXML
    private TableColumn<Transaction, Integer> Debit_Column;

    @FXML
    private TableColumn<Transaction, String> TransactionDate;

    @FXML
    private TableView<Transaction> TransactionTable;

    @FXML
    private DatePicker datePicker;
    ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();//save the dates from the datePicker
    @FXML //get the date from the datePicker
    void getDate(MouseEvent event) {
        DateLable.setVisible(true);
        if (!selectedDates.isEmpty()){
        observedTransactionList.clear();
        for (LocalDate date : selectedDates) {
            System.out.println(date);
        }
        if (selectedDates.size()==1)
        {
            DateLable.setText("Transactions from "+selectedDates.getFirst().toString()+".");
        }
        else if (selectedDates.getFirst().isAfter(selectedDates.getLast())) {
            System.out.println("Invalid Date.");
            DateLable.setText("Please choose an invalid date.");
        }
        else if (selectedDates.size()==2)
        {
            DateLable.setText("Transactions from "+selectedDates.getFirst().toString()+" to "+selectedDates.getLast().toString()+".");
        }

        System.out.println("num of dates is "+selectedDates.size());
        filterTransactions();
        }
        else
        {
            DateLable.setText("Please choose a date.");
        }
    }
    @FXML
    void resetDate(MouseEvent event) {
        selectedDates.clear();
        TransactionTable.setItems(TransactionList);
        DateLable.setVisible(true);
        System.out.println("date reset");
        DateLable.setText("Reset Transactions.");
    }
    private static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        Random random = new Random();
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay + 1));
        return LocalDate.ofEpochDay(randomEpochDay);
    }//generate random dates to save in the transactions
    void makeTransaction()
    {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);


        for (int i=0;i<30;i++)
        {
            LocalDate randomDate = generateRandomDate(startDate, endDate);
            Random rand = new Random();
            TransactionList.add(new Transaction(randomDate.toString(),rand.nextInt(20,1000)));
        }
    }//generate random transactions to save in the TableView
    ObservableList<Transaction> TransactionList = FXCollections.observableArrayList();
    //list for transactions
    ObservableList<Transaction> observedTransactionList = FXCollections.observableArrayList();
    //list for transactions after the filter
    void filterTransactions()
    {
        if (selectedDates.size() == 2){
            for (Transaction i:TransactionList) {
                if (i.getTransactionDate().compareTo(selectedDates.getFirst().toString()) >= 0) {
                    if (i.getTransactionDate().compareTo(selectedDates.getLast().toString()) <= 0){
                        observedTransactionList.addFirst(i);
                    }
                }
            }
        }
        else if (selectedDates.size()==1)
        {
            for (Transaction i:TransactionList) {
                if (i.getTransactionDate().compareTo(selectedDates.getFirst().toString()) >= 0) {
                        observedTransactionList.addFirst(i);
                    }
                }
        }
        TransactionTable.setItems(observedTransactionList);
    }//filter transactions from start_date to last_date
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setOnAction(event -> {
            if (selectedDates.size() > 1){
                selectedDates.clear();
            }
            selectedDates.add(datePicker.getValue());}
        );//get Start_date and End_date

        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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
        //style the datePicker

        TransactionDate.setCellValueFactory(new PropertyValueFactory<Transaction,String>("TransactionDate"));
        Debit_Column.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("Debit"));
        Balance_Column.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("Balance"));

        makeTransaction();
        TransactionTable.setItems(TransactionList);
    }
}

