package memory_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CreateFirstWindow extends BorderPane {

    public CreateFirstWindow() {
        Label label = new Label("Rafas Memory Game");

        label.setFont(Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.ITALIC, 30));
        this.setStyle("-fx-background-color: Red");
        this.setTop(label);
        this.setAlignment(label, Pos.CENTER);
        VBox vBox = new VBox(20);

        Button button1 = new Button("New Game");
        Button button2 = new Button("Quit");


        ButtonHandler handleButton = new ButtonHandler();

        button1.setOnAction(handleButton);
        button2.setOnAction(handleButton);


        vBox.getChildren().addAll(button1, button2);

        this.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
    }
}
