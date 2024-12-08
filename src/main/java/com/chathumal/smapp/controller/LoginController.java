package com.chathumal.smapp.controller;

import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.service.ServiceFactory;
import com.chathumal.smapp.service.custom.UserService;
import com.jfoenix.controls.JFXButton;
import com.chathumal.smapp.HelloApplication;
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

        UserService service = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.Type.USER);

        try {
//            service.addUser("admin","No","0760840801","edu@chathumal.com","test@123");
            if (txtEmail.getText().trim().length() < 4 || txtEmail.getText() == null ) {
                System.out.println("Enter valid email");
            }
            if (txtPassword.getText().trim().length() < 4 ||  txtPassword.getText() == null) {
                System.out.println("Enter valid Password");
            }
            User user = service.loginCheck(txtEmail.getText().trim(), txtPassword.getText().trim());
            if (user == null) {
                System.out.println("User not founded");
            } else {
                if (user.getPassword().equalsIgnoreCase(txtPassword.getText().trim())){
                    Stage window = (Stage) this.root.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("dashboard.fxml"))));
                } else {
                    System.out.println("Wrong Password");

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
