import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMenu extends JFrame {

    String currentUser;

    public GameMenu(String username) {
        this.currentUser = username;

        setTitle("GAMEHUB");
        setSize(550, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Background panel
        JPanel backgroundPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        backgroundPanel.setBackground(new Color(30, 30, 60));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel welcome = new JLabel("Welcome, " + username + "!", JLabel.CENTER);
        welcome.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        welcome.setForeground(new Color(70, 130, 180));
        backgroundPanel.add(welcome);

        JButton rockPaper = createArcadeButton("Rock Paper Scissors");
        JButton memoryGame = createArcadeButton("Memory Game");
        JButton snakeGame = createArcadeButton("Snake Game");
        JButton logout = createArcadeButton("Logout");

        backgroundPanel.add(rockPaper);
        backgroundPanel.add(memoryGame);
        backgroundPanel.add(snakeGame);
        backgroundPanel.add(logout);

        add(backgroundPanel, BorderLayout.CENTER);

        //Event handlers
        rockPaper.addActionListener(e -> new RockPaperScissors());
        memoryGame.addActionListener(e -> new MemoryGame());
        snakeGame.addActionListener(e -> {
            dispose();
            new SnakeGame();
        });

        logout.addActionListener(e -> {   //logout ka action listner
            int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );
            if (option == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Logged out.");
                dispose();
                new LoginSignup();
            }
        });

        setLocationRelativeTo(null); //Center the window
        setVisible(true);
    }

    private JButton createArcadeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(70, 130, 180));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {  //mouse entered a component
                btn.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(MouseEvent e) {   //mouse exits
                btn.setBackground(new Color(70, 130, 180));
            }
        });

        return btn;
    }
}
