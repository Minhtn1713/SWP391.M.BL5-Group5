///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dao;
//
//import dao.DBContext;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import model.Product;
///**
// *
// * @author kienk
// */
//public class ProductDetailDAO extends DBContext {
//    
//    public Product getProductbyName(String productName){
//        String query = "SELECT * FROM product"
//                + " Where product.name = ?";
//        try{
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, productName);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                return new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3),
//                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
//                rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11), rs.getInt(12));
//            }
//            ps.close();
//            rs.close();
//        }catch(SQLException e){
//            System.out.println(e);
//        }
//        return null;
//    }
//
//    
//    
//    public static void main(String[] args) {
//        ProductDetailDAO d = new ProductDetailDAO();
//        Product p = d.getProductbyName("");
//        
//    }
//    
//}
