import java.util.Scanner;

public class BMI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int userFeet = getUserHeight("feet", sc);
        int userInch = getUserHeight("inches", sc);
        System.out.println("You are: " + userFeet + " feet and " + userInch + " inches tall");

        float userWeight = getUserWeight(sc);
        System.out.println("Your current weight is: " + userWeight + " pounds");

        final float POUNDS_TO_KILOGRAMS = 0.4536f;
        final float FEET_TO_METERS = 0.3048f;
        final float INCHES_TO_METERS = 0.0254f;

        float kilograms = userWeight * POUNDS_TO_KILOGRAMS;
        float totalMeters = (userFeet * FEET_TO_METERS) + (userInch * INCHES_TO_METERS);
        float bmiCalculation = calculateBMI(kilograms, totalMeters);

        System.out.println("Your current BMI is: " + bmiCalculation);
        printBMICategory(bmiCalculation);

        sc.close();
    }

    public static int getUserHeight(String unit, Scanner sc) {
        System.out.println("Enter your height in " + unit);
        return sc.nextInt();
    }

    public static float getUserWeight(Scanner sc) {
        System.out.println("Enter your weight in pounds");
        return sc.nextFloat();
    }

    public static float calculateBMI(float weightInKg, float heightInMeters) {
        return weightInKg / (heightInMeters * heightInMeters);
    }

    public static void printBMICategory(float bmi) {
        if (bmi <= 18.4) {
            System.out.println("You are underweight");
        } else if (bmi <= 24.9) {
            System.out.println("You are normal");
        } else if (bmi <= 29.9) {
            System.out.println("You are overweight");
        } else {
            System.out.println("You are obese");
        }
    }
}
