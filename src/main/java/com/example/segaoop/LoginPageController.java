package com.example.segaoop;
import com.example.functionality.DataBase;
import com.example.functionality.PassHashing;
import com.example.functionality.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private RadioButton asEmployee;
    @FXML
    private RadioButton asClient;
    @FXML
    private ToggleGroup x;


    @FXML
    void LoginAll(MouseEvent event) {
        if (x.getSelectedToggle().equals(asClient))
            loginAsClient();
        else
            loginAsEmployee();
    }

    @FXML
    void loginAsClient()
    {
        HelloApplication test = new HelloApplication();
        if(username.getText() != null && password.getText() != null)
        {
            if (DataBase.getInstance().getAllAccounts().containsKey(username.getText()))
            {
                Account temp_acc = DataBase.getInstance().getAccount(username.getText());
                System.out.println(temp_acc.getPass());
                System.out.println(password.getText().toString());
                if(temp_acc.getPass().equals(password.getText().toString()))
                {
                    DataBase.getInstance().setCurrentAccount(temp_acc);
                    DataBase.getInstance().setCurrentUser(temp_acc.getOwner());
                    try {
                        test.changeScene("MainPageClientController.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    output.setText("Incorrect Password ❎");
                }
            }
            else
            {
                output.setText("Incorrect Username ❎");
            }
        }
    }

    @FXML
    void loginAsEmployee()
    {
        HelloApplication test = new HelloApplication();
        if(username.getText() != null && password.getText() != null)
        {
            // admin
            if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                try
                {
                    DataBase.getInstance().setCurrentUser(DataBase.getInstance().getAdmin());
                    test.changeScene("AdminViewPage.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // normal employee
            else
            {
                // if username exists
                if(DataBase.getInstance().getAllEmployees().containsKey(username.getText().toString()))
                {
                    Employee temp_emp = DataBase.getInstance().getEmployee(username.getText().toString());

                    // if password correct
                    if(temp_emp.getPassword().equals(password.getText().toString()))
                    {

                        DataBase.getInstance().setCurrentUser(temp_emp);
                        DataBase.getInstance().setCurrentAccount(null);
                        try {
                            test.changeScene("hello-view.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        output.setText("Incorrect Password ❎");
                    }
                }
                else
                {
                    output.setText("Incorrect Username ❎");
                }
            }
        }
    }
}
