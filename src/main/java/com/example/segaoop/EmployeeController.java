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

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<Account, Integer> AccountNumberColumn;

    @FXML
    private TableView<Account> ClientsTable;

    @FXML
    private Button CreateButton;

    @FXML
    private ComboBox<String> PriorityComboBox;

    @FXML
    private TextField NotificationTitleTextField;

    @FXML
    private TextArea NotificationTextArea;

    @FXML
    private Button SendNotificationButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button EditButton;

    @FXML
    private TableColumn<Account, String> FirstNameColumn;

    @FXML
    private TableColumn<Account, String> IDColumn;

    @FXML
    private TableColumn<Account, String> LastNameColumn;
    
    @FXML
    private Text NameText;

    HelloApplication test = new HelloApplication();

    @FXML
    private Text IDText;

    @FXML
    private Button EditMyAccount;

    @FXML
    private Button SearchButton;

    @FXML
    private TextField SearchTextField;

    @FXML
    private TableColumn<Account, String> StateColumn;

    @FXML
    private TableColumn<Account, Double> BalanceColumn;

    @FXML
    private TableColumn<Account, String> TelephoneNumberColumn;

    @FXML
    private TableColumn<Account, String> TypeColumn;

    @FXML
    private TableColumn<Account, String> UsernameColumn;

    @FXML
    private Label somethingNotSelectedMessage;

    //---Report---//
    @FXML
    private TextField ReportSubjectTextField;

    @FXML
    private TextField to_TextField;

    @FXML
    private TextArea ReportTextArea;

    @FXML
    private Button SendReportButton;

    @FXML
    private Label report_or_subject_empty_message;

    //----Report Inbox----//

    @FXML
    private Text numberOfUnreadReportsEmp;

    @FXML
    private Button previewButtonEmp;

    @FXML
    private TableColumn<Report, String> readStatusColumnEmp;

    @FXML
    private TableColumn<Report, String> titleColumnEmp;

    @FXML
    private TableView<Report> reportsTableEmp;

    @FXML
    private TableColumn<Report, String> senderColumnEmp;


    @FXML
    void BeginSearch(MouseEvent event) {
        ObservableList<Account> tobeaddedlist = FXCollections.observableArrayList();
        for (String key:DataBase.getInstance().getAllAccounts().keySet()) {

            if (key.contains(SearchTextField.getText().toString().toLowerCase()))
            {
                tobeaddedlist.add(DataBase.getInstance().getAccount(key));
            }
        }
        ClientsTable.setItems(tobeaddedlist);
    }


    @FXML
    void GoToEditScene() throws IOException {

        DataBase.getInstance().setUsernameforedit(ClientsTable.getSelectionModel().getSelectedItem().getUser_name());
        DataBase.getInstance().setIdforedit(ClientsTable.getSelectionModel().getSelectedItem().getOwner());

        HelloApplication helloApplication=new HelloApplication();
        helloApplication.changeScene("EditEmp.fxml");
    }

    @FXML
    void GoToEditEmployeeScene() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScene("Edit.fxml");

    }

    @FXML
    void ReturntoLoginScene(MouseEvent event) {
        try {
            test.changeScene("LoginPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void RemoveSelectedItem(MouseEvent event) {
        String s = ClientsTable.getSelectionModel().getSelectedItem().getUser_name();
        DataBase.getInstance().getAllAccounts().remove(s);
        updatelist();
    }
    //--Messages--//
    @FXML
    void SendNotification(MouseEvent event) {

        if(PriorityComboBox.getSelectionModel().selectedItemProperty().getValue() == null || NotificationTextArea.getText().toString().isEmpty())
        {
            somethingNotSelectedMessage.setText("Please Type your Message\n and Choose it's Urgency");
            return;
        }
        else
        {
            somethingNotSelectedMessage.setText("");
        }

        String receiverUserName = ClientsTable.getSelectionModel().getSelectedItem().getUser_name();

        String choice = String.valueOf(PriorityComboBox.getSelectionModel().selectedItemProperty().getValue().toString().toLowerCase());
        String content = NotificationTextArea.getText().toString();
        Account receiver = (Account) ClientsTable.getSelectionModel().getSelectedItem();
        DataBase.getInstance().getAccount(receiverUserName).addNotification(new Notification(choice,content));



        NotificationTextArea.setText("");
        PriorityComboBox.getSelectionModel().clearSelection();
        PriorityComboBox.setPromptText("Select Priority");
    }
    @FXML
    void sendReport(MouseEvent event) {
        try{
            String subjectText = ReportSubjectTextField.getText();
            String bodyText = ReportTextArea.getText();
            String receiver = to_TextField.getText();
            if(subjectText.isEmpty() || bodyText.isEmpty() || receiver.isEmpty())
            {
                report_or_subject_empty_message.setText("One or More Field is Empty ⚠️");

            }
            else
            {
                report_or_subject_empty_message.setText("");

                if(receiver.equalsIgnoreCase("admin"))
                {
                    DataBase.getInstance().addReport(new Report(ReportSubjectTextField,ReportTextArea));
                }
                else
                {
                    DataBase.getInstance().getEmployee(receiver).getReceivedReports().add(new Report(ReportSubjectTextField,ReportTextArea));
                }
                ReportTextArea.setText("");
                ReportSubjectTextField.setText("");
                to_TextField.setText("");
            }
        }
        catch(NullPointerException e){
            report_or_subject_empty_message.setText("Employee Does not exist");
        }
    }

    int getUnread()
    {
        int x = 0;
        Employee currentUser = (Employee) DataBase.getInstance().getCurrentUser();
        for(Report R : DataBase.getInstance().getEmployee(currentUser.getID()).getReceivedReports())
        {
            if(!R.getMessageReadStatus())
            {
                x++;
            }
        }
        return x;
    }

    //Report Inbox
    @FXML
    void deleteReportEmp(MouseEvent event) {
        Employee currentUser = (Employee) DataBase.getInstance().getCurrentUser();
        Report selectedReport = this.reportsTableEmp.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            reportsTableEmp.getItems().remove(selectedReport);
            DataBase.getInstance().getSentReports().remove(selectedReport);
        }
    }

    @FXML
    void previewReportEmp(MouseEvent event) {
        Report selectedReport = this.reportsTableEmp.getSelectionModel().getSelectedItem();
        Employee currentUser = (Employee) DataBase.getInstance().getCurrentUser();
        if (selectedReport != null) {
            HelloApplication helloApplication = new HelloApplication();
            try {
                for (Report neededReport : DataBase.getInstance().getEmployee(currentUser.getID()).getReceivedReports()) {
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



    //--End Messages--//

    void updatelist()
    {
        ObservableList<Account> tobeaddedlistfirst = FXCollections.observableArrayList();
        for (String key:DataBase.getInstance().getAllAccounts().keySet()) {
            tobeaddedlistfirst.add(DataBase.getInstance().getAccount(key));
        }
        ClientsTable.setItems(tobeaddedlistfirst);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        somethingNotSelectedMessage.setText("");
        Employee curracc = (Employee)DataBase.getInstance().getCurrentUser();
        NameText.setText(curracc.getfirstName() + ' ' + curracc.getlastName());
        IDText.setText("ID: " + curracc.getID());

        PriorityComboBox.getItems().addAll("Normal","Important","Warning");


        IDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getId()));
        FirstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getFirstName()));
        LastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getLastName()));
        TypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        StateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState()));
        AccountNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAccount_number()).asObject());
        TelephoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getTelephone()));
        BalanceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBalance()).asObject());
        UsernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser_name()));

        //Reading Reports Table

        senderColumnEmp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSender().getID().toString()));
        titleColumnEmp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        readStatusColumnEmp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        ObservableList<Report> toBeAddedReports = FXCollections.observableArrayList();
        Employee currentUser = (Employee) DataBase.getInstance().getCurrentUser();
        for(Report R : DataBase.getInstance().getEmployee(currentUser.getID()).getReceivedReports()) {
            toBeAddedReports.add(R);
        }
        reportsTableEmp.setItems(toBeAddedReports);


        numberOfUnreadReportsEmp.setText(String.valueOf(getUnread()));


        updatelist();
    }
}

