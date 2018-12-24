package com.supermarket.manager.staff.controller;


import com.supermarket.util.data.manipulation.ServerManipulation;
import com.supermarket.util.view.reaction.FXUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
public class StaffController {
    @FXML
    private TextField searchKey;
    @FXML
    private Label back;
    @FXML
    private Label tNumCol;
    @FXML
    private Label tNameCol;
    @FXML
    private Label sexCol;
    @FXML
    private Label positionCol;
    @FXML
    private Label telephoneCol;
    @FXML
    private Label cashierResultArea;

    private StringBuffer queryBuffer = new StringBuffer();


    @FXML
    protected void staffSearch() throws SQLException {
        String key = searchKey.getText();
        if (key.startsWith("0")) {
            String searchByNo = "staff_num";
            queryBuffer.append("SELECT * FROM staff WHERE ").append(searchByNo).append("='").append(searchKey.getText()).append("'");
            System.out.println(queryBuffer.toString());
        } else {
            String searchByName = "staff_name";
            queryBuffer.append("SELECT * FROM staff WHERE ").append(searchByName).append(" LIKE '%").append(searchKey.getText()).append("%'");
            System.out.println(queryBuffer.toString());
        }
        ResultSet queryResult = ServerManipulation.execQuery(queryBuffer.toString());
        queryBuffer.setLength(0);
        while (queryResult.next()) {
            updatestafffTableValue(queryResult);
            cashierResultArea.setText("查询成功");
        }
        while (!queryResult.next()) {
            cashierResultArea.setText("没有相关结果");
            break;
        }
    }

    /**
     * To update the view by filling labels with rs
     *
     * @param rs Get the result of query
     * @throws SQLException
     */
    private void updatestafffTableValue(ResultSet rs) throws SQLException {
        tNumCol.setText(rs.getString("staff_num").trim());
        tNameCol.setText(rs.getString("staff_name").trim());
        sexCol.setText(((String) rs.getString("sex")).trim());
        positionCol.setText(((String) rs.getString("position")).trim());
        telephoneCol.setText(((String) rs.getString("telephone")).trim());
    }

    @FXML

    public void back () throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maStart/start.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "经理界面", root, 87, 346);
        stage.show();
    }
}
