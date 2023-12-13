package com.example.segaoop;
import com.example.functionality.*;

import com.example.functionality.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainPageClientController implements Initializable {

    Client user = (Client) DataBase.getInstance().getCurrentUser();
    Account current_acc = DataBase.getInstance().getCurrentAccount();

    @FXML
    private Button transfer;

    @FXML
    private TextField accountID;
    @FXML
    private TextField amount;
    @FXML
    private PasswordField password;

    @FXML
    private Label invalid_amount;
    @FXML
    private Label name_title;
    @FXML
    private Label balance_title;
    @FXML
    private Label acc_id_title;

    @FXML
    private Button logOutButton;

    @FXML
    protected void transferMoney()
    {
        invalid_amount.setText("");
        if(amount.getText() != null && password.getText() != null && accountID.getText() != null)
        {
            double am;
            try {
                am = Double.parseDouble(amount.getText().toString());
            } catch (NumberFormatException e) {
                // if not a number
                invalid_amount.setText("Invalid Amount");
                return;
            }
            if(DataBase.getInstance().getAllAccounts().containsKey(accountID.getText().toString()))
            {
                Account receiver = DataBase.getInstance().getAccount(accountID.getText().toString());
                if(receiver.getPass().equals(password.getText().toString()))
                {
                    Account sender = DataBase.getInstance().getCurrentAccount();
                    transMoney(sender, receiver, am);
                }
                else
                {
                    invalid_amount.setText("Wrong Password");
                    return;
                }
            }
            else
            {
                invalid_amount.setText("No Such Account");
                return;
            }

        }
        System.out.println("ching ching");
    }

    private void transMoney(Account sender, Account receiver, double value)
    {
        try
        {
            sender.decreaseBalance(value);
            receiver.increaseBalance(value);
            System.out.println("new sender balance: " + sender.balance);
            System.out.println("new receiver balance: " + receiver.balance);
            balance_title.setText(String.valueOf(current_acc.balance));
            acc_id_title.setText(String.valueOf(current_acc.getAccount_number()));
            invalid_amount.setText("Transaction Successful");
            accountID.setText("");
            amount.setText("");
            password.setText("");
            Transaction newTrans = new Transaction(LocalDate.now().toString(), value,sender.getUser_name(),receiver.getUser_name());
            DataBase.getInstance().addTransaction(newTrans);
            DataBase.getInstance().getTransactionHistory().sort(new TransactionCompare());
        }
        catch (MoneyExceptions e)
        {
                invalid_amount.setText("Transaction Failed: Not Enough Money in Your Account");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        name_title.setText(user.firstName + " " + user.lastName);
        balance_title.setText(String.valueOf(current_acc.balance));
        acc_id_title.setText(String.valueOf(current_acc.getAccount_number()));
    }

    @FXML
    void logOut() throws IOException {
        HelloApplication test = new HelloApplication();
        test.changeScene("LoginPage.fxml");
    }
}














