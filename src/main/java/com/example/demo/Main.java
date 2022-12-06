package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("start-game.fxml"));
        User.readFromFile();
        Parent root = loader.load();
        Scene startScene = new Scene(root);
        primaryStage.setTitle("2048");
        primaryStage.setScene(startScene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
