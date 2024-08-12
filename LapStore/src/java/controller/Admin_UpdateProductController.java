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
       
            String id = (String) req.getParameter("id");
            int product_id = Integer.parseInt(id);
            String product_name = (String) req.getParameter("name");
            String product_price = (String) req.getParameter("price");
            float price = Float.parseFloat(product_price);
            String product_processor = (String) req.getParameter("processor");
            String product_graphicCard = (String) req.getParameter("graphic_card");
            String product_screenDetails = (String) req.getParameter("screen_details");
            String product_size = (String) req.getParameter("size");
            String product_weight = (String) req.getParameter("weight");
             float weight = Float.parseFloat(product_weight);
            String product_operatingSystem =(String) req.getParameter("operatingSystem");
            String product_batteryLife =(String) req.getParameter("battery");
            String product_description = (String) req.getParameter("description");
            String product_brandId = (String) req.getParameter("brandId");
            int brandId = Integer.parseInt(product_brandId);
            
            String product_image = req.getParameter("img");
            if (req.getParameter("img") == null || req.getParameter("img").length() == 0){
            product_image = req.getParameter("current-image");
            }

            // Create a Product object
            Product product = new Product(product_id, product_name, product_image, brandId,
                    price, product_processor, product_graphicCard, product_screenDetails, product_size, weight, product_operatingSystem, product_batteryLife, product_description, 1);

       
       ProductDAO proDAO = new ProductDAO();
       int success = proDAO.updateProduct(product_id, product);
       if(success == 0){
           resp.sendRedirect("error-page");
       }
       else{
           List<Product> list = proDAO.getProductList();
           req.setAttribute("list", list);
           resp.sendRedirect("admin-production-list");
       }
    }

}
