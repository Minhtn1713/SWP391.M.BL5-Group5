/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Brand;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO extends DBContext {

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

    public int updateBrand(int id, Brand brand) {
        String query = "UPDATE Brand SET name = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, brand.getName());
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating brand: " + e.getMessage());
        }
        return 0;
    }

    public int deleteBrand(int id) {
        String query = "DELETE FROM Brand WHERE id = ?";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting brand: " + e.getMessage());
        }
        return 0;
    }

    public Brand getBrandById(int id) {
        String query = "SELECT * FROM Brand WHERE id = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query)) {
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

    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<>();
        String query = "SELECT id, name FROM Brand";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Brand brand = new Brand(rs.getInt("id"), rs.getString("name"));
                brandList.add(brand);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching brands: " + e.getMessage());
        }
        return brandList;
    }
    
    
}