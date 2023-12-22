package com.example.segaoop;

import com.example.functionality.DataBase;
import com.example.functionality.Employee;
import com.example.functionality.Report;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReplyPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Label replyError;

    @FXML
    private TextField replySubjectTextField;

    @FXML
    private TextArea replyTextArea;

    @FXML
    private Label replyingToWhomLabel;

    @FXML
    private Button sendReplyButton;

    @FXML
    void backToMainPage(MouseEvent event) {
        Employee currentUser = (Employee) DataBase.getInstance().getCurrentUser();
        if(currentUser.getID().equalsIgnoreCase("admin"))
        {
            HelloApplication helloApplication = new HelloApplication();
            try
            {
                helloApplication.changeScene("AdminViewPage.fxml");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            HelloApplication helloApplication = new HelloApplication();
            try
            {
                helloApplication.changeScene("hello-view.fxml");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void sendReply(MouseEvent event) {
        Employee currentUser = (Employee) DataBase.getInstance().getCurrentUser();
        String subjectText = replySubjectTextField.getText();
        String bodyText = replyTextArea.getText();
        if(subjectText.isEmpty() || bodyText.isEmpty())
        {
            replyError.setText("Subject or Report Body is Empty ⚠️");

        }
        else
        {
            //getting the sender id to make him a receiver in the new report
            String receiverID = DataBase.getInstance().getSelectedReport().getSender().getID();
            // finding the receiver then pushing the new report in his list
            if(receiverID.equalsIgnoreCase("admin"))
            {
                DataBase.getInstance().getSentReports().add(new Report(replySubjectTextField,replyTextArea));
            }
            else
            {
                DataBase.getInstance().getEmployee(receiverID).getReceivedReports().add(new Report(replySubjectTextField,replyTextArea));
            }
            replyError.setText("");
            replyTextArea.setText("");
            replySubjectTextField.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.replyingToWhomLabel.setText(DataBase.getInstance().getSelectedReport().getSender().getID());
        this.replySubjectTextField.setPromptText(DataBase.getInstance().getSelectedReport().getCategory());
    }
}