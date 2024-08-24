/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.User;


public class AccountProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet AccountProfileControllerr</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountProfileControllerr at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        AccountDAO accDao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if(acc!=null){
        int userId = accDao.getAccountID(acc.getUsername());
        User user = userDao.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);}
        else{
        response.sendRedirect("home");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        if (status.equals("profile")) {
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            if (fullName == null || fullName.trim().isEmpty()) {
                request.setAttribute("error6", "Full name cannot be empty.");
                request.setAttribute("fullName", fullName);
                request.setAttribute("gender", gender);
                request.setAttribute("phone", phone);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
                return;
            }

            if (address == null || address.trim().isEmpty()) {
                request.setAttribute("error7", "Address cannot be empty.");
                request.setAttribute("fullName", fullName);
                request.setAttribute("gender", gender);
                request.setAttribute("phone", phone);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
                return;
            }

            // Phone validation
            if (phone == null || phone.length() < 5 || !phone.matches("\\d{5,}")) {
                request.setAttribute("error3", "Phone number must be at least 5 digits.");
                request.setAttribute("fullName", fullName);
                request.setAttribute("gender", gender);
                request.setAttribute("phone", phone);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
                return;
            }

            // Email validation
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                request.setAttribute("error4", "Invalid email format.");
                request.setAttribute("fullName", fullName);
                request.setAttribute("gender", gender);
                request.setAttribute("phone", phone);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
                return;
            }

            UserDAO user = new UserDAO();
            AccountDAO accDao = new AccountDAO();
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("account");
          
                int userId = accDao.getAccountID(acc.getUsername());
                user.updateUser(userId, fullName, phone, email, address, gender);
                request.getSession().setAttribute("updateSuccess", "Profile updated successfully!");
                response.sendRedirect("account-profile?status=profile");
           

        } else if (status.equals("setting")) {
            String password = request.getParameter("current-password");
            String newPassword = request.getParameter("newPassword");
            String rePassword = request.getParameter("rePassword");

            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("account");
            int userId = new AccountDAO().getAccountID(acc.getUsername());

            // Check current password
            if (password.equals(acc.getPassword())) {

                // Validate new password against the pattern
                String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&\\*]{8,31}$";
                if (!newPassword.matches(passwordPattern)) {
                    request.setAttribute("error5", "New password must be 8-31 characters long, and contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
                    request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
                    return;
                }

                // Check if new password and verify password match
                if (rePassword.equals(newPassword)) {
                    new AccountDAO().updatePassword(userId, newPassword);
                    request.getSession().setAttribute("updateSuccess", "Password changed successfully!");
                    response.sendRedirect("account-profile?status=setting");
                } else {
                    request.setAttribute("error2", "New password and verify password do not match.");
                    request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("error", "Current password is incorrect.");
                request.getRequestDispatcher("screens/accountProfile.jsp").forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
