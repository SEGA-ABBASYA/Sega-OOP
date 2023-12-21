package com.example.segaoop;
import com.example.functionality.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.geometry.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Objects;

public class NotificationBox {
    public NotificationBox() {
    }

    public HBox getNotificationBox(String content, String urgency) throws IOException {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5 ,5,5,10));
        hBox.setSpacing(10);

        Text text = new Text(content);
        Text urgency_text = new Text(urgency + "\n");

        text.setFont(new Font(22));
        urgency_text.setFont(new Font(22));

        //if(Objects.equals(urgency, "Important"))
        System.out.println("urgency:   " + urgency);
        if(urgency.equalsIgnoreCase("Important"))
            urgency_text.setFill(Color.ORANGE);
        else if(urgency.equalsIgnoreCase("Warning"))
            urgency_text.setFill(Color.RED);
        else {
            urgency_text.setText("");
            System.out.println("fuck oracle forms");
        }

        TextFlow textFlow = new TextFlow(urgency_text, text);

        textFlow.setStyle("-fx-color : rgb(239,242,255); " +
                "-fx-background-color: rgb(15,124,242);" +
                "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5,10,5,10));
        text.setFill(Color.color(0.934,0.945,0.996));

        hBox.getChildren().add(textFlow);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Label dt = new Label();
        dt.setText(formattedDateTime);
        dt.setTextFill(Color.WHITE);

        hBox.getChildren().add(dt);
        return hBox;
    }
}
