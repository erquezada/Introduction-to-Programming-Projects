import java.util.Scanner;
import java.io.*;
public class FruitsPrice {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("grocery-store-database.txt"); // change the file name to fit your own file's name.
        Scanner scnr = new Scanner(file);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a fruit name: ");
        String enteredFruit = scanner.nextLine();
        boolean fruitFound = false;
        for (int i = 0; scnr.hasNextLine(); i++) {
            String fruitName = scnr.next();
            double fruitPrice = scnr.nextDouble();
            if (fruitName.equals(enteredFruit)) {
                System.out.println(fruitName + " cost $" + fruitPrice + " per pound");
                fruitFound = true;
                break; // Exit the loop as soon as the fruit is found
            }
        }
        if (!fruitFound) {
            System.out.println("Fruit not found in the database.");
        }
        scnr.close();
        scanner.close();
    }
}
