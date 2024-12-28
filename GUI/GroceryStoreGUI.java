import java.io.*;
import java.util.Scanner;

public class GroceryStoreGUI extends Application {
    private double totalPounds = 0;
    private double totalPrice = 0;
    private double costOfBags = 0.15;
    private double giftCardAmount = 0;
    private File file = new File("fruits-database.csv");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));

        // Create UI elements
        Label titleLabel = new Label("Welcome to the Grocery Store");
        Button addItemButton = new Button("Add Item");
        Button viewCartButton = new Button("View Cart");
        Button clearCartButton = new Button("Clear Cart");
        Button checkoutButton = new Button("Checkout");
        Button exitButton = new Button("Exit");
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);

        // Add UI elements to layout
        layout.getChildren().addAll(titleLabel, addItemButton, viewCartButton, clearCartButton, checkoutButton, exitButton, displayArea);

        // Define button actions
        addItemButton.setOnAction(e -> addItem(displayArea));
        viewCartButton.setOnAction(e -> viewCart(displayArea));
        clearCartButton.setOnAction(e -> clearCart(displayArea));
        checkoutButton.setOnAction(e -> checkout(displayArea));
        exitButton.setOnAction(e -> exit());

        // Create and show scene
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Grocery Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addItem(TextArea displayArea) {
        try (Scanner scanner = new Scanner(file)) {
            displayArea.appendText("Please select a fruit:\n");
            while (scanner.hasNextLine()) {
                String fruitName = scanner.next();
                double fruitPrice = scanner.nextDouble();
                displayArea.appendText(fruitName + " - $" + fruitPrice + "\n");
            }

            // Ask for the fruit and pounds
            TextInputDialog fruitDialog = new TextInputDialog();
            fruitDialog.setTitle("Select Fruit");
            fruitDialog.setHeaderText("Enter the name of the fruit you want to purchase:");
            String enteredFruit = fruitDialog.showAndWait().orElse("");

            // Look for the fruit and add it to the cart
            boolean found = false;
            try (Scanner scnr = new Scanner(file)) {
                while (scnr.hasNextLine()) {
                    String fruitName = scnr.next();
                    double fruitPrice = scnr.nextDouble();
                    if (fruitName.equalsIgnoreCase(enteredFruit)) {
                        TextInputDialog poundsDialog = new TextInputDialog();
                        poundsDialog.setTitle("Enter Pounds");
                        poundsDialog.setHeaderText("Enter the number of pounds:");
                        double pounds = Double.parseDouble(poundsDialog.showAndWait().orElse("0"));
                        if (pounds <= 0) {
                            displayArea.appendText("Please enter a valid weight.\n");
                            break;
                        }
                        totalPounds += pounds;
                        totalPrice += pounds * fruitPrice;
                        displayArea.appendText("Added " + pounds + " lbs of " + fruitName + " to cart.\n");
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                displayArea.appendText("Fruit not available.\n");
            }
        } catch (FileNotFoundException e) {
            displayArea.appendText("Fruit database file not found.\n");
        }
    }

    private void viewCart(TextArea displayArea) {
        displayArea.appendText("Items in cart:\n");
        displayArea.appendText(String.format("Total weight: %.2f lbs | Total price: $%.2f\n", totalPounds, totalPrice));
    }

    private void clearCart(TextArea displayArea) {
        totalPounds = 0;
        totalPrice = 0;
        displayArea.appendText("Cart cleared.\n");
    }

    private void checkout(TextArea displayArea) {
        displayArea.appendText(String.format("Total weight: %.2f lbs | Total price: $%.2f\n", totalPounds, totalPrice));
        displayArea.appendText("Due to a bag shortage, we encourage customers to bring their own bags.\n");
        TextInputDialog bagsDialog = new TextInputDialog();
        bagsDialog.setTitle("Bag Information");
        bagsDialog.setHeaderText("How many bags did you bring? Each bag can hold up to 5 lbs.");
        double bagsBrought = Double.parseDouble(bagsDialog.showAndWait().orElse("0"));
        double bagsNeeded = Math.ceil(totalPounds / 5) - bagsBrought;
        if (bagsNeeded < 0) {
            bagsNeeded = 0;
        }
        double bagCost = bagsNeeded * costOfBags;
        double finalTotal = totalPrice + bagCost;

        displayArea.appendText("You need to purchase " + bagsNeeded + " bags.\n");
        displayArea.appendText("Additional bags cost: $" + bagCost + "\n");
        displayArea.appendText("Your new total is: $" + finalTotal + "\n");

        TextInputDialog giftCardDialog = new TextInputDialog();
        giftCardDialog.setTitle("Gift Card");
        giftCardDialog.setHeaderText("Enter gift card amount:");
        giftCardAmount = Double.parseDouble(giftCardDialog.showAndWait().orElse("0"));

        if (finalTotal > giftCardAmount) {
            displayArea.appendText("Insufficient funds!\n");
        } else {
            giftCardAmount -= finalTotal;
            displayArea.appendText("Thank you for shopping at the Grocery Store!\n");
            displayArea.appendText("Remaining gift card balance: $" + giftCardAmount + "\n");
            totalPounds = 0;
            totalPrice = 0;
        }
    }

    private void exit() {
        System.out.println("Thank you for shopping at the Grocery Store");
        System.out.println("Have a great day!");
        System.exit(0);
    }
}
