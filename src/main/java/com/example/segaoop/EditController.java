package com.example.segaoop;


import com.example.functionality.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;

public class EditController implements Initializable {



    @FXML
    AnchorPane pane;
    @FXML
    protected Button Apply,Cancel;
    @FXML
    protected TextField FirstName,FirstNameC,LastName,LastNameC,position,address,phoneC,ID,accountstate,acctype,password,username;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee emp = (Employee) DataBase.getInstance().getCurrentUser();

        FirstName.setText(emp.getfirstName().toString());
        LastName.setText(emp.getlastName().toString());
        address.setText(emp.getAddress().toString());
        position.setText(emp.getPosition().toString());
    }

   /* void getClient(MouseEvent event)  {

        Client client = DataBase.getInstance().getClient(this.ID.getText().toString());

        FirstNameC.setText(client.getFirstName().toString());
        LastNameC.setText(client.getLastName().toString());
        phoneC.setText(client.getTelephone().toString());
        accountstate.setText(client.getState().toString());


    }*/

    @FXML
    public void SaveChanges (MouseEvent event) throws IOException {

        Employee emp = (Employee) DataBase.getInstance().getCurrentUser();

        Client client = DataBase.getInstance().getClient(this.ID.getText().toString());


        if(!this.FirstName.getText().isEmpty()) {
            emp.setfirstName(this.FirstName.getText().toString());
        }
        if(!this.LastName.getText().isEmpty()) {
            emp.setlastName(this.LastName.getText().toString());
        }
        if(!this.position.getText().isEmpty()) {
            emp.setPosition(this.position.getText().toString());
        }
        if(!this.address.getText().isEmpty()) {
            emp.setAddress(this.address.getText().toString());
        }



        if(!this.FirstNameC.getText().isEmpty()) {
            client.setFirstName(this.FirstNameC.getText().toString());
        }
        if(!this.LastNameC.getText().isEmpty()) {
            client.setLastName(this.LastNameC.getText().toString());
        }
        if(!this.phoneC.getText().isEmpty()) {
            client.setTelephone(this.phoneC.getText().toString());
        }
        if(!username.getText().isEmpty()) {

            Account acc = DataBase.getInstance().getAccount(this.username.getText().toString());

            if(!this.accountstate.getText().isEmpty()) {
                if (this.accountstate.getText().equals("Active"))
                    acc.setState(true);
                else if (this.accountstate.getText().equals("Inactive"))
                    acc.setState(false);
            }
            if (!password.getText().isEmpty()){
                acc.setPass(password.getText().toString());
            }
            if (!acctype.getText().isEmpty() && (acctype.getText().equals("Saving") || acctype.getText().equals("saving"))) {
                acc.acc_type = true;
            } else
                acc.acc_type = false;
        }
    }
    @FXML
    public void ReturnBackTo(MouseEvent event) throws IOException {

        pane.setVisible(false);
        HelloApplication he = new HelloApplication();

        he.changeScene("hello-view.fxml");
    }



}



