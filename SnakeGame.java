import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        setTitle("Snake Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int BOX_SIZE = 25;
    private final int WIDTH = 600, HEIGHT = 600;
    private final int TOTAL_BOXES = (WIDTH * HEIGHT) / (BOX_SIZE * BOX_SIZE);

    private LinkedList<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        startGame();
    }

    private void startGame() {
        snake = new LinkedList<>();
        snake.add(new Point(5, 5));
        generateFood();
        direction = 'R';
        running = true;
        timer = new Timer(100, this);
        timer.start();
    }

    private void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / BOX_SIZE);
        int y = rand.nextInt(HEIGHT / BOX_SIZE);
        food = new Point(x, y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            // Draw food
            g.setColor(Color.RED);
            g.fillOval(food.x * BOX_SIZE, food.y * BOX_SIZE, BOX_SIZE, BOX_SIZE);

            // Draw snake
            for (int i = 0; i < snake.size(); i++) {
                g.setColor(i == 0 ? Color.GREEN : Color.WHITE);
                Point p = snake.get(i);  //point=location to 2d
                g.fillRect(p.x * BOX_SIZE, p.y * BOX_SIZE, BOX_SIZE, BOX_SIZE);
            }
        } else {
            showGameOver(g);
        }
    }

    private void showGameOver(Graphics g) {
        String msg = "Game Over";
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(msg, (WIDTH - metrics.stringWidth(msg)) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
            checkFood();
        }
        repaint();
    }

    private void move() {
        Point head = new Point(snake.getFirst());
        switch (direction) {
            case 'U': head.y--; break;
            case 'D': head.y++; break;
            case 'L': head.x--; break;
            case 'R': head.x++; break;
        }
        snake.addFirst(head);
        snake.removeLast();
    }

    private void checkFood() {
        if (snake.getFirst().equals(food)) {
            snake.addLast(new Point(snake.getLast()));
            generateFood();
        }
    }

    private void checkCollision() {
        Point head = snake.getFirst();
        // Wall collision
        if (head.x < 0 || head.x >= WIDTH / BOX_SIZE || head.y < 0 || head.y >= HEIGHT / BOX_SIZE) {
            running = false;
        }
        // Self collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
            }
        }
        if (!running) timer.stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') direction = 'R';
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') direction = 'U';
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') direction = 'D';
                break;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
