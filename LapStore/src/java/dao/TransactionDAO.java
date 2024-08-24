/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class TransactionDAO extends DBContext {
    public boolean isTransactionProcessed(String transactionId) {
    String query = "SELECT COUNT(*) FROM [Transaction] WHERE transaction_id = ?";
    try (PreparedStatement stm = connection.prepareStatement(query)) {
        stm.setString(1, transactionId);
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public void markTransactionAsProcessed(String transactionId) {
    String query = "INSERT INTO [Transaction] (transaction_id) VALUES (?)";
    try (PreparedStatement stm = connection.prepareStatement(query)){
        
        stm.setString(1, transactionId);
        stm.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
