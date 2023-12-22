package com.example.segaoop;


import com.example.functionality.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminViewPagaController implements Initializable {
//------------------------------Clients----------------------------//
    @FXML
    private TableView<Account> ClientsTable;

    @FXML
    private TableColumn<Account, String> IDColumn;

    @FXML
    private TableColumn<Account, String> FirstNameColumn;
    @FXML
    private TableColumn<Account, String> LastNameColumn;

    @FXML
    private TableColumn<Account, Integer> AccountNumberColumn;

    @FXML
    private TableColumn<Account, String> TelephoneNumberColumn;

    @FXML
    private TableColumn<Account, String> StateColumn;

    @FXML
    private TableColumn<Account, String> TypeColumn;

    @FXML
    private TableColumn<Account, String> UsernameColumn;
    @FXML
    private TableColumn<Account, Double> BalanceColumn;
//-----------------------client tab buttons----------------------------//
    @FXML
    private TextField SearchTextField;
    @FXML
    private Button SearchButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button EditButton;
//------------------------employee tab buttons------------------------//
    @FXML
    private TextField SearchTextField1;
    @FXML
    private Button SearchButton1;
    @FXML
    private Button DeleteButton1;
    @FXML
    private Button EditButton1;
//-------------------------------employee table-----------------------//
    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> EmployeeID;

    @FXML
    private TableColumn<Employee, String> FirstNameEmployee;

    @FXML
    private TableColumn<Employee, String> LastNameEmployee;

    @FXML
    private TableColumn<Employee, String> TelephoneNumberEmployee;

    @FXML
    private TableColumn<Employee, String> addressEmployee;

    @FXML
    private TableColumn<Employee, String> positionEmployee;

    @FXML
    private TableColumn<Employee, Float> salaryEmployee;

    @FXML
    private TableColumn<Employee, String> collegeGradeEmployee;

    @FXML
    private TableColumn<Employee, Float> totalGradeEmployee;

    @FXML
    private TableColumn<Employee, Integer> gradeYearEmployee;

    //--------------------Reports Table--------------------//
    @FXML
    private TableView<Report> reportsTable;
    @FXML
    private TableColumn<Report,String> senderColumn;

    @FXML
    private TableColumn<Report, String> titleColumn;

    @FXML
    private TableColumn<Report, String> readStatusColumn;

    @FXML
    private Button previewButton;
    @FXML
    private Button deleteReport;
    @FXML
    private Button refreshButton;

    @FXML
    private Text numberOfUnreadReports;

    @FXML
    private Button adminLogoutButton;

    //-----------Send Report-------------//
    @FXML
    private TextField to_TextFieldAdmin;
    @FXML
    private Label report_or_subject_empty_messageAdmin;
    @FXML
    private Button SendReportButtonAdmin;
    @FXML
    private TextField ReportSubjectTextFieldAdmin;

    @FXML
    private TextArea ReportTextAreaAdmin;



    @FXML
    void BeginSearch(MouseEvent event) {
// Searching For Clients
        ObservableList<Account> tobeaddedlist = FXCollections.observableArrayList();
        for (String key:DataBase.getInstance().getAllAccounts().keySet()) {

            if (key.contains(SearchTextField.getText().toLowerCase()))
            {
                tobeaddedlist.add(DataBase.getInstance().getAccount(key));
            }
        }
        ClientsTable.setItems(tobeaddedlist);
    }

    @FXML
    void BeginSearchEmployee(MouseEvent event) {
// Searching For Employees
        ObservableList<Employee> toBeAddedList = FXCollections.observableArrayList();
        for (String key:DataBase.getInstance().getAllAccounts().keySet()) {

            if (key.contains(SearchTextField.getText().toLowerCase()))
            {
                toBeAddedList.add(DataBase.getInstance().getEmployee(key));
            }
        }
        employeeTable.setItems(toBeAddedList);
    }

    @FXML
    void GoToEditScene() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScene("Edit.fxml");
    }

    @FXML
    void RemoveSelectedItem(MouseEvent event) {

    }
    @FXML
    void previewReport(MouseEvent event) {
        Report selectedReport = this.reportsTable.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            HelloApplication helloApplication = new HelloApplication();
            try {
                for (Report neededReport : DataBase.getInstance().getSentReports()) {
                    if (neededReport.getCategory().equals(selectedReport.getCategory())) {
                        neededReport.setAsRead();
                        break;
                    }
                }
                DataBase.getInstance().setSelectedReport(selectedReport);
                helloApplication.changeScene("ReadReportPage.fxml");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void deleteReport(MouseEvent event) {
        Report selectedReport = this.reportsTable.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            reportsTable.getItems().remove(selectedReport);
            DataBase.getInstance().getSentReports().remove(selectedReport);
        }
    }


    @FXML
    void sendReportAdmin(MouseEvent event) {
        String subjectText = ReportSubjectTextFieldAdmin.getText();
        String bodyText = ReportTextAreaAdmin.getText();
        String receiver = to_TextFieldAdmin.getText();
        if(subjectText.isEmpty() || bodyText.isEmpty() || receiver.isEmpty())
        {
            report_or_subject_empty_messageAdmin.setText("One or More Field is Empty ⚠️");

        }
        else
        {
            report_or_subject_empty_messageAdmin.setText("");
            //DataBase.getInstance().addReport(new Report(ReportSubjectTextField,ReportTextArea));
            if(receiver.equalsIgnoreCase("admin"))
            {
                DataBase.getInstance().addReport(new Report(ReportSubjectTextFieldAdmin,ReportTextAreaAdmin));
            }
            else
            {
                DataBase.getInstance().getEmployee(receiver).getReceivedReports().add(new Report(ReportSubjectTextFieldAdmin,ReportTextAreaAdmin));
            }
            ReportTextAreaAdmin.setText("");
            ReportSubjectTextFieldAdmin.setText("");
            to_TextFieldAdmin.setText("");
        }
    }
    @FXML
    void returnToLoginScene(MouseEvent event) {
        HelloApplication helloApplication = new HelloApplication();
        try {
            helloApplication.changeScene("LoginPage.fxml");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    int getUnread()
    {
        int x = 0;
        for(Report R : DataBase.getInstance().getSentReports())
        {
            if(!R.getMessageReadStatus())
            {
                x++;
            }
        }
        return x;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Reading Accounts From DataBase

        IDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getId()));
        FirstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getFirstName()));
        LastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getLastName()));
        TypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        StateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState()));
        AccountNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAccount_number()).asObject());
        TelephoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getTelephone()));
        BalanceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBalance()).asObject());
        UsernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser_name()));


        ObservableList<Account> tobeaddedlistfirst = FXCollections.observableArrayList();
        for (String key:DataBase.getInstance().getAllAccounts().keySet()) {
            tobeaddedlistfirst.add(DataBase.getInstance().getAccount(key));
        }
        ClientsTable.setItems(tobeaddedlistfirst);

        //Reading Employees From DataBase

        EmployeeID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
        FirstNameEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getfirstName()));
        LastNameEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getlastName()));
        TelephoneNumberEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));
        addressEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        positionEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));
        salaryEmployee.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getSalary()).asObject());
        collegeGradeEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGraduationCollage()));
        totalGradeEmployee.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getTotalGrade()).asObject());
        gradeYearEmployee.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getGraduationYear()).asObject());


        ObservableList<Employee> toBeAddedList = FXCollections.observableArrayList();
        for (String key:DataBase.getInstance().getAllEmployees().keySet()) {
            toBeAddedList.add(DataBase.getInstance().getEmployee(key));
        }
        employeeTable.setItems(toBeAddedList);



        //Reading Reports Table

        senderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSender().getID().toString()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        readStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        ObservableList<Report> toBeAddedReports = FXCollections.observableArrayList();
        for (Report R :DataBase.getInstance().getSentReports()) {
            toBeAddedReports.add(R);
        }
        reportsTable.setItems(toBeAddedReports);


        numberOfUnreadReports.setText(String.valueOf(getUnread()));
    }




}