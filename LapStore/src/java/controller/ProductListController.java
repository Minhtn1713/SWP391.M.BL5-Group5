package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.BrandDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.Brand;
import model.Product;

/**
 *
 * @author FPT
 */
@WebServlet(urlPatterns = {"/ProductListController"})
public class ProductListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO proDao = new ProductDAO();
        BrandDAO bDao = new BrandDAO();
        List<Product> allProducts = proDao.getProductList();
        List<Brand> allBrands = bDao.getAllBrands();

        // Get search parameter
        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            allProducts = allProducts.stream()
                    .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Get status parameter
        String status = request.getParameter("status");
        if ("in-stock".equals(status)) {
            allProducts = allProducts.stream()
                    .filter(p -> p.getStatus() == 1)
                    .collect(Collectors.toList());
        } else if ("out-of-stock".equals(status)) {
            allProducts = allProducts.stream()
                    .filter(p -> p.getStatus() == 0)
                    .collect(Collectors.toList());
        }

        // Get sorting parameter
        String sortBy = request.getParameter("sort-by");
        if (sortBy == null) {
            sortBy = "default"; // Default sorting
        }

        // Sort products based on the sort-by parameter
        switch (sortBy) {
            case "price-asc":
                allProducts.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                break;
            case "price-desc":
                allProducts.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                break;
            case "weight-asc":
                allProducts.sort((p1, p2) -> Double.compare(p1.getWeight(), p2.getWeight()));
                break;
            case "weight-desc":
                allProducts.sort((p1, p2) -> Double.compare(p2.getWeight(), p1.getWeight()));
                break;
            case "default":
                // No additional sorting needed, display products as they are
                break;
            default:
                // Fallback for any unsupported sort-by value
                break;
        }

        String priceRange = request.getParameter("price");
        if (priceRange != null && !priceRange.trim().isEmpty()) {
            switch (priceRange) {
                case "under-500":
                    allProducts = allProducts.stream()
                            .filter(p -> p.getPrice() < 500)
                            .collect(Collectors.toList());
                    break;
                case "500-1000":
                    allProducts = allProducts.stream()
                            .filter(p -> p.getPrice() >= 500 && p.getPrice() <= 1000)
                            .collect(Collectors.toList());
                    break;
                case "1000-1500":
                    allProducts = allProducts.stream()
                            .filter(p -> p.getPrice() >= 1000 && p.getPrice() <= 1500)
                            .collect(Collectors.toList());
                    break;
                case "1500-2000":
                    allProducts = allProducts.stream()
                            .filter(p -> p.getPrice() >= 1500 && p.getPrice() <= 2000)
                            .collect(Collectors.toList());
                    break;
                case "2000-2500":
                    allProducts = allProducts.stream()
                            .filter(p -> p.getPrice() >= 2000 && p.getPrice() <= 2500)
                            .collect(Collectors.toList());
                    break;
                case "over-2500":
                    allProducts = allProducts.stream()
                            .filter(p -> p.getPrice() > 2500)
                            .collect(Collectors.toList());
                    break;
            }
        }

        String[] selectedBrands = request.getParameterValues("brand");
        if (selectedBrands != null && selectedBrands.length > 0) {
            List<Integer> brandIds = Arrays.stream(selectedBrands)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            allProducts = allProducts.stream()
                    .filter(p -> brandIds.contains(p.getBrandId()))
                    .collect(Collectors.toList());
        }

        // Pagination parameters
        int itemsPerPage = 6; // Default to 6 items per page
        String itemsPerPageParam = request.getParameter("items-per-page");
        if (itemsPerPageParam != null && !itemsPerPageParam.trim().isEmpty()) {
            try {
                itemsPerPage = Integer.parseInt(itemsPerPageParam);
            } catch (NumberFormatException e) {
                itemsPerPage = 6; // Fallback to 6 items per page if the parameter is invalid
            }
        }

        int page = 1; // Default to the first page
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Fallback to the first page if the parameter is invalid
            }
        }

        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, totalProducts);

        List<Product> productsToShow = allProducts.subList(start, end);

        request.setAttribute("brands", allBrands);

        request.setAttribute("list", productsToShow);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("screens/productList.jsp").forward(request, response);
    }
}
