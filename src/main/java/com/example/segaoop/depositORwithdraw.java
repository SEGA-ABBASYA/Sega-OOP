package com.example.segaoop;

import com.example.functionality.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class depositORwithdraw {

    @FXML
    private TextField accountID;

    @FXML
    private TextField amount;

    @FXML
    private RadioButton asDeposit;

    @FXML
    private RadioButton asWithdraw;

    @FXML
    private Label invalid_amount;

    @FXML
    private PasswordField password;

    @FXML
    private Button transfer;

    @FXML
    private Text feesText;

    String transactionType;
    @FXML
    void isSelected(MouseEvent event) throws MoneyExceptions {

        if (asDeposit.isSelected())
        {
            transactionType="Deposit";
            transfer.setOnAction(event1 -> {
                try {
                    controlMoney(event1);
                } catch (MoneyExceptions e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("deposit selected");
        }
        else if(asWithdraw.isSelected())
        {
            transactionType="Withdraw";
            transfer.setOnAction(event1 -> {
                try {
                    controlMoney(event1);
                } catch (MoneyExceptions e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("withdraw selected");
        }
        else
        {
            invalid_amount.setText("Choose Transaction Type ❎");
        }

    }
    void controlMoney(ActionEvent event) throws MoneyExceptions {

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
                    Person emp = DataBase.getInstance().getCurrentUser();
                    transMoney((Employee) emp, receiver, am);
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

    private void transMoney(Employee emp, Account receiver, double value) {

            try {
            if (transactionType.equals("Deposit")) {
                receiver.increaseBalance(value);
                //System.out.println("new sender balance: " + sender.balance);
                System.out.println("new receiver balance: " + receiver.balance);
            }
            else if (transactionType.equals("Withdraw")) {
                receiver.Update(value);
                System.out.println("new receiver balance: " + receiver.balance);
            }

                if(receiver.flag==true){
                    feesText.setText("FEES");
                }
            invalid_amount.setText("Transaction Successful");
            accountID.setText("");
            amount.setText("");
            password.setText("");
                feesText.setText("FEES DONE");
                feesText.setFill(Color.GREEN);
            Transaction newTrans = new Transaction(emp.getfirstName(), value, receiver.getUser_name(), transactionType);
            DataBase.getInstance().addTransaction(newTrans);
            DataBase.getInstance().getTransactionHistory().sort(new TransactionCompare());
            }
            catch (MoneyExceptions e)
            {
                invalid_amount.setText("Transaction Failed: Not Enough Money in Your Account");
            }


    }

}
