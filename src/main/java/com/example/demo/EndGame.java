package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class EndGame {

    private Stage stage;
    private Scene scene;
    private static EndGame singleInstance = null;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score,long highScore) {
        Text text = new Text("GAME OVER");
        text.relocate(250,200);
        text.setFont(Font.font(80));
        root.getChildren().add(text);

        Text scoreTitle = new Text("Score:");
        scoreTitle.setFill(Color.BLACK);
        scoreTitle.relocate(200,400);
        scoreTitle.setFont(Font.font(50));
        root.getChildren().add(scoreTitle);

        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(200,500);
        scoreText.setFont(Font.font(70));
        root.getChildren().add(scoreText);

        Text highScoreTitle = new Text("High Score:");
        highScoreTitle.setFill(Color.BLACK);
        highScoreTitle.relocate(500,400);
        highScoreTitle.setFont(Font.font(50));
        root.getChildren().add(highScoreTitle);

        Text highScoreText = new Text(highScore+"");
        highScoreText.setFill(Color.BLACK);
        highScoreText.relocate(500,500);
        highScoreText.setFont(Font.font(70));
        root.getChildren().add(highScoreText);

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,60);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(150,700);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit Game");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    Platform.exit();
                }
            }
        });

        Button retryButton = new Button("RETRY");
        retryButton.setPrefSize(100,60);
        retryButton.setTextFill(Color.BLACK);
        root.getChildren().add(retryButton);
        retryButton.relocate(650,700);

        retryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("startgame.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("endgame.fxml"));
//        root = loader.load();
//        endGameScene = new Scene(root);
//        primaryStage.setTitle("2048");
//        primaryStage.setScene(endGameScene);
//        primaryStage.show();

    }

    public void setStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("startgame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitGame(ActionEvent event) {
        Platform.exit();
    }


}
