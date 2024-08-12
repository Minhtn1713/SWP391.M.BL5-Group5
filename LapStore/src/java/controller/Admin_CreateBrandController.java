/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Brand;

/**
 *
 * @author FPT
 */
@WebServlet(name = "Admin_CreateBrandController", urlPatterns = {"/admin-create-brand"})
public class Admin_CreateBrandController extends HttpServlet {

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
            out.println("<title>Servlet Admin_CreateBrandController</title>");
            out.println("</head>");
            out.println("<body>");
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
        request.getRequestDispatcher("screens/Admin_CreateBrand.jsp").forward(request, response);
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
        int success = 0;
        String error = null;

        String name = request.getParameter("name");

        
        BrandDAO brandDAO = new BrandDAO();
        
    
        // Create the product in the database
        if (name == null || name.trim().isEmpty()) {
            error = "Brand name cannot be null or empty.";
        } else {

            List<Brand> existingBrands = brandDAO.getAllBrands();
            for (Brand b : existingBrands) {
                if (b.getName().equalsIgnoreCase(name.trim())) {
                    error = "Brand name already exists.";
                    break;
                }
            }
        }

        if (error != null) {

            request.setAttribute("error", error);
            request.getRequestDispatcher("screens/Admin_CreateBrand.jsp").forward(request, response);
        } else {
                   // Create a Product object
        Brand brand1 = new Brand(name);

        // Initialize ProductDAO
        BrandDAO brand1DAO = new BrandDAO();
        
        success = brand1DAO.createBrand(brand1);
            if (success != 0) {

                List<Brand> brandList = brandDAO.getAllBrands();
                request.setAttribute("list", brandList);
                response.sendRedirect("admin-brand-list");
            } else {

                request.getRequestDispatcher("error-page.jsp").forward(request, response);
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