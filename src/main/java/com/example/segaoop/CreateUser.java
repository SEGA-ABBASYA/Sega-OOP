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
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;


public class CreateUser {

    @FXML
    private TextField FirstName;
    String firstName;

    @FXML
    private TextField LastName;
    String lastName;
    @FXML
    private TextField ID;
    String id;
    @FXML
    private TextField Balance;
    String balance;
    double balancee;
    @FXML
    private TextField AccountType;
    String accountType;
    @FXML
    private TextField PhoneNumber;
    String phoneNumber;

    @FXML
    private TextField password;
    String Password;

    @FXML
    private TextField confirm_password;
    String confirmPassword;

    @FXML
    private TextField UserName;
    String userName;

    @FXML
    private DatePicker JoinDatePicker;

    @FXML
    private Text ErrorText;
    @FXML
    public Button ADD;
    @FXML
    public Button cancel;

    void clear()
    {
        UserName.clear();
        confirm_password.clear();
        ID.clear();
        password.clear();
        JoinDatePicker.setValue(null);
        FirstName.clear();
        LastName.clear();
        Balance.clear();
        PhoneNumber.clear();
        AccountType.clear();
    }

        public boolean accountconventor(String accountType) {
            return accountType.contains("Saving account") || accountType.contains("saving account") || accountType.contains("Saving Account") || accountType.contains("saving Account") || accountType.contains("savingaccount") || accountType.contains("saving") || accountType.contains("Saving");
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


    @FXML
    void ClearButtonFunction(MouseEvent event) {
        clear();
    }
    @FXML
    void AddAccount(MouseEvent event) {
        if (!FirstName.getText().isEmpty())
        {
            firstName = FirstName.getText();
        }
        else
        {
            ErrorText.setText("Please Enter First Name");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!LastName.getText().isEmpty())
        {
            lastName = LastName.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Last Name");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!UserName.getText().isEmpty())
        {
            userName = UserName.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Username");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!PhoneNumber.getText().isEmpty())
        {
            phoneNumber = PhoneNumber.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Phone Number");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!password.getText().isEmpty() && !checklongpassword(password.getText()))
        {
            Password = password.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Password in a right way and not less than 8 characters");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!AccountType.getText().isEmpty())
        {
            accountType = AccountType.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Your Account Type");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!confirm_password.getText().isEmpty())
        {
            confirmPassword = confirm_password.getText();
        }
        else
        {
            ErrorText.setText("Please Enter you Confirm Password in a right way and not less than 8 characters");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!confirm_password.getText().equals(password.getText()))
        {
            ErrorText.setText("Passwords does not Match");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (JoinDatePicker.getValue()==null)
        {
            ErrorText.setText("Please Pick Join Date");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!Balance.getText().isEmpty())
        {
            balance = Balance.getText();
            balancee = Double.parseDouble(balance);
        }
        else
        {
            ErrorText.setText("Please Enter Balance");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!ID.getText().isEmpty())
        {
            id = ID.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Client ID");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (DataBase.getInstance().getAccount(userName)!=null)
        {
            ErrorText.setText("Please enter a unique username");
            ErrorText.setFill(Color.RED);
            return;
        }
        Client C = new Client(id,firstName,lastName,phoneNumber);
        if (DataBase.getInstance().getClient(id)==null)
        {
            DataBase.getInstance().addClient(C);
        }
        else
        {
            C = DataBase.getInstance().getClient(id);
        }
        Account A = new Account(userName,Password,balancee,true,accountconventor(accountType),C);
        DataBase.getInstance().addAccount(A);
        ErrorText.setFill(Color.GREEN);
        ErrorText.setText("Account Created Successfully");
        clear();
    }
        public void cancelCreate(){
            created.setVisible(false);
        // back to last scene
        }

    public boolean checklongpassword(String password) {
        return password.length() < 8;
    }
        public void AddingClient(){
            if(!fieldsNotEqual) {
                if (DataBase.getInstance().getCurrentUser() instanceof Employee) {
                    // add client
                    Client x = new Client(id, firstName, lastName, phoneNumber);
                    DataBase.getInstance().addClient(x);
                }
            }
        }



}