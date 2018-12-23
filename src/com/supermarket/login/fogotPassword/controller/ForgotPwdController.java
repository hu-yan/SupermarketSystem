package com.supermarket.login.fogotPassword.controller;

import com.supermarket.util.data.manipulation.ServerManipulation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPwdController {
    @FXML
    private TextField staffNum;
    @FXML
    private TextField tele;
    @FXML
    private PasswordField newPwd;
    @FXML
    private PasswordField newPwdConfirm;
    @FXML
    public Label incorrectID;
    @FXML
    public Label incorrectTele;
    @FXML
    public Label incorrectPwd;

    private StringBuffer queryBuffer = new StringBuffer();

    @FXML
    protected void forgotPwd() throws SQLException {
        String id = staffNum.getText().trim();
        String telephone = tele.getText().trim();
        String newPwd1 = newPwd.getText().trim();
        String newPwd2 = newPwdConfirm.getText().trim();

        String isID = "";
        String isTel = "";

        if (!newPwd1.equals(newPwd2)){
            incorrectPwd.setText("请输入一致的密码");
        }

        queryBuffer.append("SELECT * FROM staff WHERE staff_num = '").append(id).append("'");
        ResultSet rs = ServerManipulation.execQuery(queryBuffer.toString());
        while (rs.next()){
            isID = rs.getString("staff_num");
       }
        queryBuffer.setLength(0);
        if (!isID.equals(id)){
            incorrectID.setText("不存在该ID");
        }
        queryBuffer.append("SELECT * FROM staff WHERE telephone = '").append(telephone).append("'");
        ResultSet rs2 = ServerManipulation.execQuery(queryBuffer.toString());

        while(rs2.next()){
            isTel = rs2.getString("telephone").trim();
        }
        if (!isTel.equals(telephone)){
            incorrectTele.setText("错误的手机号");
        }else {
            queryBuffer.setLength(0);
            queryBuffer.append("UPDATE staff SET password='").append(newPwd1).append("' WHERE staff_num = '").append(id).append("'");
        }

        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        Stage stage = (Stage) incorrectID.getScene().getWindow();
        stage.close();
    }

}
