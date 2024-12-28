import javax.swing.*;
import java.awt.*;

public class CellularAutomatonGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JTextArea ruleInputArea;
    private JButton startButton, resetButton, randomizeButton, pauseButton;
    private JLabel[] cellLabels;
    private boolean[] board;
    private boolean[] userRule;
    private boolean simulationRunning = false;
    private SwingWorker<Void, Void> worker;

    // Define the 4-bit rule table (converted to true/false)
    private final boolean[][] predefinedRules = {
        {false, false, false, false},
        {false, false, false, true},
        {false, false, true, false},
        {false, false, true, true},
        {false, true, false, false},
        {false, true, false, true},
        {false, true, true, false},
        {false, true, true, true},
        {true, false, false, false},
        {true, false, false, true},
        {true, false, true, false},
        {true, false, true, true},
        {true, true, false, false},
        {true, true, false, true},
        {true, true, true, false},
        {true, true, true, true}
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CellularAutomatonGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("Cellular Automaton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Set up the board panel
        boardPanel = new JPanel(new GridLayout(1, 41)); // 41 cells in a row
        board = new boolean[41];
        cellLabels = new JLabel[41];
        initializeBoard();
        updateBoardDisplay();

        JScrollPane scrollPane = new JScrollPane(boardPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Rule input area
        JPanel rulePanel = new JPanel(new BorderLayout());
        rulePanel.add(new JLabel("Enter rule (4 lines, true/false):"), BorderLayout.NORTH);

        ruleInputArea = new JTextArea(4, 20);
        rulePanel.add(new JScrollPane(ruleInputArea), BorderLayout.CENTER);

        mainPanel.add(rulePanel, BorderLayout.NORTH);

        // Control buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        startButton = new JButton("Start Simulation");
        startButton.addActionListener(e -> {
            if (parseUserRule()) {
                runSimulation();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid input! Enter 4 lines with true/false.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetBoard());

        randomizeButton = new JButton("Randomize Board");
        randomizeButton.addActionListener(e -> randomizeBoard());

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pauseSimulation());

        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(randomizeButton);
        buttonPanel.add(pauseButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        board[board.length / 2] = true; // Initialize the center cell
        for (int i = 0; i < board.length; i++) {
            JLabel cellLabel = new JLabel(".", SwingConstants.CENTER);
            cellLabel.setFont(new Font("Arial", Font.BOLD, 20));
            cellLabel.setPreferredSize(new Dimension(30, 30));
            cellLabels[i] = cellLabel;
            boardPanel.add(cellLabel);
        }
    }

    private void updateBoardDisplay() {
        for (int i = 0; i < board.length; i++) {
            cellLabels[i].setText(board[i] ? "*" : ".");
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private boolean parseUserRule() {
        try {
            // Use predefined rule table for user rule
            userRule = new boolean[4];
            String[] input = ruleInputArea.getText().split("\n");
            if (input.length != 4) return false;

            for (int i = 0; i < 4; i++) {
                userRule[i] = Boolean.parseBoolean(input[i].trim());
            }

            // Map the user input to a corresponding predefined rule row
            int ruleIndex = 0;
            for (int i = 0; i < 4; i++) {
                if (userRule[i]) {
                    ruleIndex += (1 << (3 - i)); // Convert true/false to an index
                }
            }

            // Apply the corresponding rule from the predefined table
            userRule = predefinedRules[ruleIndex];
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void runSimulation() {
        if (simulationRunning) {
            return; // Prevent starting a new simulation while one is running
        }
        simulationRunning = true;

        // Use SwingWorker to manage the simulation on a separate thread
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                // Print initial state (Step 0)
                printBoardToConsole(0);

                // Run simulation steps
                for (int step = 1; step < 20; step++) {
                    updateBoard();
                    // Update board display on the Event Dispatch Thread (EDT)
                    SwingUtilities.invokeLater(() -> updateBoardDisplay());

                    // Print board to console
                    printBoardToConsole(step);

                    // Pause between steps for visualization
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (isCancelled()) {
                        break; // Stop if the simulation is paused
                    }
                }
                simulationRunning = false;
                return null;
            }
        };
        worker.execute();
    }

    private void updateBoard() {
        boolean[] nextBoard = new boolean[board.length];
        for (int i = 0; i < board.length; i++) {
            nextBoard[i] = isAliveNextBoard(i);
        }
        System.arraycopy(nextBoard, 0, board, 0, board.length);
    }

    private boolean isAliveNextBoard(int pos) {
        int liveNeighbors = 0;
        for (int i = Math.max(0, pos - 1); i <= Math.min(board.length - 1, pos + 1); i++) {
            if (board[i]) liveNeighbors++;
        }
        return userRule[liveNeighbors];
    }

    private void printBoardToConsole(int step) {
        StringBuilder output = new StringBuilder("Step " + step + ": ");
        for (boolean cell : board) {
            output.append(cell ? "*" : ".");
        }
        System.out.println(output);
    }

    // Reset the board to the initial state
    private void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = false;
        }
        board[board.length / 2] = true; // Reset the center cell
        updateBoardDisplay();
        if (simulationRunning && worker != null) {
            worker.cancel(true); // Cancel the running simulation if reset is triggered
        }
    }

    // Randomize the board
    private void randomizeBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = Math.random() < 0.5; // Randomly set cells to true or false
        }
        updateBoardDisplay();
    }

    // Pause the simulation
    private void pauseSimulation() {
        if (worker != null) {
            worker.cancel(true); // Cancel the simulation
            simulationRunning = false;
        }
    }
}
