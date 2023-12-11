package com.example.segaoop;

import com.example.functionality.Client;
import com.example.functionality.DataBase;
import com.example.functionality.Employee;
import com.example.functionality.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<Client, Integer> AccountNumberColumn;

    @FXML
    private TableView<Client> ClientsTable;

    @FXML
    private Button CreateButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button EditButton;

    @FXML
    private TableColumn<Client, String> FirstNameColumn;

    @FXML
    private TableColumn<Client, Integer> IDColumn;

    @FXML
    private TableColumn<Client, String> LastNameColumn;
    
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
    private TableColumn<Client, String> StateColumn;

    @FXML
    private TableColumn<Client,Float> BalanceColumn;

    @FXML
    private TableColumn<Client, Integer> TelephoneNumberColumn;

    @FXML
    private TableColumn<Client, String> TypeColumn;

    @FXML
    private TableColumn<Client, String> UsernameColumn;

    @FXML
    void BeginSearch(MouseEvent event) {
        ObservableList<Client> tobeaddedlist = FXCollections.observableArrayList();
        for (Client item:ClientTestList) {
            String s = item.getId().toLowerCase();
            if (s.contains(SearchTextField.getText().toLowerCase()))
            {
                tobeaddedlist.add(item);
            }
        }
        ClientsTable.setItems(tobeaddedlist);
    }



    @FXML
    void GoToCreateScene(MouseEvent event) {

    }

    @FXML
    void GoToEditScene(MouseEvent event) {

    }

    @FXML
    void GoToEditEmployeeScene(MouseEvent event) {

    }

    ObservableList<Client> ClientTestList = FXCollections.observableArrayList(
//            new Client("15000.0",1,123,"Youssef","Ashraf", "Youssefproof","Youssef2004","Savings","Active",01066555),
//            new Client("50000.0",2,124,"Youssef","Ahmed", "Herofis","HAHS1234","Savings","Active",10241224)
    );

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
        if (!ClientsTable.getSelectionModel().getSelectedItems().isEmpty())
        {
            int selectedIndex = ClientsTable.getSelectionModel().getSelectedIndex();
            String selectedAccountNumber = ClientsTable.getSelectionModel().getSelectedItem().getId();
            for (Client item : ClientTestList) {
                if (Objects.equals(item.getId(), selectedAccountNumber))
                {
                    ClientTestList.remove(item);
                    break;
                }
            }
            ClientsTable.getItems().remove(selectedIndex);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee curracc = (Employee)DataBase.getInstance().getCurrentUser();
        NameText.setText(curracc.firstName + ' ' + curracc.lastName);
        IDText.setText("ID: " + curracc.id);

        IDColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("ID"));
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("FirstName"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("LastName"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("Type"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("State"));
        AccountNumberColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("AccountNumber"));
        TelephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("TelephoneNumber"));
        BalanceColumn.setCellValueFactory(new PropertyValueFactory<Client,Float>("Balance"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("Username"));

        ClientsTable.setItems(ClientTestList);
    }
}

