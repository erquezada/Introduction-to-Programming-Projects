// This program runs a sample file reading fruits-database txt file based off of user input
import java.util.Scanner;
import java.io.*;

public class FruitsPrice{
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("fruits-database.txt");
        Scanner scnr = new Scanner(file);
        Scanner scanner = new Scanner(System.in);
        
        boolean fruit = true;
        String notFruit = "Not a fruit";
        System.out.println("Please enter a fruit name: ");
        String enteredFruit = scanner.nextLine();

        while (scnr.hasNextLine()) {
            String fruitName = scnr.next();
            double fruitPrice = scnr.nextDouble();
            if(fruitName.equals(enteredFruit)) {
                System.out.println(fruitName + " cost " + "$"+fruitPrice + " per pound");
                fruit = false; 
            }
        }
        if (fruit) {
            System.out.println(notFruit);
        }
    }
}
