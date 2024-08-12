<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Brand"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand List</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <style>
            #pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            #pagination a {
                display: inline-block;
                padding: 10px 15px;
                margin: 0 5px;
                border: 1px solid #ddd;
                border-radius: 5px;
                text-decoration: none;
                color: #333;
                background-color: #f5f5f5;
                transition: background-color 0.3s, color 0.3s;
            }

            #pagination a.active {
                background-color: #007bff;
                color: white;
            }

            #pagination a:hover:not(.active) {
                background-color: #ddd;
                color: #007bff;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
        <section class="product">
            <div class="top">
                <div class="search-box">
                    <i class="bx bx-search icon"></i>
                    <input type="text" id="productSearch" placeholder="Search brand by name here..." />
                </div>
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="product-content-container">
                <div class="overview">
                    <div class="title">
                        <i class="bx bx-mobile-alt icon"></i>
                        <span class="text">Brand Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="recent-order">
                            <div class="product-title">
                                <i class="bx bx-mobile-alt icon"></i>&nbsp;Brand List
                            </div>
                            <div class="product-add">
                                <a href="admin-create-brand">Add new Brand</a>
                            </div>
                            <div class="product-content">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>                                        
                                            <th>Name</th>   
                                            <th>Edit</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${list}" var="p">
                                            <tr class="product-row">
                                                <td>${p.id}</td>
                                                <td>${p.name}</td>
                                                <td>
                                                    <a href="admin-update-brand?id=${p.id}">
                                                        <i class="bx bxs-edit-alt icon"></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="admin-delete-brand?id=${p.id}">
                                                        <i class="bx bx-trash icon"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div id="pagination"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
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

            function setupPagination(totalRows = rows.length) {
                const totalPages = Math.ceil(totalRows / rowsPerPage);
                const pagination = document.getElementById('pagination');
                pagination.innerHTML = '';

                for (let i = 1; i <= totalPages; i++) {
                    const pageLink = document.createElement('a');
                    pageLink.textContent = i;
                    pageLink.href = '#';
                    pageLink.className = i === currentPage ? 'active' : '';

                    pageLink.addEventListener('click', function (e) {
                        e.preventDefault();
                        currentPage = i;
                        displayPage(currentPage);
                        setupPagination(totalRows); 
                    });

                    pagination.appendChild(pageLink);
                }
            }

            document.getElementById('productSearch').addEventListener('keyup', function () {
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

            displayPage(currentPage);
            setupPagination(rows.length);
        </script>
    </body>
</html>