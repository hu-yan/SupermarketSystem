package com.supermarket.manager.thing.controller;


import com.supermarket.util.data.manipulation.ServerManipulation;
import com.supermarket.util.data.connection.ServerConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ThingController {

    @FXML
    private TextField searchKey;
    @FXML
    private Label closeLabel;
    @FXML
    private Label tNumCol;
    @FXML
    private Label tNameCol;
    @FXML
    private Label costCol;
    @FXML
    private Label priceCol;
    @FXML
    private Label productionDateCol;
    @FXML
    private Label cashierResultArea;
    @FXML
    private TextField tNum;
    @FXML
    private TextField tName;
    @FXML
    private TextField price;
    @FXML
    private TextField cost;
    @FXML
    private TextField ydate;
    @FXML
    private TextField mdate;
    @FXML
    private TextField ddate;

    /*判断是否为整形*/
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean useLoop(String[] arr,String value){
        for(String s:arr){
            if(s.equals(value))
            {
                return true;
            }
        }
        return false;
    }
    private StringBuffer queryBuffer = new StringBuffer();

    /*判断是否是数值型*/
    public static boolean isNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /*判断是否为浮点型*/
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    @FXML
    /*查询功能*/
    protected void cashierSearch() throws SQLException {
        String key = searchKey.getText();
        if (searchKey.getLength() < 1) {
            queryBuffer.append("SELECT * FROM thing ORDER BY CONVERT(int,Tnum) ASC");
        } else if (isFloat(key)) {
            String searchByNo = "Tnum";
            queryBuffer.append("SELECT * FROM thing WHERE ").append(searchByNo).append("='").append(searchKey.getText()).append("'ORDER BY CONVERT(int,Tnum) ASC");
        } else {
            String searchByName = "Tname";
            queryBuffer.append("SELECT * FROM thing WHERE ").append(searchByName).append(" LIKE '%").append(searchKey.getText()).append("%'ORDER BY CONVERT(int,Tnum) ASC");
            System.out.println(queryBuffer.toString());
        }
        ResultSet queryResult = ServerManipulation.execQuery(queryBuffer.toString());
        if (queryResult.next()) {
            updateThingTableValue(queryResult);
            cashierResultArea.setText("查询成功");
            queryBuffer.setLength(0);
        }
        else {
            cashierResultArea.setText("没有相关结果");
            queryBuffer.setLength(0);

        }
    }

    /**
     * To update the view by filling labels with rs
     *
     * @param rs Get the result of query
     * @throws SQLException
     */

    /*将查询到的数据打印到屏幕*/
    private void updateThingTableValue(ResultSet rs) throws SQLException {
        String getNum = "";
        String getName = "";
        String getCost = "";
        String getPrice = "";
        String getDate = "";
        do {
            getNum += rs.getString("Tnum").trim()+"\r";
            getName +=rs.getString("Tname").trim()+"\r";
            getCost +=(String) rs.getString("cost").trim()+"\r";
            getPrice +=(String) rs.getString("price").trim()+"\r";
            getDate +=(String) rs.getString("production_date").trim()+"\r";
        } while (rs.next());
        tNumCol.setText(getNum);
        tNameCol.setText(getName);
        costCol.setText(getCost);
        priceCol.setText(getPrice);
        productionDateCol.setText(getDate);


    }

    /**
     * Generate the sale information then insert into sell table in database
     *
     * @throws SQLException
     */
    @FXML
    /*添加功能*/
    protected void thingInsert() throws SQLException {
        String thingNo = tNum.getText().trim();
        String thingName = tName.getText().trim();
        String priceNum = price.getText().trim();
        String costNum = cost.getText().trim();
        String getYdate = ydate.getText().trim();
        String getMdate = mdate.getText().trim();
        String getDdate = ddate.getText().trim();


        if (isInt(getYdate) || isInt(getMdate) || isInt(getDdate)) {
            int Y = Integer.parseInt(getYdate);
            int M = Integer.parseInt(getMdate);
            int D = Integer.parseInt(getDdate);
            String getDate = getYdate + "-" + getMdate + "-" + getDdate;

            if (!isNumeric(thingNo) || thingName.length() < 1 || priceNum.length() < 1 || costNum.length() < 1 || getYdate.length() < 1 || getMdate.length() < 1 || getDdate.length() < 1 ||
                    !isNumeric(priceNum) || !isNumeric(costNum) || !isNumeric(getYdate) || !isNumeric(getMdate) || !isNumeric(getDdate) ||
                    Y < 0 || Y > 9999 || M < 0 || M > 12 || D < 0 || D > 30) {
                cashierResultArea.setText("错误，无法录入。");
                queryBuffer.setLength(0);
            } else {

                queryBuffer.append("INSERT INTO thing VALUES(").append(thingNo).append(",").append("'").append(thingName).append("',").append("'").append(Float.parseFloat(priceNum)).append("',")
                        .append(Float.parseFloat(costNum)).append(",").append("'").append(getDate).append("')");
                System.out.println(queryBuffer.toString());
                ServerManipulation.nonRsExecQuery(queryBuffer.toString());
                cashierResultArea.setText("插入成功");
                queryBuffer.setLength(0);
            }
        }
        else{
            cashierResultArea.setText("错误，无法录入。");
            queryBuffer.setLength(0);
        }
    }
    @FXML
    /*删除功能*/
    protected void thingDelete() throws SQLException {
        String key = searchKey.getText();
        if (isFloat(key)) {
            String searchByNo = "Tnum";
            queryBuffer.append("DELETE FROM thing WHERE ").append(searchByNo).append("='").append(searchKey.getText()).append("'");
            System.out.println(queryBuffer.toString());

        } else {
            String searchByName = "Tname";
            queryBuffer.append("DELETE FROM thing WHERE ").append(searchByName).append("='").append(searchKey.getText()).append("'");
            System.out.println(queryBuffer.toString());

        }

        int i =ServerManipulation.nonRsExecQuery1(queryBuffer.toString());
        if (i != 0) {
            cashierResultArea.setText("删除成功");
            queryBuffer.setLength(0);
        } else {
            cashierResultArea.setText("没有相关结果");
            queryBuffer.setLength(0);

        }
    }




    @FXML
    /*修改功能*/
    protected void thingUpdate() throws SQLException {
        String thingNo = tNum.getText().trim();
        String thingName = tName.getText().trim();
        String priceNum = price.getText().trim();
        String costNum = cost.getText().trim();
        String getYdate = ydate.getText().trim();
        String getMdate = mdate.getText().trim();
        String getDdate = ddate.getText().trim();
        queryBuffer.append("SELECT * FROM thing WHERE ").append("Tnum").append("='").append(thingNo).append("'");
        ResultSet thingData = ServerManipulation.execQuery(queryBuffer.toString());

        System.out.println(thingData.toString());

        queryBuffer.setLength(0);



        while (thingData.next()) {
            cashierResultArea.setText("");
            if (!isNumeric(thingNo) && thingNo != thingData.getString("Tnum")){
                cashierResultArea.setText("错误的商品号，无法修改。");
                queryBuffer.setLength(0);
                break;
            }
            else{
                if(thingName.length()>=1){
                    queryBuffer.append("UPDATE thing SET Tname = ").append("'").append(thingName).append("'WHERE Tnum =").append(thingData.getString("Tnum"));
                    System.out.println(queryBuffer.toString());
                    ServerManipulation.nonRsExecQuery(queryBuffer.toString());
                    cashierResultArea.setText("修改成功");
                    tName.setText("");
                    queryBuffer.setLength(0);
                }
                else if(isInt(priceNum)){
                    queryBuffer.append("UPDATE thing SET price = ").append(priceNum).append("WHERE Tnum =").append(thingData.getString("Tnum"));
                    System.out.println(queryBuffer.toString());
                    ServerManipulation.nonRsExecQuery(queryBuffer.toString());
                    cashierResultArea.setText("修改成功");
                    price.setText("");
                    queryBuffer.setLength(0);
                }
                else if(isInt(costNum)){
                    queryBuffer.append("UPDATE thing SET cost = ").append(costNum).append("WHERE Tnum =").append(thingData.getString("Tnum"));
                    System.out.println(queryBuffer.toString());
                    ServerManipulation.nonRsExecQuery(queryBuffer.toString());
                    cashierResultArea.setText("修改成功");
                    cost.setText("");
                    queryBuffer.setLength(0);
                }
                else if(getYdate.length()>=1&&getMdate.length()>=1&&getDdate.length()>=1){
                    if (isInt(getYdate) || isInt(getMdate) || isInt(getDdate)) {
                        int Y = Integer.parseInt(getYdate);
                        int M = Integer.parseInt(getMdate);
                        int D = Integer.parseInt(getDdate);
                        String getDate = getYdate + "-" + getMdate + "-" + getDdate;
                        if( Y < 0 || Y > 9999 || M < 0 || M > 12 || D < 0 || D > 30){
                            queryBuffer.append("UPDATE thing SET production_date=").append("'").append(getDate).append("'WHERE Tnum =").append(thingData.getString("Tnum"));
                            System.out.println(queryBuffer.toString());
                            ServerManipulation.nonRsExecQuery(queryBuffer.toString());
                            cashierResultArea.setText("修改成功");
                            ydate.setText("");
                            mdate.setText("");
                            ddate.setText("");
                            queryBuffer.setLength(0);
                        }
                        else {
                            cashierResultArea.setText("错误，无法修改。");
                            queryBuffer.setLength(0);
                        }
                    }break;
                }break;
            }
        } }

    /**
     * Click the label, then the window will close.
     */
    @FXML
    protected void closeStage() {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }
}

