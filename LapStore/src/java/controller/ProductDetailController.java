package controller;

import dao.BrandDAO;
import dao.ProductDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Brand;
import model.Product;
import model.ProductImage;

@WebServlet(name = "ProductDetailController", urlPatterns = {"/product-detail"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductDetailDAO proDao = new ProductDetailDAO();
            BrandDAO bDAO = new BrandDAO();
            String id = request.getParameter("id");
            int idp = Integer.parseInt(id);
            Product product = proDao.getProductbyId(idp);

            List<ProductImage> pImageList;
            pImageList = proDao.getProductImage(idp);

            Brand brand = new Brand();
            brand = bDAO.getBrandById(product.getBrandId());

            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }

            request.setAttribute("brand", brand);
            request.setAttribute("image", pImageList);
            request.setAttribute("productDetail", product);
            request.getRequestDispatcher("screens/productDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        }
    }

    @Override
    public String getServletInfo() {
        return "Product detail controller";
    }
}