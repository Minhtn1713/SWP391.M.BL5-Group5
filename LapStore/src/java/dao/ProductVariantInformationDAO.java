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
        ProductVariantInfomation list = null;
        String query = "SELECT p.id, p.name, p.processor, p.screen_details, p.operating_system, p.battery_life, p.graphic_card, p.img, v.RAM_id, v.Storage_id, v.quantity, v.variant_price,  v.status, v.sale_id \n" +
                       "FROM Product p JOIN ProductVariant v ON p.id = v.product_id WHERE v.id = " + variantId;; 
       try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list = new ProductVariantInfomation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getInt(11), rs.getFloat(12), rs.getInt(13), rs.getFloat(14));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
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