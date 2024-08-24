package controller;

import dao.BlogDAO;
import model.Blog;
import model.SubBlog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.BlogCategory;

public class UpdateBlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("id"));
        BlogDAO bd = new BlogDAO();
        Blog blog = bd.getListBlogById(blogId);
        List<SubBlog> subBlogs = bd.getListSubBlogbyBlogId(blogId);
        List<BlogCategory> listCate = bd.getListBlogCategory();
        
        request.setAttribute("blog", blog);
        request.setAttribute("subBlogs", subBlogs);
        request.setAttribute("listCate", listCate);
        
        request.getRequestDispatcher("screens/Admin_UpdateBlogDetails.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Initialize variables with default values
    int blogId = 0;
    int cateId = 0;
    int quantitySub = 0;
    String title = "";
    String coverImg = "";

    // Safely parse parameters
    try {
        String blogIdStr = request.getParameter("blogId");
        if (blogIdStr != null && !blogIdStr.isEmpty()) {
            blogId = Integer.parseInt(blogIdStr);
        }
        
        String cateIdStr = request.getParameter("cate");
        if (cateIdStr != null && !cateIdStr.isEmpty()) {
            cateId = Integer.parseInt(cateIdStr);
        }
        
        String quantitySubStr = request.getParameter("quantitySub");
        if (quantitySubStr != null && !quantitySubStr.isEmpty()) {
            quantitySub = Integer.parseInt(quantitySubStr);
        }
        
        title = request.getParameter("title") != null ? request.getParameter("title") : "";
        coverImg = request.getParameter("cover") != null ? request.getParameter("cover") : "";

    } catch (NumberFormatException e) {
        e.printStackTrace(); // Log the exception
        // Handle error - return or redirect to an error page
        response.sendRedirect("error-page.jsp?error=Invalid input format");
        return;
    }
    
    BlogDAO bd = new BlogDAO();
    
    // Handle cover image upload if necessary
    // String coverImgPath = handleFileUpload(request); // Implement this method based on your file handling strategy
    
    bd.updateBlogById(blogId, title, 1, cateId, coverImg);
    
    // Update or create SubBlog entries
    for (int i = 1; i <= quantitySub; i++) {
        String header = request.getParameter("header" + i);
        String content = request.getParameter("content" + i);
        String img = request.getParameter("img" + i);
        if (header != null && content != null) {
            // Implement logic to update or insert SubBlog
            bd.updateOrInsertSubBlog(blogId, header, content, img, i);
        }
    }
    
    response.sendRedirect("admin-blog-category");
}

}
