package controller;

import dao.BrandDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Brand;
import model.Product;

public class Admin_CreateProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Assuming you have a BrandDAO to fetch brands
    BrandDAO brandDao = new BrandDAO();
    List<Brand> brands = brandDao.getAllBrands(); // Fetch the list of brands

    req.setAttribute("brands", brands); // Set the brands in the request scope
    req.getRequestDispatcher("screens/Admin_CreateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int success = 0;
        Product pro = new Product(
            0, 
            req.getParameter("name"), 
            req.getParameter("img"), 
            Integer.parseInt(req.getParameter("brandId")), 
            Float.parseFloat(req.getParameter("price")),
            req.getParameter("processor"), 
            req.getParameter("graphic_card"), 
            req.getParameter("screen_details"), 
            req.getParameter("size"), 
            Float.parseFloat(req.getParameter("weight")),
            req.getParameter("operatingSystem"), 
            req.getParameter("battery"), 
            req.getParameter("description"),
            1
        );
        String brandIdStr = req.getParameter("brandId");
         if (brandIdStr == null || brandIdStr.isEmpty()) {
                req.setAttribute("error", "Brand ID is required.");
              req.getRequestDispatcher("screens/Admin_CreateProduct.jsp").forward(req, resp);
             return;
                }

            int brandId = Integer.parseInt(brandIdStr);

        ProductDAO proDao = new ProductDAO();
        success = proDao.createProduct(pro);

        if(success != 0){
            List<Product> list = proDao.getProductList();
            req.setAttribute("list", list);
            resp.sendRedirect("admin-production-list");
        } else {
            req.getRequestDispatcher("error-page").forward(req, resp);
        }
    }
}