//This program creates a fun dungeon style board game, enjoy!
import java.util.Random;
import java.util.Scanner;

public class DungeonQuestTheDragonsLair {

  // Global variables defined here are special because their values never change during runtime.
  // You should NOT define more global variables.

  // Constants for defining the number of rows and columns the dungeon will have.
  static final int NUM_ROWS = 4;
  static final int NUM_COLUMNS = 4;

  // Constants for defining room types. Feel free to use these in your code.
  static final int ROOM_TYPE_EMPTY = 0;
  static final int ROOM_TYPE_SHIELD = 1;
  static final int ROOM_TYPE_SWORD = 2;
  static final int ROOM_TYPE_DRAGON = 3;

  public static void main(String[] args) {
    int[] position = new int[2];
    // Your code STARTS HERE.
    // initialize your variables
    int row = 0;
    int col = 0;
    System.out.println("Oh no!");
    System.out.println("A dragon is killing our sheep and scaring our villagers, please help the knight put a stop to this nightmare!");

    boolean isSwordAcquired = false;
    if (isSwordAcquired == false) {
      System.out.println("Sword is not acquired");
    }
    boolean isShieldAcquired = false;
    if (isSwordAcquired == false) {
      System.out.println("Shield is not acquired");
    }
    double playerHealth = 10;

    int[][] dungeon = generateRandomDungeon();
    while (playerHealth > 0) {
      System.out.println("Where would you like to go?");

      System.out.println("Use l to move left");
      System.out.println("Use r to move right");
      System.out.println("Use u to move up");
      System.out.println("Use d to move down");
      System.out.println();

      System.out.println("Your current health is: " + playerHealth + " health points.");
      System.out.println("You are currently at row: " + row + " col: " + col);

      printDungeonBoard(dungeon);

      int[] nextPos = validUserDirections(dungeon, row, col); // nextPos[0] holds the new row and nextPos[1] holds the new column
      row = nextPos[0];
      col = nextPos[1];

      if (dungeon[row][col] == 0) {
        System.out.println("This room appears to be empty, there's not much to do here.");
        playerHealth += 2;
      }
      if(dungeon[row][col] == 1) {
        System.out.println("There's a monster guarding the shield!");
        System.out.println("What would you like to do?");
        String userAction = battleMonster();
        while(userAction.equals("a")) {
          //playerHealth = playerHealth -3;
          userAction = battleMonster();
          //System.out.println(playerHealth);
        }
        if(userAction.equals("c")) {
          position[1] = col - 1;
        }
        if(userAction.equals("b")) {
          playerHealth = playerHealth - 3;
          isShieldAcquired = true;
          System.out.println(shieldStatus(isShieldAcquired));
        }
      }
      if(dungeon[row][col] == 2) {
        System.out.println("There's a monster guarding the sword!");
        System.out.println("There's a sword located in this room!");
        System.out.println("You have found the sword!");
        isSwordAcquired = true;
        System.out.println(swordStatus(isSwordAcquired));
        String userAction = battleMonster();
        while(userAction.equals("a")) {
          userAction  = battleMonster();
        }
        if(userAction.equals("c")) {
          position[1] = col - 1;
        }
        if(userAction.equals("b")) {
          playerHealth = playerHealth - 3;
          isShieldAcquired = true;
          System.out.println(shieldStatus(isSwordAcquired));
        }
      }
      if(dungeon[row][col] == 3) {
        System.out.println("There's a dragon located in this room!");
        System.out.println("What would you like to do?");
        String userAction = battleMonster();
        while(userAction.equals("a")) {
          userAction  = battleMonster();
        }
        if(userAction.equals("c")) {
          position[1] = col -1;
        if(userAction.equals("b")) {
          playerHealth = playerHealth - 5;
        }
        System.out.println("Congratulations! You have defeated the dragon!");
        System.exit(0);            
        }
      // To get started, first call generateRandomDungeon() to create your dungeon.
      // Make sure you save it in a variable!

      // Your code ENDS HERE  
      }
    }
  }



  // Your methods START HERE

