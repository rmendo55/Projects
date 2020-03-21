package monsterhunter;

/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *Description: This class handles the buttons shown in the first window, second window, and the window of the game.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HandleButtons implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent e) {
        // TODO Auto-generated method stub
        String text = ((Button)(e.getSource())).getText();		//obtains text from start or quit button when clicked on

        if (e.getSource() instanceof Button) {				//validates if the button is an instance of a Button
            if(text.equals("New Game")) {					//if so, if statement determines what to execute
                CreateSecondWindow secondWindow = new CreateSecondWindow();										//instantiates CreateSecondWindow to open up the second menu
            }
            else if (text.equals("Load")) {				//continues the game when player saved the game
                File file = new File("Resources/GameInfo");
                try {
                    boolean[][] visited = new boolean[0][0];
                    Object[][] rooms = new Object[0][0];
                    boolean gameOver = false;
                    boolean executeOnce = false;
                    int trackValue = 0;
                    int ammo = 0;
                    int row = 0;
                    int col = 0;
                    int playerRow = 0;
                    int playerCol = 0;
                    Scanner input = new Scanner(file);
                    String[] array = new String[0];
                    while(input.hasNextLine()) {

                        String s = input.nextLine();
                        array = s.split(",");
                        if (array[0].equalsIgnoreCase("ArraySize")) {
                            row = Integer.parseInt(array[1]);
                            visited = new boolean[row][row];
                            rooms = new Object[row][row];
                        }
                        else if (array[0].equalsIgnoreCase("PlayersCurrentPosition")) {
                            playerRow = Integer.parseInt(array[1]);
                            playerCol = Integer.parseInt(array[2]);
                        }
                        else if (array[0].equalsIgnoreCase("GameOver")) {
                            gameOver = Boolean.parseBoolean(array[1]);
                        }
                        else if (array[0].equalsIgnoreCase("ExecuteOnce")) {
                           executeOnce = Boolean.parseBoolean(array[1]);
                        }
                        else if (array[0].equalsIgnoreCase("TrackValue")) {
                            trackValue = Integer.parseInt(array[1]);
                        }
                        else if (array[0].equalsIgnoreCase("PlayersAmmo")) {
                            ammo = Integer.parseInt(array[1]);
                        }

                        else if (array[0].equalsIgnoreCase("Player")) {
                            row = Integer.parseInt(array[1]);
                            col = Integer.parseInt(array[2]);
                           visited[row][col] = true;

                        }
                        else if (array[0].equalsIgnoreCase("Dragon")) {
                            row = Integer.parseInt(array[1]);
                            col = Integer.parseInt(array[2]);
                            rooms[row][col] = new Dragon(row, col);

                        }
                        else if (array[0].equalsIgnoreCase("Diablos")) {
                            row = Integer.parseInt(array[1]);
                            col = Integer.parseInt(array[2]);
                            rooms[row][col] = new Diablos(row, col);
                        }
                        else if (array[0].equalsIgnoreCase("SpikeTrap")) {
                            row = Integer.parseInt(array[1]);
                            col = Integer.parseInt(array[2]);
                            rooms[row][col] = new SpikeTrap(row, col);
                        }
                        else if (array[0].equalsIgnoreCase("Character")) {
                            row = Integer.parseInt(array[1]);
                            col = Integer.parseInt(array[2]);
                            rooms[row][col] = 'A';
                        }
                    }
                    GameBoard game = new GameBoard(visited, rooms, trackValue, gameOver, executeOnce, ammo, playerRow, playerCol);
                    if (visited.length == 5) {
                        Scene scene = new Scene(game, 500, 500);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        scene.setOnKeyPressed(a -> {
                            game.movePlayer(a.getCode());
                        });
                    }
                    else if (visited.length == 7) {
                        Scene scene = new Scene(game, 700, 700);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        scene.setOnKeyPressed(a -> {
                            game.movePlayer(a.getCode());
                        });
                    }
                    else if (visited.length == 10) {
                        Scene scene = new Scene(game, 800, 800);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        scene.setOnKeyPressed(a -> {
                            game.movePlayer(a.getCode());
                        });
                    }
                }
                catch(FileNotFoundException ex) {

                }
            }

            else if (text.equals("5 x 5")) {
                GameBoard game = new GameBoard(5,5);
                Scene scene = new Scene(game, 500, 500);
                scene.setOnKeyPressed(a -> {

                game.movePlayer(a.getCode());
                game.createBoardGame();

            });
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Game Board");
                stage.show();


            }
            else if (text.equals("7 x 7")) {
                GameBoard game = new GameBoard(7,7);
                Scene scene = new Scene(game, 800, 800);
                scene.setOnKeyPressed(a -> {

                    game.movePlayer(a.getCode());
                    game.createBoardGame();

                });
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Game Board");
                stage.show();
            }
            else if (text.equals("10 x 10")) {
                GameBoard game = new GameBoard(10,10);
                Scene scene = new Scene(game, 700, 700);
                scene.setOnKeyPressed(a -> {

                    game.movePlayer(a.getCode());
                    game.createBoardGame();

                });
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Game Board");
                stage.show();
            }
            else {
                System.exit(0);							//terminates the program
            }
        }

    }

}