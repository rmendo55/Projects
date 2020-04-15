package memory_game;




import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class DataFromRadioButtons extends HBox{
    private String theme;
    private String grid;
    private Button startGame;
    private SaveImage saveFirstClickImage;
    private SaveImage saveFirstImageView;
    private FadeTransition fadeImageView2;
    private int numberOfCardTurns;
    private int paneIndex;
    private int trackTimeValue;
    private int endOfMatch;
    private Timer timer;





    public DataFromRadioButtons(String grid, String theme, Button button) {
        this.theme = theme;
        this.grid = grid;
        this.startGame = button;
        this.numberOfCardTurns = 0;
        this.trackTimeValue = 0;
        this.endOfMatch = 0;

    }

    public void startGame() {






        this.startGame.setOnAction(e -> {
            Text text = new Text("Card Turns: ");


            BorderPane border = new BorderPane();
            border.setStyle("-fx-background-color: red");
            GridPane pane = new GridPane();


            TrackNumberOfClicks number = new TrackNumberOfClicks();


            String gridValue = "";
            if (this.grid.length() == 7) {
                String firstLetter = this.grid.charAt(0) + "";
                String secondLetter = this.grid.charAt(1) + "";
                gridValue = firstLetter + secondLetter;

            }
            else {
                gridValue = this.grid.charAt(0) + "";
            }

            int  gridSize = Integer.parseInt(gridValue);

            ArrayList<Integer> list = shuffledList();
            ArrayList<Integer> list2 = shuffledList();

            int trackSet = 1;
            int r = -1;
            int r2 = -2;



            for (int i = 0; i < gridSize  ; i++) {
                for(int j = 0; j < gridSize; j++) {
                    r++;
                    if (trackSet == 1 && r < list.size()) {
                        Image image = new Image("file:resources/" + this.theme + "/" + list.get(r) + ".png");
                        ImageView imageView = new ImageView(image);
                        Image image2 = new Image("file:resources/" + this.theme + "/51.png");
                        ImageView imageView2 = new ImageView(image2);

                        imageView.setFitHeight(65);
                        imageView.setFitWidth(65);
                        imageView2.setFitHeight(65);
                        imageView2.setFitWidth(65);

                        pane.add(imageView, j, i);
                        pane.add(imageView2, j, i);

                        pane.setStyle("-fx-background-color: Red");
                        pane.setHgap(10);
                        pane.setVgap(10);
                        pane.setAlignment(Pos.CENTER);

                        imageView2.setOnMouseClicked(e1 -> {
                            int trackClickNumber = number.getNumber();
                            this.fadeImageView2 = new FadeTransition(Duration.seconds(0.1), imageView2);

                            numberOfCardTurns++;
                            trackTimeValue++;

                            if (trackClickNumber == 1) {
                                if (trackTimeValue == 1) {
                                    timer = new Timer(true);
                                    border.setTop(timer.getTimeText());

                                }
                                Text text2 = new Text("Card Turns: " + "" + numberOfCardTurns);
                                text2.setFont(Font.font(20));
                                border.setBottom(text2);


                                fadeImageView2.setToValue(0);
                                fadeImageView2.play();

                                paneIndex = pane.getChildren().indexOf(imageView2);

                                saveFirstClickImage = new SaveImage(imageView);
                                saveFirstImageView = new SaveImage(Duration.seconds(0.1), imageView2);

                            }
                            else if (trackClickNumber == 2) {

                                Text text3 = new Text("Card Turns: " + "" + numberOfCardTurns);
                                text3.setFont(Font.font(20));
                                border.setBottom(text3);


                                fadeImageView2.setToValue(0);
                                fadeImageView2.play();

                                CheckImageSelected check = new CheckImageSelected(saveFirstClickImage, imageView);

                                if(check.getIsMatched() == true) {
                                    endOfMatch++;
                                    if (endOfMatch == (gridSize * gridSize) / 2) {
                                        BorderPane lastPane = new BorderPane();
                                        Text lastText = new Text("Congratulations! You matched all the pairs");
                                        lastPane.setCenter(lastText);
                                        Stage lastStage = new Stage();
                                        Scene scene = new Scene(lastPane, 300, 75);
                                        lastStage.setTitle("You Finished!!!");
                                        lastStage.setScene(scene);
                                        lastStage.show();
                                        timer.getTimeline().pause();

                                    }

                                    pane.getChildren().remove(paneIndex);
                                    pane.getChildren().remove(imageView2);
                                    ImageView saveImageView = new ImageView(saveFirstClickImage.getImage());
                                    ImageView saveImageView2 = new ImageView(imageView.getImage());
                                    saveImageView.setFitHeight(65);
                                    saveImageView.setFitWidth(65);
                                    saveImageView2.setFitHeight(65);
                                    saveImageView2.setFitWidth(65);
                                    HBox hBox = new HBox(20);
                                    hBox.setStyle("-fx-background-color: red");
                                    hBox.setAlignment(Pos.CENTER);
                                    hBox.getChildren().addAll(saveImageView, saveImageView2);
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(hBox, 300, 75);
                                    stage.setTitle("You Got A Pair!!!");
                                    stage.setScene(scene);
                                    stage.show();


                                }
                                else if (check.getIsMatched() == false){

                                    saveFirstImageView.getImageView2().stop();
                                    fadeImageView2.stop();
                                    saveFirstImageView.getImageView2().setToValue(1);
                                    fadeImageView2.setFromValue(0);
                                    fadeImageView2.setDuration(Duration.seconds(1));
                                    fadeImageView2.setToValue(1);
                                    fadeImageView2.play();
                                    saveFirstImageView.getImageView2().play();

                                }
                            }

                        });



                        if (r == list.size() - 1) {
                            trackSet++;
                            r2++;
                        }
                    }

                    else if (trackSet == 2 && r2 < list2.size()) {
                        r2++;
                        Image image = new Image("file:resources/" + this.theme + "/" + list2.get(r2) + ".png");
                        ImageView imageView = new ImageView(image);
                       Image image2 = new Image("file:resources/" + this.theme + "/51.png");
                        ImageView imageView2 = new ImageView(image2);

                        imageView.setFitHeight(65);
                        imageView.setFitWidth(65);
                        imageView2.setFitHeight(65);
                        imageView2.setFitWidth(65);

                        pane.add(imageView, j, i);
                        pane.add(imageView2, j, i);

                        pane.setStyle("-fx-background-color: Red");
                        pane.setHgap(10);
                        pane.setVgap(10);
                        pane.setAlignment(Pos.CENTER);

                        imageView2.setOnMouseClicked(e1 -> {
                            int trackClickNumber = number.getNumber();
                            this.fadeImageView2 = new FadeTransition(Duration.seconds(0.1), imageView2);

                            numberOfCardTurns++;
                            trackTimeValue++;

                            if (trackClickNumber == 1) {
                                Text text2 = new Text("Card Turns: " + "" + numberOfCardTurns);
                                text2.setFont(Font.font(20));
                                border.setBottom(text2);


                                fadeImageView2.setToValue(0);
                                fadeImageView2.play();

                                paneIndex = pane.getChildren().indexOf(imageView2);

                                saveFirstClickImage = new SaveImage(imageView);
                                saveFirstImageView = new SaveImage(Duration.seconds(0.1), imageView2);

                            }
                            else if (trackClickNumber == 2) {

                                Text text3 = new Text("Card Turns: " + "" + numberOfCardTurns);
                                text3.setFont(Font.font(20));
                                border.setBottom(text3);


                                fadeImageView2.setToValue(0);
                                fadeImageView2.play();

                                CheckImageSelected check = new CheckImageSelected(saveFirstClickImage, imageView);

                                if(check.getIsMatched() == true) {
                                    endOfMatch++;
                                    if (endOfMatch == (gridSize * gridSize) / 2) {
                                        BorderPane lastPane = new BorderPane();
                                        Text lastText = new Text("Congratulations! You matched all the pairs");
                                        lastPane.setCenter(lastText);
                                        Stage lastStage = new Stage();
                                        Scene scene = new Scene(lastPane, 300, 75);
                                        lastStage.setTitle("You Finished!!!");
                                        lastStage.setScene(scene);
                                        lastStage.show();
                                        timer.getTimeline().pause();

                                    }

                                    pane.getChildren().remove(paneIndex);
                                    pane.getChildren().remove(imageView2);
                                    ImageView saveImageView = new ImageView(saveFirstClickImage.getImage());
                                    ImageView saveImageView2 = new ImageView(imageView.getImage());
                                    saveImageView.setFitHeight(65);
                                    saveImageView.setFitWidth(65);
                                    saveImageView2.setFitHeight(65);
                                    saveImageView2.setFitWidth(65);
                                    HBox hBox = new HBox(20);
                                    hBox.setStyle("-fx-background-color: red");
                                    hBox.setAlignment(Pos.CENTER);
                                    hBox.getChildren().addAll(saveImageView, saveImageView2);
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(hBox, 300, 75);
                                    stage.setTitle("You Got A Pair!!!");
                                    stage.setScene(scene);
                                    stage.show();


                                }
                                else if (check.getIsMatched() == false){

                                    saveFirstImageView.getImageView2().stop();
                                    fadeImageView2.stop();
                                    saveFirstImageView.getImageView2().setToValue(1);
                                    fadeImageView2.setFromValue(0);
                                    fadeImageView2.setDuration(Duration.seconds(1));
                                    fadeImageView2.setToValue(1);
                                    fadeImageView2.play();
                                    saveFirstImageView.getImageView2().play();

                                }
                            }

                        });

                    }




                }
            }
            Text text2 = new Text("Card Turns: " + "" + numberOfCardTurns);
            text2.setFont(Font.font(20));

            border.setBottom(text2);

            border.setCenter(pane);



            Stage stage = new Stage();
            Scene scene = new Scene(border, 500, 500);
            stage.setTitle("Memory Game");
            stage.setScene(scene);
            stage.show();
        });

    }

    public ArrayList<Integer> list() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        String gridValue = "";
        if (this.grid.length() == 7) {
            String firstLetter = this.grid.charAt(0) + "";
            String secondLetter = this.grid.charAt(1) + "";
            gridValue = firstLetter + secondLetter;

        }
        else {
            gridValue = this.grid.charAt(0) + "";
        }

        int  gridSize = Integer.parseInt(gridValue);

        for (int i = 0; i < (gridSize * gridSize) / 2 ; i++) {
            list.add(i + 1);
        }
        return list;
}

    public ArrayList<Integer> shuffledList() {
        ArrayList<Integer> list = list();
        Collections.shuffle(list);
        return list;
    }

    public ArrayList<Integer> shuffledList2() {
        ArrayList<Integer> list = list();
        Collections.shuffle(list);
        return list;
    }







}






