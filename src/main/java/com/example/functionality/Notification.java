package com.example.functionality;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification implements Message{

    protected String title = null;
    protected String date = null;
    protected String urgency;
    protected String content = null;
    protected boolean isRead = false;

    public Notification(TextField title, ChoiceBox<String> urgency, TextField content) {
        this.title = title.getText();

        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = currentDateTime.format(formatter);

        String choice = String.valueOf(urgency.getSelectionModel().selectedItemProperty());

        switch(choice.toLowerCase()){
            case "normal":
                this.urgency = "Normal";
                break;
            case "important":
                this.urgency = "Important";
                break;
            case "warning":
                this.urgency = "Warning";
                break;
        }
        this.content = content.getText();
        this.isRead = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    @Override
    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public void sendMessage(Person p , Message m)
    {
        Client c = (Client) p;

        c.addNotification((Notification) m);
    }

}
