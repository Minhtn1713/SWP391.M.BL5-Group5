package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductImage;

public class ProductDetailDAO extends DBContext {

    public Product getProductbyId(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                        rs.getInt("status"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        System.out.println("Product with ID " + id + " not found.");
        return null;
    }
    public List<ProductImage> getProductImage(int id){
        List<ProductImage> pImage = new ArrayList<>();
        String query = "Select * from ProductImage Where productImage.product_id = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pImage.add(new ProductImage(rs.getInt(1), rs.getString(2), 
                        rs.getInt(3)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return pImage;
    }
}
