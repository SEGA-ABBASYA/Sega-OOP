package com.example.segaoop;
import com.example.functionality.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.geometry.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;

public class NotificationBox {
    public NotificationBox() {
    }

    public Pane getNotificationBox(String sender, String content) throws IOException {
        Pane root = new Pane();
        root.prefHeight(100);
        root.prefWidth(300);
        root.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
        root.setBorder(new javafx.scene.layout.Border(
                new javafx.scene.layout.BorderStroke(
                        Color.RED, // Border color
                        javafx.scene.layout.BorderStrokeStyle.SOLID, // Border style
                        null, // CornerRadii (null means default)
                        new javafx.scene.layout.BorderWidths(2) // Border width
                )
        ));
        //root.setPadding(new Insets(50));
        //Pane root = FXMLLoader.load(getClass().getResource("com/example/segaoop/NotificationBox.fxml"));
        Label senderLabel = new Label("New Notification From " + sender);
        Text contentText = new Text(content);
        root.getChildren().addAll(senderLabel, contentText);
        return root;
    }
}
