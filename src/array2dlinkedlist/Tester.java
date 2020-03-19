package array2dlinkedlist;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Array2D<Object> arr2D = createNxNArray();
        populateArray2D(arr2D);
    }

    public static Array2D<Object> createNxNArray() {
        System.out.println("Enter nxn Array2D you want to create (EX: 2X2): ");
        Scanner input = new Scanner(System.in);
        String inputValue = input.next();
        int row = Integer.parseInt(inputValue.charAt(0) + "");
        int col = Integer.parseInt(inputValue.charAt(2) + "");
        return new Array2D<Object>(row,col);
    }

    public static void populateArray2D(Array2D<Object> arr2D) {
        boolean inputingValues = true;
        Scanner input = new Scanner(System.in);
        String answer = "";
        String row = "";
        String col = "";
        String value = "";
        while (inputingValues) {
            printArray2D(arr2D);
            System.out.println("\nWould you like to add values to your array2d? (Y/N): ");
            answer = input.next();
            if (answer.equalsIgnoreCase("Y")){
                System.out.println("Choose row and column to add desired value: ");
                System.out.println("The array2D is not zero indexed.(Ex: row 1 is not referred to as index 0): ");
                System.out.println("Enter Row: ");
                row = input.next();
                System.out.println("Enter Column: ");
                col = input.next();
                while(!(Integer.parseInt(row) > 0 && Integer.parseInt(row) <= arr2D.rowSize() && Integer.parseInt(col) > 0 && Integer.parseInt(col) <= arr2D.colSize())){
                    System.out.println("Error: Row or Columns is out of bounds, reenter row and column: ");
                    System.out.println("Enter Row: ");
                    row = input.next();
                    System.out.println("Enter Column: ");
                    col = input.next();
                }
                System.out.println("Enter Value: ");
                Scanner inputValue = new Scanner(System.in);
                value = inputValue.nextLine();
                arr2D.set(Integer.parseInt(row), Integer.parseInt(col), value);
            }
            else {
                System.out.println("\nYou are done populating your Array2D! Cool!!!");
                System.out.println("\nYour Arrat2D: ");
                printArray2D(arr2D);
                inputingValues = false;
            }
        }
    }

    public static void printArray2D(Array2D<Object> twoDarr) {
        Array2DNode current = twoDarr.head;
        Array2DNode current2 = twoDarr.head;
        String outPut = "";
            for (int i = 0; i < twoDarr.rowSize(); i++) {
            for (int j = 0; j < twoDarr.colSize(); j++) {
                outPut += current.getItem() + "->";
                current = current.right;
                if (j == twoDarr.colSize() - 1 && i != twoDarr.rowSize() - 1) {
                    outPut += "\n";
                    for (int k = 0; k < twoDarr.colSize(); k++) {
                        outPut += "|\t";
                    }
                    outPut += "\n";
                    current2 = current2.down;
                    current = current2;
                }
            }
        }
        System.out.println("----------Array2D Linked Type ----------" + "\n" + outPut);
    }

}
