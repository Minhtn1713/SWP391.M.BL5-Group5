package controller;

import dao.BrandDAO;
import dao.FeedbackDAO;
import dao.ProductDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Feedback;
import model.Product;
import model.ProductFeedbackData;
import model.ProductImage;

@WebServlet(name = "ProductDetailController", urlPatterns = {"/product-detail"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductDetailDAO proDao = new ProductDetailDAO();
            BrandDAO bDAO = new BrandDAO();
            FeedbackDAO fDAO = new FeedbackDAO();
            
            
            
            String id = request.getParameter("id");
            int idp = Integer.parseInt(id);
            Product product = proDao.getProductbyId(idp);

            List<ProductImage> pImageList;
            pImageList = proDao.getProductImage(idp);

            Brand brand = new Brand();
            brand = bDAO.getBrandById(product.getBrandId());

            List<Feedback> fbList;
            fbList = fDAO.getFeedbackByProductId(idp);
            
            ProductFeedbackData feedbackData = fDAO.getProductFeedbackData(idp);

            for (Feedback fb : fbList) {
                if (!fb.isAnonymous()) {
                    fb.setUserName(fDAO.getUserName(fb.getUserId()));
                } else {
                    fb.setUserName("Anonymous");
                }
            }

            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }

            request.setAttribute("feedbackData", feedbackData);
            request.setAttribute("brand", brand);
            request.setAttribute("image", pImageList);
            request.setAttribute("productDetail", product);
            request.setAttribute("feedback", fbList);
            request.getRequestDispatcher("screens/productDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Product detail controller";
    }
}
