
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand Dashboard</title>
        <link rel="stylesheet" href="styles/create-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
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
            <div class="add-container">
                <div class="overview">
                    <div class="title">
                        <i class="bx bx-mobile-alt icon"></i>
                        <span class="text">Brand Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="add-form">
                            <div class="product-title">
                                <i class="bx bx-mobile-alt icon"></i>&nbsp;Add New Product
                            </div>
                            <hr />
                            <form class="add-product" action="admin-create-brand" method="POST">
                                <label for="name">Name</label><br />
                                <input type="text" id="name" name="name" required /><br />
                                <c:if test="${not empty error}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error}</h3><br/></c:if>
                                <br /><br />
                                <button type="submit" id="add-btn">Add Brand</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="js/dashboard.js"></script>
    </body>
</html>