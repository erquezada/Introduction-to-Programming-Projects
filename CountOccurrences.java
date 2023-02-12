import java.util.Scanner; // initialize a scanner for user input
public class CountOccurrences{
	public static void main(String[] args){
		//prompt user for word or phrase or sentence
		System.out.println("Please enter a word or phrase: ");
		Scanner scnr = new Scanner(System.in);
		String myStr = scnr.nextLine().toLowerCase();
		//prompt user search for a specific letter from their word or phrase. this will return a count later
		System.out.println("Please search for a letter: ");
		char myLetter = scnr.next().charAt(0);
		// this calls a method called countOccurrances 
		int countNumberOfOccurrences = countOccurrences(myStr, myLetter);

		System.out.println(countNumberOfOccurrences);
	}
	public static int countOccurrences (String myStr, char myLetter) {
//this initializes a for loop that retrives a count with the number of occurrances of a letter in a word or phrase 
		int countNumberOfOccurrences = 0;
		for(int i = 0; i < myStr.length(); i++) {
			if (myStr.charAt(i) == myLetter) {
            countNumberOfOccurrences++;
        	}
    	}
    	//this returns the count of occurrances and gives it back to the main method for later use
    	return countNumberOfOccurrences;
	}
}