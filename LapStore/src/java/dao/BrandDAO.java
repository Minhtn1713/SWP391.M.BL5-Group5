package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;

public class BrandDAO extends DBContext {

    // Method to get a list of all brands
 public List<Brand> getAllBrands() {
    List<Brand> brandList = new ArrayList<>();
    String query = "SELECT id, name FROM Brand";
    
    try (Connection conn = connection;
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Brand brand = new Brand(rs.getInt("id"), rs.getString("name"));
            brandList.add(brand);
        }
    } catch (SQLException e) {
        System.err.println("Error fetching brands: " + e.getMessage());
    }
    return brandList;
}


    // Method to get a brand by its ID
    public Brand getBrandById(int id) {
        String query = "SELECT * FROM Brand WHERE id = ?";
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Brand(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching brand by ID: " + e.getMessage());
        }
        return null;
    }

    // Method to create a new brand
    public int createBrand(Brand brand) {
        String query = "INSERT INTO Brand (name) VALUES (?)";

        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, brand.getName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating brand: " + e.getMessage());
        }
        return 0;
    }
}
