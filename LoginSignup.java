import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginSignup extends JFrame {

    JTextField nameField;
    JPasswordField passField;
    JButton loginBtn, signupBtn;

    Connection conn;

    public LoginSignup() {
        setTitle("GAMEHUB");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(20, 20, 20));
        setLayout(null);

        JLabel heading = new JLabel("WELCOME TO GAMEHUB", JLabel.CENTER);
        heading.setFont(new Font("Cambria", Font.BOLD, 28));
        heading.setForeground(Color.CYAN);
        heading.setBounds(50, 30, 400, 40);
        add(heading);

        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setForeground(Color.GREEN);
        nameLabel.setBounds(100, 100, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 100, 180, 30);
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        add(nameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.GREEN);
        passLabel.setBounds(100, 150, 100, 30);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(200, 150, 180, 30);
        passField.setBackground(Color.BLACK);
        passField.setForeground(Color.WHITE);
        add(passField);

        loginBtn = new JButton("🎮 Login");
        loginBtn.setBounds(120, 220, 100, 30);
        loginBtn.setBackground(Color.DARK_GRAY);
        loginBtn.setForeground(Color.YELLOW);
        add(loginBtn);

        signupBtn = new JButton("🚀 Sign Up");
        signupBtn.setBounds(250, 220, 100, 30);
        signupBtn.setBackground(Color.DARK_GRAY);
        signupBtn.setForeground(Color.YELLOW);
        add(signupBtn);

        connectDatabase();

        loginBtn.addActionListener(e -> handleLogin());
        signupBtn.addActionListener(e -> handleSignup());

        setVisible(true);
    }

    private void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamehub", "root", "anchal@an@123");
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))";
            stmt.execute(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void handleLogin() {
        String username = nameField.getText();
        String password = new String(passField.getPassword());
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new GameMenu(username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void handleSignup() {
        String username = nameField.getText();
        String password = new String(passField.getPassword());
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sign-up Successful! You can now log in.");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "USER already exists!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginSignup();
    }
}
