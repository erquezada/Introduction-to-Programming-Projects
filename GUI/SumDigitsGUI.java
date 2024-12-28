import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SumDigitsGUI {

    // Calculate the sum of digits using recursion
    static int sumDigitsRecursive(int number) {
        number = Math.abs(number); // Ensure number is positive
        if (number == 0) {
            return 0;
        }
        return (number % 10 + sumDigitsRecursive(number / 10));
    }

    // Create and configure the frame
    private static JFrame createFrame() {
        JFrame frame = new JFrame("Sum of Digits Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    // Create and configure components for the GUI
    private static JPanel createInputPanel(JTextField textField, JLabel resultLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Enter a number:");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton calculateButton = new JButton("Calculate Sum");
        calculateButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the input from the text field
                    String inputText = textField.getText();
                    if (inputText.isEmpty()) {
                        throw new NumberFormatException("Input is empty");
                    }
                    int number = Integer.parseInt(inputText);

                    // Calculate the sum of digits using recursion
                    int result = sumDigitsRecursive(number);

                    // Display the result
                    resultLabel.setText("Sum of digits in " + number + " is " + result);
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    resultLabel.setText("Please enter a valid number");
                }
            }
        });

        // Reset button to clear input and result
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                resultLabel.setText("Sum of digits will appear here");
            }
        });

        // Add components to the panel
        panel.add(label);
        panel.add(textField);
        panel.add(calculateButton);
        panel.add(resetButton);
        panel.add(resultLabel);

        return panel;
    }

    public static void main(String[] args) {
        // Initialize the frame and components
        JFrame frame = createFrame();
        JTextField textField = new JTextField(15);
        JLabel resultLabel = new JLabel("Sum of digits will appear here");

        // Create input panel and add to frame
        JPanel inputPanel = createInputPanel(textField, resultLabel);
        frame.add(inputPanel, BorderLayout.CENTER);

        // Set visible
        frame.setVisible(true);
    }
}
