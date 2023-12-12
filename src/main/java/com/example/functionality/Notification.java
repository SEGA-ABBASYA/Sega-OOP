package com.example.functionality;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Notification extends Message{


    public Notification(TableView<Account> clientList, ComboBox<String> priority, TextArea content){

        sender = (Employee) DataBase.getInstance().getCurrentUser();

        receiver = clientList.getSelectionModel().getSelectedItem();
        messageReadStatus = false;
        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        String choice = String.valueOf(priority.getSelectionModel().selectedItemProperty()).toLowerCase();

        switch(choice){
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
        this.content = content.getText();



    }

    // Comparator for sorting by date in descending order
    public static Comparator<Notification> dateComparator = Comparator
            .comparing(Notification::getDate)
            .reversed();
}
