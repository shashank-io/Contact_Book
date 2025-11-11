package contactbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ContactBookUI extends JFrame {
    ContactDAO dao = new ContactDAO();

    public ContactBookUI() {
        setTitle("Contact Book");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JButton addBtn = new JButton("Add Contact");
        JButton viewBtn = new JButton("View All");

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addBtn, gbc);

        gbc.gridy = 4;
        panel.add(viewBtn, gbc);

        add(panel);

        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (!name.matches("[A-Za-z ]{2,50}")) {
                JOptionPane.showMessageDialog(this, "Invalid name. Use letters only.");
                return;
            }
            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Invalid phone number. Use exactly 10 digits.");
                return;
            }
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
                JOptionPane.showMessageDialog(this, "Invalid email format.");
                return;
            }

            try {
                Contact c = new Contact(name, phone, email);
                dao.addContact(c);
                JOptionPane.showMessageDialog(this, "Contact added!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding contact.");
            }
        });

        viewBtn.addActionListener(e -> {
            try {
                List<Contact> list = dao.getAllContacts();
                StringBuilder sb = new StringBuilder();
                for (Contact c : list) {
                    sb.append(c.getName()).append(" - ")
                            .append(c.getPhone()).append(" - ")
                            .append(c.getEmail()).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error retrieving contacts.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ContactBookUI();
    }
}
