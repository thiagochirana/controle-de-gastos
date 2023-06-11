package br.com.controlegastos.front.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaMarca extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(TelaMarca.class.getResource("TelaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Controle de Gastos Veicular PESSOAL | Login");
        stage.setScene(scene);
        stage.show();
    }
}
