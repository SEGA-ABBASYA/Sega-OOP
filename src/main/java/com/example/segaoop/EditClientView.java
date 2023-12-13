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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;

public class EditClientView implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    Button apply,cancel;
    @FXML
    TextField firstname,lastname,number;


    @FXML
    protected void SaveChanges (MouseEvent event) throws IOException {

        Client client = (Client) DataBase.getInstance().getCurrentUser();


        if(!this.firstname.getText().isEmpty()) {
            client.setFirstName(this.firstname.getText().toString());
        }
        if(!this.lastname.getText().isEmpty()) {
            client.setLastName(this.lastname.getText().toString());
        }
        if(!this.number.getText().isEmpty()) {
            client.setTelephone(this.number.getText().toString());
        }

        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScene("MainPageClientController.fxml");

    }

    @FXML
    void ReturnBackTo(MouseEvent event) throws IOException {

        pane.setVisible(false);
        HelloApplication he = new HelloApplication();

        he.changeScene("MainPageClientController.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = (Client) DataBase.getInstance().getCurrentUser();

        firstname.setText(client.getFirstName().toString());
        lastname.setText(client.getLastName().toString());
        number.setText(client.getTelephone().toString());
    }
}
