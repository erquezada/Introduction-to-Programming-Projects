import java.util.Scanner;

public class TemperatureConverter {
	public static void main(String[] args) {
		Scanner userScnr = new Scanner(System.in);
		
		System.out.println("Enter a temperature in fahrenheit: ");
		double tempFah = userScnr.nextDouble();

		double tempCel = fahToCel(tempFah);
		System.out.println("The temperature in celsius is: " + tempCel);

		double tempKel = celToKel(tempCel);
		System.out.println("The temperature in kelvin is: " + tempKel);

	}
	// Converts fahrenheit to celsius
	public static double fahToCel(double fah) {
		double cel = ((fah - 32) * 5) /9;
		return cel;
	}

	// Converts celsius to kelvin
	public static double celToKel(double cel){
		double kel = cel + 273.15;
		return kel;
	}
}
