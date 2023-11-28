package com.example.segaoop;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new AdminMainPage(stage).getScene());
        stage.setResizable(false);
        stage.show();
    }

    Stage mainFuncStage = new Stage();

    public static void main(String[] args) {

        launch(args);
    }
}