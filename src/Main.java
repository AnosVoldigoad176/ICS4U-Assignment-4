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
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    } // End of printGrid

    public static int findShapes(char[][] grid, int rowCount, int columnCount) {
        int[][] visited = new int[rowCount][columnCount]; // all false from beginning

        int count = 0;

        for (int i = 0; i < rowCount; i++)
        {
            for (int j = 0; j < columnCount; j++)
            {
                if (grid[i][j] == 'X' && visited[i][j] == 0 ) // Not arrived dot
                {
                    wentThrough(i,j, grid, visited, rowCount, columnCount);
                    count++;
                }
            }
        }
        return count;
    }


    public static int wentThrough(int i, int j, char[][] grid, int[][] arrived, int rowCount, int columnCount)
    {
        if (i < 0 || j < 0)
            return 0;

        if (i >= rowCount || j >= columnCount)
            return 0;

        if (arrived[i][j] == 1) // already arrived
            return 1;

        if (grid[i][j] == '.') // Not a dot
            return 0;

        arrived[i][j] = 1;

    // recursively mark all the 4 adjacent cells - right, left, up and down
        return wentThrough(i+1, j, grid, arrived, rowCount, columnCount)
                + wentThrough(i-1, j, grid, arrived, rowCount, columnCount)
                + wentThrough(i, j+1, grid, arrived, rowCount, columnCount)
                + wentThrough(i, j-1, grid, arrived, rowCount, columnCount);
    }

    public static void main(String[] args) throws IOException {

        Scanner inputKeyboard = new Scanner(System.in);
        System.out.println("Please input the grid data file");
        String fileName = inputKeyboard.nextLine();

        try (Scanner scanner = new Scanner(new File(fileName))) {
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
