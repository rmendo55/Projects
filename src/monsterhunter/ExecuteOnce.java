package monsterhunter;


/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *
 */

public class ExecuteOnce {
    private boolean execution;
    private Player player;
    private boolean valueForPlayer;
    private boolean valueForDiablos;

    public ExecuteOnce() {


    }

    public ExecuteOnce(Player player) {
        this.player = player;

        this.valueForPlayer = false;
        this.valueForDiablos = false;
    }
    public ExecuteOnce(Player player, boolean valueForPlayer) {
        this.player = player;
        this.valueForPlayer = valueForPlayer;
    }

    public boolean getValueForPlayer() {
        return this.valueForPlayer;
    }



    public void setValueForPlayer(boolean valueForPlayer) {
        this.valueForPlayer = valueForPlayer;
    }


}


