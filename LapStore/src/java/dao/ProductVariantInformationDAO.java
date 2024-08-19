/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ProductVariantInfomation;

/**
 *
 * @author 84834
 */
    public class ProductVariantInformationDAO extends DBContext {
    public ProductVariantInfomation getDetailInformation(int variantId) {
        ProductVariantInfomation productVariant = null;
        String query = "SELECT p.Id, p.[name], p.processor, p.screen_details, p.size, p.operating_system, p.battery_life, p.weight, p.graphic_card, " +
                       "v.ram, v.storage, v.quantity, v.variant_price, v.sale_id, v.status, v.img_Id " +
                       "FROM Product p JOIN ProductVariant v ON p.Id = v.product_id WHERE v.Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, variantId);  // Use parameterized query
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productVariant = new ProductVariantInfomation(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("nrocessor"),
                        rs.getString("screen_details"),
                        rs.getString("size"),
                        rs.getString("operating_system"),
                        rs.getString("battery_life"),
                        rs.getFloat("weight"),
                        rs.getString("graphic_card"),
                        rs.getString("img_Id"),
                        rs.getInt("ram"),
                        rs.getInt("storage"),
                        rs.getInt("quantity"),
                        rs.getFloat("variant_Price"),
                        rs.getInt("sale_Id"),
                        rs.getInt("status"),
                        rs.getFloat("sale")
                );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Better error handling
        }
        return productVariant;
    }
    public static void main(String[] args) {
        ProductVariantInformationDAO d = new ProductVariantInformationDAO();
        ProductVariantInfomation test = d.getDetailInformation(2);
        System.out.println(test);
//        List<Storage> s = d.getAllStorageList();
//        for (Storage storage : s) {
//            System.out.println(storage.getStorageSize());
//        }
        
    }  
}