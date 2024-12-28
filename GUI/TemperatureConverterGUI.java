import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI {
    private static final int MAX_HISTORY = 3;
    private static String[] history = new String[MAX_HISTORY];
    private static int historyIndex = 0;

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create labels
        JLabel labelFahrenheit = new JLabel("Enter temperature in Fahrenheit:");
        labelFahrenheit.setBounds(50, 50, 250, 25);
        frame.add(labelFahrenheit);

        // Create input field for Fahrenheit
        JTextField fahrenheitInput = new JTextField();
        fahrenheitInput.setBounds(250, 50, 100, 25);
        frame.add(fahrenheitInput);

        // Create button for conversion
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(150, 100, 100, 30);
        frame.add(convertButton);

        // Create button to clear input and results
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(150, 140, 100, 30);
        frame.add(clearButton);

        // Create output labels for results
        JLabel resultKelvin = new JLabel("Temperature in Kelvin: ");
        resultKelvin.setBounds(50, 180, 300, 25);
        frame.add(resultKelvin);

        JLabel resultCelsius = new JLabel("Temperature in Celsius: ");
        resultCelsius.setBounds(50, 210, 300, 25);
        frame.add(resultCelsius);

        JLabel resultKelvinViaCelsius = new JLabel("Temperature in Kelvin (via Celsius): ");
        resultKelvinViaCelsius.setBounds(50, 240, 300, 25);
        frame.add(resultKelvinViaCelsius);

        JLabel historyLabel = new JLabel("Conversion History:");
        historyLabel.setBounds(50, 270, 200, 25);
        frame.add(historyLabel);

        JList<String> historyList = new JList<>(history);
        historyList.setBounds(200, 270, 200, 50);
        frame.add(historyList);

        // Action when the convert button is pressed
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input temperature in Fahrenheit
                    double tempFah = Double.parseDouble(fahrenheitInput.getText());

                    // Direct conversion from Fahrenheit to Kelvin
                    double tempKel = fahToKel(tempFah);
                    resultKelvin.setText("Temperature in Kelvin: " + String.format("%.2f", tempKel));

                    // Two-step conversion (Fahrenheit -> Celsius -> Kelvin)
                    double tempCel = fahToCel(tempFah);
                    double tempKelFromCel = celToKel(tempCel);
                    resultCelsius.setText("Temperature in Celsius: " + String.format("%.2f", tempCel));
                    resultKelvinViaCelsius.setText("Temperature in Kelvin (via Celsius): " + String.format("%.2f", tempKelFromCel));

                    // Add conversion to history
                    addToHistory(tempFah, tempCel, tempKel, tempKelFromCel);
                    historyList.setListData(history);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for Fahrenheit.");
                }
            }
        });

        // Action when the clear button is pressed
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fahrenheitInput.setText("");
                resultKelvin.setText("Temperature in Kelvin: ");
                resultCelsius.setText("Temperature in Celsius: ");
                resultKelvinViaCelsius.setText("Temperature in Kelvin (via Celsius): ");
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Converts Fahrenheit to Celsius
    public static double fahToCel(double fah) {
        return ((fah - 32) * 5) / 9;
    }

    // Converts Celsius to Kelvin
    public static double celToKel(double cel) {
        return cel + 273.15;
    }

    // Directly converts Fahrenheit to Kelvin
    public static double fahToKel(double fah) {
        return ((fah - 32) * 5) / 9 + 273.15;
    }

    // Adds conversion to history array
    private static void addToHistory(double fah, double cel, double kel, double kelFromCel) {
        String conversion = String.format("F: %.2f -> C: %.2f -> K: %.2f (via C: %.2f)", fah, cel, kel, kelFromCel);
        history[historyIndex] = conversion;
        historyIndex = (historyIndex + 1) % MAX_HISTORY;
    }
}
