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
                    <div class="brand-container">
                        Brand: <a href="ProductListController?brand=${brand.id}">${brand.name}</a>
                    </div>
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

                    <div class="quantity-container">
                        <label for="quantity">Quantity:</label>
                        <div class="quantity-controls">
                            <button type="button" onclick="changeQuantity(-1)">-</button>
                            <input type="number" id="quantity" name="quantity" value="1" min="1" max="${productDetail.status == 0 ? '0' : '10'}" readonly />
                            <button type="button" onclick="changeQuantity(1)">+</button>
                        </div>
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
        <div class="feedback-section">
            <h3>Customer Feedback</h3>

            <div class="feedback-summary">
                <p><strong>Total Feedbacks:</strong> ${feedbackData.numberOfFeedbacks}</p>
                <p><strong>Average Rating:</strong> ${feedbackData.averageRating}</p>
            </div>

            <div class="feedback-sorting">
                <label for="sort-feedback">Sort by: </label>
                <select id="sort-feedback" onchange="sortFeedback()">
                    <option value="timestamp">Date (Newest)</option>
                    <option value="timestamp-desc">Date (Oldest)</option>
                    <option value="rating">Rating (High to Low)</option>
                    <option value="rating-desc">Rating (Low to High)</option>
                </select>
            </div>

            <div id="feedback-list">
                <c:forEach items="${feedback}" var="fb">
                    <div class="feedback-item" data-rating="${fb.rating}" data-timestamp="${fb.timestamp}">
                        <div class="feedback-header">
                            <div class="feedback-rating">
                                <c:forEach begin="1" end="${fb.rating}">
                                    <i class="fa fa-star"></i>
                                </c:forEach>
                            </div>
                            <div class="feedback-user">
                                <strong>${fb.userName}</strong>
                            </div>
                        </div>
                        <div class="feedback-comment">
                            <p>${fb.comment}</p>
                        </div>
                        <div class="feedback-timestamp">
                            <small>Posted on: ${fb.timestamp}</small>
                        </div>
                    </div>
                </c:forEach>
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
            function changeQuantity(amount) {
                let quantityInput = document.getElementById("quantity");
                let currentQuantity = parseInt(quantityInput.value);
                let newQuantity = currentQuantity + amount;
                if (newQuantity >= 1 && newQuantity <= 10) {
                    quantityInput.value = newQuantity;
                    document.getElementById("buy-now-link").href = "order?product_id=${productDetail.id}&quantity=" + newQuantity;
                }
            }
            function sortFeedback() {
                let feedbackList = document.getElementById("feedback-list");
                let feedbackItems = Array.from(feedbackList.getElementsByClassName("feedback-item"));
                let sortBy = document.getElementById("sort-feedback").value;

                feedbackItems.sort((a, b) => {
                    let ratingA = parseInt(a.getAttribute("data-rating"));
                    let ratingB = parseInt(b.getAttribute("data-rating"));
                    let timestampA = new Date(a.getAttribute("data-timestamp")).getTime();
                    let timestampB = new Date(b.getAttribute("data-timestamp")).getTime();

                    if (sortBy === "rating") {
                        return ratingB - ratingA; // High to Low
                    } else if (sortBy === "rating-desc") {
                        return ratingA - ratingB; // Low to High
                    } else if (sortBy === "timestamp") {
                        return timestampB - timestampA; // Newest First
                    } else if (sortBy === "timestamp-desc") {
                        return timestampA - timestampB; // Oldest First
                    }
                });

                // Clear existing list and append sorted items
                feedbackList.innerHTML = "";
                feedbackItems.forEach(item => feedbackList.appendChild(item));
            }

        </script>
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>
