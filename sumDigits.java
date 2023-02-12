/* 
This program prints out the sum of the individual digits within a number.
For example if you enter 12, this program would take the sum of 1 + 2 which is 3.
*/
import java.io.*;
import java.util.Scanner; 
class sumDigits {
    // Check if sum of digit using recursion
    static int summerDigits(int digits) {
        if (digits == 0)
            return 0;
        return (digits % 10 + summerDigits(digits / 10));
    }
 
    // Test cases for program are in main method
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
    //Test bench one
        int inputOne = 123;
        int result = summerDigits(inputOne);
        System.out.println("Sum of digits in " + inputOne + " is " + result);
    
    // Test bench two
        int inputTwo = 5555;
        int resultTwo = summerDigits(inputTwo);
        System.out.println("Sum of digits in " + inputTwo + " is " + resultTwo);
        
    //Test bench three
        int inputThree = 37;
        int resultThree = summerDigits(inputThree);
        System.out.println("Sum of digits in " + inputThree + " is " + resultThree);
    
    //Test bench four        
        int inputFour = 32;
        int resultFour = summerDigits(inputFour);
        System.out.println("Sum of digits in " + inputFour + " is " + resultFour);
    
    //Test bench five    
        int inputFive = 999;
        int resultFive = summerDigits(inputFive);
        System.out.println("Sum of digits in " + inputFive + " is " + resultFive);

    //Test bench six
        System.out.println("Enter a number to calculate the sum of digits.");
        int userInput = scnr.nextInt();
        int userResult = summerDigits(userInput);
        System.out.println("Sum of digits in " + userInput + " is " + userResult);
    }
}