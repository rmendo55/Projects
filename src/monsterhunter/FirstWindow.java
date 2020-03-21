package monsterhunter;


/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *Description: This class creates the first window for the user to either choose to play a new game, to exit the program, or to load the game only if a game was played before and saved.
 */


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * This class will automatically create a menu when the class is instantiated
 */


public class FirstWindow {

    public FirstWindow() {

        //BorderPane to set image and HBox at preferred positions
        BorderPane pane = new BorderPane();
        //StackPane to add the HBox on top of the image
        StackPane stack = new StackPane();

        stack.setMaxHeight(pane.getHeight());			//set stackPane to the BorderPane panes screen size
        stack.setMaxWidth(pane.getWidth());


        Image image = new Image("file:resources/MonsterHunter.png");			//uploading image


        ImageView imageView = new ImageView(image);				//Allocates a new ImageView object using the given image.

        imageView.setX(stack.getLayoutX());						//set the image size as the stack and borderpane screen size
        imageView.setY(stack.getLayoutY());

        //Hbox for buttons
        HBox h = new HBox();		//HBox for start and quit button



        //Start game button
        Button button1 = new Button("New Game");

        //Quit buttom
        Button button2 = new Button("Quit Game");

        //Continue button
        Button button3 = new Button("Load");





        h.getChildren().addAll(button1, button2, button3);			//adds start and quit button to HBox
        h.setSpacing(5);									//sets the gap of the HBox

        stack.getChildren().addAll(imageView, h);			//adds the the HBox on top of the image
        pane.setCenter(stack);								//sets both image and HBox at the center
        h.setAlignment(Pos.CENTER);							//aligns the HBox at the center(Horizontally and Vertically)


        HandleButtons handleButtons = new HandleButtons();				//Instantiates HandleFirstWindow class to handle start and quit button
        button1.setOnAction(handleButtons);								//set The action when button1 or button2 are clicked on
        button2.setOnAction(handleButtons);
        button3.setOnAction(handleButtons);



        Scene scene = new Scene(pane, 800, 500);
        Stage stage = new Stage();

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();


    }

}


