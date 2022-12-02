package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    @FXML
    TextField name;
    GameScene game = new GameScene();
    private Group gameRoot = new Group();
    public String userName;
    Account account;

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    public String getName() {
        System.out.println(name.getText());
        return userName;
    }
//    public void setStartScene(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("startgame.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void setGameScene(ActionEvent event) {
        userName = name.getText();
        System.out.println(userName);
        if (Account.accountHaveBeenExist(userName) == null) {
            System.out.println("Account does not exist");
            this.account = Account.makeNewAccount(userName);
            System.out.println(this.account.getUserName());
        }
        else {
            System.out.println("Account Exists");
            this.account = Account.accountHaveBeenExist(userName);
            System.out.println(this.account.getUserName());
        }
        System.out.println(this.account.getUserName());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
        stage.setScene(gameScene);
        game.game(gameScene, gameRoot, stage, endGameScene, endgameRoot, this.account);
    }

    public void setEndScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("endgame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
