package monsterhunter;


/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *Description: This class creates the second window for the user to press on the grid size for the game.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreateSecondWindow {

    public CreateSecondWindow() {

        BorderPane b = new BorderPane();

        StackPane stack = new StackPane();

        Label label = new Label("Choose Grid Size");
        label.setFont(Font.font(30));
        label.setTextFill(Color.BLACK);


        Image image = new Image("file:resources/MonsterHunter2.png");
        ImageView imageView = new ImageView(image);

        imageView.setX(stack.getLayoutX());						//set the image size as the stack and borderpane screen size
        imageView.setY(stack.getLayoutY());
        //VBox for grid size buttons
        VBox v = new VBox();

        Button grid1 = new Button("5 x 5");						//button for 5 x 5

        Button grid2 = new Button("7 x 7");						//button for 7 x 7

        Button grid3 = new Button("10 x 10");					//button for 10 x 10



        //set on Action for buttons
        //Instantiate Handle button class
        HandleButtons h = new HandleButtons();
        grid1.setOnAction(h);									//sets the action for all grid buttons
        grid2.setOnAction(h);
        grid3.setOnAction(h);

        v.getChildren().addAll(grid1, grid2, grid3);


        stack.getChildren().addAll(imageView, v, label);


        b.setCenter(stack);

        v.setSpacing(8);


        v.setAlignment(Pos.CENTER_LEFT);



        Scene scene = new Scene(b, 800, 500);

        Stage stage = new Stage();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();







    }








}
