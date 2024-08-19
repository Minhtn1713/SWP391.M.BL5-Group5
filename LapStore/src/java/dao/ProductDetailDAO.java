/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Product;

/**
 *
 * @author kienk
 */
public class ProductDetailDAO extends DBContext {

    public Product getProductbyId(int id) {
        String query = "SELECT * FROM product"
                + " Where product.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("img"),
                        rs.getInt("brand"),
                        rs.getFloat("price"),
                        rs.getString("processor"),
                        rs.getString("graphic_card"),
                        rs.getString("screen_details"),
                        rs.getString("size"),
                        rs.getFloat("weight"),
                        rs.getString("operating_system"),
                        rs.getString("battery_life"),
                        rs.getString("description"),
                        rs.getString("brand_name"),
                        rs.getInt("status"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
