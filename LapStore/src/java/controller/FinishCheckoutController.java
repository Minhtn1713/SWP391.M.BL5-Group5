/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;
import model.ProductVariant;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FinishCheckoutController", urlPatterns = {"/finish"})
public class FinishCheckoutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FinishCheckoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinishCheckoutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO o = new OrderDAO();
        Cookie[] arr=request.getCookies();
        List<ProductVariant> list = o.getAllProductVariant();

        String txt="";
        if(arr!=null){
            for(Cookie ck:arr){
                if(ck.getName().equals("cart")){
                    txt+=ck.getValue();
                }
            }
        }
        if(!txt.isEmpty() && txt!=null){
        Cart cart = new Cart(txt,list);
       
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        User u = null;
        if (a != null) {
            UserDAO user = new UserDAO();
            u = user.getUserById_1(a.getId());
        }
        
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        if(payment.equals("Pay on Receipt")){
        float total_price = cart.getTotalMoney();
        List<Item> listItem= cart.getItems();
        int success1 = o.createOrder(u, name, phone, address, total_price, 3);
        Order order = o.getOrderById_Dung(o.getOrderId());
        if(success1==1){
            for(Item i : listItem){
                int success2= o.createOrderDetail(order, i.getProductVariant(), i.getQuantity());
                int success3 = o.setProductVariantId(o.getProductVariantQuantity(i.getProductVariant().getId())-i.getQuantity(), i.getProductVariant().getId());
            }
            response.sendRedirect("cart");
        }
        } 
                
        
    }
}
} 