import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameHub extends JFrame {

    public GameHub() {
        setTitle("Game Hub");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //full application closed
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();   //panel
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(50, 50, 70));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        //Buttons
        JButton rockPaperScissorsButton = new JButton("Rock Paper Scissors");  //buttons
        JButton memoryGameButton = new JButton("Memory Game");
        JButton snakeGameButton = new JButton("Snake Game");
        JButton exitButton = new JButton("Exit");

        //Styling buttons
        JButton[] buttons = {rockPaperScissorsButton, memoryGameButton, snakeGameButton, exitButton};
        for (JButton btn : buttons) {
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(btn);
        }

        add(panel);   //added everything

        //ActionListeners
        rockPaperScissorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RockPaperScissorsGame();
            }
        });

        memoryGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MemoryGame();
            }
        });


        snakeGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SnakeGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameHub());
    }
}
