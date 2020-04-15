package memory_game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer extends StackPane{
    private int seconds;
    private int minute;
    private Text timeText;
    private Timeline timeline;
    private boolean value;

    public Timer (boolean value) {
        this.value = value;
        if (value == false) {
            getStartTime();
        }
        else {
            startTime();
        }
    }


    public Text getTimeText() {
        return timeText;

    }

    public Text getStartTime() {
        timeText = new Text(20, 50, "Time: " + "0" + minute + ":" + "0" + seconds);
        timeText.setFill(Color.BLACK);
        timeText.setFont(Font.font(20));
        return timeText;
    }

    public void startTime() {
              timeText = new Text(20, 50, "Time: " + "0" + minute + ":" + "0" + seconds);
        timeText.setFill(Color.BLACK);
        timeText.setFont(Font.font(20));


        // Create a handler for changing text
        EventHandler<ActionEvent> eventHandler = e -> {
            if (seconds >= 0 && seconds <= 9) {
                seconds++;
                timeText.setText( "Time: " + minute + ":" + "0" + seconds);
                timeText.setFill(Color.BLACK);
                timeText.setFont(Font.font(20));
            }
            else if (seconds >= 0 && seconds < 59) {
                seconds++;
                timeText.setText( "Time: " + minute + ":" + seconds);
                timeText.setFill(Color.BLACK);
                timeText.setFont(Font.font(20));
            }
            else if (seconds == 59) {
                seconds = 0;
                minute++;
                timeText.setText( "Time: " + minute + ":" + "0" + seconds);
                timeText.setFill(Color.BLACK);
                timeText.setFont(Font.font(20));
            }

        };


        // Create an animation for alternating text
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), eventHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Start animation


    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void starTimeline() {
        this.timeline.play();
    }

    public void stopTime() {
        this.timeline.stop();
    }


}
