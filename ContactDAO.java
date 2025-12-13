package contactbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    // Add a new contact
    public void addContact(Contact c) throws Exception {
        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.executeUpdate();
        }
    }

    // Get all contacts
    public List<Contact> getAllContacts() throws Exception {
        List<Contact> list = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                list.add(new Contact(name, phone, email));
            }
        }
        return list;
    }

    // Delete contact by phone
    public boolean deleteContact(String phone) throws Exception {
        String sql = "DELETE FROM contacts WHERE phone=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);
            int rows = ps.executeUpdate();
            return rows > 0; // true if deleted, false if not found
        }
    }
}