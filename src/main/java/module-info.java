/**
 * Contains all source code files to run the 2048 game.
 *
 * @author japhialoo
 */
module game {
    requires javafx.controls;
    requires javafx.fxml;

    opens game to javafx.fxml;
    exports game;
}