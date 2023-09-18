import java.util.Scanner;

public class CountOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String myStr = getUserInput("Please enter a word or phrase: ", scanner);
        char myLetter = getCharacterInput("Please search for a letter: ", scanner);

        int countNumberOfOccurrences = countOccurrences(myStr, myLetter);

        displayOccurrences(countNumberOfOccurrences);
    }

    public static String getUserInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().toLowerCase();
    }

    public static char getCharacterInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.next().charAt(0);
    }

    public static int countOccurrences(String text, char target) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    public static void displayOccurrences(int count) {
        System.out.println("The letter occurs " + count + " times.");
    }
}
