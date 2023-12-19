package com.example.functionality;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Report extends Message implements Serializable {

    public Report(TextField subject, TextArea content){

        sender = (Employee) DataBase.getInstance().getCurrentUser();
        messageReadStatus = false;

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        this.category = subject.getText().toString();


        this.content = content.getText().toString();

    }

}
