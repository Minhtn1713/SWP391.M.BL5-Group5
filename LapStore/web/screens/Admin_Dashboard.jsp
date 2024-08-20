<%-- 
    Document   : Admin_Dashboard
    Created on : Aug 18, 2024, 4:38:32 PM
    Author     : lords
--%>

<%@page import="model.Comment"%>
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
        ArrayList<Comment> listComment = (ArrayList<Comment>) request.getAttribute("listComment");
    %>
    <body>
        <section class="dashboard">
            <div class="top">
                <div class="search-box">
                    <i class="bx bx-search icon"></i>
                    <input type="text" placeholder="Search here..." />
                </div>
                <i class='bx bx-bell icon notify' ></i>
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
                            <span class="text">Users</span>
                            <span class="number">${totalUser}</span>
                        </div>
                        <div class="box box2">
                            <i class="bx bx-mobile-alt icon"></i>
                            <span class="text">Products</span>
                            <span class="number">${totalProduct}</span>
                        </div>
                        <div class="box box3">
                            <i class="bx bx-cart-alt icon"></i>
                            <span class="text">Sale</span>
                            <span class="number">${totalSale}</span>
                        </div>
                        <div class="box box4">
                            <i class="bx bx-cart icon"></i>
                            <span class="text">Orders</span>
                            <span class="number">${totalOrder}</span>
                        </div>
                    </div>


                                        <!-- ---End------->
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
    </body>
</html>