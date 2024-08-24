<%-- 
    Document   : Admin_Dashboard
    Created on : Aug 18, 2024, 4:38:32 PM
    Author     : lords
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="styles/dashboard.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <%
        HashMap<Integer, String> userMap = (HashMap<Integer, String>) request.getAttribute("listUser");
        HashMap<Integer, String> productMap = (HashMap<Integer, String>) request.getAttribute("listProduct");
       
    %>
    <body>
        <section class="dashboard">
            <div class="top">
                
                
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="dash-content">
                <div class="overview">
                    <div class="title">
                        <i class="bx bxs-dashboard"></i>
                        <span class="text">Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="box box1">
                            <i class="bx bx-user icon"></i>
                            <span class="text">Total User</span>
                            <span class="number">${totalUser}</span>
                        </div>
                        <div class="box box2">
                            <i class="bx bx-mobile-alt icon"></i>
                            <span class="text">Total Product</span>
                            <span class="number">${totalProduct}</span>
                        </div>
                        <div class="box box3">
                            <i class="bx bx-cart-alt icon"></i>
                            <span class="text">Total Revenue</span>
                            <span class="number">${totalRevenue} $</span>
                        </div>
                        <div class="box box4">
                            <i class="bx bx-cart icon"></i>
                            <span class="text">Total Order</span>
                            <span class="number">${totalOrder}</span>
                        </div>
                    </div>
        <div class="boxes" style="height: fit-content;">
                        <div class="recent-order">
                            <div class="recent-title">
                                <i class="bx bx-time icon"></i>Recent Order
                            </div>
                            <hr />
                            <div class="recent-content">
                                <div class="data client">
                                    <span class="data-title">Name</span>
                                    <c:forEach var="order" items="${recentOrders}">
            <span class="data-list">${order.name}</span>
        </c:forEach>
                                </div>
                                <div class="data orderID">
                                    <span class="data-title">Order ID</span>
                                    <c:forEach var="order" items="${recentOrders}">
            <span class="data-list">${order.id}</span>
        </c:forEach>
                                </div>
                                <div class="data order-date">
                                    <span class="data-title">Order date</span>
                                    <c:forEach var="order" items="${recentOrders}">
            <span class="data-list">${order.createdDate}</span>
        </c:forEach>
                                </div>
                                <div class="data order-status">
                                    <span class="data-title">Status</span>
                                    <c:forEach var="order" items="${recentOrders}">
            <span class="data-list">${order.statusName}</span>
        </c:forEach>
                                </div>
                                <div class="data ship">
                                    <span class="data-title">Price</span>
                                    <c:forEach var="order" items="${recentOrders}" >
            <span class="data-list">${order.totalPrice} $</span> 
        </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>


                                        <!-- ---End------->
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
    </body>
</html>