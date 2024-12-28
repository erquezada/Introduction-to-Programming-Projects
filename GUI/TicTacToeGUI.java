import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private static final int SIZE = 3; // Board size
    private static final char EMPTY_SPACE = '_';
    private char[][] board = new char[SIZE][SIZE];
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private JLabel statusLabel;
    private int turn = 0;
    private boolean isAITurn = false; // Flag to check if it's AI's turn

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Initialize the board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY_SPACE;
            }
        }

        // Create a panel for the board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE));

        // Create buttons for the board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("_");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                boardPanel.add(buttons[i][j]);
            }
        }

        // Create a status label
        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create a reset button
        JButton resetButton = new JButton("Restart Game");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(resetButton, BorderLayout.SOUTH);

        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[row][col] == EMPTY_SPACE && !isAITurn) {
                makeMove(row, col, 'X');
                if (!checkGameState('X')) {
                    isAITurn = true;
                    statusLabel.setText("Player O's turn");
                    AIPlay();
                }
            }
        }
    }

    private void makeMove(int row, int col, char player) {
        board[row][col] = player;
        buttons[row][col].setText(String.valueOf(player));
        buttons[row][col].setEnabled(false);
        buttons[row][col].setForeground(player == 'X' ? Color.RED : Color.BLUE);
    }

    private boolean checkGameState(char player) {
        if (checkWin(player)) {
            statusLabel.setText("Player " + player + " wins!");
            disableButtons();
            return true;
        } else if (checkTie()) {
            statusLabel.setText("The game is a tie.");
            return true;
        }
        return false;
    }

    public boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Horizontal win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Vertical win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win
        }
        return false;
    }

    public boolean checkTie() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY_SPACE) {
                    return false; // Board is not full
                }
            }
        }
        return true; // Board is full, game is a tie
    }

    public void disableButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void resetGame() {
        // Reset the board and buttons
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY_SPACE;
                buttons[i][j].setText("_");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
        turn = 0;
        isAITurn = false;
        statusLabel.setText("Player X's turn");
    }

    // AI Logic using Minimax Algorithm
    private void AIPlay() {
        int[] bestMove = minimax(board, 'O');
        makeMove(bestMove[0], bestMove[1], 'O');
        checkGameState('O');
        isAITurn = false;
        statusLabel.setText("Player X's turn");
    }

    // Minimax Algorithm
    public int[] minimax(char[][] board, char player) {
        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY_SPACE) {
                    board[i][j] = player;
                    int score = minimaxScore(board, player);
                    board[i][j] = EMPTY_SPACE;

                    if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimaxScore(char[][] board, char player) {
        if (checkWin('O')) {
            return 1;
        } else if (checkWin('X')) {
            return -1;
        } else if (checkTie()) {
            return 0;
        }

        char nextPlayer = (player == 'O') ? 'X' : 'O';
        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY_SPACE) {
                    board[i][j] = player;
                    int score = minimaxScore(board, nextPlayer);
                    board[i][j] = EMPTY_SPACE;

                    if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                        bestScore = score;
                    }
                }
            }
        }
        return bestScore;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToeGUI game = new TicTacToeGUI();
                game.setVisible(true);
            }
        });
    }
}
