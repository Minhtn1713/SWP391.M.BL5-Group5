<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders List</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
            <section class="product">
                <div class="top">
                    <div class="search-box">
                        <i class="bx bx-search icon"></i>
                        <form class="d-flex" action="/LapStore_main/order?search=${txtS}" method="get">
                        <input class="form-control me-2" type="search" value="${txtS}" name="txtSearch" id="txtSearch" placeholder="Enter Name:" aria-label="Search">
                    </form>
                </div>
                <h3 class="icon profile">Admin</h3>
            </div>
            <div class="product-content-container">
                <div class="overview">
                    <div class="title">
                        <i class="bi bi-card-checklist"></i>
                        <span class="text">Order Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="recent-order">
                            <div class="product-title">
                                <i class="bi bi-card-checklist"></i>&nbsp;Orders List
                            </div>
                            <div class="product-add">

                            </div>
                            <div class="product-content">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>User ID</th>
                                            <th>Total Price</th> 
                                            <th>Created Date</th> 
                                            <th>Name</th> 
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="productTableBody">
                                        <c:forEach items="${orders}" var="p">
                                            <tr class="product-row">
                                                <td>${p.id}</td>
                                                <td><a href="user/update?id=${p.userId}" class="btn btn-icon btn-pills btn-soft-success" >${p.userId}</a></td>
                                                <td>${p.totalPrice}</td>
                                                <td>${p.createdDate}</td>
                                                <td>${p.name}</td>
                                                <td>
                                                    <select class="form-control" name="status" id="status-${p.id}" required>
                                                        <option value="1" ${p.status==1 ? 'selected' : ''}>Pending</option>
                                                        <option value="2" ${p.status==2 ? 'selected' : ''}>Awaiting Payment</option>
                                                        <option value="3" ${p.status==3 ? 'selected' : ''}>Awaiting Shipment</option>
                                                        <option value="4" ${p.status==4 ? 'selected' : ''}>Completed</option>
                                                        <option value="5" ${p.status==5 ? 'selected' : ''}>Cancelled</option>
                                                    </select>
                                                </td>
                                                <td class="text-start p-3">
                                                    <a href="order/update?id=${p.id}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-edit"></i></a>
                                                    <a href="javascript:void(0);" class="btn btn-icon btn-pills btn-soft-danger" onclick="toggleStatus(${p.id});"><i class="bi bi-save"></i></a>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- Pagination controls -->
                                <div>
                                    <div class="row text-center">
                                        <div class="col-12 mt-4">
                                            <div class="d-md-flex align-items-center text-center justify-content-between">
                                                <ul class="pagination justify-content-center mb-0">
                                                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                                        <a class="page-link" href="order?index=${currentPage - 1}" aria-label="Previous">Prev</a>
                                                    </li>
                                                    <c:forEach begin="1" end="${endP}" var="i">
                                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                            <a class="page-link" href="order?index=${i}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item ${endP == currentPage ? 'disabled' : ''}">
                                                        <a class="page-link" href="order?index=${currentPage + 1}" aria-label="Next">Next</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <c:choose>
            <c:when test="${param.toggled != null}">
                <div class="toast-container position-fixed bottom-0 end-0 p-3">
                    <div id="liveToast" class="toast show" role="alert" aria-live="assertive" aria-atomic="true" style="width: 350px">
                        <div class="toast-header">
                            <img src="../assets/images/favicon.ico.png" class="rounded me-2" alt="web-logo" height="20" width="20">
                            <strong class="me-auto">LapStore_main</strong>
                            <small class="mt-1">A few seconds ago</small>
                            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                        </div>
                        <c:choose>
                            <c:when test="${param.toggled == 'successful'}">
                                <div class="toast-body text-success-emphasis">
                                    Status changed successfully!
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="toast-body text-danger-emphasis">
                                    Failed to change status!
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <script src="js/dashboard.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                                        document.addEventListener('DOMContentLoaded', function () {
                                                            var toastEl = document.getElementById('liveToast');
                                                            if (toastEl) {
                                                                var toast = new bootstrap.Toast(toastEl, {
                                                                    delay: 2000
                                                                });
                                                                toast.show();
                                                            }
                                                        });
        </script>
        <script>
            function toggleStatus(id) {
                // Get the selected status value
                var status = document.getElementById('status-' + id).value;

                // Redirect to the toggle URL with the selected status
                window.location.href = 'order/toggle?id=' + id + '&status=' + status;
            }
        </script>
    </body>
</html>