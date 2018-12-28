package com.supermarket.administrator.controller;


import com.supermarket.util.data.manipulation.ServerManipulation;
import com.supermarket.util.view.reaction.FXUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class AdminController {
    @FXML
    private TextField adminsearchKey;

    @FXML
    private Label sNumCol;
    @FXML
    private Label sNameCol;
    @FXML
    private Label SexCol;
    @FXML
    private Label positionCol;
    @FXML
    private Label telephoneCol;
    @FXML
    private Label passwordCol;

    @FXML
    private Label adminResultArea;

    @FXML
    private TextField sNum;

    @FXML
    private TextField sName;

    @FXML
    private TextField sSex;

    @FXML
    private TextField Sposition;

    @FXML
    private TextField Stelephone;

    @FXML
    private TextField Spassword;

    @FXML
    private Label closeLabel;


    private StringBuffer queryBuffer = new StringBuffer();



    //查询
    @FXML
    protected void adminSearch() throws SQLException {
        String key = adminsearchKey.getText();
        if (key.startsWith("0")) {
            String searchByNo = "staff_num";
            queryBuffer.append("SELECT * FROM staff WHERE ").append(searchByNo).append("='").append(adminsearchKey.getText()).append("'");
            System.out.println(queryBuffer.toString());
        } else {
            String searchByName = "staff_name";
            queryBuffer.append("SELECT * FROM staff WHERE ").append(searchByName).append("='").append(adminsearchKey.getText()).append("'");
            System.out.println(queryBuffer.toString());
        }
        ResultSet queryResult = ServerManipulation.execQuery(queryBuffer.toString());
        queryBuffer.setLength(0);
        while (queryResult.next()) {
            updateStaffTableValue(queryResult);
            adminResultArea.setText("查询成功");
        }
    }

    // 显示数据到lable上
    private void updateStaffTableValue(ResultSet rs) throws SQLException {
        sNumCol.setText(rs.getString("staff_num").trim());
        sNameCol.setText(rs.getString("staff_name").trim());
        SexCol.setText((rs.getString("sex")).trim());

        positionCol.setText(((String)   rs.getString("position")).trim());
        telephoneCol.setText(((String)  rs.getString("telephone")).trim());
        passwordCol.setText((String)    rs.getString("password").trim());
    }

    //插入数据
    @FXML
    protected void adminInsert() throws SQLException {
        String staff_num=sNum.getText().trim();
        String staff_name = sName.getText().trim();
        String sex = sSex.getText().trim();
        String position = Sposition.getText().trim();
        String telephone = Stelephone.getText().trim();
        String password = Spassword.getText().trim();


        queryBuffer.append("INSERT INTO staff (staff_num,staff_name,sex,position,telephone,password) VALUES(").append("'").append(staff_num).append("',").append("'").append(staff_name).append("',").append("'").append(sex).append("',")
                .append("'").append(position).append("',").append("'").append(telephone).append("',").append("'").append(password).append("')");
        System.out.println(queryBuffer.toString());
        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        adminResultArea.setText("插入成功");
        queryBuffer.setLength(0);

    }

    //更改数据
    @FXML
    protected void adminUpdate() throws SQLException {

        String staff_num = sNum.getText();
        String staff_name = sName.getText();
        String sex = sSex.getText();
        String position = Sposition.getText();
        String telephone = Stelephone.getText();
        String password = Spassword.getText();

        queryBuffer.append("UPDATE staff SET staff_name=").append("'").append(staff_name).append("',").append("sex=").append("'").append(sex).append("',").append("position=").append("'").append(position).append("',")
                .append("telephone=").append("'").append(telephone).append("',").append("password=").append("'").append(password).append("'").append("WHERE staff_num=").append("'").append(staff_num).append("'");
        System.out.println(queryBuffer.toString());
        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        adminResultArea.setText("修改成功");
        queryBuffer.setLength(0);


    }


    //删除数据
    @FXML
    protected void adminDelete() throws SQLException {
        String staff_num = sNum.getText();

        queryBuffer.append("DELETE FROM staff WHERE staff_num").append("=").append(staff_num);
        System.out.println(queryBuffer.toString());
        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        adminResultArea.setText("删除成功");
        queryBuffer.setLength(0);

    }

    //退出系统
    @FXML
    protected void closeStage() throws IOException {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();

    }
}

