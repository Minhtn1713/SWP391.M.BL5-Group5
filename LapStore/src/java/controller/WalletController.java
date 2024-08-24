/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "WalletController", urlPatterns = {"/wallet"})
public class WalletController extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if(a!=null){
        UserDAO user = new UserDAO();
        User u = user.getUserById_2(a.getId());
        request.setAttribute("balance", u.getBalance());
        request.getRequestDispatcher("screens/myWallet.jsp").forward(request, response);
        }else{
            response.sendRedirect("/LapStore_main/sign-in");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   

}
