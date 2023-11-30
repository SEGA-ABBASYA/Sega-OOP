package com.example.segaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

import javafx.util.Callback;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class TransactionView implements Initializable {

    @FXML
    private TableColumn<UserTransaction,Integer> Balance_Column;

    @FXML
    private TableColumn<UserTransaction, Integer> Debit_Column;

    @FXML
    private TableColumn<UserTransaction, String> TransactionDate;

    @FXML
    private TableView<UserTransaction> TransactionTable;

    @FXML
    private DatePicker datePicker;
    ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();//save the dates from the datePicker
    @FXML //get the date from the datePicker
    void getDate(MouseEvent event) {
        for (LocalDate date : selectedDates) {
            System.out.println(date);
        }
    filterTransactions();
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


        for (int i=0;i<20;i++)
        {
            LocalDate randomDate = generateRandomDate(startDate, endDate);
            Random rand = new Random();
            TransactionList.add(new UserTransaction(randomDate.toString(),rand.nextInt(20,1000)));
        }
    }//generate random transactions to save in the TableView
    ObservableList<UserTransaction> observedTransactionList = FXCollections.observableArrayList();

    ObservableList<UserTransaction> TransactionList = FXCollections.observableArrayList(
      new UserTransaction("2021-01-07",20),
            new UserTransaction("2021-03-07",50),
      new UserTransaction("2021-02-07",70),
      new UserTransaction("2021-04-07",30)

    );//list for transactions

    void filterTransactions()
    {
        if (selectedDates.size() > 0){
            for (UserTransaction i:TransactionList) {
                if (i.getTransactionDate().compareTo(selectedDates.getFirst().toString()) >= 0) {
                    if (i.getTransactionDate().compareTo(selectedDates.getLast().toString()) <= 0){
                        observedTransactionList.addFirst(i);
                    }
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

        TransactionDate.setCellValueFactory(new PropertyValueFactory<UserTransaction,String>("TransactionDate"));
        Debit_Column.setCellValueFactory(new PropertyValueFactory<UserTransaction,Integer>("Debit"));
        Balance_Column.setCellValueFactory(new PropertyValueFactory<UserTransaction,Integer>("Balance"));

        makeTransaction();





    }
}

