package com.supermarket.warehouse.controller;

import com.supermarket.util.data.manipulation.ServerManipulation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.table.TableColumn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseController {
    @FXML
    private TextField searchKey;

    @FXML
    private TextField Deletekey;

    private ArrayList<String> rs = null;

    @FXML
    private Label rResultArea;

    @FXML
    private Label Num;

    @FXML
    private Label Name;

    @FXML
    private Label Sum;

    @FXML
    private Label Date1;

    @FXML
    private Label Date2;

    @FXML
    private TextField tNum;

    @FXML
    private TextField tName;

    @FXML
    private TextField tSum;

    @FXML
    private TextField tDate1;

    @FXML
    private TextField tDate2;

    @FXML
    private TextField UpdateSum;

    @FXML
    private TextField UpdateNum;


    @FXML
    private TableView  table;

    @FXML
    private TableColumn Tnum;

    @FXML
    private TableColumn  Tname;

    @FXML
    private TableColumn  stcok;

    @FXML
    private TableColumn  indate1;

    @FXML
    private TableColumn  outdate1;



    private StringBuffer queryBuffer = new StringBuffer();

    @FXML
    protected void Search() throws SQLException {
        String key = searchKey.getText();
        if(key.startsWith("0")){
            String searchByNo = "Tnum";
            queryBuffer.append("SELECT * FROM warehouse WHERE ").append(searchByNo).append("='").append(searchKey.getText()).append("'");
            System.out.println(queryBuffer.toString());
        }else{
            String searchByName = "Tname";
            queryBuffer.append("SELECT * FROM warehouse WHERE ").append(searchByName).append(" LIKE'%").append(searchKey.getText()).append("%'");
            System.out.println(queryBuffer.toString());
        }
        ResultSet queryResult = ServerManipulation.execQuery(queryBuffer.toString());
        queryBuffer.setLength(0);
        while(queryResult.next()){
            updateTableValue(queryResult);
            rResultArea.setText("查询成功");
        }
    }

    private void updateTableValue(ResultSet rs) throws SQLException {
        Num.setText(rs.getString("Tnum").trim());
        Name.setText(rs.getString("Tname").trim());
        Sum.setText(((String) rs.getString("stock")).trim());
        Date1.setText(((String) rs.getString("indate")).trim());
        Date2.setText(((String) rs.getString("outdate")).trim());

    }

    @FXML
    protected void Insert() throws SQLException {
        String Num = tNum.getText().trim();
        String Name = tName.getText().trim();
        String Sum = tSum.getText().trim();
        String Date1 = tDate1.getText().trim();
        String Date2 = tDate2.getText().trim();
        queryBuffer.append("INSERT INTO warehouse VALUES(").append("'").append(Num).append("',").append("'").append(Name).append("',").append("'").append(Sum).append("',").append("'").append(Date1).append("',").append("'").append(Date2).append("')");
        System.out.println(queryBuffer.toString());
        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        rResultArea.setText("插入成功");
        queryBuffer.setLength(0);
    }

    @FXML
    protected void delete() throws SQLException {
        String deletekey = Deletekey.getText();
        if(deletekey.startsWith("0")){
            String deleteByNo = "Tnum";
            queryBuffer.append("Delete FROM warehouse WHERE ").append(deleteByNo).append(" ='").append( Deletekey.getText()).append("'");
            System.out.println(queryBuffer.toString());
        }else{
            String deleteByName = "Tname";
            queryBuffer.append("Delete FROM warehouse WHERE ").append(deleteByName).append(" ='").append( Deletekey.getText()).append("'");
            System.out.println(queryBuffer.toString());
        }
        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        rResultArea.setText("删除成功");
        queryBuffer.setLength(0);

    }


    @FXML
    protected void Update() throws SQLException {
        String Sum = UpdateSum.getText();
        String Num=UpdateNum.getText();
        queryBuffer.append("Update warehouse set stcok=").append("'").append(Sum).append("'").append("where Tnum='").append(Num).append("'");
        System.out.println(queryBuffer.toString());
        ServerManipulation.nonRsExecQuery(queryBuffer.toString());
        rResultArea.setText("修改成功");
        queryBuffer.setLength(0);
    }



}

