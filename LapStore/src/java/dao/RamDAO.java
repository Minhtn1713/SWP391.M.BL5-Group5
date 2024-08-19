/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Ram;

/**
 *
 * @author 84834
 */
public class RamDAO extends DBContext {
    public Map<Integer, String> getHashMapRam(){
        Map<Integer, String> hashMap = new HashMap<>();
        String query = "select Id, RAM from [Ram]";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                hashMap.put(rs.getInt(1), rs.getString(2));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return hashMap;
    }
    
    public List<Ram> getAllRam(){
        List<Ram> list = new ArrayList<>();
         String query = "select * from [Ram]";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Ram(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
      public Ram getRambyId(int id){
        String query = "SELECT * FROM Ram"
                + " Where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Ram(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
     public int updateRam(String id, String status){
        int succes = 0;
        String query = "UPDATE [Ram] SET " +
                                       "status = " + status +
                                       " where id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }  
     
     public int updateRam(String id, String ram, float priceBonus){
        int succes = 0;
        String query = "UPDATE [Ram] SET " +
                                       " RAM= '" + ram +
                                        "', price_bonus = " + priceBonus +
                                       "' where id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    } 
     
     public int createRam(Ram ram) {
        int success = 0;
        String query = "Insert Into [Ram]([RAM],[price_bonus], [status])"
                + " values ('" + ram.getName() + "', " + ram.getPriceBonus() + ", 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }
     
     public static void main(String[] args) {
        RamDAO r = new RamDAO();
        r.updateRam("13", "Yellow", 100);
    }
}