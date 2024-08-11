/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author lords
 */
public class UserDAO extends DBContext{
   
    public User getUserById(int id){
        String query = "Select * from [LapStore].[dbo].[User]"
                + " Where [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getBoolean(6));
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
  
}
