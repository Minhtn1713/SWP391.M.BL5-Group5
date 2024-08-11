/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AccountDAO;
import dao.SecurityDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Security;
import model.SecurityQuestion;

/**
 *
 * @author lords
 */
public class ForgotPasswordController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       AccountDAO dao = new AccountDAO();
        ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
        request.setAttribute("quest", list);
        request.getRequestDispatcher("screens/forgotPassword.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         AccountDAO accDao = new AccountDAO();
        SecurityDAO securityDao = new SecurityDAO();
        //Lay du lieu thu form cua nguoi dung    
        String username = request.getParameter("username");
        int id_question = Integer.parseInt(request.getParameter("question"));
        String anwser = request.getParameter("answer");
        String password = request.getParameter("password");
        //Check xem user do co ton tai hay khong
        int accountID = accDao.getAccountID(username);
        if (accountID == 0) {
            AccountDAO dao = new AccountDAO();
            ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
            request.setAttribute("quest", list);
            request.setAttribute("mess", "This username does not exist!");
            request.getRequestDispatcher("screens/forgotPassword.jsp");
        } else {
            Security infor = securityDao.getQuestAnswer(accountID);
            if (infor.getQuestion_id() != id_question
                    || !anwser.equalsIgnoreCase(infor.getAnswer())) {
                AccountDAO dao = new AccountDAO();
                ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
                request.setAttribute("quest", list);
                request .setAttribute("mess", "Your answer is incorrect!");
                request.getRequestDispatcher("screens/forgotPassword.jsp").forward(request, response);
            } else {
                int success = accDao.updatePassword(accountID, password);
                if (success == 0) {
                    AccountDAO dao = new AccountDAO();
                    ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
                    request.setAttribute("mess", "Update password success!");
                    request.getRequestDispatcher("screens/forgotPassword.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/LapStore/sign-in");
                }

            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
