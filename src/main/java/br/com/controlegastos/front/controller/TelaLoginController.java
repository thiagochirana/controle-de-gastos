package br.com.controlegastos.front.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {
    @FXML
    Button cancelButton, loginButton;

    @FXML
    Label loginMessageLabel;

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    protected void loginButtonAction(ActionEvent e) {


        if (usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false) {
            loginMessageLabel.setText("Deu ruim mas beleza");
        } else {
            loginMessageLabel.setText("Por favor, preencha usuário e senha!");
        }

    }

    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}