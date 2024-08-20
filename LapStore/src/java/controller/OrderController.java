/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductVariant;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderController", urlPatterns = {"/order", "/order/toggle","/order/update","/order/save"})
public class OrderController extends HttpServlet {

    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String ACTION = request.getServletPath();
        
        switch (ACTION) {

            case "/order" -> {
                getAllOrders(request, response);
            }
            case "/order/toggle" -> {
                toggle(request, response);
            }
            case "/order/update" -> {
                loadOrderDetails(request, response);
            }
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String ACTION = request.getServletPath();
        
        switch (ACTION) {

            case "/order/save" -> {
                updateOrder(request, response);
            }
    }
        
    }

    private void getAllOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
OrderDAO u = new OrderDAO();
        String indexPage = request.getParameter("index");

        // Initialize currentPage to 1 if indexPage is null or not a number
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(indexPage);
        } catch (NumberFormatException e) {
            // Handle parsing exception (if any), default to page 1
        }
        // Ensure currentPage is within valid range
        if (currentPage < 1) {
            currentPage = 1;
        }
        // Fetch blog posts from repository

        List<Order> listOrders = u.getAllOrder();

        // Iterate through each blog to fetch and set categories
        //for (User u : listUsers) {
        //List<String> cats = blogRepo.findAll(blog.getId());
        //blog.setCategories(cats);
        //}
        String search = request.getParameter("txtSearch");
        List<Order> blogSearch = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            for (Order b : listOrders) {
                if (b.getName().toLowerCase().contains(search.toLowerCase())) {
                    blogSearch.add(b);
                }

            }
            // Filter blogs by category
            listOrders = blogSearch;
        } else {
            // Fetch all blogs
            listOrders = u.getAllOrder();
            // Iterate through each blog to fetch and set categories
            //for (U blog : listBlogs) {
            //List<String> cats = blogRepo.findAll(blog.getId());
            //blog.setCategories(cats);
            //}
        }
        // Calculate pagination variables
        int pageSize = 6; // Number of blogs per page
        int totalBlogs = listOrders.size();
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
        if (totalPages == 0) {
            totalPages++;
        }
        // Adjust currentPage if it exceeds total pages
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        // Calculate start index for slicing the list
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalBlogs);
        // Slice the list to get blogs for the current page
        List<Order> paginatedBlogs = listOrders.subList(startIndex, endIndex);
        request.setAttribute("txtS", search);
        request.setAttribute("orders", paginatedBlogs);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("endP", totalPages);
        request.getRequestDispatcher("/screens/Staff_OrderList.jsp").forward(request, response);    
    }

    private void toggle(HttpServletRequest request, HttpServletResponse response) {
        OrderDAO u = new OrderDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));

        try {

            boolean isToggled = u.toggleById(status,id);
            request.setAttribute("currentSite", "/order");
            if (isToggled) {
                response.sendRedirect("/LapStore_main/order?toggled=successful");
            } else {
                response.sendRedirect("/LapStore_main/order?toggled=failed");
            }
        } catch (NumberFormatException | IOException ex) {
        }    
    }

    private void loadOrderDetails(HttpServletRequest request, HttpServletResponse response) {
        OrderDAO u = new OrderDAO();
        UserDAO u1 = new UserDAO();
        ProductDAO  p =new ProductDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            
            List<ProductVariant> listP = u.getAllProductVariantById(id);
            
            Order order = u.getOrderById_Dung(id);
            User user = u1.getUserById_Dung(order.getUserId());
            request.setAttribute("listP", listP);
            request.setAttribute("user", user);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/screens/Staff_OrderDetails.jsp").forward(request, response);

        } catch (Exception ex) {
        }    
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) {
        OrderDAO u = new OrderDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));

        try {

            boolean isToggled = u.toggleById(status,id);
            request.setAttribute("currentSite", "/order");
            if (isToggled) {
                response.sendRedirect("/LapStore_main/order/update?id="+id+"&updated=yes");
            } else {
                response.sendRedirect("/LapStore_main/order/update?id="+id+"&updated=no");
            }
        } catch (NumberFormatException | IOException ex) {
        }        
    }
}