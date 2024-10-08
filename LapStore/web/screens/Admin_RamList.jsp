<%-- 
    Document   : Admin_ProductionList
    Created on : Jun 16, 2023, 4:51:03 PM
    Author     : 84834
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Production List</title>
        <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
        <section class="product">
      <div class="top">
        <div class="search-box">
          <i class="bx bx-search icon"></i>
          <input type="text" placeholder="Search here..." />
        </div>
        <h3 class="icon profile">Hi, Admin</h3>
      </div>
      <div class="product-content-container">
        <div class="overview">
          <div class="title">
            <i class="bx bx-palette icon"></i>
            <span class="text">Ram Dashboard</span>
          </div>

          <div class="boxes">
            <div class="recent-order">
              <div class="product-title">
                <i class="bx bx-time icon"></i>&nbsp;Ram
              </div>
              <div class="product-add"><a  href="create-ram">Add new Ram</a></div>
              <div class="product-content">
                <table>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Ram</th>
                      <th>Price Bonus</th>
                      <th>Edit</th>
                      <th>Status</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${ram}" var="r">
                    <tr>
                      <td>${r.id}</td>
                      <td>${r.name}</td>
                      <td>${r.priceBonus}$</td>
                      <td><a href="update-ram?id=${r.id}"><i class="bx bxs-edit-alt icon"></i></a></td>
                      <td><c:if test="${r.status == 1}"><a href="admin-hide-ram?id=${r.id}&sta=2"><i class='bx bx-show-alt icon'></i></a></c:if>
                      <c:if test="${r.status != 1}"><a href="admin-hide-ram?id=${r.id}&sta=1"><i class='bx bx-low-vision icon' ></i></a></c:if></td>
                    </tr>
                    </c:forEach>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
        <script src="js/dashboard.js"></script>
    </body>
</html>