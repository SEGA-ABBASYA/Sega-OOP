package com.example.segaoop;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public class EditController {

    @FXML
    protected Button Apply,Cancel;
    @FXML
    protected TextField passwor,ID,UserName,FirstName,LastName,AccountNum,PhoneNum,AccState,TypeAcc,Balance;
    @FXML
    protected String UserName_,FirstName_,LastName_,AccState_,TypeAcc_;
    @FXML
    protected int ID_,AccountNum_,PhoneNum_;
    @FXML
    protected TextField position,address,gradColl,YrGradm,Tgrade;
    @FXML
    protected String position_,address_,gradColl_;
    @FXML
    protected float YrGradm_,Tgrade_;
    @FXML
    protected float Balance_;
    @FXML
    protected Label accountnuml,uswernamel,passwordl,phonenuml,accstatel,typeaccl,balancel,addressl,positionl,colgradl,tgradl,yrgradl;






    @FXML
    void ReturnBackTo(MouseEvent event) {

    }

    @FXML
    protected void SaveChanges (MouseEvent event,String t) {

        if (t.equals("Client")) {
            ID_ = Integer.parseInt(this.ID.getText());
            UserName_ = this.UserName.getText();
            FirstName_ = this.FirstName.getText();
            LastName_ = this.LastName.getText();
            AccountNum_ = Integer.parseInt(this.AccountNum.getText());
            PhoneNum_ = Integer.parseInt(this.PhoneNum.getText());
            AccState_ = this.AccState.getText();
            TypeAcc_ = this.TypeAcc.getText();
            Balance_ = Integer.parseInt(this.Balance.getText());

            position.setVisible(false);
            address.setVisible(false);
            gradColl.setVisible(false);
            YrGradm.setVisible(false);
            Tgrade.setVisible(false);
            addressl.setVisible(false);
            positionl.setVisible(false);
            colgradl.setVisible(false);
            yrgradl.setVisible(false);
            tgradl.setVisible(false);



        }

        if (t.equals("Employee")){

            ID_ = Integer.parseInt(this.ID.getText());
            UserName_ = this.UserName.getText();
            FirstName_ = this.FirstName.getText();
            LastName_ = this.LastName.getText();
            position_=this.position.getText();
            address_= this.address.getText();
            gradColl_= this.gradColl.getText();
            YrGradm_= Float.parseFloat(this.YrGradm.getText());
            Tgrade_ =  Float.parseFloat(this.Tgrade.getText());

            AccountNum.setVisible(false);
            PhoneNum.setVisible(false);
            AccState.setVisible(false);
            TypeAcc.setVisible(false);
            Balance.setVisible(false);
            accountnuml.setVisible(false);
            uswernamel.setVisible(false);
            passwordl.setVisible(false);
            phonenuml.setVisible(false);
            accstatel.setVisible(false);
            typeaccl.setVisible(false);
            balancel.setVisible(false);




        }






    }


   /* protected int ReturnID (){

        return Integer.parseInt(this.ID.getText());
    }
    protected String ReturnUserName (){

        return this.UserName.getText();
    }
    protected String ReturnFirstName (){

        return this.FirstName.getText();
    }
    protected String ReturnLastName (){

        return this.LastName.getText();
    }
    protected int ReturnAccountNum (){

        return Integer.parseInt(this.AccountNum.getText());
    }
    protected int ReturnPhoneNum (){

        return Integer.parseInt(this.PhoneNum.getText());
    }protected String ReturnAccState (){

        return this.AccState.getText();
    }
    protected String ReturnTypeAcc (){

        return this.TypeAcc.getText();
    }
    protected int ReturnBalance (){

        return Integer.parseInt(this.Balance.getText());
    }
*/




}
