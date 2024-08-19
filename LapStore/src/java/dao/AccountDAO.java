/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.SecurityQuestion;

/**
 *
 * @author lords
 */
public class AccountDAO extends DBContext{
    
    public int getAccountID(String userName){
        int id = 0;
        String sql = "SELECT id FROM Account WHERE Account.Username LIKE ?";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1,userName);
            System.out.println(userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return id;
    }
    public ArrayList<SecurityQuestion> getSecurityQuestion(){
        ArrayList<SecurityQuestion> list = new ArrayList<>();
        String sql = "SELECT * FROM SecurityQuestion";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SecurityQuestion sq = new SecurityQuestion(rs.getInt(1),rs.getNString(2));
                list.add(sq);
            }
            ps.close();
            rs.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public int createAccount(String userName, String passWord){
        int succes = 0;
        String sql = "Insert into [Account]([username], [password], [role_Id]) values ('" + userName + "', '" + passWord + "', 2)";
         try {
            PreparedStatement ps = connection.prepareStatement(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return succes;
}
     public int updatePassword(int id_acc, String new_passWord){
        int succes = 0;
        String sql = "UPDATE [Account] SET [password] = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, new_passWord);
            ps.setInt(2, id_acc);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }
     public Account getAccountById(int id) {
        String query = "select * from [LapStore].[dbo].[Account] "
                + " Where [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
         public int getTotalCustomer(){
         String sql = "select count(*) from [Account] where role_id = 2";
         int total = 0;
          try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }
}
