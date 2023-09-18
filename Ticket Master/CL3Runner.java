import java.util.Scanner;
import java.io.*;
import java.lang.*;
public class CL3Runner {
	// boolean method to check if credit card number is valid 
	public static boolean isCreditCardNumberValid(String creditCardNumber) { 
		if(creditCardNumber.length() == 16) { 
			for(int i = 0; i < creditCardNumber.length(); i++) {
				if(!Character.isDigit(creditCardNumber.charAt(i))) {
					return false; 
				}
			}
			return true; 

		}else {
			return false;
			}
	}
	// method to view a list of available events	
	public static void viewAllEventNames(Event[] events) {
		for(int i = 0; i < events.length; i++) {
			System.out.print((i+1) + ".");
			events[i].printEventDetails();
		}
	}
	//method to view a list of available concessions 
	public static void viewAllConcessions(Event[] events) {
		for (int i = 0; i < events.length; i++) {
			System.out.println(events[i].getEventName());
			events[i].getConcessionStand().printConcessionDetails(); 
		}
	}
	// main method
	public static void main(String[] args) throws IOException {
		FileReader fileReader = new FileReader("events-info.csv");
		Event[] events = fileReader.readEvents();
		boolean eventMania = true;
		while (eventMania) {
		System.out.println("Welcome to Event Mania!");
      	System.out.println("Please select an option from the menu!");
      	System.out.println("1: View events");
      	System.out.println("2. View concessions");
      	System.out.println("3. Purchase tickets and concessions");
      	System.out.println("4. Exit");

      	// swtich-case statement that allows user to pick various options/cases
      	Scanner scanner = new Scanner(System.in);
      	int switchStarter = scanner.nextInt();
      	switch(switchStarter) {
      		// case 1: will allow the user to view events
      		case 1: 
        	System.out.println("Here is a list of available events!");
        	System.out.println();	
        	viewAllEventNames(events);
            break;    

        	// case 2: will allow the user to view a concession
        	case 2:
        	System.out.println("Here is our concession menu!");
        	System.out.println();
        	viewAllConcessions(events);
        	break;

        	// case 3: will allow the user to purchase tickets for the events and concession items
        	case 3:
        	// prompt user to choose an event from list of available events
         	System.out.println("Please select an event from the menu that you wish to purchase tickets for: ");
         	System.out.println();
         	viewAllEventNames(events);
         	
         	//prompt user to purchase tickets
        	System.out.println("Enter the number of the event you would like to purchase.");
        	Scanner enteredEvent = new Scanner(System.in);
        	int userEvent = enteredEvent.nextInt();
        	Event selectedEvent = events[userEvent];
        	System.out.println("You have selected:");
        	System.out.println(events[userEvent].getEventName()); 

        	//Purchase tickets
        	System.out.println("There are: " + events[userEvent].getNumTicketsAvailable() + " tickets available for this event.");
        	System.out.println("Price per ticket: " + "$" +events[userEvent].getTicketPrice());
        	System.out.println("How many tickets would you like to purchase?");
			Scanner userTickets = new Scanner(System.in);
			int numTickets = userTickets.nextInt();
			System.out.println("You have " + numTickets + " tickets in your cart.");
			
			// If there are tickets available, return the updated ticket count to users
			events[userEvent].ticketsAreAvailable(numTickets);
			// subtract ount of ti ket
			System.out.println("There are: " + events[userEvent].getNumTicketsAvailable() + " tickets left");
			System.out.println("Your ticket total cost is: " + "$" + events[userEvent].purchaseTicket(numTickets));
			System.out.println("There are: " + events[userEvent].getNumTicketsAvailable() + " tickets left");

			// prompt user to purchase concessions
			System.out.println("would you like to purchase concession items? (y/n)");
			Scanner userConcessionScanner = new Scanner(System.in);
			char userOption = userConcessionScanner.next().charAt(0);
			if(userOption == 'y') {         	
         		Concession eventConcession = events[userEvent].getConcessionStand();
         		eventConcession.printConcessionDetails();
         	}
         	System.out.println("Enter the name of the food or drink you would like to purchase.");
        	Scanner concessionScanner = new Scanner(System.in);
        	String enteredConcession = concessionScanner.nextLine();
         	double concessionPrice = 0;

         	//Need to create loop that gives user the option to purchase multiple items
         	if(enteredConcession.equals("Popcorn")) {
         		System.out.println("How many " + enteredConcession + "s would you like to purchase?");
        		concessionPrice = events[userEvent].getConcessionStand().getPopcornPrice();
         		Scanner numberOfFood = new Scanner(System.in);
            	double numberOfFoodItems = numberOfFood.nextDouble();
            	concessionPrice = (numberOfFoodItems * concessionPrice);
         		System.out.println("$" + concessionPrice);		
         	}        	
         	if(enteredConcession.equals("Soda")) {
         		System.out.println("How many " + enteredConcession + "s would you like to purchase?");
	         	concessionPrice = events[userEvent].getConcessionStand().getSodaPrice();
	         	Scanner numberOfFood = new Scanner(System.in);
	            double numberOfFoodItems = numberOfFood.nextDouble();
	            concessionPrice = (numberOfFoodItems * concessionPrice);
	         	System.out.println("$" + concessionPrice);		
         	}
         	if(enteredConcession.equals("Hot Dog")) {
	         	System.out.println("How many " + enteredConcession + "s would you like to purchase?");
	         	concessionPrice = events[userEvent].getConcessionStand().getHotDogPrice();
	         	Scanner numberOfFood = new Scanner(System.in);
	            double numberOfFoodItems = numberOfFood.nextDouble();
	            concessionPrice = (numberOfFoodItems * concessionPrice);
         		System.out.println("$" + concessionPrice);			
         	}
         	//Process payment for user by taking in a credit card number for purchase
         	System.out.println("Enter your credit card number");
         	String creditCardNumber;
         	Scanner creditCardScanner = new Scanner(System.in);
         	creditCardNumber = creditCardScanner.nextLine();
         	if(isCreditCardNumberValid(creditCardNumber)) {
        		System.out.println("Enter the last four digits of your credit card in order to confirm your purchase");
        		String lastFourDigit = creditCardNumber.substring(13);
        		Scanner lastFourDigitScanner = new Scanner(System.in);
        		creditCardNumber = lastFourDigitScanner.nextLine();
        	} else {
        		System.out.println("Please enter a valid credit card number");
        	}
        	//Need to update the total cost of food and tickets
        	double totalSumCost = 0;
        	totalSumCost = events[userEvent].purchaseTicket(numTickets);
        	totalSumCost += concessionPrice;
        	System.out.println("Your credit card ending in " + creditCardNumber + " has been charged. $" + totalSumCost);
        	System.out.println("Your purchase has been confirmed, enjoy!");
        	break;

        	// case 4: allows the user to exit the program if they so choose to
        	case 4:
        	System.out.println("4." + "Exit");
        	System.out.println("Thank you for shopping with Event Mania.");
        	System.out.println("Have a great day!");
        	System.exit(0);
        	break;

      		default:
      		System.out.println("Please enter a valid option from the menu!");
	        break;
      	}
      }
      eventMania = false;
  }
}