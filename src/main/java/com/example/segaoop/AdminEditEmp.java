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
import java.util.Date;
import java.util.ResourceBundle;

public class AdminEditEmp implements Initializable {

    @FXML
    private Button Apply;

    @FXML
    private Button Cancel;

    @FXML
    private TextField Firstname;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Address;

    @FXML
    private TextField Position;
    @FXML
    private TextField Salary;
    @FXML
    private TextField CollegeGrade;
    @FXML
    private TextField GraduationYear;
    @FXML
    private TextField Grade;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Employee emp = DataBase.getInstance().getEmployee(DataBase.getInstance().getIdforEmpEdit());

        Address.setText(emp.getAddress().toString());//works
        ID.setText(emp.getID().toString());//works
        Firstname.setText(emp.getfirstName().toString());//works
        // LastName.setText(emp.getlastName());
        // phone.setText(emp.getTelephone().toString());
        Position.setText(emp.getPosition().toString());//works
        CollegeGrade.setText(emp.getGraduationCollage().toString());//works
        Grade.setText(String.valueOf(emp.getTotalGrade()).toString());//works
        Salary.setText(String.valueOf(emp.getSalary()).toString());
        GraduationYear.setText(String.valueOf(emp.getGraduationYear()));
        password.setText(emp.getPassword());

    }


    @FXML
    public void SaveChanges() {

        Employee emp = DataBase.getInstance().getEmployee(DataBase.getInstance().getIdforEmpEdit());

        if (!this.Firstname.getText().isEmpty()) {
            emp.setfirstName(this.Firstname.getText());
        }
//        if (!this.LastName.getText().isEmpty()) {
//            emp.setlastName(this.LastName.getText());
//        }
//        if (!this.phone.getText().isEmpty()) {
//            emp.setTelephone(this.phone.getText());
//        }

        if (!this.Address.getText().isEmpty()) {
            emp.setAddress(this.Address.getText());
        }

        if (!password.getText().isEmpty()) {
            emp.setPassword(password.getText());
        }
        if (!Position.getText().isEmpty()) {
            emp.setPosition(Position.getText());
        }
        if(!ID.getText().isEmpty()){
            emp.setID(ID.getText());
        }
        if(!Salary.getText().isEmpty()){
            emp.setSalary(Float.parseFloat(Salary.getText()));
        }
        if(!CollegeGrade.getText().isEmpty()){
            emp.setGraduationCollage(CollegeGrade.getText());
        }

         if(!Grade.getText().isEmpty()){
         emp.setTotalGrade(Float.parseFloat((Grade.getText())));
         }
        if (!GraduationYear.getText().isEmpty())
         {
               emp.setGraduationYear(Integer.parseInt(GraduationYear.getText()));
         }

    }



    @FXML
    public void ReturnBackTo(MouseEvent event) throws IOException {
        pane.setVisible(false);
        HelloApplication he = new HelloApplication();

        he.changeScene("AdminViewPage.fxml");

    }

}



