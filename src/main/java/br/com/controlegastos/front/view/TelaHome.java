package br.com.controlegastos.front.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class TelaHome extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double x,y = 0;
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaHome.fxml")));
        Scene sc = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);

        //move around
        root.setOnMousePressed(evt -> {
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX()- x);
            stage.setY(evt.getScreenY()- y);
        });

        stage.setScene(sc);
        stage.show();
    }
}

