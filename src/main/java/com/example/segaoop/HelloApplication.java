package com.example.segaoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminViewPage.fxml"));
        stage.setTitle("Sega Bank");
        stage.setScene(new Scene(root,1280,720));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}