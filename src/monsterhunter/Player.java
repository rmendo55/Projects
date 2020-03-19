package monsterhunter;


/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *
 */

public class Player {
    private int ammo;
    private int rowPos;
    private int colPos;

    public Player(int rowPos, int colPos) {
        this.rowPos = rowPos;
        this.colPos = colPos;
        this.ammo = 3;
    }

    public Player(int row, int col, int ammo) {
        this.rowPos = row;
        this.colPos = col;
        this.ammo = ammo;
    }


    //getters
    public int getAmmo() {
        return this.ammo;
    }

    public int getRowPos() {
        return this.rowPos;
    }

    public int getColPos() {
        return this.colPos;
    }

    //setters
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }


}
