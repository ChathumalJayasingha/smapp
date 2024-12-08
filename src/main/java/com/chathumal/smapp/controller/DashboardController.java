package com.chathumal.smapp.controller;

import com.chathumal.smapp.HelloApplication;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
    public VBox vboxNotifi;
    public ScrollPane scrallpaneNotifi;
    public AnchorPane notifiMainAnchorPane;
    public ScrollPane scallpaneFollowdContent;

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

    @FXML
    void initialize() {

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
                            "-fx-background-color: -fx-background;",rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            anchorPane.setStyle(style);
            Label label = new Label("User "+(vBox.getChildren().size()+1));
            anchorPane.setLeftAnchor(label, 5.0);
            anchorPane.setTopAnchor(label, 5.0);

            Label address = new Label("Address "+(vBox.getChildren().size()+1));
            anchorPane.setLeftAnchor(address, 100.0);
            anchorPane.setTopAnchor(address, 5.0);

            JFXButton button = new JFXButton("Follow");
            button.setStyle("-fx-background-color: white");
            button.setOnAction(evt -> vBox.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            anchorPane.setTopAnchor(button, 5.0);
            anchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label,address ,button);
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
                            "-fx-background-color: -fx-background;",rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));

            //anchorPane.setStyle(style);
            anchorPane.setStyle("-fx-border-radius: 10px; -fx-padding: 30px;  " +
                    "-fx-border-color: black; -fx-background-color: rgba(225,225,225,0.65)");
            Label label = new Label("User "+(vBox.getChildren().size()+1));
            anchorPane.setLeftAnchor(label, 5.0);
            anchorPane.setTopAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 50.0);

            Label address = new Label("Address "+(vBox.getChildren().size()+1));
            anchorPane.setLeftAnchor(address, 100.0);
            anchorPane.setTopAnchor(address, 5.0);

            JFXButton button = new JFXButton();
            if (i%2==1){
                button.setText("Follow");
            } else {
                button.setText("Unfollow");
            }

            button.setStyle("-fx-background-color: rgba(255,242,242,0.85); ");
            button.setOnAction(evt -> {

            });
            AnchorPane.setRightAnchor(button, 30.0);
            anchorPane.setTopAnchor(button, 40.0);

            anchorPane.getChildren().addAll(label,address ,button);
            vBox.getChildren().add(anchorPane);
        }
        scallpaneFollowdContent.setContent(vBox);

    }

    public void onChangePost(Event event) {
    }
}