  //This method prints out a dungeon board
  public static void printDungeonBoard(int[][] dungeon) {
    System.out.println();
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[i].length; j++) {
        System.out.print(dungeon[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  //This method displays users shield status
  public static String shieldStatus(boolean isShieldAcquired) {
    if (!isShieldAcquired) {
      return "Shield has not been acquired";
    } else {
      return "Shield has been acquired";
    }
  }

  //This method displays users sword status
  public static String swordStatus(boolean isSwordAcquired) {
    if (!isSwordAcquired) {
      return "Sword has not been acquired";
    } else {
      return "Sword has been acquired";
    }
  }
  // this method creates the controls for the user to use
  public static String userControls(int[] validUserDirections, char direction) {
    System.out.println("Use l to move left");
    System.out.println("Use r to move right");
    System.out.println("Use u to move up");
    System.out.println("Use d to move down");
    if (direction == 'l' || direction == 'r' || direction == 'u' || direction == 'd') {
      return Character.toString(direction);
    } else {
      return Character.toString(direction);
    }
  }
  // This method creates a prompt for the user when they encounter a monster or dragon.
  public static String battleMonster() {
    System.out.println("You have encountered a monster!");
    System.out.println("Use a to do nothing.");
    System.out.println("Use b to fight!");
    System.out.println("Use c to run away.");
    Scanner scnr = new Scanner(System.in);
    char action = scnr.next().charAt(0);

    if (action == 'a' || action == 'b' || action == 'c') {
      return Character.toString(action);
    } else {
      return Character.toString(action);
    }
  }
  //This method validates the user's input for traversing the dungeon
  //If the user input is valid, it will allow the user to move onto the next space
  //Else it will display out of bounds
  public static int[] validUserDirections(int[][] dungeon, int row, int col) {
    Scanner scnr = new Scanner(System.in);
    char direction = scnr.next().charAt(0);
    int[] position = new int[2]; // Should hold the new row position in index 0 and the new col position in index 1

    //If position == 'L' then do col - -
    //If position == 'R' then do col ++
    //If position == 'U' then do row - -
    //If position == 'D' then do row + +
    //Else if out of bounds, then error.
    if (direction == 'l' && (col - 1 >= 0)) {
      position[0] = row;
      position[1] = col - 1;

    } else if (direction == 'l' && col - 1 < 0) {
      System.out.println("You are out of bounds.");
      System.out.println("Please go to a different direction.");
    }
    if (direction == 'u' && (row - 1 >= 0)) {
      position[0] = row - 1;
      position[1] = col;
    } else if (direction == 'u' && (row - 1 < 0)) {
      System.out.println("You are out of bounds");
      System.out.println("Please go to a different direction");
    }
    if (direction == 'r' && (col + 1 <= dungeon.length - 1)) {
      position[0] = row;
      position[1] = col + 1;
    } else if (direction == 'r' && (col + 1 >= 0)) {
      System.out.println("You are out of bounds");
      System.out.println("Please go to a different direction");
    }
    if (direction == 'd' && (row + 1 <= dungeon.length - 1)) {
      position[0] = row + 1;
      position[1] = col;
    } else if (direction == 'd' && (row - 1 > 0)) {
      System.out.println("You are out of bounds");
      System.out.println("Please go to a different direction");
    }
    return position;
  }
  // This method validates whether an input performed by the user is valid when encountering a monster or dragon
  public static char validBattleAction(String battleMonster, String userControls) {
    Scanner actionScnr = new Scanner(System.in);
    char action = actionScnr.next().charAt(0);
    if (action == 'a') {
      System.out.println("Do nothing");
    }
    if (action == 'b') {
      System.out.println("Fight enemies");
    }
    if (action == 'c') {
      System.out.println("Run away");
    }
    return action;
  }

  public static boolean emptyRoom(double playerHealth) {
    //this method is for extra credit
    //if a user enters an empty room, restore users hp by two points
    playerHealth += 2;
    return true;
  }

  // Your methods END HERE

  // DO NOT change anything below this comment.

  /*
   * Create a random dungeon represented by a 2D int array (int[][]).
   * In the resulting 2D array:
   *   0 represents an empty room.
   *   1 represents a room with the shield.
   *   2 represents a room with the sword.
   *   3 represents a room with the dragon.
   */
  public static int[][] generateRandomDungeon() {
    // Create dungeon
    int[][] dungeon = new int[NUM_ROWS][NUM_COLUMNS];

    // Get random coordinates
    int[][] locations = getRandomCoordinates();

    // Set shield location
    int[] shieldLocation = locations[0];
    dungeon[shieldLocation[0]][shieldLocation[1]] = ROOM_TYPE_SHIELD;

    // Set sword location
    int[] swordLocation = locations[1];
    dungeon[swordLocation[0]][swordLocation[1]] = ROOM_TYPE_SWORD;

    // Set dragon location
    int[] dragonLocation = locations[2];
    dungeon[dragonLocation[0]][dragonLocation[1]] = ROOM_TYPE_DRAGON;

    return dungeon;
  }

  /*
   * The following method returns a an array of random coordinates.
   * Each coordinate is represented by an array of length 2.
   *
   * In the resulting array:
   *   The first coordinate is the location of the the shield.
   *   The second coordinate is the location of the the sword.
   *   The third coordinate is the location of the dragon.
   * 
   * NOTE: This method requires that the minimum size for the dungeon must be 4x4.
   */
  private static int[][] getRandomCoordinates() {
    int numToolsToObtain = 2;
    int numDragons = 1;

    if (NUM_COLUMNS < 4 || NUM_ROWS < 4) {
      System.out.println("Minimum size for the dungeon must be 4x4");
      return null;
    }


    int[][] monstersLocation = new int[numToolsToObtain + numDragons][2];
    Random rand = new Random();

    for (int i = 0; i < numToolsToObtain + numDragons; i++) {
      int row = rand.nextInt(NUM_ROWS);
      int column = rand.nextInt(NUM_COLUMNS);
      if ((row == 0 && column == 0) || (i != 0 && isCoordinateDuplicate(i + 1, row, column, monstersLocation))) {
        int columnDuplicatedValue = column;
        while (column == columnDuplicatedValue || (i != 0 && isCoordinateDuplicate(i + 1, row, column, monstersLocation))) {
          column = rand.nextInt(NUM_COLUMNS);
        }
      }
      monstersLocation[i][0] = row;
      monstersLocation[i][1] = column;
    }
    return monstersLocation;
  }

  /*
   * Returns true if a monster is already placed in the current cell (row, column), and false otherwise.
   */
  private static boolean isCoordinateDuplicate(int monsters, int row, int column, int[][] monstersLocation) {
    for (int i = 0; i < monsters; i++) {
      if (monstersLocation[i][0] == row && monstersLocation[i][1] == column) {
        return true;
      }
    }
    return false;
  }
}