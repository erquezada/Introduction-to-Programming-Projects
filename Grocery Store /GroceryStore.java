import java.io.*;
import java.util.Scanner;
public class GroceryStore {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("fruits-database.txt");
        Scanner scanner = new Scanner(System.in);
        double totalPrice = 0;
        double totalPounds = 0;
        double costOfBags = 0.15;
        double giftCardAmount = 0;
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addItem(file, scanner, totalPounds, totalPrice);
                    break;
                case 2:
                    viewCart(totalPounds, totalPrice);
                    break;
                case 3:
                    clearCart(totalPounds, totalPrice);
                    break;
                case 4:
                    checkout(scanner, totalPounds, totalPrice, costOfBags, giftCardAmount);
                    break;
                case 5:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option from the menu.");
            }
        }
    }
    public static void printMenu() {
        System.out.println("Welcome to the Grocery Store");
        System.out.println("Please select an option from the menu:");
        System.out.println("1. Add item");
        System.out.println("2. View cart");
        System.out.println("3. Clear cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
    }
    public static void addItem(File file, Scanner scanner, double totalPounds, double totalPrice) throws FileNotFoundException {
        System.out.println("Please select a fruit:");
        try (Scanner scnr = new Scanner(file)) {
            while (scnr.hasNextLine()) {
                String fruitName = scnr.next();
                double fruitPrice = scnr.nextDouble();
                System.out.println(fruitName + " - $" + fruitPrice);
            }
        }
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the name of the fruit you want to purchase:");
        String enteredFruit = scanner.nextLine();
        try (Scanner scnr = new Scanner(file)) {
            boolean found = false;
            while (scnr.hasNextLine()) {
                String fruitName = scnr.next();
                double fruitPrice = scnr.nextDouble();
                if (fruitName.equalsIgnoreCase(enteredFruit)) {
                    System.out.println("Enter the number of pounds:");
                    double pounds = scanner.nextDouble();
                    if (pounds <= 0) {
                        System.err.println("Please enter a valid weight.");
                        break;
                    }
                    totalPounds += pounds;
                    totalPrice += pounds * fruitPrice;
                    System.out.println("Added " + pounds + " lbs of " + fruitName + " to cart.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Fruit not available.");
            }
        }
    }
    public static void viewCart(double totalPounds, double totalPrice) {
        System.out.println("Items in cart:");
        System.out.printf("Total weight: %.2f lbs | Total price: $%.2f%n", totalPounds, totalPrice);
    }
    public static void clearCart(double totalPounds, double totalPrice) {
        totalPounds = 0;
        totalPrice = 0;
        System.out.println("Cart cleared.");
    }
    public static void checkout(Scanner scanner, double totalPounds, double totalPrice, double costOfBags, double giftCardAmount) {
        System.out.printf("Total weight: %.2f lbs | Total price: $%.2f%n", totalPounds, totalPrice);
        System.out.println("Due to a bag shortage, we encourage customers to bring their own bags.");
        System.out.println("How many bags did you bring? Each bag can hold up to 5 lbs.");
        double bagsBrought = scanner.nextDouble();
        double bagsNeeded = Math.ceil(totalPounds / 5) - bagsBrought;
        if (bagsNeeded < 0) {
            bagsNeeded = 0;
        }
        double bagCost = bagsNeeded * costOfBags;
        double finalTotal = totalPrice + bagCost;
        System.out.println("You need to purchase " + bagsNeeded + " bags.");
        System.out.println("Additional bags cost: $" + bagCost);
        System.out.println("Your new total is: $" + finalTotal);
        System.out.print("Enter gift card amount: ");
        giftCardAmount = scanner.nextDouble();
        if (finalTotal > giftCardAmount) {
            System.err.println("Insufficient funds!");
        } else {
            giftCardAmount -= finalTotal;
            System.out.println("Thank you for shopping at the Grocery Store!");
            System.out.println("Remaining gift card balance: $" + giftCardAmount);
            totalPounds = 0;
            totalPrice = 0;
        }
    }
    public static void exit() {
        System.out.println("Thank you for shopping at the Grocery Store");
        System.out.println("Have a great day!");
        System.exit(0);
    }
}
