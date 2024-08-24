/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.BlogCategory;
import model.SubBlog;

/**
 *
 * @author lords
 */
public class BlogDAO extends DBContext{
    public List<Blog> getListBlogbyCategoryId(int id){
        List<Blog> list = new ArrayList<>();
        String query = "Select * from [LapStore].[dbo].[Blog] where categoryId = "+ id + " order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public List<Blog> getListBlogbyCategoryId(int id, int status){
        List<Blog> list = new ArrayList<>();
        String query = "Select * from [LapStore].[dbo].[Blog] where categoryId = "+ id + " and [status]=" + status + 
                " order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public Blog getListBlogById(int id){
        String query = "Select * from [LapStore].[dbo].[Blog] where id = "+ id + " order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               return new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public List<SubBlog> getListSubBlogbyBlogId(int id){
        List<SubBlog> list = new ArrayList<>();
        String query = "Select * from [LapStore].[dbo].[SubBlog] where blogId = " + id;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new SubBlog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
     public List<BlogCategory> getListBlogCategory(){
        List<BlogCategory> list = new ArrayList<>();
        String query = "Select * from [LapStore].[dbo].[BlogCategory]";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new BlogCategory(rs.getInt(1), rs.getNString(2), rs.getInt(3)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public List<BlogCategory> getListBlogCategory(int status){
        List<BlogCategory> list = new ArrayList<>();
        String query = "Select * from [LapStore].[dbo].[BlogCategory] where [status]= " + status;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new BlogCategory(rs.getInt(1), rs.getNString(2), rs.getInt(3)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
}
