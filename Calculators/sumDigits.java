import java.util.Scanner;

public class SumDigits {
    // Calculate the sum of digits using recursion
    static int sumDigitsRecursive(int number) {
        if (number == 0) {
            return 0;
        }
        return (number % 10 + sumDigitsRecursive(number / 10));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Test cases
        int[] testCases = {123, 5555, 37, 32, 999};
        
        for (int testCase : testCases) {
            int result = sumDigitsRecursive(testCase);
            System.out.println("Sum of digits in " + testCase + " is " + result);
        }
        
        System.out.println("Enter a number to calculate the sum of digits.");
        int userInput = scanner.nextInt();
        int userResult = sumDigitsRecursive(userInput);
        System.out.println("Sum of digits in " + userInput + " is " + userResult);

        scanner.close();
    }
}
