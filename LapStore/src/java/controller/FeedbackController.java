/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import dao.FeedbackDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feedback;
import java.sql.Timestamp;
import model.Product;

/**
 *
 * @author FPT
 */
@WebServlet(name = "FeedbackController", urlPatterns = {"/feedback"})
public class FeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null) {
            FeedbackDAO fDAO = new FeedbackDAO();
            try {
                List<Integer> productIds = fDAO.getProductIdAvailable(acc.getId());
                ProductDAO proDao = new ProductDAO();
                List<Product> allProducts = proDao.getProductListById(productIds);
                request.setAttribute("productList", allProducts);

                String productId = request.getParameter("productId");
                if (productId != null) {
                    try {
                        int pid = Integer.parseInt(productId);
                        Feedback existingFeedback = fDAO.getFeedbackByProductIdAndUserId(pid, acc.getId());
                        if (existingFeedback != null) {
                            request.setAttribute("existingFeedback", existingFeedback);
                        }
                        request.setAttribute("productId", pid);
                        request.getRequestDispatcher("screens/viewFeedback.jsp").forward(request, response);
                    } catch (NumberFormatException e) {

                        response.sendRedirect("home");
                    }
                } else {
                    request.getRequestDispatcher("screens/feedbackDetail.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("errorPage.jsp");  // Redirect to a generic error page
            }
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null) {
            String productId = request.getParameter("productId");
            String feedbackContent = request.getParameter("feedback");
            String ratingStr = request.getParameter("rating");
            String anonymousStr = request.getParameter("anonymous");
            String feedbackId = request.getParameter("feedbackId");
            try {
                int pid = Integer.parseInt(productId);
                int fid = Integer.parseInt(feedbackId);
                int rating = Integer.parseInt(ratingStr);
                boolean anonymous = "on".equals(anonymousStr);

                FeedbackDAO fDAO = new FeedbackDAO();
                Feedback feedback = new Feedback();
                feedback.setId(fid);
                feedback.setProductId(pid);
                feedback.setUserId(acc.getId());
                feedback.setComment(feedbackContent);
                feedback.setRating(rating);
                feedback.setTimestamp(new Timestamp(System.currentTimeMillis()));
                feedback.setAnonymous(anonymous);

                if (fDAO.getFeedbackByProductIdAndUserId(pid, acc.getId()) != null) {
                    fDAO.updateFeedback(feedback);
                } else {
                    fDAO.addFeedback(feedback);
                }

                response.sendRedirect("product-detail?id=" + productId);
            } catch (NumberFormatException | SQLException ex) {
                Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("feedback?productId=" + productId + "&success=false");
            }
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Feedback Controller";
    }
}
