package com.chathumal.smapp.controller;

import com.jfoenix.controls.JFXButton;
import com.chathumal.smapp.HelloApplication;
import com.chathumal.smapp.service.custom.impl.UserServiceImpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {
    public AnchorPane root;
    public JFXButton btnLogin;
    public JFXTextField txtID;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    public void loginOnClick(ActionEvent actionEvent) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
           // userService.addUser(null,null);
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("dashboard.fxml"))));
            window.centerOnScreen();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
