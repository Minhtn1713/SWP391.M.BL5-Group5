package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Product;

public class ProductDAO extends DBContext {

    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Product";
        
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productList.add(new Product(
                    rs.getInt("id"),
                            rs.getString("name"),
                            rs.getFloat("price"),
                            rs.getString("processor"),
                            rs.getString("screen_details"),
//                            rs.getString("imgId"),
                            rs.getString("size"),
                            rs.getString("operating_system"),
                            rs.getString("battery_life"),
                            rs.getInt("status"),
                            rs.getInt("brand"),
                            rs.getFloat("weight"),
                            rs.getString("graphic_card"),
                            rs.getString("description"),
                            rs.getDate("release_date")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product list: " + e.getMessage());
        }
        return productList;
    }

    public Map<Integer, String> getHashMapProduct() {
        Map<Integer, String> hashMap = new HashMap<>();
        String query = "SELECT id, name FROM Product";

        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                hashMap.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product map: " + e.getMessage());
        }
        return hashMap;
    }

    public Product getProductById(int id) {
        String query = "SELECT * FROM Product WHERE id = ?";
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                             rs.getInt("id"),
                            rs.getString("name"),
                            rs.getFloat("price"),
                            rs.getString("processor"),
                            rs.getString("screen_details"),
//                            rs.getString("imgId"),
                            rs.getString("size"),
                            rs.getString("operating_system"),
                            rs.getString("battery_life"),
                            rs.getInt("status"),
                            rs.getInt("brand"),
                            rs.getFloat("weight"),
                            rs.getString("graphic_card"),
                            rs.getString("description"),
                            rs.getDate("release_date")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product by ID: " + e.getMessage());
        }
        return null;
    }

    public int updateProduct(int id, Product product) {
        String query = "UPDATE Product SET name = ?, price = ?, processor = ?, screen_details = ?, imgId = ?, " +
                "size = ?, operatingSystem = ?, battery = ?, status = ?, brandId = ?, weight = ?, " +
                "graphic_card = ?, description = ?, release_date = ? WHERE id = ?";
        
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getProcessor());
            ps.setString(4, product.getScreen_details());
            ps.setString(5, product.getImgId());
            ps.setString(6, product.getSize());
            ps.setString(7, product.getOperatingSystem());
            ps.setString(8, product.getBattery());
            ps.setInt(9, product.getStatus());
            ps.setInt(10, product.getBrandId());
            ps.setFloat(11, product.getWeight());
            ps.setString(12, product.getGraphic_card());
            ps.setString(13, product.getDescription());
            ps.setDate(14, product.getRelease_date());
            ps.setInt(15, id);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
        return 0;
    }

    public int updateProductStatus(int id, int status) {
        String query = "UPDATE Product SET status = ? WHERE id = ?";
        
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating product status: " + e.getMessage());
        }
        return 0;
    }

    public int deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE id = ?";
        
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
        return 0;
    }

    public int createProduct(Product product) {
        String query = "INSERT INTO Product (name, price, processor, screen_details, imgId, size, " +
                "operatingSystem, battery, status, brandId, weight, graphic_card, description, release_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getProcessor());
            ps.setString(4, product.getScreenDetails());
            ps.setString(5, product.getImgId());
            ps.setString(6, product.getSize());
            ps.setString(7, product.getOperatingSystem());
            ps.setString(8, product.getBattery());
            ps.setInt(9, product.getStatus());
            ps.setInt(10, product.getBrandId());
            ps.setFloat(11, product.getWeight());
            ps.setString(12, product.getGpu());
            ps.setString(13, product.getDescription());
            ps.setDate(14, product.getReleaseDate());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating product: " + e.getMessage());
        }
        return 0;
    }

    public double getPriceById(int id) {
        String query = "SELECT price FROM Product WHERE id = ?";
        
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("price");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching price by ID: " + e.getMessage());
        }
        return -1;
    }
}
