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
import model.Sale;

/**
 *
 * @author kienk
 */
public class SaleDAO extends DBContext {
    public List<Sale> getListSale(){
        List<Sale> list = new ArrayList<>();
          String query = "select * from [Sale] order by [percent] ASC";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Sale(rs.getInt(1), rs.getFloat(2)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public int getTotalSale() {
        String query = "select count(*) from [productVariant] where sale_Id !=1";
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
    
    public Sale getSaleById(int id){
        String query = "SELECT * FROM Sale"
                + " Where Id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Sale(rs.getInt(1), rs.getFloat(2));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public Sale isPercentExist (float percent){
         String query = "SELECT * FROM Sale"
                + " Where [Percent] = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, percent);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Sale(rs.getInt(1), rs.getFloat(2));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void createNewSale (float percent){
         String query = "Insert Into [Sale]([Percent])"
                + " values ("+percent+")";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Sale getNewSale (){
        String query = "SELECT Top(1) [Id], [Percent] FROM sale"
                + " ORDER BY [Id] DESC";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Sale(rs.getInt(1), rs.getFloat(2));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
//    public static void main(String[] args) {
//        SaleDAO saleDAO = new SaleDAO();
//
//        // Test getListSale method
//        List<Sale> sales = saleDAO.getListSale();
//        System.out.println("List of Sales:");
//        for (Sale sale : sales) {
//            System.out.println("ID: " + sale.getId() + ", Percent: " + sale.getPercent());
//        }
//
//        // Test getTotalSale method
//        int totalSale = saleDAO.getTotalSale();
//        System.out.println("Total Sales (excluding sale_Id = 1): " + totalSale);
//
//        // Test getSaleById method
//        Sale saleById = saleDAO.getSaleById(1); // Replace 1 with an appropriate ID
//        System.out.println("Sale by ID: " + saleById);
//
//        // Test isPercentExist method
//        float testPercent = 20.0f; // Replace with the percent you want to test
//        Sale saleWithPercent = saleDAO.isPercentExist(testPercent);
//        if (saleWithPercent != null) {
//            System.out.println("Sale with Percent " + testPercent + " exists: " + saleWithPercent);
//        } else {
//            System.out.println("No Sale found with Percent " + testPercent);
//        }
//
//        // Test createNewSale method
//        float newSalePercent = 25.0f; // Replace with the new percent to create
//        saleDAO.createNewSale(newSalePercent);
//        System.out.println("Created new sale with Percent: " + newSalePercent);
//
//        // Test getNewSale method
//        Sale newSale = saleDAO.getNewSale();
//        System.out.println("Newest Sale: " + newSale);
//    }
            }
