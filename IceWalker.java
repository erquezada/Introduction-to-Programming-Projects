/* Eric Quezada
This file generates a board game called ice walker
*/
import java.util.Scanner;
public class IceWalker {
    public static void main(String[] args){
        int[][] board = new int[5][5];
        // Generates a random board 
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = (int)Math.round(Math.random()*4) + 1;
            }
        }
        int row = 0;
        int col = 0;
        board[row][col] = 2;
        board[board.length-1][board[board.length-1].length-1] = 2; // Make sure that the ice won't break on the last tile 
        
        boolean isIceBroken = false;
        System.out.println("Welcome to Ice Walker!");
        System.out.println("In order to play this board game, you must travel acroos the board by using the following commands:");
        System.out.println("r allows you to move to right");
        System.out.println("l allows you to move to left");
        System.out.println("u allows you to move to up");
        System.out.println("d allows you to move to down");

        while(row != board.length-1 || col != board[row].length-1){
            System.out.println("You are currently at row: " + row + " col: " + col);
            printBoard(board);
            int[] nextPos = nextPosition(board, row, col); // nextPos[0] holds the new row and nextPos[1] holds the new column
            row = nextPos[0]; 
            col = nextPos[1];
            
            board[row][col]--; // Represents the ice cracking when it's stepped on
            if (board[row][col] <= 0){ // ice breaks at 0
                isIceBroken = true;
                break;
            } 
        }
        printBoard(board);
        if (isIceBroken){
            System.out.println("You've fallen through the ice");
        } else{
            System.out.println("You have made it to the end!");
        }   
    }
    // printBoard should print the board to the console 
    public static void printBoard(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // Next position should get input from the user and returns an array in the format [newRow, newColumn]
    // The input obtained from the user should be either 'L','R','U', or'D' each mapping to a different direction 
    // (left, right, up, down). Make sure that the user cannot overstep the bounds of the array (ex. you can't move left
    // when you're in the top right corner of the board)
    public static int[] nextPosition(int[][] board, int row, int col){
        Scanner scnr = new Scanner(System.in);
        char direction = scnr.next().charAt(0);
        int[] position = new int[2]; // Should hold the new row position in index 0 and the new col position in index 1
        
        //If position == 'L' then do col - -
        //If position == 'R' then do col ++
        //If position == 'U' then do row - -
        //If position == 'D' then do row + +
        //Else if out of bounds, then error.
            if(direction == 'l' && (col -1 >=0)) {
                position[0] = row;
                position[1] = col-1;

            } else if(direction == 'l' && col-1 < 0) {
                System.out.println("You are out of bounds.");
                System.out.println("Please go to a different direction.");
            }  
            if(direction == 'u' && (row -1 >=0)) {
                position [0] = row - 1;
                position [1] = col;
            } else if(direction == 'u' && (row-1 < 0)) {
                System.out.println("You are out of bounds");
                System.out.println("Please go to a different direction");
            }
            if(direction == 'r' && (col +1 <= board.length-1)) {
                position[0] = row;
                position[1] = col + 1;
            } else if(direction == 'r' && (col + 1 >= 0)) {
                System.out.println("You are out of bounds");
                System.out.println("Please go to a different direction");
            }
            if(direction == 'd' && (row + 1 <= board.length-1)) {
                position [0] = row + 1;
                position [1] = col; 
            } else if(direction == 'd' && (row-1 > 0)) {
                System.out.println("You are out of bounds");
                System.out.println("Please go to a different direction");
            }
        return position;
    }
}