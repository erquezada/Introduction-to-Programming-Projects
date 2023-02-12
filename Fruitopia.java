// This file runs an interactive program called Fruitopia
import java.io.*;
import java.util.Scanner;
import java.lang.*;
public class Fruitopia  {
  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("fruits-database.txt");
    Scanner scnr = new Scanner(file);
    Scanner scanner = new Scanner(System.in);

    //Declare variables
    boolean fruit = true;
    boolean option = true;
    double totalPrice = 0;
    double totalPounds = 0;
    double numberOfBagsUserHasToBuy = 0;
    double costOfBags = 0.15;
    double giftCardAmount = 0;
    //Home screen
    // Write a welcome message that displays the name of the store to the user
    // Ask user to select an option from a menu
    // start switch statement
    while (true) {
      System.out.println("Welcome to Fruitopia");
      System.out.println("Please select an option from the menu!");
      System.out.println("1. " + "Add item");
      System.out.println("2. " + "View cart");
      System.out.println("3. " + "Clear cart");
      System.out.println("4. " + "Check out");
      System.out.println("5. " + "Exit");

      int switchStarter = scanner.nextInt();
      switch (switchStarter) {

        //If user selects option 1
      case 1:
        System.out.println("1." + "Add item");
        System.out.println("Please select a fruit");
        System.out.println();
        scnr = new Scanner(file);
        while (scnr.hasNextLine()) {

          String fruitName = scnr.next();
          double fruitPrice = scnr.nextDouble();
          System.out.println("Fruit: " + fruitName);
          System.out.println("Price: " + fruitPrice);
          System.out.println();
        }
        // Ask user for the name of the fruit they would like to purchase
        scanner = new Scanner(System.in);
        System.out.println("Enter the name of the fruit you would like to purchase.");
        String enteredFruit = scanner.nextLine();
        scnr = new Scanner(file);
        while (scnr.hasNextLine()) {
          String fruitName1 = scnr.next();
          double fruitPrice1 = scnr.nextDouble();

          if (fruitName1.equals(enteredFruit)) {
            System.out.println("How many pounds of this fruit are you adding?");
            System.out.println("Enter a whole number greater than zero.");
            double pounds = scanner.nextDouble();
            if(pounds <= 0) {
              System.err.println("Enter a whole number greater than zero.");
              break;
            }
            totalPounds = totalPounds + pounds;
            totalPrice = totalPrice + (pounds * fruitPrice1);
            fruit = false;
          }
        }
        if (fruit) {
          System.out.println("Fruit not available");
          System.out.println("Enter the name of the fruit you would like to purchase.");
        }
        fruit = true;

        // Display current total weight and current total price
        System.out.println("Your current total weight is: " + totalPounds + " lbs and total price is: $" + totalPrice);
        break;

        //If user selects option 2:
      case 2:
        System.out.println("2." + "View cart");
        //Create an array to list items inside of cart for user
        // Display user cart with current total pounds and total price
        System.out.println("Your current total weight is: " + totalPounds + " lbs and total price is: $ " + totalPrice);
        //System.out.println(totalPounds + totalPrice);
        break;

        //If user selects option 3:
      case 3:
        System.out.println("3." + "Clear cart");

        totalPounds *= 0;
        totalPrice *= 0;

        // This empties the users cart
        System.out.println("Your current total weight is: " + totalPounds + " lbs and total price is: $" + totalPrice);
        break;

        //If user selects option 4:
        case 4: 
          System.out.println("Your current total weight is: " + totalPounds + " lbs" + "\nYour current total price is: $"+ totalPrice);
          System.out.println("Due to a bag shortage, Fruitopia does not give out bags for free.");
          System.out.println("We encourage our customers to bring their own bags.");
          System.out.println("How many bags did you bring?");
          System.out.println("Each bag can hold up to five pounds.");
          System.out.println("Enter a whole number greater than zero.");
          double numberOfBagsUserBrought = scanner.nextDouble();
          numberOfBagsUserHasToBuy = (double) (Math.ceil(totalPounds / 5) - numberOfBagsUserBrought) + (totalPounds % 5);
          if(numberOfBagsUserBrought < 0) {
              System.err.println("Enter a whole number greater than zero.");
              break;
          }
          totalPrice += numberOfBagsUserHasToBuy *0.15;
          System.out.println("Your cart's total weight is: "+ totalPounds + " lbs");
          System.out.println("So you need to purchase "+ numberOfBagsUserHasToBuy + " bags."); 
          System.out.println("If you did not bring enough bags, we will supply you with bags for a fee."); 
          System.out.println("additional bags which cost: "); 
          System.out.println(costOfBags + " cents."); 
          System.out.println("Your total is: $"+ totalPrice);
          System.out.print("Enter gift card amount: ");
          giftCardAmount = scanner.nextDouble();
          if (totalPrice>giftCardAmount) {
            System.err.println("Insufficient funds!");
          } else {
            giftCardAmount-=totalPrice;
            System.out.println("Thank you for shopping at Fruitopia, come back soon!\n" + "Remaing gift card balance: $"+ giftCardAmount);
            totalPrice = 0;
            totalPounds = 0;
          }
          break;

        //If user selects option 5:
      case 5:
        System.out.println("5." + "Exit");
        System.out.println("Thank you for shopping at Fruitopia");
        System.out.println("Have a great day!");
        System.exit(0);
        break;

      default:
        System.out.println("Please select an option from the menu!");
        System.out.println("Thank you for shopping at Fruitopia");
        System.out.println("Have a great day!");

        break;
      }
    }
  }
}