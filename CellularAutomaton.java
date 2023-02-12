import java.util.Scanner;
public class CellularAutomaton {

  // Print a '.' for dead cell and a '*' for a live cell.
  public static void printBoard(boolean[] board) {
    for (int i = 0; i < board.length; i++) {
      if (board[i]) {
        System.out.print("*");
      } else {
        System.out.print(".");
      }
    }
    System.out.println();
  }
  //this method generates a board and its center
  public static void setCenterOfBoard(boolean[] board) {
    board[board.length/2 + 1] = true;
  }
  //this method counts the number of live cells in the board
  public static int countLiveInNeighborhood(boolean[] board, int pos) {
    int liveCount = 0;
    for (int i = pos-1; i <= pos+1; i++) {
      if (i < 0 || i >= board.length) {
        continue;
      }

      if (board[i]) {
        liveCount++;
      }
    }

    return liveCount;
  }
  // this method determines what to do with a cell within the board
  public static boolean isAliveNextBoard(boolean[] board, int pos, boolean [] userRule) {
    int liveInNeighborhood = countLiveInNeighborhood(board, pos);
    // liveInNeighborhood = 1;
    //boolean[] rule = {false, true, true, false};
    // liveInNeighborhood | isAliveNextBoard
    // 0                  | false
    // 1                  | true
    // 2                  | true
    // 3                  | false
    // rule[liveInNeighborhood]
    // rule[1]
    // true
    return userRule[liveInNeighborhood];
  }
  //this method looks at a cell and based on the rule checks to see if cell is alive or not
  //if a cell is alive, then apply the rule.
  public static boolean[] nextBoard(boolean[] board, boolean [] userRule) {
    // Here we want to apply the rule.
    // First step for each cell, count the live cells in neighborhood.
    // Check if the cell in the next board is alive.
    boolean[] next = new boolean[board.length];
    for (int i = 0; i < board.length; i++) {
      boolean aliveInNext = isAliveNextBoard(board, i, userRule);
      next[i] = aliveInNext;
    }
    return next;
  }
  //tthis method gets a rule from the user (true false...) and applies the rule to other methods
  //later.
  public static boolean [] getRuleFromUser() {
    System.out.println("Note: To enter the rule for the board, the user must spell out the true or false values for the rule");
    System.out.println("For example: If the user enters true true true true ");
    System.out.println("The board will display a unique result from the true or false values for the rule");
    System.out.println();
    System.out.print("Enter the rule for the board (true false, etc...):");
    Scanner scnr = new Scanner(System.in);
    boolean[] userRule = new boolean[4];
    for (int i = 0; i < userRule.length; i++) {
      boolean boardRule = scnr.nextBoolean();
      userRule[i] = boardRule;
      if (userRule.length == 0) {
        System.err.print("Sorry not an option");
        System.exit(1);
      }
    }
    return userRule;
}
	// this method is main method, some methods will be called from this method.
  public static void main(String[] args) {
    boolean[] board = new boolean[41];
    setCenterOfBoard(board);
    printBoard(board);
    boolean [] userRule = getRuleFromUser();

    for (int i = 0; i < 20; i++) {
      board = nextBoard(board, userRule);
      printBoard(board);
    }
  }
}