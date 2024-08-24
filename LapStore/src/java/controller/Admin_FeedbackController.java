/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

/**
 *
 * @author FPT
 */
@WebServlet(name = "Admin_FeedbackController", urlPatterns = {"/admin-feedback"})
public class Admin_FeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO fdao = new FeedbackDAO();
        List<Feedback> list;
        try {
            list = fdao.getAllFeedback();
            request.setAttribute("list", list);
            request.getRequestDispatcher("screens/Admin_FeedbackList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String feedbackId = request.getParameter("feedbackId");

        if (feedbackId != null) {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            try {
                feedbackDAO.deleteFeedback(Integer.parseInt(feedbackId));
            } catch (SQLException ex) {
                Logger.getLogger(Admin_FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FeedbackDAO fdao = new FeedbackDAO();
        List<Feedback> list;
        try {
            list = fdao.getAllFeedback();
            request.setAttribute("list", list);
            request.getRequestDispatcher("screens/Admin_FeedbackList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
