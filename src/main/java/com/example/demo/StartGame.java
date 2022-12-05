package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for start game scene
 */
public class StartGame {
//    private Scene scene;
//    private Parent root;

    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    @FXML
    TextField name;
    @FXML
    ColorPicker colorPicker = new ColorPicker();
    GameScene game = new GameScene();
    private Group gameRoot = new Group();
    public String userName;
    public Color c;
    Account account;




    public void setColor(ActionEvent event) {
        c = colorPicker.getValue();
        System.out.println(c);
    }

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

    public void setGameScene(ActionEvent event) throws IOException {
        userName = name.getText();

        if (Account.accountHaveBeenExist(userName) == null) {
            System.out.println("Account does not exist");
            this.account = Account.makeNewAccount(userName);
            System.out.println("Created " + this.account.getUserName());
        }
        else {
            System.out.println("Account Exists");
            this.account = Account.accountHaveBeenExist(userName);
//            System.out.println(this.account.getUserName());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setGameRoot(gameRoot);
        Group endgameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, c);
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, c);
        stage.setScene(gameScene);
        game.game(gameScene, gameRoot, stage, endGameScene, endgameRoot, this.account);
    }

//    public void setEndScene(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("endgame.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}
