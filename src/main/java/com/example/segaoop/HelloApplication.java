package com.example.segaoop;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("SEGA United Bank");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeScene (String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot (pane);
    }

    public static void main(String[] args) {
        launch();
    }
}