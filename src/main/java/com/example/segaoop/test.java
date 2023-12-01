package com.example.segaoop;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
public class test extends Application {


    Stage window;
    Scene scene1,scene2;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Label label1 = new Label("عليا و عليك");
        Button button1 = new Button("دوس عليا");
        button1.setOnAction( e -> window.setScene(scene2));

        // Layout 1 - Children are laid out in vertical Column.
        StackPane layout1 = new StackPane();
        layout1.getChildren().addAll(label1,button1);
        scene1 = new Scene(layout1,1280,720);

        // Button 2
        Button button2 = new Button("هحححححح");
        button2.setOnAction( e -> popup.display("title of window", "Wow awesome"));

        // Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2,1280,720);

        // showing
        window.setScene(scene1);
        window.setTitle("بنك المتحده");
        window.show();

    }

}
