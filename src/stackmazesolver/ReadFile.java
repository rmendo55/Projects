package stackmazesolver;

/**
 * Author: Rafael Mendoza
 * Description: This class read a text file that is based on a Maze. A two dimensional array is used to store the values of the maze as strings.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    private String fileName;
    private String[][] board;
    private int xPos;
    private int yPos;

    public ReadFile(String fileName) {
        this.fileName = fileName;
        readFile();
    }

    //getters that are necessary
    public String[][] getBoard() {
        return this.board;
    }

    public int getXpos() {
        return this.xPos;
    }

    public int getYpos() {
        return this.yPos;
    }

    public void readFile() {
        try{
            File file = new File("resources/" + this.fileName + ".txt");
            Scanner readFile = new Scanner(file);

            int row = 0;
            int col = 0;
            int x = 1;
            String s = "";
            int currentRow = 0;
            while(readFile.hasNextLine()) {
                if (x == 1) {
                    s = readFile.nextLine();
                    String s2 = "";
                    String s3 = "";
                    int index = 0;
                    boolean value = false;
                    while(index < s.length()) {
                        if (!(s.charAt(index) == ' ') && !(value)) {
                            s2 += s.charAt(index);
                            ++index;
                        }
                        else if (s.charAt(index) == ' ') {
                            row = Integer.parseInt(s2);
                            value = true;
                            ++index;
                        }
                        else {
                            s3 += s.charAt(index);
                            ++index;
                        }
                    }
                    col = Integer.parseInt(s3);
                    this.board = new String[row][col];
                    ++x;
                }
                else if (x == 2) {
                    s = readFile.nextLine();
                    String s2 = "";
                    String s3 = "";
                    int index = 0;
                    boolean value = false;
                    while(index < s.length()) {
                        if (!(s.charAt(index) == ' ') && !(value)) {
                            s2 += s.charAt(index);
                            ++index;
                        }
                        else if (s.charAt(index) == ' ') {
                            this.xPos = Integer.parseInt(s2);
                            value = true;
                            ++index;
                        }
                        else {
                            s3 += s.charAt(index);
                            ++index;
                        }
                    }
                    this.yPos = Integer.parseInt(s3);
                    ++x;
                }
                else {
                    s = readFile.nextLine();
                    int x2 = 0;
                    while(x2 < this.board[0].length) {
                        this.board[currentRow][x2] = s.charAt(x2) + "";
                        ++x2;
                    }
                    ++currentRow;
                }
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
