import javax.swing.*;

public class RockPaperScissorsGame extends JFrame {
    public RockPaperScissorsGame() {
        setTitle("Rock Paper Scissors");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Welcome to Rock Paper Scissors!", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
