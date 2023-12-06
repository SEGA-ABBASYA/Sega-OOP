package com.example.segaoop;

import com.example.functionality.DataBase;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        DataBase.loadFromFile("database.ser");
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("TransactionView.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("SEGA ABBASYA UNITED BANK");
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        System.out.println("good bye");
        DataBase.saveToFile("database.ser");
    }


    @FXML
    public void changeScene(String fxml) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}