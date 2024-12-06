package com.chathumal.smapp.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {


    public Tab selectUserTab;
    public TableView selectedUserTableView;
    public TableColumn userACol;
    public TableColumn relationshipCol;
    public Text nameUniqueText;
    public Button addUserButton;
    public Tab addUserTab;
    @FXML
    ChoiceBox<String> selectUserDropdown, addRelationUserDropDown, addRelationDropDown,
            deleteRelationUserDropdown;

    @FXML
    Text userNameText, userAgeText, userGenderText, userStatusText, userStateText, addRelationStatusText,
            deleteRelationStatusText;

    @FXML
    TextField selectedUserGenderField, selectedUserProfilePicField, selectedUserstatusField, selectedUserStateField;

    @FXML
    ImageView userProfPicImage, addRelationStatusImage, deleteRelationStatusImage;

    @FXML
    HBox userInformationBox;

    @FXML
    Tab showRelationsTab, addRelationTab, deleteRelationTab, updateProfileTab;

    @FXML
    VBox addRelationStatusBox, deleteRelationStatusBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void fillDropdown(MouseEvent mouseEvent) {
    }

    public void setSelectedUser(MouseEvent mouseEvent) {
    }

    public void resetUserRelationshipTable(Event event) {
    }

    public void addRelation(MouseEvent mouseEvent) {
    }

    public void deleteRelation(MouseEvent mouseEvent) {
    }

    public void updateUser(MouseEvent mouseEvent) {
    }
}
