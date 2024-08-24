/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.CommentDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.ProductVariantDAO;
import dao.SaleDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Account;
import model.Comment;
import model.Order;
import model.ProductVariant;

/**
 *
 * @author 84834
 */
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if(account.getRole_id() == null) {
            req.getRequestDispatcher("sign-in").forward(req, resp);
        }else{
            if(account.getRole_id().equals("1")){
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO detailDao = new OrderDetailDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        ProductDAO productDao = new ProductDAO();
        CommentDAO commentDao = new CommentDAO();
        UserDAO userDao = new UserDAO();
        

        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int year = date.getYear();
        float totalMonth = orderDao.TotalPriceMonth(year, month);
        float totalYear = orderDao.TotalPriceYear(year);


        HashMap<Integer, String> productMap = (HashMap<Integer, String>) productDao.getHashMapProduct();
        
        //phan report comment can lay ra content va user.
        HashMap<Integer, String> userMap = (HashMap<Integer, String>) userDao.getUserName();
        ArrayList<Comment> listComment = commentDao.getReportedComment();
        
        int hot_id = detailDao.getHotProductID();
        ProductVariant hot_product = variantDao.getProductVariantByID(hot_id);
        List<String> hot = new ArrayList<>();
        if (hot_id != 0) {
            hot.add(productMap.get(hot_product.getProductId()));
            hot.add(String.valueOf(hot_product.getVariantPrice()));
            hot.add(String.valueOf(hot_product.getQuantity()));
        } else {
            hot.add(" ");
            hot.add(" ");
            hot.add(" ");
        }

        
        AccountDAO acd = new AccountDAO();
        OrderDAO ord = new OrderDAO();
        SaleDAO sd = new SaleDAO();
        int totalUser = acd.getTotalCustomer();
        int totalProduct = variantDao.getTotalProductVariant();
        int totalOrder = ord.getTotalOrder();
        int totalRevenue = ord.getTotalRevenue();
        int totalSale = sd.getTotalSale();
        List<Order> recentOrders = ord.getTop5RecentOrders();
        req.setAttribute("recentOrders", recentOrders);
        req.setAttribute("totalUser", totalUser);
        req.setAttribute("totalProduct", totalProduct);
        req.setAttribute("totalOrder", totalOrder);
        req.setAttribute("totalRevenue", totalRevenue);
        req.setAttribute("totalSale", totalSale);
        req.setAttribute("listComment", listComment);
        req.setAttribute("listUser", userMap);
        req.setAttribute("recentOrders", recentOrders);
        req.setAttribute("listProduct", productMap);
        req.getRequestDispatcher("/screens/Admin_Dashboard.jsp").forward(req, resp);
    }else {
                req.getRequestDispatcher("error-page").forward(req, resp);
            }
        }
    }
}