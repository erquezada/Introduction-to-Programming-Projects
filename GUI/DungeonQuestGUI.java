import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DungeonQuestGUI extends JFrame {
    // Constants for dungeon
    static final int NUM_ROWS = 4;
    static final int NUM_COLUMNS = 4;
    static final int ROOM_TYPE_EMPTY = 0;
    static final int ROOM_TYPE_SHIELD = 1;
    static final int ROOM_TYPE_SWORD = 2;
    static final int ROOM_TYPE_DRAGON = 3;
    static final int ROOM_TYPE_HEALING = 4;  // New room type for healing
    
    // Game state variables
    private int row = 0, col = 0;
    private boolean isSwordAcquired = false, isShieldAcquired = false;
    private double playerHealth = 10;
    private int[][] dungeon;

    private JLabel healthLabel, positionLabel, roomInfoLabel;
    private JButton leftButton, rightButton, upButton, downButton;

    public DungeonQuestGUI() {
        setTitle("Dungeon Quest: The Dragon's Lair");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        dungeon = generateRandomDungeon();

        // Create UI elements
        healthLabel = new JLabel("Health: " + playerHealth);
        positionLabel = new JLabel("Position: Row: " + row + " Col: " + col);
        roomInfoLabel = new JLabel("Room Info: None");
        
        JPanel controlPanel = new JPanel();
        leftButton = new JButton("Left");
        rightButton = new JButton("Right");
        upButton = new JButton("Up");
        downButton = new JButton("Down");
        
        // Add action listeners for buttons
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(-1, 0);
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(1, 0);
            }
        });
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, -1);
            }
        });
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, 1);
            }
        });
        
        // Layout setup
        controlPanel.setLayout(new GridLayout(1, 4));
        controlPanel.add(leftButton);
        controlPanel.add(rightButton);
        controlPanel.add(upButton);
        controlPanel.add(downButton);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(healthLabel);
        infoPanel.add(positionLabel);
        infoPanel.add(roomInfoLabel);

        add(controlPanel, BorderLayout.SOUTH);
        add(infoPanel, BorderLayout.NORTH);
        
        setVisible(true);
    }

    private void movePlayer(int dx, int dy) {
        // Update player position
        int newRow = row + dy;
        int newCol = col + dx;
        
        // Ensure player stays within bounds
        if (newRow >= 0 && newRow < NUM_ROWS && newCol >= 0 && newCol < NUM_COLUMNS) {
            row = newRow;
            col = newCol;
            handleRoom();
        }
    }

    private void handleRoom() {
        String roomInfo = "";
        switch (dungeon[row][col]) {
            case ROOM_TYPE_EMPTY:
                roomInfo = "This room is empty. You gain some health.";
                playerHealth += 2;
                break;
            case ROOM_TYPE_SHIELD:
                roomInfo = "Monster guarding the shield! Prepare for battle.";
                if (battleMonster()) {
                    isShieldAcquired = true;
                    roomInfo += " You have acquired the shield!";
                }
                break;
            case ROOM_TYPE_SWORD:
                roomInfo = "Monster guarding the sword! Prepare for battle.";
                if (battleMonster()) {
                    isSwordAcquired = true;
                    roomInfo += " You have acquired the sword!";
                }
                break;
            case ROOM_TYPE_DRAGON:
                roomInfo = "The dragon is here! Prepare for the final battle.";
                if (battleMonster()) {
                    roomInfo += " You have defeated the dragon! Victory!";
                    JOptionPane.showMessageDialog(this, "You have defeated the dragon! Victory!");
                    System.exit(0);
                }
                break;
            case ROOM_TYPE_HEALING:
                roomInfo = "You found a healing room! Your health is restored.";
                playerHealth = 10;
                break;
        }
        
        // Update UI with new info
        healthLabel.setText("Health: " + playerHealth);
        positionLabel.setText("Position: Row: " + row + " Col: " + col);
        roomInfoLabel.setText("Room Info: " + roomInfo);
        
        if (playerHealth <= 0) {
            JOptionPane.showMessageDialog(this, "Game Over!");
            System.exit(0);
        }
    }

    private boolean battleMonster() {
        int action = JOptionPane.showOptionDialog(this, "You have encountered a monster! Choose your action:", "Battle", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Fight", "Run"}, "Fight");
        return action == 0; // If "Fight" is selected
    }

    private int[][] generateRandomDungeon() {
        int[][] dungeon = new int[NUM_ROWS][NUM_COLUMNS];
        int[][] locations = getRandomCoordinates();

        // Set rooms with special items
        dungeon[locations[0][0]][locations[0][1]] = ROOM_TYPE_SHIELD; // Shield
        dungeon[locations[1][0]][locations[1][1]] = ROOM_TYPE_SWORD;  // Sword
        dungeon[locations[2][0]][locations[2][1]] = ROOM_TYPE_DRAGON; // Dragon
        dungeon[locations[3][0]][locations[3][1]] = ROOM_TYPE_HEALING; // Healing Room

        return dungeon;
    }

    private int[][] getRandomCoordinates() {
        int[][] coordinates = new int[4][2]; // 4 locations for tools and dragon

        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            coordinates[i][0] = rand.nextInt(NUM_ROWS);
            coordinates[i][1] = rand.nextInt(NUM_COLUMNS);
        }

        return coordinates;
    }

    public static void main(String[] args) {
        new DungeonQuestGUI();
    }
}
