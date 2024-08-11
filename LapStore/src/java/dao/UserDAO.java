package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class UserDAO extends DBContext {

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from [User] join Account on [User].id=Account.id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(8), rs.getInt(10), rs.getInt(11)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public User getUserById(int id) {
        String query = "select * from [User] join Account on [User].id=Account.id where [User].id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(8), rs.getInt(10), rs.getInt(11));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean toggleById(int id) {
        int check = 0;
        Connection con = null;
        int status = getUserById(id).getIsActive(); // Get current status

        String sql = "UPDATE Account SET isActive = ? WHERE id = ?"; // Update query

        try {
            // Use the connection field from DBContext
            con = (Connection) this.connection;
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Toggle the status
                ps.setInt(1, status == 1 ? 0 : 1);
                ps.setInt(2, id);

                check = ps.executeUpdate(); // Execute the update
                con.commit(); // Commit transaction
            }

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            e.printStackTrace(System.err);
        } finally {
            // No need to close the connection here as it's managed by DBContext
        }

        return check > 0; // Return true if the update was successful
    }

    public User updateUser(User u) {
        String query = """
                       update [User] set fullname=?,phone=?,address=?,gender=? where id=?;
                       update [Account] set role_Id=?,isActive=? where id=?;""";
        int check = 0;
        Connection con = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            ps.setString(index++, u.getFullName());
            ps.setString(index++, u.getPhone());
            ps.setString(index++, u.getAddress());
            ps.setInt(index++, u.getGender());
            ps.setInt(index++, u.getId());
            ps.setInt(index++, u.getRole());
            ps.setInt(index++, u.getIsActive());
            ps.setInt(index++, u.getId());
            check = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            e.printStackTrace(System.err);
        }
        return check > 0 ? u : null;
    }
    public boolean add(String email) {
        String query = """
                       insert into [User] (email) values (?); ;
                       """;
        int rows = 0;
        Connection con = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            con = connection;
            connection.setAutoCommit(false);
            ps.setString(1, email);
            rows = ps.executeUpdate();
            if (rows > 0) {
                connection.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            e.printStackTrace(System.err);
        }
        return rows > 0;
    }
    public boolean add(int id,String username,String password,int role,int status) {
        String query = """
                       insert into [Account] (id,username,password,role_Id,isActive) values (?,?,?,?,?); 
                       """;
        int rows = 0;
        Connection con = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            con = connection;
            connection.setAutoCommit(false);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setInt(4, role);
            ps.setInt(5, status);
            rows = ps.executeUpdate();
            if (rows > 0) {
                connection.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            e.printStackTrace(System.err);
        }
        return rows > 0;
    }
    public boolean usernameExists(String username) {
    String query = "SELECT COUNT(*) FROM [Account] WHERE username = ?";
    Connection con = null;
    boolean exists = false;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        con = connection;
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                exists = (count > 0);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(System.err);
    }
    return exists;
}
    
    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();

        // Call the getAllUser method using the instance
        List<User> list = userDao.getAllUser();

        // Print the list of users
        for (User user : list) {
            System.out.println(user);
        }
    }
}
