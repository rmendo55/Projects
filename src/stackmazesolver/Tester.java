package stackmazesolver;

/**
 * Author: Rafael Mendoza
 * Description: This is the main method which executes the entire program. The user will be prompt to enter the name of the file. User must not include ".txt".
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester extends Application {
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file: " );
        String s = input.nextLine();

        ReadFile a = new ReadFile(s);
        MazeSolver maze = new MazeSolver(a.getBoard(), a.getXpos(), a.getYpos());
        maze.mazeSolver();
        DrawPath draw;
        if (maze.getPathExist()) {
            draw = new DrawPath(maze.getVisited());
        }
        else {
            draw = new DrawPath();
        }

        Scene scene = new Scene(draw);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Path");
        primaryStage.show();

    }
}
