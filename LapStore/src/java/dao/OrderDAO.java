package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.OrderDetail;
import model.ProductVariant;
import model.User;

public class OrderDAO extends DBContext {

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(8), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Order getOrderById(int id) {
        String query = "select * from [Order] where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(8), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<ProductVariant> getAllProductVariantById(int id) {
        String query = "select pv.id,p.name,pv.RAM,pv.storage,od.quantity,p.price,pv.variant_price from [Order] o join [OrderDetail] od on od.order_id=o.id  join ProductVariant pv on pv.id = od.productVariant_id join Product p on p.id = pv.product_id where o.id=?";
        List<ProductVariant> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductVariant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(7), rs.getFloat(6)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ProductVariant> getAllProductVariant() {
        String query = "select pv.id,p.name,pv.RAM,pv.storage,pv.quantity,p.price,pv.variant_price from Product p join ProductVariant pv on p.id = pv.product_id";
        List<ProductVariant> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductVariant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(7), rs.getFloat(6)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ProductVariant getProductVariantById(int id) {
        String query = "select pv.id,p.name,pv.RAM,pv.storage,pv.quantity,p.price,pv.variant_price from Product p join ProductVariant pv on p.id = pv.product_id where pv.id=?";
        List<ProductVariant> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductVariant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(7), rs.getFloat(6));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<OrderDetail> getAllOrderDetailByOrderId(int id) {
        String query = "select * from [OrderDetail] where order_id=?";
        List<OrderDetail> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ProductVariant getProductVariantById2(int id) {
        String query = "select * from [ProductVariant] where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8), rs.getInt(9));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean toggleById(int status, int id) {
        int check = 0;
        Connection con = null;
        int oldStatus = getOrderById(id).getStatus(); // Get current status

        String sql = "UPDATE [Order] SET status = ? WHERE id = ?"; // Update query

        try {
            // Use the connection field from DBContext
            con = (Connection) this.connection;
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Toggle the status
                ps.setInt(1, status);
                ps.setInt(2, id);

                check = ps.executeUpdate(); // Execute the update
                con.commit(); // Commit transaction
            }

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            e.printStackTrace(System.err);
        } finally {
            // No need to close the connection here as it's managed by DBContext
        }

        return check > 0; // Return true if the update was successful
    }

    public int createOrder(User u, String name, String phone, String address, float total_price, int status) {
        int succes = 0;
        if(u!=null){
        String sql = "insert into [LapStore].[dbo].[Order](user_id,total_price,name,address,phone,status) values (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, u.getId());
            ps.setFloat(2, total_price);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, status);
            System.out.println(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        }else{
            String sql = "insert into [LapStore].[dbo].[Order](total_price,name,address,phone,status) values ( ?, ?, ?, ?, ?);";
            try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            
            ps.setFloat(1, total_price);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setInt(5, status);
            System.out.println(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        }
        return succes;
    }

    public int createOrderDetail(Order o, ProductVariant productVariant, int quantity) {
        int succes = 0;
        String sql = "insert into [LapStore].[dbo].[OrderDetail](order_id,productVariant_id,quantity) values (?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, o.getId());
            ps.setInt(2, productVariant.getId());
            ps.setInt(3, quantity);
            System.out.println(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int setProductVariantId(int quantity, int id) {
        int succes = 0;
        String sql = "UPDATE ProductVariant\n"
                + "SET quantity = ?\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            System.out.println(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int getOrderId() {
        int succes = 0;
        String sql = "SELECT TOP 1 * from [Order] Order by id desc;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public int getProductVariantQuantity(int id) {
        int succes = 0;
        String sql = "select * from ProductVariant where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(6);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    
}
