package com.supermarket.manager.controller;

import com.supermarket.util.view.reaction.FXUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ManagerController {
    @FXML
    private Label exit;


    @FXML
    public void toStaff () throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/administrator/view/AdminView.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "员工信息管理", root, 725, 882);
        stage.show();
    }
    @FXML
    public void toWarehouse () throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/warehouse/view/WarehouseView.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "仓库信息管理", root, 725, 882);
        stage.show();
    }

    @FXML
    public void toSell () throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/cashier/view/CashierView.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "销售信息管理", root, 725, 882);
        stage.show();
    }

    @FXML
    public void toThing () throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/manager/thing/view/thing.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "商品信息管理", root, 725, 882);
        stage.show();
    }

    @FXML
    protected void closeStage(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}


