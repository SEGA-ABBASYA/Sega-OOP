package com.example.functionality;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Report extends Message implements Serializable {

    private String status;
    public Report(TextField subject, TextArea content){

        sender = (Employee) DataBase.getInstance().getCurrentUser();
        messageReadStatus = false;
        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        this.category = subject.getText().toString();
        this.content = content.getText().toString();
        this.status = "📬";

    }

    @Override
    public void setAsRead() {
        super.setAsRead();
        this.status = "📭";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
