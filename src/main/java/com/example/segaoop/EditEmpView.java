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

public class EditEmpView implements Initializable {



    @FXML
    AnchorPane pane;
    @FXML
    protected Button Apply,Cancel;
    @FXML
    protected TextField FirstName,LastName,position,address;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee emp = (Employee) DataBase.getInstance().getCurrentUser();

        FirstName.setText(emp.getfirstName().toString());
        LastName.setText(emp.getlastName().toString());
        address.setText(emp.getAddress().toString());
        position.setText(emp.getPosition().toString());
    }


    @FXML
    public void SaveChanges (MouseEvent event) throws IOException {

        Employee emp = (Employee) DataBase.getInstance().getCurrentUser();

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



    }

    @FXML
    public void ReturnBackTo(MouseEvent event) throws IOException {

        pane.setVisible(false);
        HelloApplication he = new HelloApplication();
        ;
        if ((Employee) DataBase.getInstance().getCurrentUser() == DataBase.getInstance().getAdmin())
            he.changeScene("AdminViewPage.fxml");
        else
            he.changeScene("hello-view.fxml");

    }

}



