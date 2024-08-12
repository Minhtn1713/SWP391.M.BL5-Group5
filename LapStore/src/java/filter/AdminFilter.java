package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

@WebFilter("/admin-*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        Account account = (session != null) ? (Account) session.getAttribute("account") : null;

        if (account != null && "1".equals(account.getRole_id())) {
            // User is logged in and has admin role, continue the request
            chain.doFilter(request, response);
        } else {
            // User is not logged in or does not have admin role, redirect to login page or error page
            request.getRequestDispatcher("sign-in").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}