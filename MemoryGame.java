import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Timer;
import java.util.List;

public class MemoryGame extends JFrame {
    private JButton[] buttons = new JButton[16];
    private String[] values = new String[16];
    private boolean[] matched = new boolean[16];
    private int firstIndex = -1;
    private int secondIndex = -1;
    private Timer timer;

    public MemoryGame() {
        setTitle("Memory Game");
        setSize(600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(20, 20, 30));

        //Game grid
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        gridPanel.setBackground(new Color(20, 20, 30));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initGame(gridPanel);
        add(gridPanel, BorderLayout.CENTER);

        //Back button panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(20, 20, 30));
        JButton backBtn = styleButton("Back to Menu", Color.GRAY);
        backBtn.addActionListener(e -> dispose());
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initGame(JPanel panel) {
        List<String> temp = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            temp.add(String.valueOf(i));
            temp.add(String.valueOf(i));
        }
        Collections.shuffle(temp);
        values = temp.toArray(new String[0]);

        for (int i = 0; i < 16; i++) {
            int idx = i;
            buttons[i] = styleButton("", new Color(70, 130, 180));
            buttons[i].setFont(new Font("Arial", Font.BOLD, 22));
            buttons[i].addActionListener(e -> handleClick(idx));
            panel.add(buttons[i]);
        }
    }

    private void handleClick(int index) {
        if (matched[index] || !buttons[index].getText().equals("")) return;

        buttons[index].setText(values[index]);

        if (firstIndex == -1) {
            firstIndex = index;
        } else if (secondIndex == -1 && index != firstIndex) {
            secondIndex = index;
            timer = new Timer(800, e -> checkMatch());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkMatch() {
        if (values[firstIndex].equals(values[secondIndex])) {
            matched[firstIndex] = true;
            matched[secondIndex] = true;
        } else {
            buttons[firstIndex].setText("");
            buttons[secondIndex].setText("");
        }

        firstIndex = -1;
        secondIndex = -1;

        if (allMatched()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You matched all pairs!");
            dispose();
        }
    }

    private boolean allMatched() {
        for (boolean b : matched) {
            if (!b) return false;
        }
        return true;
    }

    private JButton styleButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        return button;
    }
}
