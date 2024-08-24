/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.ProductFeedbackData;

/**
 *
 * @author FPT
 */
@WebServlet(name = "Admin_ProductFeedback", urlPatterns = {"/product-feedback-static"})
public class Admin_ProductFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int pId = Integer.parseInt(productId);
        FeedbackDAO fdao = new FeedbackDAO();
        ProductFeedbackData feedbackData = fdao.getProductFeedbackData(pId);
        List<Feedback> feedback = null;
        try {
            feedback = fdao.getFeedbackByProductId(pId);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_ProductFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gson gson = new Gson();
        String feedbackJson = gson.toJson(feedback);
        
        request.setAttribute("feedbackData", feedbackData);
        request.setAttribute("feedbackJson", feedbackJson);
        request.getRequestDispatcher("screens/productFeedback.jsp").forward(request, response);
    }
}

