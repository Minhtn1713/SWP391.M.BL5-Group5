<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Lap Store</title>
        <!-- CSS -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/related-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/product-detail.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet"/>
        <!-- Favicon -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="container">
                <div class="product-info">
                    <div class="product-img">
                        <div class="slider">
                        <c:forEach items="${image}" var="img">
                            <div class="slide">
                                <img src="img/${img.url}" alt="">
                            </div>
                        </c:forEach>
                        <button class="btn-slide" id="pre-slide" onclick="moveImage(-1)">&#10094;</button>
                        <button class="btn-slide" id="next-slide" onclick="moveImage(1)">&#10095;</button>
                    </div>
                    <div class="thumbnail-container">
                        <c:forEach items="${image}" var="img" varStatus="status">
                            <img class="thumbnail ${status.index == 0 ? 'active' : ''}" src="img/${img.url}" alt="" onclick="currentSlide(${status.index})">
                        </c:forEach>
                    </div>
                </div>


                <div class="product-specs">
                    <h2 class="product-title">${productDetail.name}</h2>
                    <div class="product-price">${productDetail.price} $</div>
                    <div class="stock-status">
                        <span class="dot ${productDetail.status == 0 ? 'out-of-stock' : 'in-stock'}"></span>
                        <span>${productDetail.status == 0 ? 'Out of Stock' : 'In Stock'}</span>
                    </div>
                    <ul>
                        <li>Processor: ${productDetail.processor}</li>
                        <li>Graphic Card: ${productDetail.graphic_card}</li>
                        <li>Screen: ${productDetail.screen_details}</li>
                        <li>Size: ${productDetail.size}</li>
                        <li>Weight: ${productDetail.weight} kg</li>
                        <li>Operating System: ${productDetail.operatingSystem}</li>
                        <li>Battery Life: ${productDetail.battery}</li>
                    </ul>
                    <div class="product-description">
                        <p>${productDetail.description}</p>
                    </div>
                    <div class="button-group">
                        <c:choose>
                            <c:when test="${productDetail.status == 0}">
                                <a href="#" class="buy-now out-of-stock" onclick="alert('This product is out of stock'); return false;">
                                    BUY NOW
                                </a>
                                <a href="#" class="add-to-cart out-of-stock" onclick="alert('This product is out of stock'); return false;">
                                    <i class="fa fa-shopping-cart"></i> Add to Cart
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="order?product_id=${productDetail.id}" class="buy-now">
                                    BUY NOW
                                </a>
                                <a href="#" class="add-to-cart">
                                    <i class="fa fa-shopping-cart"></i> Add to Cart
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
        <script>
            let currentSlideIndex = 0;
            showSlide(currentSlideIndex);

            function showSlide(n) {
                let slides = document.getElementsByClassName("slide");
                let thumbnails = document.getElementsByClassName("thumbnail");

                if (n >= slides.length)
                    currentSlideIndex = 0;
                if (n < 0)
                    currentSlideIndex = slides.length - 1;

                for (let i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                    thumbnails[i].classList.remove("active");
                }

                slides[currentSlideIndex].style.display = "block";
                thumbnails[currentSlideIndex].classList.add("active");
            }

            function moveImage(n) {
                showSlide(currentSlideIndex += n);
            }

            function currentSlide(n) {
                showSlide(currentSlideIndex = n);
            }

        </script>
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>
