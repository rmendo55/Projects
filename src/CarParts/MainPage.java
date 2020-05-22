package CarParts;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents the main page. Allows person to choose either view database or add car part
 */
public class MainPage {
    private Stage stage;
    private Scene scene;

    public MainPage() { }
    public BorderPane setElements() {
        BorderPane border = new BorderPane();
        Label title = new Label("Fusion Colors Auto Body Repair & Painting");
        HBox hBox = new HBox();
        Button button1 = new Button("View Database");
        Button button2 = new Button("Add new car part");

        hBox.getChildren().addAll(button1,button2);
        border.setCenter(hBox);
        border.setTop(title);
        hBox.setAlignment(Pos.CENTER);
        border.setAlignment(hBox, Pos.CENTER);
        border.setAlignment(title, Pos.CENTER);
        hBox.getStyleClass().add("hBox");
        title.getStyleClass().add("title");
        button1.setOnAction(e -> {
            handleButton1(border);
        });
        button2.setOnAction(e -> {
            handleButton2(border);
        });
        return border;
    }
    public void handleButton1(BorderPane border) {
        DatabasePage database = new DatabasePage(border, this);
        database.readDatabase();
    }

    public void handleButton2(BorderPane border) {
        AddNewCarPart addCarPart = new AddNewCarPart(border, this);
        addCarPart.createUserInput();
    }

    public void displayPage() {
        this.stage = new Stage();
        stage.setTitle("Main Page");
        this.scene = new Scene(setElements(),400,400);
        scene.getStylesheets().add("file:resources/gui.css");
        stage.setScene(scene);
        stage.show();
    }

    public void redisplayMainPage(BorderPane border) {
        this.scene = new Scene(border, 400,400);
        this.scene.getStylesheets().add("file:resources/gui.css");
        this.stage.setScene(this.scene);
    }

    public Stage getStage() {
        return this.stage;
    }
}