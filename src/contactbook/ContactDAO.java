package contactbook;

import java.sql.*;
import java.util.*;

public class ContactDAO {
    public void addContact(Contact c) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, c.getName());
        ps.setString(2, c.getPhone());
        ps.setString(3, c.getEmail());
        ps.executeUpdate();
        con.close();
    }

    public List<Contact> getAllContacts() throws Exception {
        List<Contact> list = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM contacts");
        while (rs.next()) {
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            list.add(new Contact(name, phone, email));
        }
        con.close();
        return list;
    }
}
