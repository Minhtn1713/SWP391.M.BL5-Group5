<%-- 
    Document   : Admin_Dashbord
    Created on : Jun 10, 2023, 2:22:26 PM
    Author     : 84834
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Dashboard</title>
        <link rel="stylesheet" href="styles/create-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
    </head>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <body>
        <section class="product">
            <div class="top">
                <div class="search-box">
                    <i class="bx bx-search icon"></i>
                    <input type="text" placeholder="Search here..." />
                </div>
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="add-container">
                <div class="overview">
                    <div class="title">
                        <i class="bx bx-mobile-alt icon"></i>
                        <span class="text">Product Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="add-form">
                            <div class="product-title">
                                <i class="bx bx-mobile-alt icon"></i>&nbsp;Update Product
                            </div>
                            <hr />
                            <form class="add-product" action="admin-update-product" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="id" id="id" value="${product.id}">
                                <label for="name">Name</label><br />
                                <input type="text" id="name" name="name" value="${product.name}" required /><br />
                                <label for="price">Price:</label><br />
                                <input type="number" id="price" name="price" step="0.01" value="${product.price}" required /><br />
                                <label for="processor">Processor</label><br />
                                <input type="text" id="processor" name="processor" value="${product.processor}" required /><br />
                                <label for="screen_details">Screen Details</label><br />
                                <input type="text" id="screen_details" name="screen_details" value="${product.screen_details}" required /><br />
                                <label for="size">Size</label><br />
                                <input type="text" id="size" name="size" value="${product.size}" required /><br />
                                <label for="operating_system">Operating System</label><br />
                                <input type="text" id="operating_system" name="operatingSystem" value="${product.operatingSystem}" required /><br />
                                <label for="battery">Battery</label><br />
                                <input type="text" id="battery" name="battery" value="${product.battery}" required /><br />
                                <label for="status">Status</label><br />
                                <input type="number" id="status" name="status" value="${product.status}" required /><br />
                                <label for="brandId">Brand ID</label><br />
                                <input type="number" id="brandId" name="brandId" value="${product.brandId}" required /><br />
                                <label for="weight">Weight</label><br />
                                <input type="number" id="weight" name="weight" step="0.01" value="${product.weight}" required /><br />
                                <label for="graphic_card">Graphic Card</label><br />
                                <input type="text" id="graphic_card" name="graphic_card" value="${product.graphic_card}" required /><br />
                                <label for="description">Description</label><br />
                                <textarea id="description" name="description" rows="10">${product.description}</textarea><br />
                                <label for="img">Image</label><br />
                                <input type="hidden" name="current-image" value="${product.imgId}">
                                <input type="file" id="img" name="img" style="margin-bottom: 70px" /><br />
                                <button type="submit" id="add-btn">Update</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
    </body>
</html>
