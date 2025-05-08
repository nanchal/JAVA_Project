import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissors extends JFrame {

    private int playerScore = 0;
    private int computerScore = 0;
    private int roundsPlayed = 0;
    private final int maxRounds = 5;

    private JLabel resultLabel, scoreLabel;

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(20, 20, 30)); 

        JLabel title = new JLabel("ROCK PAPER SCISSORS", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        title.setForeground(Color.CYAN);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        //Game buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(20, 20, 30));

        JButton rockBtn = styleButton("Rock", new Color(255, 99, 71));
        JButton paperBtn = styleButton("Paper", new Color(100, 149, 237));
        JButton scissorsBtn = styleButton("Scissors", new Color(144, 238, 144));

        buttonPanel.add(rockBtn);
        buttonPanel.add(paperBtn);
        buttonPanel.add(scissorsBtn);

        //Game status
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.setBackground(new Color(20, 20, 30));

        resultLabel = new JLabel("Make your move!", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));

        scoreLabel = new JLabel("Score - You: 0 | Computer: 0", JLabel.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        statusPanel.add(resultLabel);
        statusPanel.add(scoreLabel);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBackground(new Color(20, 20, 30));
        centerPanel.add(buttonPanel);
        centerPanel.add(statusPanel);

        add(centerPanel, BorderLayout.CENTER);

        //Back button at bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(20, 20, 30));
        JButton closeBtn = styleButton("Back to Menu", Color.GRAY);
        closeBtn.addActionListener(e -> dispose());
        bottomPanel.add(closeBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        //Button listeners
        rockBtn.addActionListener(e -> playRound("Rock"));
        paperBtn.addActionListener(e -> playRound("Paper"));
        scissorsBtn.addActionListener(e -> playRound("Scissors"));

        setVisible(true);
    }

    private JButton styleButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        button.setFocusPainted(false);
        return button;
    }

    private void playRound(String playerMove) {
        if (roundsPlayed >= maxRounds) {
            JOptionPane.showMessageDialog(this,
                    "GAME OVER!\nFinal Score:\nYou: " + playerScore + "\nComputer: " + computerScore);
            dispose();
            return;
        }

        String[] options = {"Rock", "Paper", "Scissors"};
        String computerMove = options[new Random().nextInt(3)];

        String result;
        if (playerMove.equals(computerMove)) {
            result = "It's a Tie!";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            result = "You Win!";
            playerScore++;
        } else {
            result = "Computer Wins!";
            computerScore++;
        }

        roundsPlayed++;

        resultLabel.setText("You: " + playerMove + " | Computer: " + computerMove + " = " + result);
        scoreLabel.setText("Score - You: " + playerScore + " | Computer: " + computerScore);

        if (roundsPlayed == maxRounds) {
            JOptionPane.showMessageDialog(this,
                    "GAME OVER!\nFinal Score:\nYou: " + playerScore + "\nComputer: " + computerScore);
            dispose();
        }
    }

    public static void main(String[] args) {
        new RockPaperScissors();
    }
}
