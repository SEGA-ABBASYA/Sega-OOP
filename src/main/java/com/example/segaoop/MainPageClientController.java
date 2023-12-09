package com.example.segaoop;
import com.example.functionality.*;

import com.example.functionality.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.nio.channels.FileChannel;
import java.util.HashMap;

public class MainPageClientController {

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
                    //sender.update_balance();
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

    private void transMoney(Account sender, Account receiver, float value)
    {
        try
        {
            sender.decreaseBalance(value);
            receiver.increaseBalance(value);
        }
        catch (MoneyExceptions e)
        {
                invalid_amount.setText("Transaction Failed: Not Enough Money in Your Account");
        }
    }
}














