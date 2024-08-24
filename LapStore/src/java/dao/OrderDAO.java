package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.ProductVariant;
import model.Status;
import model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 84834
 */
public class OrderDAO extends DBContext {

    public List<Order> getAllOrder() {
        List<Order> listOrder = new ArrayList<>();
        String sql = "select * from [Order] ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public List<Order> getOrderList(int userId, String status) {
        List<Order> listOrder = new ArrayList<>();
        String sql = "select * from [Order] where [user_id] = ? ";
        if (!status.equals("1")) {
            sql += " and [status] = " + status;
        } else {
            sql += " and [status] != 1";
        }
        sql += " order by id desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public int getTotalOrder() {
        String query = "select count(*) from [order] where status !=1";
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
    public int getTotalRevenue() {
        String query = "Select SUM(total_price) From [Order]";
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
    public List<Order> getRecentOrder() {
        List<Order> listOrder = new ArrayList<>();
        String sql = "SELECT TOP (5) [Id]\n"
                + "      ,[User_Id]\n"
                + "      ,[Total_Price]\n"
                + "      ,[Created_Date]\n"
                + "      ,[status]\n"
                + "      ,[Name]\n"
                + "      ,[Address]\n"
                + "      ,[Phone]\n"
                + "  FROM [LapStore].[dbo].[Order] order by id desc ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public List<Status> getAllStatus() {
        List<Status> list = new ArrayList<>();
        String sql = "select * from [Status]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Status(rs.getInt(1), rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public float TotalPriceYear(int year) {
        int total = 0;
        String sql = "select Total_Price from [Order] where YEAR(Created_Date) =" + year;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getFloat(3);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public float TotalPriceMonth(int year, Month month) {
        int total = 0;
        String sql = "select * from [Order] where YEAR(Created_Date) =" + year + "and MONTH(Created_Date) = " + month;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getFloat(3);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    //Check xem id do co order nao con dang ton tai hay khong
    public int checkOrder(int userId) {
        int exist = 0;
        String sql = "select Id from [Order] where [status] = 1 and [User_Id] = " + userId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exist = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exist;
    }

    public int createOrder(int User_id, double Total_Price) {
        int succes = 0;
        String sql = "Insert into [Store].[dbo].[Order]([User_Id], [Total_Price], [Created_Date],[Status]) values (?,?,(SELECT GETDATE()),1);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, User_id);
            ps.setDouble(2, Total_Price);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public Order getOrderById(int User_Id) {
        ArrayList<Order> listOrder = new ArrayList<>();
        Order result = null;
        String sql = "select * from [Order] where  [Order].[User_Id] = " + User_Id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        result = listOrder.get(listOrder.size() - 1);
        return result;
    }

    public Order getOrderByOrderId(int id) {
        ArrayList<Order> listOrder = new ArrayList<>();
        Order result = null;
        String sql = "select * from [Order] where  [Order].[Id] = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        result = listOrder.get(listOrder.size() - 1);
        return result;
    }

    //check status cua order, neu la 1 thi moi gui len Checkout.
    public int checkStatus(int orderId) {
        int status = 0;
        String sql = "select [status] from [Order] where [Id] = " + orderId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }

    public int updateTotalPrice(int id, double totalPrice) {
        int succes = 0;
        String sql = "UPDATE [Order] SET [Total_Price] = ? WHERE Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, totalPrice);
            ps.setInt(2, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int updateDate(int id) {
        int succes = 0;
        String sql = "update [Order] set [Created_Date] = (SELECT CAST(GETDATE() AS DATE) AS CurrentDate) where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int updateStatus(int id, int status) {
        int succes = 0;
        String sql = "update [Order] set [status] = ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int updateInformation(int id, String address, String name, String phone) {
        int succes = 0;
        String sql = "update [Order] set [Address] = ?, [Name] = ?, [Phone] = ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, address);
            ps.setNString(2, name);
            ps.setString(3, phone);
            ps.setInt(4, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }
    
    public Order getOrderById_Dung(int id) {
        String query = "select * from [Order] where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    

    public List<ProductVariant> getAllProductVariantById(int id) {
        String query = "select pv.id,p.name,r.RAM,s.Storage_Size,od.quantity,p.price,pv.variant_price from [Order] o join [OrderDetail] od on od.order_id=o.id  join ProductVariant pv on pv.id = od.product_id join Product p on p.id = pv.product_id join Ram r on r.Id=pv.RAM_id join Storage s on s.Id=pv.Storage_id where o.id=?";
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
        String query = "select pv.id,p.name,r.RAM,s.Storage_Size,pv.quantity,p.price,pv.variant_price from Product p join ProductVariant pv on p.id = pv.product_id join Ram r on r.Id=pv.RAM_id join Storage s on s.Id=pv.Storage_id;";
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
        String query = "select pv.id,p.name,r.RAM,s.Storage_Size,pv.quantity,p.price,pv.variant_price from Product p join ProductVariant pv on p.id = pv.product_id join Ram r on r.Id=pv.RAM_id join Storage s on s.Id=pv.Storage_id where pv.id=?";
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
        int oldStatus = getOrderById_Dung(id).getStatus(); // Get current status

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
        String sql = "insert into [LapStore].[dbo].[OrderDetail](order_id,product_id,quantity) values (?, ?, ?);";
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
                return rs.getInt(5);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    public List<Order> getTop5RecentOrders() {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT o.*, s.status AS status_name FROM [Order] o INNER JOIN [Status] s ON o.[status] = s.id ORDER BY o.id";  
    try (
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotalPrice(rs.getFloat("total_price"));
            order.setCreatedDate(rs.getDate("created_date"));
            order.setStatus(rs.getInt("status"));
            order.setName(rs.getString("name"));
            order.setAddress(rs.getString("address"));
            order.setPhone(rs.getString("phone"));
            order.setStatusName(rs.getString("status_name"));
            orders.add(order);
            
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return orders;
}
}
