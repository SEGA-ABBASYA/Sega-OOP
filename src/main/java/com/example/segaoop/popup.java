package com.example.segaoop;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class popup  {

    public static void display(String title , String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label ();
        label.setText(message);
        Button closedbutton = new Button("Closed the window");
        closedbutton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label ,closedbutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
