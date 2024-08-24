package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductFeedbackData;

public class FeedbackDAO extends DBContext {

    public void deleteFeedback(int id) throws SQLException {
        String sql = "DELETE FROM feedback WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Feedback> getFeedbackByProductId(int productId) throws SQLException {
        String sql = "SELECT * FROM Feedback WHERE product_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setId(rs.getInt("id"));
                    feedback.setUserId(rs.getInt("user_id"));
                    feedback.setProductId(rs.getInt("product_id"));
                    feedback.setRating(rs.getInt("rating"));
                    feedback.setComment(rs.getString("comment"));
                    feedback.setTimestamp(rs.getTimestamp("timestamp"));
                    feedback.setAnonymous(rs.getBoolean("anonymous"));
                    feedbackList.add(feedback);
                }
            }
        }
        return feedbackList;
    }

    public String getUserName(int userID) throws SQLException {
        String sql = "SELECT u.fullname "
                + "FROM feedback f "
                + "JOIN [user] u ON f.user_id = u.id "
                + "WHERE f.user_id = ?";

        String userName = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userName = rs.getString("fullname");
                }
            }
        }

        return userName;
    }

    public List<Integer> getProductIdAvailable(int userID) throws SQLException {
        String sql = "SELECT product_id "
                + "FROM [orderdetail] "
                + "WHERE order_id IN ( "
                + "    SELECT id "
                + "    FROM [order] "
                + "    WHERE user_id = ? AND status = 5 "
                + ");";

        List<Integer> productIds = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productIds.add(rs.getInt("product_id"));
                }
            }
        }

        return productIds;
    }

    public void addFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO Feedback (user_id, product_id, rating, comment, timestamp, anonymous) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, feedback.getUserId());
            pstmt.setInt(2, feedback.getProductId());
            pstmt.setInt(3, feedback.getRating());
            pstmt.setString(4, feedback.getComment());
            pstmt.setTimestamp(5, feedback.getTimestamp());
            pstmt.setBoolean(6, feedback.isAnonymous());
            pstmt.executeUpdate();
        }
    }

    public Feedback getFeedbackByProductIdAndUserId(int productId, int userId) throws SQLException {
        String sql = "SELECT * FROM Feedback WHERE product_id = ? AND user_id = ?";
        Feedback feedback = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    feedback = new Feedback();
                    feedback.setId(rs.getInt("id"));
                    feedback.setProductId(rs.getInt("product_id"));
                    feedback.setUserId(rs.getInt("user_id"));
                    feedback.setComment(rs.getString("comment"));
                    feedback.setRating(rs.getInt("rating"));
                    feedback.setTimestamp(rs.getTimestamp("timestamp"));
                    feedback.setAnonymous(rs.getBoolean("anonymous"));
                }
            }
        }
        return feedback;
    }

    public void updateFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE Feedback SET comment = ?, rating = ?, anonymous = ?, timestamp = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, feedback.getComment());
            ps.setInt(2, feedback.getRating());
            ps.setBoolean(3, feedback.isAnonymous());
            ps.setTimestamp(4, feedback.getTimestamp());
            ps.setInt(5, feedback.getId());

            int rowsAffected = ps.executeUpdate();
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.INFO, "Rows affected: " + rowsAffected);

        }
    }

    public List<Feedback> getAllFeedback() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.*, u.fullname, p.name AS productname FROM Feedback f \n"
                + "                       JOIN [User] u ON f.user_Id = u.id  \n"
                + "                       JOIN Product p ON f.product_Id = p.id";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setProductId(rs.getInt("product_id"));
                feedback.setUserId(rs.getInt("user_id"));
                feedback.setRating(rs.getInt("rating"));
                feedback.setComment(rs.getString("comment"));
                feedback.setTimestamp(rs.getTimestamp("timestamp"));
                feedback.setUserName(rs.getString("fullname"));
                feedback.setProductname(rs.getString("productname"));
                feedbackList.add(feedback);
            }
        }
        return feedbackList;
    }

    public ProductFeedbackData getProductFeedbackData(int productId) {
        ProductFeedbackData feedbackData = null;
        String sql = "SELECT p.name AS productName,\n"
                + "       ROUND(AVG(CAST(f.rating AS FLOAT)), 2) AS averageRating,\n"
                + "       COUNT(f.[id]) AS numberOfFeedbacks\n"
                + "FROM Feedback f\n"
                + "JOIN Product p ON f.[product_id] = p.[id]\n"
                + "WHERE p.[id] = ? \n"
                + "GROUP BY p.name;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    double averageRating = rs.getDouble("averageRating");
                    int numberOfFeedbacks = rs.getInt("numberOfFeedbacks");

                    feedbackData = new ProductFeedbackData(productName, averageRating, numberOfFeedbacks);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackData;
    }
}
