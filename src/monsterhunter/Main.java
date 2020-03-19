package monsterhunter;

/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 * Description: The main class that runs the entire program.
 */


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application	{

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        FirstWindow first = new FirstWindow();
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);  //launches the start method
}
}