public class Sudoku {

    // size of 2D matrix
    static int BOARD_SIZE = 9;

    public static boolean solve(int board[][], int row, int col) {

        // base case
        if (row == BOARD_SIZE - 1 && col == BOARD_SIZE)
            return true;

        if (col == BOARD_SIZE) {
            row++;
            col = 0;
        }

        // if board contains preset value, next col
        if (board[row][col] != 0)
            return solve(board, row, col + 1);

        // for values 1-9
        for (int num = 1; num < 10; num++) {

            // if the value is valid in the cell
            if (check(board, row, col, num)) {

                // set value to cell
                board[row][col] = num;

                // recurse to solve the rest of the board & see validity
                if (solve(board, row, col + 1))
                    return true;
            }

            // if value is invalid in cell, remove value from cell
            board[row][col] = 0;
        }
        return false;
    }

    public static boolean check(int board[][], int row, int col, int num) {

        // check if num is in row already
        for (int y = 0; y < BOARD_SIZE; y++) {
            if (board[row][y] == num)
                return false;
        }

        // check if num is in col already
        for (int x = 0; x < BOARD_SIZE; x++) {
            if (board[x][col] == num)
                return false;
        }

        int rowFirstIndex = row - row % 3;
        int colFirstIndex = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + rowFirstIndex][j + colFirstIndex] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void print(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int board[][] = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};

        if (solve(board, 0, 0))
            print(board);
        else
            System.out.println("No Solution");
    }
}
