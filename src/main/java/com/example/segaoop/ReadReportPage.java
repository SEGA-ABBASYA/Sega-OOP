package com.example.segaoop;

import com.example.functionality.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadReportPage implements Initializable {

    @FXML
    private Button backToAdminPage;

    @FXML
    private  TextArea reportDisplayArea = new TextArea(DataBase.getInstance().getSelectedReport().getContent());

    @FXML
    private  Label whenIssued = new Label(DataBase.getInstance().getSelectedReport().getDate());

    @FXML
    private  Label whoIssued = new Label(DataBase.getInstance().getSelectedReport().getSender().getID());


    @FXML
    void backToAdminPage(MouseEvent event) {
        HelloApplication helloApplication = new HelloApplication();
        try {
            helloApplication.changeScene("AdminViewPage.fxml");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDisplayArea.setText(DataBase.getInstance().getSelectedReport().getContent());
        whoIssued.setText(DataBase.getInstance().getSelectedReport().getSender().getID());
        whenIssued.setText(DataBase.getInstance().getSelectedReport().getDate());
    }
}
