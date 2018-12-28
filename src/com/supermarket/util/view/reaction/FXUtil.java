package com.supermarket.util.view.reaction;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FXUtil {
    /**
     *
     * @param stage
     * @param title
     * @param root
     * @param height
     * @param width
     * @return 返回一个根据参数创建的弹窗
     */
    public static Stage createWindow(Stage stage, String title, Parent root, int height, int width) {
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        return stage;
    }

    /**
     * 跳转至窗口
     * @param closeLabel
     * @param loader
     * @param componentName
     * @param height
     * @param width
     * @throws IOException
     */
    public static void jumpToNewWindow(TextField closeLabel, FXMLLoader loader, String componentName, int height, int width) throws IOException {
        Stage componentStage= new Stage();
        Parent root = loader.load();
        FXUtil.createWindow(componentStage, componentName, root, height, width);
        componentStage.show();
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }
    public static void jumpToNewWindow(Label exitLabel, FXMLLoader loader, String componentName, int height, int width) throws IOException {
        Stage componentStage= new Stage();
        Parent root = loader.load();
        FXUtil.createWindow(componentStage, componentName, root, height, width);
        componentStage.show();
        Stage stage = (Stage) exitLabel.getScene().getWindow();
        stage.close();
    }
}
