package controller;

import dao.ProductDAO;
import dao.BrandDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;
import model.Brand;

public class Admin_UpdateProductController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product pro = new Product();
        ProductDAO dao = new ProductDAO();
        String id = (String) req.getParameter("id");

        int product_id = 0;
        try {
            product_id = Integer.parseInt(id);
        } catch (Exception e) {
            resp.sendRedirect("/home");
        }
        pro = dao.getProductById(product_id);
        req.setAttribute("product", pro);
        req.getRequestDispatcher("screens/Admin_UpdateProduct.jsp").forward(req, resp);
    }

    
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int success = 0;

    // Retrieve parameters from the request
    String id = req.getParameter("id");
    String name = req.getParameter("name");
    double price = Double.parseDouble(req.getParameter("price"));
    String processor = req.getParameter("processor");
    String graphicCard = req.getParameter("graphic_card");
    String screenDetails = req.getParameter("screen_details");
    String size = req.getParameter("size");
    double weight = Double.parseDouble(req.getParameter("weight"));
    String operatingSystem = req.getParameter("operating_system");
    int brand = Integer.parseInt(req.getParameter("brandId"));
    String releaseDate = req.getParameter("release_date");
    String batteryLife = req.getParameter("battery_life");
    String description = req.getParameter("description");
    int status = Integer.parseInt(req.getParameter("status"));
    String currentImage = req.getParameter("current_image");
    String newImage = req.getParameter("img");

    // Default image if not provided
    String imageUrl = (newImage != null && !newImage.isEmpty()) ? newImage : currentImage;

    // Create a Product object
    Product product = new Product(Integer.parseInt(id), name, imageUrl, (float)price, processor, graphicCard, screenDetails, size, (float)weight, operatingSystem, brand, releaseDate, batteryLife, description, status);

    // Initialize ProductDAO
    ProductDAO productDAO = new ProductDAO();
    
    // Update the product in the database
    success = productDAO.updateProduct(product);

    // Check if product update was successful
    if (success > 0) {
        resp.sendRedirect("admin-product-list");
    } else {
        // Forward to error page
        req.getRequestDispatcher("error-page").forward(req, resp);
    }
}

}
