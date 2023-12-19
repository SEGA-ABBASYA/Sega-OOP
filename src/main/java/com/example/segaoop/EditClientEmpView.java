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
    protected Text Alert1,Alert2;
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

        Alert1.setVisible(false);
        Alert2.setVisible(false);

    }
    @FXML
    public void SaveChanges(MouseEvent event) {


        if(!ID.getText().isEmpty()) {
            Client client = DataBase.getInstance().getClient(this.ID.getText());

            if (client != null) {

                Alert1.setVisible(false);

                if (!this.FirstNameC.getText().isEmpty()) {
                    client.setFirstName(this.FirstNameC.getText());
                }
                if (!this.LastNameC.getText().isEmpty()) {
                    client.setLastName(this.LastNameC.getText());
                }
                if (!this.phoneC.getText().isEmpty()) {
                    client.setTelephone(this.phoneC.getText());
                }
            } else
                Alert1.setVisible(true);
        }

        if(!username.getText().isEmpty()) {

            Account acc = DataBase.getInstance().getAccount(this.username.getText());

            if (acc != null) {

                Alert2.setVisible(false);

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
            }else
                Alert2.setVisible(true);
        }
    }



    @FXML
    public void ReturnBackTo(MouseEvent event) throws IOException {

        pane.setVisible(false);
        HelloApplication he = new HelloApplication();

        he.changeScene("hello-view.fxml");
    }

}


