package com.example.segaoop;

import com.example.functionality.Account;
import com.example.functionality.Client;
import com.example.functionality.DataBase;
import com.example.functionality.Employee;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEmployee {

    @FXML
    TextField FirstName;
    String firstName ;

    @FXML
    TextField LastName;
    String lastName;

    @FXML
    TextField TotalGrade;
    String totalGrade ;
    public float convertGradeToGPA(String grade) {
        switch (grade) {
            case "excellent", "Excellent":
                return 4.0f;
            case "very good","V.Good","Very Good":
                return 3.5f;
            case "good" ,"Good":
                return 3.0f;
            case "weak","Weak":
                return 2.0f;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
    }

    @FXML
    TextField ID;
    String id;
    @FXML
    TextField Position;
    String position ;
    @FXML
    TextField Address;
    String address ;

    @FXML
    TextField GraduatedCollage;
    String graduatedCollage;

    @FXML
    TextField YearOfGraduation;
    String yearOfGraduation ;

    @FXML
    TextField Salary;
    String salary ;
    @FXML
    TextField Password;
    String password;

    @FXML
    TextField ConfirmPassword;
    String confirmPassword;


    @FXML
    TextField PhoneNumber;
    String phoneNumber;

    @FXML
    public Button Create;
    @FXML
    public Button Clear;

    @FXML
    private Text ErrorText;

    void clearAllFields()
    {
        FirstName.clear();
        LastName.clear();
        ID.clear();
        Salary.clear();
        PhoneNumber.clear();
        GraduatedCollage.clear();
        Password.clear();
        ConfirmPassword.clear();
        YearOfGraduation.clear();
        Address.clear();
        YearOfGraduation.clear();
        Position.clear();
        TotalGrade.clear();

    }
    @FXML
    void ClearButtonFunction(MouseEvent event) {
        clearAllFields();
    }

    @FXML
    void AddAccount(MouseEvent event) {

        if (!FirstName.getText().isEmpty())
        {
            firstName = FirstName.getText();
        }
        else
        {
            ErrorText.setText("Please Enter First Name.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!LastName.getText().isEmpty())
        {
            lastName = LastName.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Last Name");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!Address.getText().isEmpty())
        {
            address = Address.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Address.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!Position.getText().isEmpty())
        {
            position = Position.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Position.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!ID.getText().isEmpty())
        {
            id = ID.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Employee ID.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!TotalGrade.getText().isEmpty())
        {
            totalGrade = TotalGrade.getText();

        }
        else
        {
            ErrorText.setText("Please Enter The Grade.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!YearOfGraduation.getText().isEmpty())
        {
            yearOfGraduation = YearOfGraduation.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Graduation Year.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!GraduatedCollage.getText().isEmpty())
        {
            graduatedCollage = GraduatedCollage.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Graduated Collage.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!PhoneNumber.getText().isEmpty())
        {
            phoneNumber = PhoneNumber.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Phone Number.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!Salary.getText().isEmpty())
        {
            salary = Salary.getText();
        }
        else
        {
            ErrorText.setText("Please Enter Salary.");
            ErrorText.setFill(Color.RED);
            return;
        }
        if (!Password.getText().isEmpty())
        {
            password = Password.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Password.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!ConfirmPassword.getText().isEmpty())
        {
            confirmPassword = ConfirmPassword.getText();
        }
        else
        {
            ErrorText.setText("Please Enter The Confirmation Correctly.");
            ErrorText.setFill(Color.RED);
            return;
        }

        if (!ConfirmPassword.getText().equals(Password.getText()))
        {
            ErrorText.setText("Passwords does not Match.");
            ErrorText.setFill(Color.RED);
            return;
        }
        if(checklongpassword(password)){
            ErrorText.setText("Password must contains at least 8 character.");
        }

        if(DataBase.getInstance().getEmployee(id)!=null)
        {
            ErrorText.setText("Please enter a unique username.");
            ErrorText.setFill(Color.RED);
            return;
        }

        Employee Emp = new Employee(id,firstName,lastName,phoneNumber,address,position,graduatedCollage,Float.parseFloat(salary),convertGradeToGPA(totalGrade),Integer.parseInt(yearOfGraduation),password);
        if (DataBase.getInstance().getEmployee(id)==null)
        {
            DataBase.getInstance().addEmployee(Emp.getID(),Emp);
        }
        else
        {
            Emp = DataBase.getInstance().getEmployee(id);
        }

        ErrorText.setFill(Color.GREEN);
        ErrorText.setText("Account Created Successfully");
        clearAllFields();
    }

    public boolean checklongpassword(String password) {
        return password.length() < 8;
    }


}
