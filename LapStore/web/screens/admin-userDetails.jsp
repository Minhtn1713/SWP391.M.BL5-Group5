<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog List</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        
    </head>
    <body>
        <section class="product">
            <div class="top"> 
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
                            
                            <div class="row">
                            <div class="col-lg-12 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" action="/LapStore/user/save?id=${user.id}" method="post" >
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Full Name</label>
                                                    <input name="fullName" id="title" type="text" class="form-control " value="${user.fullName}">   
                                                    <p class="text-danger">${errorName}</p>
                                                </div>
                                            </div><!--end col-->
                                            
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Phone</label>
                                                    <input name="phone" id="title" type="text" class="form-control "  value="${user.phone}">   
                                                    <p class="text-danger">${errorPhone}</p>
                                                </div>
                                            </div><!--end col-->
                                            
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Email</label>
                                                    <input name="email" id="title" type="text" class="form-control "  value="${user.email}">   
                                                    <p class="text-danger">${errorEmail}</p>
                                                </div>
                                            </div><!--end col-->
                                            
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Gender</label>
                                                    <select class="form-control" name="gender" required>
                                                        
                                                        <option value="1" ${user.gender==1 ? 'selected' : ''}>Male</option>
                                                        <option value="0" ${user.gender==0 ? 'selected' : ''}>Female</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->
                                            
                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Address</label>
                                                    <input name="address" id="address" type="text" class="form-control " value="${user.address}">   
                                                    <p class="text-danger">${errorAddress}</p>
                                                </div>
                                            </div><!--end col-->
                                            
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Role</label>
                                                    <select class="form-control" name="role" required>
                                                        <option value="1" ${user.role==1 ? 'selected' : ''}>Admin</option>
                                                        <option value="2" ${user.role==2 ? 'selected' : ''}>Staff</option>
                                                        <option value="3" ${user.role==3 ? 'selected' : ''}>Customer</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <select class="form-control" name="status" required>
                                                        <option value="1" ${user.isActive==1 ? 'selected' : ''}>ACTIVE</option>
                                                        <option value="0" ${user.isActive==0 ? 'selected' : ''}>INACTIVE</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->                                 
                                        </div><!--end row-->

                                        <button type="submit" class="btn btn-primary" >Update</button>

                                    </form>
                                </div>
                            </div><!--end col-->
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <c:choose>
            <c:when test="${param.updated != null}">
                <div class="toast-container position-fixed bottom-0 end-0 p-3">
                    <div id="liveToast" class="toast show" role="alert" aria-live="assertive" aria-atomic="true" style="width: 350px">
                        <div class="toast-header">
                            <img src="../assets/images/favicon.ico.png" class="rounded me-2" alt="web-logo" height="20" width="20">
                            <strong class="me-auto">LapStore</strong>
                            <small class="mt-1">A few seconds ago</small>
                            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                        </div>
                        <c:choose>
                            <c:when test="${param.updated == 'yes'}">
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
