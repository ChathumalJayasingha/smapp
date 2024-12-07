package com.chathumal.smapp.controller;

import com.chathumal.smapp.HelloApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public Button btnExit;
    public AnchorPane root;
    public ImageView imgLogout;

    public void onActionBtnExit(ActionEvent actionEvent) {
        try {
            // userService.addUser(null,null);
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("login.fxml"))));
            window.centerOnScreen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void imgLogoutOnClick(MouseEvent mouseEvent) {
        try {
            // userService.addUser(null,null);
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("login.fxml"))));
            window.centerOnScreen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void imgUpdateClick(MouseEvent mouseEvent) {
        System.out.println("update success");
    }
}
