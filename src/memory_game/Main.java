package memory_game;

/*
 * Author: Rafael Mendoza
 * Description: This program creates a memory game. The user is allowed to choose three
 * different grid sizes and three different themes.
 */


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;


public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub

        Scene scene = new Scene(new CreateFirstWindow() , 500, 300);
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
   }

}
