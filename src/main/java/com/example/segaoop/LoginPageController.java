package com.example.segaoop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.time.LocalTime;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class LoginPageController {
    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label output;

    @FXML
    void userlogin(MouseEvent event)
        {
        System.out.println("lol");
        HelloApplication test = new HelloApplication();
        if(username.getText().toString().equals("mango") && password.getText().toString().equals("zoz"))
        {

            /*output.setText("Login Successful ✅");
            try {
                java.lang.Thread.sleep(5000);
            }
            catch (java.lang.InterruptedException e) {

            }finally {
                output.setText("Login Successful ✅");
            }*/
            try {

                test.changeScene("MainPageClientController.fxml");
            }
            catch(IOException e){

            }
        }
        else
        {
            output.setText("Incorrect Username or Password ❎");
        }
    }

}
