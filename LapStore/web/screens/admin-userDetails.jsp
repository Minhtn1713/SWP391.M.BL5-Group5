<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <style>
            /* product */
.product {
  position: relative;
  background-color: var(--body-color);
  left: 250px;
  height: 100vh;
  width: calc(100% - 250px);
  padding: 10px 14px;
  transition: var(--tran-05);
}

.product .top {
  position: fixed;
  top: 0;
  display: flex;
  left: 250px;
  width: calc(100% - 250px);
  justify-content: space-between;
  padding: 10px 14px;
  background-color: var(--siderbar-color);
  z-index: 10;
}

.sidebar.close ~ .product .top {
  left: 88px;
  width: calc(100% - 88px);
}

.product .top .search-box {
  position: relative;
  height: 45px;
  max-width: 600px;
  width: 100%;
  margin: 0 30px;
}

.product .top .search-box input {
  position: absolute;
  border: 1px solid var(--border-color);
  padding: 0 25px 0 50px;
  height: 100%;
  width: 100%;
  background-color: var(--siderbar-color);
  border-radius: 10px;
  color: var(--text-color);
  font-size: 15px;
  font-weight: 400;
  outline: none;
}

.product .top .search-box .icon {
  position: absolute;
  left: 15px;
  font-size: 22px;
  z-index: 10;
  top: 50%;
  transform: translateY(-50%);
}

.product .top .profile {
  transform: translateY(20%);
}

.product .product-content-container {
  margin-top: 60px;
  width: 100%;
  position: relative;
  left: 0;
}

.sidebar.close ~ .product .product-content-container {
  left: -150px;
  width: calc(100% + 150px);
}

.product-content-container .title {
  display: flex;
  align-items: center;
  margin: 100px 0 30px 0;
}

.product-content-container .title i {
  position: relative;
  height: 35px;
  width: 35px;
  color: var(--text-color);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
}

.product-content-container .title .text {
  font-size: 24px;
  font-weight: 500;
  color: var(--text-color);
}

