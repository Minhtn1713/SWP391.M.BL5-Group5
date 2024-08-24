/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cart;
import model.Item;
import model.ProductVariant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO o= new OrderDAO();
        List<ProductVariant> list = o.getAllProductVariant();
        
        
        //temp
        //Cookie c=new Cookie("cart","2:1-3:2");
       //c.setMaxAge(2*24*60*60);
        //response.addCookie(c);
        //temp
        Cookie[] arr=request.getCookies();

        String txt="";
        if(arr!=null){
            for(Cookie ck:arr){
                if(ck.getName().equals("cart")){
                    txt+=ck.getValue();
                }
            }
        }
        Cart cart = new Cart(txt,list);
        List<Item> listItem= cart.getItems();
        int n;
        if(listItem!=null){
            n=listItem.size();
        }else{
            n=0;
        }
        request.setAttribute("size", n);
        request.setAttribute("cart",cart);
        request.getRequestDispatcher("screens/mycart.jsp").forward(request, response);
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}