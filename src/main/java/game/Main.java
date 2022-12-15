package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class to run the program
 *
 * @author japhialoo-modified
 */
public class   Main extends Application {

    /**
     * Starts the program
     * @param primaryStage Stage of the program.
     * @throws Exception If file is not found.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("start-game.fxml"));
        User.readFromFile();
        Parent root = loader.load();
        Scene startScene = new Scene(root);
        try {
            Image logo = new Image("2048_logo.png");
            primaryStage.getIcons().add(logo);
        } catch (Exception e) {
            System.out.println("logo not found");
        }
        primaryStage.setTitle("2048");

        primaryStage.setScene(startScene);
        primaryStage.show();

    }

    /**
     * Main method
     * @param args Default code.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
