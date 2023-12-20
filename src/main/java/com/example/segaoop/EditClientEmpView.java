package com.example.segaoop;

import com.example.functionality.Account;
import com.example.functionality.Client;
import com.example.functionality.DataBase;
import com.example.functionality.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditClientEmpView implements Initializable {

    @FXML
    private Button Apply;

    @FXML
    private Button Cancel;

    @FXML
    private TextField FirstNameC;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastNameC;

    @FXML
    private TextField accountstate;

    @FXML
    private TextField acctype;
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneC;

    @FXML
    private TextField username;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Account acc = DataBase.getInstance().getAccount(DataBase.getInstance().getUsernameforedit());
        Client client = DataBase.getInstance().getClient(DataBase.getInstance().getIdforedit().getId());
        FirstNameC.setText(client.getFirstName().toString());
        LastNameC.setText(client.getLastName().toString());
        phoneC.setText(client.getTelephone().toString());
        ID.setText(client.getId().toString());
        username.setText(acc.getUser_name().toString());
        acctype.setText(acc.getType().toString());
        password.setText(acc.getPass().toString());
        accountstate.setText(acc.getState().toString());

    }


    @FXML
    public void SaveChanges(MouseEvent event) {

        Client client = DataBase.getInstance().getClient(this.ID.getText());

        if (!this.FirstNameC.getText().isEmpty()) {
            client.setFirstName(this.FirstNameC.getText());
        }
        if (!this.LastNameC.getText().isEmpty()) {
            client.setLastName(this.LastNameC.getText());
        }
        if (!this.phoneC.getText().isEmpty()) {
            client.setTelephone(this.phoneC.getText());
        }

        Account acc = DataBase.getInstance().getAccount(this.username.getText());

        if (!this.accountstate.getText().isEmpty()) {
            if (this.accountstate.getText().equals("Active") || this.accountstate.getText().equals("active"))
                acc.setState(true);
            else if (this.accountstate.getText().equals("Inactive") || this.accountstate.getText().equals("inactive"))
                acc.setState(false);
        }

        if (!password.getText().isEmpty()) {
            acc.setPass(password.getText());
        }

        if (!acctype.getText().isEmpty() && (acctype.getText().equals("Saving") || acctype.getText().equals("saving"))) {
            acc.acc_type = true;


        } else if (!acctype.getText().isEmpty() && (acctype.getText().equals("current") || acctype.getText().equals("Current"))) {

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


