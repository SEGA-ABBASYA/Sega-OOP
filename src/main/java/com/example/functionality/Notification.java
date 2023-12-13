package com.example.functionality;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Notification extends Message implements Serializable {


//    public Notification(TableView<Account> clientList, ComboBox<String> priority, TextArea content)
    public Notification(Account receiverAccount, String priority, String content)
    {

        sender = (Employee) DataBase.getInstance().getCurrentUser();

        //receiver = receiverAccount.getSelectionModel().getSelectedItem();
        this.receiver = receiverAccount;

        messageReadStatus = false;
        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        //String choice = String.valueOf(priority.getSelectionModel().selectedItemProperty()).toLowerCase();

        switch(priority.toLowerCase()){
            case "normal":
                this.category = "Normal";
                break;
            case "important":
                this.category = "Important";
                break;
            case "warning":
                this.category = "Warning";
                break;
        }
//        this.content = content.getText().toString();
        this.content = content;
    }

    // Comparator for sorting by date in descending order
    public static Comparator<Notification> dateComparator = Comparator
            .comparing(Notification::getDate)
            .reversed();
}
