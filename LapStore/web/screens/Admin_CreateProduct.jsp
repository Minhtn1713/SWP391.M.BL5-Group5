<%@page import="java.util.List"%>
<%@page import="model.Brand"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Dashboard</title>
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
            <h3 class="icon profile">Admin</h3>
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
                            <i class="bx bx-mobile-alt icon"></i>&nbsp;Add New Product
                        </div>
                        <hr />
                        <form class="add-product" action="admin-create-product" method="POST">
                            <label for="name">Name</label><br />
                            <input type="text" id="name" name="name" required /><br />
                            
                            <label for="brandId">BrandID</label><br />
                            <input type="number" id="brandId" name="brandId" required /><br />
                            
                            <label for="price">Price</label><br />
                            <input type="number" id="price" name="price" step="0.01" required /><br />
                            
                            <label for="processor">Processor</label><br />
                            <input type="text" id="processor" name="processor" /><br />
                            
                            <label for="graphic_card">Graphic Card</label><br />
                            <input type="text" id="graphic_card" name="graphic_card" /><br />
                            
                            <label for="screen_details">Screen Details</label><br />
                            <input type="text" id="screen_details" name="screen_details" /><br />
                            
                            <label for="size">Size</label><br />
                            <input type="text" id="size" name="size" /><br />
                            
                            <label for="weight">Weight</label><br />
                            <input type="number" id="weight" name="weight" step="0.01" /><br />
                            
                            <label for="operatingSystem">Operating System</label><br />
                            <input type="text" id="operatingSystem" name="operatingSystem" /><br />
                            
                            <label for="battery">Battery Life</label><br />
                            <input type="text" id="battery" name="battery" /><br />
                           
                        
                            <br />
                            <br />
                            <button type="submit" id="add-btn">Add Product</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <script src="js/dashboard.js"></script>
</body>
</html>
