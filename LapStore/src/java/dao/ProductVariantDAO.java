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
import model.ProductVariant;

/**
 *
 * @author lords
 */
public class ProductVariantDAO extends DBContext{
        public int getTotalProductVariant() {
        String query = "select count(*) from ProductVariant";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
       public ProductVariant getProductVariantByID(int id){
        ProductVariant product = new ProductVariant();
        String sql = "SELECT * FROM [ProductVariant] WHERE id LIKE '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    public List<Integer> getListIdProductByName(String search){
        List<Integer> list = new ArrayList<>();
        String query = "Select id from Product "
                + "where name like '%"+ search+ "%'";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
