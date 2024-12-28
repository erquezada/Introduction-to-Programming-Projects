import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CountOccurrencesGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CountOccurrencesGUI::createAndShowGUI);
    }

    // Create and show the GUI
    private static void createAndShowGUI() {
        // Create the frame for the GUI
        JFrame frame = new JFrame("Count Occurrences");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Set up the layout
        frame.setLayout(new BorderLayout());

        // Create components
        JLabel wordLabel = new JLabel("Please enter a word or phrase: ");
        JTextField wordField = new JTextField();
        
        JLabel letterLabel = new JLabel("Please search for a letter: ");
        JTextField letterField = new JTextField();
        
        JButton countButton = new JButton("Count Occurrences");
        JButton clearButton = new JButton("Clear");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(wordLabel);
        panel.add(wordField);
        panel.add(letterLabel);
        panel.add(letterField);
        panel.add(countButton);
        panel.add(clearButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action for the Count button
        countButton.addActionListener(e -> {
            String myStr = wordField.getText().toLowerCase().trim();
            if (myStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "You must enter a valid word or phrase.");
                return;
            }

            String letterInput = letterField.getText().toLowerCase().trim();
            if (letterInput.length() != 1) {
                JOptionPane.showMessageDialog(frame, "Please enter a single letter.");
                return;
            }
            char myLetter = letterInput.charAt(0);

            if (!Character.isLetter(myLetter)) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid letter.");
                return;
            }

            int countNumberOfOccurrences = countOccurrences(myStr, myLetter);
            int totalChars = myStr.length();
            int wordCount = countWords(myStr);
            int vowelCount = countVowels(myStr);
            int consonantCount = countConsonants(myStr);
            boolean isPalindrome = isPalindrome(myStr);
            String cleanedText = removeSpecialCharacters(myStr);

            resultArea.setText(String.format(
                "The letter '%c' occurs %d times in the word/phrase.\n" +
                "Total characters: %d\n" +
                "Word count: %d\n" +
                "Vowel count: %d\n" +
                "Consonant count: %d\n" +
                "Is palindrome: %s\n" +
                "Cleaned text: %s", 
                myLetter, countNumberOfOccurrences, totalChars, wordCount, vowelCount, consonantCount, isPalindrome ? "Yes" : "No", cleanedText));
        });

        // Action for the Clear button
        clearButton.addActionListener(e -> {
            wordField.setText("");
            letterField.setText("");
            resultArea.setText("");
        });

        // Show the frame
        frame.setVisible(true);
    }

    // Method to count occurrences of a character in a string
    public static int countOccurrences(String text, char target) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    // Method to count the number of words in the string
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Method to count the number of vowels in the string
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // Method to count the number of consonants in the string
    public static int countConsonants(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c) && !isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    // Helper method to check if a character is a vowel
    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // Method to check if the string is a palindrome
    public static boolean isPalindrome(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int left = 0, right = cleanedText.length() - 1;
        while (left < right) {
            if (cleanedText.charAt(left) != cleanedText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Method to remove special characters from the string
    public static String removeSpecialCharacters(String text) {
        return text.replaceAll("[^a-zA-Z0-9 ]", "");
    }
}
