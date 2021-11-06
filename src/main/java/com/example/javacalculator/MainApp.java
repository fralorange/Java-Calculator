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
        Scene scene = new Scene(fxmlLoader.load());
        Image img = new Image(getClass().getResource("icon/calc.png").toExternalForm(),256,256,false,false);
        stage.getIcons().add(img);
        stage.initStyle(StageStyle.TRANSPARENT);
        //
        stage.setOpacity(0.985);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
