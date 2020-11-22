package fusion_colors;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Random;

/**
 * This class represents the main page. Allows person to choose either view database or add car part
 */
public class MainPage extends BorderPane {
    private Stage stage;
    private Scene scene;
    private StackPane stack;

    public MainPage() {
        this.stage = new Stage();
    }

    public BorderPane setElements() {
        BorderPane border = new BorderPane();

        //create Title
        Text text = new Text();
        text.setX(10.0f);
        text.setY(10.0f);
        text.setCache(true);
        text.setText("    FUSION COLORS \n AUTO BODY REPAIR");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(null, FontWeight.BOLD, 80));

        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        text.setEffect(reflect);
        text.setTranslateY(0);

        VBox linkedListTitle = new VBox(10);
        linkedListTitle.getChildren().add(text);
        linkedListTitle.setAlignment(Pos.CENTER);

        border.setTop(linkedListTitle);

        HBox hBox = new HBox();
        Button button1 = new Button("View Database");
        button1.getStyleClass().add("btn1");
        Button button2 = new Button("Add new car part");
        button2.getStyleClass().add("btn1");
        hBox.getChildren().addAll(button1,button2);

        border.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        border.setAlignment(hBox, Pos.CENTER);
        hBox.getStyleClass().add("hBox");

        button1.setOnAction(e -> {
            handleButton1(border);
        });
        button2.setOnAction(e -> {
            handleButton2(border);
        });
        return border;
    }

    public int randomNumberForImage() {
        Random rand = new Random();
        return rand.nextInt(22);
    }

    public void handleButton1(BorderPane border) {
        DatabasePage database = new DatabasePage(border, this);
        database.readDatabase();
    }

    public void handleButton2(BorderPane border) {
        AddNewCarPart addCarPart = new AddNewCarPart(border, this);
        addCarPart.createUserInput();
    }

    public void displayPage(boolean firstStart) {
        if (firstStart) {
            this.stack = new StackPane();
        }
        //creating stack pane to add border on to stack pane
        //Creating an image
        Image image = new Image("file:resources/background_images/0.jpg");
        //creating an imageview for image
        ImageView view = new ImageView(image);
        //resizing image
        System.out.println("Resizing");
        view.fitHeightProperty().bind(this.stage.heightProperty());
        view.fitWidthProperty().bind(this.stage.widthProperty());
        this.stack.getChildren().addAll(view, setElements());
        this.stage.setTitle("Main Page");
        this.scene = new Scene(this.stack);
        this.scene.getStylesheets().add("file:resources/Styles.css");
        this.stage.setScene(scene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        this.stage.setX(primaryScreenBounds.getMinX());
        this.stage.setY(primaryScreenBounds.getMinY());
        this.stage.setWidth(primaryScreenBounds.getWidth());
        this.stage.setHeight(primaryScreenBounds.getHeight());

        this.stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }
    public StackPane getStackPane() {
        return this.stack;
    }
}