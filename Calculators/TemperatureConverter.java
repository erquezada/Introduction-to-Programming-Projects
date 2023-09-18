import java.util.Scanner;
public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a temperature in Fahrenheit: ");
        double tempFah = scanner.nextDouble();
        scanner.close();

        double tempCel = fahToCel(tempFah);
        double tempKel = celToKel(tempCel);

        System.out.println("The temperature in Celsius is: " + tempCel);
        System.out.println("The temperature in Kelvin is: " + tempKel);
    }
    // Converts Fahrenheit to Celsius
    public static double fahToCel(double fah) {
        return ((fah - 32) * 5) / 9;
    }
    // Converts Celsius to Kelvin
    public static double celToKel(double cel) {
        return cel + 273.15;
    }
}
