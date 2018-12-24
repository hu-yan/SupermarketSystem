package com.supermarket.manager.controller;

import com.supermarket.util.view.reaction.FXUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class ManagerController {

    @FXML
        public void toStaff () throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/manager/staff/view/StaffView.fxml"));
        Parent root = loader.load();
        FXUtil.createWindow(stage, "员工查询", root, 87, 346);
        stage.show();
    }
}

