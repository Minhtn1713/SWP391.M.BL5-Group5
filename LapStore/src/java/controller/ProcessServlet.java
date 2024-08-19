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
@WebServlet(name = "ProcessServlet", urlPatterns = {"/process"})
public class ProcessServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    OrderDAO o = new OrderDAO();
    List<ProductVariant> list = o.getAllProductVariant();
    Cookie[] arr = request.getCookies();
    String txt = "";

    // Get the cart cookie value if it exists
    if (arr != null) {
        for (Cookie c : arr) {
            if (c.getName().equals("cart")) {
                txt += c.getValue();
                c.setMaxAge(0); // Delete the old cookie
                response.addCookie(c);
            }
        }
    }

    // Initialize the cart
    Cart cart = new Cart(txt, list);
    String num_raw = request.getParameter("num");
    String id_raw = request.getParameter("id");
    int id, num = 0;

    try {
        id = Integer.parseInt(id_raw);
        ProductVariant p = o.getProductVariantById(id);
        int numStore = p.getQuantity();
        num = Integer.parseInt(num_raw);

        if (num == -1 && cart.getQuantityById(id) <= 1) {
            // Remove the item if the quantity is 1 or less
            cart.removeItem(id);
        } else {
            if (num == 1 && cart.getQuantityById(id) >= numStore) {
                num = 0; // Prevent adding more than available stock
            }
            float price = p.getProductPrice() + p.getVariantPrice();
            Item t = new Item(p, num, price);
            cart.addItem(t);
        }
    } catch (NumberFormatException e) {
        // Handle potential parsing errors
    }

    // Check if the cart is empty
    List<Item> items = cart.getItems();
    txt = "";
    if (items.size() > 0) {
        // Build the cookie value string
        txt = items.get(0).getProductVariant().getId() + ":" +
              items.get(0).getQuantity();
        for (int i = 1; i < items.size(); i++) {
            txt += "-" + items.get(i).getProductVariant().getId() + ":" +
                   items.get(i).getQuantity();
        }

        // Set the new cart cookie
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2 * 24 * 60 * 60); // 2 days expiration
        response.addCookie(c);
    } else {
        // Cart is empty, remove the cookie
        Cookie c = new Cookie("cart", "");
        c.setMaxAge(0); // Delete the cookie
        response.addCookie(c);
    }

    // Pass the cart object to the JSP page
    request.setAttribute("cart", cart);
    request.getRequestDispatcher("screens/mycart.jsp").forward(request, response);
}

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO o = new OrderDAO();
    List<ProductVariant> list = o.getAllProductVariant();
    Cookie[] arr = request.getCookies();
    String txt = "";

    // Get the cart cookie value if it exists
    if (arr != null) {
        for (Cookie c : arr) {
            if (c.getName().equals("cart")) {
                txt += c.getValue();
                c.setMaxAge(0); // Delete the old cookie
                response.addCookie(c);
            }
        }
    }

    // Initialize the cart
    Cart cart = new Cart(txt, list);
    String id_raw = request.getParameter("id");
    int id, num = 0;

    try {
        id = Integer.parseInt(id_raw);
        cart.removeItem(id);
    } catch (NumberFormatException e) {
        // Handle potential parsing errors
    }

    // Check if the cart is empty
    List<Item> items = cart.getItems();
    txt = "";
    if (items.size() > 0) {
        // Build the cookie value string
        txt = items.get(0).getProductVariant().getId() + ":" +
              items.get(0).getQuantity();
        for (int i = 1; i < items.size(); i++) {
            txt += "-" + items.get(i).getProductVariant().getId() + ":" +
                   items.get(i).getQuantity();
        }

        // Set the new cart cookie
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2 * 24 * 60 * 60); // 2 days expiration
        response.addCookie(c);
    } else {
        // Cart is empty, remove the cookie
        Cookie c = new Cookie("cart", "");
        c.setMaxAge(0); // Delete the cookie
        response.addCookie(c);
    }

    // Pass the cart object to the JSP page
    request.setAttribute("cart", cart);
    request.getRequestDispatcher("screens/mycart.jsp").forward(request, response);
    }

    
}
