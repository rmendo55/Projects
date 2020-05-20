package CarParts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;

import static java.lang.Class.forName;

/**
 * This class starts the database application
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainPage main = new MainPage();
        main.displayPage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}