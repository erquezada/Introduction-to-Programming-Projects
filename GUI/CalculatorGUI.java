import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {

    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private char operator;
    private boolean isOperatorClicked = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CalculatorGUI window = new CalculatorGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CalculatorGUI() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 500);  // Increased height for better button display
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Create and style the text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        // Create the panel for buttons with improved grid layout
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(5, 4, 10, 10));  // Adjusted grid layout for more space

        // Button labels, including additional memory and clear buttons
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "M+", "MR", "M-"
        };

        // Add buttons to the panel with consistent size and padding
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.setPreferredSize(new Dimension(80, 80));  // Consistent button size
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "=":
                    calculateResult();
                    break;
                case "C":
                    clear();
                    break;
                case "M+":
                    memoryAdd();
                    break;
                case "MR":
                    memoryRecall();
                    break;
                case "M-":
                    memorySubtract();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    handleOperator(command.charAt(0));
                    break;
                default:
                    appendToDisplay(command);
                    break;
            }
        }
    }

    private void calculateResult() {
        try {
            num2 = Double.parseDouble(textField.getText());
            result = performOperation(num1, num2, operator);
            textField.setText(String.valueOf(result));
            isOperatorClicked = false;
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input");
        }
    }

    private void handleOperator(char op) {
        if (isOperatorClicked) {
            return;
        }

        try {
            num1 = Double.parseDouble(textField.getText());
            operator = op;
            textField.setText("");
            isOperatorClicked = true;
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input");
        }
    }

    private void appendToDisplay(String text) {
        textField.setText(textField.getText() + text);
        isOperatorClicked = false;
    }

    private void clear() {
        textField.setText("");
        num1 = num2 = result = 0;
        isOperatorClicked = false;
    }

    private void memoryAdd() {
        try {
            result += Double.parseDouble(textField.getText());
            textField.setText("");
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input");
        }
    }

    private void memoryRecall() {
        textField.setText(String.valueOf(result));
    }

    private void memorySubtract() {
        try {
            result -= Double.parseDouble(textField.getText());
            textField.setText("");
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input");
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private double performOperation(double num1, double num2, char operator) {
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
                    showErrorMessage("Division by zero is not allowed");
                    return Double.NaN;
                }
            default:
                return Double.NaN;
        }
    }
}
