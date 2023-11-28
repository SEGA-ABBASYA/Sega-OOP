package com.example.segaoop;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

public class AdminMainPage {

    boolean slider = false;
    Scene mainScene;
    public AdminMainPage(Stage window) {
        window.setTitle("Sega National Bank");

        Label pageTitle = new Label("Welcome Back!, Anon.");
        pageTitle.setAlignment(Pos.CENTER);

        Button[] pageButtons = new Button[4];
        pageButtons[0] = new Button("Account");
        pageButtons[1] = new Button("Clients");
        pageButtons[2] = new Button("Employee");
        pageButtons[3] = new Button("Transaction History");

        Button menuButton = new Button("Menu");


        HBox top = new HBox(500);
        top.getChildren().addAll(menuButton,pageTitle);

        ToolBar toolBar = new ToolBar(pageButtons);
        toolBar.setOrientation(Orientation.VERTICAL);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(toolBar);
        borderPane.setTop(top);

        menuButton.setOnAction(e -> {
            if(slider)
            {
                slider = false;
            }
            else
            {
                slider = true;
            }

            slidePanel(borderPane, toolBar ,slider);

        });

        pageButtons[0].setOnAction(e -> transferCenterContent(borderPane,1));



        mainScene = new Scene(borderPane,1280,720);

        window.setScene(mainScene);
        window.show();


    }

private void slidePanel(BorderPane b,ToolBar t, boolean s)
{
    if(!s)
    {
        b.setLeft(null);
    }
    else
    {
        b.setLeft(t);
    }
}

private void transferCenterContent(BorderPane b, int i)
{
    switch(i)
    {
        case 1:
            VBox v = new VBox(15);
            Label l1 = new Label("Name:");
            Label l2 = new Label("ID:");
            Label l3 = new Label("Clearance Level:");
            v.getChildren().addAll(l1,l2,l3);
            v.setAlignment(Pos.CENTER);
            b.setCenter(v);
            break;
        case 2:
            //clients page functionality
            break;
        case 3:
            //Employee page fucntionality
            break;
        case 4:
            //Transactions
            break;
            //Coming Soon

    }
}

public Scene getScene()
{
    return mainScene;
}

}
