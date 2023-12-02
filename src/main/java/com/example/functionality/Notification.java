package com.example.functionality;
import com.example.segaoop.MainPageClientController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification implements Message{

    protected String title = null;
    protected String date = null;
    protected String urgency; // we have 3 types of notifications 1-> normal 2->important 3-> bank warning
    protected String content = null;
    protected boolean isRead = false;

    public Notification(TextField title, ChoiceBox<String> urgency, String content, boolean isRead) {
        this.title = title.getText();

        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        this.urgency = String.valueOf(urgency.getSelectionModel().selectedItemProperty());
        this.content = content;
        this.isRead = isRead;
    }

    @Override
    public void sendMessage(Client c, Notification n) {

    }
}
