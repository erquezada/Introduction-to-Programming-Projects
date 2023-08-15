import java.util.Scanner;
public class IceWalkerWithItems {
    public static void main(String[] args) {
        int[][] board = generateRandomBoard(5, 5);
        int row = 0;
        int col = 0;
        board[row][col] = 2;
        board[board.length - 1][board[0].length - 1] = 2;

        System.out.println("Welcome to Ice Walker with Items!");
        System.out.println("In order to play this board game, you must travel across the board by using the following commands:");
        System.out.println("r allows you to move to the right");
        System.out.println("l allows you to move to the left");
        System.out.println("u allows you to move upwards");
        System.out.println("d allows you to move downwards");

        boolean isIceBroken = playGame(board, row, col);

        if (isIceBroken) {
            System.out.println("You've fallen through the ice");
        } else {
            System.out.println("Congratulations! You have made it to the end!");
        }
    }

    public static int[][] generateRandomBoard(int rows, int cols) {
        int[][] board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = (int) (Math.random() * 4) + 1;
            }
        }
        return board;
    }

    public static boolean isValidMove(int row, int col, int[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    public static boolean playGame(int[][] board, int row, int col) {
        Scanner scanner = new Scanner(System.in);
        int collectedItems = 0;

        while (true) {
            System.out.println("You are currently at row: " + row + " col: " + col);
            printBoard(board);

            char direction = scanner.next().charAt(0);
            int newRow = row;
            int newCol = col;

            if (direction == 'l') {
                newCol--;
            } else if (direction == 'r') {
                newCol++;
            } else if (direction == 'u') {
                newRow--;
            } else if (direction == 'd') {
                newRow++;
            }

            if (isValidMove(newRow, newCol, board)) {
                row = newRow;
                col = newCol;
                if (board[row][col] > 0) {
                    collectedItems += board[row][col];
                    System.out.println("You collected " + board[row][col] + " item(s)! Total items collected: " + collectedItems);
                    board[row][col] = 0;
                }
                board[row][col]--;
                if (board[row][col] <= 0) {
                    return true;
                }
            } else {
                System.out.println("You are out of bounds. Please go in a different direction.");
            }

            if (row == board.length - 1 && col == board[0].length - 1) {
                break;
            }
        }
        scanner.close();
        printBoard(board);
        return false;
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int value : row) {
                if (value == 0) {
                    System.out.print(". ");
                } else if (value == 2) {
                    System.out.print("X ");
                } else {
                    System.out.print(value + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
