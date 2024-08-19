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
import model.Product;
import model.ProductImage;
import model.RAM;
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
                return new Product(
                    rs.getInt("id"),
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
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public List<ProductImage> getProductImage(int id, String RamId){
        List<ProductImage> pImage = new ArrayList<>();
        String query = "Select * from ProductImage "
                + " Where productImage.product_id = ? ";
        if (!RamId.equals("")){
                query+= " AND Ram_id =  " + RamId;
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
    
    public List<RAM> getRamListById(int id){
        List<RAM> RamList = new ArrayList<>();
        String query = "Select distinct r.id, r.Ram, r.price_bonus, c.status from ProductImage p join "
                + " Ram r on p.Ram_Id = r.Id"
                + " Where p.Product_id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RamList.add(new RAM(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return RamList;
    }
    
    public List<Storage> getStorageListById(int id){
        List<Storage> storageList = new ArrayList<>();
        String query = "Select distinct c.id, c.storage_size, c.price_bonus, c.status from ProductVariant p join "
                + " Storage c on p.Storage_Id = c.Id"
                + " Where p.Product_id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                storageList.add(new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return storageList;
    }
    
    public int getRamIdByName(String name){
        int id;
        String query = "Select id from Ram "
                + "Where Ram = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return id =rs.getInt(1);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return 0;
    }
    
    public List<Storage> getAllStorageList(){
        List<Storage> list = new ArrayList<>();
        String query = "Select * from Storage";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public Storage getStoragebyId(String id){
        String query = "SELECT * FROM storage"
                + " Where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public RAM getRambyId(String id){
        String query = "SELECT * FROM Ram"
                + " Where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new RAM(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(5));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        ProductDetailDAO d = new ProductDetailDAO();
        Product p = d.getProductbyName("iphone 14 pro");
        String rid = d.getRamIdByName("purple") + "";
//        List<Storage> s = d.getAllStorageList();
//        for (Storage storage : s) {
//            System.out.println(storage.getStorageSize());
//        }
        
    }
    
}
