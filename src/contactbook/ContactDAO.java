package contactbook;

import java.sql.*;
import java.util.*;

public class ContactDAO {
    private Connection conn = DBConnection.getConnection();

    public void addContact(Contact c) throws SQLException {
        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getName());
        ps.setString(2, c.getPhone());
        ps.setString(3, c.getEmail());
        ps.executeUpdate();
    }

    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> list = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM contacts");
        while (rs.next()) {
            list.add(new Contact(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email")
            ));
        }
        return list;
    }

    public void deleteContact(String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM contacts WHERE name = ?");
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public void updateContact(String name, String newPhone, String newEmail) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE contacts SET phone = ?, email = ? WHERE name = ?");
        ps.setString(1, newPhone);
        ps.setString(2, newEmail);
        ps.setString(3, name);
        ps.executeUpdate();
    }
}