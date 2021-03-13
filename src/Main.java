/*
 * Author: Duc Anh Vu
 * Last modified: March 11, 2021
 * Description: Read in grid from text file to 2D array, then print out, and finally count shapes
 */

import java.io.*;
import java.util.*;

public class Main {

    /**
     * Method Name: readGridData
     * @author Duc Anh Vu
     * Creation Date: March 11, 2021
     * Modified Date: March 11, 2021
     * Description: Read the grid data from text file and put it in to an 2D array
     * @param scanner Scanner to check if the file has next line or end. The reason use this here so later I can use the hasNextLine() to read until the end of the file.
     * @return array grid
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static char[][] readGridData(Scanner scanner) {
        
        // Assign columnCount and rowCount to what we read from first two line per record in the text file
        // Read the first line of a "record", which is column count of the grid later we will read
        int columnCount = Integer.parseInt(scanner.nextLine());
        // Read the second line of the record, row count of the grid
        int rowCount = Integer.parseInt(scanner.nextLine());
        // Print out both of them
        System.out.println(columnCount);
        System.out.println(rowCount);
        
        // Create a 2D array, name grid, the row and column of the array is determined by what we read up there and already assigned to 2 variables
        char[][] grid = new char[rowCount][columnCount];
        // Loop until it reach the end of the row of the grid
        for (int i = 0; i < rowCount; i++) {
            // Read the whole next line (after two integer number bcz we already read them), as String.
            String line = scanner.nextLine();
            // Loop until the end of the column of the grid
            for (int j = 0; j < columnCount; j++) {
                // Divided what we read as String up there to character.
                grid[i][j] = line.charAt(j);
            }
        }
        // Return when the loop is done (reached max dimension of the array)
        return grid;
    } // End readGridData

    /**
     * Method Name: printGrid
     * @author Duc Anh Vu
     * Creation Date: March 11, 2021
     * Modified Date: March 11, 2021
     * Description: Print out the grid from the character array type in parameter
     * @param grid array grid (character type)
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void printGrid(char[][] grid) {
        
        // Get row count of the array in param
        int rowCount = grid.length;
        // Get column count of the same array
        int columnCount = grid[0].length;
        // Loop until the end of row count of the array
        for (int i = 0; i < rowCount; i++) {
            // Loop until the end of the column count of the array
            for (int j = 0; j < columnCount; j++) {
                // Print from [0][0] then [0][1] then [0][2]... till end of the column count and back to [1][0] then go
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    } // End of printGrid

    /**
     * Method Name: findShapes
     * @author Duc Anh Vu
     * Creation Date: March 11, 2021
     * Modified Date: March 11, 2021
     * Description: Print out the grid from the character array type in parameter
     * @param grid array grid (character type)
     * @param rowCount number of row of the array
     * @param columnCount number of column of the array
     * @return Total number of count time after running through all the loop that check and mark the shapes
     * Data Type: Integer
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static int findShapes(char[][] grid, int rowCount, int columnCount) {
        int[][] arrived = new int[rowCount][columnCount]; // Integer array name "arrived", to mark whether we went through the spot or not
        // Only 0 and 1 in this array, described later in wentThrough()
        int count = 0; // Count of shapes start from 0

        // Same shit up there
        for (int i = 0; i < rowCount; i++) {
            // Same again
            for (int j = 0; j < columnCount; j++) {
                // If the spot in the grid is X, and didn't arrived then mark as wentThrough() now
                if (grid[i][j] == 'X' && arrived[i][j] == 0 ) {
                    wentThrough(i,j, grid, arrived, rowCount, columnCount);
                    count++; // Increase count per time reach the condition
                }
            }
        }
        return count; // Return total count when finish loop
    }

    /**
     * Method Name: wentThrough
     * @author Duc Anh Vu
     * Creation Date: March 11, 2021
     * Modified Date: March 11, 2021
     * Description: Print out the grid from the character array type in parameter
     * @param grid array grid (character type)
     * @param i CURRENT index of the row of the array when reached condition in line 106
     * @param j CURRENT index of the column of the array when reached condition in line 106
     * @param arrived Integer array "arrived" use to mark 1 as arrived and 0 as dot or reach base case
     * @param rowCount number of row of the array
     * @param columnCount number of column of the array
     * @return 0 and 1, 1 for arrived, 0 for dot or base case
     * Data type: Integer
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static int wentThrough(int i, int j, char[][] grid, int[][] arrived, int rowCount, int columnCount) {
        if (i < 0 || j < 0) { // Base case 1: If  i and j < 0 (kinda impossible but just for sure), then return 0
            return 0;
        }
        if (i >= rowCount || j >= columnCount) { // Base case 2: If out of index length then return 0
            return 0;
        }
        if (arrived[i][j] == 1) { // Base case 3: If marked arrived already then return 1
            return 1;
        }
        if (grid[i][j] == '.') { // Base case 4: If the position equal a dot then return 0
            return 0;
        }

        arrived[i][j] = 1; // Mark as arrived if we don't get to the base case

        // Recursively mark all the 4 adjacent cells - Down, up, right, left
        return wentThrough(i+1, j, grid, arrived, rowCount, columnCount) // Down
                + wentThrough(i-1, j, grid, arrived, rowCount, columnCount) // Up
                + wentThrough(i, j+1, grid, arrived, rowCount, columnCount) // Right
                + wentThrough(i, j-1, grid, arrived, rowCount, columnCount); // Left
    }

    public static void main(String[] args) throws IOException {

        Scanner inputKeyboard = new Scanner(System.in);
        System.out.println("Please input the grid data file");
        String fileName = inputKeyboard.nextLine();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            // While loop if the file has next line to read in
            while (scanner.hasNextLine()) {
                char[][] grid = readGridData(scanner);
                printGrid(grid);
                System.out.println("This rectangle has " + findShapes(grid, grid.length, grid[0].length) + " shapes");
                System.out.println();
            }
        }
        inputKeyboard.close();
    } // End main
}
