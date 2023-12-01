package com.example.segaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableView;

public class AdminViewPagaController {

    @FXML
    private TreeTableView<?> clientList;

    @FXML
    private Tab clientTab;

    @FXML
    private TreeTableView<?> employeeList;

    @FXML
    private Tab employeeTab;

    @FXML
    private RadioButton firstNameRadio;

    @FXML
    private RadioButton firstNameRadioClient;

    @FXML
    private RadioButton idRadio;

    @FXML
    private RadioButton idRadioClients;

    @FXML
    private RadioButton lastNameRadio;

    @FXML
    private RadioButton salaryRadio;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField searchBarClients;

    @FXML
    private Button searchButton;

    @FXML
    private Button searchButtonClients;

    @FXML
    private ToggleGroup searchBy;

    @FXML
    private ToggleGroup searchByClient;

    @FXML
    private RadioButton usernameRadioClient;

}