<%-- 
    Document   : Admin_ProductionList
    Created on : Jun 16, 2023, 4:51:03 PM
    Author     : 84834
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Production List</title>
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
        />
        <link rel="stylesheet" href="styles/admin-product-variant.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <style>
            .pagination a {
                padding: 8px 16px;
                margin: 0 4px;
                border: 1px solid #ddd;
                text-decoration: none;
                color: #007bff;
                border-radius: 4px;
            }
            .pagination a.active {
                background-color: #007bff;
                color: white;
                border: 1px solid #007bff;
            }
            .pagination a:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
        <section class="product">
            <div class="top">
                <div class="search-box">
                    <i class="bx bx-search icon"></i>
                    <input type="text" id="productSearch" placeholder="Search product by name here..." />
                </div>
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="product-content-container">
                <div class="overview">
                    <div class="title">
                        <i class="bx bx-devices icon"></i>
                        <span class="text">Product Variant Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="recent-order">
                            <div class="product-title">
                                <div class="filter" id="1" data-value="&filter=1"></div>
                            </div>
                            <div class="product-add-sale" id="select-sale"><a>Edit Sale</a></div>
                            <div class="product-add"><a href="admin-create-variant">Add new Product</a></div>
                            <div class="product-content">
                                <table id="productTable">
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="check-all"/></th>
                                            <th>Image</th>
                                            <th>Name</th>
                                            <th>Ram</th>
                                            <th>Storage</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>Sale</th>
                                            <th>Price after sale</th>
                                            <th>Edit</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${product}" var="p">
                                            <tr class="product-row">
                                                <td><input type="checkbox" value="${p.id}" onclick="handleCheckboxClick(this)"/></td>
                                                 <td>
                                                    <img
                                                        src="img/${p.url}"
                                                        alt="iphone"
                                                        width="100px"
                                                        />
                                                </td>
                                                <td>${p.name}</td>
                                                <td>${p.ram}</td>
                                                <td>${p.storage}</td>
                                                <td>${p.quantity}</td>
                                                <td>${p.variantPrice}</td>
                                                <td>${p.sale == 0?"None":p.sale}${p.sale == 0?"":"%"}</td>
                                                <td>${p.variantPrice - (p.variantPrice * p.sale) / 100}</td>
                                                <td><a href="admin-update-variant?id=${p.id}"><i class="bx bxs-edit-alt icon"></i></a></td>
                                                <td>
                                                    <c:if test="${p.status == 1}">
                                                        <a href="admin-delete-variant?id=${p.id}&sta=2"><i class='bx bx-show-alt icon'></i></a>
                                                    </c:if>
                                                    <c:if test="${p.status != 1}">
                                                        <a href="admin-delete-variant?id=${p.id}&sta=1"><i class='bx bx-low-vision icon'></i></a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div id="pagination" class="pagination"></div>
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

            function setupPagination(totalRows) {
                const totalPages = Math.ceil(totalRows / rowsPerPage);
                const pagination = document.getElementById('pagination');
                pagination.innerHTML = '';

                for (let i = 1; i <= totalPages; i++) {
                    const pageLink = document.createElement('a');
                    pageLink.textContent = i;
                    pageLink.href = '#';
                    pageLink.className = i === currentPage ? 'active' : '';

                    pageLink.addEventListener('click', function(e) {
                        e.preventDefault();
                        currentPage = i;
                        displayPage(currentPage);
                        setupPagination(totalRows);
                    });

                    pagination.appendChild(pageLink);
                }
            }

            function filterTable(filter) {
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
            }

            document.getElementById('productSearch').addEventListener('keyup', function() {
                const filter = this.value.toLowerCase();
                filterTable(filter);
            });

            displayPage(currentPage);
            setupPagination(rows.length);

            let listId = [];
            const handleCheckboxClick = (checkbox) => {
                const id = checkbox.value;
                if (checkbox.checked) {
                    listId.push(id);
                } else {
                    const index = listId.indexOf(id);
                    if (index > -1) {
                        listId.splice(index, 1);
                    }
                }
            };

            const selectSale = document.getElementById("select-sale");
            selectSale.addEventListener("click", () => {
                if (listId.length === 0) {
                    alert("Please select product!");
                } else {
                    let temp = listId.join(",");
                    console.log(temp);
                    window.location.href = "sale?id=" + temp;
                }
            });

        </script>
    </body>
</html>
