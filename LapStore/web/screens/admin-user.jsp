<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
        <section class="product">
            <div class="top">
                <div class="search-box">
                    <i class="bx bx-search icon"></i>
                    <form class="d-flex" action="/LapStore_main/user?search=${txtS}" method="get">
                        <input class="form-control me-2" type="search" value="${txtS}" name="txtSearch" id="txtSearch" placeholder="Enter username or name:" aria-label="Search">
                        <select class="form-select" name="roleFilter" id="roleFilter">
                            <option value="">All Roles</option>
                            <option value="1" ${param.roleFilter == '1' ? 'selected' : ''}>Admin</option>
                            <option value="2" ${param.roleFilter == '2' ? 'selected' : ''}>Staff</option>
                            <option value="3" ${param.roleFilter == '3' ? 'selected' : ''}>Customer</option>
                        </select>
                    </form>
                </div>
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="product-content-container">
                <div class="overview">
                    <div class="title">
                        <i class='bx bx-news icon'></i>
                        <span class="text">User Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="recent-order">
                            <div class="product-title">
                                <i class='bx bx-news icon'></i>&nbsp;User
                            </div>
                            <div class="product-add"><a href="/LapStore_main/user/add">Add new User</a></div>
                            <div class="product-content">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th style="width: 5%;">ID</th>
                                            <th style="width: 20%;">Full Name</th>
                                            <th style="width: 25%;">Email</th>
                                            <th style="width: 15%;">Username</th>
                                            <th style="width: 10%;">Role</th>
                                            <th style="width: 10%;">Status</th>
                                            <th style="width: 15%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${users}" var="c">
                                            <tr>
                                                <td>${c.id}</td>
                                                <td>${c.fullName}</td>
                                                <td>${c.email}</td>
                                                <td>${c.userName}</td>
                                                <td>
                                                    <c:if test="${c.role == 1}">Admin</c:if>
                                                    <c:if test="${c.role == 2}">Staff</c:if>
                                                    <c:if test="${c.role == 3}">Customer</c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${c.isActive == 1}">Active</c:if>
                                                    <c:if test="${c.isActive == 0}">Inactive</c:if>
                                                    </td>
                                                    <td class="text-start p-3">
                                                        <a href="user/update?id=${c.id}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-edit"></i></a>
                                                        <a href="user/toggle?id=${c.id}" class="btn btn-icon btn-pills btn-soft-danger">
                                                        <c:choose>
                                                            <c:when test="${c.isActive == 1}">
                                                                <i class="uil uil-lock"></i>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <i class="uil uil-unlock"></i>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row text-center">
                                <div class="col-12 mt-4">
                                    <div class="d-md-flex align-items-center text-center justify-content-between">
                                        <ul class="pagination justify-content-center mb-0">
                                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                                <a class="page-link" href="user?index=${currentPage - 1}" aria-label="Previous">Prev</a>
                                            </li>
                                            <c:forEach begin="1" end="${endP}" var="i">
                                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                    <a class="page-link" href="user?index=${i}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item ${endP == currentPage ? 'disabled' : ''}">
                                                <a class="page-link" href="user?index=${currentPage + 1}" aria-label="Next">Next</a>
                                            </li>
                                        </ul>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/dashboard.js"></script>
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
    </body>
</html>