<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lap Store - Feedback Products</title>
    <!-- CSS -->
    <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
    <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
    <link rel="stylesheet" href="styles/related-product.css" type="text/css"/>
    <link rel="stylesheet" href="styles/feedback-detail.css" type="text/css"/>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/> <!-- New CSS file -->
    <!-- Font family -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet"/>
    <!-- Favicon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
</head>
<body>
    <header class="header-container">
        <jsp:include page="../screens/navbar.jsp"></jsp:include>
    </header>

    <main class="main-content">
        <h1>Products Available for Feedback</h1>

        <c:if test="${not empty productList}">
            <ul class="product-list">
                <c:forEach var="product" items="${productList}">
                    <li class="product-item">
                        <img src="img/${product.img}" alt="${product.name}" class="product-img" />
                        <div class="product-details">
                            <h2 class="product-name">${product.name}</h2>
                            <p class="product-description">${product.description}</p>
                            <p class="product-price">Price: ${product.price}</p>
                            <a href="feedback?productId=${product.id}" class="view-feedback-btn">View Feedback</a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${empty productList}">
            <p class="no-products">No products available for feedback at the moment.</p>
        </c:if>
    </main>

    <footer class="footer-container">
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </footer>
</body>
</html>
