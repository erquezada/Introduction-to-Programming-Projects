import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IceWalkerWithItemsGUI extends JFrame {
    private int[][] board;
    private int row = 0;
    private int col = 0;
    private int collectedItems = 0;
    private JLabel positionLabel;
    private JLabel collectedItemsLabel;
    private JTextArea boardTextArea;
    
    public IceWalkerWithItemsGUI() {
        setTitle("Ice Walker with Items");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        board = generateRandomBoard(5, 5);
        board[row][col] = 2;
        board[board.length - 1][board[0].length - 1] = 2;

        positionLabel = new JLabel("You are at: Row " + row + " Col " + col);
        collectedItemsLabel = new JLabel("Items collected: " + collectedItems);
        
        // Board TextArea
        boardTextArea = new JTextArea(10, 10);
        boardTextArea.setEditable(false);
        boardTextArea.setText(getBoardString());
        JScrollPane scrollPane = new JScrollPane(boardTextArea);

        // Direction buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton upButton = new JButton("Up");
        upButton.addActionListener(new DirectionButtonListener('u'));
        JButton downButton = new JButton("Down");
        downButton.addActionListener(new DirectionButtonListener('d'));
        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(new DirectionButtonListener('l'));
        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(new DirectionButtonListener('r'));

        buttonPanel.add(leftButton);
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(rightButton);

        // Adding components to the frame
        add(positionLabel, BorderLayout.NORTH);
        add(collectedItemsLabel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private class DirectionButtonListener implements ActionListener {
        private char direction;

        public DirectionButtonListener(char direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            movePlayer(direction);
        }
    }

    private void movePlayer(char direction) {
        int newRow = row;
        int newCol = col;

        if (direction == 'l') {
            newCol--;
        } else if (direction == 'r') {
            newCol++;
        } else if (direction == 'u') {
            newRow--;
        } else if (direction == 'd') {
            newRow++;
        }

        if (isValidMove(newRow, newCol)) {
            row = newRow;
            col = newCol;
            if (board[row][col] > 0) {
                collectedItems += board[row][col];
                collectedItemsLabel.setText("Items collected: " + collectedItems);
                board[row][col] = 0;
            }
            board[row][col]--;
            if (board[row][col] == 2) {
                JOptionPane.showMessageDialog(this, "You've fallen through the ice!");
                return;
            }
            if (row == board.length - 1 && col == board[0].length - 1) {
                JOptionPane.showMessageDialog(this, "Congratulations! You have made it to the end!");
                return;
            }
            updateBoard();
        } else {
            JOptionPane.showMessageDialog(this, "You are out of bounds. Please go in a different direction.");
        }
    }

    private void updateBoard() {
        positionLabel.setText("You are at: Row " + row + " Col " + col);
        boardTextArea.setText(getBoardString());
    }

    private String getBoardString() {
        StringBuilder boardString = new StringBuilder();
        for (int[] row : board) {
            for (int value : row) {
                if (value == 0) {
                    boardString.append(". ");
                } else if (value == 2) {
                    boardString.append("X ");
                } else {
                    boardString.append(value).append(" ");
                }
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    private boolean isValidMove(int newRow, int newCol) {
        return newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length;
    }

    private int[][] generateRandomBoard(int rows, int cols) {
        int[][] board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = (int) (Math.random() * 4) + 1;
            }
        }
        return board;
    }

    public static void main(String[] args) {
        new IceWalkerWithItemsGUI();
    }
}
