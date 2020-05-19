package CarParts;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage {

    public MainPage() { }
    public BorderPane setElements() {
        BorderPane border = new BorderPane();
        Label title = new Label("Fusion Colors Auto Body Repair & Painting");
        title.getStyleClass().add("title");
        HBox hBox = new HBox();
        hBox.getStyleClass().add("hBox");
        hBox.setAlignment(Pos.CENTER);
        Button button1 = new Button("View Database");
        Button button2 = new Button("Add new car part");
        button1.setOnAction(e -> {
            handleButton1(border);
        });
        hBox.getChildren().addAll(button1,button2);
        border.setCenter(hBox);
        border.setTop(title);
        border.setAlignment(hBox, Pos.CENTER);
        border.setAlignment(title, Pos.CENTER);
        return border;
    }
    public void handleButton1(BorderPane border) {
        DatabasePage database = new DatabasePage(border);
        database.readDatabase();
    }

    public void displayPage() {
        Stage stage = new Stage();
        stage.setTitle("Main Page");
        Scene scene = new Scene(setElements(),400,400);
        scene.getStylesheets().add("file:resources/gui.css");
        stage.setScene(scene);
        stage.show();
    }
}