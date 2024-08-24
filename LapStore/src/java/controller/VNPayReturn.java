/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dao.TransactionDAO;
import dao.UserDAO;
import model.Account;
import java.io.PrintWriter;
import model.Config;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import model.User;

/**
 *
 * @author acer
 */
@WebServlet(name = "VNPayReturn", urlPatterns = {"/vnpay_return"})
public class VNPayReturn extends HttpServlet {

    private void payment(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("account");
                UserDAO user = new UserDAO();
                User u = user.getUserById_2(acc.getId());
                String id = request.getParameter("vnp_TxnRef");
                String moneyStr = request.getParameter("vnp_Amount");
                long money = Long.parseLong(moneyStr);
                TransactionDAO d = new TransactionDAO();
                if (!d.isTransactionProcessed(id)) {
                    long updateBalance = (long) (u.getBalance() + money / 100);
                    user.insertPayment(acc.getId(), updateBalance);
                    d.markTransactionAsProcessed(id); // Mark transaction as processed

                    // Update balance in session
                    u.setBalance(updateBalance);
                    session.setAttribute("acc", acc);

                    request.setAttribute("amount", money / 100);
                    request.getRequestDispatcher("/screens/StatusTransaction.jsp").forward(request, resp);
                } else {
                    // Transaction already processed
                    request.setAttribute("message", "Transaction has already been processed.");
                    request.getRequestDispatcher("/screens/StatusTransaction.jsp").forward(request, resp);
                }

            } else {
                resp.getWriter().print("Transaction is not successful");
            }

        } else {
            resp.getWriter().print("invalid signature");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        payment(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        payment(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
