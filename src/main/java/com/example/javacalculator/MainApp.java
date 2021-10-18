package com.example.javacalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("front.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 413, 760);
        Image img = new Image(getClass().getResource("icon/calc.png").toExternalForm(),256,256,false,false);
        stage.getIcons().add(img);
        stage.initStyle(StageStyle.TRANSPARENT);
        // Max-min height and width
        stage.setMinHeight(795);
        stage.setMinWidth(430.0);
        stage.setMaxHeight(795);
        stage.setMaxWidth(430.0);
        //
        stage.setOpacity(0.985);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
