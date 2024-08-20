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
        String query = "SELECT p.id, p.name, p.img, p.brand, p.price, p.processor, p.graphic_card, "
                + "p.screen_details, p.size, p.weight, p.operating_system, p.battery_life, p.status,"
                + " p.description, b.name AS brand_name, p.status "+
                       "FROM Product p " +
                       "JOIN Brand b ON p.brand = b.id";

        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productList.add(new Product(
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
                    rs.getInt("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product list: " + e.getMessage());
        }
        return productList;
    }
 public List<Product> getListProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.img,  p.price, p.processor, p.graphic_card, "
                + "p.screen_details, p.size, p.weight, p.operating_system, p.battery_life, p.status,"
                + " p.description, p.status Where price <= 1000";

        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productList.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("img"),
                    rs.getFloat("price"),       
                    rs.getString("processor"),
                    rs.getString("graphic_card"),
                    rs.getString("screen_details"),
                    rs.getString("size"),
                    rs.getFloat("weight"),
                    rs.getString("operating_system"),
                    rs.getString("battery_life"),
                    rs.getString("description"),
                    rs.getInt("status")
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
                    rs.getInt("status")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error fetching product by ID: " + e.getMessage());
    }
    return null;
}
        public Product getProductByID(String id) {
        String query = "SELECT * FROM Product WHERE id = ?";
        try {
            Connection conn = connection;
         PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
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
                    rs.getInt("status")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error fetching product by ID: " + e.getMessage());
    }
    return null;
}


    public int updateProduct(int id, Product product) {
    String query = "UPDATE Product SET name = ?, img = ?, brand = ?, price = ?, processor = ?, graphic_card = ?, screen_details = ?, " +
                   "size = ?, weight = ?, operating_system = ?, battery_life = ?, description = ?, status = ? WHERE id = ?";

    try (Connection conn = connection;
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, product.getName());
        ps.setString(2, product.getImg());
        ps.setInt(3, product.getBrandId());
        ps.setFloat(4, product.getPrice());
        ps.setString(5, product.getProcessor());
        ps.setString(6, product.getGraphic_card());
        ps.setString(7, product.getScreen_details());
        ps.setString(8, product.getSize());
        ps.setFloat(9, product.getWeight());
        ps.setString(10, product.getOperatingSystem());
        ps.setString(11, product.getBattery());
        ps.setString(12, product.getDescription());
        ps.setInt(13, product.getStatus());
        ps.setInt(14, product.getId());

        return ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
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
    int success = 0;
    String sql = "INSERT INTO Product (name, img, brand, price, processor, graphic_card, screen_details, size, weight , operating_system, battery_life, description, status) "
            + "VALUES ('" + product.getName() + "', '" + product.getImg() + "' , " + product.getBrandId() 
            + ", " + product.getPrice() + ", '" + product.getProcessor() + "' , '" + product.getGraphic_card()+"', '" 
            + product.getScreen_details() + "', '" + product.getSize() + "', " + product.getWeight() + ",'" 
            + product.getOperatingSystem() + "', '" + product.getBattery() + "' ,'" + product.getDescription() + "', " + product.getStatus()+ ")";
    
    try (Connection conn = connection;
         PreparedStatement ps = conn.prepareStatement(sql)) {
        success = ps.executeUpdate();
          ps.close();
    } catch (SQLException e) {
        System.err.println("Error creating product: " + e.getMessage());
    }
    return success;
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