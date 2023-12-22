package com.example.functionality;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Notification extends Message implements Serializable {


    public Notification(String priority, String content)
    {

        messageReadStatus = false;

        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = currentDateTime.format(formatter);


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

        this.content = content;
        System.out.println("this.content: " + this.content);
        System.out.println("date: " + date);
    }

    // Comparator for sorting by date in descending order
    public static Comparator<Notification> dateComparator = Comparator
            .comparing(Notification::getDate)
            .reversed();
}
