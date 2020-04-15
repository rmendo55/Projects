package memory_game;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreateSecondWindow extends GridPane{

    public CreateSecondWindow() {
        Label label = new Label("Choose Grid Size");
        label.setFont(Font.font(30));
        label.setTextFill(Color.BLACK);
        this.add(label, 0, 0);

        VBox vBox = new VBox(10);

        ToggleGroup group = new ToggleGroup();
        RadioButton rButton1 = new RadioButton("4 x 4");
        RadioButton rButton2 = new RadioButton("6 x 6");
        RadioButton rButton3 = new RadioButton("10 x 10");
        rButton1.setToggleGroup(group);
        rButton2.setToggleGroup(group);
        rButton3.setToggleGroup(group);

        vBox.getChildren().addAll(rButton1, rButton2, rButton3);

        this.setStyle("-fx-background-color: red");

        this.add(vBox, 0, 3);

        Label label2 = new Label("Choose Theme");
        label2.setFont(Font.font(30));
        label.setTextFill(Color.BLACK);
        this.add(label2, 0, 5);

        VBox vBox2 = new VBox(10);

        ToggleGroup group2 = new ToggleGroup();
        RadioButton rButton4 = new RadioButton("Cars");
        RadioButton rButton5 = new RadioButton("Sports");
        RadioButton rButton6 = new RadioButton("Universities");
        rButton4.setToggleGroup(group2);
        rButton5.setToggleGroup(group2);
        rButton6.setToggleGroup(group2);

        vBox2.getChildren().addAll(rButton4, rButton5, rButton6);

        this.add(vBox2, 0, 7);

        Button startGame = new Button("Start Game");
        this.add(startGame, 5, 9);

        radioButtonsSelected(rButton1, rButton2, rButton3, rButton4, rButton5, rButton6, startGame);

        Stage stage = new Stage();
        Scene scene = new Scene(this, 400, 300);
        stage.setTitle("User Options");
        stage.setScene(scene);
        stage.show();

    }

    public void radioButtonsSelected(RadioButton rButton1, RadioButton rButton2, RadioButton rButton3, RadioButton rButton4,
                                     RadioButton rButton5, RadioButton rButton6, Button startGame) {

        rButton1.setOnAction(e1 -> {
            rButton4.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton1.getText(), rButton4.getText(), startGame);
                data.startGame();
            });
            rButton5.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton1.getText(), rButton5.getText(), startGame);
                data.startGame();
            });
            rButton6.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton1.getText(), rButton6.getText(), startGame);
                data.startGame();
            });
        });

        rButton2.setOnAction(e1 -> {
            rButton4.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton2.getText(), rButton4.getText(), startGame);
                data.startGame();
            });
            rButton5.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton2.getText(), rButton5.getText(), startGame);
                data.startGame();
            });
            rButton6.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton2.getText(), rButton6.getText(), startGame);
                data.startGame();
            });
        });
        rButton3.setOnAction(e1 -> {
            rButton4.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton3.getText(), rButton4.getText(), startGame);
                data.startGame();
            });
            rButton5.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton3.getText(), rButton5.getText(), startGame);
                data.startGame();
            });
            rButton6.setOnAction(e2 -> {
                DataFromRadioButtons data = new DataFromRadioButtons(rButton3.getText(), rButton6.getText(), startGame);
                data.startGame();
            });
        });

    }


}
