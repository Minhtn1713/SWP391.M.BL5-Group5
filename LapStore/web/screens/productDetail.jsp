<%@page import="model.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Details</title>
        <!-- Include your CSS files here -->
        <link rel="stylesheet" href="styles/product-detail.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../screens/navbar.jsp"></jsp:include>
        
        <div class="product-container">
            <%
                Product product = (Product) request.getAttribute("productDetail");
            %>
            <h1 class="product-name">${product.name}</h1>
            <div class="product-details">
                <img src="img/${product.img}" alt="${product.name}" class="product-image"/>
                <div class="product-info">
                    <p><strong>Price:</strong> $${product.price}</p>
                    <p><strong>Processor:</strong> ${product.processor}</p>
                    <p><strong>Screen:</strong> ${product.screen_details}</p>
                    <p><strong>Size:</strong> ${product.size}</p>
                    <p><strong>Operating System:</strong> ${product.operatingSystem}</p>
                    <p><strong>Battery:</strong> ${product.battery}</p>
                    <p><strong>Graphic Card:</strong> ${product.graphic_card}</p>
                    <p><strong>Description:</strong> ${product.description}</p>
                </div>
            </div>
            <a href="add-to-cart?id=${product.id}" class="add-to-cart-btn">Add to Cart</a>
        </div>

        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>
