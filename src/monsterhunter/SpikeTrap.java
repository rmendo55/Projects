package monsterhunter;


/**
 * Name: Rafael Mendoza
 * CIN: 304997295
 *
 */

public class SpikeTrap {
    private int rowPos;
    private int colPos;

    public SpikeTrap() {

    }

    public SpikeTrap(int rowPos, int colPos) {
        this.rowPos = rowPos;
        this.colPos = colPos;
    }


    //getters


    public int getRowPos() {
        return this.rowPos;
    }

    public int getColPos() {
        return this.colPos;
    }

    //setters


    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

}
