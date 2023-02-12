import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY_SPACE = '_';

    public static void main(String[] args) {
        char[][] board = {
            { EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE },
            { EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE },
            { EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE }
        };
        playGame(board);
    }

    public static void playGame(char[][] board) {
        Scanner scnr = new Scanner(System.in);
        int turn = 0;
        System.out.println("Welcome to Tic-Tac-Toe! Players take turns placing Xs or Os on the board. The first player to get three in a row wins!");

        while (true) {
            printBoard(board);
            char player = getPlayer(turn);
            System.out.println(player + ", make your move!");

            try {
                System.out.print("Enter i position: ");
                int i = scnr.nextInt();
                System.out.print("Enter j position: ");
                int j = scnr.nextInt();

                boolean successfulMove = makeMove(board, player, i, j);
                if (successfulMove) {
                    turn++;
                    if (checkWin(board, player)) {
                        System.out.println("Congratulations, " + player + " wins!");
                        break;
                    } else if (checkTie(board)) {
                        System.out.println("The game is a tie.");
                        break;
                    }
                } else {
                    System.out.println("Can't put a piece there. Try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again!");
                scnr.nextLine(); // consume the invalid input
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for (char[] row : board) {
            for (char element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean makeMove(char[][] board, char player, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return false;
        }
        if (board[i][j] == EMPTY_SPACE) {
            board[i][j] = player;
            return true;
        }
        return false;
    }

    public static char getPlayer(int move) {
        return (move % 2 == 0) ? 'X' : 'O';
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Horizontal win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Vertical win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win
        }
        return false;
    }
    public static boolean checkTie(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_') {
                    return false; // Board is not full
                }
            }
        }
        return true; // Board is full, game is a tie
    }
}
