/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.Product;
import model.ProductImage;
import model.Storage;

/**
 *
 * @author kienk
 */
public class ProductDetailDAO extends DBContext {
    
    public Product getProductbyName(String productName){
        String query = "SELECT * FROM product"
                + " Where product.name = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11), rs.getInt(12));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public List<ProductImage> getProductImage(int id, String colorId){
        List<ProductImage> pImage = new ArrayList<>();
        String query = "Select * from ProductImage "
                + " Where productImage.product_id = ? ";
        if (!colorId.equals("")){
                query+= " AND color_id =  " + colorId;
            }
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pImage.add(new ProductImage(rs.getInt(1), rs.getString(2), 
                        rs.getInt(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return pImage;
    }

    
    
    public static void main(String[] args) {
        ProductDetailDAO d = new ProductDetailDAO();
        Product p = d.getProductbyName("iphone 14 pro");
//        List<Storage> s = d.getAllStorageList();
//        for (Storage storage : s) {
//            System.out.println(storage.getStorageSize());
//        }
        
    }
    
}
