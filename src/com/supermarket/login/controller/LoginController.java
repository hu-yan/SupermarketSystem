package com.supermarket.login.controller;

import com.supermarket.util.data.manipulation.ServerManipulation;
import com.supermarket.util.view.reaction.FXUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField workID;
    @FXML
    private PasswordField pwd;
    @FXML
    private Label error;

    private boolean pwdChecker;
    private StringBuffer queryBuffer = new StringBuffer();
    private String position;


    @FXML
    protected void contract() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/login/view/Contact.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "联系管理员", root, 87, 346);
        stage.show();
    }

    @FXML
    protected void fogortPwd() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/login/fogotPassword/view/ForgortPwd.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "密码找回", root, 410, 625);
        stage.show();
    }

    @FXML
    protected void confirmLogin() throws SQLException, IOException {
        String ID = workID.getText();
        String userPwd = pwd.getText();

        queryBuffer.append("SELECT password, position FROM staff WHERE staff_num = '").append(ID).append("'");

        ResultSet loginResult = ServerManipulation.execQuery(queryBuffer.toString());

        while (!loginResult.next()){
            error.setText("错误的密码或用户名");
            break;
        }

        while (loginResult.next()) {
            pwdChecker = loginResult.getString("password").trim().equals(userPwd);
            position = loginResult.getString("position").trim();
            if (pwdChecker){
                switch (position){
                    case "cashier":
                        FXMLLoader cashierLoader = new FXMLLoader(getClass().getResource("/com/supermarket/cashier/view/CashierView.fxml"));
                        FXUtil.jumpToNewWindow(workID, cashierLoader, "收银员功能", 725, 880);
                        break;
                    case "warehouse":
                        FXMLLoader warehouseLoader= new FXMLLoader(getClass().getResource("/com/supermarket/warehouse/view/WarehouseView.fxml"));
                        FXUtil.jumpToNewWindow(workID, warehouseLoader, "仓库管理", 725, 880);
                        break;
                    case "manager":
                        //TODO
                    case "admin":
                        //TODO
                    default:
                        break;
                }
            }
        }
    }

}