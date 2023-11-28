package com.example.segaoop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPageClientScene implements EventHandler<ActionEvent> {

    private BorderPane layout = new BorderPane();

    // borders____________________________
    private HBox buttonsBar = new HBox();
    private HBox topBar = new HBox();
    //____________________________________

    // centers____________________________
    private VBox centerTransMoneyVBox;
    //____________________________________

    private Scene scene;
    private Stage stage;
    private double buttonWidth = 160.0;
    private Button account_b, transferMoney_b, transactionHistory_b,takeDeposit_b;
    public MainPageClientScene(Stage stage)
    {
        //____________________________________BORDERS____________________________________________

        // BUTTONS
            // initializing
        this.stage = stage;
        account_b = new Button("Account");
        transferMoney_b = new Button("Transfer Money");
        transactionHistory_b= new Button("Transaction History");
        takeDeposit_b = new Button("Take Deposit");
            //setting width
        account_b.setPrefWidth(buttonWidth);
        transferMoney_b.setPrefWidth(buttonWidth);
        transactionHistory_b.setPrefWidth(buttonWidth);
        takeDeposit_b.setPrefWidth(buttonWidth);
            // adding to vbox
        buttonsBar.setPadding(new javafx.geometry.Insets(10));
        buttonsBar.setSpacing(20);
        buttonsBar.setAlignment(Pos.CENTER);
        buttonsBar.getChildren().addAll(account_b, transferMoney_b, transactionHistory_b, takeDeposit_b);

        // TOP BAR with label
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(new Label("HOME"));

        //______________________________DIFFERENT CENTERS_______________________________________
        // Transfer Money
        Label transMoneyLabel = new Label("ID: ");
        transMoneyLabel.setPrefWidth(buttonWidth);
        TextField transferToInput = new TextField();
        HBox transHBox = new HBox(transMoneyLabel, transferToInput);
        transHBox.setAlignment(Pos.CENTER);
        transHBox.setSpacing(20);

        Label amountLabel = new Label("Amount: ");
        amountLabel.setPrefWidth(buttonWidth);
        TextField amountInput = new TextField();
        HBox amountHBox = new HBox(amountLabel, amountInput);
        amountHBox.setAlignment(Pos.CENTER);
        amountHBox.setSpacing(20);

        Label passwordLabel = new Label("Password: ");
        passwordLabel.setPrefWidth(buttonWidth);
        TextField passwordInput = new TextField();
        HBox passwordHBox = new HBox(passwordLabel, passwordInput);
        passwordHBox.setAlignment(Pos.CENTER);
        passwordHBox.setSpacing(20);

        centerTransMoneyVBox = new VBox(transHBox, amountHBox, passwordHBox);
        centerTransMoneyVBox.setAlignment(Pos.CENTER);
        centerTransMoneyVBox.setSpacing(20);

        //____________________________________LAYOUT____________________________________________
        layout.setTop(topBar);
        layout.setBottom(buttonsBar);

        transferMoney_b.setOnAction(e -> {layout.setCenter(centerTransMoneyVBox);});
        //layout.setCenter(centerTransMoneyVBox);
        //layout.setCenter();
        //scene = new Scene(layout, 1280, 720);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == account_b)
        {

        }
        if(actionEvent.getSource() == transferMoney_b)
        {
        }
        if(actionEvent.getSource() == transactionHistory_b)
        {

        }
        if(actionEvent.getSource() == takeDeposit_b)
        {

        }
    }

    public Scene getScene() {
        scene = new Scene(layout, 1280, 720);
        return scene;
    }
}
