package com.example.functionality;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Report implements Message{

    protected String title = null;
    protected String date = null;
    protected String urgency;
    protected String content = null;
    protected boolean isRead = false;

    public Report(ChoiceBox<String> title, TextField content) {

        String choice = String.valueOf(title.getSelectionModel().selectedItemProperty());
        switch(choice.toLowerCase()){
            case "new clients list":
                this.title = "New Client List";
                break;
            case "exited client prediction report":
                this.title = "Exited Client Prediction Report";
                break;
            case "latest loans amount":
                this.title = "Latest Loans Amount Report";
                break;
        }

        // Get the current system date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = currentDateTime.format(formatter);


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
        // ! implementation is commented until there is an employee class
        //Employee e = (Employee) p;

        //e.addNotification((Report)m);
    }
}
