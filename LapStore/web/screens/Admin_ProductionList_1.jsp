<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
    <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
</head>
<body>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <section class="product">
        <div class="top">
            <div class="search-box">
                <i class="bx bx-search icon"></i>
                <input type="text" id="productSearch" placeholder="Search product by name here..." />
            </div>
            <h3 class="icon profile">Admin</h3>
        </div>
        <div class="product-content-container">
            <div class="overview">
                <div class="title">
                    <i class="bx bx-mobile-alt icon"></i>
                    <span class="text">Product Dashboard</span>
                </div>
                <div class="boxes">
                    <div class="recent-order">
                        <div class="product-title">
                            <i class="bx bx-mobile-alt icon"></i>&nbsp;Product List
                        </div>
                        <div class="product-add">
                            <a href="admin-create-product">Add new Product</a>
                        </div>
                        <div class="product-content">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Image</th> 
                                        <th>Brand</th> 
                                        <th>Price</th>
                                        <th>Screen</th>
                                        <th>Processor</th>
                                        <th>Graphic Card</th>
                                        <th>Operating System</th>
                                        <th>Battery Life</th>
                                        <th>Weight</th>
                                        <th>Description</th>
                                        <th>Edit</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody id="productTableBody">
                                    <c:forEach items="${list}" var="p">
                                        <tr class="product-row">
                                            <td>${p.id}</td>
                                            <td>${p.name}</td>
                                            <td>
                                                <img src="img/${p.img}" width="200px"/>
                                            </td>
                                            <td>${p.brandName}</td>
                                            <td>${p.price}</td>
                                            <td>${p.screen_details}</td>
                                            <td>${p.processor}</td>
                                            <td>${p.graphic_card}</td>
                                            <td>${p.operatingSystem}</td>
                                            <td>${p.battery}</td>
                                            <td>${p.weight}</td>
                                            <td>${p.description}</td>
                                            <td>
                                                <a href="admin-update-product?id=${p.id}">
                                                    <i class="bx bxs-edit-alt icon"></i>
                                                </a>
                                            </td>
                                            <td>
                                                <c:if test="${p.status == 1}">
                                                    <a href="admin-delete-product?id=${p.id}&sta=2">
                                                        <i class='bx bx-show-alt icon'></i>
                                                    </a>
                                                </c:if>
                                                <c:if test="${p.status != 1}">
                                                    <a href="admin-delete-product?id=${p.id}&sta=1">
                                                        <i class='bx bx-low-vision icon'></i>
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- Pagination controls -->
                            <div id="pagination"> </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        const rowsPerPage = 4;
        let currentPage = 1;
        const rows = document.querySelectorAll('.product-row');

        function displayPage(page) {
            const start = (page - 1) * rowsPerPage;
            const end = start + rowsPerPage;
            rows.forEach((row, index) => {
                row.style.display = index >= start && index < end ? '' : 'none';
            });
        }

        function setupPagination() {
            const totalPages = Math.ceil(rows.length / rowsPerPage);
            const pagination = document.getElementById('pagination');
            pagination.innerHTML = ' ';

            for (let i = 1; i <= totalPages; i++) {
                const pageLink = document.createElement('a');
                pageLink.textContent = i;
                pageLink.href = '#';
                pageLink.className = i === currentPage ? 'active' : ' ';

                pageLink.addEventListener('click', function(e) {
                    e.preventDefault();
                    currentPage = i;
                    displayPage(currentPage);
                    setupPagination();
                });

                pagination.appendChild(pageLink);
            }
        }

        document.getElementById('productSearch').addEventListener('keyup', function() {
            const filter = this.value.toLowerCase();
            let filteredRows = Array.from(rows).filter(row => {
                const productName = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
                return productName.includes(filter);
            });

            rows.forEach(row => row.style.display = 'none');
            filteredRows.forEach((row, index) => {
                if (index < rowsPerPage) {
                    row.style.display = '';
                }
            });

            currentPage = 1;
            setupPagination(filteredRows.length);
        });

        function setupPagination(totalRows) {
            const totalPages = Math.ceil(totalRows / rowsPerPage);
            const pagination = document.getElementById('pagination');
            pagination.innerHTML = ' ';

            for (let i = 1; i <= totalPages; i++) {
                const pageLink = document.createElement('a');
                pageLink.textContent = i;
                pageLink.href = '#';
                pageLink.className = i === currentPage ? 'active' : ' ';

                pageLink.addEventListener('click', function(e) {
                    e.preventDefault();
                    currentPage = i;
                    displayPage(currentPage);
                    setupPagination(totalRows);
                });

                pagination.appendChild(pageLink);
            }
        }

        displayPage(currentPage);
        setupPagination(rows.length);
    </script>
     <script src="js/dashboard.js"></script>
</body>
</html>