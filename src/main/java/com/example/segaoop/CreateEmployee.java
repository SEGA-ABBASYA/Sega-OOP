package com.example.segaoop;

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
    String firstName = FirstName.getText();

    @FXML
    TextField LastName;
    String lastName = LastName.getText();

    @FXML
    TextField TotalGrade;
    String totalGrade = TotalGrade.getText();
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

    String password = "";
    @FXML
    TextField ID;
    String id = ID.getText();
    @FXML
    TextField Position;
    String position = Position.getText();
    @FXML
    TextField Address;
    String address = Address.getText();

    @FXML
    TextField GraduatedCollage;
    String graduatedCollage = GraduatedCollage.getText();

    @FXML
    TextField YearOfGraduation;
    String yearOfGraduation = YearOfGraduation.getText();
    public int graduationyear = Integer.parseInt(yearOfGraduation);

    @FXML
    TextField Salary;
    String salary = Salary.getText();
    public float sal_ary = Float.parseFloat(salary);

    @FXML
    TextField PhoneNumber;
    String phoneNumber = PhoneNumber.getText();


     public void AddingClient(){
        if(((Employee)DataBase.getInstance().getCurrentUser()).getPosition().equals("admin")) {
             if (DataBase.getInstance().getCurrentUser() instanceof Employee) {
               // add employee
              Employee x = new Employee(id,firstName,lastName,phoneNumber,address,position,graduatedCollage,sal_ary,convertGradeToGPA(totalGrade),graduationyear,password);
               DataBase.getInstance().addEmployee(x.getID(),x);
          }
        }
     }

}
