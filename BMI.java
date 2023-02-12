import java.util.Scanner;
public class BMI {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in); // Get user's height
            System.out.println("Enter your height in feet followed by inches");

            int userFeet = sc.nextInt();
            int userInch = sc.nextInt();

            System.out.println("You are: " + userFeet + " feet" + " and " +  userInch + " inches tall");

            System.out.println("Enter your weight in pounds"); // Get user's weight
            
            float userWeight = sc.nextFloat();
            System.out.println("Your current weight is: " + userWeight + " pounds"); // Display user's height & weight

            float kilograms = (float) (userWeight*0.4536);
            float feetToMeters = (float) (userFeet/3.2808);
            float inchesToMeters = (float) (userInch*0.0254);

            float bmiCalculation = kilograms/((feetToMeters+inchesToMeters)*(feetToMeters+inchesToMeters)); // calculate BMI
            System.out.println("Your current BMI is: " + bmiCalculation); // Display user's BMI

            if(bmiCalculation <= 18.4) System.out.println("You are underweight");
            else if(bmiCalculation >= 18.5 && bmiCalculation <= 24.9) System.out.println("You are normal");
            else if(bmiCalculation >= 25.0 && bmiCalculation <= 29.9) System.out.println("You are overweight");
            else System.out.println("You are obese");
        }
    }