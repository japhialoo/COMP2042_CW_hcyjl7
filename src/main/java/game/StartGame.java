package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controller for start game scene
 */
public class StartGame {
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    @FXML
    TextField name;
    @FXML
    ColorPicker colorPicker = new ColorPicker();
    GameScene game = new GameScene();
    private Group gameRoot = new Group();
    public String userName;
    public static Color c = Color.rgb(237, 194, 46);
    Account account;

    public void setColor() {
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
    public void setGameScene(ActionEvent event) {
        userName = name.getText();

        if (Account.accountHaveBeenExist(userName) == null) {
            System.out.println("Account does not exist");
            this.account = Account.makeNewAccount(userName);
            System.out.println("Created " + this.account.getUserName());
        }
        else {
            System.out.println("Account Exists");
            this.account = Account.accountHaveBeenExist(userName);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setGameRoot(gameRoot);
        Group endgameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, c);
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, c);
        stage.setScene(gameScene);
        game.game(gameScene, gameRoot, stage, endGameScene, endgameRoot, this.account, c);
    }

}
