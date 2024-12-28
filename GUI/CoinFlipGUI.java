import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CoinFlipGUI {

    private enum Coin {
        HEAD, TAIL
    }

    private Coin guess;
    private int wins = 0;
    private int losses = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoinFlipGUI game = new CoinFlipGUI();
            game.createAndShowGUI();
        });
    }

    // Create and show the GUI
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Coin Toss Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Press Space to Start the Coin Toss Game!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Buttons for Heads and Tails
        JButton headsButton = new JButton("Heads");
        JButton tailsButton = new JButton("Tails");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(headsButton);
        buttonPanel.add(tailsButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Result label and Scoreboard
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(resultLabel, BorderLayout.CENTER);

        // Coin image display
        JLabel coinImageLabel = new JLabel();
        coinImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(coinImageLabel, BorderLayout.CENTER);

        // Score label
        JLabel scoreLabel = new JLabel("Wins: 0  |  Losses: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(scoreLabel, BorderLayout.SOUTH);

        // Set up key listener to start the game when the space bar is pressed
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    titleLabel.setText("Good luck! Choose Heads or Tails to start!");
                }
            }
        });
        frame.setFocusable(true); // This allows the frame to capture key events
        frame.requestFocusInWindow(); // This ensures the frame gets focus on start

        // Set up the game logic for button clicks using lambdas
        headsButton.addActionListener((ActionEvent e) -> {
            guess = Coin.HEAD;
            playGame(resultLabel, scoreLabel, coinImageLabel);
        });

        tailsButton.addActionListener((ActionEvent e) -> {
            guess = Coin.TAIL;
            playGame(resultLabel, scoreLabel, coinImageLabel);
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    // Start the coin toss game
    private void playGame(JLabel resultLabel, JLabel scoreLabel, JLabel coinImageLabel) {
        Coin toss = tossCoin();
        String tossResult = (toss == Coin.HEAD) ? "Heads" : "Tails";  // Get the result of the toss

        // Update coin image based on toss result
        String imagePath = (toss == Coin.HEAD) ? "heads.png" : "tails.png";
        coinImageLabel.setIcon(new ImageIcon(imagePath));

        // Display result and update score
        if (guess == toss) {
            wins++;
            resultLabel.setText("Congratulations! You won the toss! The result was: " + tossResult);
        } else {
            losses++;
            resultLabel.setText("Sorry! You lost the toss. The result was: " + tossResult);
        }
        // Update score
        scoreLabel.setText("Wins: " + wins + " | Losses: " + losses);
    }

    // Flip a coin and return result
    private Coin tossCoin() {
        Random r = new Random();
        int i = r.nextInt(2);
        return (i == 0) ? Coin.HEAD : Coin.TAIL;
    }
}
