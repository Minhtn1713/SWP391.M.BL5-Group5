/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;

/**
 *
 * @author hieu
 */
public class Admin_CreateProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("screens/Admin_CreateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int success = 0;
         
        Product pro = new Product(0, req.getParameter("name"), Float.parseFloat(req.getParameter("price"))
                , req.getParameter("processor"), req.getParameter("screen_details"), req.getParameter("size")
                , req.getParameter("operating_system"), 
                req.getParameter("battery_life"),
                Float.parseFloat(req.getParameter("weight")),
                req.getParameter("graphic_card"), 1 );
        ProductDAO proDao = new ProductDAO();
        success = proDao.createProduct(pro);
        if(success != 0){
            List<Product> list = proDao.getProductList();
            req.setAttribute("list", list);
            resp.sendRedirect("admin-production-list");
        }
        else{
            req.getRequestDispatcher("error-page").forward(req, resp);
        }
    }

}
