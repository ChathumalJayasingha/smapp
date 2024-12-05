package com.chathumal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    public AnchorPane root;
    public JFXTextField txtID;
    public JFXButton btnLogin;
    public JFXTextField txtPasswordField;
    public JFXPasswordField txtPassword;
    public JFXToggleButton btnToggle;
    public Label lblEnterAndExit;

    public void logingOnAction(ActionEvent actionEvent) {
        System.out.println("Hello");
    }
}
