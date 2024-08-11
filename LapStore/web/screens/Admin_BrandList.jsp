
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Brand"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand List</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
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
                        <input type="text" placeholder="Search brand by name here..." />
                    </div>
                    <h3 class="icon profile">Hi, Admin</h3>
                </div>
                <div class="product-content-container">
                    <div class="overview">
                        <div class="title">
                            <i class="bx bx-mobile-alt icon"></i>
                            <span class="text">Brand Dashboard</span>
                        </div>
                        <div class="boxes">
                            <div class="recent-order">
                                <div class="product-title">
                                    <i class="bx bx-mobile-alt icon"></i>&nbsp;Brand List
                                </div>
                                <div class="product-add">
                                    <a href="admin-create-brand">Add new Brand</a>
                                </div>
                                <div class="product-content">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>                                        
                                                <th>Name</th>   
                                                <th>Edit</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${list}" var="p">
                                            <tr>
                                                <td>${p.id}</td>
                                                <td>${p.name}</td>                                                                                   
                                                <td>
                                                    <a href="admin-update-brand?id=${p.id}">
                                                        <i class="bx bxs-edit-alt icon"></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="admin-delete-brand?id=${p.id}">
                                                        <i class="bx bx-low-vision icon"></i>
                                                    </a>
                                                </td>

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