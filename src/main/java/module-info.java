module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens game to javafx.fxml;
    exports game;
}