import java.util.Scanner;

public class Connect4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[][] grid = new char[7][7];

        for (int row = 0; row < 7; ++row) {
            for (int col = 0; col < 7; ++col) {
                grid[row][col] = ' ';
            }
        }

        showGrid(grid);
        boolean goAhead = true;
        while (true) {
            if (goAhead)
                System.out.print("Drop a red disk at column (1-7): ");
            else
                System.out.print("Drop a yellow disk at column (1-7): ");

            int column = input.nextInt();

            if (column < 1 || column > 7) {
                System.out.println("Column should be from 1 to 7");
                continue;
            }

            if (!addChip(grid, column - 1, goAhead ? 'R' : 'Y')) {
                System.out.println("Choose another column.");
                continue;
            }

            showGrid(grid);
            if (checkGrid(grid)) {
                System.exit(0);
            }

            goAhead = !goAhead;
        }
    }

    public static boolean checkGrid(char[][] grid) {
        // CHECKS FOR WINNERS IN COLUMN
        for (int col = 0; col < 7; ++col) {
            for (int row = 0; row < 4; ++row) {
                if (grid[row][col] != ' ' &&
                        grid[row][col] == grid[row + 1][col] &&
                        grid[row + 1][col] == grid[row + 2][col] &&
                        grid[row + 2][col] == grid[row + 3][col]) {
                    if (grid[row][col] == 'Y') {
                        System.out.println("The Yellow player won!");
                        return true;
                    } else if (grid[row][col] == 'R') {
                        System.out.println("The Red player won!");
                        return true;
                    }
                }
            }
        }

        // CHECKS FOR WINNERS IN DIAGONAL
        // checking ascending diagonal for red player
        for (int row = 3; row < 7; row++) {
            for (int col = 0; col < 7 - 3; col++) {
                if (grid[row][col] == 'R' &&
                        grid[row - 1][col + 1] == 'R' &&
                        grid[row - 2][col + 2] == 'R' &&
                        grid[row - 3][col + 3] == 'R') {
                    System.out.println("The Red player won!");
                    return true;
                }
            }
        }

        // checking descending diagonal for red player
        for (int row = 3; row < 7; row++) {
            for (int col = 3; col < 7; col++) {
                if (grid[row][col] == 'R' &&
                        grid[row - 1][col - 1] == 'R' &&
                        grid[row - 2][col - 2] == 'R' &&
                        grid[row - 3][col - 3] == 'R') {
                    System.out.println("The Red player won!");
                    return true;
                }
            }
        }

        // checking ascending diagonal for yellow player
        for (int row = 3; row < 7; row++) {
            for (int col = 0; col < 7 - 3; col++) {
                if (grid[row][col] == 'Y' &&
                        grid[row - 1][col + 1] == 'Y' &&
                        grid[row - 2][col + 2] == 'Y' &&
                        grid[row - 3][col + 3] == 'Y') {
                    System.out.println("The Yellow player won!");
                    return true;
                }
            }
        }

        // checking descending diagonal for yellow player
        for (int row = 3; row < 7; row++) {
            for (int col = 3; col < 7; col++) {
                if (grid[row][col] == 'Y' &&
                        grid[row - 1][col - 1] == 'Y' &&
                        grid[row - 2][col - 2] == 'Y' &&
                        grid[row - 3][col - 3] == 'Y') {
                    System.out.println("The Yellow player won!");
                    return true;
                }
            }
        }

        // CHECK FOR WINNERS IN ROW
        for (int row = 0; row < 7; ++row) {
            int count = 1;
            for (int col = 1; col < 7; ++col) {
                if (grid[row][col] != ' ' && grid[row][col] == grid[row][col - 1]) {
                    count++;
                    if (count >= 4) {
                        if (grid[row][col] == 'Y') {
                            System.out.println("The Yellow player won!");
                            return true;
                        } else if (grid[row][col] == 'R') {
                            System.out.println("The Red player won!");
                            return true;
                        }
                    }
                } else {
                    count = 1;
                }
            }
        }

        return false;
    }

    public static boolean addChip(char[][] grid, int column, char disk) {
        if (grid[0][column] != ' ') {
            return false;
        }

        // Check all chips in the column.
        for (int row = 0; row < 7; ++row) {
            if (grid[row][column] != ' ') {
                // Put the disk on top of the current one.
                grid[row - 1][column] = disk;
                return true;
            }
        }

        // If no other disks found, place this disk at the bottom.
        grid[6][column] = disk;
        return true;
    }

    public static void showGrid(char[][] grid) {
        for (int row = 0; row < 7; ++row) {
            System.out.print("| ");
            for (int col = 0; col < 7; ++col) {
                System.out.print(grid[row][col] + "| ");
            }
            System.out.println();
        }
    }
}
