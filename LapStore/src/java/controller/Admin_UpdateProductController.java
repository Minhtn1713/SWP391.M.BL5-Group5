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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        int productId = 0;
      
            productId = Integer.parseInt(id);
        

        ProductDAO proDAO = new ProductDAO();
        Product product = proDAO.getProductById(productId);
        BrandDAO brandDAO = new BrandDAO();
        List<Brand> brands = brandDAO.getAllBrands();
        req.setAttribute("product", product);
        req.setAttribute("brands", brands);
        req.getRequestDispatcher("screens/Admin_UpdateProduct.jsp").forward(req, resp);
    }

    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int success = 0;

    try {
        // Retrieve and parse parameters with checks
        int id = parseInteger(req.getParameter("id"), 0);
        String name = req.getParameter("name");
        float price = parseFloat(req.getParameter("price"), 0.0f);
        String processor = req.getParameter("processor");
        String graphicCard = req.getParameter("graphic_card");
        String screenDetails = req.getParameter("screen_details");
        String size = req.getParameter("size");
        float weight = parseFloat(req.getParameter("weight"), 0.0f);
        String operatingSystem = req.getParameter("operating_system");
        String batteryLife = req.getParameter("battery_life");
        String description = req.getParameter("description");
        int brandId = parseInteger(req.getParameter("brandId"), 0);

        // Handle the image
        String newImage = req.getParameter("img");
        String currentImage = req.getParameter("current_image");
        String image = (newImage == null || newImage.isEmpty()) ? currentImage : newImage;

        // Create a Product object
        Product product = new Product(id, name, image, brandId, price, processor, graphicCard, screenDetails, size, weight, operatingSystem, batteryLife, description, 1);

        // Initialize ProductDAO
        ProductDAO productDAO = new ProductDAO();

        // Update the product in the database
        success = productDAO.updateProduct(product);

        // Check if product update was successful
        if (success > 0) {
            resp.sendRedirect("admin-production-list");
        } else {
            req.getRequestDispatcher("error-page").forward(req, resp);
        }
    } catch (NumberFormatException e) {
        // Log the exception and show an error page
        System.err.println("Number format exception: " + e.getMessage());
        req.getRequestDispatcher("error-page").forward(req, resp);
    }
}

// Helper methods to safely parse integers and floats
private int parseInteger(String str, int defaultValue) {
    try {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(str);
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

private float parseFloat(String str, float defaultValue) {
    try {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        return Float.parseFloat(str);
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

}
