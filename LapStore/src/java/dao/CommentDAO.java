/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Comment;

/**
 *
 * @author lords
 */
public class CommentDAO extends DBContext{
    public int createComment(int userId, int productId, String content) {
        int succes = 0;
        String query = "Insert into [Comment]([User_Id], Product_Id, content, status) values (?, ?, ?, 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setString(3, content);
            succes = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return succes;
    }
    public ArrayList<Comment> getReportedComment() {
        ArrayList<Comment> list = new ArrayList<>();
        String sql = "select * from [Comment] where status = -1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Comment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getNString(4), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        CommentDAO dao = new CommentDAO();
        System.out.println(dao.createComment(1,1, "alo"));
    }
    
}
