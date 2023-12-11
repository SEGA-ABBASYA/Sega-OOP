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
    protected TextField FirstName,LastName,position,address,phone;



    @FXML
    protected void SaveChanges (MouseEvent event) throws Exception {

        HelloApplication he= new HelloApplication();

        if (DataBase.getInstance().getCurrentUser() instanceof Client){

            Client clien = (Client) DataBase.getInstance().getCurrentUser();

            clien.setFirstName(this.FirstName.getText());
            clien.setLastName(this.LastName.getText());
            clien.setTelephone(this.phone.getText());

            position.setVisible(false);
            address.setVisible(false);

            he.changeScene("MainPageClientController.fxml");
        }

        if (DataBase.getInstance().getCurrentUser() instanceof Employee){

            Employee emp = (Employee) DataBase.getInstance().getCurrentUser();

            emp.setfirstName(this.FirstName.getText());
            emp.setfirstName(this.LastName.getText());
            emp.setPosition(this.position.getText());
            emp.setAddress(this.address.getText());

            phone.setVisible(false);

            he.changeScene("hello-view.fxml");
        }

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
