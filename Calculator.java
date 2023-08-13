import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double num1 = getInputNumber(scanner, "Enter the first number: ");
        double num2 = getInputNumber(scanner, "Enter the second number: ");
        char operator = getInputOperator(scanner);

        double result = performOperation(num1, num2, operator);

        if (Double.isNaN(result)) {
            System.out.println("Invalid operator");
        } else {
            System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
        }
    }

    private static double getInputNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    private static char getInputOperator(Scanner scanner) {
        System.out.print("Enter the operator (+, -, *, /): ");
        return scanner.next().charAt(0);
    }

    private static double performOperation(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    System.out.println("Division by zero is not allowed");
                    return Double.NaN;
                }
            default:
                return Double.NaN;
        }
    }
}
