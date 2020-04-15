package memory_game;

public class TrackNumberOfClicks {
    private static int number;

    public TrackNumberOfClicks() {
        this.number = 0;
    }

    public int getNumber() {
        ++number;
        if (number == 3) {
            this.number = 1;
            return this.number;
        }
        else {
            return this.number;
        }
    }
}
