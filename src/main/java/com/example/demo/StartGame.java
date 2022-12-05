package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for start game scene
 */
public class StartGame {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ColorPicker colorPicker = new ColorPicker();
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    @FXML
    TextField name;
    GameScene game = new GameScene();
    private Group gameRoot = new Group();
    public String userName;
    Account account;



     public ColorPicker pickColor(ActionEvent event) {
         colorPicker.setOnAction(e -> {
             Color c = colorPicker.getValue();
             System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
         });
         return colorPicker;
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

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        setGameRoot(gameRoot);
        Group endgameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
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
