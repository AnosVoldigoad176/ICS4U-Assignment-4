/*
 * Author: Duc Anh Vu
 * Last modified: March 11, 2021
 * Description: Read in grid from text file to 2D array, then print out, and finally calculate blob inside per grid
 */


import java.io.*;
import java.util.*;

public class Main {

    /**
     * Method Name: readGridData
     * Author: Duc Anh Vu
     * Creation Date: March 11, 2021
     * Modified Date: March 11, 2021
     * Description: Read the grid data from text file and put it in to an 2D array
     * Parameters: Scanner to check if the file has next line or end
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static char[][] readGridData(Scanner scanner) {
        int columnCount = Integer.parseInt(scanner.nextLine());
        int rowCount = Integer.parseInt(scanner.nextLine());
        System.out.println(columnCount);
        System.out.println(rowCount);
        char[][] grid = new char[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < columnCount; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        return grid;
    } // End readGridData

    /**
     * Method Name: printGrid
     * Author: Duc Anh Vu
     * Creation Date: March 11, 2021
     * Modified Date: March 11, 2021
     * Description: Print out the grid from the array in parameter
     * Parameters: Grid array with character type
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void printGrid(char[][] grid) {
        int rowCount = grid.length;
        int columnCount = grid[0].length;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    } // End of printGrid


    public static void main(String[] args) throws IOException {

        Scanner inputKeyboard = new Scanner(System.in);
        System.out.println("Please input the grid data file");
        String fileName = inputKeyboard.nextLine();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                char[][] grid = readGridData(scanner);
                printGrid(grid);
            }
        }
        inputKeyboard.close();
    } // End main

}
