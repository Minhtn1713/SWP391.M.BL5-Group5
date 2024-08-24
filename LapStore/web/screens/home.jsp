<%-- 
    Document   : homePage
    Created on : Aug 10, 2024, 10:42:34 PM
    Author     : lords
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>LapStore</title>
    <!-- css -->
    <link rel="stylesheet" href="styles/home.css" type="text/css"/>
    <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
    <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
    <link rel="stylesheet" href="styles/related-product.css" type="text/css"/>
    <!-- Font family -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap"
      rel="stylesheet"
    />
    <!-- Favicon -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
  </head>
  <body>
    <div class="header-container">
      <jsp:include page="../screens/navbar.jsp"></jsp:include>
      <h1 class="banner-title">LapStore</h1>
      <h2 class="banner-text">Best Laptop Store</h2>
      <button class="banner-btn"><a href="store" style="text-decoration: none; color: black"><span>SHOP NOW</span></a></button>
    </div>
        <div class="related-content-container">
    <h1 id="related-title">Our Best Seller</h1>
    <div class="content-img-container">
        
            <c:forEach var="product" items="${popularProducts}">
                <div class="content-img-row">
                <div class="content-img-row">
                    <a href="/LapStore_main/product-detail?id=${product.id}"><img src="img/${product.productName}.jpg" alt="${product.name}"></a>
                    <p class="img-text">${product.productName}</p>
                </div>
                </div> 
            </c:forEach>
        
    </div>
</div>
    <hr />
      <jsp:include page="../screens/footer.jsp"></jsp:include>
  </body>
</html>
