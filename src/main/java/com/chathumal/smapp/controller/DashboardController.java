package com.chathumal.smapp.controller;

import com.chathumal.smapp.HelloApplication;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.ExceptionHandlerUtil;
import com.chathumal.smapp.exception.NotFoundException;
import com.chathumal.smapp.service.ServiceFactory;
import com.chathumal.smapp.service.custom.UserService;
import com.chathumal.smapp.util.AlertUtil;
import com.chathumal.smapp.util.UserSession;
import com.chathumal.smapp.util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class DashboardController {
    public AnchorPane root;
    public ImageView imgLogout;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtMobile;
    public JFXTextField txtAddress;
    public JFXTextField txtUserId;
    public ScrollPane scrallpaneNotifi;
    public AnchorPane notifiMainAnchorPane;
    public ScrollPane scallpaneFollowdContent;
    public Button btnUpdate;
    public JFXTextField txtPassword;
    UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.Type.USER);

    public void imgLogoutOnClick(MouseEvent mouseEvent) {
        try {
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("login.fxml"))));
            window.centerOnScreen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void initialize() {
        String userEmail = UserSession.getInstance().getEmail();
        txtEmail.setText(userEmail);
        try {
            User user = getUserByEmail(userEmail);
            if (user != null) {
                updateProfileFields(user);
            } else {
                AlertUtil.showErrorAlert("User Not Found", "No user found with the provided email.");
            }
        } catch (NotFoundException e) {
            AlertUtil.showErrorAlert("User Not Found", "User profile could not be found.");
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException("Error", "An error occurred while loading user profile", e);
        }
    }

    private User getUserByEmail(String email) throws NotFoundException, Exception {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found for email: " + email);
        }
        return user;
    }

    private void updateProfileFields(User user) {
        txtAddress.setText(user.getAddress());
        txtMobile.setText(user.getContact());
        txtName.setText(user.getName());
        txtUserId.setText(String.valueOf(user.getId()));
        txtPassword.setText(user.getPassword());
    }

    public void notificationOnChange(Event event) {
        System.out.println("Notification ON CHANGE");

        VBox vBox = new VBox(20);
        final Random rng = new Random();
        scrallpaneNotifi.setFitToWidth(true);
        scrallpaneNotifi.setFitToHeight(true);
        for (int i = 0; i < 10; i++) {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d); " +
                            "-fx-background-color: -fx-background;", rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            anchorPane.setStyle(style);
            Label label = new Label("User " + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(label, 5.0);
            anchorPane.setTopAnchor(label, 5.0);

            Label address = new Label("Address " + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(address, 100.0);
            anchorPane.setTopAnchor(address, 5.0);

            JFXButton button = new JFXButton("Follow");
            button.setStyle("-fx-background-color: white");
            button.setOnAction(evt -> vBox.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            anchorPane.setTopAnchor(button, 5.0);
            anchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, address, button);
            vBox.getChildren().add(anchorPane);
        }
        scrallpaneNotifi.setContent(vBox);

    }

    public void onChangeFollowingContent(Event event) {
        System.out.println("Notification ON CHANGE");

        VBox vBox = new VBox(20);
        final Random rng = new Random();
        scallpaneFollowdContent.setFitToWidth(true);
        scallpaneFollowdContent.setFitToHeight(true);
        for (int i = 0; i < 10; i++) {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d); " +
                            "-fx-background-color: -fx-background;", rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));

            //anchorPane.setStyle(style);
            anchorPane.setStyle("-fx-border-radius: 10px; -fx-padding: 30px;  " +
                    "-fx-border-color: black; -fx-background-color: rgba(225,225,225,0.65)");
            Label label = new Label("User " + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(label, 5.0);
            anchorPane.setTopAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 50.0);

            Label address = new Label("Address " + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(address, 100.0);
            anchorPane.setTopAnchor(address, 5.0);

            JFXButton button = new JFXButton();
            if (i % 2 == 1) {
                button.setText("Follow");
            } else {
                button.setText("Unfollow");
            }

            button.setStyle("-fx-background-color: rgba(255,242,242,0.85); ");
            button.setOnAction(evt -> {

            });
            AnchorPane.setRightAnchor(button, 30.0);
            anchorPane.setTopAnchor(button, 40.0);

            anchorPane.getChildren().addAll(label, address, button);
            vBox.getChildren().add(anchorPane);
        }
        scallpaneFollowdContent.setContent(vBox);

    }

    public void onChangePost(Event event) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean confirmUpdate = AlertUtil.showConfirmationAlert("Confirm", "Did you want to really update your account details");
        if (confirmUpdate) {
            if (isValidUpdate()) {
                try {
                    userService.updateUser(Integer.valueOf(txtUserId.getText()), txtName.getText(), txtAddress.getText(), txtMobile.getText(), txtEmail.getText(), txtPassword.getText(), false);
                    AlertUtil.showInfoAlert("Success", "Update successful");
                } catch (NotFoundException e) {
                    AlertUtil.showErrorAlert("User Not Found", "User profile could not be found.");
                } catch (Exception e) {
                    ExceptionHandlerUtil.handleException("Error", "An error occurred while updating the user profile", e);
                }
            } else {
                AlertUtil.showErrorAlert("Invalid Input", "Please ensure all fields are valid before updating.");
            }
        } else {
            AlertUtil.showErrorAlert("Failed", "Update failed");
        }

    }

    private boolean confirmUpdate() {
        return AlertUtil.showConfirmationAlert("Confirm", "Do you really want to update your account details?");
    }

    private boolean isValidUpdate() {
        return isValidName() && isValidEmail() && isValidPassword() && isValidAddress() && isValidMobile();
    }

    private boolean isValidName() {
        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            AlertUtil.showErrorAlert("Invalid Name", "Name cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean isValidEmail() {
        String email = txtEmail.getText().trim();
        if (email.isEmpty() || !ValidationUtil.isValidEmail(email)) {
            AlertUtil.showErrorAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }
        return true;
    }

    private boolean isValidPassword() {
        String password = txtPassword.getText().trim();
        if (password.isEmpty() || password.length() < 6) {
            AlertUtil.showErrorAlert("Invalid Password", "Password must be at least 6 characters long.");
            return false;
        }
        return true;
    }

    private boolean isValidAddress() {
        String address = txtAddress.getText().trim();
        if (address.isEmpty()) {
            AlertUtil.showErrorAlert("Invalid Address", "Address cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean isValidMobile() {
        String mobile = txtMobile.getText().trim();
        if (mobile.isEmpty() || !ValidationUtil.isValidMobile(mobile)) {
            AlertUtil.showErrorAlert("Invalid Mobile", "Please enter a valid mobile number.");
            return false;
        }
        return true;
    }


}
