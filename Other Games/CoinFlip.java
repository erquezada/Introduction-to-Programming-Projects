import java.util.Random;
import java.util.Scanner;
 
// Coin toss/flip game in Java
public class CoinFlip {
 
    private enum Coin {
        Head, Tail
    };
 
    public static void main(String[] args) {
        CoinFlip game = new CoinFlip();
        game.startGame();
    }
 
    // Starts a coin flip game till user decides to quit.
    private void startGame() {
 
        Scanner scanner = new Scanner(System.in);
        Coin guess;
 
        while (true) {
            System.out.print("Enter you guess (h for heads, t for tails, q to quit):");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (choice.equalsIgnoreCase("h")) {
                guess = Coin.Head;
            } else if (choice.equalsIgnoreCase("t")) {
                guess = Coin.Tail;
            } else {
                System.out.println("Wrong choice! Try again!");
                continue;
            }
 
            Coin toss = tossCoin();
            if (guess == toss) {
                System.out.println("Congratulations! You won the toss!");
            } else {
                System.out.println("Sorry! You lost the toss.");
            }
 
        }
        scanner.close();
 
    }
 
    // Flip a coin and return result
    private Coin tossCoin() {
        Random r = new Random();
        int i = r.nextInt(2);
        if (i == 0) {
            return Coin.Head;
        } else {
            return Coin.Tail;
        }
    }
 
}