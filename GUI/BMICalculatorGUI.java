import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorGUI {

    public static void main(String[] args) {
        // Create a frame and set properties
        JFrame frame = createFrame();

        // Create UI components for input and output
        JTextField feetField = createTextField();
        JTextField inchesField = createTextField();
        JTextField weightField = createTextField();
        JLabel bmiLabel = new JLabel("Your BMI: ");
        JLabel bmiCategoryLabel = new JLabel("");

        // Create button to trigger BMI calculation
        JButton calculateButton = createCalculateButton(feetField, inchesField, weightField, bmiLabel, bmiCategoryLabel, frame);

        // Add components to the frame
        addComponentsToFrame(frame, feetField, inchesField, weightField, bmiLabel, bmiCategoryLabel, calculateButton);

        // Display the frame
        frame.setVisible(true);
    }

    // Create and return the frame
    private static JFrame createFrame() {
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(6, 2));
        return frame;
    }

    // Create and return a JTextField
    private static JTextField createTextField() {
        return new JTextField();
    }

    // Create and return a JButton with ActionListener for BMI calculation
    private static JButton createCalculateButton(JTextField feetField, JTextField inchesField, JTextField weightField, 
                                                 JLabel bmiLabel, JLabel bmiCategoryLabel, JFrame frame) {
        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get user input
                    int feet = Integer.parseInt(feetField.getText());
                    int inches = Integer.parseInt(inchesField.getText());
                    float weight = Float.parseFloat(weightField.getText());

                    // Convert weight to kilograms and height to meters
                    float kilograms = convertWeightToKg(weight);
                    float totalMeters = convertHeightToMeters(feet, inches);

                    // Calculate BMI and display results
                    float bmi = calculateBMI(kilograms, totalMeters);
                    bmiLabel.setText("Your BMI: " + String.format("%.2f", bmi));
                    bmiCategoryLabel.setText("Category: " + getBMICategory(bmi));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for height and weight.");
                }
            }
        });
        return calculateButton;
    }

    // Add components to the frame
    private static void addComponentsToFrame(JFrame frame, JTextField feetField, JTextField inchesField, JTextField weightField, 
                                             JLabel bmiLabel, JLabel bmiCategoryLabel, JButton calculateButton) {
        frame.add(new JLabel("Enter height (feet): "));
        frame.add(feetField);
        frame.add(new JLabel("Enter height (inches): "));
        frame.add(inchesField);
        frame.add(new JLabel("Enter weight (pounds): "));
        frame.add(weightField);
        frame.add(calculateButton);
        frame.add(bmiLabel);
        frame.add(bmiCategoryLabel);
    }

    // Method to convert weight from pounds to kilograms
    private static float convertWeightToKg(float weightInPounds) {
        final float POUNDS_TO_KILOGRAMS = 0.4536f;
        return weightInPounds * POUNDS_TO_KILOGRAMS;
    }

    // Method to convert height from feet and inches to meters
    private static float convertHeightToMeters(int feet, int inches) {
        final float FEET_TO_METERS = 0.3048f;
        final float INCHES_TO_METERS = 0.0254f;
        return (feet * FEET_TO_METERS) + (inches * INCHES_TO_METERS);
    }

    // Method to calculate BMI
    public static float calculateBMI(float weightInKg, float heightInMeters) {
        return weightInKg / (heightInMeters * heightInMeters);
    }

    // Method to determine the BMI category
    public static String getBMICategory(float bmi) {
        if (bmi <= 18.4) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal weight";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
