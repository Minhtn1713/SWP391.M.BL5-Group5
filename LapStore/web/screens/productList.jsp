<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LAP STORE</title>
        <!-- CSS -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/store-product.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet"/>
        <!-- Favicon -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="store-container">
                <div class="store-header"><a href="home" style="text-decoration: none; color: black;">HOME /</a> SHOP</div>
                <div class="card-container">
                    <div class="left">
                        <!-- Filter and Search Section -->
                        <div class="filter-section">
                            <!-- Search by Name -->
                            <div class="search-box">
                                <form action="ProductListController" method="get">
                                    <input type="hidden" name="items-per-page" value="${itemsPerPage}">
                                <input type="hidden" name="sort-by" value="${sortBy}">
                                <input type="text" name="search" placeholder="Search by name" value="${param.search}">
                                <button type="submit">Search</button>
                            </form>
                        </div>


                        <!-- Product Status Filter -->
                        <div class="status-filter">
                            <form action="ProductListController" method="get">
                                <input type="hidden" name="items-per-page" value="${itemsPerPage}">
                                <input type="hidden" name="sort-by" value="${sortBy}">
                                <fieldset>
                                    <legend>Status</legend>
                                    <label><input type="radio" name="status" value="all" ${param.status == 'all' ? 'checked' : ''}> All</label>
                                    <label><input type="radio" name="status" value="in-stock" ${param.status == 'in-stock' ? 'checked' : ''}> In Stock</label>
                                    <label><input type="radio" name="status" value="out-of-stock" ${param.status == 'out-of-stock' ? 'checked' : ''}> Out of Stock</label>
                                </fieldset>
                                <button type="submit">Filter</button>
                            </form>
                        </div>
                                
                                
                    </div>
                </div>

                <div class="right">
                    <div class="right-sort">
                        <div class="products-showing">
                            Showing <strong>${list.size()}</strong> products
                        </div>
                        <div class="current-showing">
                            <strong>Show</strong>
                            <form action="ProductListController" method="get" class="products-show-form">
                                <input type="hidden" name="sort-by" value="${sortBy}">
                                <select name="items-per-page" onchange="this.form.submit()">
                                    <option value="6" ${itemsPerPage == 6 ? 'selected' : ''}>6</option>
                                    <option value="12" ${itemsPerPage == 12 ? 'selected' : ''}>12</option>
                                </select>
                            </form>
                            products
                        </div>
                        <form action="ProductListController" method="get" class="products-sort-by">
                            <strong>Sort by</strong>
                            <input type="hidden" name="items-per-page" value="${itemsPerPage}">
                            <select id="sort-by" name="sort-by" class="form-control" onchange="this.form.submit()">
                                <option value="default" ${sortBy == 'default' ? 'selected' : ''}>Default</option>
                                <option value="price-asc" ${sortBy == 'price-asc' ? 'selected' : ''}>Price Ascending</option>
                                <option value="price-desc" ${sortBy == 'price-desc' ? 'selected' : ''}>Price Descending</option>
                                <option value="weight-asc" ${sortBy == 'weight-asc' ? 'selected' : ''}>Weight Ascending</option>
                                <option value="weight-desc" ${sortBy == 'weight-desc' ? 'selected' : ''}>Weight Descending</option>
                            </select>
                        </form>
                    </div>
                    <div class="right-container">
                        <c:forEach items="${list}" var="product">
                            <div class="sellter-item">
                                <a href="product-detail?id=${product.id}" style="text-decoration: none">
                                    <div class="sellter-item-content">
                                        <img class="img" src="img/${product.img}" alt="${product.name}">
                                        <div class="sellter-item-text">
                                            <h3>${product.name}</h3>
                                            <div class="stock-status">
                                                <span class="dot ${product.status == 0 ? 'out-of-stock' : 'in-stock'}"></span>
                                                <span>${product.status == 0 ? 'Out of Stock' : 'In Stock'}</span>
                                            </div>
                                            <h3>${product.price}$</h3>
                                            <div class="btn-info-container">
                                                <button class="btn-info">${product.processor}</button>
                                                <button class="btn-info">${product.graphic_card}</button>
                                                <button class="btn-info">${product.screen_details}</button>
                                                <button class="btn-info">${product.size}</button>
                                            </div>
                                            <div class="btn">
                                                <a href="order?product_id=${product.id}" class="btn-buy-now">BUY NOW</a>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="pagination" id="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="ProductListController?page=${currentPage - 1}&items-per-page=${itemsPerPage}&sort-by=${sortBy}" class="pagination-link">Previous</a>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <a href="ProductListController?page=${i}&items-per-page=${itemsPerPage}&sort-by=${sortBy}" class="pagination-link ${i == currentPage ? 'active' : ''}">${i}</a>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <a href="ProductListController?page=${currentPage + 1}&items-per-page=${itemsPerPage}&sort-by=${sortBy}" class="pagination-link">Next</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>
