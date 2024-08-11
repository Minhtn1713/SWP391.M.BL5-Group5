package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;

/**
 * Servlet for handling creation of new products.
 * 
 * @author 84834
 */
public class Admin_CreateProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("screens/Admin_CreateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int success = 0;

        // Retrieve parameters from the request
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String processor = req.getParameter("processor");
        String graphicCard = req.getParameter("graphic_card");
        String screenDetails = req.getParameter("screen_details");
        String size = req.getParameter("size");
        double weight = Double.parseDouble(req.getParameter("weight"));
        String operatingSystem = req.getParameter("operating_system");
        int brand = Integer.parseInt(req.getParameter("brand"));
        String releaseDate = req.getParameter("release_date");
        String batteryLife = req.getParameter("battery_life");
        String description = req.getParameter("description");
        int status = Integer.parseInt(req.getParameter("status"));

        // Create a Product object
        Product product = new Product(0, name, price, processor, graphicCard, screenDetails, size, weight, operatingSystem, brand, releaseDate, batteryLife, description, status);
        
        // Initialize ProductDAO
        ProductDAO productDAO = new ProductDAO();
        
        // Create the product in the database
        success = productDAO.createProduct(product);
        
        // Check if product creation was successful
        if (success != 0) {
            // Assuming you need to handle images separately
            String[] imageUrls = req.getParameterValues("image_urls");
            if (imageUrls != null) {
                for (String url : imageUrls) {
                    productDAO.addProductImage(success, url); // Add image URL to ProductImage table
                }
            }
            
            // Redirect to the product list page
            List<Product> productList = productDAO.getProductList();
            req.setAttribute("list", productList);
            resp.sendRedirect("admin-product-list");
        } else {
            // Forward to error page
            req.getRequestDispatcher("error-page").forward(req, resp);
        }
    }
}
