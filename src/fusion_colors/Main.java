package fusion_colors;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

import static java.lang.Class.forName;

/**
 * This class starts the database application
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainPage main = new MainPage();
        main.displayPage(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        launch(args);
    }
}