.product-content-container .boxes {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.product-content-container .boxes .recent-order {
  width: calc(100%);
  border-radius: 10px;
  padding: 15px 25px;
  background-color: var(--siderbar-color);
  margin-bottom: 10vh;
  position: relative;
}

.product-content-container .boxes .product-title {
  margin-bottom: 20px;
  font-weight: 500;
  font-size: 24px;
  color: var(--text-color);
  background-color: var(--siderbar-color);
}

.product-content-container .boxes .product-add {
  position: absolute;
  padding: 10px;
  background-color: var(--text-color);
  right: 10px;
  top: 10px;
  border-radius: 10px;
  color: red;
  text-decoration: none;
}
.product-content-container .boxes .product-add a {
  font-weight: 500;
  font-size: 16px;
  color: var(--siderbar-color) !important;
  text-decoration: none !important;
}

.product-content-container .boxes .product-add:hover {
  background-color: var(--primary-color);
}
.product-content-container .boxes .product-add:hover a{
  color: var(--siderbar-color) !important;
}

a:-webkit-any-link {
    color: black;
    cursor: pointer;
    text-decoration: none;
}

/* table */

table {
  width: 100%;
  border-collapse: collapse;
}

thead th {
  background-color: #f2f2f2;
  color: #333333;
  font-weight: bold;
  padding: 10px;
  text-align: left;
}
thead th:nth-child(4) {
  width: 650px;
}

tbody td {
  border-bottom: 1px solid #dddddd;
  padding: 10px;
}

/* Image Styles */
table img {
  display: block;
  margin: auto;
  width: 300px;
  height: auto;
}

/* Alternate Row Color */
tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

body.dark tbody tr {
  background-color: var(--siderbar-color);
  color: var(--text-color);
}

/* Hover Effect */
tbody tr:hover {
  background-color: #e6f7ff;
}

/* Responsive */
@media (max-width: 1000px) {
  .sidebar {
    width: 73px;
  }

  .sidebar .logo {
    opacity: 0;
    pointer-events: none;
  }

  .sidebar.close .logo {
    opacity: 1;
    pointer-events: auto;
  }

  .sidebar.close {
    width: 250px;
  }
  .sidebar li a .nav-text,
  .sidebar li .admin-submenu-item .nav-text {
    opacity: 0;
    pointer-events: none;
  }
  .sidebar li .admin-submenu-item .admin-dropdown {
    opacity: 0;
  }
  .sidebar.close li .admin-submenu-item .admin-dropdown {
    opacity: 1;
  }

  .sidebar .sub-menu {
    margin-left: 0;
  }

  .sidebar.close li a .nav-text,
  .sidebar.close li .admin-submenu-item .nav-text {
    opacity: 1;
    pointer-events: auto;
  }
  .sidebar ~ .product {
    left: 73px;
    width: calc(100% - 73px);
  }

  .sidebar ~ .product .top {
    left: 73px;
    width: calc(100% - 73px);
  }

  .sidebar.close ~ .product {
    left: 250px;
    width: calc(100% - 250px);
  }

  .sidebar.close ~ .product .top {
    left: 250px;
    width: calc(100% - 250px);
  }

  .bottom-content .mode-text {
    opacity: 0;
  }

  .sidebar.close .bottom-content .mode-text {
    opacity: 1;
  }

  .menu-bar .mode i {
    position: absolute;
    transition: var(--tran-03);
  }

  .product {
    overflow-x: auto;
  }

  .sidebar header {
    position: relative;
  }
  .sidebar .toggle {
    position: absolute;
    top: 50%;
    right: 0px;
    font-size: 25px;
    transform: translateY(-50%);
    display: flex;
    justify-content: center;
    cursor: pointer;
  }

  .sidebar.close .toggle {
    position: absolute;
    top: 50%;
    font-size: 25px;
    transform: translateY(-50%);
    display: flex;
    justify-content: center;
    width: 100%;
    right: -100px;
  }

  .sidebar .bottom-content .mode .moon-sun .i {
    opacity: 0;
  }

  .product .product-content-container .boxes {
    width: 300vw;
  }

}
    @import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Poppins:wght@400;500;600;700&family=Shadows+Into+Light&display=swap");
* {
  font-family: "Poppins", sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

:root {
  /* set color */
  --body-color: #e4e9f7;
  --siderbar-color: #fff;
  --primary-color: #695cfe;
  --primary-color-light: #f6f5ff;
  --toggle-color: #ddd;
  --text-color: #707070;
  --border-color: rgba(77, 76, 76, 0.2);

  /* Transition */
  --tran-02: all 0.2s ease;
  --tran-03: all 0.3s ease;
  --tran-04: all 0.4s ease;
  --tran-05: all 0.5s ease;
}

body {
  background-color: var(--body-color);
  height: 100vh;
  transition: var(--tran-05);
}

body.dark {
  --body-color: #18191a;
  --siderbar-color: #242526;
  --primary-color: #3a3b3c;
  --primary-color-light: #3a3b3c;
  --toggle-color: #fff;
  --text-color: #ccc;
  --border-color: rgba(241, 237, 237, 0.2);
}

/* REUSABLE CSS */

/* SIDE BAR CSS */
.sidebar header {
  position: relative;
}
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 250px;
  height: 100vh;
  padding: 10px 14px;
  background-color: var(--siderbar-color);
  z-index: 100;
  border-right: 1px solid var(--border-color);
}

.sidebar .text {
  transition: var(--tran-02);
}

.sidebar.close {
  width: 88px;
}

.sidebar.close .text {
  opacity: 0;
}

.sidebar .toggle {
  position: absolute;
  top: 50%;
  right: -10px;
  font-size: 25px;
  transform: translateY(-50%);
  display: flex;
  justify-content: center;
  cursor: pointer;
}

.sidebar.close .toggle {
  position: absolute;
  top: 50%;
  font-size: 25px;
  transform: translateY(-50%);
  display: flex;
  justify-content: center;
  width: 100%;
  right: 0.1px;
}

.sidebar li {
  height: 50px;
  margin-top: 10px;
  list-style: none;
  display: flex;
  align-items: center;
}

.sidebar li .icon {
  min-width: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.icon,
.logo a {
  font-size: 20px;
  color: var(--text-color);
}

.sidebar li .icon,
.sidebar li .text {
  font-size: 20px;
  color: var(--text-color);
  transition: var(--tran-02);
}

.sidebar li a,
.sidebar li .admin-submenu-item {
  height: 100%;
  display: flex;
  align-items: center;
  text-decoration: none;
  width: 100%;
}

.sidebar li a:hover,
.sidebar li .admin-submenu-item:hover {
  background-color: var(--primary-color);
}

.sidebar li a:hover .icon,
.sidebar li a:hover .text,
.sidebar li .admin-submenu-item:hover .icon,
.sidebar li .admin-submenu-item:hover .text {
  font-size: 20px;
  color: var(--siderbar-color);
}

body.dark .sidebar li a:hover .icon,
body.dark .sidebar li a:hover .text,
body.dark .sidebar li .admin-submenu-item:hover .icon,
body.dark .sidebar li .admin-submenu-item:hover .text {
  font-size: 20px;
  color: var(--text-color);
}

.sidebar .menu-bar {
  height: calc(100% - 50px);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.bottom-content {
  border-top: 1px solid var(--border-color);
  margin-bottom: 100px;
  padding-top: 20px;
}

.menu-bar .mode {
  background: var(--primary-color-light);
  position: relative;
  border-radius: 6px;
  border-right: 1px solid var(--border-color);
}
.menu-bar .mode .moon-sun {
  height: 50px;
  width: 60px;
  display: flex;
  align-items: center;
}
.menu-bar .mode i {
  position: absolute;
  transition: var(--tran-03);
}

.menu-bar .mode i.sun {
  opacity: 0;
}

body.dark .menu-bar .mode i.sun {
  opacity: 1;
}

body.dark .menu-bar .mode i.moon {
  opacity: 0;
}

.menu-bar .mode .toggle-switch {
  right: 0;
  position: absolute;
  height: 100%;
  min-width: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.toggle-switch .switch {
  position: relative;
  height: 22px;
  width: 44px;
  border-radius: 25px;
  background: var(--toggle-color);
}

.switch::before {
  content: "";
  position: absolute;
  height: 15px;
  width: 15px;
  border-radius: 50%;
  top: 50%;
  left: 5px;
  transform: translateY(-50%);
  background-color: var(--siderbar-color);
}

body.dark .switch::before {
  left: 24px;
}

/* Admin dropdown */
.sidebar .admin {
  position: relative;
}
.sidebar .admin-submenu-item .admin-dropdown {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: -25px;
}

.trans-arrow .admin-dropdown {
  transition: var(--tran-03);
  top: 25% !important;
  transform: rotate(90deg) !important;
}

.sidebar.close .admin-dropdown {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: 25px;
}

.sidebar .sub-menu {
  margin-left: 25px;
  display: none;
}

.sidebar.close .sub-menu {
  margin-left: 10px;
  display: none;
}

.show-submenu {
  display: block !important;
}



/* Responsive */
@media (max-width: 1000px) {
  .sidebar {
    width: 73px;
  }

  .sidebar .logo {
    opacity: 0;
    pointer-events: none;
  }

  .sidebar.close .logo {
    opacity: 1;
    pointer-events: auto;
  }

  .sidebar.close {
    width: 250px;
  }
  .sidebar li a .nav-text,
  .sidebar li .admin-submenu-item .nav-text {
    opacity: 0;
    pointer-events: none;
  }
  .sidebar li .admin-submenu-item .admin-dropdown {
    opacity: 0;
  }
  .sidebar.close li .admin-submenu-item .admin-dropdown {
    opacity: 1;
  }

  .sidebar .sub-menu {
    margin-left: 0;
  }

  .sidebar.close li a .nav-text,
  .sidebar.close li .admin-submenu-item .nav-text {
    opacity: 1;
    pointer-events: auto;
  }


  .bottom-content .mode-text {
    opacity: 0;
  }

  .sidebar.close .bottom-content .mode-text {
    opacity: 1;
  }

  .menu-bar .mode i {
    position: absolute;
    transition: var(--tran-03);
  }

  .sidebar header {
    position: relative;
  }
  .sidebar .toggle {
    position: absolute;
    top: 50%;
    right: 0px;
    font-size: 25px;
    transform: translateY(-50%);
    display: flex;
    justify-content: center;
    cursor: pointer;
  }

  .sidebar.close .toggle {
    position: absolute;
    top: 50%;
    font-size: 25px;
    transform: translateY(-50%);
    display: flex;
    justify-content: center;
    width: 100%;
    right: -100px;
  }

  .sidebar .bottom-content .mode .moon-sun .i {
    opacity: 0;
  }
}
        
        </style>
        
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
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
        
        <script>
            const body = document.querySelector("body");
const sideBar = document.querySelector(".sidebar");
const toggle = document.querySelector(".toggle");
const modeSwitch = document.querySelector(".toggle-switch");
const modeText = document.querySelector(".mode-text");

modeSwitch.addEventListener("click", () => {
  body.classList.toggle("dark");
  if (body.classList.contains("dark")) {
    modeText.innerText = "Light Mode";
  } else {
    modeText.innerText = "Dark Mode";
  }
});

toggle.addEventListener("click", () => {
  sideBar.classList.toggle("close");
});

const menuItems = document.querySelectorAll(".admin-submenu-item");
const subMenu = document.querySelectorAll(".sub-menu");

menuItems.forEach((item, index) => {
  item.addEventListener("click", () => {
    item.classList.toggle("trans-arrow");
    subMenu.forEach((subMenu) => {
      subMenu.classList.toggle("show-submenu");
    });
  });
});

        </script>
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
        <script src="js/dashboard.js"></script>
    </body>
</html>
