package com.example.segaoop;


import com.example.functionality.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;

public class EditController {



    @FXML
    AnchorPane pane;
    @FXML
    protected Button Apply,Cancel;
    @FXML
    protected TextField FirstName,FirstNameC,LastName,LastNameC,position,address,phone,phoneC,ID,accountstate,acctype,password,username;


    @FXML
    protected void SaveChanges (MouseEvent event) throws Exception {

            Employee emp = (Employee) DataBase.getInstance().getCurrentUser();

            Client clien = (Client) DataBase.getInstance().getCurrentUser();

            emp.setfirstName(this.FirstName.getText());
            emp.setlastName(this.LastName.getText());
            emp.setPosition(this.position.getText());
            emp.setAddress(this.address.getText());


            clien.setFirstName(this.FirstNameC.getText());
            clien.setLastName(this.LastNameC.getText());
            clien.setTelephone(this.phoneC.getText());
            clien.setState(this.accountstate.getText());
            clien.setId(this.ID.getText());
            HelloApplication helloApplication=new HelloApplication();

            helloApplication.changeScene("hello-view.fxml");

    }
    @FXML
    void ReturnBackTo(MouseEvent event) throws IOException{

        pane.setVisible(false);
        HelloApplication he= new HelloApplication();

        if(DataBase.getInstance().getCurrentUser() instanceof Client) {
            he.changeScene("MainPageClientController.fxml");

        }
        else {
            he.changeScene("hello-view.fxml");
        }
    }


}
