package monsterhunter;


/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *Description: This class handles all the actions done in the game.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GameBoard extends BorderPane{
    private Player player;
    private Dragon dragon;
    private Dragon dragon2;
    private Dragon dragon3;
    private boolean[][] visited;
    private Object[][] rooms;
    private boolean gameOver;
    private Label label;
    private ExecuteOnce e;
    private DuplicateCharactersValues values;
    private int trackValue;
    private int playersRow;
    private int playersCol;

    public GameBoard() {

    }


    public GameBoard(int row, int col) {
        this.visited = new boolean[row][col];       //initializes 2d boolean array (5 x 5 or 7 x 7 or 10 x 10)
        this.rooms = new Object[row][col];
        this.gameOver = false;
        this.label = new Label();
        this.label.setAlignment(Pos.TOP_CENTER);
        this.trackValue = 0;
        initializeCharacterPosition();
        createBoardGame();

    }

    public GameBoard(boolean[][] visited, Object[][] rooms, int trackValue, boolean gameOver, boolean executeOnce, int ammo, int playerRow, int playerCol) {
        this.visited = visited;
        this.rooms = rooms;
        this.label = new Label();
        this.label.setAlignment(Pos.TOP_CENTER);
        this.trackValue = trackValue;

        this.gameOver = gameOver;
        this.player = new Player(playerRow, playerCol, ammo);
        this.e = new ExecuteOnce(this.player, executeOnce);
        createBoardGame();
    }

    //getters
    public Player getPlayer() {
        return this.player;
    }

    public Object[][] getRooms() {
        return rooms;
    }

    public boolean[][] getVisited() {
        return this.visited;
    }


    //method that creates the Game Board
    public void createBoardGame() {


        VBox v = new VBox();


        Button reset = new Button("Reset");
        Button save = new Button("Save");
        Button openPath = new Button("Open Path");


        v.getChildren().addAll(reset, save, openPath);

        this.setBottom(v);
        reset.setOnAction(e -> {
            if (this.gameOver != true) {
                this.player.setRowPos(this.playersRow);
                this.player.setColPos(this.playersCol);
                initializeCharacterPosition();
                createBoardGame();
            }
        });

        save.setOnAction(e -> {
            if (this.gameOver != true) {
                File file = new File("Resources/GameInfo");
                try {
                    BorderPane p = new BorderPane();
                    Button exit = new Button("Exit");
                    p.setTop(new Label("Game has been saved"));
                    p.setCenter(exit);
                    Scene scene = new Scene(p, 200, 200);
                    exit.setOnAction(a -> {
                        System.exit(0);
                    });
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    PrintWriter pw = new PrintWriter(file);
                    pw.write("PlayersCurrentPosition," + this.player.getRowPos() + "," + this.player.getColPos() + "\n");
                    pw.write("ArraySize," + this.visited.length + "\n");
                    pw.write("PlayersAmmo," + this.player.getAmmo() + "\n");
                    pw.write("GameOver," + this.gameOver + "\n");
                    pw.write("ExecuteOnce," + this.e.getValueForPlayer() + "\n");
                    pw.write("TrackValue," + this.trackValue + "\n");
                    for (int i = 0; i < this.visited.length; i++) {
                        for (int j = 0; j < this.visited.length; j++) {
                            if (this.visited[i][j] == true) {
                                pw.write("Player," + i + "," + j);
                                pw.write("\n");
                            }
                            if (this.rooms[i][j] instanceof Dragon) {
                                pw.write("Dragon," + i + "," + j );

                                pw.write("\n");
                            }
                            if (this.rooms[i][j] instanceof SpikeTrap) {
                                pw.write("SpikeTrap," + i + "," + j );
                                pw.write("\n");
                            }
                            if (this.rooms[i][j] instanceof Diablos) {
                                pw.write("Diablos," + i + "," + j );
                                pw.write("\n");
                            }
                            if (this.rooms[i][j] instanceof Character) {
                                pw.write("Character," + i + "," + j);
                                pw.write("\n");

                            }

                        }
                    }
                    pw.flush();

                } catch (FileNotFoundException ex) {

                }
            }

        });

        openPath.setOnMouseReleased(e -> {
            GridPane g = new GridPane();
        for (int i = 0; i < this.visited.length; i++) {
            for (int j = 0; j < this.visited.length; j++) {
                Rectangle re = new Rectangle(75,75);
                if (this.visited.length == 10) {
                    re.setWidth(50);
                    re.setHeight(50);
                }
                re.setStroke(Color.BLACK);
                if (this.rooms[i][j] instanceof Diablos) {
                    re.setFill(Color.PURPLE);
                }
                else if (this.rooms[i][j] instanceof Dragon) {
                    re.setFill(Color.RED);
                }
                else if (this.rooms[i][j] instanceof SpikeTrap) {
                    re.setFill(Color.GREY);
                }
                else if (this.rooms[i][j] instanceof Character) {
                    re.setFill(Color.BLUE);
                }
                else {
                    re.setFill(Color.WHITE);
                }
                g.add(re, j, i);
            }

        }
            this.setCenter(g);
        });


        GridPane g = new GridPane();
        g.getChildren().clear();
        g.setAlignment(Pos.CENTER);
        Label l = new Label("Your Ammo: " + this.player.getAmmo());
        this.setRight(l);
        this.setAlignment(l, Pos.CENTER);


        for (int i = 0; i < visited.length; i++) {

            for (int j = 0; j < visited[0].length; j++) {
                Rectangle r = new Rectangle(75, 75);
                if (this.visited.length == 10) {
                    r.setWidth(50);
                    r.setHeight(50);

                }
                r.setStroke(Color.BLACK);



                if (this.visited[i][j] == true ) {




                        r.setFill(Color.RED);
                        r.setStroke(Color.BLACK);




                }

                else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Character) {
                    r.setFill(Color.DARKBLUE);

                    if (this.e.getValueForPlayer() == false) {
                        setLable("You got two extra RPG'S");
                        this.e.setValueForPlayer(true);
                        this.player.setAmmo(this.player.getAmmo() + 2);
                        l.setText("Your Ammo: " + this.player.getAmmo() + 2);
                        this.setRight(l);
                        this.setAlignment(l, Pos.CENTER);


                    }



                }

                else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof SpikeTrap) {
                    //game over
                    r.setFill(Color.DARKBLUE);
                    setLable("You Have Been killed by the Spike Trap. Game Over!");

                }

                else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Dragon) {
                    r.setFill(Color.DARKBLUE);
                    setLable("You have been killed by the dragon. Game Over!");
                }
                else if (this.player.getRowPos() > 0 && this.player.getRowPos() < this.visited.length - 1 &&
                        this.player.getColPos() > 0 && this.player.getColPos() < this.visited.length - 1) {
                        r.setFill(Color.DARKBLUE);
                    if (this.rooms[this.player.getRowPos()][this.player.getColPos()-1] instanceof  SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof SpikeTrap) {
                        r.setFill(Color.DARKBLUE);
                        setLable("Danger");
                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos()-1] instanceof  Diablos ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Diablos ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Diablos) {
                        r.setFill(Color.DARKBLUE);
                        setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos()-1] instanceof  Dragon ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Dragon ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Dragon) {
                        r.setFill(Color.DARKBLUE);
                        setLable("You are near Gold");
                    } else if ((this.rooms[this.player.getRowPos()][this.player.getColPos()-1] instanceof  Character ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Character ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Character) && this.e.getValueForPlayer() == false) {
                        r.setFill(Color.DARKBLUE);
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }
                }

                else if (this.player.getColPos() == 0 && this.player.getRowPos() == 0) {
                    r.setFill(Color.DARKBLUE);
                    if (this.rooms[this.player.getColPos()][this.player.getRowPos() + 1] instanceof SpikeTrap ||
                            this.rooms[this.player.getColPos() + 1][this.player.getRowPos()] instanceof SpikeTrap) {
                        setLable("Danger");
                    }
                    else if (this.rooms[this.player.getColPos()][this.player.getRowPos() + 1] instanceof Diablos ||
                            this.rooms[this.player.getColPos() + 1][this.player.getRowPos()] instanceof Diablos) {
                        setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getColPos()][this.player.getRowPos() + 1] instanceof Dragon ||
                            this.rooms[this.player.getColPos() + 1][this.player.getRowPos()] instanceof Dragon) {
                        setLable("You are near Gold");
                    }
                    else if ((this.rooms[this.player.getColPos()][this.player.getRowPos() + 1] instanceof Character ||
                            this.rooms[this.player.getColPos() + 1][this.player.getRowPos()] instanceof Character) && this.e.getValueForPlayer() == false) {
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }
                }

                else if (this.player.getColPos() == 0 && this.player.getRowPos() == this.visited.length - 1) {
                    r.setFill(Color.DARKBLUE);

                    if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof SpikeTrap) {
                        setLable("Danger");

                    }
                    else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Diablos) {
                        setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Dragon) {
                        setLable("You are near Gold");
                    }
                    else if ((this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Character)) {
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }
                }

                else if (this.player.getColPos() == this.visited.length - 1 && this.player.getRowPos() == 0) {
                    r.setFill(Color.DARKBLUE);
                    if (this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof SpikeTrap) {
                        setLable("Danger");
                    }
                    else if (this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Diablos) {
                        setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Dragon) {
                        setLable("You are near Gold");
                    }
                    else if ((this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Character) && this.e.getValueForPlayer() == false) {
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }

                }

                else if (this.player.getColPos() == this.visited.length - 1 && this.player.getRowPos() == this.visited.length - 1) {
                    r.setFill(Color.DARKBLUE);
                    if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof SpikeTrap) {
                        setLable("Danger");
                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Diablos ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Diablos) {
                        setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Dragon ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon) {
                        setLable("You are near Gold");
                    }
                    else if ((this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Character ||
                            this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Character) && this.e.getValueForPlayer() == false) {
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }

                }

                else if (this.player.getColPos() == 0 && this.player.getRowPos() > 0 && this.player.getRowPos() < this.visited.length - 1) {
                    r.setFill(Color.DARKBLUE);
                    if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof SpikeTrap) {
                                    setLable("Danger");
                    }
                     else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Diablos) {
                        setLable("You are near a fier cave");
                    }
                     else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Dragon) {
                        setLable("You are near Gold");
                    }
                     else if ((this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Character) && this.e.getValueForPlayer() == false) {
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }
                }
                else if (this.player.getColPos() == this.visited.length - 1 && this.player.getRowPos() > 0 && this.player.getRowPos() < this.visited.length - 1) {
                    r.setFill(Color.DARKBLUE);
                    if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof SpikeTrap) {
                        setLable("Danger");
                    }
                    else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Diablos ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Diablos) {
                        setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Dragon ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Dragon) {
                        setLable("You are near Gold");
                    }
                    else if ((this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Character ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Character) && this.e.getValueForPlayer() == false) {
                        setLable("You are near resources");
                    }
                    else {
                        setLable("");
                    }
                }
                else if (this.player.getColPos() > 0 && this.player.getColPos() < this.visited.length - 1 && this.player.getRowPos() == 0){
                    r.setFill(Color.DARKBLUE);
                     if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof SpikeTrap) {
                        setLable("Danger");
                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Diablos ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Diablos ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Diablos) {
                         setLable("You are near a fier cave");
                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Dragon ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Dragon ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Dragon) {
                         setLable("You are near Gold");
                    }
                    else if ((this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Character ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Character ||
                            this.rooms[this.player.getRowPos() + 1][this.player.getColPos()] instanceof Character) && this.e.getValueForPlayer() == false) {
                         setLable("You are near resources");
                    }
                    else {
                         setLable("");
                     }
                }

                else if (this.player.getColPos() > 0 && this.player.getColPos() < this.visited.length - 1 && this.player.getRowPos() == this.visited.length - 1) {
                    r.setFill(Color.DARKBLUE);
                   if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof SpikeTrap ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof SpikeTrap ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof SpikeTrap) {
                            setLable("Danger");
                    }
                   else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Diablos ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Diablos ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Diablos) {
                       setLable("You are near a fier cave");
                   }
                   else if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Dragon ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Dragon) {
                       setLable("You are near Gold");
                   }
                   else if ((this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Character ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Character ||
                           this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Character) && this.e.getValueForPlayer() == false) {
                       setLable("You are near resources");
                   }
                }



                else {
                    r.setFill(Color.DARKBLUE);
                    r.setStroke(Color.BLACK);
                }
                r.setStroke(Color.BLACK);
                g.add(r, j, i);
            }
        }
        this.setCenter(g);
        this.setAlignment(g, Pos.CENTER);
    }

    //method that moves the character when arrow keys are pressed
    public void movePlayer(KeyCode key) {


        if (this.gameOver != true) {
            Stage stage = new Stage();
            switch (key) {
                case I:
                    if (this.player.getRowPos() != 0 && this.player.getColPos() >= 0) {   //shoot up
                 if (this.rooms[this.player.getRowPos() - 1][this.player.getColPos()] instanceof Dragon) {
                     Label label = new Label("You shot upward and you killed the mythical Dragon!!");
                     Scene scene = new Scene(label, 300,  100);
                     stage.setScene(scene);
                     stage.setTitle("Fire");
                     stage.show();
                     this.gameOver = true;
                 }
                 else {
                        this.player.setAmmo(this.player.getAmmo() - 1);
                        if (this.player.getAmmo() == 0) {
                            this.gameOver = true;
                            Label l = new Label("You wasted all your ammo and the Dragon has killed you. \n Game over");
                            Scene scene = new Scene(l);
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            Label label = new Label("You shot upward and missed \n You are left with" + this.player.getAmmo() + " RPG'S \n The Dragon is coming for you!");
                            Scene scene = new Scene(label, 300, 100);
                            stage.setScene(scene);
                            stage.setTitle("Fire");
                            stage.show();
                            this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = null;

                            Random r = new Random();
                            int x = r.nextInt(this.visited.length);
                            int y = r.nextInt(this.visited.length);

                            while(this.rooms[x][y] instanceof SpikeTrap ||
                                    this.rooms[x][y] instanceof Diablos ||
                                    this.rooms[x][y] instanceof Character) {
                                x = r.nextInt(this.visited.length);
                                y = r.nextInt(this.visited.length);

                            }
                            this.dragon.setRowPos(x);
                            this.dragon.setColPos(y);
                            this.rooms[x][y] = this.dragon;
                        }




                 }


                }
                break;
                case K: if (this.player.getRowPos() != this.visited.length - 1 && this.player.getColPos() >= 0) {
                    if (this.rooms[this.player.getRowPos()  + 1][this.player.getColPos()] instanceof Dragon) {
                        Label label = new Label("You shot Downward and you killed the mythical Dragon!!");
                        Scene scene = new Scene(label, 300,  100);
                        stage.setScene(scene);
                        stage.setTitle("Fire");
                        stage.show();
                        this.gameOver = true;
                    }
                    else {
                        this.player.setAmmo(this.player.getAmmo() - 1);
                        if (this.player.getAmmo() == 0) {
                            this.gameOver = true;
                            Label l = new Label("You wasted all your ammo and the Dragon has killed you. \n Game over");
                            Scene scene = new Scene(l);
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            Label label = new Label("You shot Downward and missed \n You are left with" + this.player.getAmmo() + " RPG'S \n The Dragon is coming for you!" );
                            Scene scene = new Scene(label, 300, 100);
                            stage.setScene(scene);
                            stage.setTitle("Fire");
                            stage.show();
                            this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = null;

                            Random r = new Random();
                            int x = r.nextInt(this.visited.length);
                            int y = r.nextInt(this.visited.length);

                            while(this.rooms[x][y] instanceof SpikeTrap ||
                                    this.rooms[x][y] instanceof Diablos ||
                                    this.rooms[x][y] instanceof Character) {
                                x = r.nextInt(this.visited.length);
                                y = r.nextInt(this.visited.length);

                            }
                            this.dragon.setRowPos(x);
                            this.dragon.setColPos(y);
                            this.rooms[x][y] = this.dragon;
                        }
                    }

                }
                break;
                case J: if (this.player.getRowPos() >= 0 && this.player.getColPos() != 0) {
                  if (this.rooms[this.player.getRowPos()][this.player.getColPos() - 1] instanceof Dragon) {
                      Label label = new Label("You shot Leftward and you killed the mythical Dragon!!");
                      Scene scene = new Scene(label, 300,  100);
                      stage.setScene(scene);
                      stage.setTitle("Fire");
                      stage.show();
                      this.gameOver = true;
                  }
                  else {
                      this.player.setAmmo(this.player.getAmmo() - 1);
                      if (this.player.getAmmo() == 0) {
                          this.gameOver = true;
                          Label l = new Label("You wasted all your ammo and the Dragon has killed you. \n Game over");
                          Scene scene = new Scene(l);
                          stage.setScene(scene);
                          stage.show();
                      }
                      else {
                          Label label = new Label("You shot Leftward and missed \n You are left with" + this.player.getAmmo() + " RPG'S \n The Dragon is coming for you!");
                          Scene scene = new Scene(label, 300, 100);
                          stage.setScene(scene);
                          stage.setTitle("Fire");
                          stage.show();
                          this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = null;

                          Random r = new Random();
                          int x = r.nextInt(this.visited.length);
                          int y = r.nextInt(this.visited.length);

                          while(this.rooms[x][y] instanceof SpikeTrap ||
                                  this.rooms[x][y] instanceof Diablos ||
                                  this.rooms[x][y] instanceof Character) {
                              x = r.nextInt(this.visited.length);
                              y = r.nextInt(this.visited.length);

                          }
                          this.dragon.setRowPos(x);
                          this.dragon.setColPos(y);
                          this.rooms[x][y] = this.dragon;
                      }
                  }
                }
                break;
                case L: if (this.player.getRowPos() >= 0 && this.player.getColPos() != this.visited.length - 1) {
                    System.out.println("hi");
                    System.out.println(this.player.getRowPos());
                    System.out.println(this.player.getColPos());
                   if (this.rooms[this.player.getRowPos()][this.player.getColPos() + 1] instanceof Dragon) {
                       Label label = new Label("You shot Rightward and you killed the mythical Dragon!!");
                       Scene scene = new Scene(label, 300,  100);
                       stage.setScene(scene);
                       stage.setTitle("Fire");
                       stage.show();
                       this.gameOver = true;
                   }
                   else {
                       this.player.setAmmo(this.player.getAmmo() - 1);
                       if (this.player.getAmmo() == 0) {
                           this.gameOver = true;
                           Label l = new Label("You wasted all your ammo and the Dragon has killed you. \n Game over");
                           Scene scene = new Scene(l);
                           stage.setScene(scene);
                           stage.show();
                       }
                       else {
                           Label label = new Label("You shot Rightward and missed \n You are left with" + this.player.getAmmo() + " RPG'S \n The Dragon is coming for you!");
                           Scene scene = new Scene(label, 300, 100);
                           stage.setScene(scene);
                           stage.setTitle("Fire");
                           stage.show();
                           this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = null;

                           Random r = new Random();
                           int x = r.nextInt(this.visited.length);
                           int y = r.nextInt(this.visited.length);

                           while(this.rooms[x][y] instanceof SpikeTrap ||
                                   this.rooms[x][y] instanceof Diablos ||
                                   this.rooms[x][y] instanceof Character) {
                               x = r.nextInt(this.visited.length);
                               y = r.nextInt(this.visited.length);

                           }
                           this.dragon.setRowPos(x);
                           this.dragon.setColPos(y);
                           this.rooms[x][y] = this.dragon;
                       }
                   }
                }
                    break;

                case W: stage.close();
                    this.player.setRowPos(this.player.getRowPos() - 1);

                    if (this.player.getRowPos() < 0) {
                        this.player.setRowPos(0);
                    }

                    if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof SpikeTrap || this.player.getAmmo() == 0 ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Dragon) {
                        //game over
                        this.gameOver = true;


                    }

                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Diablos ) {
                        Random r = new Random();

                        this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
                        this.player.setRowPos(r.nextInt(5));
                        this.player.setColPos(r.nextInt(5));
                        while(this.visited[this.player.getRowPos()][this.player.getColPos()] == true) {
                            this.player.setRowPos(r.nextInt(5));
                            this.player.setColPos(r.nextInt(5));
                        }


                    }
                    break;


                case S: stage.close();
                    this.player.setRowPos(this.player.getRowPos() + 1);
                    if (this.player.getRowPos() > this.visited.length - 1) {
                        this.player.setRowPos(this.visited.length - 1);

                    }

                    if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof SpikeTrap || this.player.getAmmo() == 0 ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Dragon) {
                        //game over
                        this.gameOver = true;


                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Diablos ) {
                        Random r = new Random();

                        this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
                        this.player.setRowPos(r.nextInt(5));
                        this.player.setColPos(r.nextInt(5));
                        while(this.visited[this.player.getRowPos()][this.player.getColPos()] == true) {
                            this.player.setRowPos(r.nextInt(5));
                            this.player.setColPos(r.nextInt(5));
                        }


                    }
                    break;


                case A: stage.close();
                    this.player.setColPos(this.player.getColPos() - 1);

                    if (this.player.getColPos() < 0) {
                        this.player.setColPos(0);
                    }

                    if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof SpikeTrap || this.player.getAmmo() == 0 ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Dragon) {

                        //game over
                        this.gameOver = true;


                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Diablos ) {

                        Random r = new Random();


                        this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
                        this.player.setRowPos(r.nextInt(5));
                        this.player.setColPos(r.nextInt(5));
                        while(this.visited[this.player.getRowPos()][this.player.getColPos()] == true) {
                            this.player.setRowPos(r.nextInt(5));
                            this.player.setColPos(r.nextInt(5));
                        }


                    }
                    break;


                case D: stage.close();
                    this.player.setColPos(this.player.getColPos() + 1);
                    if (this.player.getColPos() > this.visited.length - 1) {
                        this.player.setColPos(this.visited.length - 1);
                    }

                    if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof SpikeTrap || this.player.getAmmo() == 0 ||
                            this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Dragon) {
                        //game over
                        this.gameOver = true;

                    }
                    else if (this.rooms[this.player.getRowPos()][this.player.getColPos()] instanceof Diablos ) {
                        Random r = new Random();

                        this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
                        this.player.setRowPos(r.nextInt(5));
                        this.player.setColPos(r.nextInt(5));
                        while(this.visited[this.player.getRowPos()][this.player.getColPos()] == true) {
                            this.player.setRowPos(r.nextInt(5));
                            this.player.setColPos(r.nextInt(5));
                        }


                    }


                    break;


            }


        }


        this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
        createBoardGame();


    }

    //starts all characters in the game
    public void initializeCharacterPosition() {
        //Generate Random Number for random position for: player, dragon, enemies, traps, and ammunition room
        Random r = new Random();
        this.e = new ExecuteOnce(this.player);
       if (this.visited.length == 5 && this.trackValue == 0) {
           this.dragon = new Dragon(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           this.player = new Player(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           SpikeTrap t = new SpikeTrap(r.nextInt(this.visited.length),(r.nextInt(this.visited.length)));
           Diablos d = new Diablos(r.nextInt(this.visited.length),(r.nextInt(this.visited.length)));
           int rowForAmmo = r.nextInt(this.visited.length);
           int colForAmmo = r.nextInt(this.visited.length);
           this.rooms[t.getRowPos()][t.getColPos()] = t;
           this.rooms[d.getRowPos()][d.getColPos()] = d;
           this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = this.dragon;
           this.rooms[rowForAmmo][colForAmmo] = 'A';
           this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
           this.playersRow = this.player.getRowPos();
           this.playersCol = this.player.getColPos();
       }
       else if (this.visited.length == 7 && this.trackValue == 0)  {
           this.dragon = new Dragon(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           this.player = new Player(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           SpikeTrap t = new SpikeTrap(r.nextInt(this.visited.length),(r.nextInt(this.visited.length)));
           SpikeTrap t2 = new SpikeTrap(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           Diablos d = new Diablos(r.nextInt(this.visited.length),(r.nextInt(this.visited.length)));
           Diablos d2 = new Diablos(r.nextInt(this.visited.length), (r.nextInt(this.visited.length)));

           this.rooms[t.getRowPos()][t.getColPos()] = t;
           this.rooms[d.getRowPos()][d.getColPos()] = d;
           this.rooms[t2.getRowPos()][t2.getColPos()] = t2;
           this.rooms[d2.getRowPos()][d2.getColPos()] = d2;
           this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = this.dragon;
           this.rooms[r.nextInt(this.visited.length)][r.nextInt(this.visited.length)] = 'A';
           this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
           this.playersRow = this.player.getRowPos();
           this.playersCol = this.player.getColPos();
       }
       else if (this.visited.length == 10 && this.trackValue == 0) {
           this.dragon = new Dragon(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           this.player = new Player(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           SpikeTrap t = new SpikeTrap(r.nextInt(this.visited.length),(r.nextInt(this.visited.length)));
           SpikeTrap t2 = new SpikeTrap(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           SpikeTrap t3 = new SpikeTrap(r.nextInt(this.visited.length), r.nextInt(this.visited.length));
           Diablos d = new Diablos(r.nextInt(this.visited.length),(r.nextInt(this.visited.length)));
           Diablos d2 = new Diablos(r.nextInt(this.visited.length), (r.nextInt(this.visited.length)));
           Diablos d3 = new Diablos(r.nextInt(this.visited.length), (r.nextInt(this.visited.length)));

           this.rooms[t.getRowPos()][t.getColPos()] = t;
           this.rooms[d.getRowPos()][d.getColPos()] = d;
           this.rooms[t2.getRowPos()][t2.getColPos()] = t2;
           this.rooms[d2.getRowPos()][d2.getColPos()] = d2;
           this.rooms[t3.getRowPos()][t3.getColPos()] = t3;
           this.rooms[d3.getRowPos()][d3.getColPos()] = d3;
           this.rooms[this.dragon.getRowPos()][this.dragon.getColPos()] = this.dragon;
           this.rooms[r.nextInt(this.visited.length)][r.nextInt(this.visited.length)] = 'A';
           this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
           this.playersRow = this.player.getRowPos();
           this.playersCol = this.player.getColPos();

       }
       this.trackValue++;
        for (int i = 0; i < this.visited.length; i++) {
            for (int j = 0; j < this.visited.length; j++) {
                if (i == this.player.getRowPos() && j == this.player.getColPos()) {
                    this.visited[this.player.getRowPos()][this.player.getColPos()] = true;
                }
                else {
                    this.visited[i][j] = false;
                }
            }
        }
        this.values = new DuplicateCharactersValues(this.rooms, this.visited);

    }

    //this method will open the up the path of the gameboard
    public void openBoardPath() {
        int trackValue = 0;
        GridPane p = new GridPane();
        for (int i = 0; i < this.visited.length; i++) {
            for (int j = 0; j < this.visited.length; j++) {
                Rectangle r = new Rectangle(75, 75);
                r.setStroke(Color.BLACK);

                if (this.visited[i][j] == true) {
                    r.setFill(Color.RED);
                }
                else if (this.rooms[i][j] instanceof Diablos) {
                    r.setFill(Color.DARKBLUE);
                }
                else if (this.rooms[i][j] instanceof Dragon) {
                    r.setFill(Color.PURPLE);
                }
                else if (this.rooms[i][j] instanceof Character) {
                    r.setFill(Color.GREEN);
                }

                else if (this.rooms[i][j] instanceof SpikeTrap) {
                    r.setFill(Color.GRAY);
                }
                else {
                    r.setFill(Color.ORANGE);
                }









                p.add(r, j, i);
            }

        }
        this.setCenter(p);


    }

    public void setLable(String t) {
        this.label.setText(t);
        this.label.setFont(Font.font(20));
        this.label.setTextFill(Color.RED);
        this.setTop(label);
        this.setAlignment(label, Pos.CENTER_RIGHT);

    }

    public void saveGame() {

    }






}
