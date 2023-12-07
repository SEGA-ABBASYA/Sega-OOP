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

    // (1) for client, (0) for Employee
    private boolean selectedClient;

    @FXML
    void userlogin(MouseEvent event)
    {

        HelloApplication test = new HelloApplication();
        // admin employee
        if(!selectedClient && username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
        {
            try
            {
                DataBase.getInstance().setCurrentUser(DataBase.getInstance().getAdmin());
                test.changeScene("AdminViewPage.fxml");
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        // normal employee
        else if(!selectedClient)
        {
            Employee temp_emp = DataBase.getInstance().getEmployee(username.getText());
            if (temp_emp != null)
            {
                DataBase.getInstance().setCurrentUser(temp_emp);
                DataBase.getInstance().setCurrentAccount(null);
                try
                {
                    test.changeScene("hello-view.fxml");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                output.setText("Incorrect Username or Password ❎");
            }
        }
        // client
        else if(selectedClient)
        {
            Account temp_acc = DataBase.getInstance().getAccount(username.getText());
            if (temp_acc != null)
            {
                DataBase.getInstance().setCurrentAccount(temp_acc);
                DataBase.getInstance().setCurrentUser(temp_acc.getOwner());
                try
                {
                    test.changeScene("MainPageClientController.fxml");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                output.setText("Incorrect Username or Password ❎");
            }
        }
        else
        {
            output.setText("Choose Login Type ❎");
            System.out.println("no option chosen");
        }
    }

    @FXML
    private void isSelected(MouseEvent e)
    {
        if (asEmployee.isSelected())
        {
            System.out.println("emp selected");
            selectedClient = false;
        }
        else if(asClient.isSelected())
        {
            System.out.println("client selected");
            selectedClient = true;
        }
    }
}
