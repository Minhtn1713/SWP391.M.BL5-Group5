package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Admin_DeleteProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO productDao = new ProductDAO();

        // Retrieve the product ID and status from the request parameters and convert them to int
        int id = Integer.parseInt(req.getParameter("id"));
        int status = Integer.parseInt(req.getParameter("sta"));

        // Update the product status
        int success = productDao.updateProductStatus(id, status);

        // Redirect based on success
        if (success != 0) {
            resp.sendRedirect("/LapStore_main/admin-production-list");
        } else {
            // Redirect to an error page if the update fails (you need to implement the error page)
            resp.sendRedirect("error-page");
        }
    }
}