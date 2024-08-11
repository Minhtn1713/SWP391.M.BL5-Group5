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

    public int createBrand(String name) {
        int success = 0;
        String sql = "INSERT INTO [Brand]([name]) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            success = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return success;
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
        String query = "select * from [LapStore].[dbo].[Brand] "
                + " Where [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Brand(rs.getInt(1), rs.getString(2));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Brand> getBrandList() {
        List<Brand> productList = new ArrayList<>();
        String query = "SELECT * FROM Brand";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productList.add(new Brand(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product list: " + e.getMessage());
        }
        return productList;
    }
}
