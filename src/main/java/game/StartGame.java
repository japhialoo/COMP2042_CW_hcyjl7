package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controller for start game scene
 *
 * @author japhialoo
 */
public class StartGame {
    /**
     * Width of the window
     */
    static final int WIDTH = 900;
    /**
     * Height of the window
     */
    static final int HEIGHT = 900;
    /**
     * For users to input their name
     */
    @FXML
    TextField name;
    /**
     * For users to pick color for game theme
     */
    @FXML
    ColorPicker colorPicker = new ColorPicker();
    /**
     * Slider for user to choose difficulty of the game
     */
    @FXML
    Slider difficultySlider;
    /**
     * Number of Grids to appear of the screen
     */
    static int difficulty = 4;
    /**
     * To set game scene after user logs in
     */
    GameScene game = new GameScene();
    /**
     * root for the game
     */
    private Group gameRoot = new Group();
    /**
     * Username entered by the user
     */
    public String userName;
    /**
     * Color variable for the background color of the game scene and end scene.
     * Default color set to the base game's color
     */
    public static Color c = Color.rgb(237, 194, 46);
    /**
     * Account of the current user playing the game
     */
    Account account;


    /**
     * Sets Color variable c to the color chosen by the user
     */
    public void setColor() {
        c = colorPicker.getValue();
    }

    /**
     * Initiates the Game Root
     * @param gameRoot Root of the game scene
     */
    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    /**
     * Initiates and sets the Game Scene when user clicks the "Log In" button in the menu screen
     * @param event When user clicks log-in button
     */
    public void setGameScene(ActionEvent event) {
        userName = name.getText();

        if (name.getText().isEmpty()) {
            userName = "Guest";
        }
        if (Account.accountExists(userName) == null) {
            this.account = Account.makeNewAccount(userName);
        }
        else {
            this.account = Account.accountExists(userName);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setGameRoot(gameRoot);
        Group endgameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, c);
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, c);
        stage.setScene(gameScene);
        GameScene.setN(difficulty);
        game.game(gameScene, gameRoot, stage, endGameScene, endgameRoot, this.account, c);
    }

    /**
     * Sets difficulty level according to user's input in game from difficulty slider
     */
    public void setDifficulty(){
        int result = (int)difficultySlider.getValue();
        if (result == 1) {
            difficulty = 5;
        } else if (result == 2) {
            difficulty = 4;
        } else if (result == 3) {
            difficulty = 3;
        }
    }

}
