import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeapYearGUI {
    public static void main(String[] args) {
        // Create a JFrame for the window
        JFrame frame = new JFrame("Leap Year Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        // Create UI components
        JLabel label = new JLabel("Enter a year to check if it's a leap year:");
        JTextField yearInput = new JTextField(10);
        JButton checkButton = new JButton("Check Leap Year");
        JLabel resultLabel = new JLabel("");

        // Add components to the frame
        frame.add(label);
        frame.add(yearInput);
        frame.add(checkButton);
        frame.add(resultLabel);

        // Action listener for the check button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = yearInput.getText();
                // Validate and process the input
                if (isValidYear(input)) {
                    int year = Integer.parseInt(input);
                    boolean isLeapYear = isLeapYear(year);
                    resultLabel.setText(year + (isLeapYear ? " is a leap year." : " is not a leap year."));
                } else {
                    showMessage("Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
    }

    // Function to check if the input year is a valid integer
    private static boolean isValidYear(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Function to check if a year is a leap year
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    // Function to display messages
    private static void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
