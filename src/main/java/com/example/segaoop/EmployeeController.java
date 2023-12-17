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
    private TextArea ReportTextArea;

    @FXML
    private Button SendReportButton;

    @FXML
    private Label report_or_subject_empty_message;

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
    void GoToCreateScene() {

    }

    @FXML
    void GoToEditScene() {

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

        String choice = String.valueOf(PriorityComboBox.getSelectionModel().selectedItemProperty().getValue().toString()).toLowerCase();
        String content = NotificationTextArea.getText().toString();
        Account receiver = (Account) ClientsTable.getSelectionModel().getSelectedItem();
        DataBase.getInstance().getAccount(receiverUserName).addNotification(new Notification(choice,content));
//        DataBase.getInstance().getAccount(receiverUserName).addNotification(new Notification(receiver,choice,content));

        //DataBase.getInstance().getAccount(receiverUserName).addNotification(new Notification(ClientsTable,PriorityComboBox,NotificationTextArea));
        //ClientsTable.getSelectionModel().getSelectedItem().addNotification(new Notification(ClientsTable,PriorityComboBox,NotificationTextArea));



        NotificationTextArea.setText("");
        PriorityComboBox.getSelectionModel().clearSelection();
        PriorityComboBox.setPromptText("Select Priority");
    }
    @FXML
    void sendReport(MouseEvent event) {
        String subjectText = ReportSubjectTextField.getText();
        String bodyText = ReportTextArea.getText();
        if(subjectText.isEmpty() || bodyText.isEmpty())
        {
            report_or_subject_empty_message.setText("Subject or Report Body is Empty ⚠️");

        }
        else
        {
            report_or_subject_empty_message.setText("");
            DataBase.getInstance().addReport(new Report(ReportSubjectTextField,ReportTextArea));
            ReportTextArea.setText("");
            ReportSubjectTextField.setText("");
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


        updatelist();
    }
}

