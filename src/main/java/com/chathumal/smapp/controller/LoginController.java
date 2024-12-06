package com.chathumal.smapp.controller;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXPasswordField;
//import com.jfoenix.controls.JFXTextField;
//import com.jfoenix.controls.JFXToggleButton;
import com.chathumal.smapp.service.ServiceFactory;
import com.chathumal.smapp.service.custom.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    public AnchorPane root;
    public Label lblEnterAndExit;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Button btnLogin;

    public void loginOnClick(ActionEvent actionEvent) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            userService.addUser(null,null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
