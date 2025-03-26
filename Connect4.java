import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connect4GUI extends JFrame {
    private char[][] grid = new char[6][7]; // Game grid: 6 rows, 7 columns
    private JButton[] columnButtons = new JButton[7]; // Buttons for dropping disks
    private JLabel redScoreLabel, yellowScoreLabel; // Score display labels
    private int redScore = 0, yellowScore = 0; // Player scores
    private boolean redTurn = true; // Tracks whose turn it is (true = red, false = yellow)
    private JPanel gamePanel; // Panel for drawing the game board

    public Connect4GUI() {
        // Initialize empty grid
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = ' ';
            }
        }

        // Set up window properties
        setTitle("Connect 4"); // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setLayout(new BorderLayout()); // Layout manager

        // Create and set up score panel
        JPanel scorePanel = new JPanel();
        redScoreLabel = new JLabel("Red: 0");
        redScoreLabel.setForeground(Color.RED); // Red text for red player
        yellowScoreLabel = new JLabel("Yellow: 0");
        yellowScoreLabel.setForeground(Color.YELLOW); // Yellow text for yellow player
        scorePanel.add(redScoreLabel);
        scorePanel.add(yellowScoreLabel);
        add(scorePanel, BorderLayout.NORTH); // Add to top of window

        // Create game panel with custom drawing
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g); // Draw the game board
            }
        };
        gamePanel.setPreferredSize(new Dimension(700, 600)); // Set board size
        add(gamePanel, BorderLayout.CENTER); // Add to center of window

        // Create button panel for dropping disks and quitting
        JPanel buttonPanel = new JPanel();
        for (int i = 0; i < 7; i++) {
            columnButtons[i] = new JButton("Drop " + (i + 1)); // Column buttons
            final int col = i;
            columnButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dropDisk(col); // Handle disk drop
                }
            });
            buttonPanel.add(columnButtons[i]);
        }
        
        // Create and add quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit application
            }
        });
        buttonPanel.add(quitButton);
        
        add(buttonPanel, BorderLayout.SOUTH); // Add buttons to bottom

        pack(); // Size window to fit components
        setLocationRelativeTo(null); // Center window
        setVisible(true); // Show window
    }

    // Draw the game board and pieces
    private void drawGrid(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 700, 600); // Draw blue background
        
        // Draw grid circles
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                g.setColor(Color.WHITE);
                g.fillOval(col * 100, row * 100, 90, 90); // Empty slot
                
                // Draw player pieces
                if (grid[row][col] == 'R') {
                    g.setColor(Color.RED);
                    g.fillOval(col * 100 + 10, row * 100 + 10, 70, 70); // Red piece
                } else if (grid[row][col] == 'Y') {
                    g.setColor(Color.YELLOW);
                    g.fillOval(col * 100 + 10, row * 100 + 10, 70, 70); // Yellow piece
                }
            }
        }
    }

    // Handle disk dropping logic
    private void dropDisk(int column) {
        if (addChip(column, redTurn ? 'R' : 'Y')) { // Add disk for current player
            gamePanel.repaint(); // Redraw board
            if (checkWin()) { // Check for winner
                updateScore(); // Update winner's score
                resetGame(); // Start new game
            } else {
                redTurn = !redTurn; // Switch turns
                updateButtonColors(); // Update button colors
            }
        }
    }

    // Add a disk to the specified column
    private boolean addChip(int column, char disk) {
        if (grid[0][column] != ' ') { // Check if column is full
            JOptionPane.showMessageDialog(this, "Column is full!");
            return false;
        }

        // Find lowest empty spot in column
        for (int row = 5; row >= 0; row--) {
            if (grid[row][column] == ' ') {
                grid[row][column] = disk;
                return true;
            }
        }
        return false;
    }

    // Check for four in a row in any direction
    private boolean checkWin() {
        // Check horizontal
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row][col] != ' ' && 
                    grid[row][col] == grid[row][col + 1] &&
                    grid[row][col] == grid[row][col + 2] &&
                    grid[row][col] == grid[row][col + 3]) {
                    announceWinner(grid[row][col]);
                    return true;
                }
            }
        }

        // Check vertical
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 3; row++) {
                if (grid[row][col] != ' ' &&
                    grid[row][col] == grid[row + 1][col] &&
                    grid[row][col] == grid[row + 2][col] &&
                    grid[row][col] == grid[row + 3][col]) {
                    announceWinner(grid[row][col]);
                    return true;
                }
            }
        }

        // Check ascending diagonal
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row][col] != ' ' &&
                    grid[row][col] == grid[row + 1][col + 1] &&
                    grid[row][col] == grid[row + 2][col + 2] &&
                    grid[row][col] == grid[row + 3][col + 3]) {
                    announceWinner(grid[row][col]);
                    return true;
                }
            }
        }

        // Check descending diagonal
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (grid[row][col] != ' ' &&
                    grid[row][col] == grid[row + 1][col - 1] &&
                    grid[row][col] == grid[row + 2][col - 2] &&
                    grid[row][col] == grid[row + 3][col - 3]) {
                    announceWinner(grid[row][col]);
                    return true;
                }
            }
        }

        return false; // No winner found
    }

    // Show winner announcement
    private void announceWinner(char winner) {
        String message = winner == 'R' ? "Red Player Wins!" : "Yellow Player Wins!";
        JOptionPane.showMessageDialog(this, message);
    }

    // Update score display
    private void updateScore() {
        if (redTurn) {
            redScore++;
            redScoreLabel.setText("Red: " + redScore);
        } else {
            yellowScore++;
            yellowScoreLabel.setText("Yellow: " + yellowScore);
        }
    }

    // Reset game state for new game
    private void resetGame() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = ' '; // Clear grid
            }
        }
        redTurn = true; // Red starts
        updateButtonColors();
        gamePanel.repaint(); // Redraw empty board
    }

    // Update button colors based on current turn
    private void updateButtonColors() {
        for (JButton button : columnButtons) {
            button.setBackground(redTurn ? Color.RED : Color.YELLOW);
        }
    }

    // Main method to launch application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Connect4GUI(); // Create and show game window
            }
        });
    }
}
