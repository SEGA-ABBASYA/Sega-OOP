package com.example.segaoop;
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

public class CreateUser {
    @FXML
    private Button Done ;
    @FXML
    private TextField FirstName ;
    @FXML
    private TextField LastName ;
    @FXML
    private TextField ID ;
    @FXML
    private TextField Balance ;

    @FXML
    public ChoiceBox<String>accountType;
    @FXML
    private TextField PhoneNumber ;
    @FXML
    private TextField password ;
    @FXML
    private TextField confirm_password ;
    @FXML
    private TextField UserName ;



}
