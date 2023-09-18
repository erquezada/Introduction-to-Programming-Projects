import java.util.Scanner;

public class CellularAutomaton {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean replay = true;
        while (replay) {
            boolean[] board = new boolean[41];
            setCenterOfBoard(board);
            printBoard(board);
            boolean[] userRule = getRuleFromUser();

            for (int i = 0; i < 20; i++) {
                board = nextBoard(board, userRule);
                printBoard(board);
            }

            System.out.print("Do you want to play again? (y/n): ");
            char replayChoice = scanner.next().charAt(0);
            replay = (replayChoice == 'y' || replayChoice == 'Y');
        }
    }

    public static void printBoard(boolean[] board) {
        for (boolean cell : board) {
            System.out.print(cell ? "*" : ".");
        }
        System.out.println();
    }

    public static void setCenterOfBoard(boolean[] board) {
        board[board.length / 2 + 1] = true;
    }

    public static int countLiveInNeighborhood(boolean[] board, int pos) {
        int liveCount = 0;
        for (int i = pos - 1; i <= pos + 1; i++) {
            if (i >= 0 && i < board.length && board[i]) {
                liveCount++;
            }
        }
        return liveCount;
    }

    public static boolean isAliveNextBoard(boolean[] board, int pos, boolean[] userRule) {
        int liveInNeighborhood = countLiveInNeighborhood(board, pos);
        return userRule[liveInNeighborhood];
    }

    public static boolean[] nextBoard(boolean[] board, boolean[] userRule) {
        boolean[] next = new boolean[board.length];
        for (int i = 0; i < board.length; i++) {
            next[i] = isAliveNextBoard(board, i, userRule);
        }
        return next;
    }

    public static boolean[] getRuleFromUser() {
        System.out.println("Note: Enter the rule for the board as true/false values, e.g., true true true true");
        System.out.print("Enter the rule for the board: ");
        Scanner scanner = new Scanner(System.in);
        boolean[] userRule = new boolean[4];
        for (int i = 0; i < userRule.length; i++) {
            userRule[i] = scanner.nextBoolean();
        }
        return userRule;
    }
}
