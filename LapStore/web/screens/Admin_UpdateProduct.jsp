<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="styles/create-product.css" type="text/css"/>
    <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
</head>
<body>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <section class="product">
        <div class="top">
            <h3 class="icon profile">Admin</h3>
        </div>
        <div class="add-container">
            <div class="overview">
                <div class="title">
                    <span class="text">Update Product</span>
                </div>
                <div class="boxes">
                    <div class="add-form">
                        <div class="product-title">
                            <i class="bx bx-mobile-alt icon"></i>&nbsp;Update Product
                        </div>
                        <hr />
                        <form class="add-product" action="admin-update-product" method="POST" >
                            <input type="hidden" name="id" value="${product.id}" />
                            
                            <label for="name">Name</label><br />
                            <input type="text" id="name" name="name" value="${product.name}" required /><br />
                            
                            <label for="img">Image</label><br />
                            <input type="file" id="img" name="img" /><br />
                            <input type="hidden" name="current_image" value="${product.img}" />
                            
                            <label for="brandId">Brand</label><br />
                           <select id="brandId" name="brandId" required>
                            <c:forEach var="brand" items="${brands}">
                              <option value="${brand.id}">${brand.name}</option>
                            </c:forEach>
                            </select><br /> 
                            
                            <label for="price">Price</label><br />
                            <input type="number" id="price" name="price" step="0.01" value="${product.price}" required /><br />
                            
                            <label for="processor">Processor</label><br />
                            <input type="text" id="processor" name="processor" value="${product.processor}" required/><br />
                            
                            <label for="graphic_card">Graphic Card</label><br />
                            <input type="text" id="graphic_card" name="graphic_card" value="${product.graphic_card}" required/><br />
                            
                            <label for="screen_details">Screen Details</label><br />
                            <input type="text" id="screen_details" name="screen_details" value="${product.screen_details}" required/><br />
                            
                            <label for="size">Size</label><br />
                            <input type="text" id="size" name="size" value="${product.size}" required/><br />
                            
                            <label for="weight">Weight</label><br />
                            <input type="number" id="weight" name="weight" step="0.01" value="${product.weight}" required/><br />
                            
                            <label for="operatingSystem">Operating System</label><br />
                            <input type="text" id="operatingSystem" name="operatingSystem" value="${product.operatingSystem}" required/><br />
                            
                            <label for="battery">Battery Life</label><br />
                            <input type="text" id="battery" name="battery" value="${product.battery}" required/><br />
                            
                            <label for="description">Description</label><br />
                            <textarea id="description" name="description" required rows="5">${product.description}</textarea><br />
                            
                            <br/>
                            <br/>
                            <button type="submit" id="update-btn">Update Product</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
                             <script src="js/dashboard.js"></script>
</body>
</html>
