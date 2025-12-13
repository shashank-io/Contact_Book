package contactbook;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {
    private final UserDAO userDAO = new UserDAO(); // instance of UserDAO

    public LoginUI() {
        setTitle("Login");
        setSize(320, 160);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI components
        JPanel panel = new JPanel(new GridLayout(3, 2, 6, 6));
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        // Layout
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginBtn);

        add(panel);

        // Action listener for login
        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email and password required!");
                return;
            }

            try {
                User user = userDAO.authenticate(email, password); // call DAO
                if (user != null) {
                    JOptionPane.showMessageDialog(this,
                            "Login successful as " + user.getRole());
                    dispose(); // close login window
                    new ContactBookUI(user); // open main UI with role
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error during login.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}