package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;

public class UserDAO extends DBContext {

   

   

    public int createUser(int id, String phone, String name){
        int succes = 0;
        String sql = "insert into [LapStore].[dbo].[User](id,fullname,phone) values (?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);            
            ps.setString(3, phone);
            System.out.println(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }
  

   

   
    public boolean add(int id,String email) {
        String query = """
                       insert into [User] (id,email) values (?,?); 
                       """;
        int rows = 0;
        Connection con = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            con = connection;
            connection.setAutoCommit(false);
            ps.setInt(1, id);
            ps.setString(2, email);
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
    public boolean add(String username,String password,int role,int status) {
        String query = """
                       insert into [Account] (username,password,role_Id,isActive) values (?,?,?,?); 
                       """;
        int rows = 0;
        Connection con = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            con = connection;
            connection.setAutoCommit(false);
            
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, role);
            ps.setInt(4, status);
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
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from [User] join Account on [User].id=Account.id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getInt(6), rs.getString(9), rs.getInt(11), rs.getInt(12),rs.getDouble(7)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
 
    public void updateUser(int id,String name, String phone, String address, String gender){
        String query = "UPDATE [LapStore].[dbo].[User] " +
                        " SET fullName = ?, phone = ?, address = ?";
                        
        if (!gender.equals("#")){
            query+= ", gender = ? ";
        }
        query+=" WHERE id = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            ps.setString(index++, name);
            ps.setString(index++, phone);
            ps.setString(index++, address);
            if (!gender.equals("#")){
                ps.setString(index++, gender);
            }
            ps.setInt(index++, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    
    
    public User getUserById(int id){
        String query = "Select * from [LapStore].[dbo].[User]"
                + " Where [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getInt(6));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public User getUserById_Dung(int id){
        String query = "Select * from [LapStore].[dbo].[User]"
                + " Where [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(4),rs.getString(3), rs.getString(5), rs.getInt(6));
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
        int status = getUserById_1(id).getIsActive(); // Get current status

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
                       update [User] set balance=? where id=?;
                       update [Account] set role_Id=?,isActive=? where id=?;""";
        int check = 0;
        Connection con = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            ps.setDouble(index++, u.getBalance());
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
    public User getUserById_1(int id) {
        String query = "select * from [User] join Account on [User].id=Account.id where [User].id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getInt(6), rs.getString(9), rs.getInt(11), rs.getInt(12));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public User getUserById_2(int id) {
        String query = "select * from [User] join Account on [User].id=Account.id where [User].id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getInt(6), rs.getString(9), rs.getInt(11), rs.getInt(12),rs.getDouble(7));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
     
    public void updateUser(int id,String name, String phone,String email, String address, String gender){
        String query = "UPDATE [LapStore].[dbo].[User] " +
                        " SET fullName = ?, phone = ?,email= ?, address = ?";
                        
        if (!gender.equals("#")){
            query+= ", gender = ? ";
        }
        query+=" WHERE id = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            ps.setString(index++, name);
            ps.setString(index++, phone);
             ps.setString(index++, email);
            ps.setString(index++, address);
            if (!gender.equals("#")){
                ps.setString(index++, gender);
            }
            ps.setInt(index++, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    public HashMap<Integer, String> getUserName(){
        HashMap<Integer, String> map = new HashMap<>();
        String query = "Select * from [LapStore].[dbo].[User]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }
    
    public void insertPayment(int id, long balance) {
        String sql = "UPDATE [User] SET balance = ? WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setLong(1, balance);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean updateAccountBalance(User user) {
        String sql = "UPDATE [User] SET balance = ? WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setDouble(1, user.getBalance());
            stm.setInt(2, user.getId());
            int affectedRows = stm.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating account balance: " + e.getMessage());
            return false;
        }
    }
}
