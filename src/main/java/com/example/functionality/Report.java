package com.example.functionality;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Report extends Message{

    public Report(TextField subject, TextArea content){

        sender = (Employee) DataBase.getInstance().getCurrentUser();
        messageReadStatus = false;
        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        this.category = subject.getText();


        this.content = content.getText();

    }

}
