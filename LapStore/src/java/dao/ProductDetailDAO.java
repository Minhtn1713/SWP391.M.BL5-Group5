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
import model.Ram;
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
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4), 
                    rs.getFloat(5),       
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getFloat(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getInt(14));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public List<ProductImage> getProductImage(int id, String ramId){
        List<ProductImage> pImage = new ArrayList<>();
        String query = "Select * from ProductImage "
                + " Where productImage.product_id = ? ";
        if (!ramId.equals("")){
                query+= " AND ram_id =  " + ramId;
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
    
    public List<Ram> getRamListById(int id){
        List<Ram> RamList = new ArrayList<>();
        String query = "Select distinct r.id, r.ram, r.price_bonus, r.status from ProductImage p join "
                + " Ram r on p.Ram_Id = r.Id"
                + " Where p.Product_Id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RamList.add(new Ram(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(5)));
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
                + "Where ram = ?";
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
                + " Where Id = ?";
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
    
    public Ram getRambyId(String id){
        String query = "SELECT * FROM Ram"
                + " Where Id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
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
    
    
    public static void main(String[] args) {
        ProductDetailDAO d = new ProductDetailDAO();
        Product p = d.getProductbyName("iphone 14 pro");
        String rid = d.getRamIdByName("purple") + "";
        List<Storage> s = d.getAllStorageList();
        for (Storage storage : s) {
            System.out.println(storage.getStorageSize());
        }
        
}
    
}
