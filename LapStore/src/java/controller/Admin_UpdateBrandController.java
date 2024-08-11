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
@WebServlet(name = "Admin_UpdateBrandController", urlPatterns = {"/admin-update-brand"})
public class Admin_UpdateBrandController extends HttpServlet {

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
            out.println("<title>Servlet Admin_UpdateBrandController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin_UpdateBrandController at " + request.getContextPath() + "</h1>");
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
        Brand brand = new Brand();
        BrandDAO dao = new BrandDAO();
        String id = (String) request.getParameter("id");

        int brand_id = 0;
        try {
            brand_id = Integer.parseInt(id);
        } catch (Exception e) {
            response.sendRedirect("/home");
        }
        brand = dao.getBrandById(brand_id);
        request.setAttribute("brand", brand);
        request.getRequestDispatcher("screens/Admin_UpdateBrand.jsp").forward(request, response);
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
        String id = (String) request.getParameter("id");
        int brand_id = Integer.parseInt(id);
        String name = (String) request.getParameter("name");
        
        Brand brand = new Brand(name);

        BrandDAO brandDAO = new BrandDAO();
        int succes = brandDAO.updateBrand(brand_id, brand);
        if (succes == 0) {
            response.sendRedirect("error-page");
        } else {
            List<Brand> list = brandDAO.getBrandList();
            request.setAttribute("list", list);
            response.sendRedirect("admin-brand-list");
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
