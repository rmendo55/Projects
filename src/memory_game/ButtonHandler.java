package memory_game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        // TODO Auto-generated method stub
        String text = ((Button) (e.getSource())).getText();

        if (e.getSource() instanceof Button) {
            if (text.equals("New Game")) {
                CreateSecondWindow window2 = new CreateSecondWindow();
            } else {
                System.exit(0);
            }

        }
    }
}