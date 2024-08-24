package controller;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteBlogController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            try {
                int blogId = Integer.parseInt(id);
                BlogDAO blogDAO = new BlogDAO();
                int result = blogDAO.deleteBlog(blogId);
                if (result > 0) {
                    response.sendRedirect("admin-blog-category"); 
                } else {
                    response.getWriter().println("Error deleting blog.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid blog ID.");
            }
        } else {
            response.getWriter().println("No blog ID provided.");
        }
    }
}
