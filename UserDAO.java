package contactbook;

import java.sql.*;

public class UserDAO {
    public User authenticate(String email, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT role FROM users WHERE email=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String role = rs.getString("role");
            con.close();
            return new User(email, role);
        }
        con.close();
        return null;
    }
}