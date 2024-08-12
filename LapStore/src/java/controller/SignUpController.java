/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AccountDAO;
import dao.SecurityDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.SecurityQuestion;

/**
 *
 * @author lords
 */
public class SignUpController extends HttpServlet {
   
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
            out.println("<title>Servlet SignUpController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("screens/signUp.jsp").forward(request, response);
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
        UserDAO userDao = new UserDAO();
        SecurityDAO securityDao = new SecurityDAO();
        //Lay du lieu tu form nguoi dung nhap        
        String userName = (String) request.getParameter("username");
        String passWord = (String) request.getParameter("password");
        int ques_id = -1;
        try {
            ques_id = Integer.parseInt(request.getParameter("question"));
        } catch (Exception e) {
            System.out.println(e);
        }
        String name = request.getParameter("name");
        String answer = request.getParameter("answer");
        String phone = request.getParameter("phone");
        //Kiem tra xem tai khoan do da duoc tao ra hay chua    
        int succes = accDao.getAccountID(userName);
        // Neu tai khoan da ton tai thi se tra ve voi 1 mess    
        if (succes != 0) {
            ArrayList<SecurityQuestion> list = accDao.getSecurityQuestion();
            request.setAttribute("quest", list);
            request.setAttribute("mess", "This username already exist");
            request.getRequestDispatcher("screens/signUp.jsp").forward(request, response);
        } //Neu chua duoc tao thi se tao tai khoan tu username va password    
        else {
            succes = accDao.createAccount(userName, passWord);
            // Neu tao that bai thi se quay tro ve trang dang ki cung voi tin nhan loi thuoc ve he thong           
            if (succes == 0) {
                ArrayList<SecurityQuestion> list = accDao.getSecurityQuestion();
                request.setAttribute("quest", list);
                request.setAttribute("mess", "We so sorry, may be website has some problem!");
                request.getRequestDispatcher("screens/signUp.jsp").forward(request, response);
            } //Neu chay thanh cong thi se tiep tuc tao truong thong tin trong bang user
            else {
                int id = accDao.getAccountID(userName);
                succes = userDao.createUser(id, phone, name);
                // Neu tao that bai thi se quay tro ve trang dang ki cung voi tin nhan loi thuoc ve he thong
                if (succes == 0) {
                    ArrayList<SecurityQuestion> list = accDao.getSecurityQuestion();
                    request.setAttribute("quest", list);
                    request.setAttribute("mess", "We are sorry, may be website has some problem!");
                    request.getRequestDispatcher("screens/signUp.jsp").forward(request, response);
                } //Neu tao thanh cong se tao them 1 truong trong bang Security
                else {
                    if (ques_id != -1) {
                        succes = securityDao.insertSecurity(ques_id, id, answer);
                        request.setAttribute("mess", ques_id + "  " + id + "  " + answer);
                        if (succes == 1) {
                            response.sendRedirect("sign-in");
                        }
                        
                    }
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
