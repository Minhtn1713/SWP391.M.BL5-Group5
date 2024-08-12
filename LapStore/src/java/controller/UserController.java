/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UserController", urlPatterns = {"/user", "/user/toggle", "/user/update", "/user/save","/user/add"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String ACTION = request.getServletPath();

        switch (ACTION) {

            case "/user" -> {
                getAllUsers(request, response);
            }
            case "/user/toggle" -> {
                toggle(request, response);
            }
            case "/user/update" -> {
                loadUserDetails(request, response);
            }
            case "/user/add" -> {
                request.getRequestDispatcher("/screens/admin-newUser.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String ACTION = request.getServletPath();

        switch (ACTION) {
            case "/user/save" -> {
                updateUser(request, response);
            }
            case "/user/add" -> {
                addUser(request, response);
            }

        }
    }

    private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO u = new UserDAO();
        String indexPage = request.getParameter("index");

        // Initialize currentPage to 1 if indexPage is null or not a number
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(indexPage);
        } catch (NumberFormatException e) {
            // Handle parsing exception (if any), default to page 1
        }
        // Ensure currentPage is within valid range
        if (currentPage < 1) {
            currentPage = 1;
        }
        // Fetch blog posts from repository

        List<User> listUsers = u.getAllUser();

        // Iterate through each blog to fetch and set categories
        //for (User u : listUsers) {
        //List<String> cats = blogRepo.findAll(blog.getId());
        //blog.setCategories(cats);
        //}
        String search = request.getParameter("txtSearch");
        List<User> blogSearch = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            for (User b : listUsers) {
                if (b.getUserName().toLowerCase().contains(search.toLowerCase())) {
                    blogSearch.add(b);
                }

            }
            // Filter blogs by category
            listUsers = blogSearch;
        } else {
            // Fetch all blogs
            listUsers = u.getAllUser();
            // Iterate through each blog to fetch and set categories
            //for (U blog : listBlogs) {
            //List<String> cats = blogRepo.findAll(blog.getId());
            //blog.setCategories(cats);
            //}
        }
        // Calculate pagination variables
        int pageSize = 6; // Number of blogs per page
        int totalBlogs = listUsers.size();
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
        if (totalPages == 0) {
            totalPages++;
        }
        // Adjust currentPage if it exceeds total pages
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        // Calculate start index for slicing the list
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalBlogs);
        // Slice the list to get blogs for the current page
        List<User> paginatedBlogs = listUsers.subList(startIndex, endIndex);
        request.setAttribute("txtS", search);
        request.setAttribute("users", paginatedBlogs);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("endP", totalPages);
        request.getRequestDispatcher("/screens/admin-user.jsp").forward(request, response);
    }

    private void toggle(HttpServletRequest request, HttpServletResponse response) {
        UserDAO u = new UserDAO();
        int id = Integer.parseInt(request.getParameter("id"));

        try {

            boolean isToggled = u.toggleById(id);
            request.setAttribute("currentSite", "/user");
            if (isToggled) {
                response.sendRedirect("/LapStore/user?toggled=successful");
            } else {
                response.sendRedirect("/LapStore/user?toggled=failed");
            }
        } catch (NumberFormatException | IOException ex) {
        }
    }

    private void loadUserDetails(HttpServletRequest request, HttpServletResponse response) {
        UserDAO u = new UserDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            User user = u.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/screens/admin-userDetails.jsp").forward(request, response);

        } catch (Exception ex) {
        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
                String username = request.getParameter("username");

        
        int role = Integer.parseInt(request.getParameter("role"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        int status = Integer.parseInt(request.getParameter("status"));
        //String status = String.valueOf(request.getParameter("status"));
        UserDAO u=new UserDAO();
        User update = new User( id, fullName, null, phone,address,gender, null,role,status);
        
        if (isValidFullName(fullName) && isValidPhone(phone) && isValidAddress(address) ) {
        User isUpdated = u.updateUser(update);
        if (isUpdated != null) {
            response.sendRedirect("/LapStore/user/update?id="+id+"&updated=yes");
        } else {
            response.sendRedirect("/LapStore/user/update?id="+id+"&updated=no");
        }
        }else {
            response.sendRedirect("/LapStore/user/update?id="+id+"&updated=no");
        }
    }

    public static boolean isValidFullName(String fullName) {
        // Full name should not be null or empty, and must match the regex for alphabetic characters and spaces.
        return fullName != null && fullName.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean isValidPhone(String phone) {
    // Phone number should not be null, empty, and should match the pattern for 9 or 10 digits.
    return phone != null && phone.matches("^\\d{9,10}$");
}
    public static boolean isValidEmail(String email) {
    // Email should not be null, empty, and should follow a basic email pattern.
    String emailPattern = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    return email != null && !email.isEmpty() && email.matches(emailPattern);
}

    public static boolean isValidAddress(String address) {
        // Address should not be null or empty, and should match a basic pattern for common address components.
        return address != null && address.matches("^[a-zA-Z0-9\\s,.-]+$");
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));
        int status = Integer.parseInt(request.getParameter("status"));
        UserDAO u = new UserDAO();
        boolean add = u.usernameExists(username);
        if(add || !isValidEmail(email) || username == null || password == null ||username.isEmpty()||password.isEmpty() ){
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("role", role);
            request.setAttribute("status", status);
            if(add||username == null||username.isEmpty()){
                request.setAttribute("errorUsername", "Invalid username");
            }else{
                request.setAttribute("errorUsername", null);
            }
            if(!isValidEmail(email)){
                request.setAttribute("errorEmail", "Invalid Email");
            }else{
                request.setAttribute("errorEmail", null);
            }
            if(password == null||password.isEmpty()){
                request.setAttribute("errorPass", "Please input a password");
            }else{
                request.setAttribute("errorPass", null);
            }
            request.getRequestDispatcher("/screens/admin-newUser.jsp").forward(request, response);
        }else{
            boolean successUser = u.add(email);
            if(successUser){
            int id=u.getAllUser().size()+1;
            boolean successAcc = u.add(id,username, password, role, status);
            if(successUser && successAcc){
                response.sendRedirect("/LapStore/user/add?added=successful");
            }}else{
                request.setAttribute("error", "Failed to add user to the database.");
                request.getRequestDispatcher("/screens/admin-newUser.jsp").forward(request, response);
            }
        }
    }

}
