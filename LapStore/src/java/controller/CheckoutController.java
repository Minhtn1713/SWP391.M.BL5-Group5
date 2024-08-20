
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
import model.ProductVariant;
import model.User;


@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id;
        String name,phone,address;
        Account a = (Account) session.getAttribute("account");
        if(a!=null){
        UserDAO user = new UserDAO();
        User u = user.getUserById_1(a.getId());
        id = a.getId();
        name = u.getFullName();
        phone = u.getPhone();
        address = u.getAddress();
        }else{
            name="";
            phone="";
            address="";
        }
        Cookie[] arr=request.getCookies();
        OrderDAO o= new OrderDAO();
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
        
        List<Item> listItem= cart.getItems();
        int n;
        if(listItem!=null){
            n=listItem.size();
        }else{
            n=0;
        }
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("size", n);
        request.setAttribute("cart",cart);
        request.getRequestDispatcher("screens/checkout.jsp").forward(request, response);
        }else{
            
            response.sendRedirect("/LapStore_main/cart");
        }
    }

    
   

}