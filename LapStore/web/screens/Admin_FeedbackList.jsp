<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Management</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="styles/admin-feedback.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
            <div class="container">
                <h2>Feedback Management</h2>
                <div class="search-filter">
                    <select id="searchCriteria" onchange="filterTable()">
                        <option value="productname">Search by Product Name</option>
                        <option value="username">Search by User Name</option>
                    </select>
                    <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Enter search term">

                    <!-- Rating Filter -->
                    <select id="ratingFilter" onchange="filterTable()">
                        <option value="">Filter by Rating</option>
                        <option value="5">5 stars</option>
                        <option value="4">4 stars</option>
                        <option value="3">3 stars</option>
                        <option value="2">2 stars</option>
                        <option value="1">1 star</option>
                    </select>

                    <!-- Date Range Filter -->
                    <select id="dateFilter" onchange="filterTable()">
                        <option value="">Filter by Date Range</option>
                        <option value="7">Last 7 days</option>
                        <option value="30">Last 30 days</option>
                        <option value="90">Last 90 days</option>
                        <option value="365">Last year</option>
                    </select>
                </div>
                <table class="feedback-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product ID</th>
                            <th>User ID</th>
                            <th onclick="sortTable(3, 'int')">Rating</th>
                            <th>Comment</th>
                            <th onclick="sortTable(5, 'date')">Date</th>
                            <th>Product Name</th>
                            <th>Username</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="feedback" items="${list}">
                        <tr>
                            <td>${feedback.id}</td>
                            <td>${feedback.productId}</td>
                            <td>${feedback.userId}</td>
                            <td>${feedback.rating}</td>
                            <td>${feedback.comment}</td>
                            <td>${feedback.timestamp}</td>
                            <td><a href="product-feedback-static?productId=${feedback.productId}">${feedback.productname}</a></td>
                            <td>${feedback.userName}</td>
                            <td>
                                <form action="admin-feedback" method="post" onsubmit="return confirm('Are you sure you want to delete this feedback?');">
                                    <input type="hidden" name="feedbackId" value="${feedback.id}" />
                                    <button type="submit" class="action-btn delete">Delete</button>
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
          <div id="pagination"> </div>
        </div>

        <script>
            function filterTable() {
                const criteria = document.getElementById("searchCriteria").value;
                const input = document.getElementById("searchInput").value.trim().toLowerCase();
                const rating = document.getElementById("ratingFilter").value;
                const dateRange = document.getElementById("dateFilter").value;
                const table = document.querySelector(".feedback-table tbody");
                const rows = table.getElementsByTagName("tr");

                // Determine the correct column index (7th for productname, 8th for username)
                let columnIndex;
                if (criteria === 'productname') {
                    columnIndex = 6; // Product Name column
                } else if (criteria === 'username') {
                    columnIndex = 7; // Username column
                }

                const today = new Date();

                for (let i = 0; i < rows.length; i++) {
                    const cells = rows[i].getElementsByTagName("td");
                    if (cells.length > 0) {
                        const cell = cells[columnIndex];
                        const textValue = cell.textContent.trim().toLowerCase();
                        const ratingValue = cells[3].textContent.trim(); // Rating column
                        const dateValue = new Date(cells[5].textContent.trim()); // Date column

                        let showRow = true;

                        // Filter by search criteria (productname/username)
                        if (textValue.includes(input) === false) {
                            showRow = false;
                        }

                        // Filter by rating
                        if (rating && ratingValue !== rating) {
                            showRow = false;
                        }

                        // Filter by date range
                        if (dateRange) {
                            const daysAgo = new Date(today);
                            daysAgo.setDate(today.getDate() - parseInt(dateRange));
                            if (dateValue < daysAgo) {
                                showRow = false;
                            }
                        }

                        // Show/Hide the row based on filters
                        rows[i].style.display = showRow ? "" : "none";
                    }
                }
            }
            let sortOrder = {}; // Object to keep track of sort order per column

            function sortTable(columnIndex, type) {
                const table = document.querySelector(".feedback-table tbody");
                const rows = Array.from(table.rows);

                // Determine sort order: ascending or descending
                sortOrder[columnIndex] = !sortOrder[columnIndex] ? 'asc' : (sortOrder[columnIndex] === 'asc' ? 'desc' : 'asc');

                let sortedRows;
                if (type === 'int') {
                    sortedRows = rows.sort((a, b) => {
                        const aValue = parseInt(a.cells[columnIndex].textContent.trim());
                        const bValue = parseInt(b.cells[columnIndex].textContent.trim());
                        return sortOrder[columnIndex] === 'asc' ? aValue - bValue : bValue - aValue;
                    });
                } else if (type === 'date') {
                    sortedRows = rows.sort((a, b) => {
                        const aValue = new Date(a.cells[columnIndex].textContent.trim());
                        const bValue = new Date(b.cells[columnIndex].textContent.trim());
                        return sortOrder[columnIndex] === 'asc' ? aValue - bValue : bValue - aValue;
                    });
                }

                // Re-attach the sorted rows to the table
                table.innerHTML = '';
                sortedRows.forEach(row => table.appendChild(row));
            }


        </script>
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
        <script src="js/dashboard.js"></script>
    </body>
</html>
