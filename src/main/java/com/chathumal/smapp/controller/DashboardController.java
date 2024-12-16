package com.chathumal.smapp.controller;

import com.chathumal.smapp.HelloApplication;
import com.chathumal.smapp.entity.Content;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.DuplicateEntryException;
import com.chathumal.smapp.exception.ExceptionHandlerUtil;
import com.chathumal.smapp.exception.NotFoundException;
import com.chathumal.smapp.service.ServiceFactory;
import com.chathumal.smapp.service.custom.ContentService;
import com.chathumal.smapp.service.custom.UserService;
import com.chathumal.smapp.service.custom.impl.ContentServiceImpl;
import com.chathumal.smapp.util.AlertUtil;
import com.chathumal.smapp.util.UserSession;
import com.chathumal.smapp.util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

public class DashboardController {
    public AnchorPane root;
    public ImageView imgLogout;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtMobile;
    public TextField txtAddress;
    public TextField txtUserId;
    public TextField txtPassword;
    public ScrollPane scrallpaneNotifi;
    public AnchorPane notifiMainAnchorPane;
    public ScrollPane scallpaneFollowdContent;
    public Button btnUpdate;
    public Label lblIsAdmin;
    public Tab tabCreateUser;
    public TextField txtCreateUserName;
    public TextField txtCreateUserEmail;
    public TextField txtCreateUserMobile;
    public TextField txtCreateUserAddress;
    public TextField txtCreateUserPassword;
    public Button btnCreateNewUser;
    public CheckBox chkboxAdmAccess;
    public AnchorPane postAnchor;
    public Button btnPublishPost;
    public TextArea txtCreatePost;
    public ScrollPane scrollPanePost;
    public ScrollPane scrollPaneAllUsers;
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
                boolean fulacs = user.isFulacs();
                if (fulacs) {
                    lblIsAdmin.setText("Admin User");
                    tabCreateUser.setDisable(false);
                } else {
                    lblIsAdmin.setText("Normal User");
                    tabCreateUser.setDisable(true);
                }
                UserSession.getInstance().setCurrentUser(user);
            } else {
                AlertUtil.showErrorAlert("User Not Found", "No user found with the provided email.");
            }
        } catch (NotFoundException e) {
            AlertUtil.showErrorAlert("User Not Found", "User profile could not be found.");
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException("Error", "An error occurred while loading user profile", e);
        }
        loadPersonPost();
        loadAllUsers();
    }

    private void loadAllUsers() {
        scrollPaneAllUsers.setContent(null);
        List<User> userList = null;
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.Type.USER);
        try {
            userList = userService.findAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        VBox vBox = new VBox(20);
        final Random rng = new Random();
        scrollPaneAllUsers.setFitToWidth(true);
        scrollPaneAllUsers.setFitToHeight(true);
        for (int i = 0; i < userList.size(); i++) {
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(userList.get(i).getEmail());
            String style = String.format("-fx-background: rgb(%d, %d, %d); " +
                            "-fx-background-color: -fx-background;", rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            //anchorPane.setStyle(style);
            anchorPane.setStyle("-fx-padding: 5px; -fx-border-color: #808080; -fx-border-radius: 3px; -fx-background-color: rgba(68,68,68,0.18)");
            Label label = new Label("User " + userList.get(i).getName() + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(label, 5.0);
            anchorPane.setTopAnchor(label, 5.0);

            Label address = new Label(userList.get(i).getAddress() + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(address, 100.0);
            anchorPane.setTopAnchor(address, 5.0);

            JFXButton button = new JFXButton("View");
            button.setStyle("-fx-background-color: white");
            button.setOnAction(
                    (event) -> {
                        System.out.println("test");
                        System.out.println("anchorPane = " + anchorPane.getId());
                        System.out.println("anchorPane = " + anchorPane.getChildren());
                        try {
                            User byEmail = userService.findByEmail(anchorPane.getId());
                            Stage popupStage = new Stage();
                            popupStage.setTitle("Update User Form");
                            popupStage.initModality(Modality.WINDOW_MODAL);
                            popupStage.initOwner(new Stage());

                            GridPane gridPane = new GridPane();
                            gridPane.setAlignment(Pos.CENTER);
                            gridPane.setPadding(new Insets(20));
                            gridPane.setHgap(10);
                            gridPane.setVgap(10);

                            Label lblId = new Label("Id");
                            TextField txtId = new TextField();
                            txtId.setText(String.valueOf(byEmail.getId()));
                            txtId.setDisable(true);
                            Label lblName = new Label("Name");
                            TextField txtName = new TextField();
                            txtName.setText(byEmail.getName());
                            Label lblAddress = new Label("Address");
                            TextField txtAddress = new TextField();
                            txtAddress.setText(byEmail.getAddress());
                            Label lblMobile = new Label("Mobile Number");
                            TextField txtMobile = new TextField();
                            txtMobile.setText(byEmail.getContact());
                            Label lblEmail = new Label("Email");
                            TextField txtEmail = new TextField();
                            txtEmail.setText(byEmail.getEmail());
                            Label lblPassword = new Label("Password");
                            TextField txtPassword = new TextField();
                            txtPassword.setText(byEmail.getPassword());
                            Button update = new Button("Update User");
                            gridPane.add(lblName, 0, 0);
                            gridPane.add(txtName, 1, 0);
                            gridPane.add(lblAddress, 0, 1);
                            gridPane.add(txtAddress, 1, 1);
                            gridPane.add(lblMobile, 0, 2);
                            gridPane.add(txtMobile, 1, 2);
                            gridPane.add(lblEmail, 0, 3);
                            gridPane.add(txtEmail, 1, 3);
                            gridPane.add(lblPassword, 0, 4);
                            gridPane.add(txtPassword, 1, 4);
                            gridPane.add(lblId, 0, 5);
                            gridPane.add(txtId, 1, 5);
                            gridPane.add(update, 0, 6);


                            update.setOnAction(event1 -> {
                                boolean confirmUpdate = AlertUtil.showConfirmationAlert("Confirm", "Did you want to really create new account");
                                if (confirmUpdate) {
                                    try {
                                        userService.updateUser(Integer.valueOf(txtId.getText()),txtName.getText(), txtAddress.getText(),
                                                txtMobile.getText(), txtEmail.getText(), txtPassword.getText(), false);

                                            AlertUtil.showInfoAlert("Success", "User update successful");

                                    } catch (DuplicateEntryException e) {
                                        ExceptionHandlerUtil.handleException("Error", "An error occurred while updating the user profile", e);
                                    } catch (Exception e) {
                                        ExceptionHandlerUtil.handleException("Error", "An error occurred while updating the user profile", e);
                                    }
                                } else {
                                    AlertUtil.showErrorAlert("Failed", "Update failed");
                                }
                            });


                            Scene popupScene = new Scene(gridPane, 400, 300);
                            popupStage.setScene(popupScene);
                            popupStage.showAndWait();

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            AnchorPane.setRightAnchor(button, 5.0);
            anchorPane.setTopAnchor(button, 5.0);
            anchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, address, button);
            vBox.getChildren().add(anchorPane);
        }
        scrollPaneAllUsers.setContent(vBox);
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

    public void loadPersonPost() {
        scrollPanePost.setContent(null);
        List<Content> content = null;
        ContentService contentService = (ContentService) ServiceFactory.getInstance().getService(ServiceFactory.Type.CONTENT);
        try {
            content = contentService.findContent(UserSession.getInstance().getCurrentUser());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        VBox vBox = new VBox(20);
        final Random rng = new Random();
        scrollPanePost.setFitToWidth(true);
        scrollPanePost.setFitToHeight(true);
        for (int i = 0; i < content.size(); i++) {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d); " +
                            "-fx-background-color: -fx-background;", rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            //anchorPane.setStyle(style);
            anchorPane.setStyle("-fx-padding: 5px; -fx-border-color: #808080; -fx-border-radius: 3px; -fx-background-color: rgba(68,68,68,0.18)");
            Label label = new Label("My Post " + content.get(i).getCid() + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(label, 5.0);
            anchorPane.setTopAnchor(label, 5.0);

            Label address = new Label(content.get(i).getContent() + (vBox.getChildren().size() + 1));
            anchorPane.setLeftAnchor(address, 100.0);
            anchorPane.setTopAnchor(address, 5.0);

            JFXButton button = new JFXButton("Remove");
            button.setStyle("-fx-background-color: white");
            button.setOnAction(evt -> vBox.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            anchorPane.setTopAnchor(button, 5.0);
            anchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, address, button);
            vBox.getChildren().add(anchorPane);
        }
        scrollPanePost.setContent(vBox);
    }

    public void btnCreateNewUserOnAction(ActionEvent actionEvent) {
        boolean confirmUpdate = AlertUtil.showConfirmationAlert("Confirm", "Did you want to really create new account");
        if (confirmUpdate) {
            if (isValidUpdateNewUser()) {
                try {
                    boolean b = userService.addUser(txtCreateUserName.getText(), txtCreateUserAddress.getText(), txtCreateUserMobile.getText(), txtCreateUserEmail.getText(), txtCreateUserPassword.getText(), chkboxAdmAccess.isSelected());
                    if (b) {
                        AlertUtil.showInfoAlert("Success", "User Create successful");
                    } else {
                        AlertUtil.showWarningAlert("Failed", "User creation failed");
                    }
                } catch (DuplicateEntryException e) {
                    ExceptionHandlerUtil.handleException("Error", "An error occurred while updating the user profile", e);
                } catch (Exception e) {
                    ExceptionHandlerUtil.handleException("Error", "An error occurred while updating the user profile", e);
                }
            } else {
                AlertUtil.showErrorAlert("Invalid Input", "Please ensure all fields are valid before updating.");
            }
        } else {
            AlertUtil.showErrorAlert("Failed", "Update failed");
        }
        clearNewUserAddingFiled();

    }

    void clearNewUserAddingFiled() {
        txtCreateUserEmail.clear();
        txtCreateUserPassword.clear();
        txtCreateUserMobile.clear();
        txtCreateUserName.clear();
        txtCreateUserAddress.clear();
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

    private boolean isValidUpdateNewUser() {
        return isValidNameNewUser() && isValidEmailNewUser() && isValidPasswordNewUser() && isValidAddressNewUser() && isValidMobileNewUser();
    }

    private boolean isValidName() {
        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            AlertUtil.showErrorAlert("Invalid Name", "Name cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean isValidNameNewUser() {
        String name = txtCreateUserName.getText().trim();
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

    private boolean isValidEmailNewUser() {
        String email = txtCreateUserEmail.getText().trim();
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

    private boolean isValidPasswordNewUser() {
        String password = txtCreateUserPassword.getText().trim();
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

    private boolean isValidAddressNewUser() {
        String address = txtCreateUserAddress.getText().trim();
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

    private boolean isValidMobileNewUser() {
        String mobile = txtCreateUserMobile.getText().trim();
        if (mobile.isEmpty() || !ValidationUtil.isValidMobile(mobile)) {
            AlertUtil.showErrorAlert("Invalid Mobile", "Please enter a valid mobile number.");
            return false;
        }
        return true;
    }


    public void onChangeCreateUserTab(Event event) {
    }


    public void btnPublishPostOnAction(ActionEvent actionEvent) {
        String createPostText = txtCreatePost.getText();
        ContentService contentService = (ContentService) ServiceFactory.getInstance().getService(ServiceFactory.Type.CONTENT);
        try {
            boolean b = contentService.addContent(UserSession.getInstance().getCurrentUser(), createPostText);
            if (b) {
                AlertUtil.showInfoAlert("User Content", "Content Added Success");
            } else {
                AlertUtil.showErrorAlert("User Content", "Content Added Successfully");
            }
            txtCreatePost.clear();
            loadPersonPost();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
