package stackmazesolver;

/**
 * Author: Rafael Mendoza
 * Description: The DrawPath class is in charge of displaying the path. Gridpane is used to represent the each position that can be accessed.
 */

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class DrawPath extends GridPane {

    public DrawPath() {
        BorderPane pane = new BorderPane();
        Label label = new Label("No Path was Exists.");
        label.setFont(Font.font(40));
        label.setTextFill(Color.RED);
       this.add(label, 0,0);
    }

    public DrawPath(int[][] visited) {
        int size = 0;
        if (visited.length < 50 && visited[0].length < 50) {
            size = 35;
        }
        else {
            size = 3;
        }
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                Rectangle r = new Rectangle(size, size);
                r.setStroke(Color.BLACK);
                if (visited[i][j] == 1) {
                    r.setFill(Color.GREEN);
                }
                else {
                    r.setFill(Color.RED);
                }
                this.add(r,j,i);
            }
        }
    }
}
