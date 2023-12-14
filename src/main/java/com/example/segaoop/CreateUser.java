package com.example.segaoop;
import com.example.functionality.Account;
import com.example.functionality.Client;
import com.example.functionality.DataBase;
import com.example.functionality.Employee;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUser {

    @FXML
    private TextField FirstName;
    String firstName = FirstName.getText();

    @FXML
    private TextField LastName;
    String lastName = LastName.getText();
    @FXML
    private TextField ID;
    String id = ID.getText();
    @FXML
    private TextField Balance;
    String balance = Balance.getText();
    double balancee = Double.parseDouble(balance);
    @FXML
    private TextField AccountType;
    String accountType = AccountType.getText();
    @FXML
    private TextField PhoneNumber;
    String phoneNumber = PhoneNumber.getText();

    @FXML
    private TextField password;
    String Password = password.getText();

    @FXML
    private TextField confirm_password;
    String confirmPassword = confirm_password.getText();

    @FXML
    private TextField UserName;
    String userName = UserName.getText();

    @FXML
    public Button ADD;

    @FXML
    public Button cancel;

        public boolean accountconventor(String accountType) {
             if (accountType.contains("Saving account") || accountType.contains("saving account") || accountType.contains("Saving Account") || accountType.contains("saving Account") || accountType.contains("savingaccount")) {
              return true;
               }
            return false;
        }

        @FXML
        private Label passworderrorLabel = new Label("Passwords are not the same.");
        @FXML
        private Label created = new Label("Created successfully.");
         private boolean fieldsNotEqual;
         private boolean flagforCreate = false;


         public void updateCreateSuccussfully(){
         created.setVisible(true);
         // call database and then back to next scene
         }

        public void cancelCreate(){
            created.setVisible(false);
        // back to last scene
        }

         public void comparePasswords() {

                passworderrorLabel.setVisible(false);

             password.textProperty().addListener((observable, oldValue, newValue) -> {
                 updateErrorLabel();
             });
             confirm_password.textProperty().addListener((observable, oldValue, newValue) -> {
                 updateErrorLabel();
             });
                System.out.println("Entered first");
        }


        public void updateErrorLabel() {
            fieldsNotEqual = !password.getText().equals(confirm_password.getText());
            passworderrorLabel.setVisible(fieldsNotEqual);
            System.out.println("Entered second");
        }


        public void AddingClient(){
            if(!fieldsNotEqual) {
                if (DataBase.getInstance().getCurrentUser() instanceof Employee) {
                    // add client
                    Client x = new Client(id, firstName, lastName, accountconventor(accountType), phoneNumber,balancee);
                    DataBase.getInstance().addClient(x.getId(), x);
                }
            }
        }

}