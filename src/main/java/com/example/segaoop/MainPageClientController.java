package com.example.segaoop;
import com.example.functionality.*;

import com.example.functionality.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.channels.FileChannel;
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
    private TextField password;

    @FXML
    private Label no_such_account;
    @FXML
    private Label wrong_password;
    @FXML
    private Label invalid_amount;
    @FXML
    private Label name_title;
    @FXML
    private Label balance_title;
    @FXML
    private Label acc_id_title;
    @FXML
    protected void transferMoney()
    {
        no_such_account.setText("");
        wrong_password.setText("");
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
                    wrong_password.setText("Wrong Password");
                    return;
                }
            }
            else
            {
                no_such_account.setText("No Such Account");
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
}














