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

    String errorStyle = String.format("-fx-border-color: #d33838; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyleGradient = String.format("-fx-text-fill: #4fd800;");


    @FXML
    protected void loginButtonAction(ActionEvent e) {


        if (usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false) {
            loginMessageLabel.setText("Deu ruim mas beleza");
        } else {
            loginMessageLabel.setText("Por favor, preencha usuário e senha!");

            usernameTextField.setStyle(errorStyle);
            passwordTextField.setStyle(errorStyle);

        }



    }

    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}