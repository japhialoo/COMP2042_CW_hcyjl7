package game;

import javafx.application.Platform;
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
import java.util.Objects;
import java.util.Optional;


public class EndGame {

    private Stage stage;
    private Scene scene;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    GameScene game = new GameScene();
    private static EndGame singleInstance = null;
    private EndGame(){

    }

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public Text setStyle(Text text, int x, int y, int font) {
        text.relocate(x,y);
        text.setFont(Font.font(font));
        return text;
    }

    public void endGameShow(Group root, Account account,long highScore, Color c) {
        StartGame.difficulty = 4;
        Text text = new Text("GAME OVER");
        text = setStyle(text, 250, 200, 80);
        root.getChildren().add(text);

        Text scoreTitle = new Text("Score:");
        scoreTitle.setFill(Color.BLACK);
        scoreTitle = setStyle(scoreTitle, 200, 400, 50);
        root.getChildren().add(scoreTitle);

        Text scoreText = new Text(account.score+"");
        scoreText.setFill(Color.BLACK);
        scoreText = setStyle(scoreText,200, 500, 70);
        root.getChildren().add(scoreText);

        Text highScoreTitle = new Text("High Score:");
        highScoreTitle.setFill(Color.BLACK);
        highScoreTitle = setStyle(highScoreTitle, 500, 400, 50);
        root.getChildren().add(highScoreTitle);

        Text highScoreText = new Text(highScore+"");
        highScoreText.setFill(Color.BLACK);
        highScoreText = setStyle(highScoreText, 500, 500, 70);
        root.getChildren().add(highScoreText);

        if (c == Color.BLACK) {
            scoreTitle.setFill(Color.WHITE);
            scoreText.setFill(Color.WHITE);
            highScoreTitle.setFill(Color.WHITE);
            highScoreText.setFill(Color.WHITE);

        }

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,60);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(150,700);
        quitButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Game");
            alert.setHeaderText("You will now exit the game :)");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(null) == ButtonType.OK){
                root.getChildren().clear();
                Platform.exit();
            }
        });

        Button retryButton = new Button("RETRY");
        retryButton.setPrefSize(100,60);
        retryButton.setTextFill(Color.BLACK);
        root.getChildren().add(retryButton);
        retryButton.relocate(400,700);

        retryButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Group gameRoot = new Group();
            Group endgameRoot = new Group();
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, c);
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, c);
            stage.setScene(gameScene);
            game.game(gameScene, gameRoot, stage, endGameScene, endgameRoot, account, c);
        });

        Button menuButton = new Button("MENU");
        menuButton.setPrefSize(100,60);
        menuButton.setTextFill(Color.BLACK);
        root.getChildren().add(menuButton);
        menuButton.relocate(650,700);

        menuButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start-game.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
